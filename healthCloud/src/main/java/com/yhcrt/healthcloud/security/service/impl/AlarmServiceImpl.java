/**
 * @Title:   AlarmServiceImpl.java 
 * @Package: com.yhcrt.healthcloud.security.service.impl  
 * @Description: 
 * @author: rpf
 * @date: 2017年12月22日 
 * @version V1.0 
 * Copyright © 2017 Wuhan YHCRT Technology Co.Ltd. All rights reserved.
 */
package com.yhcrt.healthcloud.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhcrt.healthcloud.security.entity.Alarm;
import com.yhcrt.healthcloud.security.mapper.AlarmMapper;
import com.yhcrt.healthcloud.security.service.AlarmService;

/**
 * @ClassName: AlarmServiceImpl
 * @Description:
 * @version V1.0 
 * @author rpf
 * @date: 2017年12月22日 
 */
@Service
public class AlarmServiceImpl implements AlarmService {

	@Autowired
	private AlarmMapper alarmMapper;

	@Override
	public List<Alarm> listAlarm(String memberName,String imei,String alarmTime,String isRead,String alarmType) {
		List<Alarm> alarms = alarmMapper.listAlarm(memberName, imei, alarmTime, isRead, alarmType);
		return alarms;
	}

	@Override
	public Integer countUnreadAlarm() {
		return alarmMapper.countUnreadAlarm();
	}

	@Override
	public Alarm getAlarmById(String alarmId) {
		return alarmMapper.getAlarmById(alarmId);
	}

	@Override
	public void updateAlarmStatus(String alarmId) {
		alarmMapper.updateAlarmStatus(alarmId);
	}
	
	
	
	
	
	

}
