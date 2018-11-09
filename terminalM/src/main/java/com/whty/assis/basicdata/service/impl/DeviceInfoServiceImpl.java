/**
 * 
 */
package com.whty.assis.basicdata.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.basicdata.dao.DeviceInfoDao;
import com.whty.assis.basicdata.model.DeviceInfo;
import com.whty.assis.basicdata.service.DeviceInfoService;
import com.whty.assis.basicdata.service.SendDeviceCommandService;
import com.whty.assis.basicdata.service.impl.devicecommand.CheckRuleServiceImpl;
import com.whty.assis.basicdata.service.impl.devicecommand.LockScreenCommandServiceImpl;
import com.whty.assis.basicdata.service.impl.devicecommand.RestartCommandServiceImpl;
import com.whty.assis.basicdata.service.impl.devicecommand.SendMessageCommandServiceImpl;
import com.whty.assis.basicdata.service.impl.devicecommand.ShutdownCommandServiceImpl;
import com.whty.assis.basicdata.service.impl.devicecommand.UnLockScreenCommandServiceImpl;
import com.whty.assis.basicdata.service.impl.devicecommand.UpdateConfigCommandServiceImpl;
import com.whty.assis.basicdata.service.impl.devicecommand.UpdateImageCommandServiceImpl;
import com.whty.assis.basicdata.service.impl.devicecommand.UploadAppInfoCommandServiceImpl;
import com.whty.common.util.SysConstants;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * @author zhangzheng
 * @date 2018年3月29日
 */
@Service
public class DeviceInfoServiceImpl implements DeviceInfoService {

	@Autowired
	private DeviceInfoDao deviceInfoDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.DeviceInfoService#saveDeviceInfo(com.
	 * whty.assis.basicdata.model.DeviceInfo)
	 */
	@Override
	public void saveDeviceInfo(DeviceInfo bean) {
		deviceInfoDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.DeviceInfoService#listByCondition(java.
	 * util.Map)
	 */
	@Override
	public List<DeviceInfo> listByCondition(Map<String, Object> paramMap) {
		return deviceInfoDao.listByCondition(paramMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.DeviceInfoService#queryDeviceInfoPage(
	 * java.util.Map)
	 */
	@Override
	public HandlerResult queryDeviceInfoPage(Map<String, Object> paramMap) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(deviceInfoDao.listByCondition(paramMap));
		return rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.DeviceInfoService#updateDeviceInfo(com.
	 * whty.assis.basicdata.model.DictType)
	 */
	@Override
	public void updateDeviceInfo(DeviceInfo bean) {
		deviceInfoDao.update(bean);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.DeviceInfoService#queryAIOPage(java.util
	 * .Map, com.whty.page.PageContext)
	 */
	@Override
	public HandlerResult queryAIOPage(Map<String, Object> paraMap, PageContext page) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(deviceInfoDao.listAIODevice(paraMap));
		rs.setPage(page);
		return rs;
	}

	@Override
	public List<DeviceInfo> queryAIOPage2(Map<String, Object> paraMap) {
		return deviceInfoDao.listAIODevice(paraMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.DeviceInfoService#queryebookPackagePage(
	 * java.util.Map, com.whty.page.PageContext)
	 */
	@Override
	public HandlerResult queryEbookPackagePage(Map<String, Object> paraMap, PageContext page) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(deviceInfoDao.listEbookpackageDevice(paraMap));
		rs.setPage(page);
		return rs;
	}

	@Override
	public List<DeviceInfo> queryEbookPackagePage2(Map<String, Object> paraMap) {
		return deviceInfoDao.listEbookpackageDevice(paraMap);
	}

	/*
	 * (non-Javadoc) 关机指令
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.DeviceInfoService#lockScreen(java.lang.
	 * String)
	 */
	@Override
	public String shutDown(String deviceId) {

		final DeviceInfo deviceInfo = deviceInfoDao.loadById(Integer.valueOf(deviceId));

		if (deviceInfo == null) {
			return "未找到设备!";
		}

		SendDeviceCommandService sendCommand = new ShutdownCommandServiceImpl();

		sendCommand.sendCommand(deviceInfo);

		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc) 重启
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.DeviceInfoService#lockScreen(java.lang.
	 * String)
	 */
	@Override
	public String restart(String deviceId) {

		DeviceInfo deviceInfo = deviceInfoDao.loadById(Integer.valueOf(deviceId));

		if (deviceInfo == null) {
			return "未找到设备!";
		}

		SendDeviceCommandService sendCommand = new RestartCommandServiceImpl();

		sendCommand.sendCommand(deviceInfo);

		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc) 上传应用
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.DeviceInfoService#lockScreen(java.lang.
	 * String)
	 */
	@Override
	public String uploadAppInfo(String deviceId) {

		DeviceInfo deviceInfo = deviceInfoDao.loadById(Integer.valueOf(deviceId));

		if (deviceInfo == null) {
			return "未找到设备!";
		}

		SendDeviceCommandService sendCommand = new UploadAppInfoCommandServiceImpl();
		sendCommand.sendCommand(deviceInfo);

		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.DeviceInfoService#lockScreen(java.lang.
	 * String)
	 */
	@Override
	public String lockScreen(String deviceId) {

		DeviceInfo deviceInfo = deviceInfoDao.loadById(Integer.valueOf(deviceId));

		if (deviceInfo == null) {
			return "未找到设备!";
		}

		SendDeviceCommandService sendCommand = new LockScreenCommandServiceImpl();
		sendCommand.sendCommand(deviceInfo);

		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.DeviceInfoService#updateImage(java.lang.
	 * String)
	 */
	@Override
	public String updateImage(String deviceId) {
		DeviceInfo deviceInfo = deviceInfoDao.loadById(Integer.valueOf(deviceId));

		if (deviceInfo == null) {
			return "未找到设备!";
		}

		SendDeviceCommandService sendCommand = new UpdateImageCommandServiceImpl();
		sendCommand.sendCommand(deviceInfo);

		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.DeviceInfoService#updateConfig(java.lang
	 * .String)
	 */
	@Override
	public String updateConfig(String deviceId) {
		DeviceInfo deviceInfo = deviceInfoDao.loadById(Integer.valueOf(deviceId));

		if (deviceInfo == null) {
			return "未找到设备!";
		}

		SendDeviceCommandService sendCommand = new UpdateConfigCommandServiceImpl();
		sendCommand.sendCommand(deviceInfo);

		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.DeviceInfoService#updateConfig(java.lang
	 * .String)
	 */
	@Override
	public String unLockScreen(String deviceId) {
		DeviceInfo deviceInfo = deviceInfoDao.loadById(Integer.valueOf(deviceId));

		if (deviceInfo == null) {
			return "未找到设备!";
		}

		SendDeviceCommandService sendCommand = new UnLockScreenCommandServiceImpl();
		sendCommand.sendCommand(deviceInfo);

		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.DeviceInfoService#batchShutdown(java.
	 * lang.String)
	 */
	@Override
	public String batchShutdown(String ids) {
		if (StringUtils.isEmpty(ids))
			return SysConstants.SUCCESS;

		String[] idStr = ids.split(",");

		for (int i = 0; i < idStr.length; i++) {

			DeviceInfo deviceInfo = deviceInfoDao.loadById(Integer.valueOf(idStr[i]));

			if (deviceInfo == null) {
				return "未找到设备!";
			}

			SendDeviceCommandService sendCommand = new ShutdownCommandServiceImpl();

			sendCommand.sendCommand(deviceInfo);

		}

		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.DeviceInfoService#batchRestart(java.lang
	 * .String)
	 */
	@Override
	public String batchRestart(String ids) {
		if (StringUtils.isEmpty(ids))
			return SysConstants.SUCCESS;

		String[] idStr = ids.split(",");

		for (int i = 0; i < idStr.length; i++) {

			DeviceInfo deviceInfo = deviceInfoDao.loadById(Integer.valueOf(idStr[i]));

			if (deviceInfo == null) {
				return "未找到设备!";
			}

			SendDeviceCommandService sendCommand = new RestartCommandServiceImpl();
			sendCommand.sendCommand(deviceInfo);

		}

		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.DeviceInfoService#batchSendMessage(java.
	 * lang.String)
	 */
	@Override
	public String batchSendMessage(String ids, String excuteTime, String sendText) {
		if (StringUtils.isEmpty(ids))
			return SysConstants.SUCCESS;

		String[] idStr = ids.split(",");

		for (int i = 0; i < idStr.length; i++) {

			DeviceInfo deviceInfo = deviceInfoDao.loadById(Integer.valueOf(idStr[i]));

			if (deviceInfo == null) {
				return "未找到设备!";
			}

			SendDeviceCommandService sendCommand = new SendMessageCommandServiceImpl();
			sendCommand.sendCommand(deviceInfo, excuteTime, sendText);

		}

		return SysConstants.SUCCESS;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.DeviceInfoService#sendDeviceConfig()
	 */
	@Override
	public String sendDeviceConfig() {

		List<DeviceInfo> deviceInfos = deviceInfoDao.listAIODevice(new HashMap<String, Object>(0));

		for (DeviceInfo bean : deviceInfos) {

			SendDeviceCommandService updateImageCommand = new UpdateImageCommandServiceImpl();

			updateImageCommand.sendCommand(bean);

		}
		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.DeviceInfoService#loadById(java.lang.
	 * Integer)
	 */
	@Override
	public DeviceInfo loadById(Integer deviceId) {
		return deviceInfoDao.loadById(deviceId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.DeviceInfoService#batchLockScreen(java.
	 * lang.String)
	 */
	@Override
	public String batchLockScreen(String ids) {
		if (StringUtils.isEmpty(ids))
			return SysConstants.SUCCESS;

		String[] idStr = ids.split(",");

		for (int i = 0; i < idStr.length; i++) {

			DeviceInfo deviceInfo = deviceInfoDao.loadById(Integer.valueOf(idStr[i]));

			if (deviceInfo == null) {
				return "未找到设备!";
			}

			SendDeviceCommandService sendCommand = new LockScreenCommandServiceImpl();
			sendCommand.sendCommand(deviceInfo);

		}

		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.DeviceInfoService#batchUnLockScreen(java
	 * .lang.String)
	 */
	@Override
	public String batchUnLockScreen(String ids) {
		if (StringUtils.isEmpty(ids))
			return SysConstants.SUCCESS;

		String[] idStr = ids.split(",");

		for (int i = 0; i < idStr.length; i++) {

			DeviceInfo deviceInfo = deviceInfoDao.loadById(Integer.valueOf(idStr[i]));

			if (deviceInfo == null) {
				return "未找到设备!";
			}

			SendDeviceCommandService sendCommand = new UnLockScreenCommandServiceImpl();
			sendCommand.sendCommand(deviceInfo);

		}

		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.DeviceInfoService#loadEbookpackageById(
	 * java.lang.Integer)
	 */
	@Override
	public DeviceInfo loadEbookpackageById(Integer deviceId) {
		return deviceInfoDao.loadEbookpackageById(deviceId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.DeviceInfoService#checkRule(java.lang.
	 * String)
	 */
	@Override
	public String checkRule(String schoolId) {
		Map<String, Object> param = new HashMap<String, Object>();

		param.put("schoolId", schoolId);

		List<DeviceInfo> deviceInfoList = deviceInfoDao.listEbookpackageDevice(param);

		for (DeviceInfo deviceInfo : deviceInfoList) {
			SendDeviceCommandService sendCommand = new CheckRuleServiceImpl();
			sendCommand.sendCommand(deviceInfo);
		}

		return SysConstants.SUCCESS;
	}

	@Override
	public int selectDeviceInfo() {
		return deviceInfoDao.selectDeviceInfo();
	}
}
