/**
 * @Title:   SysRoleService.java 
 * @Package: com.yhcrt.healthcloud.system.service  
 * @Description: 
 * @author: rpf
 * @date: 2017年5月26日 
 * @version V1.0 
 * Copyright © 2017 Wuhan YHCRT Technology Co.Ltd. All rights reserved.
 */
package com.yhcrt.healthcloud.system.service;

import java.util.List;

import com.yhcrt.healthcloud.system.entity.SysRole;

/**
 * @ClassName: SysRoleService
 * @Description:
 * @version V1.0 
 * @author rpf
 * @date: 2017年5月26日 
 */
public interface SysRoleService {
	
	/**
	 * 新增角色
	 * @param sysRole
	 * @return
	 */
	int insert(SysRole sysRole);
	
	/**
	 * 根据角色Id删除角色
	 * @param roleId
	 * @return
	 */
	int deleteByRoleId(Integer roleId);
	
	/**
	 * 更新角色
	 * @param sysRole
	 * @return
	 */
	int updateByRoleId(SysRole sysRole);
	
	/**
	 * 查询所有角色列表
	 * @return
	 */
	List<SysRole> listAllSysRole();
	
	/**
	 * 根据角色ID查询角色
	 * @param roleId
	 * @return
	 */
	SysRole getSysRoleByRoleId(Integer roleId);

	//查询其角色查询其能分配的角色
	List<SysRole> queryByList(List<Integer> list);

}
