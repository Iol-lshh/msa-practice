package lshh.pollservice.dto.auth;

import lshh.core.lib.type.InputDto;

public record LogOutCommand(
        String refresh
)  implements InputDto {
}
