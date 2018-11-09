package com.fxzhj.mapper;

import com.fxzhj.model.RangeDevice;

public interface RangeDeviceMapper {

	//新增数据
	void addBean(RangeDevice rangeDevice);

	//根据具体小区id删除数据
	void deleteByCId(Integer communityId);

	//根据区域id删除数据
	void deleteByAId(Integer areaId);

}