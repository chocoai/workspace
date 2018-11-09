package com.yhcrt.healthcloud.health.service;

import java.util.List;

import com.yhcrt.healthcloud.health.entity.HrTransfusion;

public interface HrTransfusionService {

	int insert(HrTransfusion record);

	List<HrTransfusion> selectByRecordId(Integer recordId);

	int updateByPrimaryKey(HrTransfusion record);

	int delete(Integer record);
}
