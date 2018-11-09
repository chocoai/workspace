package com.fxzhj.mapper;

import java.util.List;

import com.fxzhj.model.Power;

public interface PowerMapper {

	//根据id修改状态
	void updatePort(Long id, Integer status);

	//根据条件查询设备用电记录
	List<Power> queryPower(Power power);

	//根据设备id查询设备明细电量
	List<Power> queryByDid(Long deviceId);
}