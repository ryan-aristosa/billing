package com.academy.billing.exception;

public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final String prefix;


    public UserNotFoundException(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String getMessage() {
        return prefix;
    }

}
