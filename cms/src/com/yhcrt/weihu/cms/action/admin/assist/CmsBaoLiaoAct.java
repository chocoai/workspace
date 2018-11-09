package com.yhcrt.weihu.cms.action.admin.assist;

import static com.yhcrt.weihu.common.page.SimplePage.cpn;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yhcrt.weihu.cms.entity.assist.CmsBaoLiao;
import com.yhcrt.weihu.cms.manager.assist.CmsBaoLiaoMng;
import com.yhcrt.weihu.common.page.Pagination;
import com.yhcrt.weihu.common.web.CookieUtils;
import com.yhcrt.weihu.core.entity.CmsSite;
import com.yhcrt.weihu.core.manager.CmsLogMng;
import com.yhcrt.weihu.core.manager.CmsUserMng;
import com.yhcrt.weihu.core.web.WebErrors;
import com.yhcrt.weihu.core.web.util.CmsUtils;

@Controller
public class CmsBaoLiaoAct {
	private static final Logger log = LoggerFactory.getLogger(CmsBaoLiaoAct.class);

	@RequiresPermissions("baoliao:v_list")
	@RequestMapping("/baoliao/v_list.do")
	public String list(String queryTitle, Boolean queryRecommend,
			Boolean queryChecked, Integer pageNo,HttpServletRequest request, ModelMap model) {
		Pagination pagination = manager.getPage(CmsUtils.getSiteId(request), null, null, queryTitle, queryRecommend, queryChecked,
				true, false, cpn(pageNo), CookieUtils.getPageSize(request));
			
		model.addAttribute("pagination", pagination);
		model.addAttribute("pageNo", pagination.getPageNo());
		model.addAttribute("queryTitle", queryTitle);
		model.addAttribute("queryRecommend", queryRecommend);
		model.addAttribute("queryChecked", queryChecked);
		return "baoliao/list";
	}
	@RequiresPermissions("baoliao:v_add")
	@RequestMapping("/baoliao/v_add.do")
	public String add(HttpServletRequest request, ModelMap model) {
		CmsBaoLiao baoliao = new CmsBaoLiao();
		
		model.addAttribute("baoliao", baoliao);
		return "baoliao/add";
	}

	@RequiresPermissions("baoliao:v_edit")
	@RequestMapping("/baoliao/v_edit.do")
	public String edit(Integer id, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsBaoLiao baoliao = manager.findById(id);
		
		model.addAttribute("baoliao", baoliao);
		return "baoliao/edit";
	}

//	@RequiresPermissions("baoliao:o_save")
//	@RequestMapping("/baoliao/o_save.do")
//	public String save(CmsBaoLiao bean, 
//			HttpServletRequest request, ModelMap model) {
//		WebErrors errors = validateSave(bean, request);
//		if (errors.hasErrors()) {
//			return errors.showErrorPage(model);
//		}
//		CmsSite site = CmsUtils.getSite(request);
//		CmsUser admin = CmsUtils.getUser(request);
//		
//		bean.setSite(site);
//		bean.setAdmin(admin);
//		bean.setReplyTime(new Timestamp(System.currentTimeMillis()));
//		bean = manager.saveBaoLiao(bean);
//		log.info("save CmsBaoLiao id={}", bean.getId());
//		cmsLogMng.operating(request, "cmsBaoLiao.log.save", "id="
//				+ bean.getId() + ";title=" + bean.getTitle());
//		return "redirect:v_list.do";
//	}

	@RequiresPermissions("baoliao:o_update")
	@RequestMapping("/baoliao/o_update.do")
	public String update(CmsBaoLiao bean, 
			 Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateUpdate(bean.getId(), request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		if(bean.getAdmin()!=null){
			bean.setAdmin(CmsUtils.getUser(request));
		}
		bean.setReplyTime(new Timestamp(System.currentTimeMillis()));
		bean = manager.update(bean);
		log.info("update CmsBaoLiao id={}.", bean.getId());
		cmsLogMng.operating(request, "cmsBaoLiao.log.update", "id="
				+ bean.getId() + ";title=" + bean.getTitle());
		return "redirect:v_list.do";
	}

	
	@RequiresPermissions("baoliao:o_delete")
	@RequestMapping("/baoliao/o_delete.do")
	public String delete(String queryTitle, Boolean queryRecommend,
			Boolean queryChecked,Integer[] ids, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsBaoLiao[] beans = manager.deleteByIds(ids);
		for (CmsBaoLiao bean : beans) {
			log.info("delete CmsBaoLiao id={}", bean.getId());
		}
		return list(queryTitle,queryRecommend, queryChecked,pageNo, request, model);
	}
	@RequiresPermissions("baoliao:o_check")
	@RequestMapping("/baoliao/o_check.do")
	public String check(String queryTitle, Boolean queryRecommend,
			Boolean queryChecked, Integer[] ids, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsBaoLiao[] beans = manager.checkByIds(ids,CmsUtils.getUser(request),true);
		for (CmsBaoLiao bean : beans) {
			log.info("delete CmsBaoLiao id={}", bean.getId());
			cmsLogMng.operating(request, "cmsBaoLiao.log.check", "id="
					+ bean.getId() + ";title=" + bean.getTitle());
		}
		return list(queryTitle, queryRecommend, queryChecked, pageNo, request,
				model);
	}
	
	@RequiresPermissions("baoliao:o_check_cancel")
	@RequestMapping("/baoliao/o_check_cancel.do")
	public String cancel_check(String queryTitle, Boolean queryRecommend,
			Boolean queryChecked, Integer[] ids, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsBaoLiao[] beans = manager.checkByIds(ids,CmsUtils.getUser(request),false);
		for (CmsBaoLiao bean : beans) {
			log.info("delete CmsBaoLiao id={}", bean.getId());
			cmsLogMng.operating(request, "cmsBaoLiao.log.check", "id="
					+ bean.getId() + ";title=" + bean.getTitle());
		}
		return list(queryTitle, queryRecommend, queryChecked, pageNo, request,
				model);
	}
	
//	private WebErrors validateSave(CmsBaoLiao bean, HttpServletRequest request) {
//		WebErrors errors = WebErrors.create(request);
//		CmsSite site = CmsUtils.getSite(request);
//		bean.setSite(site);
//		return errors;
//	}
	
	private WebErrors validateEdit(Integer id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		CmsSite site = CmsUtils.getSite(request);
		if (vldExist(id,site.getId(), errors)) {
			return errors;
		}
		return errors;
	}

	private WebErrors validateUpdate(Integer id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		CmsSite site = CmsUtils.getSite(request);
		if (vldExist(id,site.getId(), errors)) {
			return errors;
		}
		return errors;
	}

	private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		CmsSite site = CmsUtils.getSite(request);
		if (errors.ifEmpty(ids, "ids")) {
			return errors;
		}
		for (Integer id : ids) {
			vldExist(id, site.getId(),errors);
		}
		return errors;
	}

	private boolean vldExist(Integer id, Integer siteId,  WebErrors errors) {
		if (errors.ifNull(id, "id")) {
			return true;
		}
		CmsBaoLiao entity = manager.findById(id);
		if (errors.ifNotExist(entity, CmsBaoLiao.class, id)) {
			return true;
		}
		if (!entity.getSite().getId().equals(siteId)) {
			errors.notInSite(CmsBaoLiao.class, id);
			return true;
		}
		return false;
	}
	
	@Autowired
	private CmsBaoLiaoMng manager;
	@Autowired
	private CmsUserMng userMng;
	@Autowired
	private CmsLogMng cmsLogMng;
}