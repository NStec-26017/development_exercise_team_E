package com.example.fullness.stationary.exception;

public class ProductSearchException extends RuntimeException {

    public ProductSearchException(String message) {
        super(message);
    }

    public ProductSearchException(String message, Throwable cause) {
        super(message, cause);
    }

}
