package com.fxzhj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxzhj.mapper.PowerMapper;
import com.fxzhj.model.Power;
import com.fxzhj.service.PowerService;

import tk.mybatis.mapper.util.StringUtil;

@Service
public class PowerServiceImpl implements PowerService {

	@Autowired
	private PowerMapper PMapper;

	//根据条件查询设备用电记录
	@Override
	public List<Power> queryPower(Power power) {
		return PMapper.queryPower(power);
	}

	//根据设备id查询设备明细电量
	@Override
	public List<Power> queryByDid(String deviceId) {
		if(StringUtil.isNotEmpty(deviceId)){
			return PMapper.queryByDid(Long.parseLong(deviceId));
		}
		return null;
	}

}
