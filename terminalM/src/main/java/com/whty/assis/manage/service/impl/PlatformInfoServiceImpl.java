package com.whty.assis.manage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.manage.dao.PlatformInfoDao;
import com.whty.assis.manage.model.PlatformInfo;
import com.whty.assis.manage.service.PlatformInfoService;
import com.whty.page.util.HandlerResult;

@Service
public class PlatformInfoServiceImpl implements PlatformInfoService {

	@Autowired
	private PlatformInfoDao platformInfoDao;

	@Override
	public HandlerResult queryPlatformInfo(Map<String, Object> paramMap) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(platformInfoDao.listByCondition(paramMap));
		return rs;
	}

	@Override
	public void save(PlatformInfo platformInfo) {
		platformInfoDao.save(platformInfo);

	}

	@Override
	public void update(PlatformInfo platformInfo) {
		platformInfoDao.update(platformInfo);
	}

	@Override
	public void deleteByPlatformInfo(String platformCode) {
		platformInfoDao.deleteByPlatformInfo(platformCode);
	}

	@Override
	public List<PlatformInfo> listByCondition(HashMap<String, Object> hashMap) {
		return platformInfoDao.listByCondition(hashMap);
	}
}
