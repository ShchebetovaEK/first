package by.tms.project.exception;

public class DoctorRepeatException extends Exception{
    public DoctorRepeatException() {
        super();
    }

    public DoctorRepeatException(String message) {
        super(message);
    }

    public DoctorRepeatException(String message, Throwable cause) {
        super(message, cause);
    }

    public DoctorRepeatException(Throwable cause) {
        super(cause);
    }
}
