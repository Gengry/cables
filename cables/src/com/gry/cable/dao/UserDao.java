package com.gry.cable.dao;

import java.util.Map;

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

	public Map<String, Object> getPassword(String username) {
		String sql = "select * from tb_userinfo where username=? ";
		return baseDao.execQuery(sql, new Object[]{username}).get(0);
	}

	public int changePassword(int userid, String password) {
		String sql = "update tb_userinfo set password=? where userid=? ";
		return baseDao.execUpdate(sql, new Object[]{password,userid});
	}
	
}
