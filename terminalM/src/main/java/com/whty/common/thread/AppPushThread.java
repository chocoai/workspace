/**
 * 
 */
package com.whty.common.thread;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aliyun.iot.util.LogUtil;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.iot.model.v20180120.InvokeThingServiceRequest;
import com.aliyuncs.iot.model.v20180120.InvokeThingServiceResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.whty.assis.basicdata.dao.DeviceUserDao;
import com.whty.assis.basicdata.model.Command;
import com.whty.assis.basicdata.model.DeviceUser;
import com.whty.assis.mall.dao.AppPushDao;
import com.whty.assis.mall.dao.AppPushLogDao;
import com.whty.assis.mall.model.AppPush;
import com.whty.assis.mall.model.AppPushLog;
import com.whty.assis.mall.service.AppPushService;
import com.whty.common.util.HMACSHA1;
import com.whty.common.util.SysConfig;
import com.whty.common.util.TimeUtils;

/**
 * @author zhangzheng
 * @date 2018年5月14日
 */
@Component
public class AppPushThread {
	private static Logger log = Logger.getLogger(AppPushThread.class);

	// private static final int queueSize = 5000; // 入库队列大小
	private static boolean threadRun = true; // 线程是否运行

	@Autowired
	private AppPushService appPushService;

	@Autowired
	private DeviceUserDao deviceUserDao;

	@Autowired
	private AppPushLogDao appPushLogDao;

	@Autowired
	private AppPushDao appPushDao;

	private static AppPushThread info;

	@PostConstruct
	public void init() {
		info = this;
		info.appPushService = this.appPushService;
		info.deviceUserDao = this.deviceUserDao;
		info.appPushLogDao = this.appPushLogDao;
		info.appPushDao = this.appPushDao;
	}

	/**
	 * 队列
	 */
	// private static final BlockingQueue<Syslog> queue = new
	// LinkedBlockingQueue<Syslog>(queueSize);
	private static Queue<AppPush> queue = new ConcurrentLinkedQueue<AppPush>(); // 此队列按照
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
		AppPushThread.threadRun = threadRun;
	}

	/**
	 * 向队列中增加Syslog对象，基于ConcurrentLinkedQueue
	 * 
	 * @param syslog
	 */
	public static void add(AppPush bean) {
		queue.offer(bean); // 将指定元素插入此队列的尾部
	}

	/**
	 * 获取对象，基于ConcurrentLinkedQueue
	 * 
	 * @return
	 */
	public static AppPush getAppPush() {
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

								AppPush bean = getAppPush();
								if (null == bean) {
									Thread.sleep(200);
								} else {
									log.info("发送更新应用列表命令start......");

									Integer classId = bean.getClassId();
									Integer schoolId = bean.getSchoolId();

									Map<String, Object> param = new HashMap<String, Object>(2);
									param.put("schoolId", schoolId);
									param.put("classId", classId);

									List<DeviceUser> appUserList = info.deviceUserDao.listByCondition(param);

									
									int sendNum = 0;
									for (DeviceUser deviceUser : appUserList) {
										String regionId = SysConfig.getStrValue("iot.regionId");
										String accessKeyID = SysConfig.getStrValue("user.accessKeyID");
										String accessKeySecret = SysConfig.getStrValue("user.accessKeySecret");
										String domain = SysConfig.getStrValue("iot.domain");
										
										IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyID,
												accessKeySecret);
										DefaultProfile.addEndpoint(regionId, regionId, deviceUser.getAliProductKey(),
												domain);
										DefaultAcsClient client = new DefaultAcsClient(profile);
										InvokeThingServiceRequest invokeThingServiceRequest = new InvokeThingServiceRequest();

										invokeThingServiceRequest.setProductKey(deviceUser.getAliProductKey());
										invokeThingServiceRequest.setIotId(deviceUser.getAliIotId());
										invokeThingServiceRequest.setDeviceName(deviceUser.getAliDeviceName());
										invokeThingServiceRequest.setIdentifier("commandService");

										Random random = new Random(new Date().getTime());
										int keyIndex = random.nextInt(5) % (5 - 1 + 1) + 1;

										String timeStamp = TimeUtils.date2String(new Date(), TimeUtils.DAY_FORMAT_2);
										String mac = HMACSHA1
												.bytesToHexString(HMACSHA1.HmacSHA1Encrypt(timeStamp, keyIndex));
										String action = SysConfig.getStrValue("mqtt.server.command.update_app_list");
										String type = SysConfig.getStrValue("mqtt.server.command.type");
										String version = SysConfig.getStrValue("mqtt.server.command.version");
										String para = "[]";
										Command command = new Command(action, type, null, version, keyIndex + timeStamp,null,
												null, mac, null, para);
										String str = null;
										str = String.format("{%s:\"%s\"}", "commandService", command.toString());

										invokeThingServiceRequest.setArgs(str);

										InvokeThingServiceResponse invokeThingServiceResponse = client
												.getAcsResponse(invokeThingServiceRequest);

										if (invokeThingServiceResponse != null
												&& invokeThingServiceResponse.getSuccess() != false) {
											sendNum = sendNum + 1;
											LogUtil.print("调用命令推送成功！ " + com.alibaba.fastjson.JSONObject
													.toJSONString(invokeThingServiceResponse));

											AppPushLog appPushLog = new AppPushLog();
											appPushLog.setDeviceId(deviceUser.getDeviceId());
											appPushLog.setAppPushId(bean.getId());
											appPushLog.setUserId(deviceUser.getSchoolUserId());
											appPushLog.setCommandText(str);
											appPushLog.setCreateTime(new Date());
											appPushLog.setUpdateTime(new Date());
											appPushLog.setClassId(deviceUser.getClassId());
											info.appPushLogDao.save(appPushLog);

										} else {
											LogUtil.error(
													"调用命令推送失败！！requestId:" + invokeThingServiceResponse.getRequestId()
															+ "原因：" + invokeThingServiceResponse.getErrorMessage());
										}
									}
									bean.setPushTime(new Date());
									bean.setStatus(2);
									info.appPushDao.update(bean);

									// sysLog.save();// 日志入库
									log.info("发送更新应用列表命令end......");
								}

							} catch (Exception e) {
								log.error("发送更新应用列表命令异常：" + e.getMessage());
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
