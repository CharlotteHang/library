package ca.ubc.cs.cpsc210.model.exception;

public class NullArgumentException extends IllegalArgumentException {
//    public NullArgumentException() {
//        super();
//    }

    public NullArgumentException(String detaiMessage) {
        super(detaiMessage);
    }
}
