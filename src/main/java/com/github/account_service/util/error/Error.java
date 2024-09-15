package com.github.account_service.util.error;

public class Error extends RuntimeException {
    public Error() {
    }

    public Error(String message) {
        super(message);
    }
}