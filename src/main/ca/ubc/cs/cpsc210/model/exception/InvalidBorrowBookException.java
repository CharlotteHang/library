package ca.ubc.cs.cpsc210.model.exception;

public class InvalidBorrowBookException extends RuntimeException {
//    public InvalidBorrowBookException() {
//        super();
//    }

    public InvalidBorrowBookException(String detaiMessage) {
        super(detaiMessage);

    }
}
