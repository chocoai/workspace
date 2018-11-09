package com.fxzhj.mapper;

import java.util.List;

import com.fxzhj.model.Record;

public interface RecordMapper {

	//根据小区id查询设备充电记录
	List<Record> queryByCId(Record record);

	//根据区域id查询设备充电记录
	List<Record> queryByAId(Record record);
}