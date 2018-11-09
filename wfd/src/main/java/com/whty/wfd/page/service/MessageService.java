package com.whty.wfd.page.service;

import com.github.pagehelper.PageInfo;
import com.whty.wfd.page.model.TPostMessage;
import com.whty.wfd.page.model.TPostMessageAudio;
import com.whty.wfd.page.model.TPostMessageImg;
import com.whty.wfd.page.model.TUser;

import java.util.List;

public interface MessageService {

	// 发表评论
	void addMessage(TUser tUser, TPostMessage tPostMessage, List<TPostMessageImg> tPostMessageImgs, Integer[] userIds,
			List<TPostMessageAudio> tPostMessageAudios);

	// 语音发表评论
	void addMessageAudio(TUser tUser, TPostMessage tPostMessage, TPostMessageAudio tPostMessageAudio);

	// 查看该帖子下的所有评论
	List<TPostMessage> getPostMessagesByPostId(String postId, Integer userId, String orderByClause);

	// 个人中心查询该用户下的所有帖子的评论
	PageInfo<TPostMessage> getPostMessagesByUserId(Integer userId, Integer pageNum, Integer pageSize);

	// 标识删除帖子
	int updateByMessageId(String messageId);

	// 设置最佳答案
	int updateByMessageIdAnswer(String messageId, boolean type);

	// 修改单个ID message的未读状态为已读
	int updateByMessageIdIsRead(String messageId, boolean isRead);

	// 修改该用户所有的IsRead
	void updateMessageIsRead(Integer userId);

	// 查看@我的
	List<TPostMessage> getReply(Integer userId);

	// 改变所有@我的状态为true
	int updateIsRead(Integer userId);
}