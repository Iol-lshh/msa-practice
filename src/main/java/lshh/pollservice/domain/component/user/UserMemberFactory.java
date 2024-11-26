package lshh.pollservice.domain.component.user;

import lshh.pollservice.domain.component.EntityFactory;
import lshh.pollservice.domain.entity.user.UserAuthority;
import lshh.pollservice.domain.entity.user.UserMember;
import lshh.pollservice.dto.user.UserRoleAuthority;
import lshh.pollservice.dto.user.UserSignUpCommand;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMemberFactory implements EntityFactory<UserMember> {
    public UserMember generate(UserSignUpCommand command) {
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
}
