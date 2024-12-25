package lshh.pollservice.presentation.component;

import lshh.pollservice.presentation.vo.ResponseState;
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
