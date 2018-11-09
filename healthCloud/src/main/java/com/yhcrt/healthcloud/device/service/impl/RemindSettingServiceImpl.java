package com.yhcrt.healthcloud.device.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhcrt.healthcloud.device.entity.RemindSetting;
import com.yhcrt.healthcloud.device.entity.RemindSettingExample;
import com.yhcrt.healthcloud.device.entity.RemindSettingExample.Criteria;
import com.yhcrt.healthcloud.device.mapper.RemindSettingMapper;
import com.yhcrt.healthcloud.device.service.RemindSettingService;

@Service
public class RemindSettingServiceImpl implements RemindSettingService {
	@Autowired
	private RemindSettingMapper remindSettingMapper;

	@Override
	public int insert(RemindSetting record) {
		return remindSettingMapper.insert(record);
	}

	@Override
	public int deleteByCid(Integer cid) {
		return remindSettingMapper.deleteByPrimaryKey(cid);
	}

	@Override
	public int update(RemindSetting record) {
		return remindSettingMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<RemindSetting> selectByParams(HashMap<String, Object> params) {
		RemindSettingExample example = new RemindSettingExample();
		Criteria criteria = example.createCriteria();
		if (params.get("imei") != null) {
			criteria.andImeiEqualTo((String) params.get("imei"));
		}else{
			criteria.andImeiIsNull();
		}
		if (params.get("remindType") != null) {
			criteria.andRemindTypeEqualTo((Integer) params.get("remindType"));
		}
		return remindSettingMapper.selectByExample(example);
	}

	@Override
	public RemindSetting selectByCid(Integer cid) {
		return remindSettingMapper.selectByPrimaryKey(cid);
	}

}
