package com.fxzhj.service;

import java.util.List;

import com.fxzhj.model.Device;
import com.fxzhj.model.RangeDevice;

public interface DeviceService {

	// 查询设备
	List<Device> queryDevice(Device device);

	//新增判断是否重复
	int count(Device device);

	//新增数据
	void addDevice(Device device);

	//修改判断是否重复
	int countClearById(Device device);

	//修改数据
	int updateDevice(Device device);

	//查询所有未绑定的设备
	List<Device> queryUnboundedDevice(Device device);

	//根据传入的条件修改设备相关表
	void deviceService(RangeDevice rangeDevice);

	//根据查询条件查询设备运营记录
	List<Device> queryOperateDevice(Device device);

	//根据id修改状态
	void updateStatusById(Long id, Integer status);

	//下拉框设置
	List<Device> showCombobox();

}
