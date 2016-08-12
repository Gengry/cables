package com.gry.cable.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.gry.cable.dao.OrderDao;

public class OrderService {

	
	OrderDao orderDao = new OrderDao();
	
	public int saveOrder(Map<String,Object> params){
		try {
			List<Map<String,Object>> list = orderDao.saveOrder(params);
			return Integer.valueOf(list.get(0).get("orderid").toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
}
