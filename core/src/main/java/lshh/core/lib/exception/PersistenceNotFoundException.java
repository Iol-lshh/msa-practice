package lshh.core.lib.exception;

public class PersistenceNotFoundException extends RuntimeException{
    public PersistenceNotFoundException() {
        super();
    }

    public PersistenceNotFoundException(String message) {
        super(message);
    }

    public PersistenceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersistenceNotFoundException(Throwable cause) {
        super(cause);
    }

    protected PersistenceNotFoundException(String message, Throwable cause,
                                             boolean enableSuppression,
                                             boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
