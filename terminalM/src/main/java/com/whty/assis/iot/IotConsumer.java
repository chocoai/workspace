/**
 * 
 */
package com.whty.assis.iot;

import java.util.Properties;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Consumer;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.whty.assis.basicdata.dao.IotMessagesLogDao;
import com.whty.common.thread.IotConsumerThread;
import com.whty.common.util.SysConfig;

/**
 * iot上报消息消费者
 * 
 * @author zhangzheng
 * @date 2018年5月28日
 */

@Component
public class IotConsumer {

	@Autowired
	private IotMessagesLogDao iotMessagesLogDao;

	private static Logger logger = LoggerFactory.getLogger(IotConsumer.class);

	private static IotConsumer info;

	@PostConstruct
	public void init() {
		info = this;
		info.iotMessagesLogDao = this.iotMessagesLogDao;
	}

	public static void start() {
		Properties properties = new Properties();
		// 您在控制台创建的 Consumer ID
		properties.put(PropertyKeyConst.ConsumerId, SysConfig.getStrValue("ali_iotmq_consumer_id"));
		// AccessKey 阿里云身份验证，在阿里云服务器管理控制台创建
		properties.put(PropertyKeyConst.AccessKey, SysConfig.getStrValue("ali_accesskey"));
		// SecretKey 阿里云身份验证，在阿里云服务器管理控制台创建
		properties.put(PropertyKeyConst.SecretKey, SysConfig.getStrValue("ali_secretkey"));
		// 设置 TCP 接入域名（此处以公共云生产环境为例）
		properties.put(PropertyKeyConst.ONSAddr, SysConfig.getStrValue("ali_iotmq_address"));

		Consumer consumer = ONSFactory.createConsumer(properties);

		// 订阅另外一个 Topic
		consumer.subscribe("IOT_MQ_GW", "*", new MessageListener() { // 订阅全部 Tag
			public Action consume(Message message, ConsumeContext context) {
				String body = new String(message.getBody());
				logger.info("Receive: " + body);

				IotConsumerThread.add(message);

				return Action.CommitMessage;
			}
		});

		consumer.start();

		logger.info("IotConsumer Started");
	}

}
