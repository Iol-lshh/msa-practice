package lshh.pollservice.dto.auth;

import lshh.core.lib.type.InputDto;

public record LogInCommand(
        String loginId,
        String password
)  implements InputDto {
}
