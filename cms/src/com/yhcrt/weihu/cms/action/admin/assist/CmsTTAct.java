package com.yhcrt.weihu.cms.action.admin.assist;

import static com.yhcrt.weihu.common.page.SimplePage.cpn;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yhcrt.weihu.cms.entity.assist.CmsTT;
import com.yhcrt.weihu.cms.entity.assist.CmsTType;
import com.yhcrt.weihu.cms.manager.assist.CmsFileMng;
import com.yhcrt.weihu.cms.manager.assist.CmsTTMng;
import com.yhcrt.weihu.cms.manager.assist.CmsTTypeMng;
import com.yhcrt.weihu.common.page.Pagination;
import com.yhcrt.weihu.common.web.CookieUtils;
import com.yhcrt.weihu.core.entity.CmsSite;
import com.yhcrt.weihu.core.entity.CmsUser;
import com.yhcrt.weihu.core.manager.CmsLogMng;
import com.yhcrt.weihu.core.web.WebErrors;
import com.yhcrt.weihu.core.web.util.CmsUtils;

@Controller
public class CmsTTAct {
	private static final Logger log = LoggerFactory
			.getLogger(CmsTTAct.class);

	@RequiresPermissions("tt:v_list")
	@RequestMapping("/tt/v_list.do")
	public String list(Integer queryTypeId,Integer isAdmin,Integer queryDemand, Boolean queryRecommend,
			Boolean queryChecked, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		Pagination pagination = manager.getPage(site.getId(),queryTypeId,isAdmin,queryDemand,null,
				queryRecommend, queryChecked, true, false, cpn(pageNo),
				CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		model.addAttribute("pageNo", pagination.getPageNo());
		model.addAttribute("queryTypeId", queryTypeId);
		model.addAttribute("queryDemand", queryDemand);
		model.addAttribute("queryRecommend", queryRecommend);
		model.addAttribute("queryChecked", queryChecked);
		model.addAttribute("typeList",cmsTTypeMng.getList(site.getId()));
		
		return "tt/list";
	}

	@RequiresPermissions("tt:v_add")
	@RequestMapping("/tt/v_add.do")
	public String add(HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsTT cmsTT = new CmsTT();
		List<CmsTType> typeList = cmsTTypeMng
				.getList(site.getId());
		model.addAttribute("typeList", typeList);
		model.addAttribute("cmsTT", cmsTT);
		return "tt/add";
	}

	@RequiresPermissions("tt:v_edit")
	@RequestMapping("/tt/v_edit.do")
	public String edit(Integer id, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsTT cmsTT = manager.findById(id);
		List<CmsTType> typeList = cmsTTypeMng
				.getList(site.getId());

		model.addAttribute("cmsTT", cmsTT);
		model.addAttribute("typeList", typeList);
		model.addAttribute("pageNo", pageNo);
		return "tt/edit";
	}

	@RequiresPermissions("tt:o_save")
	@RequestMapping("/tt/o_save.do")
	public String save(CmsTT bean, Integer typeId,String[] picPaths, String[] picDescs,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsSite site = CmsUtils.getSite(request);
		CmsUser admin = CmsUtils.getUser(request);
		CmsTType type = cmsTTypeMng.findById(typeId);
		bean.setType(type);
		bean.setSite(site);
		bean.setAdmin(admin);
		bean.setIsAdmin(1);
		bean.setCreateTime(new Timestamp(System.currentTimeMillis()));
		bean = manager.save(bean, picPaths, picDescs);
		//处理附件
		fileMng.updateFileByPathsTT(picPaths, null, true, bean);
		log.info("save CmsTT id={}", bean.getId());
		cmsLogMng.operating(request, "cmsTT.log.save", "id="
				+ bean.getId() + ";title=" + bean.getTitle());
		return "redirect:v_list.do";
	}

	@RequiresPermissions("tt:o_update")
	@RequestMapping("/tt/o_update.do")
	public String update(Integer queryTypeId,Integer isAdmin,Integer queryDemand,Boolean queryRecommend,
			Boolean queryChecked, CmsTT bean, String[] picPaths,String[] picDescs,String[] oldpicPaths,
			 Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateUpdate(bean.getId(), request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
//		if(bean.getAdmin()!=null){
//				if(!bean.getAdmin().equals(CmsUtils.getUser(request))){
//					bean.setAdmin(CmsUtils.getUser(request));
//				}
//			}else{
//				bean.setAdmin(CmsUtils.getUser(request));
//		}
		bean = manager.update(bean,picPaths, picDescs);
		//处理之前的附件有效性
		fileMng.updateFileByPathsTT(oldpicPaths,null,false,bean);
		//处理更新后的附件有效性
		fileMng.updateFileByPathsTT(picPaths,null,true,bean);
		log.info("update CmsTT id={}.", bean.getId());
		cmsLogMng.operating(request, "cmsTT.log.update", "id="
				+ bean.getId() + ";title=" + bean.getTitle());
		return list(queryTypeId, isAdmin,queryDemand,queryRecommend, queryChecked, pageNo, request,
				model);
	}

	@RequiresPermissions("tt:o_delete")
	@RequestMapping("/tt/o_delete.do")
	public String delete(Integer queryTypeId,Integer isAdmin,Integer queryDemand, Boolean queryRecommend,
			Boolean queryChecked, Integer[] ids, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsTT[] beans = manager.deleteByIds(ids);
		for (CmsTT bean : beans) {
			log.info("delete CmsTT id={}", bean.getId());
			cmsLogMng.operating(request, "CmsTT.log.delete", "id="
					+ bean.getId() + ";title=" + bean.getTitle());
		}
		return list(queryTypeId,isAdmin,queryDemand, queryRecommend, queryChecked, pageNo, request,
				model);
	}
	
	@RequiresPermissions("tt:o_check")
	@RequestMapping("/tt/o_check.do")
	public String check(Integer queryTypeId,Integer isAdmin,Integer queryDemand, Boolean queryRecommend,
			Boolean queryChecked, Integer[] ids, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsTT[] beans = manager.checkByIds(ids,CmsUtils.getUser(request),true);
		for (CmsTT bean : beans) {
			log.info("delete CmsTT id={}", bean.getId());
			cmsLogMng.operating(request, "cmsTT.log.check", "id="
					+ bean.getId() + ";title=" + bean.getTitle());
		}
		return list(queryTypeId, isAdmin,queryDemand,queryRecommend, queryChecked, pageNo, request,
				model);
	}
	
	@RequiresPermissions("tt:o_check_cancel")
	@RequestMapping("/tt/o_check_cancel.do")
	public String cancel_check(Integer queryTypeId, Integer isAdmin,Integer queryDemand,Boolean queryRecommend,
			Boolean queryChecked, Integer[] ids, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsTT[] beans = manager.checkByIds(ids,CmsUtils.getUser(request),false);
		for (CmsTT bean : beans) {
			log.info("delete CmsTT id={}", bean.getId());
			cmsLogMng.operating(request, "cmsTT.log.check", "id="
					+ bean.getId() + ";title=" + bean.getTitle());
		}
		return list(queryTypeId, isAdmin,queryDemand,queryRecommend, queryChecked, pageNo, request,
				model);
	}

	private WebErrors validateSave(CmsTT bean, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		CmsSite site = CmsUtils.getSite(request);
		bean.setSite(site);
		return errors;
	}

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
		CmsTT entity = manager.findById(id);
		if (errors.ifNotExist(entity, CmsTT.class, id)) {
			return true;
		}
		if (!entity.getSite().getId().equals(siteId)) {
			errors.notInSite(CmsTT.class, id);
			return true;
		}
		return false;
	}

	@Autowired
	private CmsTTypeMng cmsTTypeMng;
	@Autowired
	private CmsLogMng cmsLogMng;
	@Autowired
	private CmsTTMng manager;
	@Autowired
	private CmsFileMng fileMng;
}