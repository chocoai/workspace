package com.fxzhj.service;

import java.util.List;

import com.fxzhj.model.Port;

public interface PortService {

	//根据设备id查询端口信息
	List<Port> queryPort(String deviceId);

	//根据id修改状态
	void updatePort(Long id, Integer status);

}
