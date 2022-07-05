package com.xender.practical.controller;

import java.util.List;

import javax.transaction.InvalidTransactionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.xender.practical.exception.DataAlreadyExistsException;
import com.xender.practical.exception.DataNotFoundException;
import com.xender.practical.exception.EmailAlreadyExistsException;
import com.xender.practical.exception.MobileNumberAlreadyExistsException;
import com.xender.practical.exception.PasswordNotMatchException;
import com.xender.practical.exception.UserNameAlreadyeExistsException;
import com.xender.practical.exception.UserNameNotFoundException;
import com.xender.practical.model.UserData;
import com.xender.practical.services.UserServicesImplm;


@RestController
@CrossOrigin
@RequestMapping("/user")
@ResponseBody
@ResponseStatus
public class UserController {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserServicesImplm userServicesImplm;
	
	@GetMapping("/")
	public void message() {
		LOG.info("Have a Fun coding..");
	}
	
	@GetMapping("/getall")
	public ResponseEntity<List<UserData>> getAllUser() throws DataNotFoundException{
		LOG.info("getAllUser controller");
		List<UserData> list = userServicesImplm.allUsers();
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "returned all userdetails");
		ResponseEntity<List<UserData>> response = new ResponseEntity<List<UserData>>(list,headers,HttpStatus.OK);
		return response;
	}
	
	@PostMapping("/register")
	@ResponseStatus(code = HttpStatus.CREATED, reason = "CREATED")
	public ResponseEntity<UserData> registeruser(@RequestBody UserData user) throws DataAlreadyExistsException,
			UserNameAlreadyeExistsException, MobileNumberAlreadyExistsException, EmailAlreadyExistsException  {
		LOG.info("registeruser controller");
		UserData data = userServicesImplm.register(user);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message","User registered successfully");
		ResponseEntity<UserData> response = new ResponseEntity<UserData>(data,headers,HttpStatus.CREATED);
//		return ResponseEntity.status(HttpStatus.CREATED).body(data);
		return response;
	}

	@PostMapping("/login")
	public ResponseEntity<UserData> loginUser(@RequestBody UserData user)
			throws DataNotFoundException, PasswordNotMatchException, UserNameNotFoundException {
		LOG.info("LoginUser controller");
		UserData data = userServicesImplm.login(user);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message","User Logined successfully");
		ResponseEntity<UserData> response = new ResponseEntity<UserData>(data,headers,HttpStatus.OK);
		return response;
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logoutuser(@RequestBody String userName) throws InvalidTransactionException {
		LOG.info("Logout User controller");
		HttpHeaders headers = new HttpHeaders();
		String str = userServicesImplm.logout(userName);
		headers.add("message", "User logged out successfully");
		ResponseEntity<String> response = new ResponseEntity<String>(str, headers, HttpStatus.OK);
		return response;
	}

	@PutMapping("/resetpassword")
	public ResponseEntity<UserData> resetpassword(@RequestBody UserData user) throws InvalidTransactionException{
		LOG.info("password reset controller");
		UserData data = userServicesImplm.resetPassword(user);
		HttpHeaders headers = new HttpHeaders();
		headers.add("msg","Password reset successful");
		ResponseEntity<UserData> response = new ResponseEntity<UserData>(data,headers,HttpStatus.CREATED);
		return response;
	}
	
	
}
