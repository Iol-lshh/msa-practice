package lshh.pollservice.presentation;

import lombok.extern.slf4j.Slf4j;
import lshh.pollservice.domain.AuthService;
import lshh.pollservice.domain.UserService;
import lshh.pollservice.dto.auth.AuthenticationCommand;
import lshh.pollservice.dto.auth.AuthenticationSet;
import lshh.pollservice.dto.auth.AuthorizationSet;
import lshh.pollservice.dto.auth.LogInCommand;
import lshh.pollservice.dto.user.UserSignUpCommand;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class PollControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;

    @Nested
    class CreateTest {
        @Test
        void create() {

        }
    }

    @Nested
    class ListTest {
        @Test
        void all() throws Exception {
            UserSignUpCommand command = new UserSignUpCommand("pollAll", "tester", "password");
            userService.signUp(command);
            LogInCommand logInCommand = new LogInCommand("pollAll", "password");
            AuthenticationSet authenticationSet = authService.logIn(logInCommand);
            AuthenticationCommand authenticationCommand = new AuthenticationCommand(authenticationSet.authentication());
            AuthorizationSet authorizationSet = authService.authenticate(authenticationCommand);

            var result = mockMvc.perform(get("/poll/all")
                            .header("Authorization", "Bearer " + authorizationSet.access())
                            .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse();

            log.info("result: " + result.getContentAsString());
        }
    }

    @Nested
    class DetailTest {
        @Test
        void detail() {
        }
    }


}