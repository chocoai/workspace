package com.yhcrt.healthcloud.device.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhcrt.healthcloud.device.entity.SecureLocationSetting;
import com.yhcrt.healthcloud.device.entity.SecureLocationSettingExample;
import com.yhcrt.healthcloud.device.entity.SecureLocationSettingExample.Criteria;
import com.yhcrt.healthcloud.device.mapper.SecureLocationSettingMapper;
import com.yhcrt.healthcloud.device.service.SecureLocationSettingService;

/**
 * 
 * @author rpf
 *
 */

@Service
public class SecureLocationSettingServiceImpl implements SecureLocationSettingService {
	@Autowired
	private SecureLocationSettingMapper locationSettingMapper;

	@Override
	public int insert(SecureLocationSetting locationSetting) {
		return locationSettingMapper.insert(locationSetting);
	}

	@Override
	public int deleteById(Integer id) {
		return locationSettingMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(SecureLocationSetting locationSetting) {
		return locationSettingMapper.updateByPrimaryKey(locationSetting);
	}

	@Override
	public List<SecureLocationSetting> selectByParams(HashMap<String, Object> params) {
		SecureLocationSettingExample example = new SecureLocationSettingExample();
		Criteria criteria = example.createCriteria();
		if (params.get("imei") != null) {
			criteria.andImeiEqualTo((String) params.get("imei"));
		} else {
			// criteria.andImeiIsNull();
			criteria.andImeiEqualTo("");
		}
		return locationSettingMapper.selectByExample(example);
	}

	@Override
	public SecureLocationSetting getOneById(Integer id) {
		return locationSettingMapper.selectByPrimaryKey(id);
	}

}
