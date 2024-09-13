package com.github.account_service.util.exception;


public class DataValidationException extends RuntimeException {
    public DataValidationException(String message) {
        super(message);
    }
}