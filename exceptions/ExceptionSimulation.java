package twisk.exceptions;

public class ExceptionSimulation extends RuntimeException{
    public ExceptionSimulation(String message) {
        super(message);
    }

    public ExceptionSimulation(String message, Throwable cause) {
        super(message, cause);
    }
}
