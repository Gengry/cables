package com.gry.cable.dao;

import java.util.List;
import java.util.Map;

public class CustomerInfoDao {

BaseDao baseDao = BaseDao.createInstance();
	
	public int saveCustomerInfo(String customername, String contacts, String telePhone,
			String address, String warehouse, String remark){
		String sql = "insert into tb_customer (customername,contacts,telePhone,address,warehouse,remark,available) values (?,?,?,?,?,?,1)";
		return baseDao.execInsert(sql, new Object[]{customername,contacts,telePhone,address,warehouse,remark});
	}

	public List<Map<String, Object>> getCustomerInfoByStandard(
			String customerName, String contacts) {
		String sql;
		sql = "select * from tb_customer where 1=1 ";
		if(!(customerName == null || "".equals(customerName))){
			sql += " and customername = '"+customerName+"'";
		}
		if(!(contacts == null || "".equals(contacts))){
			sql += " and contacts = '"+contacts+"'";
		}
		return baseDao.execQuery(sql, new Object[]{});
	}

	public int deleteCustomerInfo(int customerId) {
		String sql;
		sql = "delete from tb_customer where customerid ='"+customerId+"' ";
		return baseDao.execUpdate(sql, new Object[]{});
	}

	public List<Map<String, Object>> getAllProvider() {
		String sql;
		sql = " select * from tb_customer where 1=1 ";
		return baseDao.execQuery(sql, new Object[]{});
	}
	
}
