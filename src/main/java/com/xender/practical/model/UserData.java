package com.xender.practical.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.context.annotation.Bean;

@Entity
public class UserData
{
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userid;
	private String name;
	private String email;
	private String username;
	private String password;
	private String mobileNumber;
	
	
	public UserData() {

	}

	public UserData(Integer userid, String name, String email, String username, String password, String mobileNumber) {
		super();
		this.userid = userid;
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.mobileNumber = mobileNumber;
	}
	
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	@Override
	public String toString() {
		return "UserData [userid=" + userid + ", name=" + name + ", email=" + email + ", username=" + username
				+ ", password=" + password + ", mobileNumber=" + mobileNumber + "]";
	}
	
	
	
}