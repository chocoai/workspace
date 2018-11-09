/**
 * 
 */
package com.whty.common.sys;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.whty.common.thread.AppPushThread;
import com.whty.common.thread.DeviceCommandLogThread;
import com.whty.common.thread.IotConsumerThread;

/**
 * 系统初始化
 * 
 * @author zhangzheng
 * @date 2018年5月14日
 */
@Service
public class SysInit {
	private Log logger = LogFactory.getLog(getClass());

	public void init() {
		logger.info("---------------------系统初始化-------------------------");

		logger.info("---------------------应用上报命令推送线程启动-------------------------");
		AppPushThread.startSaveDBThread();
		
		DeviceCommandLogThread.startSaveDBThread();
		logger.info("---------------------启动设备命令日志队列-------------------------");
		
		
		logger.info("---------------------iot消费者线程启动-------------------------");
		IotConsumerThread.startSaveDBThread();
		
		logger.info("---------------------系统初始完成-------------------------");
		
		
	}
	
	
}
