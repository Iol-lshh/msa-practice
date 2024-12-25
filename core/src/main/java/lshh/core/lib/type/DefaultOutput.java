package lshh.core.lib.type;

import lombok.Getter;

@Getter
public enum DefaultOutput implements OutputDto {
    OK("확인"),
    SUCCESS("성공"),
    FAIL("실패"),
    ERROR("오류")
    ;

    private final String message;

    DefaultOutput(String message) {
        this.message = message;
    }
}
