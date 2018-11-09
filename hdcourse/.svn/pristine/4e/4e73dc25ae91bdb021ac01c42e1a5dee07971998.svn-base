package com.whty.assis.demo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.demo.dao.InstallLogDao;
import com.whty.assis.demo.model.InstallLog;
import com.whty.assis.demo.service.InstallLogService;
import com.whty.common.excel.PoiExcelUtils;
import com.whty.page.util.HandlerResult;

@Service("installLogService")
public class InstallLogServiceImpl implements InstallLogService {

	@Autowired
	private InstallLogDao installLogDao;

	@Override
	public HandlerResult queryInstallLog(Map<String, Object> paramMap) {
		HandlerResult handlerResult = new HandlerResult();
		handlerResult.setResultList(installLogDao.listByCondition(paramMap));
		return handlerResult;
	}

	@Override
	public HSSFWorkbook exportExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (request.getParameter("startTime") != null && StringUtils.isNotEmpty(request.getParameter("startTime"))) {
			paramMap.put("startTime", request.getParameter("startTime"));
		}

		if (request.getParameter("endTime") != null && StringUtils.isNotEmpty(request.getParameter("endTime"))) {
			paramMap.put("endTime", request.getParameter("endTime"));
		}

		if (request.getParameter("area") != null && StringUtils.isNotEmpty(request.getParameter("area"))) {
			paramMap.put("area", request.getParameter("area"));
		}

		if (request.getParameter("school") != null && StringUtils.isNotEmpty(request.getParameter("school"))) {
			paramMap.put("school", request.getParameter("school"));
		}

		if (request.getParameter("userName") != null && StringUtils.isNotEmpty(request.getParameter("userName"))) {
			paramMap.put("userName", request.getParameter("userName"));
		}

		List<InstallLog> installLogList = installLogDao.listByCondition(paramMap);

		String[] tableHeader = { "区域@area", "学校@school", "班级@className", "cpu@cpu", "内存@memery", "硬盘@disk", "mac@mac",
				"版本@version", "人员@userName", "时间@createTimeStr" };

		return PoiExcelUtils.createExcel2Export("运营统计", "运营统计", tableHeader, installLogList);
	}

}
