package com.yhcrt.weihu.bbs.manager;

import java.util.List;

import com.yhcrt.weihu.bbs.entity.BbsCollection;
import com.yhcrt.weihu.common.page.Page;

public interface BbsCollectionMng {

	/**
	 * 根据topicId或forumId查找收藏记录
	 * @param topicId
	 * @param forumId
	 * @return
	 */
	public List<BbsCollection> getListByTopicOrForum(Integer topicId,Integer forumId);
	
	/**
	 * 根据topicId或forumId删除收藏记录
	 * 此方法用于在删除帖子的时候将这些帖子的收藏记录也删掉
	 * @param topicId
	 * @param forumId
	 * @return
	 */
	public int deleteByTopicOrForum(Integer topicId,Integer forumId);
	
	/**
	 * 根据用户ID和收藏的ID批量删除
	 * @param userId
	 * @param ids
	 * @return
	 */
	public int deleteByIds(Integer[] ids);
	
	/**
	 * 根据用户ID和收藏的ID单个删除
	 * @param userId
	 * @param ids
	 * @return
	 */
	public void deleteById(Integer id);
	
	/**
	 * 取消收藏
	 * @param userId
	 * @param forumId
	 * @param topicId
	 */
	public void deleteByNotId(Integer userId,Integer forumId,Integer topicId);
	
	/**
	 * 判断是否已收藏
	 * @param userId
	 * @param forumId
	 * @param topicId
	 * @return
	 */
	public int isCollection(Integer userId,Integer forumId,Integer topicId);
	
	/**
	 * 保存收藏记录
	 * @param userId
	 * @param forumId
	 * @param topicId
	 */
	public void save(Integer userId,Integer forumId,Integer topicId);
	
	/**
	 * 获取收藏列表
	 * @param pageSize
	 * @param pageNo
	 * @param userId
	 * @return
	 */
	public Page<BbsCollection> getPage(Integer pageSize,Integer pageNo,Integer userId,String type);
	
}
