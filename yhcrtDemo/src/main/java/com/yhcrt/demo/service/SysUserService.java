package com.yhcrt.demo.service;

import java.util.List;

import com.yhcrt.demo.model.Authority;
import com.yhcrt.demo.model.SysUser;
import com.yhcrt.demo.util.QueryResult;



/**
 * @author fengkun
 * @email 231788364@qq.com
 */
public interface SysUserService {

	List<SysUser> getSysUserList(List<SysUser> resultList);
	
	SysUser getByUserName(String userName);
	
	void update(SysUser sysUser);
	
	void updateBySelected(SysUser sysUser);
	
	SysUser getByUserNameAndPassword(String userName,String password);
	
	void insert(SysUser sysUser);
	
	QueryResult<SysUser> doPaginationQuery(SysUser sysUser);
	
	boolean deleteByPK(Long[] ids);


}
