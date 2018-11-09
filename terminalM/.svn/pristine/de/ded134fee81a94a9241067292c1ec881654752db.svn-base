package com.whty.assis.basicdata.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.whty.assis.basicdata.model.School;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

import net.sf.json.JSONArray;

public interface SchoolService {

	public void save(School school);

	public List<School> listByCondition(Map<String, Object> paramMap);

	public List<Map<String, Object>> listSchooleMap(Map<String, Object> param);

	public HandlerResult querySchoolPage(Map<String, Object> paramMap, PageContext page);

	public void update(School school);

	/**
	 * @param paraMap
	 * @return
	 */
	public List<Map<String, Object>> listByConditionByAreaCode(Map<String, Object> paraMap);

	JSONArray listSchool(String url, String platformCode, String province, String city, String orgName);
	
	String listOgra(Map<String, Object> param, String string, String string2, String url);
//	public List<Map<String, Object>> listSchoolAreaTree(Map<String, Object> paraMap);
	
	//按地区查询所有学校
	public List<School> getSchools(Integer provinceId,Integer cityId,Integer areaId);
	
	//查询所有学校
	public List<School> getAllSchools();

	/**
	 * @param valueOf
	 * @return
	 */
	public School loadById(Integer valueOf);

	/**
	 * @param request
	 * @return
	 */
	public String update(HttpServletRequest request);

	/**
	 * @param valueOf
	 * @return
	 */
	public String delete(Integer valueOf);

}
