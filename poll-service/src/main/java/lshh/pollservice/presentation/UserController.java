package lshh.pollservice.presentation;

import lombok.RequiredArgsConstructor;
import lshh.pollservice.domain.GoogleAuthService;
import lshh.pollservice.domain.UserService;
import lshh.pollservice.dto.user.UserDetail;
import lshh.pollservice.dto.user.UserRoleUpdate;
import lshh.pollservice.dto.user.UserSignUp;
import lshh.pollservice.presentation.component.UserResponseFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController implements DefaultExceptionHandlable {
    private final UserService service;
    private final UserResponseFactory responseFactory;
    private final GoogleAuthService googleAuthService;

    @PostMapping("/signup")
    public Map<String, Object> signUp(@RequestBody UserSignUp command) {
        var result = service.signUp(command);
        return responseFactory.ok(result);
    }


    @PostMapping("/role/add")
    public Map<String, Object> addRole(@RequestBody UserRoleUpdate command) {
        var result = service.addRole(command);
        return responseFactory.ok(result);
    }

    @GetMapping("/detail/{loginId}")
    public Map<String, Object> detail(@RequestParam("loginId") String loginId) {
        UserDetail result = service.detail(loginId);
        return responseFactory.ok(result);
    }

}

