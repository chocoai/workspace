package com.yhcrt.timer;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.ibatis.logging.Log;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yhcrt.entity.system.SysConfig;
import com.yhcrt.service.stsyem.SysConfigService;
import com.yhcrt.utils.SpringUtil;


/**
 * 定时任务
 *
 */
@Component
public class ToTimer{
	
	@Resource
	SysConfigService sysConfigService;
	/**
	 * 
	 * @Title: run
	 * @Description: 每半分钟触发任务(测试)
	 * @return: void
	 */
	@Scheduled(cron = "30 * * * * ? ")
	public void run(){
		/**
		 * 备份数据库
		 */
		try {
			sysConfigService.backupDatebase();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("------------备份失败");
		}
		System.out.println(new Date().getTime());
	}

	
	
	
	
	
	
}
