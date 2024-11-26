package lshh.pollservice.presentation.component;

import lshh.pollservice.dto.auth.AuthenticationSet;
import lshh.pollservice.dto.auth.AuthorizationSet;
import lshh.pollservice.presentation.vo.ResponseState;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AuthResponseFactory extends AbstractResponseFactory{

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
