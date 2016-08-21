package com.gry.cable.dao;

public class UserDao {

	BaseDao baseDao = BaseDao.createInstance();
	
	public boolean loginCheck(String username,String password){
		String sql = "select * from tb_userinfo where username=? and password=?";
		if(baseDao.execQuery(sql, new Object[]{username,password}).size()==1){
			return true;
		}else{
			return false;
		}
	}
	
}
