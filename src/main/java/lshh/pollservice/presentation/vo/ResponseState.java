package lshh.pollservice.presentation.vo;

import lshh.pollservice.dto.DefaultResult;
import lshh.pollservice.dto.Result;

public enum ResponseState {
    OK("성공"),
    FAIL("실패")
    ;

    private final String defaultMessage;

    ResponseState(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    public static ResponseState from(Result result) {
        return switch (result){
            case DefaultResult.OK -> OK;
            case DefaultResult.FAIL -> FAIL;
            default -> FAIL;
        };
    }

    public String toString() {
        return this.name();
    }

    public String getDefaultMessage(){
        return this.defaultMessage;
    }
}
