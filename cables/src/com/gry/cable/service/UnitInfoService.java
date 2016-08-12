package com.gry.cable.service;

import java.util.List;
import java.util.Map;

import com.gry.cable.dao.UnitInfoDao;

public class UnitInfoService {

	UnitInfoDao unitInfoDao = new UnitInfoDao();
	
	public int saveUnitInfo(String unit){
		int rs = -1;
		rs = unitInfoDao.saveUnitInfo(unit);
		return rs;
	}
	
	public String[][] getUnitInfo(){
		List<Map<String,Object>> maps = unitInfoDao.getUnitInfo();
		String[][] datas = new String[maps.size()][2];
		for (int i=0;i<maps.size();i++) {
			datas[i][1] = (String) maps.get(i).get("unit");
			datas[i][0] = maps.get(i).get("unitid").toString();
		}
		return datas;
	}
	public String[] getUnit(){
		List<Map<String,Object>> maps = unitInfoDao.getUnitInfo();
		String[] datas = new String[maps.size()];
		for (int i=0;i<maps.size();i++) {
			datas[i] = maps.get(i).get("unit").toString();
		}
		return datas;
	}
	public String[] getUnit1(){
		List<Map<String,Object>> maps = unitInfoDao.getUnitInfo();
		String[] datas = new String[maps.size()+1];
		datas[0]="";
		for (int i=0;i<maps.size();i++) {
			datas[i+1] = maps.get(i).get("unit").toString();
		}
		return datas;
	}


	public int deleteUnitInfo(String unitid){
		return unitInfoDao.deleteUnitInfo(unitid);
	}
}
