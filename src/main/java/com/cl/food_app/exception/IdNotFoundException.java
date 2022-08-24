package com.cl.food_app.exception;

public class IdNotFoundException extends RuntimeException {

	String message = "Id not found";

	@Override
	public String getMessage() {
		return message;
	}
}
