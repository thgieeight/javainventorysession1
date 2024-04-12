package com.own.springproject.service;

import java.util.List;

import com.own.springproject.model.User;

public interface UserService {

	void signup(User user);
	User login(String un, String psw);
	User isUserExist(String un);
	List<User> getAllInfo(); 
	User getUserById(int uid);
	User isEmailExist(String em);

}
