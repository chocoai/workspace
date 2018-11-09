/**
 *
 */
package com.whty.util;

import com.whty.entity.*;
import com.whty.mapper.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author zhangzheng
 * @date 2018年6月18日
 */
@Component
public class DeviceStatusLogThread implements ApplicationRunner {
	private static Logger log = Logger.getLogger(DeviceStatusLogThread.class);

	// private static final int queueSize = 5000; // 入库队列大小
	private static boolean threadRun = true; // 线程是否运行

	@Autowired
	private TDeviceInfoMapper tDeviceInfoMapper;
	@Autowired
	private TAioUseTakingMapper tAioUseTakingMapper;
	@Autowired
	private TAioOnlieMapper tAioOnlieMapper;
	@Autowired
	private TEbookOnlieMapper tEbookOnlieMapper;
	@Autowired
	private TEbookUseTakingMapper tEbookUseTakingMapper;

	/**
	 * 队列
	 */
	private static Queue<DeviceStatusListener> queue = new ConcurrentLinkedQueue<DeviceStatusListener>(); // 此队列按照

	/**
	 * 向队列中增加Syslog对象，基于LinkedBlockingQueue
	 *
	 *            public static void add(Syslog sysLog){ try { log.info(
	 *            "put操作日志到缓存queue start......"); queue.put(sysLog); log.info(
	 *            "put操作日志到缓存queue end......"); } catch (InterruptedException e)
	 *            { log.error("put操作日志到缓存queue异常"); throw new RuntimeException(
	 *            "ThreadSysLog -> add Exception"); } }
	 */

	public static void setThreadRun(boolean threadRun) {
		DeviceStatusLogThread.threadRun = threadRun;
	}

	/**
	 * 向队列中增加Syslog对象，基于ConcurrentLinkedQueue
	 *
	 */
	public static void add(DeviceStatusListener bean) {
		queue.offer(bean); // 将指定元素插入此队列的尾部
	}

	/**
	 * 获取对象，基于ConcurrentLinkedQueue
	 *
	 * @return
	 */
	public static DeviceStatusListener getDeviceCommandLog() {
		return queue.poll(); // 获取并移除此队列的头，如果此队列为空，则返回 null
	}

	/**
	 * 启动入库线程
	 */
	@Override
	public void run(ApplicationArguments args) throws Exception {
		try {
			for (int i = 0; i < 5; i++) {
				Thread insertDbThread = new Thread(new Runnable() {
					public void run() {
						while (threadRun) {
							try {
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

								DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
								Date dayTime = new Date();
								Date nowTime = new Date();

								DeviceStatusListener deviceStatusListener = getDeviceCommandLog();
								Thread.sleep(200);
								if(deviceStatusListener!=null){
									dayTime.setTime((Long) deviceStatusListener.getStatus().get("time"));
								//设备在线
								if(deviceStatusListener.getAction().equals("online")){
									//一体机
									if(deviceStatusListener.getProductKey().equals(Constants.AIO_PRODUCT_KEY)){
										TDeviceInfoExample example = new TDeviceInfoExample();
										example.createCriteria().andAliDeviceNameEqualTo(deviceStatusListener.getDeviceName());
										List<TDeviceInfo> tDeviceInfos = tDeviceInfoMapper.selectByExample(example);
										if(tDeviceInfos!=null && tDeviceInfos.size()>0){
											TDeviceInfo deviceInfo = tDeviceInfos.get(0);
											if(deviceInfo!=null){
												deviceInfo.setOnline(Constants.DEVICE_STATUS_OPEN);
												tDeviceInfoMapper.updateByPrimaryKey(deviceInfo);
												//开机入库
												TAioOnlie tAioOnlie = new TAioOnlie();
												tAioOnlie.setStatus(Constants.DEVICE_STATUS_OPEN);//开机状态
												tAioOnlie.setDeviceId(deviceInfo.getId());
												tAioOnlie.setOpenTime(dayTime);
												tAioOnlie.setCloseTime(dayTime);
												tAioOnlie.setCreateTime(nowTime);
												tAioOnlie.setUpdateTime(nowTime);
												tAioOnlieMapper.insert(tAioOnlie);

											}
										}
									}
									//平板
									if(deviceStatusListener.getProductKey().equals(Constants.EBOOK_PRODUCT_KEY)){
										TDeviceInfoExample example = new TDeviceInfoExample();
										example.createCriteria().andAliDeviceNameEqualTo(deviceStatusListener.getDeviceName());
										List<TDeviceInfo> tDeviceInfos =tDeviceInfoMapper.selectByExample(example);
										if(tDeviceInfos!=null && tDeviceInfos.size()>0){
											TDeviceInfo deviceInfo = tDeviceInfos.get(0);
											if(deviceInfo!=null){
												deviceInfo.setOnline(Constants.DEVICE_STATUS_OPEN);
												tDeviceInfoMapper.updateByPrimaryKey(deviceInfo);
												//开机入库
												TEbookOnlie tEbookOnlie = new TEbookOnlie();
												tEbookOnlie.setDeviceId(deviceInfo.getId());
												tEbookOnlie.setStatus(Constants.DEVICE_STATUS_OPEN);
												tEbookOnlie.setCreateTime(nowTime);
												tEbookOnlie.setUpdateTime(nowTime);
												tEbookOnlie.setOpenTime(dayTime);
												tEbookOnlie.setCloseTime(dayTime);
												tEbookOnlieMapper.insert(tEbookOnlie);
											}
										}
									}
								}

								//设备离线
								if(deviceStatusListener.getAction().equals("offline")){
									Date dayTime2 =format.parse(sdf.format(dayTime));
									//一体机
									if(deviceStatusListener.getProductKey().equals(Constants.AIO_PRODUCT_KEY)){
										TDeviceInfoExample example = new TDeviceInfoExample();
										example.createCriteria().andAliDeviceNameEqualTo(deviceStatusListener.getDeviceName());
										List<TDeviceInfo> tDeviceInfos = tDeviceInfoMapper.selectByExample(example);
										if(tDeviceInfos!=null && tDeviceInfos.size()>0){
											TDeviceInfo deviceInfo = tDeviceInfos.get(0);
											if(deviceInfo!=null){
												deviceInfo.setOnline(Constants.DEVICE_STATUS_CLOSE);
												tDeviceInfoMapper.updateByPrimaryKey(deviceInfo);

												TAioOnlieExample tAioOnlieExample = new TAioOnlieExample();
												tAioOnlieExample.createCriteria().andDeviceIdEqualTo(deviceInfo.getId()).andStatusEqualTo(Constants.DEVICE_STATUS_OPEN);
												tAioOnlieExample.setOrderByClause("open_time desc");
												List<TAioOnlie> tAioOnlies =tAioOnlieMapper.selectByExample(tAioOnlieExample);
												if(tAioOnlies!=null && tAioOnlies.size()>0){
													//修改设备状态为关机状态
													TAioOnlie tAioOnlie = tAioOnlies.get(0);
													tAioOnlie.setStatus(Constants.DEVICE_STATUS_CLOSE);
													tAioOnlie.setCloseTime(dayTime);
													tAioOnlie.setUpdateTime(nowTime);
													tAioOnlieMapper.updateByPrimaryKey(tAioOnlie);

													//
													TAioUseTakingExample tAioUseTakingExample = new TAioUseTakingExample();
													tAioUseTakingExample.createCriteria().andDeviceIdEqualTo(deviceInfo.getId()).andDayTimeEqualTo(dayTime2);
													List<TAioUseTaking> tAioUseTakings = tAioUseTakingMapper.selectByExample(tAioUseTakingExample);
													Long useTaking = dayTime.getTime()-tAioOnlie.getOpenTime().getTime();
													if(tAioUseTakings!=null && tAioUseTakings.size()>0){
														if(useTaking>0){
															TAioUseTaking tAioUseTaking = tAioUseTakings.get(0);
															tAioUseTaking.setUpdateTime(nowTime);
															tAioUseTaking.setUseTaking(tAioUseTaking.getUseTaking()+useTaking);
															tAioUseTakingMapper.updateByPrimaryKey(tAioUseTaking);
														}
													}else{
														if(useTaking>0){
															TAioUseTaking tAioUseTaking = new TAioUseTaking();
															tAioUseTaking.setDeviceId(deviceInfo.getId());
															tAioUseTaking.setUseTaking(useTaking);
															tAioUseTaking.setCreatTime(nowTime);
															tAioUseTaking.setUpdateTime(nowTime);
															tAioUseTaking.setDayTime(dayTime2);
															tAioUseTakingMapper.insert(tAioUseTaking);
														}
													}
												}
											}
										}
									}
									//平板
									if(deviceStatusListener.getProductKey().equals(Constants.EBOOK_PRODUCT_KEY)){
										TDeviceInfoExample example = new TDeviceInfoExample();
										example.createCriteria().andAliDeviceNameEqualTo(deviceStatusListener.getDeviceName());
										List<TDeviceInfo> tDeviceInfos = tDeviceInfoMapper.selectByExample(example);
										if(tDeviceInfos!=null && tDeviceInfos.size()>0){
											TDeviceInfo deviceInfo = tDeviceInfos.get(0);
											if(deviceInfo!=null){
												deviceInfo.setOnline(Constants.DEVICE_STATUS_CLOSE);
												tDeviceInfoMapper.updateByPrimaryKey(deviceInfo);

												TEbookOnlieExample tEbookOnlieExample = new TEbookOnlieExample();
												tEbookOnlieExample.createCriteria().andDeviceIdEqualTo(deviceInfo.getId()).andStatusEqualTo(Constants.DEVICE_STATUS_OPEN);
												tEbookOnlieExample.setOrderByClause("open_time desc");
												List<TEbookOnlie> tEbookOnlies =tEbookOnlieMapper.selectByExample(tEbookOnlieExample);
												if(tEbookOnlies!=null && tEbookOnlies.size()>0) {
													//修改设备状态为关机状态
													TEbookOnlie tEbookOnlie = tEbookOnlies.get(0);
													tEbookOnlie.setStatus(Constants.DEVICE_STATUS_CLOSE);
													tEbookOnlie.setCloseTime(dayTime);
													tEbookOnlie.setUpdateTime(nowTime);
													tEbookOnlieMapper.updateByPrimaryKey(tEbookOnlie);

													//
													TEbookUseTakingExample tEbookUseTakingExample = new TEbookUseTakingExample();
													tEbookUseTakingExample.createCriteria().andDeviceIdEqualTo(deviceInfo.getId()).andDayTimeEqualTo(dayTime2);
													List<TEbookUseTaking> tEbookUseTakings = tEbookUseTakingMapper.selectByExample(tEbookUseTakingExample);
													Long useTaking = dayTime.getTime() - tEbookOnlie.getOpenTime().getTime();
													if (tEbookUseTakings != null && tEbookUseTakings.size() > 0) {
														if(useTaking>0){
															TEbookUseTaking tEbookUseTaking = tEbookUseTakings.get(0);
															tEbookUseTaking.setUpdateTime(nowTime);
															tEbookUseTaking.setUseTaking(tEbookUseTaking.getUseTaking() + useTaking);
															tEbookUseTakingMapper.updateByPrimaryKey(tEbookUseTaking);
														}
													} else {
														if(useTaking>0){
															TEbookUseTaking tEbookUseTaking = new TEbookUseTaking();
															tEbookUseTaking.setDeviceId(deviceInfo.getId());
															tEbookUseTaking.setUseTaking(useTaking);
															tEbookUseTaking.setCreatTime(nowTime);
															tEbookUseTaking.setUpdateTime(nowTime);
															tEbookUseTaking.setDayTime(dayTime2);
															tEbookUseTakingMapper.insert(tEbookUseTaking);
														}
													}
												}
											}
										}
									}
								}

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
