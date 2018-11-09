/**
 * 
 */
package com.whty.assis.basicdata.dao;

import java.util.List;
import java.util.Map;

import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.basicdata.model.DeviceInfo;

/**
 * @author zhangzheng
 * @date   2018年3月29日
 */
public interface DeviceInfoDao extends IBaseDao<DeviceInfo>{

	/**
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> listDeviceSchoolLocation(Map<String, Object> param);

	/**
	 * @param paraMap
	 * @return
	 */
	List<DeviceInfo> listAIODevice(Map<String, Object> paraMap);

	/**
	 * @param paraMap
	 * @return
	 */
	List<DeviceInfo> listEbookpackageDevice(Map<String, Object> paraMap);

	/**
	 * @param iotId
	 * @return
	 */
	DeviceInfo loadByIdAliIotId(String iotId);

	/**
	 * @param deviceId
	 * @return
	 */
	DeviceInfo loadEbookpackageById(Integer deviceId);


	/**
	 * 查询设备总数
	 */
	int selectDeviceInfo();
}