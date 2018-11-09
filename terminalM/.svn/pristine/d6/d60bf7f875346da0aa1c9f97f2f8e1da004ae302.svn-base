/**
 * 
 */
package com.whty.assis.basicdata.service;

import java.util.List;
import java.util.Map;

import com.whty.assis.basicdata.model.DeviceMonitorLog;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * @author zhangzheng
 * @date 2018年4月6日
 */
public interface DeviceMonitorLogService {
	public List<DeviceMonitorLog> listByCondition(Map<String, Object> paramMap);

	/**
	 * @param param
	 * @return
	 */
	public HandlerResult queryMonitor(Map<String, Object> paraMap, PageContext page);

	/**
	 * @param param
	 * @return
	 */
	public DeviceMonitorLog loadByDeviceLast(Map<String, Object> param);
}
