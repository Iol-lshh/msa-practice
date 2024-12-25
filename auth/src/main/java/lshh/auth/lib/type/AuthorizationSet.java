package lshh.auth.lib.type;

import lshh.core.lib.type.OutputDto;

public record AuthorizationSet(
        String access,
        String refresh
)  implements OutputDto {
}
