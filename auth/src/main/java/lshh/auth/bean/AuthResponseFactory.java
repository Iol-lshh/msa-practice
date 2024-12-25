package lshh.auth.bean;

import lshh.core.lib.component.AbstractResponseFactory;
import lshh.auth.lib.type.AuthenticationSet;
import lshh.auth.lib.type.AuthorizationSet;
import lshh.core.lib.type.ResponseState;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AuthResponseFactory extends AbstractResponseFactory {

    public Map<String, Object> aprove(AuthenticationSet authSet) {
        return Map.of(
                "status", ResponseState.OK,
                "authentication", authSet.authentication()
        );
    }

    public Map<String, Object> aprove(AuthorizationSet authSet) {
        return Map.of(
                "status", ResponseState.OK,
                "access", authSet.access(),
                "refresh", authSet.refresh()
        );
    }
}
