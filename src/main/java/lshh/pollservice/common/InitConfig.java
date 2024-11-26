package lshh.pollservice.common;

import lombok.Getter;
import lshh.pollservice.domain.UserService;
import lshh.pollservice.dto.user.UserRoleAuthority;
import lshh.pollservice.dto.user.UserRoleUpdateCommand;
import lshh.pollservice.dto.user.UserSignUpCommand;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitConfig {
    @Getter
    private static InitConfig instance;
    private final UserService userService;

    public InitConfig(UserService userService) {
        this.userService = userService;
        instance = this;
    }

    public void initConfig(){
        signUpRootUser();
    }

    private void signUpRootUser() {
        UserSignUpCommand userSignUpCommand = new UserSignUpCommand("root", "root", "1234");
        userService.signUp(userSignUpCommand);
        UserRoleUpdateCommand userRoleCommand = new UserRoleUpdateCommand("root", UserRoleAuthority.USER);
        UserRoleUpdateCommand adminRoleCommand = new UserRoleUpdateCommand("root", UserRoleAuthority.ADMIN);
        userService.addRole(userRoleCommand);
        userService.addRole(adminRoleCommand);
    }
}
