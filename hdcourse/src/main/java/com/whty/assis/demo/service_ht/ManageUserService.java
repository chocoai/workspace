package com.whty.assis.demo.service_ht;

import java.util.List;
import java.util.Map;

import com.whty.assis.demo.model.ManageUserInfo;
import com.whty.page.util.HandlerResult;

public interface ManageUserService {

	public ManageUserInfo queryManageUserInfoByAccount(String account);

	public void updateManageUser(ManageUserInfo mUser);

	public void addManageUser(ManageUserInfo mUser);

	public HandlerResult queryManageUser(Map paramap);

	public void updateManageUserStatus(Map paramap);

	public void deleteManageUser(Map paramap);

	public List<ManageUserInfo> queryallUsers(Map paramap);

	public void addManageUserList(List list);

}
