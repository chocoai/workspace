package com.whty.ebp.manage.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.ebp.manage.dao.SoftComponentDao;
import com.whty.ebp.manage.model.SoftComponent;
import com.whty.ebp.manage.service.SoftComponentService;
import com.whty.page.util.HandlerResult;

@Service
public class SoftComponentServiceImpl implements SoftComponentService {
	
	@Autowired
	private SoftComponentDao softComponentDao;

	@SuppressWarnings("rawtypes")
	@Override
	public HandlerResult querySoftComponentPage(Map paramMap) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(softComponentDao.querySoftComponent(paramMap));
		return rs;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<SoftComponent> querySoftComponent(Map paramMap) {
		return softComponentDao.querySoftComponent(paramMap);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public SoftComponent querySoftComponentById(String id) {
		Map paramMap = new HashMap();
		paramMap.put("id", id);
		List<SoftComponent> list = softComponentDao.querySoftComponent(paramMap);
		return list != null && list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public void saveSoftComponent(SoftComponent softComponent) {
		softComponentDao.saveSoftComponent(softComponent);
	}

	@Override
	public void updateSoftComponent(SoftComponent softComponent) {
		softComponentDao.updateSoftComponent(softComponent);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void deleteSoftComponent(List list) {
		Map paramMap = new HashMap();
		paramMap.put("idList", list);
		List<SoftComponent> softComponentList = softComponentDao.querySoftComponent(paramMap);
		for(SoftComponent softComponent : softComponentList){
			File softFile = new File(softComponent.getFileUrl());
			if(softFile.exists()){
				softFile.deleteOnExit();
				try {
					FileUtils.forceDelete(softFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		softComponentDao.deleteSoftComponent(list);
	}
	
	@Override
	public void saveSoftComponentBatch(List<SoftComponent> list){
		softComponentDao.saveSoftComponentBatch(list);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<SoftComponent> querySoftComponentForApi(Map paramMap){
		return softComponentDao.querySoftComponentForApi(paramMap);
	}

}
