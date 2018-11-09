package com.whty.wfd.page.service.impl;

import com.whty.wfd.page.dao.CollectionMapper;
import com.whty.wfd.page.dao.TUserFavoriteMessageMapper;
import com.whty.wfd.page.dao.TUserFavoritePostMapper;
import com.whty.wfd.page.model.*;
import com.whty.wfd.page.service.CollectionService;
import com.whty.wfd.page.vo.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {

	@Autowired
	private TUserFavoritePostMapper tUserFavoritePostMapper;
	@Autowired
	private TUserFavoriteMessageMapper TUserFavoriteMessageMapper;
	@Autowired
	private CollectionMapper collectionMapper;

	@Override
	public List<Collection> getCollectionPost(Integer userId) {
		return collectionMapper.getCollections(userId);
	}

	@Override
	public List<TUserFavoritePost> getCollectionPostByUserIDPostId(Integer userId, String PostId) {
		TUserFavoritePostExample example = new TUserFavoritePostExample();
		example.createCriteria().andUserIdEqualTo(userId).andPlatePostIdEqualTo(PostId);
		return tUserFavoritePostMapper.selectByExample(example);
	}

	@Override
	public Integer addCollectionPost(Integer userId, String PostId) {
		TUserFavoritePost tUserFavoritePost = new TUserFavoritePost();
		tUserFavoritePost.setUserId(userId);
		tUserFavoritePost.setPlatePostId(PostId);
		tUserFavoritePost.setCreateTime(new Date());
		tUserFavoritePost.setUpdateTime(new Date());
		return tUserFavoritePostMapper.insert(tUserFavoritePost);
	}

	@Override
	public Integer deleteCollectionPost(Integer userId, String PostId) {
		TUserFavoritePostExample example = new TUserFavoritePostExample();
		example.createCriteria().andUserIdEqualTo(userId).andPlatePostIdEqualTo(PostId);
		return tUserFavoritePostMapper.deleteByExample(example);
	}

	@Override
	public Integer addCollectionMessage(Integer userId, String messageId) {
		TUserFavoriteMessage TUserFavoriteMessage = new TUserFavoriteMessage();
		TUserFavoriteMessage.setUserId(userId);
		TUserFavoriteMessage.setPostMessageId(messageId);
		TUserFavoriteMessage.setCreateTime(new Date());
		TUserFavoriteMessage.setUpdateTime(new Date());
		return TUserFavoriteMessageMapper.insert(TUserFavoriteMessage);
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public Integer deleteCollectionList(List<TUserFavoritePostKey> keys, List<TUserFavoriteMessageKey> mKeys) {
		try {
			if (keys.size() > 0) {
				for (TUserFavoritePostKey key : keys) {
					tUserFavoritePostMapper.deleteByPrimaryKey(key);
				}
			}
			if (mKeys.size() > 0) {
				for (TUserFavoriteMessageKey key : mKeys) {
					TUserFavoriteMessageMapper.deleteByPrimaryKey(key);
				}
			}
			return 1;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.wfd.page.service.CollectionService#deleteCollectionMessage(java.
	 * lang.Integer, java.lang.String)
	 */
	@Override
	public Integer deleteCollectionMessage(Integer userId, String messageId) {
		TUserFavoriteMessage TUserFavoriteMessage = new TUserFavoriteMessage();
		TUserFavoriteMessage.setUserId(userId);
		TUserFavoriteMessage.setPostMessageId(messageId);

		TUserFavoriteMessageExample tUserFavoriteMessageExample = new TUserFavoriteMessageExample();
		tUserFavoriteMessageExample.createCriteria().andUserIdEqualTo(userId).andPostMessageIdEqualTo(messageId);
		return TUserFavoriteMessageMapper.deleteByExample(tUserFavoriteMessageExample);
	}

}
