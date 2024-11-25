package lshh.pollservice.dto.auth;

public record LoginCommand(
        String loginId,
        String password
) {
}
