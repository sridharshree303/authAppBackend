package com.xender.practical.exception;

public class UserNameAlreadyeExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNameAlreadyeExistsException() {
		super();
	}
	
	public UserNameAlreadyeExistsException(String s) {
		super(s);
	}
}
