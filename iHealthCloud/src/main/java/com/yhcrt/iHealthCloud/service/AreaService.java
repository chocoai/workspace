package com.yhcrt.iHealthCloud.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.yhcrt.iHealthCloud.entity.Area;

public interface AreaService {

	List<Area> getAllArea();

	Area getAreaRootNode();

	Area getAreaById(Integer areaId);

	List<Area> getChildAreaByParentId(Integer parentId);

	int update(Area area);

	int insert(Area area);

	String getAreaFullName(Integer parentAreaId, String areaName);

	public String getAreaCode(JSONObject pdataObj);

}
