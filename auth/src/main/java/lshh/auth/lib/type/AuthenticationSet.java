package lshh.auth.lib.type;

import lshh.core.lib.type.OutputDto;

public record AuthenticationSet (
    String authentication
) implements OutputDto {
}
