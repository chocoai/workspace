package com.fxzhj.service;

import java.util.List;

import com.fxzhj.model.Area;

public interface AreaService {

	//tree根目录省
	List<Area> queryAreaTree();

	//根据省其子孙节点
	List<Area> queryChild(Integer id, Byte deep);

}
