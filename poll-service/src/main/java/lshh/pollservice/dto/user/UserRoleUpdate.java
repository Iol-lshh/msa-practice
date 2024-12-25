package lshh.pollservice.dto.user;

import lshh.pollservice.dto.InputDto;

public record UserRoleUpdate(
        String loginId,
        UserRoleAuthority role
) implements InputDto {
}
