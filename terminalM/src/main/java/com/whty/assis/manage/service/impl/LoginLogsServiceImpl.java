package com.whty.assis.manage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.whty.assis.manage.dao.LoginLogsDao;
import com.whty.assis.manage.model.LoginLogs;
import com.whty.assis.manage.service.LoginLogsService;

public class LoginLogsServiceImpl implements LoginLogsService {

//	Logger log = Logger.getLogger(LoginLogsServiceImpl.class);
	@Autowired
	private LoginLogsDao loginlogsdao;

	@Override
	public void saveLoginLogs(LoginLogs loginLogs) {
		this.loginlogsdao.saveLoginLogs(loginLogs);
		try {
			// 一重礼 即日起，教育云平台教师用户活动期间首次登录教学助手应用即获得5元话费奖励；

			// 三重礼 活动期间教师用户每日登录教学助手应用均可获得一次抽奖机会
			// this.makeActivityMessage(loginLogs);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
