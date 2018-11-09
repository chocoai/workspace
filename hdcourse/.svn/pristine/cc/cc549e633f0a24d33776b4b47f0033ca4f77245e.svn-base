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

import com.bigdata.assis.manage.dao.CountCOUDao;
import com.bigdata.assis.manage.model.COUCount;
import com.bigdata.assis.manage.service.CountCOUService;
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
public class CountCOUServiceImpl implements CountCOUService {

	private static Logger log = Logger.getLogger(CountCOUServiceImpl.class);

	@Autowired
	private CountCOUDao countCOUDao;

	@Override
	public COUCount getHomeCount() {
		COUCount cou = new COUCount();
		List<COUCount> list = countCOUDao.getHomeCount();
		cou.setALL_LOGIN_CITY_NUM(list.get(0).getALL_LOGIN_CITY_NUM());
		cou.setALL_LOGIN_ORGA_NUM(list.get(0).getALL_LOGIN_ORGA_NUM());
		cou.setALL_LOGIN_USER_NUM(list.get(0).getALL_LOGIN_USER_NUM());
		return cou;
	}

	@Override
	public COUCount getAllMonCount(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public COUCount getCityHomeCount(Map<String, Object> param) {
		COUCount cou = new COUCount();
		List<COUCount> list = countCOUDao.getCityHomeCount(param);
		cou.setALL_LOGIN_CITY_NUM(list.get(0).getALL_LOGIN_CITY_NUM());
		cou.setALL_LOGIN_ORGA_NUM(list.get(0).getALL_LOGIN_ORGA_NUM());
		cou.setALL_LOGIN_USER_NUM(list.get(0).getALL_LOGIN_USER_NUM());
		return cou;
	}

}
