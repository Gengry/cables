package com.gry.cable.service;

import java.util.List;
import java.util.Map;

import com.gry.cable.dao.ProviderInfoDao;

public class ProviderInfoService {

	ProviderInfoDao providerInfoDao;
	
	public int saveProviderInfo(String providername, String contacts, String telePhone,
			String address, String warehouse, String remark) {
		providerInfoDao = new ProviderInfoDao();
		return providerInfoDao.saveProviderInfo(providername, contacts, telePhone, address, warehouse, remark);
	}
	
	public String[][] getProviderInfo(String providerName,String contacts){
		providerInfoDao = new ProviderInfoDao();
		List<Map<String,Object>> maps = providerInfoDao.getProviderInfoByStandard(providerName,contacts);
		String[][] datas = new String[maps.size()][7];
		for (int i=0;i<maps.size();i++) {
			datas[i][0] = (String) maps.get(i).get("providername");
			datas[i][1] = (String) maps.get(i).get("contacts");
			datas[i][2] = (String) maps.get(i).get("telephone");
			datas[i][3] = (String) maps.get(i).get("address");
			datas[i][4] = (String) maps.get(i).get("warehouse");
			datas[i][5] = (String) maps.get(i).get("remark");
			datas[i][6] = (String) maps.get(i).get("providerid").toString();
		}
		return datas;
		
	}

	public int deleteProviderInfo(int providerId) {
		providerInfoDao = new ProviderInfoDao();
		return providerInfoDao.deleteProviderInfo(providerId);
	}

	public List<Map<String, Object>> getAllProvider() {
		providerInfoDao = new ProviderInfoDao();
		return providerInfoDao.getAllProvider();
	}

}
