package lshh.pollservice.domain;

import lombok.extern.slf4j.Slf4j;
import lshh.pollservice.dto.user.UserSignUpCommand;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Nested
    class SignUpTest {
         @Test
         void signUp() {
             UserSignUpCommand command = new UserSignUpCommand("testSignUp", "tester", "password");
             var result = userService.signUp(command);
             assertNotNull(result);
             assertNotNull(result.id());
         }
    }

}