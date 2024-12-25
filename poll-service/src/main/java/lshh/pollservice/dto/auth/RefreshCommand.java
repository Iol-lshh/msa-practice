package lshh.pollservice.dto.auth;

import lshh.pollservice.dto.InputDto;

public record RefreshCommand(
        String refresh
)  implements InputDto {
}
