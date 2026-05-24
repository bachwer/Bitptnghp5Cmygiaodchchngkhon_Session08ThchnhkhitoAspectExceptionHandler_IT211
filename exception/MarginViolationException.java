package org.example.ex5.exception;

public class MarginViolationException extends RuntimeException {
    public MarginViolationException(String message) {
        super(message);
    }

    public MarginViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}

