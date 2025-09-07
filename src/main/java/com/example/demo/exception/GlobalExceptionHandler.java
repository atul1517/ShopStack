package com.example.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	//Handle Validation Error 
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationError(MethodArgumentNotValidException ex)
	{
		Map<String, String> errors = new HashMap<String, String>();
		
		ex.getBindingResult().getAllErrors().forEach( error -> {
			
		String field = ((FieldError) error).getField();
		String message = error.getDefaultMessage();
		errors.put(field, message);
		
		});
		
		return ResponseEntity.badRequest().body(errors); 
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<Map<String, String>> handleNotFound(ProductNotFoundException ex)
	{
		Map<String, String> errors = new HashMap<String, String>();
		errors.put("error", ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, String>> handleGeneral(Exception ex)
	{
		Map<String, String> errors = new HashMap<String, String>();
		
		errors.put("error", "Unexcepted Error"+ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
		
	}
	

}
