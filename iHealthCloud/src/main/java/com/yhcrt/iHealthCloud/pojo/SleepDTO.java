/**
 * @Title:   SleepDTO.java 
 * @Package: com.yhcrt.iHealthCloud.pojo  
 * @Description: 
 * @author: rpf
 * @date: 2017年11月30日 
 * @version V1.0 
 * Copyright © 2017 Wuhan YHCRT Technology Co.Ltd. All rights reserved.
 */
package com.yhcrt.iHealthCloud.pojo;

/**
 * @ClassName: SleepDTO
 * @Description:
 * @version V1.0
 * @author rpf
 * @date: 2017年11月30日
 */
public class SleepDTO {

	private String sleepDuration;

	private String deepSleepDuration;

	private String sleepQuality;

	private String dataDate;

	private String uploadTime;

	public String getSleepDuration() {
		return sleepDuration;
	}

	public void setSleepDuration(String sleepDuration) {
		this.sleepDuration = sleepDuration;
	}

	public String getDeepSleepDuration() {
		return deepSleepDuration;
	}

	public void setDeepSleepDuration(String deepSleepDuration) {
		this.deepSleepDuration = deepSleepDuration;
	}

	public String getSleepQuality() {
		return sleepQuality;
	}

	public void setSleepQuality(String sleepQuality) {
		this.sleepQuality = sleepQuality;
	}

	public String getDataDate() {
		return dataDate;
	}

	public void setDataDate(String dataDate) {
		this.dataDate = dataDate;
	}

	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

	@Override
	public String toString() {
		return "SleepDTO [sleepDuration=" + sleepDuration + ", deepSleepDuration=" + deepSleepDuration
				+ ", sleepQuality=" + sleepQuality + ", dataDate=" + dataDate + ", uploadTime=" + uploadTime + "]";
	}

	/**
	 * 
	 */
	public SleepDTO() {
		
	}

}
