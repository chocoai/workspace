package com.whty.wfd.page.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.whty.wfd.page.model.TPlate;
import com.whty.wfd.page.model.TPlateExample;

public interface TPlateMapper {
	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	long countByExample(TPlateExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int deleteByExample(TPlateExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int insert(TPlate record);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int insertSelective(TPlate record);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	List<TPlate> selectByExample(TPlateExample example);

	List<TPlate> selectByUserId(Map<String, Object> example);

	List<TPlate> selectByIdentityIdSchool(Integer schoolId);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	TPlate selectByPrimaryKey(String id);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByExampleSelective(@Param("record") TPlate record, @Param("example") TPlateExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByExample(@Param("record") TPlate record, @Param("example") TPlateExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByPrimaryKeySelective(TPlate record);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByPrimaryKey(TPlate record);

	/**
	 * @param classPlateParam
	 * @return
	 */
	List<TPlate> selectByClassId(Map<String, Object> classPlateParam);

	/**
	 * @param schoolParam
	 * @return
	 */
	List<TPlate> selectBySchoolId(Map<String, Object> schoolParam);

}