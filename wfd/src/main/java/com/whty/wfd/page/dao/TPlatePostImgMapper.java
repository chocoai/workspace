package com.whty.wfd.page.dao;

import com.whty.wfd.page.model.TPlatePostImg;
import com.whty.wfd.page.model.TPlatePostImgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TPlatePostImgMapper {
	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	long countByExample(TPlatePostImgExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int deleteByExample(TPlatePostImgExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int insert(TPlatePostImg record);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int insertSelective(TPlatePostImg record);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	List<TPlatePostImg> selectByExample(TPlatePostImgExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	TPlatePostImg selectByPrimaryKey(Integer id);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByExampleSelective(@Param("record") TPlatePostImg record, @Param("example") TPlatePostImgExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByExample(@Param("record") TPlatePostImg record, @Param("example") TPlatePostImgExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByPrimaryKeySelective(TPlatePostImg record);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByPrimaryKey(TPlatePostImg record);

	int insertManyImg(List<TPlatePostImg> tPlatePostImgs);
}