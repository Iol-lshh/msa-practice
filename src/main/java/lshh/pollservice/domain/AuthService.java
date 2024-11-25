package lshh.pollservice.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lshh.pollservice.common.lib.JwtHelper;
import lshh.pollservice.domain.component.auth.RefreshFactory;
import lshh.pollservice.domain.component.auth.RefreshWriter;
import lshh.pollservice.domain.component.user.UserRepository;
import lshh.pollservice.domain.entity.user.User;
import lshh.pollservice.domain.entity.user.UserRefresh;
import lshh.pollservice.dto.DefaultResult;
import lshh.pollservice.dto.auth.AuthorizationSet;
import lshh.pollservice.dto.auth.LoginCommand;
import lshh.pollservice.dto.auth.LogoutCommand;
import lshh.pollservice.dto.auth.RefreshCommand;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService implements UserDetailsService {
    private final UserRepository repository;
    private final JwtHelper jwtHelper;
    private final RefreshFactory refreshFactory;
    private final RefreshWriter refreshWriter;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.getUserDetailsByLoginId(username);
    }

    @Transactional
    public AuthorizationSet login(LoginCommand command) {
        User user = repository.getByLoginIdAndPassword(command.loginId(), command.password());
        return generateAuthorizationSet(user);
    }

    @Transactional
    public AuthorizationSet refresh(RefreshCommand command) {
        User user = repository.getByRefreshToken(command.refresh());
        return generateAuthorizationSet(user);
    }

    AuthorizationSet generateAuthorizationSet(User user){
        UserRefresh userRefresh = refreshFactory.generateByMember(user);
        refreshWriter.write(userRefresh);
        String accessToken = jwtHelper.generateToken(user);
        String refreshToken = userRefresh.getRefresh();
        log.info("login: { loginId: "+user.getLoginId()+", access: "+accessToken+", refresh: "+refreshToken + " }");
        return new AuthorizationSet(accessToken, refreshToken);
    }

    public DefaultResult logout(LogoutCommand command) {

            return null;
    }
}
