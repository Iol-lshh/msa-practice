package lshh.pollservice.dto.user;

import lshh.pollservice.domain.entity.user.UserMember;
import lshh.pollservice.dto.Result;

public record UserSimple(
        Long id,
        String loginId,
        String name
) implements Result {
    public static UserSimple from(UserMember userMember) {
        return new UserSimple(
                userMember.getId(),
                userMember.getLoginId(),
                userMember.getName()
        );
    }
}
