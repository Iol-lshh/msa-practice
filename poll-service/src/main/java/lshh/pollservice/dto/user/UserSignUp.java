package lshh.pollservice.dto.user;

import lshh.core.lib.type.InputDto;

public record UserSignUp(
        String loginId,
        String name,
        String password
) implements InputDto {
}
