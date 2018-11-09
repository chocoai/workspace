/**
 * 
 */
package com.whty.assis.basicdata.dao;

import java.util.Map;

import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.basicdata.model.DeviceMonitorLog;

/**
 * @author zhangzheng
 * @date   2018年4月6日
 */
public interface DeviceMonitorLogDao extends IBaseDao<DeviceMonitorLog>{

	/**
	 * @param param
	 * @return
	 */
	DeviceMonitorLog loadByDeviceLast(Map<String, Object> param);

}
