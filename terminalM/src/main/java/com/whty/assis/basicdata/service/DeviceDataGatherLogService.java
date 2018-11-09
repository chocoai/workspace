/**
 * 
 */
package com.whty.assis.basicdata.service;

import java.util.Map;

import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * @author zhangzheng
 * @date 2018年6月1日
 */
public interface DeviceDataGatherLogService {

	public HandlerResult queryDeviceDataGatherLogPage(Map<String, Object> paramMap);

	/**
	 * @param paraMap
	 * @param page
	 * @return
	 */
	public HandlerResult queryDeviceDataGatherLogPage(Map<String, Object> paraMap, PageContext page);

	public HandlerResult queryDataGatherCountLogPage(Map<String, Object> paramMap);
}
