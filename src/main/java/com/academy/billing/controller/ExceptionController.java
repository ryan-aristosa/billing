package com.academy.billing.controller;

import com.academy.billing.exception.BillingNotFoundException;
import com.academy.billing.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(value = { BillingNotFoundException.class, UserNotFoundException.class })
	public ResponseEntity<Object> handleRecordNotFound(Exception e) {
		String message = e.getMessage() + " not found";
		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}

}