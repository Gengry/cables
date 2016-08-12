package com.gry.cable.dao;

import java.util.Vector;

public class OrderItemDao {

	BaseDao baseDao = BaseDao.createInstance();
	
	public int saveOrderItems(Vector vector,int orderid){
		
		String sql = "insert into tb_orderitem (orderid,cablemodel,cablestandard,unit,quality,color,number,price,discount,total,remark,available) "
				+ "values (?,?,?,?,?,?,?,?,?,?,'',1)";
		int rs = baseDao.execInsert(sql, new Object[]{orderid,vector.get(1),vector.get(2),vector.get(3),
				vector.get(4),vector.get(5),Float.valueOf(vector.get(6).toString()),
				Float.valueOf(vector.get(7).toString()),Float.valueOf(vector.get(8).toString().equals("")||vector.get(8).toString()==null?"10":vector.get(8).toString()),
				Float.valueOf(vector.get(9).toString())});
		return rs;
	}
}
