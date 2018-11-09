package com.fxzhj.service;

import java.util.List;

import com.fxzhj.model.Record;

public interface RecordService {

	//根据条件查询充电记录
	List<Record> queryDevice(Record record);

}
