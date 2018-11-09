package com.fxzhj.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxzhj.mapper.RecordMapper;
import com.fxzhj.model.Record;
import com.fxzhj.service.RecordService;

@Service
public class RecordServiceImpl implements RecordService {

	@Autowired
	private RecordMapper RMapper;

	//根据条件查询充电记录
	@Override
	public List<Record> queryDevice(Record record) {
		List<Record> list = new ArrayList<Record>();
		String deep = record.getDeep();
		if("9".equals(deep)){
			//表示具体小区id
			list = RMapper.queryByCId(record);
		}else if("1".equals(deep) || "2".equals(deep) ||"3".equals(deep)){
			//表示区域id
			list = RMapper.queryByAId(record);
		}
		return list;
	}


}
