package com.xender.practical.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xender.practical.model.UserData;


@Repository
public interface IUserRepository extends JpaRepository<UserData, Integer> {
	
	public abstract UserData findByUsername(String username);
	public abstract UserData findByEmail(String email);
	public abstract UserData findByMobileNumber(Long mobileNumber);
	public abstract UserData findByUserid(Integer userid);

//	public User findByUser_email(String user_email,String user_mobile,String user_name);
//	public String findByUser_email(String user_email);
//	public String findByUser_mobile(String user_mobile);
//	public String findByUser_name(String user_name);
	
//	public List<User> findByUser_email(String user_email);
//	public List<User> findByUser_id(Integer user_id);
//	public List<User> findByUser_mobile(String user_mobile);
//	public List<User> findByUser_name(String user_name);
}
