package lshh.pollservice.dto.user;

import lshh.pollservice.domain.entity.user.User;
import lshh.pollservice.dto.Result;

public record UserSimple(
        Long id,
        String loginId,
        String name
) implements Result {
    public static UserSimple from(User user) {
        return new UserSimple(
                user.getId(),
                user.getLoginId(),
                user.getName()
        );
    }
}
