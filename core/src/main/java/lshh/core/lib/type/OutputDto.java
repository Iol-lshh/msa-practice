package lshh.core.lib.type;

public interface OutputDto {
    static OutputDto ok() {
        return DefaultOutput.OK;
    }

    static OutputDto success() {
        return DefaultOutput.SUCCESS;
    }
}
