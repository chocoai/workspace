package com.yhcrt.healthcloud.health.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhcrt.healthcloud.health.entity.HrOperation;
import com.yhcrt.healthcloud.health.mapper.HrOperationMapper;
import com.yhcrt.healthcloud.health.service.HrOperationService;

@Service
public class HrOperationServiceImpl implements HrOperationService {
	@Autowired
	private HrOperationMapper hrOperationMapper;

	@Override
	public int insert(HrOperation record) {
		return hrOperationMapper.insert(record);
	}

	@Override
	public List<HrOperation> selectByRecordId(Integer recordId) {
		List<HrOperation> hrOperations = hrOperationMapper.selectByRecordId(recordId);
		return hrOperations;
	}

	@Override
	public int updateByPrimaryKey(HrOperation record) {
		return hrOperationMapper.updateByPrimaryKey(record);
	}

	@Override
	public int delete(Integer record) {
		return hrOperationMapper.deleteByPrimaryKey(record);
	}

}
