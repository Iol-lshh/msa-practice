package lshh.auth.lib.type;

public record AuthorizationSet(
        String access,
        String refresh
)  implements OutputDto {
}
