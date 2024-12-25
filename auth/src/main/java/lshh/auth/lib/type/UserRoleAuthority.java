package lshh.auth.lib.type;

import lombok.Getter;

@Getter
public enum UserRoleAuthority{
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN"),
    TEMP_USER("ROLE_TEMPORARY_USER")
    ;

    private final String authorityName;

    UserRoleAuthority(String authorityName) {
        this.authorityName = authorityName;
    }
}
