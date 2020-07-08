package ca.ubc.cs.cpsc210.model.exception;

public class InvalidBookException extends RuntimeException {
//    public InvalidBookException() {
//        super();
//    }

    public InvalidBookException(String detaiMessage) {
        super(detaiMessage);
    }
}
