package com.yhcrt.weihu.bbs.action;

import static com.yhcrt.weihu.common.page.SimplePage.cpn;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yhcrt.weihu.bbs.manager.BbsWebserviceCallRecordMng;
import com.yhcrt.weihu.common.page.Pagination;
import com.yhcrt.weihu.common.web.CookieUtils;

@Controller
public class BbsWebserviceCallRecordAct {

	@RequestMapping("/webserviceCallRecord/v_list.do")
	public String list(Integer pageNo, HttpServletRequest request, ModelMap model) {
		Pagination pagination = manager.getPage(cpn(pageNo), CookieUtils
				.getPageSize(request));
		model.addAttribute("pagination",pagination);
		return "webserviceCallRecord/list";
	}

	@RequestMapping("/webserviceCallRecord/o_delete.do")
	public String delete(Integer[] ids, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		manager.deleteByIds(ids);
		return list(pageNo, request, model);
	}
	
	
	@Autowired
	private BbsWebserviceCallRecordMng manager;
}