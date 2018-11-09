package com.whty.wfd.page.dao;

import com.whty.wfd.page.model.TPlatePostEditImg;
import com.whty.wfd.page.model.TPlatePostEditImgExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TPlatePostEditImgMapper {
	/**
	 *
	 * @mbg.generated 2018-09-27
	 */
	long countByExample(TPlatePostEditImgExample example);

	/**
	 *
	 * @mbg.generated 2018-09-27
	 */
	int deleteByExample(TPlatePostEditImgExample example);

	/**
	 *
	 * @mbg.generated 2018-09-27
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 *
	 * @mbg.generated 2018-09-27
	 */
	int insert(TPlatePostEditImg record);

	/**
	 *
	 * @mbg.generated 2018-09-27
	 */
	int insertSelective(TPlatePostEditImg record);

	/**
	 *
	 * @mbg.generated 2018-09-27
	 */
	List<TPlatePostEditImg> selectByExample(TPlatePostEditImgExample example);

	/**
	 *
	 * @mbg.generated 2018-09-27
	 */
	TPlatePostEditImg selectByPrimaryKey(Integer id);

	/**
	 *
	 * @mbg.generated 2018-09-27
	 */
	int updateByExampleSelective(@Param("record") TPlatePostEditImg record,
			@Param("example") TPlatePostEditImgExample example);

	/**
	 *
	 * @mbg.generated 2018-09-27
	 */
	int updateByExample(@Param("record") TPlatePostEditImg record, @Param("example") TPlatePostEditImgExample example);

	/**
	 *
	 * @mbg.generated 2018-09-27
	 */
	int updateByPrimaryKeySelective(TPlatePostEditImg record);

	/**
	 *
	 * @mbg.generated 2018-09-27
	 */
	int updateByPrimaryKey(TPlatePostEditImg record);

	int insertManyImg(List<TPlatePostEditImg> tPlatePostEditImgs);
}