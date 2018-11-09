/**
 * 
 */
package com.whty.assis.devicemanage.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.whty.assis.base.controller.BaseController;
import com.whty.assis.basicdata.model.School;
import com.whty.assis.basicdata.model.SchoolClassAppWhitelistRule;
import com.whty.assis.basicdata.model.SchoolClassBrowserTagRule;
import com.whty.assis.basicdata.model.SchoolClassBrowserWhitelistRule;
import com.whty.assis.basicdata.model.SchoolClassUrlBlacklistRule;
import com.whty.assis.basicdata.model.SchoolClassUsetimeRule;
import com.whty.assis.basicdata.service.SchoolService;
import com.whty.assis.devicemanage.model.AppWhitelistRule;
import com.whty.assis.devicemanage.model.BrowserTagRule;
import com.whty.assis.devicemanage.model.BrowserWhitelist;
import com.whty.assis.devicemanage.model.BrowserWhitelistRule;
import com.whty.assis.devicemanage.model.SchoolClassAppNetBlacklist;
import com.whty.assis.devicemanage.model.UrlBlackList;
import com.whty.assis.devicemanage.model.UrlBlackListRule;
import com.whty.assis.devicemanage.model.UseTimeRule;
import com.whty.assis.devicemanage.service.AppNetBlacklistService;
import com.whty.assis.devicemanage.service.AppWhitelistRuleService;
import com.whty.assis.devicemanage.service.BrowserTagRuleService;
import com.whty.assis.devicemanage.service.BrowserWhitelistRuleService;
import com.whty.assis.devicemanage.service.SchoolClassRuleService;
import com.whty.assis.devicemanage.service.UrlBlackListRuleService;
import com.whty.assis.devicemanage.service.UseTimeRuleService;
import com.whty.assis.sysres.model.TaManageUserInfo;
import com.whty.common.util.SysConstants;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * @author zhangzheng
 * @date 2018年5月3日
 */
@Controller
@RequestMapping("/manage/schoolClassRule")
public class SchoolClassRuleController extends BaseController {

	@Autowired
	private SchoolClassRuleService schoolClassRuleService;

	@Autowired
	private UrlBlackListRuleService urlBlackListRuleService;

	@Autowired
	private BrowserTagRuleService browserTagRuleService;

	@Autowired
	private BrowserWhitelistRuleService browserWhitelistRuleService;

	@Autowired
	private UseTimeRuleService useTimeRuleService;

	@Autowired
	private AppWhitelistRuleService appWhitelistRuleService;

	@Autowired
	private AppNetBlacklistService appNetBlacklistService;

	@Autowired
	private SchoolService schoolService;

	@RequestMapping(value = "/querySchoolClassBrowserWhitelistRule")
	public void querySchoolClassBrowserWhitelistRule(HttpServletRequest request, HttpServletResponse response,
			Model model) {
		String schoolClassId = request.getParameter("schoolClassId");

		Map<String, Object> paraMap = this.getParameterMap(request);

		PageContext page = PageContext.getContext();
		page.setCurrentPage(Integer.parseInt(paraMap.get("currPage").toString()));
		page.setPageSize(Integer.parseInt(paraMap.get("pageSize").toString()));
		HandlerResult pageList = browserWhitelistRuleService.queryBySchoolClassRule(Integer.valueOf(schoolClassId),
				page);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", pageList.getResultList());
		resultMap.put("page", pageList.getPage());
		printJson(response, resultMap);

	}

	@RequestMapping(value = "/querySchoolClassUrlBlacklistRule")
	public void querySchoolClassUrlBlacklistRule(HttpServletRequest request, HttpServletResponse response,
			Model model) {

		String schoolClassId = request.getParameter("schoolClassId");

		Map<String, Object> paraMap = this.getParameterMap(request);

		PageContext page = PageContext.getContext();
		page.setCurrentPage(Integer.parseInt(paraMap.get("currPage").toString()));
		page.setPageSize(Integer.parseInt(paraMap.get("pageSize").toString()));
		HandlerResult pageList = urlBlackListRuleService.queryBySchoolClassRule(Integer.valueOf(schoolClassId), page);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", pageList.getResultList());
		resultMap.put("page", pageList.getPage());
		printJson(response, resultMap);

	}

	@RequestMapping(value = "/querySchoolClassAppWhitelistRule")
	public void querySchoolClassAppWhitelistRule(HttpServletRequest request, HttpServletResponse response,
			Model model) {
		String schoolClassId = request.getParameter("schoolClassId");
		Map<String, Object> paraMap = this.getParameterMap(request);

		PageContext page = PageContext.getContext();
		page.setCurrentPage(Integer.parseInt(paraMap.get("currPage").toString()));
		page.setPageSize(Integer.parseInt(paraMap.get("pageSize").toString()));
		HandlerResult pageList = appWhitelistRuleService.queryBySchoolClassRule(Integer.valueOf(schoolClassId), page);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", pageList.getResultList());
		resultMap.put("page", pageList.getPage());
		printJson(response, resultMap);
	}

	@RequestMapping(value = "/querySchoolClassUsetimeRule")
	public void querySchoolClassUsetimeRule(HttpServletRequest request, HttpServletResponse response, Model model) {

		String schoolClassId = request.getParameter("schoolClassId");

		Map<String, Object> paraMap = this.getParameterMap(request);

		PageContext page = PageContext.getContext();
		page.setCurrentPage(Integer.parseInt(paraMap.get("currPage").toString()));
		page.setPageSize(Integer.parseInt(paraMap.get("pageSize").toString()));

		HandlerResult pageList = useTimeRuleService.queryBySchoolClassRule(Integer.valueOf(schoolClassId), page);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", pageList.getResultList());
		resultMap.put("page", pageList.getPage());
		printJson(response, resultMap);

	}

	@RequestMapping(value = "/querySchoolClassBrowserTagRule")
	public void querySchoolClassBrowserTagRule(HttpServletRequest request, HttpServletResponse response, Model model) {

		String schoolClassId = request.getParameter("schoolClassId");

		Map<String, Object> paraMap = this.getParameterMap(request);

		PageContext page = PageContext.getContext();
		page.setCurrentPage(Integer.parseInt(paraMap.get("currPage").toString()));
		page.setPageSize(Integer.parseInt(paraMap.get("pageSize").toString()));

		HandlerResult pageList = browserTagRuleService.queryBySchoolClassRule(Integer.valueOf(schoolClassId), page);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", pageList.getResultList());
		resultMap.put("page", pageList.getPage());
		printJson(response, resultMap);
	}

	@RequestMapping(value = "/queryRule")
	public void queryRule(HttpServletRequest request, HttpServletResponse response, Model model) {

		String schoolClassId = request.getParameter("schoolClassId");
		String ruleId = request.getParameter("ruleId");

		if (StringUtils.isEmpty(schoolClassId)) {
			return;
		}

		Map<String, Object> paraMap = this.getParameterMap(request);

		PageContext page = PageContext.getContext();
		page.setCurrentPage(Integer.parseInt(paraMap.get("currPage").toString()));
		page.setPageSize(Integer.parseInt(paraMap.get("pageSize").toString()));
		HandlerResult pageList = new HandlerResult();
		switch (Integer.valueOf(ruleId)) {
		case 1:
			pageList = useTimeRuleService.queryBySchoolClassRule(Integer.valueOf(schoolClassId), page);
			break;
		case 2:
			pageList = urlBlackListRuleService.queryBySchoolClassRule(Integer.valueOf(schoolClassId), page);
			break;
		case 3:
			pageList = appWhitelistRuleService.queryBySchoolClassRule(Integer.valueOf(schoolClassId), page);
			break;
		case 4:
			pageList = browserWhitelistRuleService.queryBySchoolClassRule(Integer.valueOf(schoolClassId), page);
			break;
		case 5:
			pageList = browserTagRuleService.queryBySchoolClassRule(Integer.valueOf(schoolClassId), page);
			break;
		case 6:
			pageList = appNetBlacklistService.queryBySchoolClassRule(Integer.valueOf(schoolClassId), page);
			break;
		default:
			break;
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", pageList.getResultList());
		resultMap.put("page", pageList.getPage());
		printJson(response, resultMap);
	}

	/**
	 * 保存规则
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = "/classRuleDetail")
	public String classRuleDetail(HttpServletRequest request, HttpServletResponse response, Model model) {

		// String schoolClassId = request.getParameter("schoolClassId");//
		// 学校班级id
		// String ruleType = request.getParameter("ruleType");// 规则类型

		System.out.println("1");
		return "schoolClassRule.classRuleDetail";
	}

	/**
	 * 保存规则
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = "/saveSchoolClassBrowserWhitelistRule")
	public void saveSchoolClassBrowserWhitelistRule(HttpServletRequest request, HttpServletResponse response,
			Model model) {
		String browserWhitelistRuleId = request.getParameter("ruleId");
		String schoolClassId = request.getParameter("schoolClassId");

		List<UrlBlackList> urlBlackListList = urlBlackListRuleService
				.getUrlBlackListBySchooClassId(Integer.valueOf(schoolClassId));

		List<BrowserWhitelist> browserWhitelistList = browserWhitelistRuleService
				.getBroserWhitelistByRuleId(Integer.valueOf(browserWhitelistRuleId));

		for (UrlBlackList urlBlackList : urlBlackListList) {
			for (BrowserWhitelist browserWhitelist : browserWhitelistList) {
				if (browserWhitelist.getUrl().equals(urlBlackList.getUrl())) {
					String result = "url冲突";
					printText(response, result);
					return;
				}
			}
		}

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("schoolClassId", schoolClassId);
		param.put("browserWhitelistRuleId", browserWhitelistRuleId);

		// 先删除相同的规则，在添加，保证规则不重复
		schoolClassRuleService.deleteSchoolClassBrowserWhitelistRuleBySchoolClassAndRuleId(param);

		SchoolClassBrowserWhitelistRule bean = new SchoolClassBrowserWhitelistRule();

		bean.setUpdateTime(new Date());
		bean.setCreateTime(new Date());
		bean.setSchoolClassId(Integer.valueOf(schoolClassId));
		bean.setBrowserWhitelistRuleId(Integer.valueOf(browserWhitelistRuleId));
		schoolClassRuleService.saveSchoolClassBrowserWhitelistRule(bean);

		String result = "success";
		printText(response, result);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = "/saveSchoolClassUrlBlacklistRule")
	public void saveSchoolClassUrlBlacklistRule(HttpServletRequest request, HttpServletResponse response, Model model) {
		String urlBlacklistRuleId = request.getParameter("ruleId");
		String schoolClassId = request.getParameter("schoolClassId");

		List<BrowserWhitelist> browserWhitelistList = browserWhitelistRuleService
				.getwhiteListBySchoolClassId(Integer.valueOf(schoolClassId));

		List<UrlBlackList> urlBlackListList = urlBlackListRuleService
				.getUrlBlackListByRuleId(Integer.valueOf(urlBlacklistRuleId));

		for (BrowserWhitelist browserWhitelist : browserWhitelistList) {
			for (UrlBlackList urlBlackList : urlBlackListList) {
				if (browserWhitelist.getUrl().equals(urlBlackList.getUrl())) {
					String result = "url冲突";
					printText(response, result);
					return;
				}
			}
		}

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("schoolClassId", schoolClassId);
		param.put("urlBlacklistRuleId", urlBlacklistRuleId);

		schoolClassRuleService.deleteSchoolClassUrlBlacklistRuleBySchoolClassAndRule(param);

		SchoolClassUrlBlacklistRule bean = new SchoolClassUrlBlacklistRule();
		bean.setCreateTime(new Date());
		bean.setUpdateTime(new Date());
		bean.setSchoolClassId(Integer.valueOf(schoolClassId));
		bean.setUrlBlacklistRuleId(Integer.valueOf(urlBlacklistRuleId));

		schoolClassRuleService.saveSchoolClassUrlBlacklistRule(bean);

		String result = SysConstants.SUCCESS;
		printText(response, result);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = "/saveSchoolClassAppNetBlacklist")
	public void saveSchoolClassAppNetBlacklist(HttpServletRequest request, HttpServletResponse response, Model model) {
		String appNetBlacklistId = request.getParameter("ruleId");
		String schoolClassId = request.getParameter("schoolClassId");

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("schoolClassId", schoolClassId);
		param.put("appNetBlacklistId", appNetBlacklistId);

		// 先删除同类规则，在添加
		schoolClassRuleService.deleteSchoolClassAppNetBlacklistBySchoolClassAndRule(param);

		SchoolClassAppNetBlacklist bean = new SchoolClassAppNetBlacklist();

		bean.setCreateTime(new Date());
		bean.setUpdateTime(new Date());
		bean.setSchoolClassId(Integer.valueOf(schoolClassId));
		bean.setAppNetBlacklistId(Integer.valueOf(appNetBlacklistId));

		schoolClassRuleService.saveSchoolClassAppNetBlacklist(bean);

		String result = SysConstants.SUCCESS;
		printText(response, result);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = "/saveSchoolClassAppWhitelistRule")
	public void saveSchoolClassAppWhitelistRule(HttpServletRequest request, HttpServletResponse response, Model model) {
		String appWhitelistRuleId = request.getParameter("ruleId");
		String schoolClassId = request.getParameter("schoolClassId");

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("schoolClassId", schoolClassId);
		param.put("appWhitelistRuleId", appWhitelistRuleId);

		schoolClassRuleService.deleteAppWhitelistRuleBySchoolClassAndRule(param);

		SchoolClassAppWhitelistRule bean = new SchoolClassAppWhitelistRule();

		bean.setCreateTime(new Date());
		bean.setUpdateTime(new Date());
		bean.setSchoolClassId(Integer.valueOf(schoolClassId));
		bean.setAppWhitelistRuleId(Integer.valueOf(appWhitelistRuleId));

		schoolClassRuleService.saveSchoolClassAppWhitelistRule(bean);

		String result = SysConstants.SUCCESS;
		printText(response, result);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = "/saveSchoolClassUsetimeRule")
	public void saveSchoolClassUsetimeRule(HttpServletRequest request, HttpServletResponse response, Model model) {
		String usetimeRuleId = request.getParameter("ruleId");
		String schoolClassId = request.getParameter("schoolClassId");

		List<UseTimeRule> useTimeRuleList = useTimeRuleService.listBySchoolClassRule(Integer.valueOf(schoolClassId));

		UseTimeRule useTimeRule = useTimeRuleService.loadById(Integer.valueOf(usetimeRuleId));

		for (UseTimeRule bean : useTimeRuleList) {
			if (useTimeRule.getDateType() != bean.getDateType()) {
				String result = "规则类型必须相同";
				printText(response, result);
				return;
			}
		}

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("usetimeRuleId", usetimeRuleId);
		param.put("schoolClassId", schoolClassId);

		schoolClassRuleService.deleteSchoolClassUsetimeRuleBySchoolClassAndRule(param);

		SchoolClassUsetimeRule bean = new SchoolClassUsetimeRule();
		bean.setCreateTime(new Date());
		bean.setUpdateTime(new Date());
		bean.setSchoolClassId(Integer.valueOf(schoolClassId));
		bean.setUsetimeRuleId(Integer.valueOf(usetimeRuleId));

		schoolClassRuleService.saveSchoolClassUsetimeRule(bean);

		String result = SysConstants.SUCCESS;
		printText(response, result);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = "/saveSchoolClassBrowserTagRule")
	public void saveSchoolClassBrowserTagRule(HttpServletRequest request, HttpServletResponse response, Model model) {
		String browserTagRuleId = request.getParameter("ruleId");
		String schoolClassId = request.getParameter("schoolClassId");
		// SchoolClassRule bean =
		// schoolClassRuleService.getBySchoolClassId(Integer.valueOf(schoolClassId));

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("schoolClassId", schoolClassId);
		param.put("browserTagRuleId", browserTagRuleId);

		schoolClassRuleService.deleteSchoolClassBrowserTagRuleBySchoolClassAndRule(param);

		SchoolClassBrowserTagRule bean = new SchoolClassBrowserTagRule();
		bean.setCreateTime(new Date());
		bean.setUpdateTime(new Date());
		bean.setSchoolClassId(Integer.valueOf(schoolClassId));
		bean.setBrowserTagRuleId(Integer.valueOf(browserTagRuleId));
		schoolClassRuleService.saveSchoolClassBrowserTagRule(bean);

		String result = SysConstants.SUCCESS;
		printText(response, result);
	}

	/**
	 * 删除规则
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = "/deleteRule")
	public void deleteRule(HttpServletRequest request, HttpServletResponse response, Model model) {
		String schoolClassId = request.getParameter("schoolClassId");
		String ruleId = request.getParameter("ruleId");
		String ruleType = request.getParameter("ruleType");

		switch (Integer.valueOf(ruleType)) {
		case 1:
			Map<String, Object> useTimeRuleParam = new HashMap<String, Object>();
			useTimeRuleParam.put("usetimeRuleId", ruleId);
			useTimeRuleParam.put("schoolClassId", schoolClassId);
			schoolClassRuleService.deleteSchoolClassUsetimeRuleBySchoolClassAndRule(useTimeRuleParam);
			break;
		case 2:
			Map<String, Object> urlBlacklistParam = new HashMap<String, Object>();
			urlBlacklistParam.put("schoolClassId", schoolClassId);
			urlBlacklistParam.put("urlBlacklistRuleId", ruleId);
			schoolClassRuleService.deleteSchoolClassUrlBlacklistRuleBySchoolClassAndRule(urlBlacklistParam);
			break;
		case 3:
			Map<String, Object> appWhitelistRuleParam = new HashMap<String, Object>();
			appWhitelistRuleParam.put("schoolClassId", schoolClassId);
			appWhitelistRuleParam.put("appWhitelistRuleId", ruleId);
			schoolClassRuleService.deleteAppWhitelistRuleBySchoolClassAndRule(appWhitelistRuleParam);
			break;
		case 4:
			Map<String, Object> browserWhitelistRuleParam = new HashMap<String, Object>();
			browserWhitelistRuleParam.put("schoolClassId", schoolClassId);
			browserWhitelistRuleParam.put("browserWhitelistRuleId", ruleId);
			schoolClassRuleService
					.deleteSchoolClassBrowserWhitelistRuleBySchoolClassAndRuleId(browserWhitelistRuleParam);
			break;
		case 5:
			Map<String, Object> browserTagRuleParam = new HashMap<String, Object>();
			browserTagRuleParam.put("schoolClassId", schoolClassId);
			browserTagRuleParam.put("browserTagRuleId", ruleId);
			schoolClassRuleService.deleteSchoolClassBrowserTagRuleBySchoolClassAndRule(browserTagRuleParam);
			break;
		case 6:
			Map<String, Object> appNetBlacklistParam = new HashMap<String, Object>();
			appNetBlacklistParam.put("schoolClassId", schoolClassId);
			appNetBlacklistParam.put("appNetBlacklistId", ruleId);
			schoolClassRuleService.deleteSchoolClassAppNetBlacklistBySchoolClassAndRule(appNetBlacklistParam);
			break;
		default:
			break;

		}

		// schoolClassRuleService.deleteSchoolClassRule(bean);

		String result = SysConstants.SUCCESS;
		printText(response, result);
	}

	/**
	 * 添加规则页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "/addSchoolClassRulePage")
	public String addSchoolClassRulePage(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		String ruleType = request.getParameter("ruleType");// 规则类型
		String schoolClassId = request.getParameter("schoolClassId");

		model.addAttribute("ruleType", ruleType);
		model.addAttribute("schoolClassId", schoolClassId);

		Map<String, Object> resultMap = new HashMap<String, Object>(1);

		HandlerResult handlerResult = new HandlerResult();
		String resultPage = null;

		String search_type = request.getParameter("search_type");
		String currentPage = request.getParameter("currPage");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");
		PageContext page = PageContext.getContext();

		if (null == currentPage || StringUtils.isNotEmpty(search_type)) {
			page.setCurrentPage(1);
			page.setPageSize(10);
			page.setTotalPage(0);
			page.setTotalRows(0);
		} else {
			page.setCurrentPage(Integer.parseInt(currentPage));
			page.setPageSize(Integer.parseInt(pageSize));
			page.setTotalPage(Integer.parseInt(totalPage));
		}
		page.setPagination(false);

		switch (Integer.valueOf(ruleType)) {
		case 1:
			handlerResult = useTimeRuleService.queryUseTimeRulePage(resultMap);
			resultPage = "addSchoolClassUsetimeRule.list";
			break;
		case 2:
			handlerResult = urlBlackListRuleService.queryUrlBlackListRulePage(resultMap);
			resultPage = "addSchoolClassUrlBlacklistRule.list";
			break;
		case 3:
			handlerResult = appWhitelistRuleService.queryAppWhitelistRulePage(resultMap);
			resultPage = "addSchoolClassAppWhitelistRule.list";
			break;
		case 4:
			handlerResult = browserWhitelistRuleService.queryBrowserWhitelistRulePage(resultMap);
			resultPage = "addSchoolClassBrowserWhitelistRule.list";
			break;
		case 5:
			handlerResult = browserTagRuleService.queryBrowserTagRulePage(resultMap);
			resultPage = "addSchoolClassBrowserTagRule.list";
			break;
		case 6:
			handlerResult = appNetBlacklistService.queryAppNetBlacklistPage(resultMap);
			resultPage = "addSchoolClassAppNetBlacklist.list";
		default:

			break;
		}
		model.addAttribute("list", handlerResult.getResultList());
		page.setPagination(true);
		return resultPage;
	}

	@RequestMapping(value = "/querySchoolClassRule")
	public void querySchoolClassRule(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {

		Map<String, Object> paraMap = this.getParameterMap(request);
		PageContext page = PageContext.getContext();
		page.setCurrentPage(Integer.parseInt(paraMap.get("currPage").toString()));
		page.setPageSize(Integer.parseInt(paraMap.get("pageSize").toString()));

		String schoolClassId = request.getParameter("schoolClassId");

		if (schoolClassId != null) {
			paraMap.put("schoolClassId", schoolClassId);
		}

		List<BrowserTagRule> browserTagRuleList = null;
		List<BrowserWhitelistRule> browserWhitelistRuleList = null;
		List<AppWhitelistRule> appWhitelistRuleList = null;
		List<UrlBlackListRule> urlBlackListRuleList = null;
		List<UseTimeRule> useTimeRuleList = null;
		try {
			page.setPagination(true);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("schoolClassId", schoolClassId);

			browserTagRuleList = browserTagRuleService.listBySchoolClassRule(Integer.valueOf(schoolClassId));
			browserWhitelistRuleList = browserWhitelistRuleService
					.listBySchoolClassRule(Integer.valueOf(schoolClassId));
			appWhitelistRuleList = appWhitelistRuleService.listBySchoolClassRule(Integer.valueOf(schoolClassId));
			urlBlackListRuleList = urlBlackListRuleService.listBySchoolClassRule(Integer.valueOf(schoolClassId));
			useTimeRuleList = useTimeRuleService.listBySchoolClassRule(Integer.valueOf(schoolClassId));

			page.setPagination(false);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("browserTagRuleList", browserTagRuleList);
		resultMap.put("browserWhitelistRuleList", browserWhitelistRuleList);
		resultMap.put("appWhitelistRuleList", appWhitelistRuleList);
		resultMap.put("urlBlackListRuleList", urlBlackListRuleList);
		resultMap.put("useTimeRuleList", useTimeRuleList);

		printJson(response, resultMap);
	}

	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, Model model) throws Exception {

		String schoolId = request.getParameter("schoolId");

		TaManageUserInfo mUser = (TaManageUserInfo) request.getSession().getAttribute(SysConstants.SESSION_USER);

		if (mUser.getSchoolId() != null) {
			schoolId = mUser.getSchoolId().toString();
		} else {
			if (mUser.getRoleId().equals("1")) {
				List<School> schoolList = schoolService.listByCondition(new HashMap<String, Object>());
				model.addAttribute("schoolList", schoolList);
			}
		}

		String schoolClassId = request.getParameter("schoolClassId");

		Map<String, Object> paramMap = new HashMap<String, Object>();

		if (schoolClassId != null) {
			paramMap.put("schoolClassId", schoolClassId);
			model.addAttribute("schoolClassId", schoolClassId);
		}

		String search_type = request.getParameter("search_type");
		String currentPage = request.getParameter("currPage");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");
		PageContext page = PageContext.getContext();

		if (null == currentPage || StringUtils.isNotEmpty(search_type)) {
			page.setCurrentPage(1);
			page.setPageSize(10);
			page.setTotalPage(0);
			page.setTotalRows(0);
		} else {
			page.setCurrentPage(Integer.parseInt(currentPage));
			page.setPageSize(Integer.parseInt(pageSize));
			page.setTotalPage(Integer.parseInt(totalPage));
		}
		page.setPagination(false);

		HandlerResult handlerResult = schoolClassRuleService.querySchoolClassRulePage(paramMap);

		if (StringUtils.isNotEmpty(schoolId)) {
			List<Map<String, Object>> schoolClassTree = schoolClassRuleService.listGradeTree(Integer.valueOf(schoolId));

			for (Map<String, Object> schoolClass : schoolClassTree) {
				Object object = schoolClass.get("sub_value");

				System.out.println(object.toString());
				model.addAttribute("schoolClassTree", schoolClassTree);
			}
		}

		page.setPagination(true);
		model.addAttribute("list", handlerResult.getResultList());

		model.addAttribute("schoolId", schoolId);
		model.addAttribute("page", page);
		model.addAllAttributes(paramMap);

		return "schoolClassRule.list";
	}

}
