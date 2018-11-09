package com.whty.wfd.page.dao;

import com.whty.wfd.page.model.TClassPlate;
import com.whty.wfd.page.model.TClassPlateExample;
import com.whty.wfd.page.model.TClassPlateKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TClassPlateMapper {
	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	long countByExample(TClassPlateExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int deleteByExample(TClassPlateExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int deleteByPrimaryKey(TClassPlateKey key);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int insert(TClassPlate record);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int insertSelective(TClassPlate record);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	List<TClassPlate> selectByExample(TClassPlateExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	TClassPlate selectByPrimaryKey(TClassPlateKey key);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByExampleSelective(@Param("record") TClassPlate record, @Param("example") TClassPlateExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByExample(@Param("record") TClassPlate record, @Param("example") TClassPlateExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByPrimaryKeySelective(TClassPlate record);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByPrimaryKey(TClassPlate record);
}