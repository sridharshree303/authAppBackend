package com.xender.practical.exception;

import javax.transaction.InvalidTransactionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(CustomExceptionHandler.class);
	
	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFoundException(){
		LOG.error("handleUserNotFoundException");
		HttpHeaders headers = new HttpHeaders();
		String msg = "Data not found";
		headers.add("messgae","User not found");
		return new ResponseEntity<Object>(msg,headers,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DataAlreadyExistsException.class)
	public ResponseEntity<Object> handleUserAlreadyExistsException(){
		LOG.error("handleUserAlreadyExistsException");
		String msg = "Data already exists";
		HttpHeaders headers = new HttpHeaders();
		headers.add("message","User already exists");
		return new ResponseEntity<Object>(msg,headers,HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<Object> handleEmailAlreadyExistsException(){
		LOG.error("handleEmailAlreadyExistsException");
		String msg = "Email Already Exist";
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", msg);
		return new ResponseEntity<Object>(msg,headers,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(UserNameAlreadyeExistsException.class)
	public ResponseEntity<Object> handleUserNameAlreadyeExistsException(){
		LOG.error("handleUserNameAlreadyeExistsException");
		String msg = "UserName already Exist";
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", msg);
		return new ResponseEntity<Object>(msg,headers,HttpStatus.FOUND);
	}
	
	@ExceptionHandler(MobileNumberAlreadyExistsException.class)
	public ResponseEntity<Object> handleMobileNumberAlreadyExistsException(){
		LOG.error("handleMobileNumberAlreadyExistsException");
		String msg = "Mobile Number already exist";
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", msg);
		return new ResponseEntity<Object>(msg,headers,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserNameNotFoundException.class)
	public ResponseEntity<Object> handleUserNameNotFoundException(){
		LOG.error("handleUserNameNotFoundException");
		String msg = "Username not Exists";
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", msg);
		return new ResponseEntity<Object>(msg,headers,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PasswordNotMatchException.class)
	public ResponseEntity<Object> handlePasswordNotMatchException(){
		LOG.error("handlePasswordNotMatchException");
		String msg = "Password does not matched";
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", msg);
		return new ResponseEntity<Object>(msg,headers,HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(InvalidTransactionException.class)
	public ResponseEntity<Object> handleInvalidTransactionException(){
		LOG.error("handleInvalidTransactionException");
		HttpHeaders headers = new HttpHeaders();
		headers.add("message","Invalid Transaction Details Provided");
		return new ResponseEntity<Object>(null,headers,HttpStatus.BAD_REQUEST);
	}
	
}
