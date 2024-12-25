package lshh.pollservice.dto.user;

public record GoogleUserResource(
    String googleId
) implements ThirdPartyUserResource {
}
