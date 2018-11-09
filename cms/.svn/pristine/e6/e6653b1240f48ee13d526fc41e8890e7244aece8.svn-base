package com.yhcrt.weihu.cms.action.admin.assist;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yhcrt.weihu.cms.entity.assist.CmsTType;
import com.yhcrt.weihu.cms.manager.assist.CmsTTypeMng;
import com.yhcrt.weihu.common.web.ResponseUtils;
import com.yhcrt.weihu.core.entity.CmsSite;
import com.yhcrt.weihu.core.manager.CmsLogMng;
import com.yhcrt.weihu.core.web.WebErrors;
import com.yhcrt.weihu.core.web.util.CmsUtils;

@Controller
public class CmsTTypeAct {
	private static final Logger log = LoggerFactory
			.getLogger(CmsTTypeAct.class);

	@RequiresPermissions("tt_type:v_list")
	@RequestMapping("/tt_type/v_list.do")
	public String list(Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		List<CmsTType> list = manager.getList(site.getId());
		model.addAttribute("list", list);
		return "tt_type/list";
	}

	@RequiresPermissions("tt_type:v_add")
	@RequestMapping("/tt_type/v_add.do")
	public String add(ModelMap model) {
		return "tt_type/add";
	}

	@RequiresPermissions("tt_type:v_edit")
	@RequestMapping("/tt_type/v_edit.do")
	public String edit(Integer id, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		model.addAttribute("CmsTType", manager.findById(id));
		model.addAttribute("pageNo", pageNo);
		return "tt_type/edit";
	}
	
	@RequiresPermissions("tt_type:v_ajax_edit")
	@RequestMapping("/tt_type/v_ajax_edit.do")
	public void ajaxEdit(Integer id, HttpServletRequest request,HttpServletResponse response, ModelMap model) throws JSONException {
		JSONObject object = new JSONObject();
		CmsTType ctg=manager.findById(id);
		if(ctg!=null){
			object.put("id", ctg.getId());
			object.put("name", ctg.getName());
		}
		ResponseUtils.renderJson(response, object.toString());
	}

	@RequiresPermissions("tt_type:o_save")
	@RequestMapping("/tt_type/o_save.do")
	public String save(CmsTType bean, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = manager.save(bean);
		log.info("save CmsTType id={}", bean.getId());
		cmsLogMng.operating(request, "cmsTType.log.save", "id="
				+ bean.getId() + ";name=" + bean.getName());
		return "redirect:v_list.do";
	}

	@RequiresPermissions("tt_type:o_update")
	@RequestMapping("/tt_type/o_update.do")
	public String update(CmsTType bean, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateUpdate(bean.getId(), request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = manager.update(bean);
		log.info("update CmsTType id={}.", bean.getId());
		cmsLogMng.operating(request, "cmsTType.log.update", "id="
				+ bean.getId() + ";name=" + bean.getName());
		return list(pageNo, request, model);
	}

	@RequiresPermissions("tt_type:o_delete")
	@RequestMapping("/tt_type/o_delete.do")
	public String delete(Integer[] ids, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsTType[] beans = manager.deleteByIds(ids);
		for (CmsTType bean : beans) {
			log.info("delete CmsTType id={}", bean.getId());
			cmsLogMng.operating(request, "cmsTType.log.delete", "id="
					+ bean.getId() + ";name=" + bean.getName());
		}
		return list(pageNo, request, model);
	}

	private WebErrors validateSave(CmsTType bean,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		CmsSite site = CmsUtils.getSite(request);
		bean.setSite(site);
		return errors;
	}

	private WebErrors validateEdit(Integer id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (vldExist(id, errors)) {
			return errors;
		}
		return errors;
	}

	private WebErrors validateUpdate(Integer id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (vldExist(id, errors)) {
			return errors;
		}
		return errors;
	}

	private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (errors.ifEmpty(ids, "ids")) {
			return errors;
		}
		for (Integer id : ids) {
			vldExist(id, errors);
		}
		return errors;
	}

	private boolean vldExist(Integer id, WebErrors errors) {
		if (errors.ifNull(id, "id")) {
			return true;
		}
		CmsTType entity = manager.findById(id);
		if (errors.ifNotExist(entity, CmsTType.class, id)) {
			return true;
		}
		return false;
	}

	@Autowired
	private CmsLogMng cmsLogMng;
	@Autowired
	private CmsTTypeMng manager;
}