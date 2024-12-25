package lshh.pollservice.dto.auth;

import lshh.core.lib.type.InputDto;

public record LogInCommandByGoogle(
    String googleId,
    Long userId
)  implements InputDto {
}
