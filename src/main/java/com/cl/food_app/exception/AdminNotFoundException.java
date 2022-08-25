package com.cl.food_app.exception;

public class AdminNotFoundException extends RuntimeException {

	int id;
	String message = "Admin Id Not Found";

	@Override
	public String getMessage() {
		return message;
	}

	public AdminNotFoundException(int id) {
		super();
		this.id = id;
	}
}
