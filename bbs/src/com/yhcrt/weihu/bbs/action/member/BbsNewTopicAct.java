package com.yhcrt.weihu.bbs.action.member;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yhcrt.weihu.bbs.entity.BbsPost;
import com.yhcrt.weihu.bbs.entity.BbsUser;
import com.yhcrt.weihu.bbs.manager.BbsPointDetailMng;
import com.yhcrt.weihu.bbs.manager.BbsPostMng;
import com.yhcrt.weihu.bbs.web.CmsUtils;
import com.yhcrt.weihu.bbs.web.FrontUtils;
import com.yhcrt.weihu.core.entity.CmsSite;

/**
 * 于帖子相关的部分action
 * @author 
 *
 */
@Controller
public class BbsNewTopicAct {

	@RequestMapping("/member/to_friend_topic.jspx")
	public String toFriendsTopic(HttpServletRequest request, ModelMap model){
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		model.put("isSign", bbsPointDetailMng.isSign(user.getId()));
		return "/WEB-INF/t/cms/www/blue/topic/friend_topic.html";
	}
	
	@RequestMapping("/member/to_reply{postId}.jspx")
	public String toReply(@PathVariable Integer postId,
			HttpServletRequest request, ModelMap model){
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		BbsPost bbsPost = bbsPostMng.findById(postId);
		if(bbsPost == null){
			return "redirect:/";
		}
		model.put("postTypeId", bbsPost.getTopic().getPostType().getId());
		model.put("bbsPost", bbsPost);
		return "/WEB-INF/t/cms/www/blue/topic/reply_post.html";
	}
	
	@Autowired
	private BbsPostMng bbsPostMng;
	@Autowired
	private BbsPointDetailMng bbsPointDetailMng;
	
}
