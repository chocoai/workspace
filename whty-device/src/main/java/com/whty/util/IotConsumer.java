/**
 * 
 */
package com.whty.util;

import com.aliyun.openservices.ons.api.*;
import com.whty.entity.*;
import com.whty.mapper.*;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * iot上报消息消费者
 * 
 * @author zhangzheng
 * @date 2018年5月28日
 */

@Component
public class IotConsumer implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments args) {
		Properties properties = new Properties();
		// 您在控制台创建的 Consumer ID
		properties.put(PropertyKeyConst.ConsumerId, Constants.ConsumerId);
		// AccessKey 阿里云身份验证，在阿里云服务器管理控制台创建
		properties.put(PropertyKeyConst.AccessKey, "LTAI1mVSjKP4rKWi");
		// SecretKey 阿里云身份验证，在阿里云服务器管理控制台创建
		properties.put(PropertyKeyConst.SecretKey, "355MPmXG6NeRvbALZCXlR5E9X21eBV");
		// 设置 TCP 接入域名（此处以公共云生产环境为例）
		properties.put(PropertyKeyConst.ONSAddr,"http://onsaddr-internet.aliyun.com/rocketmq/nsaddr4client-internet");
		// 集群订阅方式 (默认)
		// properties.put(PropertyKeyConst.MessageModel, PropertyValueConst.CLUSTERING);
		// 广播订阅方式
		properties.put(PropertyKeyConst.MessageModel, PropertyValueConst.BROADCASTING);
		Consumer consumer = ONSFactory.createConsumer(properties);
		consumer.subscribe(Constants.TOPIC,"*", new MessageListenerImpl());

		consumer.start();
		System.out.println("Consumer Started");
	}


}
