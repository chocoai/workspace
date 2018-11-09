/**
 * 
 */
package com.whty.assis.sysres.service;

import java.util.List;

import com.whty.assis.sysres.model.SysRoleModular;

/** 
 * @ClassName: SysRoleModularService 
 * @author: zjd
 * @date: 2018年6月5日 下午2:03:58  
 */
public interface SysRoleModularService {

	List<SysRoleModular> selectByRoleId(Integer roleId);
	
	String insert(String roleId,String[] resIds);
}
