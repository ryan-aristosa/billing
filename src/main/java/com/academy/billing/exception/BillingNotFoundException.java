package com.academy.billing.exception;

public class BillingNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    @Override
    public String getMessage() {
        return "Billing";
    }

}
