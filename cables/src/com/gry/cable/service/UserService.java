package com.gry.cable.service;

import java.util.Map;

import com.gry.cable.dao.UserDao;

public class UserService {

	UserDao userDao = new UserDao();
	
	public boolean loginCheck(String username,String password){
		return userDao.loginCheck(username, password);
	}
	
	public Map<String,Object> getPassword(String username){
		return userDao.getPassword(username);
	}
	public int changePassword(int userid,String password){
		return userDao.changePassword(userid,password);
	}
}
