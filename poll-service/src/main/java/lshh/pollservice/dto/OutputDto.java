package lshh.pollservice.dto;

public interface OutputDto {
    static OutputDto ok() {
        return DefaultOutput.OK;
    }

    static OutputDto success() {
        return DefaultOutput.SUCCESS;
    }
}
