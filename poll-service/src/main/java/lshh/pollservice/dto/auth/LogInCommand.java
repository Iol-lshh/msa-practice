package lshh.pollservice.dto.auth;

import lshh.pollservice.dto.InputDto;

public record LogInCommand(
        String loginId,
        String password
)  implements InputDto {
}
