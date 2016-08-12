package com.gry.cable.dao;

import java.util.List;
import java.util.Map;

public class CableInfoDao {

	BaseDao baseDao = BaseDao.createInstance();
	
	public int saveCableInfo(String cableModel,String cableStandard){
		String sql = "insert into tb_cable (cablemodel,cablestandard,remark,available) values (?,?,'',1)";
		return baseDao.execInsert(sql, new Object[]{cableModel,cableStandard});
	}

	public List<Map<String,Object>> getCableInfoByModel(String cableModel) {
		String sql;
		Object[] param;
		if(cableModel == null||"".equals(cableModel)){
			sql = "select distinct cablemodel from tb_cable";
			param = new Object[]{};
		}else{
			sql = "select distinct cablemodel from tb_cable where cablemodel = ?";
			param = new Object[]{cableModel};
		}
		return baseDao.execQuery(sql, param);
	}

	public List<Map<String, Object>> getCableInfoByStandard(String cableModel) {
		String sql;
		Object[] param;
		sql = "select  cablemodel,cablestandard from tb_cable where cablemodel = ?";
		param = new Object[]{cableModel};
		
		return baseDao.execQuery(sql, param);
	}
	
	public List<Map<String, Object>> getCableInfoByModelAndStandard(String cableModel,String cableStandard) {
		String sql;
		Object[] param;
		sql = "select  cablemodel,cablestandard from tb_cable where cablemodel = ? and cablestandard = ?";
		param = new Object[]{cableModel,cableStandard};
		
		return baseDao.execQuery(sql, param);
	}

	public int deleteCableInfo(String cableModel, String cableStandard) {
		String sql;
		Object[] param;
		if(!(cableStandard == null|| "".equals(cableStandard))){
			sql = "delete from tb_cable where cablemodel = ? and cablestandard = ?";
			param = new Object[]{cableModel,cableStandard};
		}else{
			sql = "delete from tb_cable where cablemodel = ? ";
			param = new Object[]{cableModel};
		}
		return baseDao.execUpdate(sql, param);
	}

	public List<Map<String, Object>> getCableInfoModel() {
		String sql;
		sql = "select distinct cablemodel from tb_cable ";
		return baseDao.execQuery(sql, new Object[]{});
	}
	
}
