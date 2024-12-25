package lshh.pollservice.presentation;

import lombok.RequiredArgsConstructor;
import lshh.pollservice.domain.AuthService;
import lshh.pollservice.domain.GoogleAuthService;
import lshh.pollservice.dto.auth.*;
import lshh.pollservice.presentation.component.AuthResponseFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController
public class AuthController implements DefaultExceptionHandlable {
    private final AuthService authService;
    private final AuthResponseFactory responseFactory;
    private final GoogleAuthService googleAuthService;

    @PostMapping("/login")
    public Map<String, Object> logIn(@RequestBody LogInCommand command) {
        AuthenticationSet result = authService.logIn(command);
        return responseFactory.aprove(result);
    }

    @PostMapping("/authenticate")
    public Map<String, Object> authenticate(@RequestBody AuthenticationCommand command) {
        AuthorizationSet result = authService.authenticate(command);
        return responseFactory.aprove(result);
    }

    @PostMapping("/logout")
    public Map<String, Object> logOut(@RequestBody LogOutCommand command) {
        var result = authService.logOut(command);
        return responseFactory.result(result);
    }

    @PostMapping("/refresh")
    public Map<String, Object> refresh(@RequestBody RefreshCommand command) {
        var result = authService.refresh(command);
        return responseFactory.aprove(result);
    }
}
