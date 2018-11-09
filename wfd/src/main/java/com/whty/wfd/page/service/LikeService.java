package com.whty.wfd.page.service;

import com.github.pagehelper.PageInfo;
import com.whty.wfd.page.model.TPostLike;
import com.whty.wfd.page.model.TPostLikeKey;
import com.whty.wfd.page.model.TPostMessageLike;
import com.whty.wfd.page.model.TPostMessageLikeKey;
import com.whty.wfd.page.vo.Like;

import java.util.List;

/**
 * \* User: zjd \* Date: 2018/8/17 \* Time: 16:01 \* Description: \
 */
public interface LikeService {

	// 查询该用户帖子、评论点赞
	public PageInfo<Like> getLikes(Integer userId, Integer pageNum, Integer pageSize);

	// 添加评论点赞
	int addMessageLike(TPostMessageLike tPostMessageLike);

	// 删除评论点赞
	int deleteMessageLike(TPostMessageLikeKey key);

	// 查询该评论点赞数量
	int getMessageLikeCountByMessageId(String messageId);

	// 添加帖子点赞
	int addPostLike(TPostLike tPostLike);

	// 删除帖子点赞
	int deletePostLike(TPostLikeKey key);

	// 查询该帖子点赞数量
	int getPostLikeCountByPostId(String PostId);

	// 所有改用户未读改为已读写
	void updateLikeIsRead(Integer userId);

	int updateLikeIsReadById(String id, Integer userId, boolean type);
}