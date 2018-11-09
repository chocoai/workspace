package com.whty.wfd.page.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.whty.wfd.page.model.TPlatePost;
import com.whty.wfd.page.model.TPlatePostExample;

public interface TPlatePostMapper {
	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	long countByExample(TPlatePostExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int deleteByExample(TPlatePostExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int deleteByPrimaryKey(String id);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int insert(TPlatePost record);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int insertSelective(TPlatePost record);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	List<TPlatePost> selectByExampleWithBLOBs(TPlatePostExample example);

	/**
	 * 查询帖子
	 * 
	 * @param param
	 * @return
	 */
	List<TPlatePost> selectPlatePost(Map<String, Object> param);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	List<TPlatePost> selectByExample(TPlatePostExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	TPlatePost selectByPrimaryKey(String id);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByExampleSelective(@Param("record") TPlatePost record, @Param("example") TPlatePostExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByExampleWithBLOBs(@Param("record") TPlatePost record, @Param("example") TPlatePostExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByExample(@Param("record") TPlatePost record, @Param("example") TPlatePostExample example);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByPrimaryKeySelective(TPlatePost record);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByPrimaryKeyWithBLOBs(TPlatePost record);

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	int updateByPrimaryKey(TPlatePost record);

	List<TPlatePost> getPostByPlateId(@Param("plateId") String plateId, @Param("userId") Integer userId,
			@Param("orderByClause") Integer orderByClause, @Param("pageNum") Integer pageNum,
			@Param("pageSize") Integer pageSize);

	List<TPlatePost> getPostByPlateIdAllTeacher(@Param("plateId") String plateId, @Param("userId") Integer userId,
			@Param("orderByClause") Integer orderByClause, @Param("pageNum") Integer pageNum,
			@Param("pageSize") Integer pageSize);

	List<TPlatePost> getPostByPlateIdAll(@Param("plateId") String plateId, @Param("userId") Integer userId,
			@Param("orderByClause") Integer orderByClause, @Param("schoolId") Integer schoolId,
			@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

	List<TPlatePost> getPostByPlateId1(@Param("plateId") String plateId, @Param("userId") Integer userId,
			@Param("orderByClause") Integer orderByClause, @Param("pageNum") Integer pageNum,
			@Param("pageSize") Integer pageSize);

	List<TPlatePost> getPostByPlateIdAllTeacher2(@Param("plateId") String plateId, @Param("userId") Integer userId,
			@Param("orderByClause") Integer orderByClause, @Param("pageNum") Integer pageNum,
			@Param("pageSize") Integer pageSize);

	List<TPlatePost> getPostByPlateIdAll3(@Param("plateId") String plateId, @Param("userId") Integer userId,
			@Param("orderByClause") Integer orderByClause, @Param("schoolId") Integer schoolId,
			@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

	List<TPlatePost> getPostByPlateIdIsTop(@Param("plateId") String plateId, @Param("userId") Integer userId,
			@Param("orderByClause") String orderByClause);

	List<TPlatePost> getPostByPlateIdAllTeacherIsTop(@Param("plateId") String plateId, @Param("userId") Integer userId,
			@Param("orderByClause") String orderByClause);

	List<TPlatePost> getPostByPlateIdAllIsTop(@Param("plateId") String plateId, @Param("userId") Integer userId,
			@Param("orderByClause") String orderByClause);

	List<TPlatePost> getPostByUserId(@Param("userId") Integer userId, @Param("pageNum") Integer pageNum,
			@Param("pageSize") Integer pageSize);

	TPlatePost getPostByPostId(@Param("postId") String postId, @Param("userId") Integer userId);

	/**
	 * @param platePostParam
	 * @return
	 */
	List<TPlatePost> getPostByPlateList(Map<String, Object> platePostParam);

	// 标识删除
	int updateByPlateId(String plateId);

	List<String> getPostIdsByPlateId(String plateId);

	List<TPlatePost> getHostPost(@Param("userId") Integer userId,@Param("schoolId") Integer schoolId);
}