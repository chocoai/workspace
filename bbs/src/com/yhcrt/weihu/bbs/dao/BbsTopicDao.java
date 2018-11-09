package com.yhcrt.weihu.bbs.dao;

import java.util.List;

import com.yhcrt.weihu.bbs.entity.BbsTopic;
import com.yhcrt.weihu.bbs.entity.BbsTopicCountEnum;
import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Page;
import com.yhcrt.weihu.common.page.Pagination;

public interface BbsTopicDao {
	
	public Page<BbsTopic> getFriendDynamic(List<Integer> friendIds,Integer pageSize,Integer pageNo);
	
	/**
	 * 获取用户的帖子
	 * @param userId
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public Page<BbsTopic> getPage(Integer userId,Integer pageSize,Integer pageNo);
	
	/**
	 * 获取首页中图片帖子
	 * @param size
	 * @return
	 */
	public List<BbsTopic> getIndexNewsList(Integer size);
	
	/**
	 * 获取帖子列表分页
	 * @param pageSize
	 * @param pageNo
	 * @param forumId
	 * @param siteId
	 * @return
	 */
	public Page<BbsTopic> getPage(Integer pageSize,Integer pageNo,Integer forumId,Integer siteId,String type,String typeId);
	
	/**
	 * 获取首页指定板块的帖子列表
	 * @param size  获取数量
	 * @param forumId   板块ID
	 * @param isImage   是否带图片
	 * @return
	 */
	public List<BbsTopic> getTopicIndexActive(Integer size,Integer forumId,Boolean isImage);
	
	/**
	 * 获取首页中精彩帖子的列表
	 * @param size   获取的条数
	 * @return   返回的结果的条数并不是固定的,可能没有这么多条
	 */
	public List<BbsTopic> getTopicIndex(Integer size);
	
	/**
	 * 查找置顶贴
	 * 
	 * @param webId
	 * @param ctgId
	 * @param forumId
	 * @return
	 */
	public List<BbsTopic> getTopTopic(Integer webId, Integer ctgId,
			Integer forumId);

	/**
	 * 获得主题分页
	 * 
	 * @param webId
	 *            站点ID
	 * @param forumId
	 *            板块ID
	 * @param postTypeId
	 *            帖子子类ID
	 * @param parentPostTypeId
	 *            帖子父类ID           
	 * @param prime
	 *            是否精华
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination getForTag(Integer siteId, Integer forumId,Integer parentPostTypeId, Integer postTypeId,Short status,
			Short primeLevel, String keyWords, String creater,
			Integer createrId, Short topLevel, int pageNo, int pageSize,String jinghua);

	/**
	 * 获取会员发表的主题
	 * 
	 * @param webId
	 * @param memberId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination getMemberTopic(Integer webId, Integer memberId,
			int pageNo, int pageSize);

	/**
	 * 获取会员已回复的主题
	 * 
	 * @param webId
	 * @param memberId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination getMemberReply(Integer webId, Integer memberId,
			int pageNo, int pageSize);
	
	public List<BbsTopic> getMemberReply(Integer siteId, Integer userId,
			Integer first,Integer count);

	/**
	 * 获取最近回复主题
	 * 
	 * @param webId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination getTopicByTime(Integer webId, int pageNo, int pageSize);

	public Pagination getPage(int pageNo, int pageSize);

	public BbsTopic findById(Integer id);

	public BbsTopic save(BbsTopic bean);

	public BbsTopic updateByUpdater(Updater<BbsTopic> updater);

	public BbsTopic deleteById(Integer id);

	public Pagination getForSearchDate(Integer siteId, Integer forumId,
			Short primeLevel, Integer day, int pageNo, int pageSize);

	public List<BbsTopic> getList(Integer forumId,String keywords,Integer userId,Integer first,Integer count);
	
	public List<BbsTopic> getNewList(Integer first,Integer count,Integer orderby);
	
	public List<BbsTopic> getTopList(Short topLevel,Integer count,Integer orderby);

	public List<BbsTopic> getTopicList();
	
	public void updateAllTopicCount(BbsTopicCountEnum e);
	
	public List<BbsTopic> getTopicList(Integer userId,Integer bigId,Integer smallId,Integer count);
}