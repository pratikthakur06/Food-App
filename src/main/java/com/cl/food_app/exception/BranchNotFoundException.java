package com.cl.food_app.exception;

public class BranchNotFoundException extends RuntimeException {

	int id;
	String message = "Branch Id Not Found";

	@Override
	public String getMessage() {
		return message;
	}

	public BranchNotFoundException(int id) {
		super();
		this.id = id;
	}
}
