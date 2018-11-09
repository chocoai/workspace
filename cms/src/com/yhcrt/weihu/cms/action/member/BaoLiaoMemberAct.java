package com.yhcrt.weihu.cms.action.member;

import static com.yhcrt.weihu.cms.Constants.TPLDIR_MEMBER;
import static com.yhcrt.weihu.common.page.SimplePage.cpn;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yhcrt.weihu.cms.entity.assist.CmsBaoLiao;
import com.yhcrt.weihu.cms.entity.assist.CmsComment;
import com.yhcrt.weihu.cms.manager.assist.CmsBaoLiaoMng;
import com.yhcrt.weihu.cms.manager.assist.CmsCommentMng;
import com.yhcrt.weihu.cms.manager.assist.CmsFileMng;
import com.yhcrt.weihu.common.page.Pagination;
import com.yhcrt.weihu.common.web.CookieUtils;
import com.yhcrt.weihu.core.entity.CmsSite;
import com.yhcrt.weihu.core.entity.CmsUser;
import com.yhcrt.weihu.core.entity.MemberConfig;
import com.yhcrt.weihu.core.web.WebErrors;
import com.yhcrt.weihu.core.web.util.CmsUtils;
import com.yhcrt.weihu.core.web.util.FrontUtils;

/**
 * 会员中心报料Action
 * 
 * @author 
 * 
 */
@Controller
public class BaoLiaoMemberAct {

	public static final String MYBL_LIST = "tpl.baoliaoList";
	public static final String MYBL_EDIT = "tpl.baoliaoEdit";
	public static final String MYBL_COMMENTLIST = "tpl.baoliao_comment_list";
	public static final String MYBL_COMMENTREPLY = "tpl.baoliao_comment_reply";

	/**
	 * 我的报料
	 * 
	 * 如果没有登录则跳转到登陆页
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/mybaoliao_list.jspx")
	public String mybaoliaos(Integer pageNo, HttpServletRequest request,
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
		Pagination pagination = baoliaoMng.getPage(site.getId(), user.getId(), null, null, null, null, true, true, cpn(pageNo),
				CookieUtils.getPageSize(request));
		List<CmsComment> commentList =  commentMng.getBaoliaoList(site.getId(),null, null, null, null, false,0);
		model.addAttribute("pagination", pagination);
		model.addAttribute("commentList", commentList);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MEMBER, MYBL_LIST);
	}
	@RequestMapping(value = "/member/baoliao_comment.jspx")
	public String myblcomments(Integer id,Integer pageNo, HttpServletRequest request,
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
		CmsBaoLiao baoliao = baoliaoMng.findById(id);
		Pagination pagination = commentMng.getBaoliaoPage(site.getId(),id,null, null, null, false, cpn(pageNo),
				CookieUtils.getPageSize(request));
		List<CmsComment> commentList = commentMng.getChildList(null, true);
		model.addAttribute("pagination", pagination);
		model.addAttribute("commentList", commentList);
		model.addAttribute("baoliao", baoliao);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MEMBER, MYBL_COMMENTLIST);
	}
	/**
	 * 查看评论回复
	 */
	@RequestMapping(value = "/member/blcomment_replay.jspx")
	public String blcomment_replay(Integer id, String nextUrl,HttpServletRequest request,
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
        
		model.addAttribute("commentList", commentList);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MEMBER, MYBL_COMMENTREPLY);
	}
	@RequestMapping(value = "/member/baoliao_edit.jspx")
	public String edit(Integer id, String nextUrl,HttpServletRequest request,
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
		WebErrors errors = validateEdit(id, site, user, request);
		if (errors.hasErrors()) {
			return FrontUtils.showError(request, response, model, errors);
		}
		CmsBaoLiao baoliao = baoliaoMng.findById(id);
		model.addAttribute("baoliao", baoliao);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MEMBER, MYBL_EDIT);
	}
	@RequestMapping(value = "/member/baoliao_update.jspx")
	public String update(Integer id, CmsBaoLiao baoliao,String captcha,String[] picPaths, String[] picDescs,
			String nextUrl, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
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
		WebErrors errors = validateUpdate(id, site, user, request);
		if (errors.hasErrors()) {
			return FrontUtils.showError(request, response, model, errors);
		}
		
		baoliaoMng.update(baoliao, picPaths, picDescs);
		//处理附件
		fileMng.updateFileByPathsBL(picPaths, null, true, baoliao);
		return FrontUtils.showSuccess(request, model, nextUrl);
	}
	/**
	 * 删除报料（id）
	 * 
	 * 如果没有登录则跳转到登陆页
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/baoliao_delete.jspx")
	public String delete(Integer id, String nextUrl, HttpServletRequest request,
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
		WebErrors errors = validateDelete(id, site, user, request);
		if (errors.hasErrors()) {
			return FrontUtils.showError(request, response, model, errors);
		}
		 baoliaoMng.deleteById(id);
		 
		 //删除爆料下对应的评论及回复
		List<CmsComment> commentList = commentMng.getBaoliaoList(site.getId(),id, null, null, null, false,0);
		List<CmsComment> childList;
		if(!commentList.isEmpty()){
			for(int i=0; i<commentList.size();i++) {
				CmsComment comment = commentList.get(i);
				childList = commentMng.getChildList(comment.getId(), false);
				//先删除回复
				if(!childList.isEmpty()){
					for(int j = 0;j<childList.size();j++){
						CmsComment child = childList.get(j);
						commentMng.deleteById(child.getId());
					}
				}
				//再删除评论
				commentMng.deleteById(comment.getId());
		}
		}
		
		
		// 返回评论列表
		return FrontUtils.showSuccess(request, model, nextUrl);
	}
	
	private WebErrors validateEdit(Integer id, CmsSite site, CmsUser user,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (vldOpt(errors, site, user, id)) {
			return errors;
		}
		return errors;
	}
	
	private WebErrors validateUpdate(Integer id, CmsSite site, CmsUser user, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (vldOpt(errors, site, user, id)) {
			return errors;
		}
		
		return errors;
	}
	private WebErrors validateDelete(Integer id, CmsSite site, CmsUser user,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (vldOpt(errors, site, user, id)) {
			return errors;
		}
		return errors;
	}
	private boolean vldOpt(WebErrors errors, CmsSite site, CmsUser user,
			Integer id) {
		
			if (errors.ifNull(id, "id")) {
				return true;
			}
			CmsBaoLiao baoliao = baoliaoMng.findById(id);
			
			// 数据不存在
			if (errors.ifNotExist(baoliao, CmsBaoLiao.class, id)) {
				return true;
			}
			// 非本用户数据
			if (!baoliao.getUser().getId().equals(user.getId())) {
				errors.noPermission(CmsBaoLiao.class, id);
				return true;
			}
		
		return false;
	}
	@Autowired
	private CmsFileMng fileMng;

	@Autowired
	private CmsBaoLiaoMng baoliaoMng;
	@Autowired
	private CmsCommentMng commentMng;
}
