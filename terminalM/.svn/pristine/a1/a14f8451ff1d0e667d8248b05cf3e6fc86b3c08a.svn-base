package com.whty.assis.basicdata.service;

import java.util.List;
import java.util.Map;

import com.whty.assis.basicdata.model.Area;
import com.whty.assis.basicdata.model.SchoolAreaTreeNode;


public interface AreaService {

	public List<Map<String,Object>> listArea(Map<String,Object> param);
	
	public List<Map<String,Object>> queryArea(Map<String,Object> paraMap);

	public List<String> queryAreaNameByAreaCode(Map<String, Object> param) throws Exception;

	public List<Map<String,Object>> getTAreaByCity(Map<String, Object> areaMap);

	List<Area> getArea(Map<String, Object> param);
	
	List<SchoolAreaTreeNode> queryAreaTree();
	
	//根据等级查询
	List<Area> getAreaByLevelId(Integer levelId);
	//根据父ID
	List<Area> getAreaByParentId(Integer parentId);
}
