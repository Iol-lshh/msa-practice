package lshh.pollservice.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lshh.core.lib.exception.PersistenceDuplicatedException;
import lshh.core.lib.util.ClockManager;
import lshh.auth.lib.thirdparty.google.GoogleAuthorization;
import lshh.pollservice.domain.component.thirdparty.google.account.GoogleAuthManager;
import lshh.auth.lib.util.HashTokenHelper;
import lshh.pollservice.domain.component.thirdparty.google.account.GoogleAccountFactory;
import lshh.pollservice.domain.component.thirdparty.google.account.GoogleAccountRepository;
import lshh.pollservice.domain.component.thirdparty.google.account.GoogleJwtManager;
import lshh.pollservice.domain.component.user.UserMemberFactory;
import lshh.pollservice.domain.component.user.UserMemberRepository;
import lshh.pollservice.domain.entity.auth.google.GoogleAccount;
import lshh.pollservice.domain.entity.user.UserAuthentication;
import lshh.pollservice.domain.entity.user.UserMember;
import lshh.core.lib.type.OutputDto;
import lshh.auth.lib.type.AuthenticationSet;
import lshh.pollservice.dto.auth.LogInCommandByGoogle;
import lshh.auth.lib.thirdparty.google.GoogleUserResource;
import lshh.pollservice.dto.user.UserAddAccountByGoogle;
import lshh.pollservice.dto.user.UserAuthorizeForSignUpByGoogle;
import lshh.pollservice.dto.user.UserSignUpByGoogle;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class GoogleAuthService {
    private final GoogleAccountRepository repository;
    private final GoogleAccountFactory factory;
    private final GoogleAuthManager manager;
    private final UserMemberRepository userMemberRepository;
    private final UserMemberFactory userMemberFactory;
    private final HashTokenHelper authenticationTokenHelper;
    private final ClockManager clockManager;
    private final GoogleJwtManager jwtManager;


    @Transactional(readOnly = true)
    public String authorizeForSignUp(UserAuthorizeForSignUpByGoogle command) {
        repository.findByGoogleId(command.googleId()).ifPresent(googleAuthentication -> {
            throw new PersistenceDuplicatedException("GoogleAccount already exists");
        });
        GoogleAuthorization authorization = manager.getAuthorization(command);
        // jwt로 정보를 말아 보낸다.
        return jwtManager.generateToken(authorization);
    }

    @Transactional
    public OutputDto signUp(UserSignUpByGoogle command){
        GoogleUserResource userResource = manager.getUserResource(command);
        UserMember userMember = userMemberFactory.generate(userResource);
        GoogleAccount account = factory.generate(userResource, userMember);
        repository.save(account);
        userMemberRepository.save(userMember);
        return OutputDto.success();
    }

    @Transactional(readOnly = true)
    public AuthenticationSet login(LogInCommandByGoogle command) {
        GoogleAccount account = repository.getByGoogleId(command.googleId());
        manager.getAuthorization(command);
        UserMember userMember = userMemberRepository.getById(account.getUserId());
        return generateAuthenticationSet(userMember);
    }

    AuthenticationSet generateAuthenticationSet(UserMember userMember){
        UserAuthentication authentication = userMember.authenticate(authenticationTokenHelper, clockManager.getClock());
        log.info("generate authentication set: { loginId: {}, authentication: {} }", userMember.getLoginId(), authentication.getToken());
        return new AuthenticationSet(authentication.getToken());
    }

    @Transactional
    public OutputDto addAccount(UserAddAccountByGoogle command) {
        repository.findByGoogleId(command.googleId()).ifPresent(googleAuthentication -> {
            throw new PersistenceDuplicatedException("GoogleAccount already exists");
        });
        UserMember userMember = userMemberRepository.getById(command.userId());
        GoogleUserResource userResource = manager.getUserResource(command);
        GoogleAccount account = factory.generate(userResource, userMember);
        repository.save(account);
        return OutputDto.success();
    }


}
