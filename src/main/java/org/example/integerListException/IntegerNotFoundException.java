package org.example.integerListException;

public class IntegerNotFoundException extends RuntimeException{
    public IntegerNotFoundException() {
    }

    public IntegerNotFoundException(String message) {
        super(message);
    }

    public IntegerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public IntegerNotFoundException(Throwable cause) {
        super(cause);
    }

    public IntegerNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
