package com.fxzhj.mapper;

import java.util.List;

import com.fxzhj.model.Area;

public interface AreaMapper {

	//查询省
	List<Area> queryProvince();

	//查询市
	List<Area> queryCity(Integer id);

	//查询区县
	List<Area> queryCounty(Integer id);

}