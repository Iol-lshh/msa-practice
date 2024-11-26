package lshh.pollservice.dto.user;

import lshh.pollservice.dto.Request;

public record UserSignUpCommand(
        String loginId,
        String name,
        String password
) implements Request {
}
