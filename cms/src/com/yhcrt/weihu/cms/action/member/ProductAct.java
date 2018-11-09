package com.yhcrt.weihu.cms.action.member;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.yhcrt.weihu.cms.Constants.TPLDIR_EXCHANGE;
//import static com.yhcrt.weihu.cms.Constants.TPLDIR_MEMBER;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yhcrt.weihu.cms.entity.assist.CmsChange;
//import com.yhcrt.weihu.cms.entity.assist.CmsTT;
//import com.yhcrt.weihu.cms.entity.main.Channel;
import com.yhcrt.weihu.cms.entity.main.CmsModel;
import com.yhcrt.weihu.cms.entity.main.Content;
import com.yhcrt.weihu.cms.entity.main.ContentDoc;
import com.yhcrt.weihu.cms.entity.main.ContentExt;
import com.yhcrt.weihu.cms.entity.main.ContentTxt;
import com.yhcrt.weihu.cms.entity.main.ContentType;
import com.yhcrt.weihu.cms.manager.assist.CmsChangeMng;
import com.yhcrt.weihu.cms.manager.assist.CmsFileMng;
import com.yhcrt.weihu.cms.manager.main.CmsModelMng;
import com.yhcrt.weihu.cms.manager.main.ContentMng;
import com.yhcrt.weihu.cms.manager.main.ContentTypeMng;
import com.yhcrt.weihu.common.util.StrUtils;
import com.yhcrt.weihu.common.web.RequestUtils;
import com.yhcrt.weihu.core.entity.CmsSite;
import com.yhcrt.weihu.core.entity.CmsUser;
import com.yhcrt.weihu.core.entity.MemberConfig;
import com.yhcrt.weihu.core.web.WebErrors;
import com.yhcrt.weihu.core.web.util.CmsUtils;
import com.yhcrt.weihu.core.web.util.FrontUtils;

/**
 * 换客Action
 * 
 */
@Controller
public class ProductAct extends AbstractContentMemberAct{

	public static final String PRODUCt_LIST = "tpl.productList";
	public static final String PRODUCt_ADD = "tpl.productAdd";
	public static final String PRODUCt_EDIT = "tpl.productEdit";

	/**
	 * 发布产品列表
	 * 
	 * @param channelId
	 *            栏目ID
	 * @param pageNo
	 *            页码
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/product_list.jspx")
	public String list(String queryTitle, Integer modelId,
			Integer queryChannelId, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		//CmsUser user = CmsUtils.getUser(request);
		List<CmsChange> exchangeList = changeMng.getList(site.getId(), null, null, true);
		model.addAttribute("exchangeList", exchangeList);
		return super.list(queryTitle, modelId, 60, PRODUCt_LIST,
				pageNo, request, model);
	}

	/**
	 * 产品添加
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/product_add.jspx")
	public String add(HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		model.addAttribute("site", site);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_EXCHANGE, PRODUCt_ADD);
	}

	/**
	 * 产品保存
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/product_save.jspx")
	public String save(String title, String txt,String tagStr, Integer channelId, ContentDoc doc,
			String[] picPaths, String[] picDescs,HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {

		CmsSite site = CmsUtils.getSite(request);
		CmsUser user = CmsUtils.getUser(request);
		
		Content bean = new Content();
		bean.setSite(site);
		bean.setChangeState(0);
		CmsModel  m = cmsModelMng.findById(12);
		bean.setModel(m);
		
		bean.setAttr(RequestUtils.getRequestMap(request, "attr_"));
		String[] tagArr = StrUtils.splitAndTrim(tagStr, ",",null);
		
		ContentExt ext = new ContentExt();
		ext.setTitle(title);
		ContentTxt t = new ContentTxt();
		t.setTxt(txt);
		ContentType type = contentTypeMng.getDef();
		if (type == null) {
			throw new RuntimeException("Default ContentType not found.");
		}
		Integer typeId = type.getId();
		bean = manager.save(bean, ext, t, null, null, null, null, null, tagArr, null,null, null,
				picPaths,picDescs,channelId, typeId, null,false, user, true);
		//处理附件
		fileMng.updateFileByPaths(null,picPaths,null,ext.getTitleImg(),ext.getTypeImg(),ext.getContentImg(),true,bean);
		model.addAttribute("site", site);
		FrontUtils.frontData(request, model, site);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_EXCHANGE, PRODUCt_ADD);
	}

	/**
	 * 产品修改
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/product_edit.jspx")
	public String edit(Integer id, String nextUrl,HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
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
		WebErrors errors = validateEdit(id, site, user, request);
		if (errors.hasErrors()) {
			return FrontUtils.showError(request, response, model, errors);
		}
		Content content = contentMng.findById(id);
	
		model.addAttribute("content", content);
		model.addAttribute("sessionId",request.getSession().getId());
		model.addAttribute("site", site);
		FrontUtils.frontData(request, model, site);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_EXCHANGE, PRODUCt_EDIT);
	}

	@RequestMapping(value = "/member/product_update.jspx")
	public String update(Content content,Integer id, String title, String txt,String tagStr, Integer channelId, ContentDoc doc,
			String[] picPaths, String[] picDescs,
			String nextUrl, HttpServletRequest request,
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
		WebErrors errors = validateUpdate(id, channelId, site, user, request);
		if (errors.hasErrors()) {
			return FrontUtils.showError(request, response, model, errors);
		}
		ContentExt ext = new ContentExt();
		ext.setId(id);
		ext.setTitle(title);
		ContentTxt t = new ContentTxt();
		t.setId(id);
		t.setTxt(txt);

		Map<String, String> attr = RequestUtils.getRequestMap(request, "attr_");
		String[] tagArr = StrUtils.splitAndTrim(tagStr, ",",null);


		ContentType type = contentTypeMng.getDef();
		if (type == null) {
			throw new RuntimeException("Default ContentType not found.");
		}
		Integer typeId = type.getId();
		content = manager.update(content, ext, t,null, tagArr, null, null, null, null,  null,null, null
				,picPaths,picDescs, attr, channelId, typeId, null, user, true);
		//处理附件
		fileMng.updateFileByPaths(null,picPaths,null,ext.getTitleImg(),ext.getTypeImg(),ext.getContentImg(),true,content);

		return FrontUtils.showSuccess(request, model, nextUrl);
	}
    //产品删除
	@RequestMapping(value = "/member/product_delete.jspx")
	public String delete(Integer[] ids, HttpServletRequest request,
			String nextUrl, HttpServletResponse response, ModelMap model) {
	
		return super.delete(ids, request, nextUrl, response, model);
	}
	private WebErrors validateUpdate(Integer id, Integer channelId,
			CmsSite site, CmsUser user, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (vldOpt(errors, site, user, new Integer[] { id })) {
			return errors;
		}
		return errors;
	}
	private WebErrors validateEdit(Integer id, CmsSite site, CmsUser user,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (vldOpt(errors, site, user, new Integer[] { id })) {
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
			Content tt = manager.findById(id);
			// 数据不存在
			if (errors.ifNotExist(tt, Content.class, id)) {
				return true;
			}
			// 非本用户数据
			if (!tt.getUser().getId().equals(user.getId())) {
				errors.noPermission(Content.class, id);
				return true;
			}
		}
		return false;
	}
	@Autowired
	private ContentMng manager;
	@Autowired
	private CmsFileMng fileMng;
	@Autowired
	protected ContentTypeMng contentTypeMng;
	@Autowired
	protected CmsModelMng cmsModelMng;
	@Autowired
	protected CmsChangeMng changeMng;
}