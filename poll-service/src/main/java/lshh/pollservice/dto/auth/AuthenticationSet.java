package lshh.pollservice.dto.auth;

import lshh.pollservice.dto.OutputDto;

public record AuthenticationSet (
    String authentication
) implements OutputDto {
}
