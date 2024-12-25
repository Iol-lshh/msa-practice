package lshh.pollservice.domain.component.user;

import lshh.core.lib.component.EntityFactory;
import lshh.pollservice.domain.entity.user.UserAuthority;
import lshh.pollservice.domain.entity.user.UserMember;
import lshh.auth.lib.type.ThirdPartyUserResource;
import lshh.auth.lib.type.UserRoleAuthority;
import lshh.pollservice.dto.user.UserSignUp;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMemberFactory implements EntityFactory<UserMember> {
    public UserMember generate(UserSignUp command) {
        List<UserAuthority> authorities = new ArrayList<>();
        authorities.add(UserAuthority.builder().role(UserRoleAuthority.TEMP_USER).build());
        return UserMember.builder()
                .loginId(command.loginId())
                .name(command.name())
                .password(command.password())
                .userAuthentication(new ArrayList<>())
                .userRefresh(new ArrayList<>())
                .userAuthorities(authorities)
                .build();
    }

    public UserMember generate(ThirdPartyUserResource userResource){
        // todo
        return null;
    }
}
