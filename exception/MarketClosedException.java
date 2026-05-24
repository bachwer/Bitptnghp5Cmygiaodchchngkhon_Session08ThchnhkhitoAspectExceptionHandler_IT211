package org.example.ex5.exception;

public class MarketClosedException extends RuntimeException {
    public MarketClosedException(String message) {
        super(message);
    }

    public MarketClosedException(String message, Throwable cause) {
        super(message, cause);
    }
}

