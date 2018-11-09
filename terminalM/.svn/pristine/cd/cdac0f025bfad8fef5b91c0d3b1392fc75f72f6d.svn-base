package com.whty.assis.basicdata.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.basicdata.model.School;

public interface SchoolDao extends IBaseDao<School>{


	/**
	 * @param paraMap
	 * @return
	 */
	List<Map<String, Object>> listByConditionByAreaCode(Map<String, Object> paraMap);

	/**
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> listSchoolMap(Map<String, Object> param);
	
	//查询所有学校
	public List<School> getSchools(@Param("provinceCode")Integer provinceId,@Param("cityCode")Integer cityId,@Param("areaCode")Integer areaId);

	public School getSchoolById(Integer id);
	
	public List<School> getAllSchools();
}
