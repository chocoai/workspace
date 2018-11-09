package com.yhcrt.weihu.cms.action.member;

import static com.yhcrt.weihu.cms.Constants.TPLDIR_COMMENT;
import static com.yhcrt.weihu.common.page.SimplePage.cpn;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yhcrt.weihu.cms.entity.assist.CmsBaoLiao;
import com.yhcrt.weihu.cms.entity.assist.CmsComment;
import com.yhcrt.weihu.cms.entity.main.Content;
import com.yhcrt.weihu.cms.entity.main.ContentCount;
import com.yhcrt.weihu.cms.manager.assist.CmsBaoLiaoMng;
import com.yhcrt.weihu.cms.manager.assist.CmsCommentMng;
import com.yhcrt.weihu.cms.manager.main.ContentCountMng;
import com.yhcrt.weihu.cms.manager.main.ContentMng;
import com.yhcrt.weihu.common.page.Pagination;
import com.yhcrt.weihu.common.web.CookieUtils;
import com.yhcrt.weihu.common.web.RequestUtils;
import com.yhcrt.weihu.core.entity.CmsSite;
import com.yhcrt.weihu.core.entity.CmsUser;
import com.yhcrt.weihu.core.entity.MemberConfig;
import com.yhcrt.weihu.core.web.WebErrors;
import com.yhcrt.weihu.core.web.util.CmsUtils;
import com.yhcrt.weihu.core.web.util.FrontUtils;

/**
 * 会员中心获取评论Action
 * 
 * @author 
 * 
 */
@Controller
public class CommentMemberAct {
	private static final Logger log = LoggerFactory
			.getLogger(CommentMemberAct.class);

	public static final String COMMENT_LIST = "tpl.commentLists";
	public static final String COMMENT_MNG = "tpl.commentMng";
	public static final String COMMENT_REPLY = "tpl.commentReply";

	/**
	 * 我的评论
	 * 
	 * 如果没有登录则跳转到登陆页
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/mycomments.jspx")
	public String mycomments(Integer pageNo, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		MemberConfig mcfg = site.getConfig().getMemberConfig();
		// 没有开启会员功能
		if (!mcfg.isMemberOn()) {
			return FrontUtils.showMessage(request, model, "member.memberClose");
		}
		if (user == null) {
			return FrontUtils.showLogin(request, model, site);
		}
		Pagination pagination = commentMng.getPageForMember(site.getId(), null, 
				user.getId(), null, null, null, null, true, cpn(pageNo),
				CookieUtils.getPageSize(request));
		List<CmsComment> commentList = commentMng.getChildList(null, true);
		model.addAttribute("pagination", pagination);
		model.addAttribute("commentList", commentList);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_COMMENT, COMMENT_LIST);
	}
	/**
	 * 查看评论回复
	 */
	@RequestMapping(value = "/member/comment_replay.jspx")
	public String comment_replay(Integer id, String nextUrl,HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		MemberConfig mcfg = site.getConfig().getMemberConfig();
		// 没有开启会员功能
		if (!mcfg.isMemberOn()) {
			return FrontUtils.showMessage(request, model, "member.memberClose");
		}
		if (user == null) {
			return FrontUtils.showLogin(request, model, site);
		}
		
		List<CmsComment> commentList = commentMng.getChildList(id, true);
//		CmsComment comment=commentMng.findById(id);
//		if(!comment.getCommentUser().equals(user)){
//			WebErrors errors=WebErrors.create(request);
//			errors.addErrorCode("error.noPermissionsView");
//			return FrontUtils.showError(request, response, model, errors);
//		}
//		model.addAttribute("comment", comment);
		model.addAttribute("commentList", commentList);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_COMMENT, COMMENT_REPLY);
	}

	/**
	 * 我的信息所有的评论
	 * 
	 * 如果没有登录则跳转到登陆页
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/news_comments.jspx")
	public String news_comments(Integer pageNo, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		MemberConfig mcfg = site.getConfig().getMemberConfig();
		// 没有开启会员功能
		if (!mcfg.isMemberOn()) {
			return FrontUtils.showMessage(request, model, "member.memberClose");
		}
		if (user == null) {
			return FrontUtils.showLogin(request, model, site);
		}
		Pagination pagination = commentMng.getPageForMember(site.getId(), null,
				null, user.getId(), null, null, null, true, cpn(pageNo),
				CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_COMMENT, COMMENT_MNG);
	}

	/**
	 * 删除评论（id，评论人id，来访ip）
	 * 
	 * 如果没有登录则跳转到登陆页
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/comment_delete.jspx")
	public String delete(Integer commentId,Integer userId, String ip,
			Integer pageNo, String nextUrl, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user = CmsUtils.getUser(request);

		FrontUtils.frontData(request, model, site);
		MemberConfig mcfg = site.getConfig().getMemberConfig();
		// 没有开启会员功能
		if (!mcfg.isMemberOn()) {
			return FrontUtils.showMessage(request, model, "member.memberClose");
		}
		if (user == null) {
			return FrontUtils.showLogin(request, model, site);
		}
		// 删除单条评论
		CmsComment bean;
		List<CmsComment> childList;
		if (commentId != null) {
			CmsComment cmsComment=commentMng.findById(commentId);
			if(cmsComment==null){
				return FrontUtils.showMessage(request, model, "comment.notFound");
			}
			if(!canDeleteComment(cmsComment, user)){
				return FrontUtils.showMessage(request, model, "comment.deleteError");
			}
			Content content = cmsComment.getContent();
			
			if (content == null) {
				return FrontUtils.showMessage(request, model, "comment.deleteError");
			} else {
				ContentCount contentCount = contentCountMng.findById(content.getId());
				// 评论-1
				contentCountMng.commentMinusCount(content.getId());
				
                //先删除回复
				childList = commentMng.getChildList(commentId, false);
				if (!childList.isEmpty()) {
					for (int i = 0; i < childList.size(); i++) {
						CmsComment child = childList.get(i);
						commentMng.deleteById(child.getId());
						contentCountMng.commentMinusCount(content.getId());
					}
				}
				contentCountMng.update(contentCount);
			}
            
			bean = commentMng.deleteById(commentId);

			log.info("delete CmsComment id={}", bean.getId());
		} else {
			// 依据评论人或者评论ip删除评论
			List<CmsComment> comments = commentMng.getListForDel(site.getId(),
					user.getId(), userId, ip);
			for (int i = 0; i < comments.size(); i++) {
				bean = comments.get(i);
				if(!canDeleteComment(bean, user)){
					return FrontUtils.showMessage(request, model, "comment.deleteError");
				}
				commentMng.deleteById(comments.get(i).getId());
				log.info("delete CmsComment id={}", bean.getId());
			}
		}
		// 返回评论列表
		return FrontUtils.showSuccess(request, model, nextUrl);
	}
	/**
	 * 删除爆料评论（id，baoliaoId，评论人id）
	 * 
	 * 如果没有登录则跳转到登陆页
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/blcomment_delete.jspx")
	public String deleteBlCommt(Integer commentId, Integer baoliaoId,Integer userId,String ip,
			Integer pageNo, String nextUrl, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user = CmsUtils.getUser(request);

		FrontUtils.frontData(request, model, site);
		MemberConfig mcfg = site.getConfig().getMemberConfig();
		// 没有开启会员功能
		if (!mcfg.isMemberOn()) {
			return FrontUtils.showMessage(request, model, "member.memberClose");
		}
		if (user == null) {
			return FrontUtils.showLogin(request, model, site);
		}
		CmsBaoLiao baoliao ;
		// 删除单条评论
		CmsComment bean;
		List<CmsComment> childList;
		if (commentId != null) {
			CmsComment cmsComment=commentMng.findById(commentId);
			
			if(cmsComment==null){
				return FrontUtils.showMessage(request, model, "comment.notFound");
			}
			if(!canDeleteComment(cmsComment, user)){
				return FrontUtils.showMessage(request, model, "comment.deleteError");
			}
			if(baoliaoId!=null){
				baoliao = baoliaoMng.findById(baoliaoId);
			}else{
				return FrontUtils.showMessage(request, model, "comment.deleteError");
			}
			if(baoliao != null){
				if(baoliao.getCommtCount() != null||baoliao.getCommtCount() > 0)
					 baoliao.setCommtCount(baoliao.getCommtCount()-1);
				else baoliao.setCommtCount(0);
				
			}else{
				return FrontUtils.showMessage(request, model, "comment.deleteError");
			}
			childList = commentMng.getChildList(commentId, false);
			if(!childList.isEmpty()){
				for(int i = 0;i<childList.size();i++){
					CmsComment child = childList.get(i);
					commentMng.deleteById(child.getId());
					baoliao.setCommtCount(baoliao.getCommtCount()-1);
				}
			}
			baoliaoMng.update(baoliao);
			bean = commentMng.deleteById(commentId);
			log.info("delete CmsComment id={}", bean.getId());
		} else {
			return FrontUtils.showMessage(request, model, "comment.notFound");
		}
		/*
		 * Pagination pagination = commentMng.getPageForMember(site.getId(),
		 * null, null, user.getId(), null, null, null, true, cpn(pageNo),
		 * CookieUtils.getPageSize(request)); model.addAttribute("pagination",
		 * pagination);
		 */
		// 返回评论列表
		return FrontUtils.showSuccess(request, model, nextUrl);
	}
	private  boolean canDeleteComment(CmsComment comment,CmsUser user){
		//匿名用户评论文章的所有者可以删除
		if(comment.getCommentUser()==null&&!comment.getContent().getUser().equals(user)){
			return false;
		}else if(comment.getCommentUser()==null&&comment.getContent().getUser().equals(user)){
			return true;
		}else{
			//非匿名用户评论 文章的所有者可以删除，评论者也可以删除
			if(comment.getCommentUser().equals(user)||comment.getContent().getUser().equals(user)){
				return true;
			}else{
				return false;
			}
		}
	}

	@Autowired
	private CmsCommentMng commentMng;
	@Autowired
	private CmsBaoLiaoMng baoliaoMng;
	@Autowired
	private ContentCountMng contentCountMng;
}
