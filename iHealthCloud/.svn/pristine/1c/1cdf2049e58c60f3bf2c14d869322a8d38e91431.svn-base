/**
 * @Title:   SysDictService.java 
 * @Package: com.yhcrt.healthcloud.system.service  
 * @Description: 
 * @author: rpf
 * @date: 2017年5月26日 
 * @version V1.0 
 * Copyright © 2017 Wuhan YHCRT Technology Co.Ltd. All rights reserved.
 */
package com.yhcrt.iHealthCloud.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.yhcrt.iHealthCloud.entity.Area;
import com.yhcrt.iHealthCloud.entity.SysDictionary;



/**
 * @ClassName: SysDictService
 * @Description:
 * @version V1.0 
 * @author rpf
 * @date: 2017年5月26日 
 */
public interface SysDictService {
	
	/**
	 * 新增数据字典
	 * @param sysDictionary
	 * @return
	 */
	int insert(SysDictionary sysDictionary);
	
	/**
	 * 删除数据字典
	 * @param dictId
	 * @return
	 */
	int deleteByDictId(Integer dictId);
	
	/**
	 * 更新数据字典
	 * @param sysDictionary
	 * @return
	 */
	int updateByDictId(SysDictionary sysDictionary);
	
	/**
	 * 查询所有数据字典
	 * @return
	 */
	List<SysDictionary> listAllSysDict();
	
	/**
	 * 查询某一个数据字典
	 * @param dictId
	 * @return
	 */
	SysDictionary getSysDictByDictId(Integer dictId);
	
	
	String getItems(JSONObject pdataObj);
	
	String getItemsMore(JSONObject pdataObj);
	
	String selectSeviceType(JSONObject pdataObj);
	
	String selectDictByParam(JSONObject pdataObj);
	
	String geHotServiceType(JSONObject pdataObj);
	
	String getServiceDetail(JSONObject pdataObj);
	
	String getServiceType(JSONObject pdataObj);

	//获取所有"org_type"的服务类型
	String getAllItems(JSONObject pdataObj);
	
	Area selectAreaAndName(Integer parentId,String areaName);
	
	SysDictionary selectDictEnNameAndValue(String dictEnName,String dictValue);
	
	List<SysDictionary> selectDictEnName(String dictEnName);
	
	List<Area> selectArea(Integer parentId);
	
	List<SysDictionary> getSysDictParentId(String parentId) ;

}
