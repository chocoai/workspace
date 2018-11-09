/**
 * 
 */
package com.whty.common.thread;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.whty.assis.basicdata.dao.DeviceCommandLogDao;
import com.whty.assis.basicdata.model.DeviceCommandLog;

/**
 * @author zhangzheng
 * @date 2018年6月18日
 */
@Component
public class DeviceCommandLogThread {
	private static Logger log = Logger.getLogger(DeviceCommandLogThread.class);

	// private static final int queueSize = 5000; // 入库队列大小
	private static boolean threadRun = true; // 线程是否运行

	@Autowired
	private DeviceCommandLogDao deviceCommandLogDao;

	private static DeviceCommandLogThread info;

	@PostConstruct
	public void init() {
		info = this;
		info.deviceCommandLogDao = this.deviceCommandLogDao;
	}

	/**
	 * 队列
	 */
	// private static final BlockingQueue<Syslog> queue = new
	// LinkedBlockingQueue<Syslog>(queueSize);
	private static Queue<DeviceCommandLog> queue = new ConcurrentLinkedQueue<DeviceCommandLog>(); // 此队列按照
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
		DeviceCommandLogThread.threadRun = threadRun;
	}

	/**
	 * 向队列中增加Syslog对象，基于ConcurrentLinkedQueue
	 * 
	 * @param syslog
	 */
	public static void add(DeviceCommandLog bean) {
		queue.offer(bean); // 将指定元素插入此队列的尾部
	}

	/**
	 * 获取对象，基于ConcurrentLinkedQueue
	 * 
	 * @return
	 */
	public static DeviceCommandLog getDeviceCommandLog() {
		return queue.poll(); // 获取并移除此队列的头，如果此队列为空，则返回 null
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
								DeviceCommandLog bean = getDeviceCommandLog();
								if (null == bean) {
									Thread.sleep(200);
								} else {
									info.deviceCommandLogDao.save(bean);
								}
							} catch (Exception e) {
								log.error("保存发送命令异常：" + e.getMessage());
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
