package lshh.pollservice.presentation.component;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ParticipantExceptionResponseFactory {
    public Map<String, Object> failVoteException(String message) {
        return Map.of(
                "state", "FAIL",
                "errorCode", "VoteException",
                "message", message
        );
    }
}
