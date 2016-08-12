package com.gry.cable.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JComboBox;

import com.gry.cable.dao.BaseDao;
import com.gry.cable.dao.StorageDao;

public class StorageService {

	StorageDao storageDao = new StorageDao();
	
	public int storageIn(Vector vector,String providerName,Date storagetime){
		int rs = -1;
		for (Object data : vector) {
			Vector data1 = (Vector)data;
			if(!data1.get(3).equals("米")){
				List<Map<String,Object>> list = storageDao.getStorage(data1);
				if(list.size()>0){
					rs =storageDao.storageInPanNum((Integer)list.get(0).get("storageid"), (Float)list.get(0).get("number")+Float.valueOf(data1.get(6).toString()));
				}else{
					rs =storageDao.storageIn(data1,providerName,storagetime);
				}
			}else{
				rs =storageDao.storageIn(data1,providerName,storagetime);
			}
		}
		return rs;
	}

	public String[] getStorageNum(Map<String, String> params) {
		List<Map<String,Object>> list = storageDao.getStorageNum(params);
		String[] data = new String[list.size()];
		for (int i = 0; i<list.size();i++) {
			data[i] = list.get(i).get("number").toString();
		}
		return data;
	}

	public int storageOut(Vector vector) {
		int rs = -1;
		for (Object data : vector) {
			Vector data1 = (Vector)data;
			rs = storageDao.storageOut(data1);
		}
		return rs;
	}

	public String[][] getStorageCheck(String model, String standard,
			String unit, String quality, String color) {
		List<Map<String,Object>> maps = storageDao.getStorageCheck(model,standard,unit,quality,color);
		String[][] datas = new String[maps.size()][11];
		for (int i=0;i<maps.size();i++) {
			datas[i][0] =  i+1+"";
			//[{number=2.0, unit=米, storagetime=2016-08-07 16:33:47.0, address=, color=5, available=1, cablemodel=测试, cablestandard=1*1, remark=, providername=1, storageid=2, quality=qweq}
			datas[i][1] =  maps.get(i).get("cablemodel").toString();
			datas[i][2] =  maps.get(i).get("cablestandard").toString();
			datas[i][3] =  maps.get(i).get("unit").toString();
			datas[i][4] =  maps.get(i).get("quality").toString();
			datas[i][5] =  maps.get(i).get("color").toString();
			datas[i][6] =  maps.get(i).get("number").toString();
			datas[i][7] =  maps.get(i).get("providername").toString();
			datas[i][8] =  maps.get(i).get("storagetime").toString();
			datas[i][9] =  maps.get(i).get("address").toString();
			datas[i][9] =  maps.get(i).get("remark").toString();
		}
		return datas;
	}
	
}
