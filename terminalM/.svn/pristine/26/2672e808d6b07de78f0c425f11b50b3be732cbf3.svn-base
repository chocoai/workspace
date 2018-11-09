package com.whty.assis.demo.service;

import java.util.Map;

import com.whty.assis.manage.model.Ta_user;
import com.whty.page.util.HandlerResult;

public interface TaUserService {

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
	public HandlerResult queryUser(Map map);

	// 跟新用户状态信息
	public void updateUserStatus(Map<String, Object> map);

	// 查询前一天所有登录的用户
	@SuppressWarnings("rawtypes")
	public HandlerResult getUserList(Map map);
}
