package com.whty.assis.demo.dao;

import java.util.List;
import java.util.Map;

import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.demo.model.ManageUserInfo;

public interface ManageUserDao extends IBaseDao<ManageUserInfo> {

	// 根据帐号查询后台账户
	public ManageUserInfo loadByAccount(String account);

	public List<ManageUserInfo> queryManageUser(Map paramap);

	public void addManageUser(ManageUserInfo manageUserInfo);

	public void updateManageUser(ManageUserInfo manageUserInfo);

	public void updateManageUserStatus(Map paramap);

	public void deleteManageUser(Map paramap);

}
