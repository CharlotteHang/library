package ca.ubc.cs.cpsc210.model.exception;

public class InvalidNumberException extends RuntimeException {
//    public InvalidNumberException() {
//        super();
//    }

    public InvalidNumberException(String detaiMessage) {
        super(detaiMessage);
    }
}
