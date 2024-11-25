package lshh.pollservice.presentation;

import lombok.RequiredArgsConstructor;
import lshh.pollservice.domain.AuthService;
import lshh.pollservice.dto.auth.LoginCommand;
import lshh.pollservice.dto.auth.LoginCommandByGoogle;
import lshh.pollservice.dto.auth.LogoutCommand;
import lshh.pollservice.dto.auth.RefreshCommand;
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

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginCommand command) {
        var result = authService.login(command);
        return responseFactory.aprove(result);
    }

    @PostMapping("/login/google")
    public Map<String, Object> loginByGoogle(@RequestBody LoginCommandByGoogle command) {
        return null;
    }

    @PostMapping("/logout")
    public Map<String, Object> logout(@RequestBody LogoutCommand command) {
        var result = authService.logout(command);
        return responseFactory.result(result);
    }

    @PostMapping("/refresh")
    public Map<String, Object> refresh(@RequestBody RefreshCommand command) {
        var result = authService.refresh(command);
        return responseFactory.aprove(result);
    }
}
