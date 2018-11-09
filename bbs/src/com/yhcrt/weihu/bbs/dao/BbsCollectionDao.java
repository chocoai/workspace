package com.yhcrt.weihu.bbs.dao;

import java.util.List;

import com.yhcrt.weihu.bbs.entity.BbsCollection;
import com.yhcrt.weihu.common.page.Page;

public interface BbsCollectionDao {
	
	public List<BbsCollection> getListByTopicOrForum(Integer topicId,Integer forumId);
	public int deleteByTopicOrForum(Integer topicId,Integer forumId);
	public int deleteByIds(Integer[] ids);
	public void deleteByNotId(Integer userId,Integer forumId,Integer topicId);
	public int isCollection(Integer userId,Integer forumId,Integer topicId);
	public Page<BbsCollection> getPage(Integer pageSize,Integer pageNo,Integer userId,String type);
	public void save(BbsCollection bean);
	public BbsCollection findById(Integer id);
	public void deleteById(Integer id);
	
}
