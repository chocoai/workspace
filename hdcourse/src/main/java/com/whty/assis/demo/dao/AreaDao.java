package com.whty.assis.demo.dao;

import java.util.List;
import java.util.Map;

import com.whty.assis.demo.model.Area;

public interface AreaDao {
	public List<Map> queryArea(Map paraMap);

	List<String> queryAreaNameByAreaCode(Map<String, Object> param);

	List<Area> getArea(Map<String, Object> param);

	public List<Map> getTAreaByCity(Map<String, Object> areaMap);
}
