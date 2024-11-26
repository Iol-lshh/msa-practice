package lshh.pollservice.presentation;

import lombok.RequiredArgsConstructor;
import lshh.pollservice.domain.UserService;
import lshh.pollservice.dto.user.UserDetail;
import lshh.pollservice.dto.user.UserRoleUpdateCommand;
import lshh.pollservice.dto.user.UserSignUpCommand;
import lshh.pollservice.presentation.component.UserResponseFactory;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/role/add")
    public Map<String, Object> addRole(@RequestBody UserRoleUpdateCommand command) {
        var result = service.addRole(command);
        return responseFactory.ok(result);
    }

    @GetMapping("/detail/{loginId}")
    public Map<String, Object> detail(@RequestParam("loginId") String loginId) {
        UserDetail result = service.detail(loginId);
        return responseFactory.ok(result);
    }

}

