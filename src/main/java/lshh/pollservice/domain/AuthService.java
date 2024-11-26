package lshh.pollservice.domain;

import lombok.extern.slf4j.Slf4j;
import lshh.pollservice.common.lib.ClockManager;
import lshh.pollservice.common.lib.HashTokenHelper;
import lshh.pollservice.common.lib.JwtHelper;
import lshh.pollservice.domain.component.user.UserRepository;
import lshh.pollservice.domain.entity.user.User;
import lshh.pollservice.domain.entity.user.UserAuthentication;
import lshh.pollservice.domain.entity.user.UserRefresh;
import lshh.pollservice.dto.DefaultResult;
import lshh.pollservice.dto.auth.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class AuthService implements UserDetailsService {
    private final UserRepository repository;
    private final JwtHelper jwtHelper;
    private final HashTokenHelper refreshTokenHelper;
    private final HashTokenHelper authenticationTokenHelper;
    private final ClockManager clockManager;

    public AuthService(
            UserRepository repository,
            JwtHelper jwtHelper,
            ClockManager clockManager,
            @Qualifier("refreshTokenHelper") HashTokenHelper refreshTokenHelper,
            @Qualifier("authenticationTokenHelper") HashTokenHelper authenticationTokenHelper
    ) {
        this.repository = repository;
        this.jwtHelper = jwtHelper;
        this.clockManager = clockManager;
        this.refreshTokenHelper = refreshTokenHelper;
        this.authenticationTokenHelper = authenticationTokenHelper;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.getUserDetailsByLoginId(username);
    }

    @Transactional
    public AuthenticationSet logIn(LogInCommand command) {
        User user = repository.getByLoginIdAndPassword(command.loginId(), command.password());
        var result = generateAuthenticationSet(user);
        repository.save(user);
        return result;
    }

    @Transactional
    public AuthorizationSet authenticate(AuthenticationCommand command) {
        User user = repository.getByAuthenticationToken(command.authenticationToken());
        var result = generateAuthorizationSet(user);
        repository.save(user);
        return result;
    }

    @Transactional
    public AuthorizationSet refresh(RefreshCommand command) {
        String refreshToken = command.refresh();
        User user = repository.getByRefreshToken(command.refresh());
        if(user.isNeedRefresh(refreshToken)){
            var result = generateAuthorizationSet(user);
            repository.save(user);
            return result;
        }
        var result = generateAuthorizationSet(user, refreshToken);
        repository.save(user);
        return result;
    }

    AuthenticationSet generateAuthenticationSet(User user){
        UserAuthentication authentication = user.authenticate(authenticationTokenHelper, clockManager.getClock());
        log.info("generate authentication set: { loginId: {}, access: {} }", user.getLoginId(), authentication.getToken());
        return new AuthenticationSet(authentication.getToken());
    }

    AuthorizationSet generateAuthorizationSet(User user){
        UserRefresh refresh =  user.refreshAuthorization(refreshTokenHelper, clockManager.getClock());
        return generateAuthorizationSet(user, refresh.getToken());
    }

    AuthorizationSet generateAuthorizationSet(User user, String refreshToken){
        String accessToken = jwtHelper.generateToken(user);
        log.info("generate authorization set: { loginId: {}, access: {}, refresh: {} }", user.getLoginId(), accessToken, refreshToken);
        return new AuthorizationSet(accessToken, refreshToken);
    }

    public DefaultResult logOut(LogOutCommand command) {
        User user = repository.getByRefreshToken(command.refresh());
        user.logOut(command.refresh());
        log.info("logout: { loginId: {} }", user.getLoginId());
        return DefaultResult.OK;
    }
}
