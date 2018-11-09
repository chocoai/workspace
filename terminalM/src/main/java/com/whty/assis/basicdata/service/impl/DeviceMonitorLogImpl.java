/**
 * 
 */
package com.whty.assis.basicdata.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.basicdata.dao.DeviceMonitorLogDao;
import com.whty.assis.basicdata.model.DeviceMonitorLog;
import com.whty.assis.basicdata.service.DeviceMonitorLogService;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * @author zhangzheng
 * @date 2018年4月6日
 */
@Service
public class DeviceMonitorLogImpl implements DeviceMonitorLogService {

	@Autowired
	private DeviceMonitorLogDao deviceMonitorLogDao;

	@Override
	public List<DeviceMonitorLog> listByCondition(Map<String, Object> paramMap) {
		return deviceMonitorLogDao.listByCondition(paramMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.DeviceMonitorLogService#queryMonitor(
	 * java.util.Map)
	 */
	@Override
	public HandlerResult queryMonitor(Map<String, Object> paraMap, PageContext page) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(deviceMonitorLogDao.listByCondition(paraMap));
		rs.setPage(page);
		return rs;
	}

	/* (non-Javadoc)
	 * @see com.whty.assis.basicdata.service.DeviceMonitorLogService#loadByDeviceLast(java.util.Map)
	 */
	@Override
	public DeviceMonitorLog loadByDeviceLast(Map<String, Object> param) {
		return deviceMonitorLogDao.loadByDeviceLast(param);
	}
}
