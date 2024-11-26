package lshh.pollservice.dto.auth;

import lshh.pollservice.dto.Request;

public record LogInCommand(
        String loginId,
        String password
)  implements Request {
}
