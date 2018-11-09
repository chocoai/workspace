package com.yhcrt.weihu.bbs.manager.impl;

import static com.yhcrt.weihu.bbs.entity.BbsTopic.TOPIC_VOTE;
import static com.yhcrt.weihu.bbs.entity.BbsTopic.TOPIC_VOTE_SINGLE;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.yhcrt.weihu.bbs.Constants;
import com.yhcrt.weihu.bbs.MagicConstants;
import com.yhcrt.weihu.bbs.cache.BbsConfigEhCache;
import com.yhcrt.weihu.bbs.dao.BbsTopicDao;
import com.yhcrt.weihu.bbs.entity.BbsCollection;
import com.yhcrt.weihu.bbs.entity.BbsCommonMagic;
import com.yhcrt.weihu.bbs.entity.BbsForum;
import com.yhcrt.weihu.bbs.entity.BbsPost;
import com.yhcrt.weihu.bbs.entity.BbsTopic;
import com.yhcrt.weihu.bbs.entity.BbsTopicCountEnum;
import com.yhcrt.weihu.bbs.entity.BbsTopicText;
import com.yhcrt.weihu.bbs.entity.BbsUser;
import com.yhcrt.weihu.bbs.entity.BbsUserGroup;
import com.yhcrt.weihu.bbs.entity.BbsVoteItem;
import com.yhcrt.weihu.bbs.entity.BbsVoteTopic;
import com.yhcrt.weihu.bbs.entity.BbsVoteTopicSingle;
import com.yhcrt.weihu.bbs.manager.BbsCollectionMng;
import com.yhcrt.weihu.bbs.manager.BbsCommonMagicMng;
import com.yhcrt.weihu.bbs.manager.BbsForumMng;
import com.yhcrt.weihu.bbs.manager.BbsFriendsMng;
import com.yhcrt.weihu.bbs.manager.BbsMagicConfigMng;
import com.yhcrt.weihu.bbs.manager.BbsOperationMng;
import com.yhcrt.weihu.bbs.manager.BbsPointDetailMng;
import com.yhcrt.weihu.bbs.manager.BbsPostMng;
import com.yhcrt.weihu.bbs.manager.BbsPostTypeMng;
import com.yhcrt.weihu.bbs.manager.BbsTopicMng;
import com.yhcrt.weihu.bbs.manager.BbsUserGroupMng;
import com.yhcrt.weihu.bbs.manager.BbsUserMng;
import com.yhcrt.weihu.bbs.manager.BbsVoteItemMng;
import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Page;
import com.yhcrt.weihu.common.page.Pagination;
import com.yhcrt.weihu.common.util.DateUtils;
import com.yhcrt.weihu.core.manager.CmsSiteMng;
import com.yhcrt.weihu.core.web.MagicMessage;

@Service
@Transactional
public class BbsTopicMngImpl implements BbsTopicMng {
	
	public static final String AUTH_KEY = "auth_key";

	@Override
	public void forwordTopic(BbsTopic topic,Integer userId){
		List<BbsVoteItem> voteItem = bbsVoteItemMng.findByTopic(topic.getId());
		String[] name = new String[voteItem.size()];
		for(int i=0; i<name.length; i++){
			name[i] = voteItem.get(i).getName();
		}
		Integer allayReply = null;
		if(topic.getAllayReply()){
			allayReply = 1;
		}else{
			allayReply = 0;
		}
		postTopic(userId, topic.getSite().getId(), topic.getForum().getId(), topic.getPostType().getId(),
				topic.getTitle(), topic.getFirstPost().getContent(), topic.getFirstPost().getPosterIp(),
				(int)topic.getCategory(), (int)topic.getCategory(), name, null, null, topic.getTitleImg(), allayReply);
	}
	
	@Override
	public Page<BbsTopic> getFriendDynamic(Integer userId, Integer pageSize, Integer pageNo) {
		List<Integer> friendIds = bbsFriendsMng.getMyFriendsId(userId);
		friendIds.add(userId);
		return dao.getFriendDynamic(friendIds, pageSize, pageNo);
	}

	@Override
	public Page<BbsTopic> getPage(Integer userId, Integer pageSize, Integer pageNo) {
		return dao.getPage(userId, pageSize, pageNo);
	}
	
	@Override
	public List<BbsTopic> getIndexNewsList(Integer size) {
		return dao.getIndexNewsList(size);
	}
	
	/**
	 * 获取帖子列表的分页数据
	 * @param pageSize
	 * @param pageNo
	 * @param forumId
	 * @param siteId
	 * @return
	 */
	public Page<BbsTopic> getPage(Integer pageSize,Integer pageNo,Integer forumId,Integer siteId,String type,String typeId){
		return dao.getPage(pageSize, pageNo, forumId, siteId,type,typeId);
	}
	
	/**
	 * 获取首页中活动板块的帖子
	 * @param size
	 * @param forumId
	 * @return
	 */
	public List<BbsTopic> getActiveIndex(Integer size,Integer forumId,Boolean isImage){
		return dao.getTopicIndexActive(size, forumId, null);
	}
	
	/**
	 * 获取首页中精彩帖子的信息
	 * @param size  获取的条数
	 * @return  返回的结果，若数据够，那么就是固定的条数，否则，不足固定的条数
	 */
	public List<BbsTopic> getTopicIndex(Integer size){
		return dao.getTopicIndex(size);
	}
	
	public void move(Integer[] ids, Integer forumId, String reason,
			BbsUser operator) {
		BbsTopic topic;
		BbsForum origForum;
		BbsForum currForum;

		for (Integer id : ids) {

			topic = dao.findById(id);
			origForum = topic.getForum();
			if (!origForum.getId().equals(forumId)) {
				currForum = bbsForumMng.findById(forumId);
				topic.setForum(currForum);
				origForum.setTopicTotal(origForum.getTopicTotal() - 1);
				currForum.setTopicTotal(currForum.getTopicTotal() + 1);
				if (origForum.getLastPost() != null) {
					if (origForum.getLastPost().getTopic().getId() == id) {
						BbsPost lastPost = bbsPostMng.getLastPost(origForum
								.getId(), 0);
						if (lastPost != null) {
							origForum.setLastPost(lastPost);
						} else {
							origForum.setLastPost(null);
						}
					}
				}
			}
			bbsOperationMng.saveOpt(topic.getSite(), operator, "移动主题", reason,
					topic);
		}
	}

	public void shieldOrOpen(Integer[] ids, boolean shield, String reason,
			BbsUser operator) {
		BbsTopic topic;
		for (Integer id : ids) {
			topic = dao.findById(id);
			short status = topic.getStatus();
			if (shield) {
				if (status == BbsTopic.NORMAL) {
					topic.setStatus(BbsTopic.SHIELD);
				}
				bbsOperationMng.saveOpt(topic.getSite(), operator, "屏蔽主题",
						reason, topic);
			} else {
				if (status == BbsTopic.SHIELD) {
					topic.setStatus(BbsTopic.NORMAL);
				}
				bbsOperationMng.saveOpt(topic.getSite(), operator, "解除主题",
						reason, topic);
			}
		}
	}

	public void lockOrOpen(Integer[] ids, boolean lock, String reason,
			BbsUser operator) {
		BbsTopic topic;
		for (Integer id : ids) {
			topic = dao.findById(id);
			short status = topic.getStatus();
			if (lock) {
				if (status == BbsTopic.NORMAL) {
					topic.setStatus(BbsTopic.LOCKED);
				}
				bbsOperationMng.saveOpt(topic.getSite(), operator, "锁定主题",
						reason, topic);
			} else {
				if (status == BbsTopic.LOCKED) {
					topic.setStatus(BbsTopic.NORMAL);
				}
				bbsOperationMng.saveOpt(topic.getSite(), operator, "解除主题",
						reason, topic);
			}
		}
	}

	public void upOrDown(Integer[] ids, Date time, String reason,
			BbsUser operator) {
		BbsTopic topic;
		for (Integer id : ids) {
			topic = dao.findById(id);
			topic.setSortTime(time);
			bbsOperationMng.saveOpt(topic.getSite(), operator, "提升/下沉主题",
					reason, topic);
		}
	}

	public void prime(Integer[] ids, short primeLevel, String reason,
			BbsUser operator) {
		BbsTopic topic;
		BbsUser toUser;
		BbsForum topicForum;
		for (Integer id : ids) {
			topic = dao.findById(id);
			topic.setPrimeLevel(primeLevel);
			toUser=topic.getCreater();
			topicForum=topic.getForum();
			if(primeLevel==1){
				toUser.setPrestige(toUser.getPrestige()+topicForum.getPrestigePrime1());
				toUser.setPrimeCount(toUser.getPrimeCount()+1);
			}else if(primeLevel==2){
				toUser.setPrestige(toUser.getPrestige()+topicForum.getPrestigePrime2());
				toUser.setPrimeCount(toUser.getPrimeCount()+1);
			}else if(primeLevel==3){
				toUser.setPrestige(toUser.getPrestige()+topicForum.getPrestigePrime3());
				toUser.setPrimeCount(toUser.getPrimeCount()+1);
			}else if(primeLevel==0){
				toUser.setPrestige(toUser.getPrestige()+topicForum.getPrestigePrime0());
				toUser.setPrimeCount(toUser.getPrimeCount()-1);
			}
			//加了精华 积分就加，去除精华后不取消
			toUser.setPoint(toUser.getPoint()+topicForum.getPointPrime());
			bbsOperationMng.saveOpt(topic.getSite(), operator, "精华", reason,
					topic);
		}
	}

	public void upTop(Integer[] ids, short topLevel, String reason,
			BbsUser operator) {
		BbsTopic topic;
		for (Integer id : ids) {
			topic = dao.findById(id);
			topic.setTopLevel(topLevel);
			bbsOperationMng.saveOpt(topic.getSite(), operator, "置顶", reason,
					topic);
		}
	}

	public void highlight(Integer[] ids, String color, boolean bold,
			boolean italic, Date time, String reason, BbsUser operator) {
		BbsTopic topic;
		for (Integer id : ids) {
			topic = dao.findById(id);
			topic.setStyleColor(color);
			topic.setStyleTime(time);
			topic.setStyleBold(bold);
			topic.setStyleItalic(italic);
			bbsOperationMng.saveOpt(topic.getSite(), operator, "高亮", reason,
					topic);
		}
	}
	
	public void highlightWithNoLog(Integer[] ids, String color, boolean bold,
			boolean italic, Date time, String reason, BbsUser operator) {
		BbsTopic topic;
		for (Integer id : ids) {
			topic = dao.findById(id);
			topic.setStyleColor(color);
			topic.setStyleTime(time);
			topic.setStyleBold(bold);
			topic.setStyleItalic(italic);
		}
	}

	public BbsTopic updateTitle(Integer id, String title, BbsUser editor) {
		BbsTopic topic = dao.findById(id);
		topic.setTitle(title);
		bbsOperationMng.saveOpt(topic.getSite(), editor, "修改主题标题", null, topic);
		return topic;
	}

	public BbsTopic updateTopic(Integer id, String title, String content,
			BbsUser editor, String ip) {
		BbsTopic topic = dao.findById(id);
		topic.setTitle(title);
		bbsPostMng.updatePost(topic.getFirstPost().getId(), title, content,
				editor, ip);
		return topic;
	}

	public BbsTopic postTopic(Integer userId, Integer siteId, Integer forumId,
			Integer postTypeId, String title, String content, String ip,
			Integer category, Integer categoryType,String[] name, List<MultipartFile> file,
			List<String> code,String titleImg,Integer allayReply) {
		BbsForum forum = bbsForumMng.findById(forumId);
		BbsUser user = bbsUserMng.findById(userId);
		BbsTopicText text = new BbsTopicText();
		BbsTopic topic = createTopic(category,categoryType);
		topic.setSite(siteMng.findById(siteId));
		topic.setForum(forum);
		if (postTypeId != null) {
			topic.setPostType(bbsPostTypeMng.findById(postTypeId));
		}
		topic.setCreater(user);
		topic.setLastReply(user);
		topic.setTopicText(text);
		topic.setTopicTitle(title);
		topic.setTitleImg(titleImg);
		if(allayReply==null || allayReply==1){
			topic.setAllayReply(true);
		}else{
			topic.setAllayReply(false);
		}
		text.setTitle(title);
		text.setTopic(topic);
		if (file != null && file.size() > 0) {
			topic.setAffix(true);
		} else {
			topic.setAffix(false);
		}
		topic.init();
		save(topic);
		handleVoteItem(topic, name);
		BbsPost post = bbsPostMng.post(userId, siteId, topic.getId(),
				postTypeId, title, content, ip, file, code,null);
		topic.setFirstPost(post);
		updateTopicCount(topic, user);
		bbsConfigEhCache.setBbsConfigCache(1, 1, 1, 0, null, siteId);
		return topic;
	}
	
	
	public BbsTopic postTopic(Integer userId, Integer siteId, Integer forumId,Integer postTypeId,
			String title,String ip ) {
		String topictitle;
		if(10<title.length()){
			topictitle = title.substring(0, 10);
		}else{
			topictitle = title;
		}
		 
		BbsForum forum = bbsForumMng.findById(forumId);
		BbsUser user = bbsUserMng.findById(userId);
		BbsTopicText text = new BbsTopicText();
		BbsTopic topic = new BbsTopic();
		topic.setSite(siteMng.findById(siteId));
		topic.setForum(forum);
		if (postTypeId != null) {
			topic.setPostType(bbsPostTypeMng.findById(postTypeId));
		}
		topic.setCreater(user);
		topic.setLastReply(user);
		topic.setTopicText(text);
		topic.setTopicTitle(topictitle);
		text.setTitle(topictitle);
		text.setTopic(topic);
		topic.init();
		save(topic);
		BbsPost post = bbsPostMng.post(userId, siteId, topic.getId(),
				postTypeId, topictitle, title, ip);
		topic.setFirstPost(post);
		updateTopicCount(topic, user);
		bbsConfigEhCache.setBbsConfigCache(1, 1, 1, 0, null, siteId);
		return topic;
	}

	@Transactional(readOnly = true)
	public Pagination getForTag(Integer siteId, Integer forumId,Integer parentPostTypeId,Integer postTypeId, Short status,
			Short primeLevel, String keyWords, String creater,
			Integer createrId, Short topLevel, int pageNo, int pageSize,String jinghua) {
		return dao.getForTag(siteId, forumId,parentPostTypeId,postTypeId, status, primeLevel, keyWords,
				creater, createrId, topLevel, pageNo, pageSize,jinghua);

	}

	@Transactional(readOnly = true)
	public Pagination getMemberTopic(Integer webId, Integer memberId,
			int pageNo, int pageSize) {
		return dao.getMemberTopic(webId, memberId, pageNo, pageSize);
	}

	@Transactional(readOnly = true)
	public List<BbsTopic> getTopTopic(Integer webId, Integer ctgId,
			Integer forumId) {
		return dao.getTopTopic(webId, ctgId, forumId);
	}

	@Transactional(readOnly = true)
	public Pagination getMemberReply(Integer webId, Integer memberId,
			int pageNo, int pageSize) {
		return dao.getMemberReply(webId, memberId, pageNo, pageSize);
	}
	
	@Transactional(readOnly = true)
	public List<BbsTopic> getMemberReply(Integer siteId, Integer userId,
			Integer first,Integer count) {
		return dao.getMemberReply(siteId, userId, first, count);
	}

	@Transactional(readOnly = true)
	public Pagination getTopicByTime(Integer webId, int pageNo, int pageSize) {
		return dao.getTopicByTime(webId, pageNo, pageSize);
	}

	@Transactional(readOnly = true)
	public Pagination getForSearchDate(Integer siteId, Integer forumId,
			Short primeLevel, Integer day, int pageNo, int pageSize) {
		return dao.getForSearchDate(siteId, forumId, primeLevel, day, pageNo,
				pageSize);
	}

	public BbsTopic save(BbsTopic topic) {
		initTopic(topic);
		dao.save(topic);
		return topic;
	}

	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = dao.getPage(pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public BbsTopic findById(Integer id) {
		BbsTopic entity = dao.findById(id);
		return entity;
	}

	public BbsTopic update(BbsTopic bean) {
		Updater<BbsTopic> updater = new Updater<BbsTopic>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public BbsTopic deleteById(Integer id) {
		BbsTopic bean = dao.findById(id);
		bean.setFirstPost(null);
		bean.setLastPost(null);
		List<BbsPost> postList = bbsPostMng.getPostByTopic(id);
		List<BbsCollection> collectionList = bbsCollectionMng.getListByTopicOrForum(id, null);
		if(collectionList != null){
			for(int i=0; i<collectionList.size(); i++){
				bbsCollectionMng.deleteById(collectionList.get(i).getId());
			}
		}
		for (BbsPost post : postList) {
			if (post.equals(bean.getForum().getLastPost())) {
				BbsPost post1 = bbsPostMng.getLastPost(bean.getForum().getId(),
						id);
				if (post1 == null) {
					bean.getForum().setLastPost(null);
					bean.getForum().setLastReply(null);
					bean.getForum().setLastTime(null);
				} else {
					bean.getForum().setLastPost(post1);
					bean.getForum().setLastReply(post1.getCreater());
					bean.getForum().setLastTime(post1.getCreateTime());
				}
			}
			bbsPostMng.deleteById(post.getId());
		}
		dao.deleteById(id);
		return bean;
	}

	public BbsTopic[] deleteByIds(Integer[] ids) {
		BbsTopic[] beans = new BbsTopic[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	private void initTopic(BbsTopic topic) {
		Date now = new Timestamp(System.currentTimeMillis());
		topic.setCreateTime(now);
		topic.setLastTime(now);
		topic.setSortTime(now);
		topic.setViewCount(0L);
		topic.setReplyCount(0);
		topic.setStatus(BbsTopic.NORMAL);
		if (topic.getTopLevel() == null) {
			topic.setTopLevel((short) 0);
		}
		if (topic.getPrimeLevel() == null) {
			topic.setPrimeLevel((short) 0);
		}
		if (topic.getStyleBold() == null) {
			topic.setStyleBold(false);
		}
		if (topic.getStyleItalic() == null) {
			topic.setStyleItalic(false);
		}
	}

	public void updateTopicCount(BbsTopic topic, BbsUser user) {
		if(bbsPointDetailMng.isTodayComplete(user.getId(), Constants.POINT_TYPE_TOPIC)){
			return ;//此处表示今日已经完成了一次,直接返回，不加积分
		}
		BbsForum forum = topic.getForum();
		String description = "<b>"+user.getUsername()+"</b>完成<b>每日发帖</b>任务,获得系统赠送积分";
		bbsPointDetailMng.save(user.getId(), forum.getPointTopic(), description, Constants.POINT_TOPIC, Constants.POINT_TYPE_TOPIC);
		forum.setLastPost(topic.getFirstPost());
		forum.setLastReply(topic.getCreater());
		forum.setLastTime(topic.getSortTime());
		forum.setPostToday(forum.getPostToday() + 1);
		forum.setPostTotal(forum.getPostTotal() + 1);
		forum.setTopicTotal(forum.getTopicTotal() + 1);
		//是否启用积分
		if(forum.getPointAvailable()){
			user.setPoint(user.getPoint() + forum.getPointTopic());
		}
		//是否启用威望
		if(forum.getPrestigeAvailable()){
			user.setPrestige(user.getPrestige()+forum.getPrestigeTopic());
		}
		user.setTopicCount(user.getTopicCount() + 1);
		user.setPostToday(user.getPostToday() + 1);
		List<BbsUserGroup> list = bbsUserGroupMng.getListOfPersonal();
		Integer pointsAll = bbsPointDetailMng.getPointsAll(user.getId());
		for(int i=0; i<list.size(); i++){
			if(pointsAll>=list.get(i).getPoint()){
				continue;
			}else if(i == list.size()-1){
				if(user.getGroup().getId()!=list.get(i).getId()){
					bbsUserMng.updateGroup(user.getId(), list.get(i).getId());
					break;
				}else{
					break;
				}
			}else{
				if(user.getGroup().getId()!=list.get(i-1).getId()){
					bbsUserMng.updateGroup(user.getId(), list.get(i-1).getId());
					break;
				}else{
					break;
				}
			}
		}
	}

	public List<BbsTopic> getList(Integer forumId,String keywords,Integer userId,Integer first,Integer count) {
		return dao.getList(forumId,keywords,userId,first,count);
	}

	public List<BbsTopic> getNewList(Integer first,Integer count,Integer orderby) {
		return dao.getNewList(first,count,orderby);
	}
	
	public List<BbsTopic> getTopList(Short topLevel,Integer count,Integer orderby){
		return dao.getTopList(topLevel, count, orderby);
	}
	
	public List<BbsTopic> getTopicList(){
		return dao.getTopicList();
	}
	public void updateAllTopicCount(BbsTopicCountEnum e){
		dao.updateAllTopicCount(e);
	}
	
	/**
	 *  使用道具
	 */
	public String useMagic(HttpServletRequest request,Integer siteId,Integer tid,
			String magicName,Integer userId,String ip,String color,Integer postCreaterId){
		BbsTopic topic=null;
		if(tid!=null){
			topic=findById(tid);
		}
		BbsUser user=bbsUserMng.findById(userId);
		BbsPost post;
		BbsUser postCreater;
		if(StringUtils.isNotBlank(magicName)){
			if(magicName.equals(MagicConstants.MAGIC_CLOSE)){
				/**
				 * 沉默卡--关闭主题
				 */
				if(bbsForumMng.getModerators(siteId).contains(topic.getCreater().getUsername())){
					return MagicConstants.MAGIC_OPEN_ERROR_NOIN_MODERATORS;
				}
				topic.setAllayReply(false);
			}else if(magicName.equals(MagicConstants.MAGIC_OPEN)){
				/**
				 * 喧嚣卡---打开主题
				 */
				topic.setAllayReply(true);
			}else if(magicName.equals(MagicConstants.MAGIC_BUMP)){
				/**
				 * 提升卡
				 */
				topic.setTopLevel(MagicConstants.TOP_LEVEL_BUMP);
			}else if(magicName.equals(MagicConstants.MAGIC_JACK)){
				/**
				 * 千斤顶
				 */
				topic.setTopLevel(MagicConstants.TOP_LEVEL_JACK);
			}else if(magicName.equals(MagicConstants.MAGIC_STICK)){
				/**
				 * 置顶卡
				 */
				topic.setTopLevel(MagicConstants.TOP_LEVEL_STICK);
			}else if(magicName.equals(MagicConstants.MAGIC_SOFA)){
				/**
				 * 抢沙发(沙发的台词以后再设置道具那里再做更改由后台定制而来)
				 */
				if(topic.getHasSofeed()){
					return MagicConstants.MAGIC_SOFEED_ERROR;
				}else{
					String sofalines=magicConfigMng.findById(siteId).getMagicSofaLines();
					bbsPostMng.reply(userId, siteId, tid,topic.getPostType().getId(), sofalines,
							sofalines, ip, null, null,null);
					topic.setHasSofeed(true);
				}
			}else if(magicName.equals(MagicConstants.MAGIC_HIGHTLIGHT)){
				/**
				 * 变色卡--高亮显示
				 */
				highlightWithNoLog(new Integer[]{tid}, color, false, false,DateUtils.afterDate(new Date(), 1), "", user);
			}else if(magicName.equals(MagicConstants.MAGIC_NAMEPOST)){
				/**
				 * 照妖镜---查看匿名身份（tid传入pid）,返回传回帖子创建者的用户名，拼接了常量字符串在上层处理返回问题
				 */
				post=bbsPostMng.findById(tid);
				bbsUserMng.updatePoint(userId, null, null, magicName, 1, 1);
				return MagicConstants.MAGIC_NAMEPOST_SUCCESS+post.getCreater().getUsername();
			}else if(magicName.equals(MagicConstants.MAGIC_ANONYMOUSPOST)){
				/**
				 * 匿名卡---隐藏自己信息
				 */
				post=bbsPostMng.findById(tid);
				post.setAnonymous(true);
			}else if(magicName.equals(MagicConstants.MAGIC_REPENT)){
				/**
				 * 悔悟卡--删除自己的主题
				 */
				deleteById(tid);
			}else if(magicName.equals(MagicConstants.MAGIC_SHOWIP)){
				/**
				 * 窥视卡---查看用户ip
				 */
				postCreater=bbsUserMng.findById(postCreaterId);
				if(postCreater!=null){
					//更新道具使用
					bbsUserMng.updatePoint(userId, null, null, magicName, 1, 1);
					return postCreater.getUsername()+MagicConstants.MAGIC_SHOWIP_SUCCESS+postCreater.getLastLoginIp();
				}else{
					return MagicConstants.MAGIC_FIND_USER_ERROR;
				}
			}else if(magicName.equals(MagicConstants.MAGIC_CHECKONLINE)){
				/**
				 * 雷达卡---查看用户是否在线
				 */
				postCreater=bbsUserMng.findById(postCreaterId);
				if(postCreater!=null){
					bbsUserMng.updatePoint(userId, null, null, magicName, 1, 1);
				}
				else{
					return MagicConstants.MAGIC_FIND_USER_ERROR;
				}
			}else if(magicName.equals(MagicConstants.MAGIC_MONEY)){
				/**
				 * 金钱卡
				 */
				BbsCommonMagic commomMagic=commomMagicMng.findByIdentifier(magicName);
				int price=commomMagic.getPrice();
				Integer money=generateRandom(1,price*2);
				String str="";
				MagicMessage magicMessage = MagicMessage.create(request);
				if(commomMagic.getCredit()==1){
					//积分
					str = magicMessage.getMessage("cmsUser.point");
					bbsUserMng.updatePoint(userId, money, null, magicName, 1, 1);
				}else if(commomMagic.getCredit()==2){
					//威望
					str = magicMessage.getMessage("cmsUser.prestige");
					bbsUserMng.updatePoint(userId, null, money, magicName, 1, 1);
				}
				return str+MagicConstants.MAGIC_MONEY_SUCCESS+money;
			}
			//未返回的道具（减少道具数量）公用
			bbsUserMng.updatePoint(userId, null, null, magicName, 1, 1);
		}
		return MagicConstants.MAGIC_OPEN_SUCCESS;
	}

	private BbsTopic createTopic(Integer category,Integer categoryType) {
		if (category != null ) {
			if(category == TOPIC_VOTE){
				if(categoryType==1){
					BbsVoteTopic topic = new BbsVoteTopic();
					return topic;
				}else if(categoryType==2){
					BbsVoteTopicSingle topic=new BbsVoteTopicSingle();
					return topic;
				}
			}
		}
		return new BbsTopic();
	}

	private void handleVoteItem(BbsTopic topic, String[] name) {
		if (name != null) {
			if(topic.getCategory() == TOPIC_VOTE||topic.getCategory() ==TOPIC_VOTE_SINGLE){
				for(String s : name){
					BbsVoteItem bean = new BbsVoteItem();
					bean.init();
					bean.setName(s);
					if(topic.getCategory() == TOPIC_VOTE){
						bean.setTopic((BbsVoteTopic)topic);
					}else if(topic.getCategory() == TOPIC_VOTE_SINGLE){
						bean.setTopic((BbsVoteTopicSingle)topic);
					}
					bbsVoteItemMng.save(bean);
				}
			}
		}
	}
	/**
	 *  获取某个范围内的随机数
	 * @param a
	 * @param b
	 * @return
	 */
	private  int generateRandom(int a, int b) {
	        int temp = 0;
	            if (a > b) {
	                temp = new Random().nextInt(a - b);
	                return temp + b;
	            } else {
	                temp = new Random().nextInt(b - a);
	                return temp + a;
	            }
	    }
	
	public List<BbsTopic> getTopicList(Integer userId,Integer bigId,Integer smallId,Integer count){
		return dao.getTopicList(userId, bigId, smallId, count);
		
	}

	private BbsPostMng bbsPostMng;
	private BbsForumMng bbsForumMng;
	private BbsOperationMng bbsOperationMng;
	private CmsSiteMng siteMng;
	private BbsUserMng bbsUserMng;
	private BbsConfigEhCache bbsConfigEhCache;
	private BbsPostTypeMng bbsPostTypeMng;
	private BbsVoteItemMng bbsVoteItemMng;
	@Autowired
	private BbsMagicConfigMng magicConfigMng;
	@Autowired
	private BbsCommonMagicMng commomMagicMng;
	@Autowired
	private BbsCollectionMng bbsCollectionMng;
	@Autowired
	private BbsPointDetailMng bbsPointDetailMng;
	@Autowired
	private BbsFriendsMng bbsFriendsMng;
	@Autowired
	private BbsUserGroupMng bbsUserGroupMng;

	private BbsTopicDao dao;

	@Autowired
	public void setDao(BbsTopicDao dao) {
		this.dao = dao;
	}

	@Autowired
	public void setBbsPostMng(BbsPostMng bbsPostMng) {
		this.bbsPostMng = bbsPostMng;
	}

	@Autowired
	public void setBbsForumMng(BbsForumMng bbsForumMng) {
		this.bbsForumMng = bbsForumMng;
	}

	@Autowired
	public void setBbsOperationMng(BbsOperationMng bbsOperationMng) {
		this.bbsOperationMng = bbsOperationMng;
	}

	@Autowired
	public void setSiteMng(CmsSiteMng siteMng) {
		this.siteMng = siteMng;
	}

	@Autowired
	public void setBbsUserMng(BbsUserMng bbsUserMng) {
		this.bbsUserMng = bbsUserMng;
	}

	@Autowired
	public void setBbsConfigEhCache(BbsConfigEhCache bbsConfigEhCache) {
		this.bbsConfigEhCache = bbsConfigEhCache;
	}

	@Autowired
	public void setBbsPostTypeMng(BbsPostTypeMng bbsPostTypeMng) {
		this.bbsPostTypeMng = bbsPostTypeMng;
	}

	@Autowired
	public void setBbsVoteItemMng(BbsVoteItemMng bbsVoteItemMng) {
		this.bbsVoteItemMng = bbsVoteItemMng;
	}


}
