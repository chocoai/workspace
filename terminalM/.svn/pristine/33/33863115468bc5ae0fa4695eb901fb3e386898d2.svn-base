package com.whty.assis.manage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.manage.dao.ModularDao;
import com.whty.assis.manage.model.Modular;
import com.whty.assis.manage.service.ModularService;

@Service
public class ModularServiceImpl implements ModularService {

	@Autowired
	private ModularDao modularDao;

	@Override
	public List<Modular> queryAllModular() {
		return modularDao.queryAllModular();
	}

	@Override
	public void addModular(Modular Modular) {
		modularDao.addModular(Modular);
	}

	@Override
	public void deleteModular(List list) {
		modularDao.deleteModular(list);
	}

	@Override
	public void updateModular(Map paramap) {
		modularDao.updateModular(paramap);
	}

}
