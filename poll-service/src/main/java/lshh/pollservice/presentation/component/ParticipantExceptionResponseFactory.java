package lshh.pollservice.presentation.component;

import lshh.core.lib.component.AbstractResponseFactory;
import lshh.core.lib.type.ResponseState;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ParticipantExceptionResponseFactory extends AbstractResponseFactory {
    public Map<String, Object> failVoteException(String message) {
        return Map.of(
                "state", ResponseState.FAIL,
                "errorCode", "VoteException",
                "message", message
        );
    }
}
