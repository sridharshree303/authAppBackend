package com.xender.practical.exception;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.transaction.InvalidTransactionException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.xender.practical.model.UserData;
import com.xender.practical.services.UserServices;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class handleExceptionTests {

	@Autowired
	private UserServices userservice;

	// email exception
	@Test
	void testemailExceptiononregister() throws DataAlreadyExistsException, UserNameAlreadyeExistsException,
			MobileNumberAlreadyExistsException, EmailAlreadyExistsException {
		UserData expected = new UserData();
		expected.setEmail("varshini16@gmail.com");

		EmailAlreadyExistsException thrown = assertThrows(EmailAlreadyExistsException.class,
				() -> userservice.register(expected));
		assertTrue(thrown.getMessage().contains("email is already registered"));
	}
	
	@Test
	void testUserNameNotFoundException() throws UserNameNotFoundException {
		UserData user = new UserData();
		user.setUsername("KOSRID");
		user.setPassword("Asdf@123");
		
		UserNameNotFoundException thrown = assertThrows(UserNameNotFoundException.class,
				() -> userservice.login(user));
		assertTrue(thrown.getMessage().contains("Invalid user login crediantials"));
	}
	
	@Test
	void testPasswordNotMatchException() throws PasswordNotMatchException {
		UserData user = new UserData();
		user.setUsername("KOSRIDHA");
		user.setPassword("Asdf@12");
		
		PasswordNotMatchException thrown = assertThrows(PasswordNotMatchException.class,
				() -> userservice.login(user));
		assertTrue(thrown.getMessage().contains("Invalid password"));
	}
	
	// username exception
	@Test
	void testUsernameExceptiononregister() throws UserNameAlreadyeExistsException {
		UserData expected = new UserData();
		expected.setUsername("Varshini@129");

		UserNameAlreadyeExistsException thrown = assertThrows(UserNameAlreadyeExistsException.class,
				() -> userservice.register(expected));
		assertTrue(thrown.getMessage().contains("Username already exists"));

	}

	// mobilenumber exception
	@Test
	void testMobileExceptiononregister() throws DataAlreadyExistsException, UserNameAlreadyeExistsException,
			MobileNumberAlreadyExistsException, EmailAlreadyExistsException {
		UserData expected = new UserData();
		expected.setMobileNumber("9387487396");

		MobileNumberAlreadyExistsException thrown = assertThrows(MobileNumberAlreadyExistsException.class,
				() -> userservice.register(expected));
		assertTrue(thrown.getMessage().contains("Mobile number already exists"));
	}

	//dataalready found exception
	@Test
	void testDataAlreadyExceptiononregister() throws DataAlreadyExistsException, UserNameAlreadyeExistsException,
			MobileNumberAlreadyExistsException, EmailAlreadyExistsException {
		UserData expected = new UserData();
		expected.setMobileNumber("9387487383");
		expected.setUsername("Varshini@131");
		expected.setEmail("varshini11@gmail.com");

		DataAlreadyExistsException thrown = assertThrows(DataAlreadyExistsException.class,
				() -> userservice.register(expected));
		assertTrue(thrown.getMessage().contains("User already registred"));
	}
	
	//resetpassword exception
	@Test
	void testInvalidTransactionException() throws InvalidTransactionException{
		UserData user = new UserData();
		user.setEmail("Sridhar2@gmail.com");// wrong email
		
		InvalidTransactionException thrown = assertThrows(InvalidTransactionException.class,
				() -> userservice.resetPassword(user));
		assertTrue(thrown.getMessage().contains("Invalid Email/Username"));
	}

}
