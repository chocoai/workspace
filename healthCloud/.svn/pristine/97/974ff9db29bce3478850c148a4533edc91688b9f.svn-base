/**
 * @Title:   SysDictService.java 
 * @Package: com.yhcrt.healthcloud.system.service  
 * @Description: 
 * @author: rpf
 * @date: 2017年5月26日 
 * @version V1.0 
 * Copyright © 2017 Wuhan YHCRT Technology Co.Ltd. All rights reserved.
 */
package com.yhcrt.healthcloud.system.service;

import java.util.List;

import com.yhcrt.healthcloud.system.entity.SysDictionary;

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

	/**
	 * 根据dict_en_name查询
	 * @param string
	 * @return
	 */
	List<SysDictionary> queryByDname(String dict_en_name);

}
