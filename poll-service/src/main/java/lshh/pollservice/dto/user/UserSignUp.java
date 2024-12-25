package lshh.pollservice.dto.user;

import lshh.pollservice.dto.InputDto;

public record UserSignUp(
        String loginId,
        String name,
        String password
) implements InputDto {
}
