package com.academy.billing.exception;

public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    @Override
    public String getMessage() {
        return "User";
    }

}
