package lshh.pollservice.presentation.advice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lshh.pollservice.common.exception.VoteException;
import lshh.pollservice.presentation.ParticipantController;
import lshh.pollservice.presentation.component.ParticipantExceptionResponseFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice(assignableTypes = {ParticipantController.class})
public class ParticipantControllerAdvice {
    private final ParticipantExceptionResponseFactory responseFactory;

    @ExceptionHandler(VoteException.class)
    public Map<String, Object> handleVoteException(VoteException exception){
        log.warn("VoteException: " + exception.getMessage());
        return responseFactory.failVoteException("투표에 실패했습니다." + exception.getMessage());
    }
}
