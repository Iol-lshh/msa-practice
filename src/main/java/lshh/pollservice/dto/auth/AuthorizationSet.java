package lshh.pollservice.dto.auth;

import lshh.pollservice.dto.Result;

public record AuthorizationSet(
        String access,
        String refresh
)  implements Result {
}
