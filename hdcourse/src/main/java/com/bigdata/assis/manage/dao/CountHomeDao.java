package com.bigdata.assis.manage.dao;

import java.util.List;
import java.util.Map;

import com.bigdata.assis.manage.model.HomeCount;

public interface CountHomeDao {

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