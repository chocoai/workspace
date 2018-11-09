package com.yhcrt.weihu.bbs.action;

import static com.yhcrt.weihu.common.page.SimplePage.cpn;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yhcrt.weihu.bbs.action.BbsThirdAccountAct;
import com.yhcrt.weihu.bbs.entity.BbsThirdAccount;
import com.yhcrt.weihu.bbs.manager.BbsThirdAccountMng;
import com.yhcrt.weihu.bbs.web.CmsUtils;
import com.yhcrt.weihu.common.page.Pagination;
import com.yhcrt.weihu.common.web.CookieUtils;
import com.yhcrt.weihu.core.entity.CmsSite;
import com.yhcrt.weihu.core.web.WebErrors;

@Controller
public class BbsThirdAccountAct {
	private static final Logger log = LoggerFactory.getLogger(BbsThirdAccountAct.class);

	@RequestMapping("/account/v_list.do")
	public String list(String username,String source,Integer pageNo, HttpServletRequest request, ModelMap model) {
		Pagination pagination = manager.getPage(username,source,cpn(pageNo), CookieUtils
				.getPageSize(request));
		model.addAttribute("username",username);
		model.addAttribute("source",source);
		model.addAttribute("pagination",pagination);
		model.addAttribute("pageNo",pagination.getPageNo());
		return "account/list";
	}
	
	@RequestMapping("/account/o_delete.do")
	public String delete(String username,String source,Long[] ids, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		BbsThirdAccount[] beans = manager.deleteByIds(ids);
		for (BbsThirdAccount bean : beans) {
			log.info("delete BbsThirdAccount id={}", bean.getId());
		}
		return list(username,source,pageNo, request, model);
	}

	
	private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		CmsSite site = CmsUtils.getSite(request);
		if (errors.ifEmpty(ids, "ids")) {
			return errors;
		}
		for (Long id : ids) {
			vldExist(id, site.getId(), errors);
		}
		return errors;
	}

	private boolean vldExist(Long id, Integer siteId, WebErrors errors) {
		if (errors.ifNull(id, "id")) {
			return true;
		}
		BbsThirdAccount entity = manager.findById(id);
		if(errors.ifNotExist(entity, BbsThirdAccount.class, id)) {
			return true;
		}
		return false;
	}
	
	@Autowired
	private BbsThirdAccountMng manager;
}