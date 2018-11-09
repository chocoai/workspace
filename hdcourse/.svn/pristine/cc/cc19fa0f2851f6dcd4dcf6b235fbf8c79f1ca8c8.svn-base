package com.whty.assis.demo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.demo.dao.AreaDao;
import com.whty.assis.demo.model.Area;
import com.whty.assis.demo.service.AreaService;

@Service
public class AreaServiceImpl implements AreaService {

	@Autowired
	private AreaDao areaDao;

	@Override
	public List<Map> queryArea(Map paraMap) {
		return areaDao.queryArea(paraMap);
	}

	@Override
	public List<String> queryAreaNameByAreaCode(Map<String, Object> param) throws Exception {
		return areaDao.queryAreaNameByAreaCode(param);
	}

	@Override
	public List<Map> getTAreaByCity(Map<String, Object> areaMap) {
		return areaDao.getTAreaByCity(areaMap);
	}

	@Override
	public List<Area> getArea(Map<String, Object> param) {
		return areaDao.getArea(param);
	}

}
