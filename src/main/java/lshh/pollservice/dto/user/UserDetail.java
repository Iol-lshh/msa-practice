package lshh.pollservice.dto.user;

import lshh.pollservice.domain.entity.user.UserAuthority;
import lshh.pollservice.domain.entity.user.UserMember;

import java.util.List;

public record UserDetail(
        Long id,
        String loginId,
        String name,
        String password,
        List<UserRoleAuthority> roles
) {
    public static UserDetail from(UserMember userMember) {
        List<UserRoleAuthority> roles = userMember.getUserAuthorities().stream()
                .map(UserAuthority::getRole)
                .toList();
        return new UserDetail(
                userMember.getId(),
                userMember.getLoginId(),
                userMember.getName(),
                userMember.getPassword(),
                roles
        );
    }
}
