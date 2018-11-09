/**
 * @Title:   AlarmMapper.java 
 * @Package: com.yhcrt.healthcloud.security.mapper  
 * @Description: 
 * @author: rpf
 * @date: 2017年12月22日 
 * @version V1.0 
 * Copyright © 2017 Wuhan YHCRT Technology Co.Ltd. All rights reserved.
 */
package com.yhcrt.healthcloud.security.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yhcrt.healthcloud.security.entity.Alarm;

/**
 * @ClassName: AlarmMapper
 * @Description:
 * @version V1.0
 * @author rpf
 * @date: 2017年12月22日
 */
public interface AlarmMapper {

	List<Alarm> listAlarm(@Param("memberName") String memberName, @Param("imei") String imei,
			@Param("alarmTime") String alarmTime, @Param("isRead") String isRead, @Param("alarmType") String alarmType);
	
	public Integer countUnreadAlarm();
	
	public Alarm getAlarmById(String alarmId);
	
	public void updateAlarmStatus(String alarmId);
	
	

}
