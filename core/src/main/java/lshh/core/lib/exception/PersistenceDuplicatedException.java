package lshh.core.lib.exception;

public class PersistenceDuplicatedException extends RuntimeException{
    public PersistenceDuplicatedException() {
        super();
    }

    public PersistenceDuplicatedException(String message) {
        super(message);
    }

    public PersistenceDuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersistenceDuplicatedException(Throwable cause) {
        super(cause);
    }

    protected PersistenceDuplicatedException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
