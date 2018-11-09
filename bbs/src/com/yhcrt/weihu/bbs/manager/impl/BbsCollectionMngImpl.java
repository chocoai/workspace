package com.yhcrt.weihu.bbs.manager.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yhcrt.weihu.bbs.dao.BbsCollectionDao;
import com.yhcrt.weihu.bbs.entity.BbsCollection;
import com.yhcrt.weihu.bbs.manager.BbsCollectionMng;
import com.yhcrt.weihu.bbs.manager.BbsForumMng;
import com.yhcrt.weihu.bbs.manager.BbsTopicMng;
import com.yhcrt.weihu.bbs.manager.BbsUserMng;
import com.yhcrt.weihu.common.page.Page;

@Service
@Transactional
public class BbsCollectionMngImpl implements BbsCollectionMng {

	@Override
	public void deleteById(Integer id) {
		if(id == null){
			return ;
		}
		dao.deleteById(id);
	}
	
	@Override
	public List<BbsCollection> getListByTopicOrForum(Integer topicId, Integer forumId) {
		return dao.getListByTopicOrForum(topicId, forumId);
	}

	@Override
	public int deleteByTopicOrForum(Integer topicId, Integer forumId) {
		if(topicId == null && forumId == null){
			return 0;
		}
		return 0;
	}

	@Override
	public int deleteByIds(Integer[] ids) {
		if(ids==null){
			return -1;
		}
		return dao.deleteByIds(ids);
	}
	
	@Override
	public void deleteByNotId(Integer userId, Integer forumId, Integer topicId) {
		if(userId == null){
			return ;
		}
		if(forumId == null && topicId == null){
			return ;
		}
		dao.deleteByNotId(userId, forumId, topicId);
	}

	@Override
	public int isCollection(Integer userId, Integer forumId, Integer topicId) {
		if(userId == null){
			return 0;
		}
		if(forumId == null && topicId == null){
			return 0;
		}
		return dao.isCollection(userId, forumId, topicId);
	}
	
	@Override
	public Page<BbsCollection> getPage(Integer pageSize, Integer pageNo, Integer userId,String type) {
		return dao.getPage(pageSize, pageNo, userId,type);
	}

	@Override
	public void save(Integer userId, Integer forumId, Integer topicId) {
		BbsCollection collection = new BbsCollection();
		collection.setCreater(bbsUserMng.findById(userId));
		collection.setCreateTime(new Timestamp(System.currentTimeMillis()));
		if(forumId == null){
			collection.setForum(null);
		}else{
			collection.setForum(bbsForumMng.findById(forumId));
		}
		if(topicId == null){
			collection.setTopic(null);
		}else{
			collection.setTopic(bbsTopicMng.findById(topicId));
		}
		dao.save(collection);
	}

	private BbsCollectionDao dao;
	private BbsUserMng bbsUserMng;
	private BbsForumMng bbsForumMng;
	private BbsTopicMng bbsTopicMng;
	
	@Autowired
	public void setDao(BbsCollectionDao dao){
		this.dao = dao;
	}
	@Autowired
	public void setBbsUserMng(BbsUserMng bbsUserMng) {
		this.bbsUserMng = bbsUserMng;
	}
	@Autowired
	public void setBbsForumMng(BbsForumMng bbsForumMng){
		this.bbsForumMng = bbsForumMng;
	}
	@Autowired
	public void setBbsTopicMng(BbsTopicMng bbsTopicMng){
		this.bbsTopicMng = bbsTopicMng;
	}

}
