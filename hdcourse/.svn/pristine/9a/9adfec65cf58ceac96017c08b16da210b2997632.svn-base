
package com.whty.common.thread.widgetlog;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.whty.assis.api.service.ClientUserService;

/**
 * 操作日志入库处理
 */
@Component
public class ThreadWidgetLog {

	private static Logger log = Logger.getLogger(ThreadWidgetLog.class);

	// private static final int queueSize = 5000; // 入库队列大小
	private static boolean threadRun = true; // 线程是否运行

	@Autowired
	private ClientUserService clientUserService;

	private static ThreadWidgetLog info;

	@PostConstruct
	public void init() {
		info = this;
		info.clientUserService = this.clientUserService;
	}

	/**
	 * 队列
	 */
	// private static final BlockingQueue<Syslog> queue = new
	// LinkedBlockingQueue<Syslog>(queueSize);
	private static Queue<Map<String, Object>> queue = new ConcurrentLinkedQueue<Map<String, Object>>(); // 此队列按照
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
		ThreadWidgetLog.threadRun = threadRun;
	}

	/**
	 * 向队列中增加Syslog对象，基于ConcurrentLinkedQueue
	 * 
	 * @param syslog
	 */
	public static void add(Map<String, Object> syslog) {
		queue.offer(syslog); // 将指定元素插入此队列的尾部
	}

	/**
	 * 获取对象，基于ConcurrentLinkedQueue
	 * 
	 * @return
	 */
	public static Map<String, Object> getWidgetLog() {
		return queue.poll(); // 获取并移除此队列的头，如果此队列为空，则返回 null
	}

	/**
	 * 启动入库线程
	 */
	public static void startSaveDBThread() {
		try {
			for (int i = 0; i < 10; i++) {
				Thread insertDbThread = new Thread(new Runnable() {
					public void run() {
						while (threadRun) {
							try {
								// 取队列数据
								// Syslog sysLog = queue.take(); //
								// 基于LinkedBlockingQueue
								// Syslog sysLog = getWidgetLog();
								Map<String, Object> widgetLog = getWidgetLog();
								if (null == widgetLog) {
									Thread.sleep(200);
								} else {
									log.info("保存操作日志到数据库start......");
									info.clientUserService.saveWidgetInfoLog(widgetLog);
									// sysLog.save();// 日志入库
									log.info("保存操作日志到数据库end......");
								}

							} catch (Exception e) {
								log.error("保存操作日志到数据库异常：" + e.getMessage());
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
