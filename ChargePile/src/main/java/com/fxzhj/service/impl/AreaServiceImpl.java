package com.fxzhj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxzhj.mapper.AreaMapper;
import com.fxzhj.model.Area;
import com.fxzhj.service.AreaService;

@Service
public class AreaServiceImpl implements AreaService {

	@Autowired
	private AreaMapper AMapper;

	//查询省
	@Override
	public List<Area> queryAreaTree() {
		return AMapper.queryProvince();
	}


	//根据省其子孙节点
	@Override
	public List<Area> queryChild(Integer id, Byte deep) {
		if(deep==1){
			return AMapper.queryCity(id);
		}else if(deep==2){
			return AMapper.queryCounty(id);
		}else{
			return null;
		}
	}

}
