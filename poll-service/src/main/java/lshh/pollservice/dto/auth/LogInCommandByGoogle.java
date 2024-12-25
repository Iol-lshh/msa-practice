package lshh.pollservice.dto.auth;

import lshh.pollservice.dto.InputDto;

public record LogInCommandByGoogle(
    String googleId,
    Long userId
)  implements InputDto {
}
