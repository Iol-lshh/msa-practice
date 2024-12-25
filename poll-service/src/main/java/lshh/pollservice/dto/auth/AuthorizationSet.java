package lshh.pollservice.dto.auth;

import lshh.pollservice.dto.OutputDto;

public record AuthorizationSet(
        String access,
        String refresh
)  implements OutputDto {
}
