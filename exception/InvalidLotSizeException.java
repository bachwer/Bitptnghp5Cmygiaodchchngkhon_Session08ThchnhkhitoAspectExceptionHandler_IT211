package org.example.ex5.exception;

public class InvalidLotSizeException extends RuntimeException {
    public InvalidLotSizeException(String message) {
        super(message);
    }

    public InvalidLotSizeException(String message, Throwable cause) {
        super(message, cause);
    }
}



