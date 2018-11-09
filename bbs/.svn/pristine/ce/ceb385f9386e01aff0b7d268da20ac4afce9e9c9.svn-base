package com.yhcrt.weihu.bbs.action.front;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yhcrt.weihu.bbs.web.CmsUtils;
import com.yhcrt.weihu.bbs.web.FrontUtils;
import com.yhcrt.weihu.common.web.RequestUtils;
import com.yhcrt.weihu.core.entity.CmsSite;

@Controller
public class BbsTopicSearchAct {
	public static final String SEARCH_RESULT = "tpl.search";

	@RequestMapping(value = "/topic/search*.jhtml")
	public String searchSubmit(HttpServletRequest request,
			ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		String keywords = RequestUtils.getQueryParam(request, "keywords");
		String forumIdStr = RequestUtils.getQueryParam(request, "forumId");
		String searchType = RequestUtils.getQueryParam(request, "searchType");
		Integer forumId = 0;
		if (forumIdStr != null && StringUtils.isNotBlank(forumIdStr)) {
			forumId = Integer.parseInt(forumIdStr);
		}
		model.put("keywords", keywords);
		model.put("forumId", forumId);
		model.put("searchType", searchType);
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);
		if(searchType.equals("0")){
			return "/WEB-INF/t/cms/www/blue/search/search_topic.html";
		}else if(searchType.equals("1")){
			return "/WEB-INF/t/cms/www/blue/search/search_user.html";
		}else{
			return "/WEB-INF/t/cms/www/blue/search/search_topic.html";
		}
	}

}
