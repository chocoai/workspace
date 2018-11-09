package com.whty.wfd.page.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whty.wfd.page.dao.LikeMapper;
import com.whty.wfd.page.dao.TPostLikeMapper;
import com.whty.wfd.page.dao.TPostMessageLikeMapper;
import com.whty.wfd.page.model.*;
import com.whty.wfd.page.service.LikeService;
import com.whty.wfd.page.vo.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * \* User: zjd \* Date: 2018/8/17 \* Time: 16:06 \* Description: \
 */
@Service
public class LikeServiceImpl implements LikeService {

	@Autowired
	private LikeMapper likeMapper;
	@Autowired
	private TPostMessageLikeMapper tPostMessageLikeMapper;
	@Autowired
	private TPostLikeMapper tPostLikeMapper;

	@Override
	public PageInfo<Like> getLikes(Integer userId, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Like> likes = likeMapper.getLikes(userId);
		PageInfo<Like> p = new PageInfo<>(likes);
		return p;
	}

	@Override
	public int addMessageLike(TPostMessageLike tPostMessageLike) {
		return tPostMessageLikeMapper.insert(tPostMessageLike);
	}

	@Override
	public int deleteMessageLike(TPostMessageLikeKey key) {
		return tPostMessageLikeMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int getMessageLikeCountByMessageId(String messageId) {
		TPostMessageLikeExample example = new TPostMessageLikeExample();
		example.createCriteria().andPostMessageIdEqualTo(messageId);
		return (int) tPostMessageLikeMapper.countByExample(example);
	}

	@Override
	public int addPostLike(TPostLike tPostLike) {
		return tPostLikeMapper.insert(tPostLike);
	}

	@Override
	public int deletePostLike(TPostLikeKey key) {
		return tPostLikeMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int getPostLikeCountByPostId(String PostId) {
		TPostLikeExample example = new TPostLikeExample();
		example.createCriteria().andPlatePostIdEqualTo(PostId);
		return (int) tPostLikeMapper.countByExample(example);
	}

	@Override
	public void updateLikeIsRead(Integer userId) {
		tPostMessageLikeMapper.updateByUserId(userId);
		tPostLikeMapper.updateByUserId(userId);
	}

	@Override
	public int updateLikeIsReadById(String id, Integer userId, boolean type) {
		int result = 0;
		if (type) {
			TPostMessageLike tPostMessageLike = new TPostMessageLike();
			tPostMessageLike.setPostMessageId(id);
			tPostMessageLike.setUserId(userId);
			tPostMessageLike.setIsRead(true);
			tPostMessageLike.setUpdateTime(new Date());
			result = tPostMessageLikeMapper.updateByPrimaryKeySelective(tPostMessageLike);
		} else {
			TPostLike tPostLike = new TPostLike();
			tPostLike.setUserId(userId);
			tPostLike.setPlatePostId(id);
			tPostLike.setIsRead(true);
			tPostLike.setUpdateTime(new Date());
			result = tPostLikeMapper.updateByPrimaryKeySelective(tPostLike);
		}
		return result;
	}
}