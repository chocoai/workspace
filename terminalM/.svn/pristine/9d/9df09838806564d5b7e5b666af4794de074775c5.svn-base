package com.whty.assis.basicdata.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.whty.assis.basicdata.model.SchoolLocation;
import com.whty.assis.basicdata.model.SchoolLocationArea;
import com.whty.page.util.HandlerResult;

public interface SchoolLocationService {

	public String saveSchoolLocation(HttpServletRequest request);

	public List<SchoolLocation> listByCondition(Map<String, Object> paramMap);

	public HandlerResult querySchoolLocationPage(Map<String, Object> paramMap);

	public void updateSchoolLocation(SchoolLocation bean);

	/**
	 * 查询功能区域
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<SchoolLocationArea> listSchoolLocationArea(Map<String, Object> paramMap);

	/**
	 * 保存功能区域
	 * 
	 * @param request
	 * @return
	 */
	public String saveSchoolLocationArea(HttpServletRequest request);

	/**
	 * 更新功能区域
	 * 
	 * @param request
	 * @return
	 */
	public String updateSchoolLocationArea(HttpServletRequest request);

	/**
	 * 删除功能区域
	 * 
	 * @param request
	 * @return
	 */
	public String deleteSchoolLocationArea(HttpServletRequest request);

	/**
	 * @param schoolLocationId
	 * @param schoolLocationAreaId
	 * @return
	 */
	public String deleteSchoolLocation(String schoolLocationId,String layer);

}
