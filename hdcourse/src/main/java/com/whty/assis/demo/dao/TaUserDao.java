package com.whty.assis.demo.dao;

import java.util.List;
import java.util.Map;

import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.demo.model.Ta_user;
import com.whty.assis.demo.model.WidgetLog;

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

	public List<Ta_user> getUserList2(Map map);

	public void powerOff(Map map);

	public void saveWidgetInfoLog(Map map);

	public void saveWidgetInfoLogBack(Map map);

	public void updateWidgetLog(Map map);

	public List<WidgetLog> getWidgetLog(Map map);

	public List<WidgetLog> getWidgetLog2(Map map);

	public List<WidgetLog> getWidgetLogNotLoginTransfer(Map map);

	public void saveTerminalUseLog(JSONObject terminalLog);

	public void saveInstallLog(Map map);

	public String visitCount(Map map);

	public List<WidgetLog> getOp();

	public void saveInstallMac(Map<String, Object> paramMap);

	public List<Map<String, Object>> qqbb(Map<String, Object> paramMap);

	public void saveJfLog(Map<String, Object> param);

	public List<WidgetLog> getWidgetLog3(Map<String, Object> param);

	public List<Map<String, Object>> getUserLoginLogByTime(Map<String, Object> param);

	public void saveUserLoginLog(Map<String, Object> param);

}
