package com.xender.practical.exception;

public class UserNameNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNameNotFoundException() {
		super();
	}
	
	public UserNameNotFoundException(String s) {
		super(s);
	}
}
