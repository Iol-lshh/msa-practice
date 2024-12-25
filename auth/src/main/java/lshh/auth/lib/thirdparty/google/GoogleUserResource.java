package lshh.auth.lib.thirdparty.google;

import lshh.auth.lib.type.ThirdPartyUserResource;

public record GoogleUserResource(
    String googleId
) implements ThirdPartyUserResource {
}
