package com.gry.cable.service;

import java.util.List;
import java.util.Map;

import com.gry.cable.dao.CustomerInfoDao;

public class CustomerInfoService {

	CustomerInfoDao customerInfoDao;
	
	public int saveCustomerInfo(String customername, String contacts, String telePhone,
			String address, String warehouse, String remark) {
		customerInfoDao = new CustomerInfoDao();
		return customerInfoDao.saveCustomerInfo(customername, contacts, telePhone, address, warehouse, remark);
	}
	
	public String[][] getCustomerInfo(String customerName,String contacts){
		customerInfoDao = new CustomerInfoDao();
		List<Map<String,Object>> maps = customerInfoDao.getCustomerInfoByStandard(customerName,contacts);
		String[][] datas = new String[maps.size()][7];
		for (int i=0;i<maps.size();i++) {
			datas[i][0] = (String) maps.get(i).get("customername");
			datas[i][1] = (String) maps.get(i).get("contacts");
			datas[i][2] = (String) maps.get(i).get("telephone");
			datas[i][3] = (String) maps.get(i).get("address");
			datas[i][4] = (String) maps.get(i).get("warehouse");
			datas[i][5] = (String) maps.get(i).get("remark");
			datas[i][6] = (String) maps.get(i).get("customerid").toString();
		}
		return datas;
		
	}

	public int deleteCableInfo(int customerId) {
		customerInfoDao = new CustomerInfoDao();
		return customerInfoDao.deleteCustomerInfo(customerId);
	}

	public List<Map<String, Object>> getAllCustomer() {
		customerInfoDao = new CustomerInfoDao();
		return customerInfoDao.getAllProvider();
	}

}
