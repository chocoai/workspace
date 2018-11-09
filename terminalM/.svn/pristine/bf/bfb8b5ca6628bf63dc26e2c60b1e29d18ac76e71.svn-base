package com.whty.common.aaa;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.whty.assis.manage.dao.SysErrorLogDao;
import com.whty.assis.manage.model.SysErrorLog;


/*
 * 错误操作日志入库线程
 */
@Component
public class ThreadSysErrorLog {
	private static Logger log = Logger.getLogger(ThreadSysErrorLog.class);

	// private static final int queueSize = 5000; // 入库队列大小
	private static boolean threadRun = true; // 线程是否运行

	@Autowired
	private SysErrorLogDao sysErrorLogDao;

	private static ThreadSysErrorLog threadSysErrorLog;

	@PostConstruct
	public void init()
	{
		threadSysErrorLog = this;
		threadSysErrorLog.sysErrorLogDao = this.sysErrorLogDao;
	}

	/**
	 * 队列
	 */
	// private static final BlockingQueue<Syslog> queue = new
	// LinkedBlockingQueue<Syslog>(queueSize);
	private static Queue<SysErrorLog> queue = new ConcurrentLinkedQueue<SysErrorLog>(); // 此队列按照
																										// FIFO（先进先出）原则对元素进行排序

	/**
	 * 向队列中增加对象，基于LinkedBlockingQueue
	 * 
	 * @param sysLog
	 *            public static void add(Syslog sysLog){ try { log.info(
	 *            "put操作日志到缓存queue start......"); queue.put(sysLog); log.info(
	 *            "put操作日志到缓存queue end......"); } catch (InterruptedException e)
	 *            { log.error("put操作日志到缓存queue异常"); throw new RuntimeException(
	 *            "ThreadSysLog -> add Exception"); } }
	 */

	public static void setThreadRun(boolean threadRun) {
//		ThreadWidgetLog.threadRun = threadRun;
	}

	/**
	 * 向队列中增加Syslog对象，基于ConcurrentLinkedQueue
	 * 
	 * @param syslog
	 */
	public static void add(SysErrorLog sysErrorlog) {
		queue.offer(sysErrorlog); // 将指定元素插入此队列的尾部
	}

	/**
	 * 获取对象，基于ConcurrentLinkedQueue
	 * 
	 * @return
	 */
	public static SysErrorLog getSysErrorLog() {
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
								SysErrorLog sysErrorLog = getSysErrorLog();
								if (null == sysErrorLog) {
									Thread.sleep(200);
								} else {
									log.info("保存错误日志到数据库start......");
									threadSysErrorLog.sysErrorLogDao.save(sysErrorLog);
									log.info("保存错误日志到数据库end......");
								}
							} catch (Exception e) {
								log.error("保存操作日志到数据库异常：" + e.getMessage());
								e.printStackTrace();
							}
						}
					}
				});
				insertDbThread.setName("little-ant-Thread-SysError-insertDB-" + (i + 1));
				insertDbThread.start();
			}
		} catch (Exception e) {
			throw new RuntimeException("ThreadSysError new Thread Exception");
		}
	}
}
