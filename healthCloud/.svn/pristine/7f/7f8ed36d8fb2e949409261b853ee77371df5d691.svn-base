package com.yhcrt.healthcloud.health.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhcrt.healthcloud.health.entity.HrTransfusion;
import com.yhcrt.healthcloud.health.mapper.HrTransfusionMapper;
import com.yhcrt.healthcloud.health.service.HrTransfusionService;

@Service
public class HrTransfusionServiceImpl implements HrTransfusionService {
	@Autowired
	private HrTransfusionMapper hrTransfusionMapper;

	@Override
	public int insert(HrTransfusion record) {
		return hrTransfusionMapper.insert(record);
	}

	@Override
	public List<HrTransfusion> selectByRecordId(Integer recordId) {
		return hrTransfusionMapper.selectByRecordId(recordId);
	}

	@Override
	public int updateByPrimaryKey(HrTransfusion record) {
		return hrTransfusionMapper.updateByPrimaryKey(record);
	}

	@Override
	public int delete(Integer record) {
		return hrTransfusionMapper.deleteByPrimaryKey(record);
	}

}
