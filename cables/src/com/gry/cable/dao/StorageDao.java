package com.gry.cable.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class StorageDao {

	BaseDao baseDao = BaseDao.createInstance();
	
	public int storageIn(Vector vector,String providerName,Date storagetime){
		String sql = "insert into tb_storage (cablemodel,cablestandard,unit,quality,color,number,providername,storagetime,address,remark,available)"
				+ "values (?,?,?,?,?,?,?,?,?,'',1)";
		return baseDao.execInsert(sql,new Object[]{vector.get(1),vector.get(2),vector.get(3),vector.get(4),vector.get(5),Float.valueOf(vector.get(6).toString()),providerName,storagetime,""});
	}
	
	public int storageInPanNum(int id,float num){
		String sql = "update tb_storage set number=? where storageid=?";
		return baseDao.execUpdate(sql, new Object[]{num,id});
	}
	
	public List<Map<String,Object>> getStorage(Vector vector){
		String sql = "select * from tb_storage where cablemodel=? and cablestandard=? and quality=? and color = ? and unit <> '米'";
		return baseDao.execQuery(sql, new Object[]{vector.get(1),vector.get(2),vector.get(4),vector.get(5)});
	}

	public List<Map<String, Object>> getStorageNum(Map<String, String> params) {
		String sql = "select * from tb_storage where cablemodel=? and cablestandard=? and quality=? and color=? and unit=?";
		return baseDao.execQuery(sql, new Object[]{params.get("model"),params.get("standard"),params.get("quality"),params.get("color"),params.get("unit"),});
	}

	public int storageOut(Vector vector) {
		int rs = -1;
		String sql;
		Object[] params;
		float num = Float.valueOf(vector.get(10).toString().substring(vector.get(10).toString().indexOf(":")+1))-Float.valueOf(vector.get(6).toString());
		if(num>0){
			sql = "update tb_storage set number=? where cablemodel=? and cablestandard=? and quality=? and color=? and unit=? and number=? ";
			params = new Object[]{num,vector.get(1),vector.get(2),vector.get(4),vector.get(5),vector.get(3),Float.valueOf(vector.get(10).toString().substring(vector.get(10).toString().indexOf(":")+1))};
		}else{
			sql = "delete from tb_storage where cablemodel=? and cablestandard=? and quality=? and color=? and unit=? and number=? ";
			params = new Object[]{vector.get(1),vector.get(2),vector.get(4),vector.get(5),vector.get(3),Float.valueOf(vector.get(10).toString().substring(vector.get(10).toString().indexOf(":")+1))};
		}
		return baseDao.execUpdate(sql, params);
	}

	public List<Map<String, Object>> getStorageCheck(String model, String standard,
			String unit, String quality, String color) {
		String sql="";
		sql = "select * from tb_storage where 1=1 ";
		if(!(model == null || "".equals(model))){
			sql += " and cablemodel = '"+model+"'";
		}
		if(!(standard == null || "".equals(standard))){
			sql += " and cablestandard = '"+standard+"'";
		}
		if(!(unit == null || "".equals(unit))){
			sql += " and unit = '"+unit+"'";
		}
		if(!(quality == null || "".equals(quality))){
			sql += " and quality = '"+quality+"'";
		}
		if(!(color == null || "".equals(color))){
			sql += " and color = '"+color+"'";
		}
		return baseDao.execQuery(sql, new Object[]{});
	}
	
}
