package com.cl.food_app.exception;

public class FoodOrderNotFoundException extends RuntimeException {

	int id;
	String message = "Food Order Id Not Found";

	@Override
	public String getMessage() {
		return message;
	}

	public FoodOrderNotFoundException(int id) {
		super();
		this.id = id;
	}
	
	
}
