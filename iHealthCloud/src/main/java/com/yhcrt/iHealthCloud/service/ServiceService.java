package com.yhcrt.iHealthCloud.service;

import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.yhcrt.iHealthCloud.entity.Service;

/* @Description: 	
 * @version 1.0     2017年9月7日	
 * @author jimmy	
*/
public interface ServiceService {

	Integer add(Service service);

	Integer del(Integer cid);

	Integer upd(Service service);

	Service get(Integer cid);

	List<Service> getAll(HashMap<String, Object> params);

	/**
	 * 根据部分参数查询服务列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	String getServices(JSONObject jsonObject);

	/**
	 * 获取预约服务筛选条件Item接口
	 * 
	 * @param jsonObject
	 * @return
	 */
	String getServiceFilterItem(JSONObject jsonObject);

	/**
	 * 获取服务详情接口
	 * 
	 * @param jsonObject
	 * @return
	 */
	String getServiceDetail(JSONObject jsonObject);

	/**
	 * 获取服务详情补充接口
	 * 
	 * @param jsonObject
	 * @return
	 */
	String getServiceDetailItem(JSONObject jsonObject);

	/**
	 * 
	 * @Description: 根据机构服务供应商查询服务
	 * @param orgId
	 * @return List<Service>
	 */
	List<Service> selectByProviderId(Integer providerId);

	/**
	 * 
	 * @Description: 服务搜索
	 * @param searchText
	 * @return List<Service>
	 */
	List<Service> selectBySearchText(String searchText);

}