package com.whty.assis.demo.service;

import java.util.List;
import java.util.Map;

import com.whty.assis.demo.model.SoftComponent;
import com.whty.page.util.HandlerResult;

public interface SoftComponentService {

	@SuppressWarnings("rawtypes")
	public HandlerResult querySoftComponentPage(Map paramMap);

	@SuppressWarnings("rawtypes")
	public List<SoftComponent> querySoftComponent(Map paramMap);

	public SoftComponent querySoftComponentById(String id);

	public void saveSoftComponent(SoftComponent softComponent);

	public void updateSoftComponent(SoftComponent softComponent);

	@SuppressWarnings("rawtypes")
	public void deleteSoftComponent(List list);

	public void saveSoftComponentBatch(List<SoftComponent> list);

	@SuppressWarnings("rawtypes")
	public List<SoftComponent> querySoftComponentForApi(Map paramMap);
}
