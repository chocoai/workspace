package com.whty.assis.demo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.demo.dao.ActivityCountDao;
import com.whty.assis.demo.model.AreaMonthActivityCount;
import com.whty.assis.demo.model.OrgMonthActivityCount;
import com.whty.assis.demo.service.ActivityCountService;
import com.whty.page.util.HandlerResult;

@Service
public class ActivityCountServiceImpl implements ActivityCountService {

	@Autowired
	private ActivityCountDao activityCountDao;

	@Override
	public List<OrgMonthActivityCount> getOrgMonthActivityCount(Map<String, Object> map) {
		return activityCountDao.getOrgMonthActivityCount(map);

	}

	@Override
	public List<AreaMonthActivityCount> getAreaMonthActivityCount(Map<String, Object> map) {
		return activityCountDao.getAreaMonthActivityCount(map);
	}

	@Override
	public HandlerResult queryAreaMonthActivityCount(Map<String, Object> paramMap) {
		HandlerResult handlerResult = new HandlerResult();
		handlerResult.setResultList(activityCountDao.getAreaMonthActivityCount(paramMap));
		return handlerResult;
	}

	@Override
	public HandlerResult queryOrgMonthActivityCount(Map<String, Object> paramMap) {
		HandlerResult handlerResult = new HandlerResult();
		handlerResult.setResultList(activityCountDao.getOrgMonthActivityCount(paramMap));
		return handlerResult;
	}

}
