/**
 * 
 */
package com.whty.assis.sysres.service;

import java.util.List;

import com.whty.assis.sysres.model.TaManageUserInfo;

/** 
 * @ClassName: SysRoleService 
 * @author: zjd
 * @date: 2018年6月4日 上午9:43:31  
 */
public interface SysUserService {
	
	int insert(TaManageUserInfo user);
	
	int delete(TaManageUserInfo user);
	
	int update(TaManageUserInfo user);
	
	List<TaManageUserInfo>  selectUsers();
	
	TaManageUserInfo selectByAccount(String account);
	
	TaManageUserInfo selectById(Integer id);

}
