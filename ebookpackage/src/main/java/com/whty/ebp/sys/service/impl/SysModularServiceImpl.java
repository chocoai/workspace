package com.whty.ebp.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.ebp.sys.dao.SysModularDao;
import com.whty.ebp.sys.model.SysModular;
import com.whty.ebp.sys.service.SysModularService;

@Service
public class SysModularServiceImpl implements SysModularService {

	@Autowired
	private SysModularDao modularDao;
	
	@Override
	public List<SysModular> queryAllModular() {
		return modularDao.queryAllModular();
	}

	@Override
	public void addModular(SysModular Modular) {
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
