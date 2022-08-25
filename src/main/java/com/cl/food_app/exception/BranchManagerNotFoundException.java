package com.cl.food_app.exception;

public class BranchManagerNotFoundException extends RuntimeException {

	int id;
	String message = "Branch Manager Id Not Found";

	@Override
	public String getMessage() {
		return message;
	}

	public BranchManagerNotFoundException(int id) {
		super();
		this.id = id;
	}
}
