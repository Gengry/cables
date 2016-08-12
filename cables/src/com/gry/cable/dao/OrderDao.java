package com.gry.cable.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class OrderDao {

	BaseDao baseDao = BaseDao.createInstance();
	
	public List<Map<String, Object>> saveOrder(Map<String,Object> params) throws ParseException{
		String sql = "insert into tb_order (inorout,companyname,contacts,telephone,address,warehouse,ordertime,totalprice,remark,available) values (?,?,?,?,?,?,?,?,'',1)";
		SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
		baseDao.execInsert(sql, new Object[]{params.get("inorout"),params.get("companyname"),params.get("contacts"),params.get("telephone"),params.get("address"),
				params.get("warehouse"),sdf.parseObject(params.get("ordertime").toString()),params.get("totalprice")});
		sql =" SELECT max(orderid) as orderid from tb_order ";
		return baseDao.execQuery(sql, new Object[]{});
	}
	
}
