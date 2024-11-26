package lshh.pollservice.dto.auth;

import lshh.pollservice.dto.Request;

public record LogOutCommand(
        String refresh
)  implements Request {
}
