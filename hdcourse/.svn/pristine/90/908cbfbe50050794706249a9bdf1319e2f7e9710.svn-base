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

import com.bigdata.assis.manage.dao.CountHomeDao;
import com.bigdata.assis.manage.model.HomeCount;
import com.bigdata.assis.manage.service.CountHomeService;
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
import com.whty.page.util.HandlerResult;

@Service
public class CountHomeServiceImpl implements CountHomeService {

	private static Logger log = Logger.getLogger(CountHomeServiceImpl.class);

	@Autowired
	private CountHomeDao countHomeDao;

	@Override
	public List<HomeCount> getBasicIndexes() {
		List<HomeCount> list = countHomeDao.getBasicIndexes();
		return list;
	}

	@Override
	public List<HomeCount> getCityBasicIndexes(Map<String, Object> param) {
		List<HomeCount> list = countHomeDao.getCityBasicIndexes(param);
		return list;
	}

	@Override
	public List<HomeCount> getAllIndexesDateCount(Map<String, Object> param) {
		List<HomeCount> list = countHomeDao.getAllIndexesDateCount(param);
		return list;
	}

	@Override
	public List<HomeCount> getAllIndexesMonthCount(Map<String, Object> param) {
		List<HomeCount> list = countHomeDao.getAllIndexesMonthCount(param);
		return list;
	}

	@Override
	public List<HomeCount> getCityIndexesDateCount(Map<String, Object> param) {
		List<HomeCount> list = countHomeDao.getCityIndexesDateCount(param);
		return list;
	}

	@Override
	public List<HomeCount> getCityIndexesMonthCount(Map<String, Object> param) {
		List<HomeCount> list = countHomeDao.getCityIndexesMonthCount(param);
		return list;
	}

	@Override
	public List<HomeCount> getAllValidIndexesDateCount(Map<String, Object> param) {
		List<HomeCount> list = countHomeDao.getAllValidIndexesDateCount(param);
		return list;
	}

	@Override
	public List<HomeCount> getAllValidIndexesMonthCount(Map<String, Object> param) {
		List<HomeCount> list = countHomeDao.getAllValidIndexesMonthCount(param);
		return list;
	}

	@Override
	public List<HomeCount> getCityValidIndexesDateCount(Map<String, Object> param) {
		List<HomeCount> list = countHomeDao.getCityValidIndexesDateCount(param);
		return list;
	}

	@Override
	public List<HomeCount> getCityValidIndexesMonthCount(Map<String, Object> param) {
		List<HomeCount> list = countHomeDao.getCityValidIndexesMonthCount(param);
		return list;
	}
}
