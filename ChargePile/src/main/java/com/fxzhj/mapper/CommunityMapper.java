package com.fxzhj.mapper;

import java.util.List;

import com.fxzhj.model.Community;

public interface CommunityMapper {

	//根据区县id查询小区
	List<Community> queryByParentId(Integer parentId);
}