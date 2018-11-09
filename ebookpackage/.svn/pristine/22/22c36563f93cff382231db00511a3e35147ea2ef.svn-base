package com.whty.ebp.sys.dao;

import java.util.List;
import java.util.Map;

import com.whty.ebp.base.dao.IBaseDao;
import com.whty.ebp.sys.model.SysUser;

public interface SysUserDao extends IBaseDao<SysUser> {

	//根据帐号查询后台账户
	public SysUser loadByAccount(String account);
	
	public List<SysUser> querySysUser(Map paramap);
	
	public void addSysUser(SysUser manageUserInfo);
	
	public void updateSysUser(SysUser manageUserInfo);
	
	public void updateSysUserStatus(Map paramap);
	
	public void deleteSysUser(Map paramap);

}
