package com.whty.assis.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.whty.assis.api.service.ClientUserService;
import com.whty.assis.demo.service.LoginHistoryService;

/**
 * 用户登录统计 每天晚上统计
 * 
 * @author zhangzheng
 *
 */
public class LoginCountJob {

	private static final Log log = LogFactory.getLog(LoginCountJob.class);

	@Autowired
	private LoginHistoryService loginHistoryService;

	@Autowired
	private ClientUserService clicentUserService;

	public void execute() {
		log.info("-------------- 开始执行  execute  用户登录统计------------------------");
		// runner();

		loginHistoryService.countTask();

		// List<LoginHistory> loginHistoryList =
		// loginHistoryService.getOrgUseCountByCurrentMonth();

		// System.out.println(loginHistoryList.size());

		// AreaMonthUsageCount bean = new AreaMonthUsageCount();
		//
		// bean.setOrgId("111");
		// bean.setMonth(1);
		// bean.setYear(18);
		//
		// bean.setOrgName("sadfsafsadf");
		// bean.setAreaCode("1121ccz");
		// bean.setUseCount(231);
		// bean.setRate(22);
		//
		// bean.setPreviousUseCount(44);
		// bean.setUpdateTime(new Date());
		//// loginHistoryService.saveAreaMonthUsageCount(bean);
		// loginHistoryService.updateAreaMonthUsageCount(bean);

		log.info("-------------- 执行  execute 用户登录统计 ------------------------");
		/*
		 * 每天晚上定时清理前一天扫码登录的无效数据
		 */
		// loginHistoryService.clearScanCodeData();
	}

};