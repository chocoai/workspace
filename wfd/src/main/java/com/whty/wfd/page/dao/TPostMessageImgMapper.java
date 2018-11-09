package com.whty.wfd.page.dao;

import com.whty.wfd.page.model.TPostMessageImg;
import com.whty.wfd.page.model.TPostMessageImgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TPostMessageImgMapper {
	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	long countByExample(TPostMessageImgExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int deleteByExample(TPostMessageImgExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int insert(TPostMessageImg record);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int insertSelective(TPostMessageImg record);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	List<TPostMessageImg> selectByExample(TPostMessageImgExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	TPostMessageImg selectByPrimaryKey(Integer id);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByExampleSelective(@Param("record") TPostMessageImg record,
			@Param("example") TPostMessageImgExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByExample(@Param("record") TPostMessageImg record, @Param("example") TPostMessageImgExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByPrimaryKeySelective(TPostMessageImg record);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByPrimaryKey(TPostMessageImg record);

	/**
	 * 批量添加图片
	 */
	int insertManyImg(List<TPostMessageImg> tPostMessageImgs);
}