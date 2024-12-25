package lshh.pollservice.domain.component.thirdparty.google.account;

import lombok.RequiredArgsConstructor;
import lshh.pollservice.common.bean.GoogleAuthenticationClient;
import lshh.pollservice.common.lib.GoogleAuthorization;
import lshh.pollservice.dto.auth.LogInCommandByGoogle;
import lshh.pollservice.dto.user.GoogleUserResource;
import lshh.pollservice.dto.user.UserAddAccountCommandByGoogle;
import lshh.pollservice.dto.user.UserAuthorizeForSignUpCommandByGoogle;
import lshh.pollservice.dto.user.UserSignUpByGoogle;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GoogleAuthManager {
    private final GoogleAuthenticationClient client;

    public GoogleUserResource getUserResource(UserSignUpByGoogle command) {
        // todo
        // 2 단계
        // authorization 받아옴
        // userResource 받아옴
        return null;
    }

    public GoogleAuthorization getAuthorization(UserAuthorizeForSignUpCommandByGoogle command) {
        // todo
        // refresh 필요시 refresh
        return null;
    }

    public GoogleAuthorization getAuthorization(LogInCommandByGoogle command) {
        // todo
        // refresh 필요시 refresh
        return null;
    }

    public GoogleUserResource getUserResource(UserAddAccountCommandByGoogle command) {
        // todo
        return null;
    }
}
