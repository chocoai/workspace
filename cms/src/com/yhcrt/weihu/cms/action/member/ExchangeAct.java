package com.yhcrt.weihu.cms.action.member;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.yhcrt.weihu.cms.Constants.TPLDIR_EXCHANGE;
import static com.yhcrt.weihu.common.page.SimplePage.cpn;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.yhcrt.weihu.cms.action.CommonUpload;
import com.yhcrt.weihu.cms.entity.assist.CmsChange;
import com.yhcrt.weihu.cms.entity.assist.CmsTT;
import com.yhcrt.weihu.cms.entity.main.Content;
import com.yhcrt.weihu.cms.manager.assist.CmsChangeMng;
import com.yhcrt.weihu.cms.manager.main.ContentMng;
import com.yhcrt.weihu.common.page.Pagination;
import com.yhcrt.weihu.common.web.CookieUtils;
import com.yhcrt.weihu.common.web.ResponseUtils;
import com.yhcrt.weihu.core.entity.CmsSite;
import com.yhcrt.weihu.core.entity.CmsUser;
import com.yhcrt.weihu.core.entity.MemberConfig;
import com.yhcrt.weihu.core.web.WebErrors;
import com.yhcrt.weihu.core.web.util.CmsUtils;
import com.yhcrt.weihu.core.web.util.FrontUtils;

/**
 *交换Action
 * 
 */
@Controller
public class ExchangeAct {

	public static final String EXCHANGE_LIST = "tpl.exchangeList";
	public static final String EXCHANGE_ADD = "tpl.exchangeAdd";
	public static final String EXCHANGE_EDIT = "tpl.exchangeEdit";
	public static final String EXCHANGE_DETAIL = "tpl.exchangeDetail";
	public static final String EXPRO_LIST = "tpl.exp_list";
	/**
	 * 交换列表
	 * 
	 * @param title
	 *            文章标题
	 * @param channelId
	 *            栏目ID
	 * @param pageNo
	 *            页码
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/exchange_list.jspx")
	public String list(String queryTitle, Integer modelId,
			Integer queryChannelId, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
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
		Pagination pagination = changeMng.getPage(user.getId(), null, site.getId(), true,cpn(pageNo),
				CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_EXCHANGE, EXCHANGE_LIST);
	}
	@RequestMapping(value = "/member/expro_list.jspx")
	public String exlist(Integer contentId, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
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
		if (contentId == null) {
			return FrontUtils.showMessage(request, model, "exchange.contentNotFound");
		}
		Content content = contentMng.findById(contentId);
		Pagination pagination = changeMng.getPage(null, contentId, site.getId(), false,cpn(pageNo),
				CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		model.addAttribute("content", content);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_EXCHANGE, EXPRO_LIST);
	}
	//发布物品者选择交换
	@RequestMapping(value = "/member/expro_agree.jspx")
	public String expro(Integer changeId,Integer contentId,String nextUrl,HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user = CmsUtils.getUser(request);
		MemberConfig mcfg = site.getConfig().getMemberConfig();
		// 没有开启会员功能
		if (!mcfg.isMemberOn()) {
			return FrontUtils.showMessage(request, model, "member.memberClose");
		}
		if (user == null) {
			return FrontUtils.showLogin(request, model, site);
		}
		if (contentId == null) {
			return FrontUtils.showMessage(request, model, "exchange.contentNotFound");
		}
		
		List<CmsChange> exchangeList = changeMng.getList(site.getId(), null, contentId, true);
		
		for(int i=0;i<exchangeList.size();i++){
			CmsChange bean = exchangeList.get(i);
			if(bean.getId()== changeId){
				bean.setStatus(1);
			}else{
				bean.setStatus(-1);
			}
			changeMng.update(bean);
		}
		
		//物品交换结束
		Content content = contentMng.findById(contentId);
		content.setChangeState(1);
		contentMng.update(content);
		model.addAttribute("contentId", contentId);
		model.addAttribute("content", content);
		FrontUtils.frontData(request, model, site);
		return FrontUtils.showSuccess(request, model, nextUrl);
	}
	/**
	 * 交换
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/exchange_add.jspx")
	public String add(Integer contentId,HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user = CmsUtils.getUser(request);
		Content content = contentMng.findById(contentId);
		if(content.getUser() !=null && user == content.getUser()){
			
		}
		CmsChange change = new CmsChange();
		model.addAttribute("site", site);
		model.addAttribute("content", content);
		model.addAttribute("change", change);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_EXCHANGE, EXCHANGE_ADD);
	}
	/**
	 * 修改
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/exchange_edit.jspx")
	public String edit(Integer id, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsChange change = changeMng.findById(id);
		model.addAttribute("site", site);
		model.addAttribute("change", change);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_EXCHANGE, EXCHANGE_EDIT);
	}

	/**
	 * 保存
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/exchange_save.jspx", method = RequestMethod.POST)
	public void submit(Integer id,CmsChange bean,Integer contentId,
			HttpServletRequest request,HttpServletResponse response, ModelMap model) throws JSONException, IOException {
		
		JSONObject json = new JSONObject();
		CmsUser user = CmsUtils.getUser(request);
		Content content = null;
	    if(contentId == null){
	    	json.put("success", false);
	    	json.put("status", 1);
		}else{
			content = contentMng.findById(contentId);
		}
		if (content == null) {
			json.put("success", false);
			json.put("status", 1);
		}else if(content.getUser() !=null && user == content.getUser()){
			json.put("success", false);
			json.put("status", 2);
		}else{
			if(id == null){
				bean.setContent(content);
				bean.setUser(user);
				changeMng.save(bean);
			}else{
				changeMng.update(bean);
			}
			json.put("success", true);
			json.put("status", 0);
		}
		
		ResponseUtils.renderJson(response, json.toString());
	}
	@RequestMapping(value = "/member/exchange/{id}.jspx", method = RequestMethod.GET)
	public String detail(@PathVariable Integer id, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsChange cmsChange = changeMng.findById(id);
		
		model.addAttribute("cmsChange", cmsChange);
		FrontUtils.frontData(request, model, site);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_EXCHANGE, EXCHANGE_DETAIL);
		

	}
    //删除
	@RequestMapping(value = "/member/exchange_delete.jspx")
	public String delete(Integer[] ids, HttpServletRequest request,
			String nextUrl, HttpServletResponse response, ModelMap model) {
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
		WebErrors errors = validateDelete(ids, site, user, request);
		if (errors.hasErrors()) {
			return FrontUtils.showError(request, response, model, errors);
		}
		changeMng.deleteByIds(ids);
		
		return FrontUtils.showSuccess(request, model, nextUrl);
	}
	private WebErrors validateDelete(Integer[] ids, CmsSite site, CmsUser user,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (vldOpt(errors, site, user, ids)) {
			return errors;
		}
		return errors;
	}
	private boolean vldOpt(WebErrors errors, CmsSite site, CmsUser user,
			Integer[] ids) {
		for (Integer id : ids) {
			if (errors.ifNull(id, "id")) {
				return true;
			}
			CmsChange cmsChange = changeMng.findById(id);
			// 数据不存在
			if (errors.ifNotExist(cmsChange, CmsTT.class, id)) {
				return true;
			}
			// 非本用户数据
			if (!cmsChange.getUser().getId().equals(user.getId())) {
				errors.noPermission(CmsTT.class, id);
				return true;
			}
		}
		return false;
	}
	@Autowired
	private ContentMng contentMng;
	@Autowired
	private CmsChangeMng changeMng;
}
