package com.whty.wfd.page.service;

import com.github.pagehelper.PageInfo;
import com.whty.wfd.page.model.*;
import org.omg.CORBA.INTERNAL;

import java.util.List;

public interface PostService {

	// 获取该用户下所有发布的帖子
	List<TPlatePost> getPostByUserId(Integer userId, Integer pageNum, Integer pageSize);

	// 获取该用户下所有发布的帖子的总页数
	Integer getTotal(Integer userId);

	// 发帖
	void addPost(TUser tUser, TPlatePost tPlatePost, List<TPlatePostImg> tPlatePostImgs, Integer[] userIds);

	// 根据模板Id获取帖子分页
	List<TPlatePost> getPostByPlateIdAllPage(String plateId, Integer pageNum, Integer pageSize, Integer userId,
											 Integer orderByClause);

	List<TPlatePost> getPostByPlateIdAllTeacherPage(String plateId, Integer pageNum, Integer pageSize, Integer userId,
													Integer orderByClause);

	List<TPlatePost> getPostByPlateIdAllMasterPage(String plateId, Integer pageNum, Integer pageSize, Integer userId,
												   Integer orderByClause, Integer schoolId);

	// 根据模板Id获取帖子所有置顶的帖子
	List<TPlatePost> getPostByPlateIdAllPage1(String plateId, Integer pageNum, Integer pageSize, Integer userId,
											  Integer orderByClause);

	List<TPlatePost> getPostByPlateIdAllTeacherPage2(String plateId, Integer pageNum, Integer pageSize, Integer userId,
													 Integer orderByClause);

	List<TPlatePost> getPostByPlateIdAllMasterPage3(String plateId, Integer pageNum, Integer pageSize, Integer userId,
													Integer orderByClause, Integer schoolId);

	List<TPlatePost> getPostByPlateIdAll(String plateId, Integer userId, String orderByClause);

	List<TPlatePost> getPostByPlateIdAllTeacher(String plateId, Integer userId, String orderByClause);

	List<TPlatePost> getPostByPlateIdAllMaster(String plateId, Integer userId, String orderByClause);

	// 根据帖子ID获取帖子详情
	TPlatePost getPostByPostId(String postId, Integer userId);

	// 置顶帖子与取消
	int updatePostByPostId(TPlatePost tPlatePost);

	// 表示删除帖子,并删除下面所有评论
	int deletePostByPostId(TPlatePost tPlatePost);

	// 保存发帖内容
	int savePostContent(Integer userId, String content, String plateId, String[] imgUrls, String[] imgHttpUrls,
			String userIds);

	// 保存发帖内容
	int deletePostContent(String id);

	// 得到备用帖子内容
	TPlatePostEdit getTPlatePostEditByUserId(Integer userId);

	// 得到备用帖子图片
	List<TPlatePostEditImg> getTPlatePostEditImgByUserId(Integer userId);

	TPlatePost selectByKey(String postId);

	//获取热门帖子
	List<TPlatePost> getHostPost(Integer userId,Integer schoolId);
}
