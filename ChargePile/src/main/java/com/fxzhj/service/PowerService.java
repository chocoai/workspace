package com.fxzhj.service;

import java.util.List;

import com.fxzhj.model.Power;

public interface PowerService {

	//根据条件查询设备用电记录
	List<Power> queryPower(Power power);
	
	//根据设备id查询设备明细电量
	List<Power> queryByDid(String deviceId);

}
