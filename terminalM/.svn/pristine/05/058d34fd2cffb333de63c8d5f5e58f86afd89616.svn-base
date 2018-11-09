package com.whty.assis.basicdata.dao;

import java.util.List;
import java.util.Map;

import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.basicdata.model.Area;

public interface AreaDao extends IBaseDao<Area> {

	public List<Map<String,Object>> listArea(Map<String, Object> param);
	
	public List<Map<String,Object>> queryArea(Map<String,Object> paraMap);

	List<String> queryAreaNameByAreaCode(Map<String, Object> param);

	List<Area> getArea(Map<String, Object> param);

	public List<Map<String,Object>> getTAreaByCity(Map<String, Object> areaMap);
	
	//根据等级查询
	List<Area> getAreaByLevelId(Integer levelId);
	//根据父ID
	List<Area> getAreaByParentId(Integer parentId);

}
