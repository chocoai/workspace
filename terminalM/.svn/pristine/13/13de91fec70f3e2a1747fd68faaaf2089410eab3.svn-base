/**
 * 
 */
package com.whty.common.thread;

import java.util.Date;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aliyun.openservices.ons.api.Message;
import com.whty.assis.basicdata.dao.IotMessagesLogDao;
import com.whty.assis.basicdata.model.IotMessagesLog;
import com.whty.assis.basicdata.service.ConsumerService;
import com.whty.assis.iot.IotConsumerMessage;

import net.sf.json.JSONObject;

/**
 * @author zhangzheng
 * @date 2018年5月28日
 */
@Component
public class IotConsumerThread {
	private static Logger log = Logger.getLogger(IotConsumerThread.class);

	// private static final int queueSize = 5000; // 入库队列大小
	private static boolean threadRun = true; // 线程是否运行

	@Autowired
	private IotMessagesLogDao iotMessagesLogDao;

	@Autowired
	private ConsumerService consumerService;

	private static IotConsumerThread info;

	@PostConstruct
	public void init() {
		info = this;
		info.consumerService = this.consumerService;
		info.iotMessagesLogDao = this.iotMessagesLogDao;
	}

	/**
	 * 队列
	 */
	// private static final BlockingQueue<Syslog> queue = new
	// LinkedBlockingQueue<Syslog>(queueSize);
	private static Queue<Message> queue = new ConcurrentLinkedQueue<Message>(); // 此队列按照
	// FIFO（先进先出）原则对元素进行排序

	/**
	 * 向队列中增加Syslog对象，基于LinkedBlockingQueue
	 * 
	 * @param sysLog
	 *            public static void add(Syslog sysLog){ try { log.info(
	 *            "put操作日志到缓存queue start......"); queue.put(sysLog); log.info(
	 *            "put操作日志到缓存queue end......"); } catch (InterruptedException e)
	 *            { log.error("put操作日志到缓存queue异常"); throw new RuntimeException(
	 *            "ThreadSysLog -> add Exception"); } }
	 */
	public static void setThreadRun(boolean threadRun) {
		IotConsumerThread.threadRun = threadRun;
	}

	/**
	 * 向队列中增加Syslog对象，基于ConcurrentLinkedQueue
	 * 
	 * @param syslog
	 */
	public static void add(Message bean) {
		queue.offer(bean); // 将指定元素插入此队列的尾部
	}

	/**
	 * 获取对象，基于ConcurrentLinkedQueue
	 * 
	 * @return
	 */
	public static Message getMessage() {
		// synchronized(queue) {
		// if(!queue.isEmpty()){
		return queue.poll(); // 获取并移除此队列的头，如果此队列为空，则返回 null
		// }
		// return null;
		// }
	}

	/**
	 * 启动入库线程
	 */
	public static void startSaveDBThread() {
		try {
			for (int i = 0; i < 5; i++) {
				Thread insertDbThread = new Thread(new Runnable() {
					public void run() {
						while (threadRun) {
							try {
								Message message = getMessage();
								if (null == message) {
									Thread.sleep(200);
								} else {
									log.info("处理iot消费者消息start......");
									String body = new String(message.getBody());

									IotMessagesLog iotMessagesLog = new IotMessagesLog();
									iotMessagesLog.setBody(body);
									iotMessagesLog.setBornHost(message.getBornHost());
									iotMessagesLog.setBornTimestamp(new Date(message.getBornTimestamp()));
									iotMessagesLog.setCreateTime(new Date());
									iotMessagesLog.setUpdateTime(new Date());
									iotMessagesLog.setKey(message.getKey());
									iotMessagesLog.setMsgId(message.getMsgID());
									iotMessagesLog.setTag(message.getTag());
									iotMessagesLog.setTopic(message.getTopic());

									info.iotMessagesLogDao.save(iotMessagesLog);

									JSONObject jsStr = JSONObject.fromObject(body);

									IotConsumerMessage iotMessage = (IotConsumerMessage) JSONObject.toBean(jsStr,
											IotConsumerMessage.class);

									info.consumerService.handle(iotMessage);

									log.info("处理iot消费者消息end......");
								}

							} catch (Exception e) {
								log.error("处理iot消费者消息异常：" + e.getMessage());
								e.printStackTrace();
							}
						}
					}
				});

				insertDbThread.setName("little-ant-Thread-SysLog-insertDB-" + (i + 1));
				insertDbThread.start();
			}
		} catch (Exception e) {
			throw new RuntimeException("ThreadSysLog new Thread Exception");
		}
	}
}
