package lshh.pollservice.dto.user;

import lshh.pollservice.domain.entity.user.UserMember;
import lshh.core.lib.type.OutputDto;

public record UserSimple(
        Long id,
        String loginId,
        String name
) implements OutputDto {
    public static UserSimple from(UserMember userMember) {
        return new UserSimple(
                userMember.getId(),
                userMember.getLoginId(),
                userMember.getName()
        );
    }
}
