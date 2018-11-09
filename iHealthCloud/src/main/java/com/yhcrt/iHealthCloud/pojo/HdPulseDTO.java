/**
 * @Title:   HdPulseDTO.java 
 * @Package: com.yhcrt.iHealthCloud.pojo  
 * @Description: 
 * @author: rpf
 * @date: 2017年11月24日 
 * @version V1.0 
 * Copyright © 2017 Wuhan YHCRT Technology Co.Ltd. All rights reserved.
 */
package com.yhcrt.iHealthCloud.pojo;

/**
 * @ClassName: HdPulseDTO
 * @Description:
 * @version V1.0
 * @author rpf
 * @date: 2017年11月24日
 */
public class HdPulseDTO {

	private String dataDate;

	private Integer pulse;

	public String getDataDate() {
		return dataDate;
	}

	public void setDataDate(String dataDate) {
		this.dataDate = dataDate;
	}

	public Integer getPulse() {
		return pulse;
	}

	public void setPulse(Integer pulse) {
		this.pulse = pulse;
	}

	@Override
	public String toString() {
		return "HdPulseDTO [dataDate=" + dataDate + ", pulse=" + pulse + "]";
	}

	/**
	 * 
	 */
	public HdPulseDTO() {

	}

}
