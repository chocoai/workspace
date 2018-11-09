package com.whty.assis.manage.controller;

import java.io.PrintWriter;
import java.util.Arrays;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whty.assis.api.utils.HttpUtils;
import com.whty.assis.base.controller.BaseController;
import com.whty.assis.demo.service.AreaService;
import com.whty.assis.manage.model.BaseProperty;
import com.whty.assis.manage.service.BasePropertyService;
import com.whty.common.util.GUIDGenerator;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/baseProperty")
public class BasePropertyController extends BaseController {

	@Autowired
	private BasePropertyService basePropertyService;

	@Autowired
	private AreaService areaService;

	/**
	 * 查询属性
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getUserInfo")
	public void getUserInfo(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestBody String getUserkeyInfoStr) throws Exception {

		Map<String, Object> param = new HashMap<String, Object>();
		String userId = "";
		String userName = "";
		String platformCode = "";
		String loginPlatformCode = "";
		String loginAccount = "";
		String orgId = "";
		JSONObject useKeyInfoResultJson = JSONObject.fromObject(getUserkeyInfoStr);
		System.out.println(useKeyInfoResultJson);
		if (useKeyInfoResultJson.optString("retCode").equals("000000")) {
			System.out.println(useKeyInfoResultJson.optString("retCode"));
			JSONObject useKeyInfoResultJsonData = new JSONObject();
			JSONArray useKeyInfoResultArrayJsonData = useKeyInfoResultJson.optJSONArray("data");

			if (useKeyInfoResultArrayJsonData.size() > 0) {
				useKeyInfoResultJsonData = useKeyInfoResultArrayJsonData.optJSONObject(0);
			}

			String useKeyInfoResultJsonDataPlatformCode = useKeyInfoResultJsonData.optString("sysCode");

			platformCode = useKeyInfoResultJsonDataPlatformCode; // 用户平台编码
			userId = useKeyInfoResultJsonData.optString("platUserId");

			param.put("userId", userId);
			param.put("loginPlatformCode", platformCode);

			String useKeyInfoResultJsonDataPlatformCodeLoginAccount = useKeyInfoResultJsonData
					.optString("loginAccount");
			loginAccount = useKeyInfoResultJsonDataPlatformCodeLoginAccount;
			param.put("loginAccount", loginAccount);
			// 查询到登录平台编码
			String ss = "http://116.211.105.38:13022/aamty/allAccount/queryAccountPlatformInfo";

			Map<String, Object> ssmap = new HashMap<String, Object>();
			// ssmap.put("account", "tyjiaoshi02");
			ssmap.put("account", useKeyInfoResultJsonDataPlatformCodeLoginAccount);
			String result2;
			try {
				result2 = HttpUtils.getInstance().httpPost(ss, JSONObject.fromObject(ssmap).toString());

				JSONObject logUserInfo = JSONObject.fromObject(result2);

				if (logUserInfo.optString("result").equals("000000")) {
					JSONObject logUserInfoData = logUserInfo.optJSONObject("data");
					loginPlatformCode = logUserInfoData.optString("platformCode");// 登录平台编码
					param.put("loginPlatformCode", loginPlatformCode);

					String platformUrl = logUserInfoData.optString("platformUrl");

					String userInfoUrlResult = HttpUtils.getInstance().httpGet(platformUrl + "/user/" + userId);
					String userInfoName = JSONObject.fromObject(userInfoUrlResult).optJSONObject("userinfo")
							.optString("name");

					orgId = JSONObject.fromObject(userInfoUrlResult).optJSONObject("userinfo").optString("orgaid");

					param.put("orgId", orgId);

					if (StringUtils.isNotEmpty(userInfoName)) {
						userName = userInfoName;
						param.put("userName", userName);
					}

				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		printJson(response, param);
	}

	/**
	 * 查询属性
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String search_type = request.getParameter("search_type");
		String currentPage = request.getParameter("currPage");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");

		Map<String, Object> para = new HashMap<String, Object>();
		para.put("property_key", request.getParameter("property_key"));
		para.put("status", request.getParameter("status"));
		para.put("platform_code", request.getParameter("platform_code"));
		para.put("property_type_name", request.getParameter("property_type_name"));

		PageContext page = PageContext.getContext();
		// 请自行验证
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
		page.setPagination(true);

		HandlerResult resultList = basePropertyService.queryBaseProperty(para);
		model.addAttribute("propertylist", resultList.getResultList());
		model.addAttribute("page", page);
		// model.addAttribute("listJson",
		// JSONArray.fromObject(resultList.getResultList()));
		model.addAllAttributes(para);
		// 查询并增加平台列表
		page.setPagination(false);
		List platformList = basePropertyService.getPlatformList();
		model.addAttribute("platformList", platformList);
		return "baseProperty/list";
	}

	/**
	 * 新建和编辑管理员信息
	 */
	@RequestMapping(value = "/save")
	public void save(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String id = request.getParameter("id");
		String property_key = request.getParameter("property_key");
		String property_value = request.getParameter("property_value");
		String status = request.getParameter("status");
		String description = request.getParameter("description");
		String platform_code = request.getParameter("platform_code");
		String platform_name = request.getParameter("platform_name");
		String property_type_name = request.getParameter("property_type_name");

		BaseProperty baseProperty = new BaseProperty();
		baseProperty.setId(id);
		baseProperty.setProperty_key(property_key);
		baseProperty.setProperty_value(property_value);
		baseProperty.setStatus(status);
		baseProperty.setDescription(description);
		baseProperty.setPlatform_code(platform_code);
		baseProperty.setPlatform_name(platform_name);
		baseProperty.setProperty_type_name(property_type_name);

		String result = null;
		if (StringUtils.isEmpty(id)) {
			baseProperty.setId(GUIDGenerator.getGUID());
			Date tDate = new Date();
			baseProperty.setCreate_time(tDate);
			basePropertyService.addBaseProperty(baseProperty);
			result = "success";
		} else {
			Date tDate = new Date();
			baseProperty.setUpdate_time(tDate);
			basePropertyService.updateBaseProperty(baseProperty);
			result = "success";
		}

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write(result);
	}

	@RequestMapping(value = "/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String ids = request.getParameter("ids");
		List<String> idList = Arrays.asList(ids.split(","));
		Map<String, Object> paramap = new HashMap<String, Object>();
		paramap.put("idList", idList);

		basePropertyService.deleteBaseProperty(paramap);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write("success");
	}

	/**
	 * 查询行政区数据
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/queryArea")
	public void queryArea(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String parentId = request.getParameter("parentId");
		String levelId = request.getParameter("levelId");
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(parentId)) {
			paraMap.put("parentId", parentId);
		}
		if (StringUtils.isNotEmpty(levelId)) {
			paraMap.put("levelId", levelId);
		}

		List<Map> list = areaService.queryArea(paraMap);

		Map resultMap = new HashMap();
		resultMap.put("list", list);
		printJson(response, resultMap);
	}

	@ResponseBody
	@RequestMapping(value = "/viewForEdit")
	public String loadBaseProById(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("查看错误日志");
		}
		String id = request.getParameter("id");
		BaseProperty baseProperty = basePropertyService.loadBaseProById(id);
		return JSONObject.fromObject(baseProperty).toString();
	}
}