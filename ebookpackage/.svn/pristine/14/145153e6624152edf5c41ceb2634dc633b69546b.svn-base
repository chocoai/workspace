package com.whty.ebp.manage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.ebp.manage.dao.AreaDao;
import com.whty.ebp.manage.service.AreaService;

@Service
public class AreaServiceImpl implements AreaService {

	@Autowired
	private AreaDao areaDao;

	@Override
	public List<Map<String,Object>> queryArea(Map<String,Object> paraMap) {
		return areaDao.queryArea(paraMap);
	}

	@Override
	public List<String> queryAreaNameByAreaCode(Map<String, Object> param) throws Exception {
		return areaDao.queryAreaNameByAreaCode(param);
	}

	@Override
	public List<Map<String,Object>> getTAreaByCity(Map<String, Object> areaMap) {
		return areaDao.getTAreaByCity(areaMap);
	}

}
