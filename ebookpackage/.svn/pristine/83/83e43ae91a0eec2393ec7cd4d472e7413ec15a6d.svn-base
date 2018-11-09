package com.whty.ebp.manage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.ebp.manage.dao.PlatformInfoDao;
import com.whty.ebp.manage.model.PlatformInfo;
import com.whty.ebp.manage.service.PlatformInfoService;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

@Service
public class PlatformInfoServiceImpl implements PlatformInfoService {

	@Autowired
	private PlatformInfoDao platformInfoDao;

	@Override
	public List<Map<String, Object>> queryPlatformInfoByAppWhiteListId(Map<String, Object> params) {
		return platformInfoDao.queryPlatformInfoByAppWhiteListId(params);
	}
	
	@Override
	public List<Map<String, Object>> queryPlatformInfoByAppId(Map<String, Object> params) {
		return platformInfoDao.queryPlatformInfoByAppId(params);
	}

	@Override
	public PlatformInfo loadById(String platformCode) {
		return platformInfoDao.loadById(platformCode);
	}

	@Override
	public List<PlatformInfo> listByCondition(Map<String, Object> param) {
		return platformInfoDao.listByCondition(param);
	}

	@Override
	public void save(PlatformInfo bean) {
		platformInfoDao.save(bean);
	}

	@Override
	public void delete(String platformCode) {
		platformInfoDao.deleteById(platformCode);

	}

	@Override
	public HandlerResult queryPlatformInfo(Map<String, Object> paramMap, PageContext page) {
		HandlerResult re = new HandlerResult();
		List<PlatformInfo> appList = platformInfoDao.listByCondition(paramMap);
		re.setResultList(appList);
		re.setPage(page);
		return re;
	}

	@Override
	public void update(PlatformInfo bean) {
		platformInfoDao.update(bean);
	}

	@Override
	public List<Map<String, Object>> queryPlatformInfoByDerivativeEbpAppId(Map<String, Object> param) {
		return platformInfoDao.queryPlatformInfoByDerivativeEbpAppId(param);
	}

}
