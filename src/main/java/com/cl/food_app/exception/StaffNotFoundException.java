package com.cl.food_app.exception;

public class StaffNotFoundException extends RuntimeException {

	int id;
	String message = "Staff Id Not Found";

	@Override
	public String getMessage() {
		return message;
	}

	public StaffNotFoundException(int id) {
		super();
		this.id = id;
	}
}
