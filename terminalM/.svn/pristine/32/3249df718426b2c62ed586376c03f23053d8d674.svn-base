package com.whty.assis.manage.dao;

import java.util.List;
import java.util.Map;

import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.manage.model.ManageUserInfo;

public interface ManageUserDao extends IBaseDao<ManageUserInfo> {

	// 根据帐号查询后台账户
	public ManageUserInfo loadByAccount(String account);

	public List<ManageUserInfo> queryManageUser(Map<String,Object> paramap);

	public int addManageUser(ManageUserInfo manageUserInfo);

	public int updateManageUser(ManageUserInfo manageUserInfo);

	public int updateManageUserStatus(Map<String,Object> paramap);

	public int deleteManageUser(Map<String,Object> paramap);
	
	public List<ManageUserInfo> getUsers();

}
