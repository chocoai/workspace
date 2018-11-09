package com.yhcrt.healthcloud.device.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhcrt.healthcloud.device.entity.HeartRateSetting;
import com.yhcrt.healthcloud.device.entity.HeartRateSettingExample;
import com.yhcrt.healthcloud.device.entity.HeartRateSettingExample.Criteria;
import com.yhcrt.healthcloud.device.mapper.HeartRateSettingMapper;
import com.yhcrt.healthcloud.device.service.HeartRateSettingService;
@Service
public class HeartRateSettingServiceImpl implements HeartRateSettingService {
	
	@Autowired
	private HeartRateSettingMapper heartRateSettingMapper;

	@Override
	public int insert(HeartRateSetting heartRateSetting) {
		return heartRateSettingMapper.insert(heartRateSetting);
	}

	@Override
	public int deleteByCid(Integer cid) {
		return heartRateSettingMapper.deleteByPrimaryKey(cid);
	}

	@Override
	public int update(HeartRateSetting heartRateSetting) {
		return heartRateSettingMapper.updateByPrimaryKey(heartRateSetting);
	}

	@Override
	public List<HeartRateSetting> selectByParams(HashMap<String, Object> params) {
		HeartRateSettingExample example = new HeartRateSettingExample();
		Criteria criteria = example.createCriteria();
		if (params.get("imei") != null) {
			criteria.andImeiEqualTo((String) params.get("imei"));
		} else {
			// criteria.andImeiIsNull();
			criteria.andImeiEqualTo("");
		}
		return heartRateSettingMapper.selectByExample(example);
	}

	@Override
	public HeartRateSetting getHeartRateSettingByCid(Integer cid) {
		return heartRateSettingMapper.selectByPrimaryKey(cid);
	}

}
