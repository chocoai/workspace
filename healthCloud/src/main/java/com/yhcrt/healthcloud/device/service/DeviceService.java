package com.yhcrt.healthcloud.device.service;

import java.util.List;
import java.util.Map;

import com.yhcrt.healthcloud.device.entity.Device;

/**
 * @Description:设备SERVICE层接口类
 * @author gongjun 2017年5月17日 版权所有：武汉炎黄创新科技服务有限公司
 */
public interface DeviceService {

	int insert(Device device);

	int deleteByDeviceId(Integer deviceId);

	int update(Device device);

	List<Device> listDevicesByArgs(Map<String, Object> map);

	List<Device> listAll();

	Device getDeviceByDeviceId(Integer deviceId);

	/**
	 * 根据imei查询Device对象信息
	 * 
	 * @param imei
	 * @return
	 */
	Device selectByImei(String imei);

	/**
	 * 根据deviceId更新Device对象信息
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(Device record);

	//新增修改时排除重复
	int countByMap(Map<String, Object> map);

}
