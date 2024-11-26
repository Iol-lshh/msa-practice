package lshh.pollservice.dto.auth;

import lshh.pollservice.dto.Request;

public record RefreshCommand(
        String refresh
)  implements Request {
}
