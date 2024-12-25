package lshh.pollservice.dto.auth;

import lshh.core.lib.type.InputDto;

public record AuthenticationCommand (
    String authenticationToken
) implements InputDto {
}
