package com.yhcrt.iHealthCloud.service;
import com.alibaba.fastjson.JSONObject;
import com.yhcrt.iHealthCloud.entity.ServiceProvider;

/**
 * 
 * @ClassName: ServiceProviderService
 * @Description: 服务供应商
 * @version V1.0 
 * @author rpf
 * @date: 2018年3月20日
 */
public interface ServiceProviderService {
	
	ServiceProvider getProviderById(Integer id);
	
	/**
	 * 服务供应商详情
	 * @param jsonObject
	 * @return
	 */
	String getServiceProviderDetail(JSONObject jsonObject);
	
	/**
	 * 获取供应商列表
	 * @param jsonObject
	 * @return
	 */
	String getServiceProviders(JSONObject jsonObject);
	
	/**
	 * 获取当前地理位置附近的服务供应商
	 * @param jsonObject
	 * @return
	 */
	String getProvidersByGps(JSONObject jsonObject);
	
	/**
	 * 获取热门推荐服务供应商
	 * @param jsonObject
	 * @return
	 */
	String getRecommendProviders(JSONObject jsonObject);
	

}
