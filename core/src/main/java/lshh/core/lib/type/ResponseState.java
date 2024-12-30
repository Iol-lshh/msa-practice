package lshh.core.lib.type;

public enum ResponseState {
    OK("확인"),
    SUCCESS("성공"),
    FAIL("실패"),
    ERROR("오류")
    ;

    private final String defaultMessage;

    ResponseState(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    public static ResponseState from(OutputDto outputDto) {
        return switch (outputDto){
            case DefaultOutput.OK -> OK;
            case DefaultOutput.SUCCESS -> SUCCESS;
            case DefaultOutput.FAIL -> FAIL;
            default -> ERROR;
        };
    }

    @Override
    public String toString() {
        return this.name();
    }

    public String getDefaultMessage(){
        return this.defaultMessage;
    }
}
