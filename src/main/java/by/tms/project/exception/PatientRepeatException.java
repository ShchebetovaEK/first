package by.tms.project.exception;

public class PatientRepeatException extends Exception{
    public PatientRepeatException() {
        super();
    }

    public PatientRepeatException(String message) {
        super(message);
    }

    public PatientRepeatException(String message, Throwable cause) {
        super(message, cause);
    }

    public PatientRepeatException(Throwable cause) {
        super(cause);
    }
}
