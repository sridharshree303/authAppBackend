package com.xender.practical.exception;

public class PasswordNotMatchException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PasswordNotMatchException() {
		super();
	}
	
	public PasswordNotMatchException(String s) {
		super(s);
	}
}
