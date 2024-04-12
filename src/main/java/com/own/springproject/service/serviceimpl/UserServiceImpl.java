package com.own.springproject.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.own.springproject.model.User;
import com.own.springproject.repository.UserRepository;
import com.own.springproject.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userrepo;
	
	@Override
	public void signup(User user) {
		userrepo.save(user);
	}

	@Override
	public User login(String un, String psw) {
		return userrepo.findByUsernameAndPassword(un, psw);
		}

	@Override
	public User isUserExist(String un) {
		return userrepo.findByUsername(un);
		}

	@Override
	public List<User> getAllInfo() {
		return userrepo.findAll();
	}

	@Override
	public User getUserById(int uid) {
		return userrepo.findById(uid).get();
		}

	@Override
	public User isEmailExist(String em) {
		return userrepo.findByEmail(em);
		}

}
