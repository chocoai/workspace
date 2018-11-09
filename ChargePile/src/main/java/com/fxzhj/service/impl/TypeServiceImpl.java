package com.fxzhj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxzhj.mapper.TypeMapper;
import com.fxzhj.model.Type;
import com.fxzhj.service.TypeService;

@Service
public class TypeServiceImpl implements TypeService {

	@Autowired
	private TypeMapper TMapper;

	//二维码绑定类型
	@Override
	public List<Type> showCombobox() {
		return TMapper.showCombobox();
	}

}
