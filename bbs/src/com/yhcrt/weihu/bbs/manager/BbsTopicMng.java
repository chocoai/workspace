package com.yhcrt.weihu.bbs.manager;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.yhcrt.weihu.bbs.entity.BbsTopic;
import com.yhcrt.weihu.bbs.entity.BbsTopicCountEnum;
import com.yhcrt.weihu.bbs.entity.BbsUser;
import com.yhcrt.weihu.common.page.Page;
import com.yhcrt.weihu.common.page.Pagination;

public interface BbsTopicMng {

	/**
	 * 转发
	 * @param topicId
	 */
	public void forwordTopic(BbsTopic topic,Integer userId);
	
	/**
	 * 获取好友动态
	 * @param userId
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public Page<BbsTopic> getFriendDynamic(Integer userId,Integer pageSize,Integer pageNo);
	
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
	 * 获取帖子列表的分页数据
	 * @param pageSize
	 * @param pageNo
	 * @param forumId
	 * @param siteId
	 * @return
	 */
	public Page<BbsTopic> getPage(Integer pageSize,Integer pageNo,Integer forumId,Integer siteId,String type,String typeId);
	
	/**
	 * 获取首页中活动板块的帖子
	 * @param size
	 * @param forumId
	 * @return
	 */
	public List<BbsTopic> getActiveIndex(Integer size,Integer forumId,Boolean isImage);
	
	/**
	 * 获取首页中精彩帖子的信息
	 * @param size  获取的条数
	 * @return  返回的结果，若数据够，那么就是固定的条数，否则，不足固定的条数
	 */
	public List<BbsTopic> getTopicIndex(Integer size);
	
	public void move(Integer[] ids, Integer forumId, String reason,
			BbsUser operator);

	public void shieldOrOpen(Integer[] ids, boolean shield, String reason,
			BbsUser operator);

	public void lockOrOpen(Integer[] ids, boolean lock, String reason,
			BbsUser operator);

	public void upOrDown(Integer[] ids, Date time, String reason,
			BbsUser operator);

	public void prime(Integer[] ids, short primeLevel, String reason,
			BbsUser operator);

	public void upTop(Integer[] ids, short topLevel, String reason,
			BbsUser operator);

	public void highlight(Integer[] ids, String color, boolean bold,
			boolean italic, Date time, String reason, BbsUser operator);
	
	public void highlightWithNoLog(Integer[] ids, String color, boolean bold,
			boolean italic, Date time, String reason, BbsUser operator);

	public BbsTopic updateTitle(Integer id, String title, BbsUser editor);

	public BbsTopic postTopic(Integer userId, Integer siteId, Integer forumId,
			Integer postTypeId, String title, String content, String ip,
			Integer category, Integer categoryType,String[] name, List<MultipartFile> file,
			List<String> code,String titleImg,Integer allayReply);
	
	public BbsTopic postTopic(Integer userId, Integer siteId, Integer forumId,Integer postTypeId,
			String title,String ip);

	public Pagination getForTag(Integer siteId, Integer forumId,
			Integer parentPostTypeId, Integer postTypeId, Short status,
			Short primeLevel, String keyWords, String creater,
			Integer createrId, Short topLevel, int pageNo, int pageSize,
			String jinghua);

	public Pagination getMemberTopic(Integer webId, Integer memberId,
			int pageNo, int pageSize);

	public Pagination getMemberReply(Integer webId, Integer memberId,
			int pageNo, int pageSize);
	
	public List<BbsTopic> getMemberReply(Integer siteId, Integer userId,
			Integer first,Integer count);

	public Pagination getForSearchDate(Integer siteId, Integer forumId,
			Short primeLevel, Integer day, int pageNo, int pageSize);

	public Pagination getPage(int pageNo, int pageSize);

	public BbsTopic findById(Integer id);

	public BbsTopic save(BbsTopic bean);

	public BbsTopic update(BbsTopic bean);

	public BbsTopic deleteById(Integer id);

	public BbsTopic[] deleteByIds(Integer[] ids);

	public List<BbsTopic> getList(Integer forumId,String keywords,Integer userId,Integer first,Integer count);

	public List<BbsTopic> getNewList(Integer first,Integer count,Integer orderby);
	
	public List<BbsTopic> getTopList(Short topLevel,Integer count,Integer orderby);
	
	public List<BbsTopic> getTopicList();
	
	public void updateAllTopicCount(BbsTopicCountEnum e);

	/**
	 * 
	 * @param tid
	 *            主题id
	 * @param magicName
	 *            道具名称
	 */
	public String useMagic(HttpServletRequest request, Integer siteId,
			Integer tid, String magicName, Integer userId, String ip,
			String color, Integer postCreaterId);
	
	public List<BbsTopic> getTopicList(Integer userId,Integer bigId,Integer smallId,Integer count);
}
