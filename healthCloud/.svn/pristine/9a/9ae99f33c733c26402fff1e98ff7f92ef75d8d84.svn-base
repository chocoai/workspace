/**
 * @Title:   Alarm.java 
 * @Package: com.yhcrt.healthcloud.security.entity  
 * @Description: 
 * @author: rpf
 * @date: 2017年12月21日 
 * @version V1.0 
 * Copyright © 2017 Wuhan YHCRT Technology Co.Ltd. All rights reserved.
 */
package com.yhcrt.healthcloud.security.entity;

import com.yhcrt.healthcloud.util.DictUtil;

/**
 * @ClassName: Alarm
 * @Description:
 * @version V1.0
 * @author rpf
 * @date: 2017年12月21日
 */
public class Alarm {

	private Integer alarmId;

	private Integer memberId;

	private String realName;

	private String alarmType;

	private String alarmContent;

	private Integer isRead;

	private Integer status;

	private String alarmTime;

	private String imei;

	private String phoneNum;

	public Integer getAlarmId() {
		return alarmId;
	}

	public void setAlarmId(Integer alarmId) {
		this.alarmId = alarmId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getAlarmType() {
		return alarmType;
	}
	
	public String getAlarmTypeView() {
    	return DictUtil.viewByCode(DictUtil.ALARM_TYPE, getAlarmType());
	}

	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}

	public String getAlarmContent() {
		return alarmContent;
	}

	public void setAlarmContent(String alarmContent) {
		this.alarmContent = alarmContent;
	}

	public Integer getIsRead() {
		return isRead;
	}
	
	public String getIsReadView() {
		return DictUtil.viewByCode(DictUtil.IS_READ, getIsRead().toString());
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAlarmTime() {
		return alarmTime;
	}

	public void setAlarmTime(String alarmTime) {
		this.alarmTime = alarmTime;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Alarm() {

	}

	@Override
	public String toString() {
		return "Alarm [alarmId=" + alarmId + ", memberId=" + memberId + ", realName=" + realName + ", alarmType="
				+ alarmType + ", alarmContent=" + alarmContent + ", isRead=" + isRead + ", status=" + status
				+ ", alarmTime=" + alarmTime + ", imei=" + imei + ", phoneNum=" + phoneNum + "]";
	}

}
