package lshh.pollservice.dto.auth;

import lshh.pollservice.dto.InputDto;

public record AuthenticationCommand (
    String authenticationToken
) implements InputDto {
}
