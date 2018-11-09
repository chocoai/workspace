/**
 * 
 */
package com.whty.assis.basicdata.service;

import java.util.List;
import java.util.Map;

import com.whty.assis.basicdata.model.DeviceInfo;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * @author zhangzheng
 * @date 2018年3月29日
 */
public interface DeviceInfoService {
	
	public DeviceInfo loadById(Integer deviceId);
	
	public void saveDeviceInfo(DeviceInfo bean);

	public List<DeviceInfo> listByCondition(Map<String, Object> paramMap);

	public HandlerResult queryDeviceInfoPage(Map<String, Object> paramMap);

	public void updateDeviceInfo(DeviceInfo bean);

	/**
	 * @param paraMap
	 * @param page
	 * @return
	 */
	public HandlerResult queryAIOPage(Map<String, Object> paraMap, PageContext page);
	public List<DeviceInfo> queryAIOPage2(Map<String, Object> paraMap);

	/**
	 * @param paraMap
	 * @param page
	 * @return
	 */
	public HandlerResult queryEbookPackagePage(Map<String, Object> paraMap, PageContext page);
	public List<DeviceInfo> queryEbookPackagePage2(Map<String, Object> paraMap);

	/**
	 * @param deviceId
	 * @return
	 */
	public String lockScreen(String deviceId);

	/**
	 * @param deviceId
	 * @return
	 */
	public String updateImage(String deviceId);
	
	/**
	 * @param deviceId
	 * @return
	 */
	public String shutDown(String deviceId);
	
	/**
	 * @param deviceId
	 * @return
	 */
	public String restart(String deviceId);
	
	/**
	 * @param deviceId
	 * @return
	 */
	public String uploadAppInfo(String deviceId);

	/**
	 * @param deviceId
	 * @return
	 */
	public String updateConfig(String deviceId);
	
	/**
	 * @param deviceId
	 * @return
	 */
	public String unLockScreen(String deviceId);

	/**
	 * @param ids
	 * @return
	 */
	public String batchShutdown(String ids);

	/**
	 * @param ids
	 * @return
	 */
	public String batchRestart(String ids);

	/**
	 * @param ids
	 * @return
	 */
	public String batchSendMessage(String ids,String excuteTime,String sendText);

	/**
	 * @return
	 */
	public String sendDeviceConfig();

	/**
	 * @param ids
	 * @return
	 */
	public String batchLockScreen(String ids);

	/**
	 * @param ids
	 * @return
	 */
	public String batchUnLockScreen(String ids);

	/**
	 * @param valueOf
	 * @return
	 */
	public DeviceInfo loadEbookpackageById(Integer valueOf);

	/**
	 * @param schoolId
	 * @return
	 */
	public String checkRule(String schoolId);

	/**
	 * 查询设备总数
	 */
	int selectDeviceInfo();
	
}