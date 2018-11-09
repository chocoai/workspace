package com.whty.wfd.page.dao;

import com.whty.wfd.page.model.TClassUser;
import com.whty.wfd.page.model.TClassUserExample;
import com.whty.wfd.page.model.TClassUserKey;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TClassUserMapper {
	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	long countByExample(TClassUserExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int deleteByExample(TClassUserExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int deleteByPrimaryKey(TClassUserKey key);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int insert(TClassUser record);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int insertSelective(TClassUser record);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	List<TClassUser> selectByExample(TClassUserExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	TClassUser selectByPrimaryKey(TClassUserKey key);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByExampleSelective(@Param("record") TClassUser record, @Param("example") TClassUserExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByExample(@Param("record") TClassUser record, @Param("example") TClassUserExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByPrimaryKeySelective(TClassUser record);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByPrimaryKey(TClassUser record);

	/**
	 * 批量添加
	 */
	int insertMany(@Param("ClassId") Integer ClassId, @Param("userId") Integer[] userId,
			@Param("createTime") Date createTime);
}