package com.whty.assis.basicdata.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
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
import com.whty.assis.basicdata.dao.DbViewDao;
import com.whty.assis.basicdata.dao.IdDao;
import com.whty.assis.basicdata.model.DbView;
import com.whty.assis.basicdata.model.School;
import com.whty.assis.basicdata.model.SchoolClass;
import com.whty.assis.basicdata.service.AreaService;
import com.whty.assis.basicdata.service.SchoolClassService;
import com.whty.assis.basicdata.service.SchoolService;
import com.whty.assis.manage.model.PlatformInfo;
import com.whty.assis.manage.service.BasePropertyService;
import com.whty.assis.manage.service.PlatformInfoService;
import com.whty.assis.sysres.model.TaManageUserInfo;
import com.whty.common.util.SysConfig;
import com.whty.common.util.SysConstants;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

import net.sf.json.JSONArray;

/**
 * 学校管理
 * 
 * @author zhangzheng
 *
 */
@Controller
@RequestMapping("/manage/school")
public class SchoolController extends BaseController {

	@Autowired
	private SchoolService schoolService;

	@Autowired
	private AreaService areaService;

	@Autowired
	private BasePropertyService basePropertyService;

	@Autowired
	private SchoolClassService schoolClassService;

	@Autowired
	private PlatformInfoService platformInfoService;

	@Autowired
	private DbViewDao dbViewDao;

	@Autowired
	private IdDao idDao;

	// @RequestMapping(value = "/writePerson")
	// public void writePerson(HttpServletRequest request, HttpServletResponse
	// response, Model model) throws Exception {
	// schoolClassService.writePerson(11);
	// }

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/querySchoolByCode")
	public void querySchoolByCode(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		String code = request.getParameter("code");// 区域编码

		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("code", code);
		List<Map<String, Object>> list = schoolService.listByConditionByAreaCode(paraMap);

		Map resultMap = new HashMap();
		resultMap.put("list", list);
		printJson(response, resultMap);
	}

	
	/**
	 * 根据区域编码查询学校
	 */
	@RequestMapping(value = "/update")
	public void update(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
	
		String result  = schoolService.update(request);
		
		printText(response, result);
	}
	
	/**
	 * 根据区域编码查询学校
	 */
	@RequestMapping(value = "/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		String id = request.getParameter("id");
		String result = schoolService.delete(Integer.valueOf(id));
		printText(response, result);
	}
	
	/**
	 * 根据区域编码查询学校
	 */
	@RequestMapping(value = "/detail")
	public void detail(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		String id = request.getParameter("id");
		School school = schoolService.loadById(Integer.valueOf(id));
		printJson(response, school);
	}
	
	/**
	 * 根据区域编码查询学校
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/querySchoolClass")
	public void querySchoolClass(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		String schoolId = request.getParameter("schoolId");// 区域编码

		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("schoolId", schoolId);
		List<SchoolClass> list = schoolClassService.getSchoolClass(paraMap);

		Map resultMap = new HashMap();
		resultMap.put("list", list);
		printJson(response, resultMap);
	}

	/**
	 * 根据区域编码查询学校
	 */
	@RequestMapping(value = "/querySchool")
	public void querySchool(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		Map<String, Object> paraMap = this.getParameterMap(request);
		PageContext page = PageContext.getContext();
		page.setCurrentPage(Integer.parseInt(paraMap.get("currPage").toString()));
		page.setPageSize(Integer.parseInt(paraMap.get("pageSize").toString()));

		String areaCode = request.getParameter("areaCode");

		if (areaCode != null && !"".equals(areaCode)) {
			paraMap.put("areaCode", areaCode);
		}

		HandlerResult pageList = new HandlerResult();

		try {
			page.setPagination(true);
			pageList = schoolService.querySchoolPage(paraMap, page);
			page.setPagination(false);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", pageList.getResultList());
		resultMap.put("page", pageList.getPage());
		printJson(response, resultMap);
	}

	/**
	 * 学校列表
	 */
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String, Object> paramMap = this.getParameterMap(request);

		Map<String, Object> areaMap = new HashMap<String, Object>();
		areaMap.put("levelId", 1);
		List<Map<String, Object>> provinceList = areaService.listArea(areaMap);
		model.addAttribute("provinceList", JSONArray.fromObject(provinceList));

		// //查询市列表
		if (paramMap.get("provinceCode") != null && StringUtils.isNotEmpty(paramMap.get("provinceCode").toString())) {
			areaMap.put("parentId", paramMap.get("provinceCode").toString());
			areaMap.put("levelId", 2);
			List<Map<String, Object>> cityList = areaService.listArea(areaMap);
			model.addAttribute("cityList", JSONArray.fromObject(cityList));
		}

		// //查询区列表
		if (paramMap.get("cityCode") != null && StringUtils.isNotEmpty(paramMap.get("cityCode").toString())) {
			areaMap.put("parentId", paramMap.get("cityCode").toString());
			areaMap.put("levelId", 3);
			List<Map<String, Object>> areaList = areaService.listArea(areaMap);
			model.addAttribute("areaList", JSONArray.fromObject(areaList));
		}

		try {
			String search_type = request.getParameter("search_type");
			String currentPage = request.getParameter("currPage");
			String pageSize = request.getParameter("pageSize");
			String totalPage = request.getParameter("totalPage");
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
			// List<Map<String, Object>> listSchool =
			// schoolService.listSchooleMap(paramMap);
			// model.addAttribute("list", listSchool);

			page.setPagination(false);
			// String str = request.getParameter("msg");

			// List<SchoolAreaTreeNode> nodeTree = areaService.queryAreaTree();
			// model.addAttribute("nodeTree", nodeTree);

			HandlerResult pageList = schoolService.querySchoolPage(paramMap, page);
			model.addAttribute("list", pageList.getResultList());

			List<PlatformInfo> platformInfoList = platformInfoService.listByCondition(new HashMap<String, Object>());
			model.addAttribute("platformInfoList", platformInfoList);

			Map<String, Object> provinceParam = new HashMap<String, Object>();
			provinceParam.put("levelId", 1);

			List<DbView> schoolTypeList = dbViewDao.listSchoolType();

			model.addAttribute("schoolTypeList", schoolTypeList);
			model.addAttribute("provinceList", provinceList);

			model.addAttribute("page", page);
			model.addAllAttributes(paramMap);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return "school.list";
	}

	@RequestMapping(value = "/getAllSchool")
	public void getAllSchool(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		String platformCode = request.getParameter("platformCode");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String orgName = request.getParameter("orgName");
		String aamUrl = basePropertyService.getPropertyValue("aamUrl", platformCode);

		String url = aamUrl + "ogra/query";

		JSONArray schoolJson = schoolService.listSchool(url, platformCode, province, city, orgName);
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(schoolJson);
	}

	/**
	 * 新建和编辑管理员信息
	 */
	@RequestMapping(value = "/save")
	public void save(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		// String id = request.getParameter("id");
		String schoolName = request.getParameter("schoolName");
		String mobile = request.getParameter("mobile");
		String address = request.getParameter("address");

		String webUrl = request.getParameter("webUrl");

		String provinceCode = request.getParameter("provinceCode");
		String cityCode = request.getParameter("cityCode");
		String areaCode = request.getParameter("areaCode");
		final String aamOrgaId = request.getParameter("aamOrgaId");
		String email = request.getParameter("email");
		String contacts = request.getParameter("contacts");
		final String platformCode = request.getParameter("platformCode");
		School school = new School();

		String schoolType = request.getParameter("schoolType");

		Map<String, Object> idParam = new HashMap<String, Object>(2);
		idParam.put("databaseName", SysConfig.getStrValue("databaseName"));
		idParam.put("tableName", "t_school");

		final Integer schoolId = idDao.getId(idParam);

		// school.setId(id);
		school.setName(schoolName);
		school.setMobile(mobile);
		school.setAddress(address);
		school.setEmail(email);
		school.setWebUrl(webUrl);
		school.setProvinceCode(provinceCode);
		school.setCityCode(cityCode);
		school.setAreaCode(areaCode);
		school.setAamOrgaId(aamOrgaId);
		school.setContacts(contacts);

		school.setSchoolType(Integer.valueOf(schoolType));

		school.setCreateTime(new Date());
		school.setUpdateTime(new Date());
		school.setPlatformCode(platformCode);
		
		TaManageUserInfo mUser = (TaManageUserInfo) request.getSession().getAttribute(SysConstants.SESSION_USER);
		
		school.setCreator(mUser.getId());// TODO

		schoolService.save(school);

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					schoolClassService.writePerson(platformCode, aamOrgaId, schoolId);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();

		String result = null;

		result = "success";

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write(result);
	}

}
