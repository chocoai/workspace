/**
 * @Title:   AlarmService.java 
 * @Package: com.yhcrt.healthcloud.security.service  
 * @Description: 
 * @author: rpf
 * @date: 2017年12月22日 
 * @version V1.0 
 * Copyright © 2017 Wuhan YHCRT Technology Co.Ltd. All rights reserved.
 */
package com.yhcrt.healthcloud.security.service;

import java.util.List;

import com.yhcrt.healthcloud.security.entity.Alarm;

/**
 * @ClassName: AlarmService
 * @Description:
 * @version V1.0
 * @author rpf
 * @date: 2017年12月22日
 */
public interface AlarmService {
	/**
	 * 获取告警列表
	 */
	public List<Alarm> listAlarm(String memberName,String imei,String alarmTime,String isRead,String alarmType);

	/**
	 * 统计未读告警数量
	 */
	public Integer countUnreadAlarm();
	
	public Alarm getAlarmById(String alarmId);
	
	public void updateAlarmStatus(String alarmId);

}
