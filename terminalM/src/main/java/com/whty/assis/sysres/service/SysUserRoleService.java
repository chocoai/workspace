/**
 * 
 */
package com.whty.assis.sysres.service;

import com.whty.assis.sysres.model.SysUserRole;

/** 
 * @ClassName: SysUserRoleService 
 * @author: zjd
 * @date: 2018年6月7日 下午2:16:38  
 */
public interface SysUserRoleService {

	
	int insert(String roleId,String userId);
	int delet(String userId);
}
