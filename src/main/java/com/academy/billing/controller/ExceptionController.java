package com.academy.billing.controller;

import com.academy.billing.exception.BillingNotFoundException;
import com.academy.billing.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(value = BillingNotFoundException.class)
	public ResponseEntity<Object> billingNotFound() {
		return new ResponseEntity<>("Billing not found", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity<Object> recordNotFound() {
		return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
	}

}