package com.whty.wfd.page.service;

import com.whty.wfd.page.model.TUserFavoriteMessageKey;
import com.whty.wfd.page.model.TUserFavoritePost;
import com.whty.wfd.page.model.TUserFavoritePostKey;
import com.whty.wfd.page.vo.Collection;

import java.util.List;

public interface CollectionService {

	// 查询个人中心该用户所有收藏的帖子
	List<Collection> getCollectionPost(Integer userId);

	// 查看某个帖子by userID和postId
	List<TUserFavoritePost> getCollectionPostByUserIDPostId(Integer userId, String PostId);

	// 添加帖子收藏
	Integer addCollectionPost(Integer userId, String PostId);

	// 添加帖子收藏
	Integer deleteCollectionPost(Integer userId, String PostId);

	// 添加评论收藏
	Integer addCollectionMessage(Integer userId, String messageId);

	// 删除多个
	Integer deleteCollectionList(List<TUserFavoritePostKey> keys, List<TUserFavoriteMessageKey> mKeys);

	/**
	 * @param id
	 * @param messageId
	 * @return
	 */
	Integer deleteCollectionMessage(Integer id, String messageId);
}