package lshh.pollservice.domain.component.user;

import lshh.pollservice.domain.component.EntityFactory;
import lshh.pollservice.domain.entity.user.User;
import lshh.pollservice.dto.user.UserSignUpCommand;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserFactory implements EntityFactory<User> {
    public User generate(UserSignUpCommand command) {
        return User.builder()
                .loginId(command.loginId())
                .name(command.name())
                .password(command.password())
                .userAuthentication(new ArrayList<>())
                .userRefresh(new ArrayList<>())
                .build();
    }
}
