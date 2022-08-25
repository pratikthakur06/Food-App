package com.cl.food_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cl.food_app.util.ResponseStructure;

@ControllerAdvice
public class ItemExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ItemNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> itemNotFoundExceptionEntity(ItemNotFoundException exception) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Item With Id " + exception.id + " Not Found In Database");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setT("No Such Item Id Found");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
}
