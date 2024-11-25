package lshh.pollservice.domain.component.auth;

import lshh.pollservice.domain.entity.user.UserRefresh;

public interface RefreshWriter {
    UserRefresh write(UserRefresh userRefresh);
}
