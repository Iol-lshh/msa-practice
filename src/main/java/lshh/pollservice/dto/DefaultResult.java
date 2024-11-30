package lshh.pollservice.dto;

import lombok.Getter;

@Getter
public enum DefaultResult implements Result{
    OK("확인"),
    SUCCESS("성공"),
    FAIL("실패"),
    ERROR("오류")
    ;

    private final String message;

    DefaultResult(String message) {
        this.message = message;
    }
}
