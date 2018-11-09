package com.fxzhj.mapper;

import java.util.List;

import com.fxzhj.model.Device;

public interface DeviceMapper {

    // 查询设备
	List<Device> queryDevice(Device device);

	//新增判断是否重复
	int count(Device device);

	//新增数据
	void addDevice(Device device);

	//修改判断是否重复
	int countClearById(Device device);

	//修改数据
	void updateDevice(Device device);

	//查询所有未绑定的设备
	List<Device> queryUnboundedDevice(Device device);

	//根据小区id修改绑定地址
	void updateByCId(Integer communityId);
	
	//根据区域id修改绑定地址
	void updateByAId(Integer areaId);

	//根据id修改状态
	void updateStatusById(Long id, Integer status);

	//根据查询条件查询小区设备运营记录
	List<Device> queryOperateDevice(Device device);
		
	//根据查询条件查询区域设备运营记录
	List<Device> queryAreaDevice(Device device);

	//修改设备绑定地址
	void updateDeviceAreaorComm(Device device);

	//下拉框显示
	List<Device> showCombobox();

	//根据id查询设备端口和节点编号
	Device queryById(Long id);

}