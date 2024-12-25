package lshh.core.bean;

import lshh.core.lib.component.AbstractResponseFactory;
import lshh.core.lib.type.ResponseState;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DefaultExceptionResponseFactory extends AbstractResponseFactory {

    public Map<String, Object> failPersistenceNotFoundException(String message) {
        return Map.of(
                "state", ResponseState.FAIL,
                "errorCode", "PersistenceNotFound",
                "message", message
        );
    }

    public Map<String, Object> error(String message) {
        return Map.of(
                "state", ResponseState.ERROR,
                "errorCode", "UnexpectedError",
                "message", message
        );
    }
}
