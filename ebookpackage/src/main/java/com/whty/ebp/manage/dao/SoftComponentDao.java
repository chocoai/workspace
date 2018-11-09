package com.whty.ebp.manage.dao;

import java.util.List;
import java.util.Map;

import com.whty.ebp.manage.model.SoftComponent;

public interface SoftComponentDao {
	
	@SuppressWarnings("rawtypes")
	public List<SoftComponent> querySoftComponent(Map paramMap);
	
	public void saveSoftComponent(SoftComponent softComponent);
	
	public void saveSoftComponentBatch(List<SoftComponent> list);
	
	public void updateSoftComponent(SoftComponent softComponent);
	
	@SuppressWarnings("rawtypes")
	public void deleteSoftComponent(List list);
	
	@SuppressWarnings("rawtypes")
	public List<SoftComponent> querySoftComponentForApi(Map paramMap);
	
}
