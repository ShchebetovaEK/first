package by.tms.project.exception;

public class UserRepeatException extends Exception {
    public UserRepeatException() {
        super();
    }

    public UserRepeatException(String message) {
        super(message);
    }

    public UserRepeatException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserRepeatException(Throwable cause) {
        super(cause);
    }
}
