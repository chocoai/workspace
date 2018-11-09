package com.whty.wfd.page.dao;

import com.whty.wfd.page.model.TPlatePostEdit;
import com.whty.wfd.page.model.TPlatePostEditExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TPlatePostEditMapper {
	/**
	 *
	 * @mbg.generated 2018-10-10
	 */
	long countByExample(TPlatePostEditExample example);

	/**
	 *
	 * @mbg.generated 2018-10-10
	 */
	int deleteByExample(TPlatePostEditExample example);

	/**
	 *
	 * @mbg.generated 2018-10-10
	 */
	int deleteByPrimaryKey(String id);

	/**
	 *
	 * @mbg.generated 2018-10-10
	 */
	int insert(TPlatePostEdit record);

	/**
	 *
	 * @mbg.generated 2018-10-10
	 */
	int insertSelective(TPlatePostEdit record);

	/**
	 *
	 * @mbg.generated 2018-10-10
	 */
	List<TPlatePostEdit> selectByExampleWithBLOBs(TPlatePostEditExample example);

	/**
	 *
	 * @mbg.generated 2018-10-10
	 */
	List<TPlatePostEdit> selectByExample(TPlatePostEditExample example);

	/**
	 *
	 * @mbg.generated 2018-10-10
	 */
	TPlatePostEdit selectByPrimaryKey(String id);

	/**
	 *
	 * @mbg.generated 2018-10-10
	 */
	int updateByExampleSelective(@Param("record") TPlatePostEdit record,
			@Param("example") TPlatePostEditExample example);

	/**
	 *
	 * @mbg.generated 2018-10-10
	 */
	int updateByExampleWithBLOBs(@Param("record") TPlatePostEdit record,
			@Param("example") TPlatePostEditExample example);

	/**
	 *
	 * @mbg.generated 2018-10-10
	 */
	int updateByExample(@Param("record") TPlatePostEdit record, @Param("example") TPlatePostEditExample example);

	/**
	 *
	 * @mbg.generated 2018-10-10
	 */
	int updateByPrimaryKeySelective(TPlatePostEdit record);

	/**
	 *
	 * @mbg.generated 2018-10-10
	 */
	int updateByPrimaryKeyWithBLOBs(TPlatePostEdit record);

	/**
	 *
	 * @mbg.generated 2018-10-10
	 */
	int updateByPrimaryKey(TPlatePostEdit record);
}