package com.xender.practical.exception;

public class EmailAlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmailAlreadyExistsException() {
		super();
	}
	
	public EmailAlreadyExistsException(String s) {
		super(s);
	}
	
}
