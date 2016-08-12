package com.gry.cable.service;

import java.util.Vector;

import com.gry.cable.dao.OrderItemDao;

public class OrderItemService {

	OrderItemDao dao = new OrderItemDao();
	
	public int saveOrderItems(Vector vector,int id){
		int rs = -1;
		for (Object data : vector) {
			Vector data1 = (Vector)data;
			rs = dao.saveOrderItems(data1, id);
		}
		return rs;
	}
	
}
