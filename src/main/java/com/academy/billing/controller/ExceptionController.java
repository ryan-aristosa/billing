package com.academy.billing.controller;

import com.academy.billing.exception.BillingNotFoundException;
import com.academy.billing.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(value = { BillingNotFoundException.class, UserNotFoundException.class })
	public ResponseEntity<Object> handleRecordNotFound(Exception e) {
		String message = e.getMessage() + " not found";
		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}

	// 400 Bad Request
	// Ex. expected Long as path variable, but found String
	@ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Object> handleBadRequest() {
		return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
	}

	// 404 Not Found
	// Ex. wrong url
	@ExceptionHandler(value = NoHandlerFoundException.class)
	public ResponseEntity<Object> handleNotFound() {
		return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
	}

}