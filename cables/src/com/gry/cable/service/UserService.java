package com.gry.cable.service;

import com.gry.cable.dao.UserDao;

public class UserService {

	UserDao userDao = new UserDao();
	
	public boolean loginCheck(String username,String password){
		return userDao.loginCheck(username, password);
	}
	
}
