package lshh.core.lib.exception;

public class PersistenceNotFoundException extends RuntimeException{
    public PersistenceNotFoundException(String message) {
        super(message);
    }
    public PersistenceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
