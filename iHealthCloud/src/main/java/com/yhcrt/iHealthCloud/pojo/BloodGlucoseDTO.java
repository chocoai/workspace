/**
 * @Title:   BloodGlucoseDTO.java 
 * @Package: com.yhcrt.iHealthCloud.pojo  
 * @Description: 
 * @author: rpf
 * @date: 2017年11月30日 
 * @version V1.0 
 * Copyright © 2017 Wuhan YHCRT Technology Co.Ltd. All rights reserved.
 */
package com.yhcrt.iHealthCloud.pojo;

/**
 * @ClassName: BloodGlucoseDTO
 * @Description:
 * @version V1.0
 * @author rpf
 * @date: 2017年11月30日
 */
public class BloodGlucoseDTO {

	private String bgType;

	private String bgValue;

	private String dataSource;

	private String dataDate;

	private String uploadTime;

	public String getBgType() {
		return bgType;
	}

	public void setBgType(String bgType) {
		this.bgType = bgType;
	}

	public String getBgValue() {
		return bgValue;
	}

	public void setBgValue(String bgValue) {
		this.bgValue = bgValue;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
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
		return "BloodGlucoseDTO [bgType=" + bgType + ", bgValue=" + bgValue + ", dataSource=" + dataSource
				+ ", dataDate=" + dataDate + ", uploadTime=" + uploadTime + "]";
	}

	/**
	 * 
	 */
	public BloodGlucoseDTO() {

	}

}
