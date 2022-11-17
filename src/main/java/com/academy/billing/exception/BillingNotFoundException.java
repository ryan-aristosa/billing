package com.academy.billing.exception;

public class BillingNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final String prefix;


    public BillingNotFoundException(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String getMessage() {
        return prefix;
    }

}
