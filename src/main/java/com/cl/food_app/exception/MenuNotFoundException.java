package com.cl.food_app.exception;

public class MenuNotFoundException extends RuntimeException {

	int id;
	String message = "Menu Id Not Found";

	@Override
	public String getMessage() {
		return message;
	}

	public MenuNotFoundException(int id) {
		super();
		this.id = id;
	}
}
