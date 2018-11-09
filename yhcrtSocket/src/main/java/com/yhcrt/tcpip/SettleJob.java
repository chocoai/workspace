package com.yhcrt.tcpip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.yhcrt.tcpip.socket.WatchServer;

@Configuration
@EnableScheduling // 启用定时任务
public class SettleJob {
	
	Logger logger = LoggerFactory.getLogger(SettleJob.class);
	private WatchServer server = null;
	
	/**
	 * 定时功能：
	 */
	@Scheduled(cron = "0/5 * * * * ?")
	public void settleSchedule(){
		logger.info("----------- serverSocket Start ------------");
		if(server == null){
			server = new WatchServer();
		}
		if(server.getSs()==null){
			server.start();
		}
	}

}
