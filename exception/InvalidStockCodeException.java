package org.example.ex5.exception;

public class InvalidStockCodeException extends RuntimeException {
    public InvalidStockCodeException(String message) {
        super(message);
    }

    public InvalidStockCodeException(String message, Throwable cause) {
        super(message, cause);
    }
}

