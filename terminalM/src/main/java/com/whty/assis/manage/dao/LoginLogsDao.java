package com.whty.assis.manage.dao;

import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.manage.model.LoginLogs;

public interface LoginLogsDao extends IBaseDao<LoginLogs> {
	/* 新增登录日志 */
	public void saveLoginLogs(LoginLogs loginLogs);
	// /*查询全部的登录日志*/
	// public List<LoginLogsVo> queryalllogs(Map<String, Object> map);
	// //查询活动期间用户登录次数
	// @SuppressWarnings("rawtypes")
	// public Integer loginCountByActivity(Map map);
}
