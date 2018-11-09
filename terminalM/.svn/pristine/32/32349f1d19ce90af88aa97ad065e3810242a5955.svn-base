/**
 * 
 */
package com.whty.test;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Consumer;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.whty.assis.iot.IotConsumerMessage;

import net.sf.json.JSONObject;

/**
 * @author zhangzheng
 * @date 2018年4月7日
 */
public class ConsumerTest {

	public static Logger logger = LoggerFactory.getLogger(ConsumerTest.class);

	public static void main(String[] args) {
		Properties properties = new Properties();
		// 您在控制台创建的 Consumer ID
		properties.put(PropertyKeyConst.ConsumerId, "CID_IOT_MQ_GW");
		// AccessKey 阿里云身份验证，在阿里云服务器管理控制台创建
		properties.put(PropertyKeyConst.AccessKey, "LTAI1mVSjKP4rKWi");
		// SecretKey 阿里云身份验证，在阿里云服务器管理控制台创建
		properties.put(PropertyKeyConst.SecretKey, "355MPmXG6NeRvbALZCXlR5E9X21eBV");
		// 设置 TCP 接入域名（此处以公共云生产环境为例）
		properties.put(PropertyKeyConst.ONSAddr, "http://onsaddr-internet.aliyun.com/rocketmq/nsaddr4client-internet");
		// 集群订阅方式 (默认)
		// properties.put(PropertyKeyConst.MessageModel,
		// PropertyValueConst.CLUSTERING);
		// 广播订阅方式
		// properties.put(PropertyKeyConst.MessageModel,
		// PropertyValueConst.BROADCASTING);
		Consumer consumer = ONSFactory.createConsumer(properties);

		// 订阅另外一个 Topic
		consumer.subscribe("IOT_MQ_GW", "*", new MessageListener() { // 订阅全部 Tag
			public Action consume(Message message, ConsumeContext context) {
				System.out.println("Receive: " + message);

				String body = new String(message.getBody());
				System.out.println(body);

				JSONObject jsStr = JSONObject.fromObject(body);

				IotConsumerMessage iotConsumerMessage = (IotConsumerMessage) JSONObject.toBean(jsStr,
						IotConsumerMessage.class);

				System.out.println(iotConsumerMessage.getDeviceName());

				logger.info(message.toString());

				return Action.CommitMessage;
			}
		});

		consumer.start();
		System.out.println("Consumer Started");
	}
}
