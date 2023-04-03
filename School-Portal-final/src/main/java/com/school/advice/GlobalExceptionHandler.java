package com.school.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.school.exception.ErrorType;
import com.school.exception.IdNotFoundException;


@RestControllerAdvice
public class GlobalExceptionHandler{

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ErrorType> handleEmpNotFoundEx(IdNotFoundException ex){
		return new ResponseEntity<ErrorType>(new ErrorType(
				ex.getMessage(), "ID_NOT_FOUND","No data present with this id","School Portal"),HttpStatus.BAD_REQUEST);
	}	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String,String> handleInvalidArgument(MethodArgumentNotValidException ex){
		Map<String, String> errorMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		return errorMap;
	}
}
