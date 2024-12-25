package lshh.core.bean;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lshh.core.lib.exception.PersistenceNotFoundException;
import lshh.core.lib.type.DefaultExceptionHandlable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice(assignableTypes = {DefaultExceptionHandlable.class})
public class DefaultControllerAdvice {
    private final DefaultExceptionResponseFactory factory;

    @ExceptionHandler(PersistenceNotFoundException.class)
    public Map<String, Object> handlePersistenceNotFoundException(PersistenceNotFoundException exception){
        log.warn("PersistenceNotFoundException: " + exception.getMessage());
        return factory.failPersistenceNotFoundException("DB에서 해당 정보를 찾을 수 없습니다." + exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Map<String, Object> handleException(Exception exception){
        log.error("Exception: " + exception.getMessage());
        return factory.error("예기치 못한 오류가 발생했습니다." + exception.getMessage());
    }
}
