package com.fxzhj.mapper;

import java.util.List;

import com.fxzhj.model.Port;

public interface PortMapper {

	//批量新增设备端口号
	void batchPort(List<Port> list);

	//根据设备id修改状态
	void updateByDeviceId(Long id, Integer status);

	//根据设备id查询端口信息
	List<Port> queryPort(long deviceId);

	//根据id修改状态
	void updatePort(Long id, Integer status);

	//删除端口
	void delete(Long id);
}