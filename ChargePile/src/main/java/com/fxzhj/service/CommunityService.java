package com.fxzhj.service;

import java.util.List;

import com.fxzhj.model.Community;

public interface CommunityService {

	List<Community> queryByParentId(Integer id);

}
