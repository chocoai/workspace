package com.whty.wfd.page.dao;

import com.whty.wfd.page.model.TBaseProperty;
import com.whty.wfd.page.model.TBasePropertyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TBasePropertyMapper {
	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	long countByExample(TBasePropertyExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int deleteByExample(TBasePropertyExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int insert(TBaseProperty record);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int insertSelective(TBaseProperty record);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	List<TBaseProperty> selectByExample(TBasePropertyExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	TBaseProperty selectByPrimaryKey(Integer id);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByExampleSelective(@Param("record") TBaseProperty record, @Param("example") TBasePropertyExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByExample(@Param("record") TBaseProperty record, @Param("example") TBasePropertyExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByPrimaryKeySelective(TBaseProperty record);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByPrimaryKey(TBaseProperty record);
}