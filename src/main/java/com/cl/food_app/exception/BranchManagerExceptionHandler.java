package com.cl.food_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cl.food_app.util.ResponseStructure;

@ControllerAdvice
public class BranchManagerExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BranchManagerNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> branchManagerNotFoundExceptionEntity(
			BranchManagerNotFoundException exception) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Branch Manager With Id " + exception.id + " Not Found In Database");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setT("No Such Branch Manager Id Found");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
}
