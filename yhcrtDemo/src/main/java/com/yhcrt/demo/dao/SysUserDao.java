package com.yhcrt.demo.dao;

import java.util.List;

import com.yhcrt.demo.model.SysUser;


/**
 * @author fengkun
 * @email 231788364@qq.com
 */
public interface SysUserDao  {
	
	public SysUser getByUserName(String userName);
	
	public void update(SysUser sysUser);
	
	public void updateBySelected(SysUser sysUser);
	
	public SysUser getByUserNameAndPassword(String userName,String password);
	
	public void insert(SysUser sysUser);
	
	public List<SysUser> doPaginationQuery(SysUser sysUser);
	
	public void deleteByPK(Long[] ids);
}
