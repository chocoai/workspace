package com.whty.wfd.page.dao;

import com.whty.wfd.page.model.TPostMessage;
import com.whty.wfd.page.model.TPostMessageExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TPostMessageMapper {
	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	long countByExample(TPostMessageExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int deleteByExample(TPostMessageExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int insert(TPostMessage record);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int insertSelective(TPostMessage record);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	List<TPostMessage> selectByExampleWithBLOBs(TPostMessageExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	List<TPostMessage> selectByExample(TPostMessageExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	TPostMessage selectByPrimaryKey(String id);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByExampleSelective(@Param("record") TPostMessage record, @Param("example") TPostMessageExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByExampleWithBLOBs(@Param("record") TPostMessage record, @Param("example") TPostMessageExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByExample(@Param("record") TPostMessage record, @Param("example") TPostMessageExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByPrimaryKeySelective(TPostMessage record);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByPrimaryKeyWithBLOBs(TPostMessage record);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByPrimaryKey(TPostMessage record);

	/**
	 * @param postMessage
	 * @return
	 */
	List<TPostMessage> selectByPlatePost(Map<String, Object> postMessage);

	List<TPostMessage> getPostMessagesByPostId(@Param("postId") String postId, @Param("userId") Integer userId,
			@Param("orderByClause") String orderByClause);

	List<TPostMessage> getPostMessagesByUserId(Integer userId);

	// 标识删除
	int updateByPostId(String postId);

	// 批量标识删除
	int updateByPostIds(List<String> postIds);

	// 标识删除
	int updateByMessageId(String messageId);

	void updateMessageIsRead(Integer userId);

	// 得到所有@我的
	List<TPostMessage> getReply(Integer userId);
}