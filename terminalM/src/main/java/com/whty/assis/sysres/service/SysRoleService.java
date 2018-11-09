/**
 * 
 */
package com.whty.assis.sysres.service;

import com.whty.assis.sysres.model.SysRole;

import java.util.List;

/** 
 * @ClassName: SysRoleService 
 * @author: zjd
 * @date: 2018年6月4日 上午9:43:31  
 */
public interface SysRoleService {

	int insert(SysRole sysRole);
	
	int delete(Integer id);
	
	int update(SysRole sysRole);
	
	List<SysRole> selectRoles();
}
