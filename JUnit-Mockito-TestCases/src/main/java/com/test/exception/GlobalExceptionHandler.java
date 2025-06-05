package com.test.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.test.model.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ResponseStatus<String>> handleResourceNotFound(CustomException ex,WebRequest request) {
		ResponseStatus<String> response = new ResponseStatus<>(HttpStatus.NOT_FOUND.value(), "ERROR", ex.getMessage(),null, new Date());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseStatus<String>> handleGlobalException(Exception ex, WebRequest request) {
		ResponseStatus<String> response = new ResponseStatus<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "ERROR",
				"An unexpected error occurred", null, new Date());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
