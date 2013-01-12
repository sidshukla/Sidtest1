package com.cloudreach.solution.exception;

public class FinsburyApplicationException extends Exception{

	private static final long serialVersionUID = 7412665648957762482L;
	
	public FinsburyApplicationException(String errorMessage) {
		super(errorMessage);
	}

}
