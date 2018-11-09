/**
 * 
 */
package com.whty.assis.basicdata.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.basicdata.dao.DataGatherCountLogDao;
import com.whty.assis.basicdata.dao.DeviceDataGatherLogDao;
import com.whty.assis.basicdata.service.DeviceDataGatherLogService;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * @author zhangzheng
 * @date 2018年6月1日
 */
@Service
public class DeviceDataGatherLogServiceImpl implements DeviceDataGatherLogService {

	@Autowired
	private DeviceDataGatherLogDao deviceDataGatherLogDao;

	@Autowired
	private DataGatherCountLogDao dataGatherCountLogDao;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.basicdata.service.DeviceDataGatherLogService#
	 * queryBrandPage(java.util.Map)
	 */
	@Override
	public HandlerResult queryDeviceDataGatherLogPage(Map<String, Object> paramMap) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(deviceDataGatherLogDao.listByCondition(paramMap));
		return rs;
	}

	
	@Override
	public HandlerResult queryDataGatherCountLogPage(Map<String, Object> paramMap) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(dataGatherCountLogDao.listByCondition(paramMap));
		return rs;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.basicdata.service.DeviceDataGatherLogService#
	 * queryBrandPage(java.util.Map, com.whty.page.PageContext)
	 */
	@Override
	public HandlerResult queryDeviceDataGatherLogPage(Map<String, Object> paraMap, PageContext page) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(deviceDataGatherLogDao.listByCondition(paraMap));
		rs.setPage(page);
		return rs;
	}

}