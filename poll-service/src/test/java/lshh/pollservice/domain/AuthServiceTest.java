package lshh.pollservice.domain;

import lombok.extern.slf4j.Slf4j;
import lshh.pollservice.common.exception.PersistenceNotFoundException;
import lshh.pollservice.domain.component.user.UserMemberRepository;
import lshh.pollservice.domain.entity.user.UserMember;
import lshh.pollservice.dto.DefaultOutput;
import lshh.pollservice.dto.auth.*;
import lshh.pollservice.dto.user.UserSignUp;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class AuthServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;
    @Autowired
    private UserMemberRepository userMemberRepository;


    @Nested
    class LoadUserRoleUpdateMemberByUsernameTest {
        @Test
        void loadUserByUsername() {
            UserSignUp command = new UserSignUp("loadUserByUsername", "tester", "password");
            userService.signUp(command);

            UserDetails user = authService.loadUserByUsername("loadUserByUsername");

            assertNotNull(user);
            assertNotNull(user.getUsername());
        }
    }

    @Nested
    class LogInTest{
        @Test
        void logIn() {
            UserSignUp command = new UserSignUp("logInTest", "tester", "password");
            userService.signUp(command);

            LogInCommand logInCommand = new LogInCommand("logInTest", "password");
            AuthenticationSet result = authService.logIn(logInCommand);

            assertNotNull(result);
            assertNotNull(result.authentication());
        }
    }

    @Nested
    class AuthenticateTest{
        @Test
        void authenticate() {
            UserSignUp command = new UserSignUp("authenticate", "tester", "password");
            userService.signUp(command);
            LogInCommand logInCommand = new LogInCommand("authenticate", "password");
            AuthenticationSet authenticationSet = authService.logIn(logInCommand);

            AuthenticationCommand authenticationCommand = new AuthenticationCommand(authenticationSet.authentication());
            AuthorizationSet result = authService.authenticate(authenticationCommand);

            log.info("AuthorizationSet: "+result);
            assertNotNull(result);
            assertNotNull(result.access());
            assertNotNull(result.refresh());
        }
    }

    @Nested
    class RefreshTest{
        @Test
        void refresh() {
            UserSignUp command = new UserSignUp("refresh", "tester", "password");
            userService.signUp(command);
            LogInCommand logInCommand = new LogInCommand("refresh", "password");
            AuthenticationSet authenticationSet = authService.logIn(logInCommand);
            AuthenticationCommand authenticationCommand = new AuthenticationCommand(authenticationSet.authentication());
            AuthorizationSet authorizationSet = authService.authenticate(authenticationCommand);

            AuthorizationSet result = authService.refresh(new RefreshCommand(authorizationSet.refresh()));

            log.info("Before AuthorizationSet: "+authorizationSet);
            log.info("After AuthorizationSet: " + result);
            assertNotNull(result);
            assertNotNull(result.access());
            assertNotNull(result.refresh());
        }
    }

    @Nested
    class GenerateAuthenticationSetTest{
        @Test
        @Transactional
        void generateAuthenticationSet() {
            UserSignUp command = new UserSignUp("generateAuthenticationSet", "tester", "password");
            userService.signUp(command);
            UserMember userMember = userMemberRepository.getByLoginId("generateAuthenticationSet");

            AuthenticationSet result = authService.generateAuthenticationSet(userMember);

            assertNotNull(result);
            assertNotNull(result.authentication());
        }
    }

    @Nested
    class GenerateAuthorizationSetTest{
        @Test
        @Transactional
        void generateAuthorizationSet() {
            UserSignUp command = new UserSignUp("generateAuthenticationSet", "tester", "password");
            userService.signUp(command);
            UserMember userMember = userMemberRepository.getByLoginId("generateAuthenticationSet");

            AuthorizationSet result = authService.generateAuthorizationSet(userMember);

            assertNotNull(result);
            assertNotNull(result.access());
            assertNotNull(result.refresh());
        }

        @Test
        @Transactional
        void generateAuthorizationSet2() {
            UserSignUp command = new UserSignUp("generateAuthenticationSet2", "tester", "password");
            userService.signUp(command);
            UserMember userMember = userMemberRepository.getByLoginId("generateAuthenticationSet2");
            AuthorizationSet authorizationSet = authService.generateAuthorizationSet(userMember);

            AuthorizationSet result = authService.generateAuthorizationSet(userMember, authorizationSet.refresh());

            assertNotNull(result);
            assertNotNull(result.access());
            assertNotNull(result.refresh());
            assertEquals(authorizationSet.refresh(), result.refresh());
        }
    }

    @Nested
    class LogOutTest{
        @Test
        @Transactional
        void logOut() {
            UserSignUp command = new UserSignUp("logOut", "tester", "password");
            userService.signUp(command);
            LogInCommand logInCommand = new LogInCommand("logOut", "password");
            AuthenticationSet authenticationSet = authService.logIn(logInCommand);
            AuthenticationCommand authenticationCommand = new AuthenticationCommand(authenticationSet.authentication());
            AuthorizationSet authorizationSet = authService.authenticate(authenticationCommand);

            DefaultOutput result = authService.logOut(new LogOutCommand(authorizationSet.refresh()));

            assertNotNull(result);
            assertEquals("확인", result.getMessage());
            assertThrows(PersistenceNotFoundException.class, ()->authService.refresh(new RefreshCommand(authorizationSet.refresh())));
        }
    }

}