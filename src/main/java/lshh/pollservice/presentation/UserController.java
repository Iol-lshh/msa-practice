package lshh.pollservice.presentation;

import lombok.RequiredArgsConstructor;
import lshh.pollservice.domain.UserService;
import lshh.pollservice.dto.user.UserSignUpCommand;
import lshh.pollservice.presentation.component.UserResponseFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController implements DefaultExceptionHandlable {
    private final UserService service;
    private final UserResponseFactory responseFactory;

    @PostMapping("/signup")
    public Map<String, Object> signUp(@RequestBody UserSignUpCommand command) {
        var result = service.signUp(command);
        return responseFactory.ok(result);
    }
}
