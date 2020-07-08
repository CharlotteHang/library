package ca.ubc.cs.cpsc210.model.exception;

public class EmptyStringException extends IllegalArgumentException {
//    public EmptyStringException() {
//        super();
//    }

    public EmptyStringException(String detaiMessage) {
        super(detaiMessage);
    }
}
