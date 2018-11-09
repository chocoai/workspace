package com.whty.wfd.page.dao;

import com.whty.wfd.page.model.TUser;
import com.whty.wfd.page.model.TUserExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TUserMapper {
	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	long countByExample(TUserExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int deleteByExample(TUserExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int insert(TUser record);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int insertSelective(TUser record);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	List<TUser> selectByExample(TUserExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	TUser selectByPrimaryKey(Integer id);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByExampleSelective(@Param("record") TUser record, @Param("example") TUserExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByExample(@Param("record") TUser record, @Param("example") TUserExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByPrimaryKeySelective(TUser record);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByPrimaryKey(TUser record);

	/**
	 * @param classParam
	 * @return
	 */
	List<TUser> selectByClass(Map<String, Object> classParam);

	/**
	 * @param plateParam
	 * @return
	 */
	List<TUser> selectByPlate(Map<String, Object> plateParam);

	/**
	 * @param plateParam
	 * @return
	 */
	List<TUser> selectUserByPlate(Map<String, Object> plateParam);
	List<TUser> selectUserByPlateName(Map<String, Object> plateParam);

	/**
	 * @param platePostId
	 * @return
	 */
	TUser selectByPlatePostId(String platePostId);
}