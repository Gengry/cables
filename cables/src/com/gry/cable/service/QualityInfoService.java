package com.gry.cable.service;

import java.util.List;
import java.util.Map;

import com.gry.cable.dao.QualityInfoDao;

public class QualityInfoService {

	QualityInfoDao unitInfoDao = new QualityInfoDao();
	
	public int saveUnitInfo(String unit){
		int rs = -1;
		rs = unitInfoDao.saveUnitInfo(unit);
		return rs;
	}
	
	public String[][] getUnitInfo(){
		List<Map<String,Object>> maps = unitInfoDao.getUnitInfo();
		String[][] datas = new String[maps.size()][2];
		for (int i=0;i<maps.size();i++) {
			datas[i][1] = (String) maps.get(i).get("quality");
			datas[i][0] = maps.get(i).get("qualityid").toString();
		}
		return datas;
	}
	
	public String[] getUnit(){
		List<Map<String,Object>> maps = unitInfoDao.getUnitInfo();
		String[] datas = new String[maps.size()];
		for (int i=0;i<maps.size();i++) {
			datas[i] = maps.get(i).get("quality").toString();
		}
		return datas;
	}
	public String[] getUnit1(){
		List<Map<String,Object>> maps = unitInfoDao.getUnitInfo();
		String[] datas = new String[maps.size()+1];
		datas[0]="";
		for (int i=0;i<maps.size();i++) {
			datas[i+1] = maps.get(i).get("quality").toString();
		}
		return datas;
	}

	public int deleteUnitInfo(String unitid){
		return unitInfoDao.deleteUnitInfo(unitid);
	}
}
