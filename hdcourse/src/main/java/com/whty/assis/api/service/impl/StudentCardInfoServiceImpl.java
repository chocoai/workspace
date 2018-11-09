package com.whty.assis.api.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.api.service.StudentCardInfoService;
import com.whty.assis.demo.dao.StudentCardPropertyDao;

@Service
public class StudentCardInfoServiceImpl implements StudentCardInfoService {

	@Autowired
	private StudentCardPropertyDao studentCardInfoDao;

	@Override
	public String getStudentCardConfig(Map<String, Object> param) {
		return studentCardInfoDao.getStudentCardConfig(param);
	}

}
