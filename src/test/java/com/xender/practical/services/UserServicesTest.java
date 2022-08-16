package com.xender.practical.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.InvalidTransactionException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.xender.practical.exception.DataAlreadyExistsException;
import com.xender.practical.exception.DataNotFoundException;
import com.xender.practical.exception.EmailAlreadyExistsException;
import com.xender.practical.exception.MobileNumberAlreadyExistsException;
import com.xender.practical.exception.PasswordNotMatchException;
import com.xender.practical.exception.UserNameAlreadyeExistsException;
import com.xender.practical.exception.UserNameNotFoundException;
import com.xender.practical.model.UserData;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserServicesTest {

	@Autowired
	private UserServices userservice;

	@Test
	void testallusers() throws DataNotFoundException {
		List<UserData> list = new ArrayList<>();
		list = userservice.allUsers();
		assertNotNull(list);
	}

//	@Test
//	void testRegisterUser() throws DataAlreadyExistsException, UserNameAlreadyeExistsException,
//			MobileNumberAlreadyExistsException, EmailAlreadyExistsException {
//		UserData expected = new UserData();
//		expected.setUserid(10000);
//		expected.setName("Varshi22");
//		expected.setEmail("varshi22@gmail.com");
//		expected.setMobileNumber("9387480022");
//		expected.setUsername("Varshi@22");
//		expected.setPassword("shjsjskdjs");
//
//		//register
//		UserData result = userservice.register(expected);
//		assertNotNull(result);
//
//	}

	// email exception
	@Test
	void TestemailExceptiononregister() throws DataAlreadyExistsException, UserNameAlreadyeExistsException, MobileNumberAlreadyExistsException, EmailAlreadyExistsException {
		UserData expected = new UserData();
		expected.setEmail("varshini16@gmail.com");

		EmailAlreadyExistsException thrown = assertThrows(EmailAlreadyExistsException.class,
				()->userservice.register(expected)
				);
		assertTrue(thrown.getMessage().contains("email is already registered"));
	}
	
	//username exception
	@Test
	void TestUsernameExceptiononregister() throws UserNameAlreadyeExistsException {
		UserData expected = new UserData();
	
		expected.setUsername("Varshini@129");
	
		UserNameAlreadyeExistsException thrown = assertThrows(UserNameAlreadyeExistsException.class,
				()->userservice.register(expected)
				);
		assertTrue(thrown.getMessage().contains("Username already exists"));

	}
	 
	// mobilenumber exception
	@Test
	void TestMobileExceptiononregister() throws DataAlreadyExistsException, UserNameAlreadyeExistsException, MobileNumberAlreadyExistsException, EmailAlreadyExistsException {
		UserData expected = new UserData();
		expected.setMobileNumber("9387487396");
		
		MobileNumberAlreadyExistsException thrown = assertThrows(MobileNumberAlreadyExistsException.class,
				()->userservice.register(expected)
				);
		assertTrue(thrown.getMessage().contains("Mobile number already exists"));
	}
	
	@Test
	void TestDataAlreadyExceptiononregister() throws DataAlreadyExistsException, UserNameAlreadyeExistsException, MobileNumberAlreadyExistsException, EmailAlreadyExistsException {
		UserData expected = new UserData();
		expected.setMobileNumber("9387487383");
		expected.setUsername("Varshini@131");
		expected.setEmail("varshini11@gmail.com");
		
		DataAlreadyExistsException thrown = assertThrows(DataAlreadyExistsException.class,
				()->userservice.register(expected)
				);
		assertTrue(thrown.getMessage().contains("User already registred"));
	}
	

	@Test
	void testLoginUser() throws DataNotFoundException, PasswordNotMatchException, UserNameNotFoundException {
		UserData input = new UserData();
		input.setUsername("Varshini@130");
		input.setPassword("shjsjskdjs");

		UserData result = userservice.login(input);
		assertNotNull(result);
		assertEquals("Varshini3", result.getName());
		System.out.println(result.getName());

	}

	@Test
	void testLogout() throws InvalidTransactionException {
		UserData logout = new UserData();
		logout.setUsername("Varshini@129");
		String username = logout.getUsername();

		String msg = userservice.logout(username);
		assertNotNull(msg);
		assertEquals("User Logged out successfully", msg);
		System.out.println(msg);
	}

	@Test
	void testResetPassword() throws InvalidTransactionException {
		UserData reset = new UserData();
		reset.setEmail("varshini11@gmail.com");
		reset.setPassword("Asdf@123");

		UserData result = userservice.resetPassword(reset);
		assertNotNull(result);
		assertEquals("Varshini@132", result.getUsername());
		System.out.println(result.getUsername());
	}
}
