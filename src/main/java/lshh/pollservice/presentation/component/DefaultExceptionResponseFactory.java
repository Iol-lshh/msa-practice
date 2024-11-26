package lshh.pollservice.presentation.component;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DefaultExceptionResponseFactory {

    public Map<String, Object> failPersistenceNotFoundException(String message) {
        return Map.of(
                "state", "FAIL",
                "errorCode", "PersistenceNotFound",
                "message", message
        );
    }

    public Map<String, Object> error(String message) {
        return Map.of(
                "state", "ERROR",
                "errorCode", "UnexpectedError",
                "message", message
        );
    }
}
