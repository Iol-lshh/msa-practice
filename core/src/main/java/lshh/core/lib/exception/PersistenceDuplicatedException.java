package lshh.core.lib.exception;

public class PersistenceDuplicatedException extends RuntimeException{
    public PersistenceDuplicatedException(String message) {
        super(message);
    }
    public PersistenceDuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
