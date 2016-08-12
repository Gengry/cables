package com.gry.cable.dao;

import java.util.List;
import java.util.Map;

public class QualityInfoDao {

	BaseDao baseDao = BaseDao.createInstance();
	
	public int saveUnitInfo(String unit){
		String sql = "insert into tb_quality (quality,available) values (?,1)";
		return baseDao.execInsert(sql, new Object[]{unit});
	}

	public List<Map<String,Object>> getUnitInfo() {
		String sql;
		Object[] param;
		sql = "select * from tb_quality";
		param = new Object[]{};
		return baseDao.execQuery(sql, param);
	}

	
	public int deleteUnitInfo(String unitId) {
		String sql;
		Object[] param;
		sql = "delete from tb_quality where qualityid = ? ";
		param = new Object[]{unitId};
		return baseDao.execUpdate(sql, param);
	}
	
}
