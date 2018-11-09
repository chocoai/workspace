/**
 * @Title:   HealthDataService.java 
 * @Package: com.yhcrt.iHealthCloud.service  
 * @Description: 
 * @author: rpf
 * @date: 2017年12月14日 
 * @version V1.0 
 * Copyright © 2017 Wuhan YHCRT Technology Co.Ltd. All rights reserved.
 */
package com.yhcrt.iHealthCloud.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName: HealthDataService
 * @Description:
 * @version V1.0 
 * @author rpf
 * @date: 2017年12月14日 
 */
public interface HealthDataService {
	
	/**
	 * 获取用户近一周的健康数据
	 * @param pdataObj
	 * @return
	 */
	public String getHealthDataForWeek(JSONObject pdataObj);
	
	/**
	 * 孝康管家-健康管理首页数据(步数、心率、血压)
	 * @param jsonObj
	 * @return
	 */
	public String getHealthIndexData(JSONObject jsonObj);
	
	
	public String uploadBloodOxygen(JSONObject jsonObject);
	
	
	public String uploadTemperature(JSONObject jsonObject);

}
