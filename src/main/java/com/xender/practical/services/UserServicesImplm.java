package com.xender.practical.services;

import java.util.List;

import javax.transaction.InvalidTransactionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xender.practical.exception.DataAlreadyExistsException;
import com.xender.practical.exception.DataNotFoundException;
import com.xender.practical.exception.EmailAlreadyExistsException;
import com.xender.practical.exception.MobileNumberAlreadyExistsException;
import com.xender.practical.exception.PasswordNotMatchException;
import com.xender.practical.exception.UserNameAlreadyeExistsException;
import com.xender.practical.exception.UserNameNotFoundException;
import com.xender.practical.model.UserData;
import com.xender.practical.repository.IUserRepository;

@Service
public class UserServicesImplm implements UserServices {

	private static final Logger LOG = LoggerFactory.getLogger(UserServicesImplm.class);

	@Autowired
	private IUserRepository userRepository;

	public boolean isLogged;
	private UserData tempUser;

	@SuppressWarnings("unused")
	@Override
	public List<UserData> allUsers() throws DataNotFoundException {
		LOG.info("get allUser service");
		List<UserData> list = userRepository.findAll();
		int len = list.size();
		if (0 != list.size()) {
			LOG.info("Users data found");
			return list;
		} else {
			LOG.info("No data found");
			throw new DataNotFoundException("Data not found");
		}
	}

	@SuppressWarnings("unused")
	@Override
	public UserData register(UserData user) throws DataAlreadyExistsException, UserNameAlreadyeExistsException,
			MobileNumberAlreadyExistsException, EmailAlreadyExistsException {
		LOG.info("register service");

		// generating userId
		Integer baseId = 10000;
		List<UserData> list = userRepository.findAll();
		Integer len = list.size();

		if (len != 0) {
			UserData lastuser = list.get(--len);
			Integer lastId = lastuser.getUserid();
			Integer newUserid = lastId + 1;
			user.setUserid(newUserid);
		} else {
			Integer newUserId = baseId + 1;
			user.setUserid(newUserId);
		}

		// validating user and registering data
		UserData validmobile = userRepository.findByMobileNumber(user.getMobileNumber());
		UserData validemail = userRepository.findByEmail(user.getEmail());
		UserData validusername = userRepository.findByUsername(user.getUsername());

		boolean email = false;
		boolean usrname = false;
		boolean mobilenumber= false;
		

		if (null == validemail || null == validmobile || null == validusername) {
			LOG.info("user validated");
			
			if (null == validemail) {
				LOG.info("validated email");
				email = true;
			}else {
				LOG.info("Email already exists");
				throw new EmailAlreadyExistsException("email is already registered");
			}
			
			if(null == validusername) {
				LOG.info("validated username");
				usrname = true;
			}else {
				LOG.info("Username already exists");
				throw new UserNameAlreadyeExistsException("Username already exists");
			}
			
			if(null == validmobile) {
				LOG.info(" validated mobilenumber");
				mobilenumber = true;
			}else {
				LOG.info("Mobile number already exists");
				throw new MobileNumberAlreadyExistsException("Mobile number already exists");
			}
			
			if(email && usrname && mobilenumber) {
				LOG.info("data registered successfully");
				return userRepository.save(user);
			}else {
				LOG.info("User already registred");
				throw new DataAlreadyExistsException("User already registred");
			}
			
		}else {
			LOG.info("User already registred");
			throw new DataAlreadyExistsException("User already registred");
		}

//		if (null == validemail || null == validmobile || null == validusername) {
//			LOG.info("user validated");
//			if (null == validemail) {
//				LOG.info("validated email");
//				if (null == validusername) {
//					LOG.info("validated username");
//					if (null == validmobile) {
//						LOG.info(" validated mobilenumber");
//						return userRepository.save(user);
//					} else {
//						LOG.info("Mobile number already exists");
//						throw new MobileNumberAlreadyExistsException("Mobile number already exists");
//					}
//				} else {
//					LOG.info("Username already exists");
//					throw new UserNameAlreadyeExistsException("Username already exists");
//				}
//			} else {
//				LOG.info("Email already exists");
//				throw new EmailAlreadyExistsException("email is already registered");
//			}
//		} else {
//			LOG.info("User already registred");
//			throw new DataAlreadyExistsException("User already registred");
//		}

	}

	@SuppressWarnings("unused")
	@Override
	public UserData login(UserData user)
			throws PasswordNotMatchException, UserNameNotFoundException {
		LOG.info("Login service");
		tempUser = userRepository.findByUsername(user.getUsername());
		if (tempUser != null) {
				if (user.getPassword().equals(tempUser.getPassword())) {
					LOG.info("Password validated");
					LOG.info("Login succesfull");
					isLogged = true;
					return tempUser; 
				} else {
					LOG.info("Entered Wrong password");
					throw new PasswordNotMatchException("Invalid password");
				}
		} else {
			LOG.info("Invalid user crediantials");
			throw new UserNameNotFoundException("Invalid user login crediantials");
		}
	}

	@Override
	public String logout(String username) throws InvalidTransactionException {
		LOG.info("Logout service");
		if (isLogged) {
			LOG.info("Logout successful");
			isLogged = false;
			return "User Logged out successfully";
		} else {
			LOG.info("Logout failed");
			throw new InvalidTransactionException("LogOut Unsuccessful");
		}
	}

	@Override
	public UserData resetPassword(UserData user) throws InvalidTransactionException {
		LOG.info("Rest password Service");
		UserData validUser = userRepository.findByEmail(user.getEmail());
		if (validUser != null) {
			LOG.info("User data found");
			validUser.setPassword(user.getPassword());
			return userRepository.save(validUser);
		} else {
			LOG.info("Entered Invalid Details");
			throw new InvalidTransactionException("Invalid Email/Username");
		}
	}

}
