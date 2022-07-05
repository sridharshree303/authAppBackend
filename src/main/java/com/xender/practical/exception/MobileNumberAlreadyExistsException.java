package com.xender.practical.exception;

public class MobileNumberAlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MobileNumberAlreadyExistsException() {
		super();
	}
	
	public MobileNumberAlreadyExistsException(String s) {
		super(s);
	}
}
