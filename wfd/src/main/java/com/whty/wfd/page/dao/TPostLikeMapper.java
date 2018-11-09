package com.whty.wfd.page.dao;

import com.whty.wfd.page.model.TPostLike;
import com.whty.wfd.page.model.TPostLikeExample;
import com.whty.wfd.page.model.TPostLikeKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TPostLikeMapper {
	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	long countByExample(TPostLikeExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int deleteByExample(TPostLikeExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int deleteByPrimaryKey(TPostLikeKey key);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int insert(TPostLike record);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int insertSelective(TPostLike record);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	List<TPostLike> selectByExample(TPostLikeExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	TPostLike selectByPrimaryKey(TPostLikeKey key);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByExampleSelective(@Param("record") TPostLike record, @Param("example") TPostLikeExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByExample(@Param("record") TPostLike record, @Param("example") TPostLikeExample example);

	void updateByUserId(@Param("userId") Integer userId);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByPrimaryKeySelective(TPostLike record);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByPrimaryKey(TPostLike record);

	List<TPostLike> selectByUserId(Integer userId);
}