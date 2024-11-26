package lshh.pollservice.dto.auth;

import lshh.pollservice.dto.Result;

public record AuthenticationSet (
    String authentication
) implements Result {
}
