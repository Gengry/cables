package com.gry.cable.service;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.gry.cable.dao.CableInfoDao;

public class CableInfoService {

	CableInfoDao cableInfoDao = new CableInfoDao();
	
	public int saveCableInfo(Vector vector,String cableModel){
		int rs = -1;
		for (Object data : vector) {
			Vector data1 = (Vector)data;
			int flat = cableInfoDao.getCableInfoByModelAndStandard(cableModel,data1.get(0).toString()).size();
			if(flat==0){
				rs = cableInfoDao.saveCableInfo(cableModel, data1.get(0).toString());
			}
		}
		return rs;
	}
	
	public String[][] getCableInfoByModel(String cableModel){
		List<Map<String,Object>> maps = cableInfoDao.getCableInfoByModel(cableModel);
		String[][] datas = new String[maps.size()][2];
		for (int i=0;i<maps.size();i++) {
			datas[i][0] = maps.get(i).get("cablemodel").toString();
		}
		return datas;
	}
	public String[] getStandardByModel(String cableModel){
		List<Map<String,Object>> maps = cableInfoDao.getCableInfoByStandard(cableModel);
		String[] datas = new String[maps.size()];
		for (int i=0;i<maps.size();i++) {
			datas[i] = maps.get(i).get("cablestandard").toString();
		}
		return datas;
	}
	public String[] getStandardByModel1(String cableModel){
		List<Map<String,Object>> maps = cableInfoDao.getCableInfoByStandard(cableModel);
		String[] datas = new String[maps.size()+1];
		datas[0] = "";
		for (int i=0;i<maps.size();i++) {
			datas[i+1] = maps.get(i).get("cablestandard").toString();
		}
		return datas;
	}

	public String[][] getCableInfoByStandard(String cableModel) {
		List<Map<String,Object>> maps = cableInfoDao.getCableInfoByStandard(cableModel);
		String[][] datas = new String[maps.size()][2];
		for (int i=0;i<maps.size();i++) {
			datas[i][0] =  maps.get(i).get("cablemodel").toString();
			datas[i][1] =  maps.get(i).get("cablestandard").toString();
		}
		return datas;
	}
	public int deleteCableInfo(String cableModel,String cableStandard){
		return cableInfoDao.deleteCableInfo(cableModel,cableStandard);
	}

	public String[] getCableInfoModel() {
		List<Map<String,Object>> maps = cableInfoDao.getCableInfoModel();
		String[] model = new String[maps.size()];
		for (int i=0;i<maps.size();i++) {
			model[i] = maps.get(i).get("cablemodel").toString();
		}
		return model;
	}
	public String[] getCableInfoModel1() {
		List<Map<String,Object>> maps = cableInfoDao.getCableInfoModel();
		String[] model = new String[maps.size()+1];
		model[0]="";
		for (int i=0;i<maps.size();i++) {
			model[i+1] = maps.get(i).get("cablemodel").toString();
		}
		return model;
	}
}
