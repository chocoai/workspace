package com.fxzhj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxzhj.mapper.CommunityMapper;
import com.fxzhj.model.Community;
import com.fxzhj.service.CommunityService;

@Service
public class CommunityServiceImpl implements CommunityService {

	@Autowired
	private CommunityMapper CMapper;

	//查询具体小区
	@Override
	public List<Community> queryByParentId(Integer id) {
		return CMapper.queryByParentId(id);
	}

}
