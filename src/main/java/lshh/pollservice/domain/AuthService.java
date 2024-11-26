package lshh.pollservice.domain;

import lombok.extern.slf4j.Slf4j;
import lshh.pollservice.common.lib.ClockManager;
import lshh.pollservice.common.lib.HashTokenHelper;
import lshh.pollservice.common.lib.JwtHelper;
import lshh.pollservice.domain.component.user.UserMemberRepository;
import lshh.pollservice.domain.entity.user.UserMember;
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
    private final UserMemberRepository repository;
    private final JwtHelper jwtHelper;
    private final HashTokenHelper refreshTokenHelper;
    private final HashTokenHelper authenticationTokenHelper;
    private final ClockManager clockManager;

    public AuthService(
            UserMemberRepository repository,
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
        UserMember userMember = repository.getByLoginIdAndPassword(command.loginId(), command.password());
        var result = generateAuthenticationSet(userMember);
        repository.save(userMember);
        return result;
    }

    @Transactional
    public AuthorizationSet authenticate(AuthenticationCommand command) {
        UserMember userMember = repository.getByAuthenticationToken(command.authenticationToken());
        var result = generateAuthorizationSet(userMember);
        repository.save(userMember);
        return result;
    }

    @Transactional
    public AuthorizationSet refresh(RefreshCommand command) {
        String refreshToken = command.refresh();
        UserMember userMember = repository.getByRefreshToken(command.refresh());
        if(userMember.isNeedRefresh(refreshToken)){
            var result = generateAuthorizationSet(userMember);
            repository.save(userMember);
            return result;
        }
        var result = generateAuthorizationSet(userMember, refreshToken);
        repository.save(userMember);
        return result;
    }

    AuthenticationSet generateAuthenticationSet(UserMember userMember){
        UserAuthentication authentication = userMember.authenticate(authenticationTokenHelper, clockManager.getClock());
        log.info("generate authentication set: { loginId: {}, access: {} }", userMember.getLoginId(), authentication.getToken());
        return new AuthenticationSet(authentication.getToken());
    }

    AuthorizationSet generateAuthorizationSet(UserMember userMember){
        UserRefresh refresh =  userMember.refreshAuthorization(refreshTokenHelper, clockManager.getClock());
        return generateAuthorizationSet(userMember, refresh.getToken());
    }

    AuthorizationSet generateAuthorizationSet(UserMember userMember, String refreshToken){
        String accessToken = jwtHelper.generateToken(userMember);
        log.info("generate authorization set: { loginId: {}, access: {}, refresh: {} }", userMember.getLoginId(), accessToken, refreshToken);
        return new AuthorizationSet(accessToken, refreshToken);
    }

    public DefaultResult logOut(LogOutCommand command) {
        UserMember userMember = repository.getByRefreshToken(command.refresh());
        userMember.logOut(command.refresh());
        log.info("logout: { loginId: {} }", userMember.getLoginId());
        return DefaultResult.OK;
    }
}
