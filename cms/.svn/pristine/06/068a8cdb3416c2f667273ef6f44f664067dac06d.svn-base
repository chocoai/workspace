package com.yhcrt.weihu.cms.action.front;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yhcrt.weihu.cms.Constants;
import com.yhcrt.weihu.cms.entity.assist.CmsSearchWords;
import com.yhcrt.weihu.cms.entity.main.Channel;
import com.yhcrt.weihu.cms.manager.assist.CmsSearchWordsMng;
import com.yhcrt.weihu.cms.manager.main.ChannelMng;
import com.yhcrt.weihu.cms.service.SearchWordsCache;
import com.yhcrt.weihu.common.web.RequestUtils;
import com.yhcrt.weihu.common.web.ResponseUtils;
import com.yhcrt.weihu.core.entity.CmsSite;
import com.yhcrt.weihu.core.web.util.CmsUtils;
import com.yhcrt.weihu.core.web.util.FrontUtils;

@Controller
public class SearchAct {
	public static final String SEARCH_INPUT = "tpl.searchInput";

	public static final String SEARCH_RESULT = "tpl.searchResult";

	public static final String SEARCH_ERROR = "tpl.searchError";

	public static final String SEARCH_JOB = "tpl.searchJob";

	public static final String SEARCH_HOUSE = "tpl.searchHouse";

	public static final String SEARCH_FC = "tpl.searchFc";

	public static final String SEARCH_PRODUCt = "tpl.findProduct";

	public static final String SEARCH_TT = "tpl.findTT";

	@RequestMapping(value = "/searchCont*.jspx", method = RequestMethod.GET)
	public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		// 将request中所有参数保存至model中。
		model.putAll(RequestUtils.getQueryParams(request));
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);
		String q = RequestUtils.getQueryParam(request, "q");
		String channelId = RequestUtils.getQueryParam(request, "channelId");
		if (StringUtils.isBlank(q) && StringUtils.isBlank(channelId)) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(), Constants.TPLDIR_SPECIAL, SEARCH_INPUT);
		} else {
			String parseQ = parseKeywords(q);
			model.addAttribute("input", q);
			model.addAttribute("q", parseQ);
			searchWordsCache.cacheWord(q);
			return FrontUtils.getTplPath(request, site.getSolutionPath(), Constants.TPLDIR_SPECIAL, SEARCH_RESULT);
		}
	}

	@RequestMapping(value = "/findProduct*.jspx", method = RequestMethod.GET)
	public String findProduct(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		String keywords = RequestUtils.getQueryParam(request, "keywords");
		model.putAll(RequestUtils.getQueryParams(request));
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);
		model.addAttribute("keywords", keywords);
		return FrontUtils.getTplPath(request, site.getSolutionPath(), Constants.TPLDIR_SPECIAL, SEARCH_PRODUCt);
	}

	@RequestMapping(value = "/findTT*.jspx", method = RequestMethod.GET)
	public String findTT(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		String keywords = RequestUtils.getQueryParam(request, "keywords");
		String channelId = RequestUtils.getQueryParam(request, "channelId");
		model.putAll(RequestUtils.getQueryParams(request));
		if (channelId == null || "".equals(channelId)) {
			channelId = "146";
		}
		Channel channel = channelMng.findById(Integer.parseInt(channelId));
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);
		model.addAttribute("keywords", keywords);
		model.addAttribute("channel", channel);
		return FrontUtils.getTplPath(request, site.getSolutionPath(), Constants.TPLDIR_SPECIAL, SEARCH_TT);
	}

	/**
	 * 根据所选条件搜索楼盘
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/searchHouse*.jspx", method = RequestMethod.GET)
	public String searchHouse(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		String area = RequestUtils.getQueryParam(request, "area");
		String price = RequestUtils.getQueryParam(request, "price");
		String acreage = RequestUtils.getQueryParam(request, "acreage");
		String channelId = RequestUtils.getQueryParam(request, "channelId");
		if (StringUtils.isNotBlank(channelId)) {
			Channel channel = channelMng.findById(Integer.parseInt(channelId));
			model.addAttribute("channel", channel);
		}
		model.putAll(RequestUtils.getQueryParams(request));
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);
		model.addAttribute("queryArea", area);
		model.addAttribute("queryPrice", price);
		model.addAttribute("queryAcreage", acreage);
		return FrontUtils.getTplPath(request, site.getSolutionPath(), Constants.TPLDIR_SPECIAL, SEARCH_HOUSE);
	}

	@RequestMapping(value = "/houseSearch*.jspx", method = RequestMethod.GET)
	public String houseSearch(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		String keyword = RequestUtils.getQueryParam(request, "keyword");
		model.putAll(RequestUtils.getQueryParams(request));
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);
		model.addAttribute("keyword", keyword);
		return FrontUtils.getTplPath(request, site.getSolutionPath(), Constants.TPLDIR_SPECIAL, SEARCH_FC);
	}

	@RequestMapping("/search/v_ajax_list.jspx")
	public void ajaxList(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws JSONException {
		JSONObject object = new JSONObject();
		Map<String, String> wordsMap = new LinkedHashMap<String, String>();
		String word = RequestUtils.getQueryParam(request, "term");
		if (StringUtils.isNotBlank(word)) {
			List<CmsSearchWords> words = manager.getList(word, CmsSearchWords.HIT_DESC, true);
			for (CmsSearchWords w : words) {
				wordsMap.put(w.getName(), w.getName());
			}
		}
		object.put("words", wordsMap);
		ResponseUtils.renderJson(response, object.get("words").toString());
	}

	public static String parseKeywords(String q) {
		char c = '\\';
		int cIndex = q.indexOf(c);
		if (cIndex != -1 && cIndex == 0) {
			q = q.substring(1);
		}
		if (cIndex != -1 && cIndex == q.length() - 1) {
			q = q.substring(0, q.length() - 1);
		}
		try {
			String regular = "[\\+\\-\\&\\|\\!\\(\\)\\{\\}\\[\\]\\^\\~\\*\\?\\:\\\\]";
			Pattern p = Pattern.compile(regular);
			Matcher m = p.matcher(q);
			String src = null;
			while (m.find()) {
				src = m.group();
				q = q.replaceAll("\\" + src, ("\\\\" + src));
			}
			q = q.replaceAll("AND", "and").replaceAll("OR", "or").replace("NOT", "not").replace("[", "［").replace("]",
					"］");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return q;
	}

	@Autowired
	private ChannelMng channelMng;

	@Autowired
	private CmsSearchWordsMng manager;

	@Autowired
	private SearchWordsCache searchWordsCache;
}
