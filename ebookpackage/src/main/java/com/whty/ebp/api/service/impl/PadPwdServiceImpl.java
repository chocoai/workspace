package com.whty.ebp.api.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.ebp.api.dao.PadPwdDao;
import com.whty.ebp.api.service.PadPwdService;

@Service
public class PadPwdServiceImpl implements PadPwdService {

	@Autowired
	private PadPwdDao padPwdDao;

	@Override
	public List<Map<String, Object>> find(Map<String, Object> param) {
		return padPwdDao.find(param);
	}

}
