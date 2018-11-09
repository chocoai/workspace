package com.whty.assis.manage.dao;

import java.util.List;
import java.util.Map;

import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.manage.model.Ta_user;

import net.sf.json.JSONObject;

public interface TaUserDao extends IBaseDao<Ta_user> {

	// 查询客户端登录用户信息
	@SuppressWarnings("rawtypes")
	public Ta_user getUserByParam(Map map);

	// 更新用户数据
	public void updateUser(Ta_user ta_user);

	// 新增客户端登录用户
	public void addUser(Ta_user ta_user);

	// 删除用户
	@SuppressWarnings("rawtypes")
	public void deleteUser(Map map);

	// 根据用户查询用户列表
	@SuppressWarnings("rawtypes")
	public List<Ta_user> queryUser(Map map);

	// 跟新用户状态信息
	public void updateUserStatus(Map<String, Object> map);

	@SuppressWarnings("rawtypes")
	public List<Ta_user> getUserList(Map map);

	public void powerOff(Map<String, Object> map);

	public void saveWidgetInfoLog(Map<String, Object> map);

	public void saveWidgetInfoLogBack(Map<String, Object> map);

	public void updateWidgetLog(Map<String, Object> map);

	public void saveTerminalUseLog(JSONObject terminalLog);

	public void saveInstallLog(Map<String, Object> map);

	public String visitCount(Map<String, Object> map);

	public void saveInstallMac(Map<String, Object> paramMap);

}
