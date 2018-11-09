package com.yhcrt.healthcloud.health.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhcrt.healthcloud.health.entity.HrTrauma;
import com.yhcrt.healthcloud.health.mapper.HrTraumaMapper;
import com.yhcrt.healthcloud.health.service.HrTraumaService;

@Service
public class HrTraumaServiceImpl implements HrTraumaService{
	@Autowired
	private HrTraumaMapper hrTraumaMapper;
	
	
	@Override
	public int insert(HrTrauma record) {
		return hrTraumaMapper.insert(record);
	}

	@Override
	public List<HrTrauma> selectByRecordId(Integer recordId) {
		return hrTraumaMapper.selectByRecordId(recordId);
	}

	@Override
	public int updateByPrimaryKey(HrTrauma record) {
		return hrTraumaMapper.updateByPrimaryKey(record);
	}

	@Override
	public int delete(Integer record) {
		return hrTraumaMapper.deleteByPrimaryKey(record);
	}
	
}
