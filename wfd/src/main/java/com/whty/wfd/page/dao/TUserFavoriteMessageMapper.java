package com.whty.wfd.page.dao;

import com.whty.wfd.page.model.TUserFavoriteMessage;
import com.whty.wfd.page.model.TUserFavoriteMessageExample;
import com.whty.wfd.page.model.TUserFavoriteMessageKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TUserFavoriteMessageMapper {
	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	long countByExample(TUserFavoriteMessageExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int deleteByExample(TUserFavoriteMessageExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int deleteByPrimaryKey(TUserFavoriteMessageKey key);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int insert(TUserFavoriteMessage record);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int insertSelective(TUserFavoriteMessage record);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	List<TUserFavoriteMessage> selectByExample(TUserFavoriteMessageExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	TUserFavoriteMessage selectByPrimaryKey(TUserFavoriteMessageKey key);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByExampleSelective(@Param("record") TUserFavoriteMessage record,
			@Param("example") TUserFavoriteMessageExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByExample(@Param("record") TUserFavoriteMessage record,
			@Param("example") TUserFavoriteMessageExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByPrimaryKeySelective(TUserFavoriteMessage record);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByPrimaryKey(TUserFavoriteMessage record);
}