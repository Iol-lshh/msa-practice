package lshh.pollservice.dto.auth;

import lshh.core.lib.type.InputDto;

public record RefreshCommand(
        String refresh
)  implements InputDto {
}
