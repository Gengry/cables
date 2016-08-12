package com.gry.cable.dao;

import java.util.List;
import java.util.Map;

public class ColorInfoDao {

	BaseDao baseDao = BaseDao.createInstance();
	
	public int saveUnitInfo(String unit){
		String sql = "insert into tb_color (color,available) values (?,1)";
		return baseDao.execInsert(sql, new Object[]{unit});
	}

	public List<Map<String,Object>> getUnitInfo() {
		String sql;
		Object[] param;
		sql = "select * from tb_color";
		param = new Object[]{};
		return baseDao.execQuery(sql, param);
	}

	
	public int deleteUnitInfo(String unitId) {
		String sql;
		Object[] param;
		sql = "delete from tb_color where colorid = ? ";
		param = new Object[]{unitId};
		return baseDao.execUpdate(sql, param);
	}
	
}
