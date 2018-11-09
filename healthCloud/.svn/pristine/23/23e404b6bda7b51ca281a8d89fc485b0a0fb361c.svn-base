/**
 * @Title:   SysResService.java 
 * @Package: com.yhcrt.healthcloud.system.service  
 * @Description: 
 * @author: rpf
 * @date: 2017年5月16日 
 * @version V1.0 
 * Copyright © 2017 Wuhan YHCRT Technology Co.Ltd. All rights reserved.
 */
package com.yhcrt.healthcloud.system.service;

import java.util.List;
import java.util.Set;

import com.yhcrt.healthcloud.system.entity.SysRes;

/**
 * @ClassName: SysResService
 * @Description:权限资源管理
 * @version V1.0 
 * @author rpf
 * @date: 2017年5月16日 
 */
public interface SysResService {
	
	/**
	 * 根据权限资源Id查询权限资源
	 * @param resId
	 * @return
	 */
	SysRes getSysResByResId(Integer resId);
	
	/**
	 * 查询权限资源根节点
	 * @return
	 */
	SysRes getSysResRootNode();
	
	/**
	 * 新增资源
	 * @param record
	 * @return
	 */
	int insert(SysRes record);
	
	/**
	 * 根据父级资源查询所有子级资源
	 * @param parentId
	 * @return
	 */
    List<SysRes> getChildResByParentId(String parentId);
    
    /**
	 * 查询子资源记录中最大rescode
	 * @param parentId
	 * @return
	 */
    String getLastResCodeByParentId(String parentId);
    
    /**
     * 根据资源ID删除权限资源
     * @param resId
     * @return
     */
    int deleteByPrimaryKey(Integer resId);

    /**
     * 根据权限资源ID更新权限资源
     * @param record
     * @return
     */
    int updateByPrimaryKey(SysRes record);
    
    /**
     * 查询所有的权限资源
     * @return
     */
    List<SysRes> listAllSysRes();
    
    Set<String> findPermissionsByUserId(String userId);

}
