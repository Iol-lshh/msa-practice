package lshh.pollservice.domain.component.thirdparty.google.account;

import lshh.core.lib.component.EntityFactory;
import lshh.pollservice.domain.entity.auth.google.GoogleAccount;
import lshh.pollservice.domain.entity.user.UserMember;
import lshh.auth.lib.thirdparty.google.GoogleUserResource;
import org.springframework.stereotype.Component;

@Component
public class GoogleAccountFactory implements EntityFactory<GoogleAccount> {
    public GoogleAccount generate(GoogleUserResource resource, UserMember user) {
        return GoogleAccount.builder()
                .googleId(resource.googleId())
                .userId(user.getId())
                .build();
    }
}
