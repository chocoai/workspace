package com.fxzhj.mapper;

import java.util.List;

import com.fxzhj.model.Type;

public interface TypeMapper {

	//二维码绑定类型
	List<Type> showCombobox();

	//查询类型为2的content
	String queryByType(Integer i);
}