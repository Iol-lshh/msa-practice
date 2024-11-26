package lshh.pollservice.dto;

public interface Result {
    static Result ok() {
        return DefaultResult.OK;
    }
}
