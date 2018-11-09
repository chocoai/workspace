package com.yhcrt.healthcloud.health.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhcrt.healthcloud.health.entity.HealthRecord;
import com.yhcrt.healthcloud.health.entity.HealthRecordExample;
import com.yhcrt.healthcloud.health.mapper.HealthRecordMapper;
import com.yhcrt.healthcloud.health.service.HealthRecordService;

@Service
public class HealthRecordServiceImpl implements HealthRecordService {
	@Autowired
	private HealthRecordMapper healthRecordMapper;

	// zhengjiadong
	@Override
	public int insertSelective(HealthRecord record) {
		return healthRecordMapper.insertSelective(record);
	}

	@Override
	public HealthRecord selectByPrimaryKey(Integer memberId) {
		return healthRecordMapper.selectByMemberId(memberId);
	}

	@Override
	public int updateByPrimaryKeySelective(HealthRecord record) {
		return healthRecordMapper.updateByPrimaryKeySelective(record);
	}

	// huzelin
	@Override
	public HealthRecord getRecordByRecordId(int recordId) {
		return healthRecordMapper.selectByPrimaryKey(recordId);
	}

	@Override
	public HealthRecord getRecordByMemberId(int memberId) {
		HealthRecordExample example = new HealthRecordExample();
		example.createCriteria().andMemberIdEqualTo(String.valueOf(memberId));
		example.setOrderByClause("record_id desc");
		List<HealthRecord> selectByExample = healthRecordMapper.selectByExample(example);
		if (selectByExample.size() > 0) {
			return selectByExample.get(0);
		} else {
			return new HealthRecord();
		}
	}

	@Override
	public int saveRecord(HealthRecord record) {
		return healthRecordMapper.insert(record);
	}

	@Override
	public int updateRecord(HealthRecord record) {
		return healthRecordMapper.updateByPrimaryKeySelective(record);
	}
}
