package com.cl.food_app.exception;

public class ItemNotFoundException extends RuntimeException {

	int id;
	String message = "Item Id Not Found";

	@Override
	public String getMessage() {
		return message;
	}

	public ItemNotFoundException(int id) {
		super();
		this.id = id;
	}
}
