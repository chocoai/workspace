package com.fxzhj.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling // 启用定时任务
public class SettleJob {
	
	Logger logger = LoggerFactory.getLogger(SettleJob.class);
	
	
	/**
	 * 定时功能：
	 */
	@Scheduled(cron = "00 00 00 ? * *")
	public void settleSchedule(){
		
	}

}
