package lshh.pollservice.dto.user;

import lshh.core.lib.type.InputDto;
import lshh.auth.lib.type.UserRoleAuthority;

public record UserRoleUpdate(
        String loginId,
        UserRoleAuthority role
) implements InputDto {
}
