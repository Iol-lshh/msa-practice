package lshh.pollservice.dto.auth;

import lshh.pollservice.dto.InputDto;

public record LogOutCommand(
        String refresh
)  implements InputDto {
}
