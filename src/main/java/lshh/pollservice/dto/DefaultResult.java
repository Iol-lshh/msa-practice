package lshh.pollservice.dto;

import lombok.Getter;

@Getter
public enum DefaultResult implements Result{
    OK("OK"),  // OK
    FAIL("FAIL"), // FAIL
    ;

    private final String message;

    DefaultResult(String message) {
        this.message = message;
    }
}
