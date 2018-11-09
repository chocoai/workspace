package com.yhcrt.healthcloud.system.service;

import java.util.HashMap;
import java.util.List;

import com.yhcrt.healthcloud.system.entity.SysVersion;

/**
 * 版本service
 * @author hull
 *
 */
public interface SysVersionService {

	//根据条件查询所有的版本信息
	List<SysVersion> queryAll(HashMap<String, Object> params);

	//根据kid查询版本信息
	SysVersion queryById(String kid);

	//修改版本
	Integer update(SysVersion version);

	//新增版本 
	Integer insert(SysVersion version);

	//查询版本存放路径
	String queryUrl(String kid);

	//批量删除版本信息
	void batchDel(List<String> list);

}
