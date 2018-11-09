package com.whty.ebp.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.ebp.manage.dao.PkgInfoDao;
import com.whty.ebp.manage.model.PkgInfo;
import com.whty.ebp.manage.service.PkgInfoService;

@Service
public class PkgInfoServiceImpl implements PkgInfoService{

	@Autowired
	private PkgInfoDao pkgInfoDao;
	
	@Override
	public List<PkgInfo> queryAllPkgInfo() {
		return pkgInfoDao.selectPkgInfo();
	}

}
