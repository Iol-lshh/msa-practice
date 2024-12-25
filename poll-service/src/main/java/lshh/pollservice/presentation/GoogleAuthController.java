package lshh.pollservice.presentation;

import lombok.RequiredArgsConstructor;
import lshh.pollservice.domain.GoogleAuthService;
import lshh.pollservice.dto.auth.LogInCommandByGoogle;
import lshh.pollservice.dto.user.UserAddAccountCommandByGoogle;
import lshh.pollservice.dto.user.UserAuthorizeForSignUpCommandByGoogle;
import lshh.pollservice.dto.user.UserSignUpByGoogle;
import lshh.pollservice.presentation.component.AuthResponseFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth/google")
public class GoogleAuthController {
    private final AuthResponseFactory responseFactory;
    private final GoogleAuthService googleAuthService;

    @PostMapping("/authorize")
    public Map<String, Object> authorizeForSignUp(@RequestBody UserAuthorizeForSignUpCommandByGoogle command) {
        var result = googleAuthService.authorizeForSignUp(command);
        return responseFactory.ok(result);
    }

    @PostMapping("/siginup")
    public Map<String, Object> signUp(@RequestBody UserSignUpByGoogle command) {
        var result = googleAuthService.signUp(command);
        return responseFactory.ok(result);
    }

    @PostMapping("/login")
    public Map<String, Object> logIn(LogInCommandByGoogle command) {
        var result = googleAuthService.login(command);
        return responseFactory.aprove(result);
    }

    @PostMapping("/add")
    public Map<String, Object> addAccount(UserAddAccountCommandByGoogle command) {
        var result = googleAuthService.addAccount(command);
        return responseFactory.ok(result);
    }
}
