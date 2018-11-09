/**
 * @Title:   HdStepDTO.java 
 * @Package: com.yhcrt.iHealthCloud.pojo  
 * @Description: 
 * @author: rpf
 * @date: 2017年11月24日 
 * @version V1.0 
 * Copyright © 2017 Wuhan YHCRT Technology Co.Ltd. All rights reserved.
 */
package com.yhcrt.iHealthCloud.pojo;

/**
 * @ClassName: HdStepDTO
 * @Description:
 * @version V1.0
 * @author rpf
 * @date: 2017年11月24日
 */
public class HdStepDTO {

	private String dataDate;

	private String stepCount;

	public String getDataDate() {
		return dataDate;
	}

	public void setDataDate(String dataDate) {
		this.dataDate = dataDate;
	}

	public String getStepCount() {
		return stepCount;
	}

	public void setStepCount(String stepCount) {
		this.stepCount = stepCount;
	}

	@Override
	public String toString() {
		return "HdStepDTO [dataDate=" + dataDate + ", stepCount=" + stepCount + "]";
	}

	/**
	 * 
	 */
	public HdStepDTO() {
		
	}

}
