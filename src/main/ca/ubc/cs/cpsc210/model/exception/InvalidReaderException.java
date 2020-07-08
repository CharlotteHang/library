package ca.ubc.cs.cpsc210.model.exception;

public class InvalidReaderException extends RuntimeException {
//    public InvalidReaderException() {
//        super();
//    }

    public InvalidReaderException(String detaiMessage) {
        super(detaiMessage);
    }
}
