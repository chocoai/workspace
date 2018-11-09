package com.yhcrt.healthcloud.system.service;

import java.util.List;

import com.yhcrt.healthcloud.system.entity.Area;

public interface AreaService {
	
	List<Area> getAllArea();
	
	Area getAreaRootNode();
	
	Area getAreaById(Integer areaId);
	
	List<Area> getChildAreaByParentId(Integer parentId);
	
	int update(Area area);
	
	int insert(Area area);
	
	String getAreaFullName(Integer parentAreaId,String areaName);

}
