package lshh.pollservice.common.bean;

import lombok.Getter;
import lshh.pollservice.domain.UserService;
import lshh.pollservice.dto.user.UserRoleAuthority;
import lshh.pollservice.dto.user.UserRoleUpdate;
import lshh.pollservice.dto.user.UserSignUp;
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
        UserSignUp userSignUp = new UserSignUp("root", "root", "1234");
        userService.signUp(userSignUp);
        UserRoleUpdate userRoleCommand = new UserRoleUpdate("root", UserRoleAuthority.USER);
        UserRoleUpdate adminRoleCommand = new UserRoleUpdate("root", UserRoleAuthority.ADMIN);
        userService.addRole(userRoleCommand);
        userService.addRole(adminRoleCommand);
    }
}
