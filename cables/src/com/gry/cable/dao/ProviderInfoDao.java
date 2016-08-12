package com.gry.cable.dao;

import java.util.List;
import java.util.Map;

public class ProviderInfoDao {

BaseDao baseDao = BaseDao.createInstance();
	
	public int saveProviderInfo(String customername, String contacts, String telePhone,
			String address, String warehouse, String remark){
		String sql = "insert into tb_provider (providername,contacts,telePhone,address,warehouse,remark,available) values (?,?,?,?,?,?,1)";
		return baseDao.execInsert(sql, new Object[]{customername,contacts,telePhone,address,warehouse,remark});
	}

	public List<Map<String, Object>> getProviderInfoByStandard(
			String customerName, String contacts) {
		String sql;
		sql = "select * from tb_provider where 1=1 ";
		if(!(customerName == null || "".equals(customerName))){
			sql += " and providername = '"+customerName+"'";
		}
		if(!(contacts == null || "".equals(contacts))){
			sql += " and contacts = '"+contacts+"'";
		}
		return baseDao.execQuery(sql, new Object[]{});
	}

	public int deleteProviderInfo(int providerId) {
		String sql;
		//Object[] param;
		sql = "delete from tb_provider where providerid ='"+providerId+"' ";
		return baseDao.execUpdate(sql, new Object[]{});
	}

	public List<Map<String, Object>> getAllProvider() {
		String sql;
		sql = " select * from tb_provider where 1=1 ";
		return baseDao.execQuery(sql, new Object[]{});
	}
	
}
