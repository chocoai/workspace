package com.yhcrt.weihu.bbs.action.front;

import static com.yhcrt.weihu.bbs.Constants.TPLDIR_FORUM;
import static com.yhcrt.weihu.bbs.Constants.TPLDIR_INDEX;
import static com.yhcrt.weihu.bbs.Constants.TPLDIR_TOPIC;
import static com.yhcrt.weihu.common.web.Constants.INDEX;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yhcrt.weihu.bbs.Constants;
import com.yhcrt.weihu.bbs.action.front.DynamicPageAct;
import com.yhcrt.weihu.bbs.cache.TopicCountEhCache;
import com.yhcrt.weihu.bbs.entity.BbsConcern;
import com.yhcrt.weihu.bbs.entity.BbsForum;
import com.yhcrt.weihu.bbs.entity.BbsPostType;
import com.yhcrt.weihu.bbs.entity.BbsTopic;
import com.yhcrt.weihu.bbs.entity.BbsUser;
import com.yhcrt.weihu.bbs.entity.BbsUserGroup;
import com.yhcrt.weihu.bbs.manager.BbsCollectionMng;
import com.yhcrt.weihu.bbs.manager.BbsConcernMng;
import com.yhcrt.weihu.bbs.manager.BbsConfigMng;
import com.yhcrt.weihu.bbs.manager.BbsForumMng;
import com.yhcrt.weihu.bbs.manager.BbsFriendShipMng;
import com.yhcrt.weihu.bbs.manager.BbsFriendsMng;
import com.yhcrt.weihu.bbs.manager.BbsPostTypeMng;
import com.yhcrt.weihu.bbs.manager.BbsTopicMng;
import com.yhcrt.weihu.bbs.web.CmsUtils;
import com.yhcrt.weihu.bbs.web.FrontUtils;
import com.yhcrt.weihu.common.web.springmvc.MessageResolver;
import com.yhcrt.weihu.core.entity.CmsSite;
import com.yhcrt.weihu.core.web.front.URLHelper;

@Controller
public class DynamicPageAct {
	private static final Logger log = LoggerFactory
			.getLogger(DynamicPageAct.class);

	public static final String TPL_INDEX = "tpl.index";
	public static final String TPL_FORUM = "tpl.forum";
	public static final String TPL_TOPIC = "tpl.topic";
	public static final String TPL_NO_VIEW = "tpl.noview";
	
	public static final String LOGIN_INPUT = "tpl.loginInput";
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap model,HttpServletResponse response) {
		CmsSite site = CmsUtils.getSite(request);
	//	model.addAttribute("site", site);
		FrontUtils.frontData(request, model, site);
		List<BbsTopic> topicList = bbsTopicMng.getTopicIndex(12);
		List<BbsTopic> topTopicList = new ArrayList<BbsTopic>(); 
		BbsTopic topTopic = new BbsTopic(); 
		List<BbsTopic> downTopicList = new ArrayList<BbsTopic>(); 
		BbsTopic downTopic = new BbsTopic(); 
		if(topicList != null && topicList.size()!=0){
			if(topicList.size()<=6){
				topTopic = topicList.get(0);
				for(int i=1 ;i<topicList.size() ;i++){
					topTopicList.add(topicList.get(i));
				}
			}else{
				topTopic = topicList.get(0);
				for(int i=1 ;i<6 ;i++){
					topTopicList.add(topicList.get(i));
				}
				downTopic = topicList.get(6);
				for(int i=7; i<topicList.size(); i++){
					downTopicList.add(topicList.get(i));
				}
			}
		}
		List<BbsTopic> activeLists = bbsTopicMng.getActiveIndex(7, 1,null);
		BbsTopic topActive = new BbsTopic();
		List<BbsTopic> activeList = new ArrayList<BbsTopic>();
		if(activeLists != null && activeLists.size()!=0){
			topActive = activeLists.get(0);
			for(int i=1; i<activeLists.size(); i++){
				activeList.add(activeLists.get(i));
			}
		}
		//图片新闻
		List<BbsTopic> newsList = bbsTopicMng.getIndexNewsList(5);
		//大咖圈
		BbsForum topForum = bbsForumMng.findById(Constants.INDEX_TOP_FORUM);
		model.addAttribute("topForum", topForum);
		model.addAttribute("newsList", newsList);
		model.addAttribute("topTopic", topTopic);
		model.addAttribute("topTopicList", topTopicList);
		model.addAttribute("downTopic", downTopic);
		model.addAttribute("downTopicList", downTopicList);
		model.addAttribute("topActive", topActive);
		model.addAttribute("activeList", activeList);
		return FrontUtils.getTplPath(request, site,
				TPLDIR_INDEX, TPL_INDEX);
	}
	
	/**
	 * WEBLOGIC的默认路径
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index.jhtml", method = RequestMethod.GET)
	public String indexForWeblogic(HttpServletRequest request, ModelMap model,HttpServletResponse response) {
		return index(request, model,response);
	}

	@RequestMapping(value = "/**/*.*", method = RequestMethod.GET)
	public String dynamic(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,String ty,Integer pageSize,Integer pageNo) {
		if(ty==null){
			ty="a";
		}
		String[] paths = URLHelper.getPaths(request);//拆分请求路径，根据‘/’，去掉后缀
		for(int i=0;i<paths.length;i++){
		}
		String requestUrl = request.getRequestURI();
		String address = null;
		if(0<=requestUrl.lastIndexOf('.')){
			 address = requestUrl.substring(requestUrl.lastIndexOf('/')+1,requestUrl.lastIndexOf('.'));
		}
		if(ty!=null){
			if(ty.length()>=4){
				if("jing".equals(ty.substring(0,4))){
					return forum(paths[0],null, request, response, model,"index_jing",ty, pageSize,pageNo);
				}
			}
		}
		
		if("index_jing".equals(address)){
			return forum(paths[0], null,request, response, model,"index_jing",ty,pageSize,pageNo);
		}else if("index_active".equals(address)){
			return forum(paths[0],null,request,response,model,"index_active",ty,pageSize,pageNo);
		}
		int len = paths.length;
		if (len == 1) {
			return null;
		} else if (len == 2) {
			if (paths[1].equals(INDEX)) {
				// 主题列表
				return forum(paths[0],null, request, response, model,"index",ty,pageSize,pageNo);
			} else {
				// 主题详细页
				try {
					Integer topicId = Integer.parseInt(paths[1]);
					return topic(paths[0], topicId, request, response, model);
				} catch (NumberFormatException e) {
					log.debug("topic id must String: {}", paths[1]);
					return FrontUtils.pageNotFound(request, response, model);
				}
			}
		}else if(len==3){
			// 主题分类列表
			return forum(paths[0], paths[2],request, response, model,paths[2],ty,pageSize,pageNo);
		}else {
			log.debug("Illegal path length: {}, paths: {}", len, paths);
			return FrontUtils.pageNotFound(request, response, model);
		}
	}

	/**
	 * 获取板块信息
	 * @param path  板块的访问路径
	 * @param typeId
	 * @param request
	 * @param response
	 * @param model
	 * @param type
	 * @param ty
	 * @return
	 */
	private String forum(String path,String typeId, HttpServletRequest request,
			HttpServletResponse response, ModelMap model,String type,String ty,
			Integer pageSize,Integer pageNo) {
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		BbsForum forum = bbsForumMng.getByPath(site.getId(), path);
		boolean check = checkView(forum, user, site);
		if (!check) {
			model.put("msg",MessageResolver.getMessage(request, "member.hasnopermission"));
			return FrontUtils.getTplPath(request, site,
					TPLDIR_FORUM, TPL_NO_VIEW);
		}
		model.put("manager", checkManager(forum, user, site));
		model.put("uptop", checkUpTop(forum, user, site));
		model.put("sheild", checkShield(forum, user, site));
		model.put("moderators", checkModerators(forum, user, site));
		model.put("forum", forum);
		if(StringUtils.isNotBlank(typeId)){
			Integer typeIds=Integer.valueOf(typeId);
			BbsPostType postType=bbsPostTypeMng.findById(typeIds);
			if(postType!=null&&postType.getParent()==null){
				//有子类的，包含子类
				if(postType.getChilds()!=null&&postType.getChilds().size()>0){
					model.put("parentTypeId", typeIds);
				}else{
					//帖子大类(没有子类)
					model.put("typeId", typeIds);
				}
			}else{
				model.put("typeId", typeIds);
			}
		}
		model.put("type", type);
		model.put("ty", ty);
		//判断板块是否已收藏
		if(user != null){
			model.put("isCollection", bbsCollectionMng.isCollection(user.getId(), forum.getId(), null));
		}else{
			model.put("isCollection", 0);
		}
		//Page<BbsTopic> pageBean = bbsTopicMng.getPage(pageSize, pageNo, forum.getId(), site.getId(),type,typeId);
		FrontUtils.frontPageData(request, model);
		//model.addAttribute("pageBean", pageBean);
		return FrontUtils.getTplPath(request, site,
				TPLDIR_FORUM, TPL_FORUM);
	}

	private String topic(String path, Integer topicId,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		BbsForum forum = bbsForumMng.getByPath(site.getId(), path);
		BbsTopic topic = bbsTopicMng.findById(topicId);
		//防止跳跃路径访问
		if(topic!=null){
			forum=topic.getForum();
		}else{
			return FrontUtils.pageNotFound(request, response, model); 
		}
		boolean check = checkView(forum, user, site);
		if (!check) {
			model.put("msg",MessageResolver.getMessage(request, "member.hasnopermission"));
			return FrontUtils.getTplPath(request, site,
					TPLDIR_FORUM, TPL_NO_VIEW);
		}
		topicCountEhCache.setViewCount(topicId);
		if(topic!=null&&topic.getPostType()!=null){
			model.put("postTypeId", topic.getPostType().getId());
		}
		//判断是否已收藏 和是否已关注
		if(user != null){
			Boolean isConcern = bbsConcernMng.isConcernByUser(user.getId(), topic.getCreater().getId());
			model.put("isCollection", bbsCollectionMng.isCollection(user.getId(), null, topicId));
			if(isConcern == null){
				model.put("isConcern", 0);
			}else if(isConcern){
				model.put("isConcern", 1);
			}else{
				model.put("isConcern", 0);
			}
			List<BbsConcern> bbsConcerns = bbsConcernMng.getListByUser(user.getId());
			List<Integer> isConcerns = new ArrayList<Integer>();
			if(bbsConcerns != null){
				for(int i=0; i<bbsConcerns.size(); i++){
					isConcerns.add(bbsConcerns.get(i).getConcernUser().getId());
				}
			}
			model.put("isConcerns", isConcerns);
			model.put("isFriends", bbsFriendsMng.getMyFriendsId(user.getId()));
			model.put("isApplys", bbsFriendShipMng.getApplyIds(user.getId()));
		}else{
			model.put("isCollection", 0);
			model.put("isConcern", 0);
			model.put("isConcerns", null);
		}
		model.put("moderators", checkModerators(forum, user, site));
		model.put("forum", forum);
		model.put("topic", topic);
		FrontUtils.frontPageData(request, model);
		return FrontUtils.getTplPath(request, site,
				TPLDIR_TOPIC, TPL_TOPIC);
	}

	private boolean checkView(BbsForum forum, BbsUser user, CmsSite site) {
		if(forum==null){
			return false;
		}else if (forum.getGroupViews() == null) {
			return false;
		} else {
			BbsUserGroup group = null;
			if (user == null) {
				group = bbsConfigMng.findById(site.getId()).getDefaultGroup();
			} else {
				group = user.getGroup();
			}
			if (group == null) {
				return false;
			}
			if (forum.getGroupViews().indexOf("," + group.getId() + ",") == -1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 查询是否拥有主题管理权限
	 * @param forum
	 * @param user
	 * @param site
	 * @return
	 */
	private boolean checkManager(BbsForum forum, BbsUser user, CmsSite site) {
		if (forum.getGroupViews() == null) {
			return false;
		} else {
			BbsUserGroup group = null;
			if (user == null) {
				group = bbsConfigMng.findById(site.getId()).getDefaultGroup();
			} else {
				group = user.getGroup();
			}
			if (group == null) {
				return false;
			}
			if (!group.hasRight(forum, user)) {
				return false;
			}
			if (!group.topicManage()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 查询是否拥有主题置顶权限
	 * @param forum
	 * @param user
	 * @param site
	 * @return
	 */
	private boolean checkUpTop(BbsForum forum, BbsUser user, CmsSite site) {
		if (forum.getGroupViews() == null) {
			return false;
		} else {
			BbsUserGroup group = null;
			if (user == null) {
				group = bbsConfigMng.findById(site.getId()).getDefaultGroup();
			} else {
				group = user.getGroup();
			}
			if (group == null) {
				return false;
			}
			if (!group.hasRight(forum, user)) {
				return false;
			}
			if (group.topicTop() == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 查询是否拥有主题屏蔽、移动权限
	 * @param forum
	 * @param user
	 * @param site
	 * @return
	 */
	private boolean checkShield(BbsForum forum, BbsUser user, CmsSite site) {
		if (forum.getGroupViews() == null) {
			return false;
		} else {
			BbsUserGroup group = null;
			if (user == null) {
				group = bbsConfigMng.findById(site.getId()).getDefaultGroup();
			} else {
				group = user.getGroup();
			}
			if (group == null) {
				return false;
			}
			if (!group.hasRight(forum, user)) {
				return false;
			}
			if (!group.topicShield()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断是否具有版主权限
	 * @param forum
	 * @param user
	 * @param site
	 * @return
	 */
	private boolean checkModerators(BbsForum forum, BbsUser user, CmsSite site) {
		if (forum.getGroupViews() == null) {
			return false;
		} else {
			BbsUserGroup group = null;
			if (user == null) {
				group = bbsConfigMng.findById(site.getId()).getDefaultGroup();
			} else {
				group = user.getGroup();
			}
			if (group == null) {
				return false;
			}
			if (!group.hasRight(forum, user)) {
				return false;
			}
		}
		return true;
	}

	@Autowired
	private BbsTopicMng bbsTopicMng;
	@Autowired
	private BbsForumMng bbsForumMng;
	@Autowired
	private BbsConfigMng bbsConfigMng;
	@Autowired
	private TopicCountEhCache topicCountEhCache;
	@Autowired
	private BbsPostTypeMng bbsPostTypeMng;
	@Autowired
	private BbsCollectionMng bbsCollectionMng;
	@Autowired
	private BbsConcernMng bbsConcernMng;
	@Autowired
	private BbsFriendShipMng bbsFriendShipMng;
	@Autowired
	private BbsFriendsMng bbsFriendsMng;

}
