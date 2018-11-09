/**
 * 
 */
package com.whty.assis.basicdata.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.basicdata.dao.DeviceInfoDao;
import com.whty.assis.basicdata.dao.TerminalDeviceTypeDao;
import com.whty.assis.basicdata.model.DeviceInfo;
import com.whty.assis.basicdata.model.TerminalDeviceType;
import com.whty.assis.basicdata.service.ConsumerService;
import com.whty.assis.basicdata.service.DeviceMessageHandler;
import com.whty.assis.iot.IotConsumerMessage;

/**
 * @author zhangzheng
 * @date 2018年5月28日
 */
@Service
public class ConsumerServiceImpl implements ConsumerService {

	@Autowired
	private TerminalDeviceTypeDao terminalDeviceTypeDao;

	@Autowired
	private DeviceInfoDao deviceInfoDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.ConsumerService#handle(com.whty.assis.
	 * iot.IotConsumer)
	 */
	@Override
	public void handle(IotConsumerMessage iotConsumerMessage) {
		Map<String, Object> terminalDeviceTypeParam = new HashMap<String, Object>(1);
		terminalDeviceTypeParam.put("aliProductKey", iotConsumerMessage.getProductKey());

		List<TerminalDeviceType> ss = terminalDeviceTypeDao.listByCondition(terminalDeviceTypeParam);

		if (ss == null || ss.size() > 0)
			return;

		DeviceInfo deviceInfo = deviceInfoDao.loadByIdAliIotId(iotConsumerMessage.getIotId());

		if (deviceInfo == null)
			return;

		TerminalDeviceType terminalDeviceType = ss.get(0);

		switch (terminalDeviceType.getId()) {
		case 1:
			DeviceMessageHandler deviceMessageHandler = new AioDeviceMessageHandler();
			deviceMessageHandler.handler(iotConsumerMessage);
			break;
		case 2:

			break;

		default:
			break;

		}

	}
}
