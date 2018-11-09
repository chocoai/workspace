package com.yhcrt.healthcloud.health.service;

import java.util.List;

import com.yhcrt.healthcloud.health.entity.HrOperation;


public interface HrOperationService {
	
	int insert(HrOperation record);
	
	List<HrOperation> selectByRecordId(Integer recordId);
	
	int updateByPrimaryKey(HrOperation record);
	
	int delete(Integer record);
}
