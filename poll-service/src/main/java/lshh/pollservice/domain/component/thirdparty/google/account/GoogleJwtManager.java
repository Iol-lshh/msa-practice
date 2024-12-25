package lshh.pollservice.domain.component.thirdparty.google.account;

import lombok.RequiredArgsConstructor;
import lshh.auth.lib.thirdparty.google.GoogleAuthorization;
import lshh.auth.lib.util.JwtHelper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GoogleJwtManager {
    private final JwtHelper googleJwtHelper;

    public String generateToken(GoogleAuthorization authorization) {

        // todo
        return "";
    }
}
