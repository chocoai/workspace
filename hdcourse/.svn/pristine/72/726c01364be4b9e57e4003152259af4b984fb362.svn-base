package com.bigdata.assis.manage.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.ui.Model;

import com.bigdata.assis.manage.model.HomeCount;
import com.whty.assis.demo.model.AreaMonthUsageCount;
import com.whty.assis.demo.model.OrgMonthUsageCount;
import com.whty.assis.demo.model.UserMonthUsageCount;
import com.whty.page.util.HandlerResult;

public interface CountHomeService {

	List<HomeCount> getBasicIndexes();

	List<HomeCount> getCityBasicIndexes(Map<String, Object> param);

	List<HomeCount> getAllIndexesDateCount(Map<String, Object> param);

	List<HomeCount> getAllIndexesMonthCount(Map<String, Object> param);

	List<HomeCount> getCityIndexesDateCount(Map<String, Object> param);

	List<HomeCount> getCityIndexesMonthCount(Map<String, Object> param);

	List<HomeCount> getAllValidIndexesDateCount(Map<String, Object> param);

	List<HomeCount> getAllValidIndexesMonthCount(Map<String, Object> param);

	List<HomeCount> getCityValidIndexesDateCount(Map<String, Object> param);

	List<HomeCount> getCityValidIndexesMonthCount(Map<String, Object> param);
}
