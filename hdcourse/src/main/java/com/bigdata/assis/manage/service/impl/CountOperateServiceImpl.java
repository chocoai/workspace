package com.bigdata.assis.manage.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.bigdata.assis.manage.dao.CountOperateDao;
import com.bigdata.assis.manage.model.UserOperate;
import com.bigdata.assis.manage.service.CountOperateService;
import com.whty.assis.demo.dao.AreaDao;
import com.whty.assis.demo.dao.CountLoginDao;
import com.whty.assis.demo.dao.UseCountDao;
import com.whty.assis.demo.model.Area;
import com.whty.assis.demo.model.AreaMonthUsageCount;
import com.whty.assis.demo.model.OrgMonthUsageCount;
import com.whty.assis.demo.model.UserMonthUsageCount;
import com.whty.assis.demo.service.CountLoginService;
import com.whty.assis.demo.service.UseCountService;
import com.whty.common.excel.PoiExcelUtils;
import com.whty.common.util.BigDecimalUtils;
import com.whty.oraclepage.util.HandlerResult;

@Service
public class CountOperateServiceImpl implements CountOperateService {

	private static Logger log = Logger.getLogger(CountOperateServiceImpl.class);

	@Autowired
	private CountOperateDao countOperateDao;

	@Autowired
	private AreaDao areaDao;

	@Override
	public HandlerResult getUserOperate(Map<String, Object> paramMap) {
		HandlerResult handlerResult = new HandlerResult();
		handlerResult.setResultList(countOperateDao.getUserOperate(paramMap));
		return handlerResult;
	}

	@Override
	public List<UserOperate> getUserFunc(Map<String, Object> paramMap) {
		List<UserOperate> list = countOperateDao.getUserFunc(paramMap);
		return list;
	}

}
