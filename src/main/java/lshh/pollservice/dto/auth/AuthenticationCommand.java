package lshh.pollservice.dto.auth;

import lshh.pollservice.dto.Request;

public record AuthenticationCommand (
    String authenticationToken
) implements Request {
}
