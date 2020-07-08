package ca.ubc.cs.cpsc210.model.exception;

public class InvalidReturnBookException extends RuntimeException {
//    public InvalidReturnBookException() {
//        super();
//    }

    public InvalidReturnBookException(String detaiMessage) {
        super(detaiMessage);

    }
}
