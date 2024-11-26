package lshh.pollservice.dto.user;

import lshh.pollservice.dto.Request;

public record UserRoleUpdateCommand(
        String loginId,
        UserRoleAuthority role
) implements Request {
}
