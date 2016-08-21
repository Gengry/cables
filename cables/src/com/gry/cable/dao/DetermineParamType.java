package com.gry.cable.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import com.gry.cable.common.DateUtil;


public class DetermineParamType {

	public static void determine(PreparedStatement ps, int index, Object value) throws SQLException{
		if(null == value){
			ps.setString(index, null);
		}else
		if(value instanceof Date){
			ps.setObject(index, DateUtil.toString((Date)value, "yyyy-MM-dd HH:mm:ss"));
		}else{
			ps.setObject(index, value);
		}
	}
}