package lshh.core.lib.exception;

public class PersistenceNotAllowedException extends RuntimeException {
    public PersistenceNotAllowedException() {
        super();
    }

    public PersistenceNotAllowedException(String message) {
        super(message);
    }

    public PersistenceNotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersistenceNotAllowedException(Throwable cause) {
        super(cause);
    }

    protected PersistenceNotAllowedException(String message, Throwable cause,
                                             boolean enableSuppression,
                                             boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
