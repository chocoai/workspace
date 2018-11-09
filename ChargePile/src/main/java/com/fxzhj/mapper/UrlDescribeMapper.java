package com.fxzhj.mapper;

import com.fxzhj.model.UrlDescribe;

public interface UrlDescribeMapper {

	//增到绑定的路径描述
	void addUrlDescribe(UrlDescribe urlDescribe);

	//首先根据二维码id删除路径描述信息
	int deleteByCid(Long id);

	//批量删除路径描述信息
	void batchDelete(String[] ids);
}