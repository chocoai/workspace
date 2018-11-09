package com.yhcrt.healthcloud.device.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhcrt.healthcloud.device.entity.DSFSetting;
import com.yhcrt.healthcloud.device.entity.DSFSettingExample;
import com.yhcrt.healthcloud.device.entity.DSFSettingExample.Criteria;
import com.yhcrt.healthcloud.device.mapper.DSFSettingMapper;
import com.yhcrt.healthcloud.device.service.DSFSettingService;
/**
 * 
 * @author rpf
 *
 */
@Service
public class DSFSettingServiceImpl implements DSFSettingService {

	@Autowired
	private DSFSettingMapper dsfSettingMapper;

	@Override
	public int insert(DSFSetting dsfSetting) {
		return dsfSettingMapper.insert(dsfSetting);
	}
	@Override
	public int delete(Integer cid) {
		return dsfSettingMapper.deleteByPrimaryKey(cid);
	}
	@Override
	public int update(DSFSetting dsfSetting) {
		return dsfSettingMapper.updateByPrimaryKey(dsfSetting);
	}
	@Override
	public List<DSFSetting> getListByParams(HashMap<String, Object> params) {
		DSFSettingExample example = new DSFSettingExample();
		Criteria criteria = example.createCriteria();
		if (params.get("imei") != null) {
			criteria.andImeiEqualTo((String) params.get("imei"));
		} else {
			criteria.andImeiIsNull();
		}
		if (params.get("dataType") != null) {
			criteria.andDataTypeEqualTo((Integer) params.get("dataType"));
		}
		return dsfSettingMapper.selectByExample(example);
	}
	@Override
	public DSFSetting getDSFSettingByCid(Integer cid) {
		return dsfSettingMapper.selectByPrimaryKey(cid);
	}

}
