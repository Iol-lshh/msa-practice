package lshh.pollservice.domain.component.auth;

import lshh.pollservice.domain.entity.user.User;
import lshh.pollservice.domain.entity.user.UserRefresh;
import org.springframework.stereotype.Component;

@Component
public class RefreshFactory {
    public UserRefresh generateByMember(User user) {
        // todo
        return null;
    }
}
