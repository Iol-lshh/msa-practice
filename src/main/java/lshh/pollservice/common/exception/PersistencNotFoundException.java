package lshh.pollservice.common.exception;

public class PersistencNotFoundException extends RuntimeException{
    public PersistencNotFoundException(String message) {
        super(message);
    }
    public PersistencNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
