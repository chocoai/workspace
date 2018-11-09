package com.whty.assis.basicdata.controller;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whty.assis.base.controller.BaseController;
import com.whty.assis.basicdata.dao.DbViewDao;
import com.whty.assis.basicdata.model.School;
import com.whty.assis.basicdata.model.SchoolLocation;
import com.whty.assis.basicdata.model.SchoolLocationArea;
import com.whty.assis.basicdata.service.SchoolLocationAreaService;
import com.whty.assis.basicdata.service.SchoolLocationService;
import com.whty.assis.basicdata.service.SchoolService;
import com.whty.assis.sysres.model.TaManageUserInfo;
import com.whty.common.util.SysConstants;
import com.whty.page.PageContext;

@Controller
@RequestMapping("/manage/schoolLocation")
public class SchoolLocationController extends BaseController {

	@Autowired
	private SchoolLocationService schoolLocationService;

	@Autowired
	private SchoolLocationAreaService schoolLocationAreaService;

	@Autowired
	private DbViewDao dbViewDao;

	@Autowired
	private SchoolService schoolService;

	@RequestMapping(value = "/querySchoolLocationArea")
	public void querySchoolLocationArea(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(name = "pageValue", defaultValue = "1") Integer page) throws Exception {
		Map<String, Object> paraMap = this.getParameterMap(request);

		String schoolLocationId = request.getParameter("schoolLocationId");
		String layer = request.getParameter("layer");
		String number = request.getParameter("number");
		if (schoolLocationId != null) {
			paraMap.put("schoolLocationId", schoolLocationId);
		}

		if (StringUtils.isNotEmpty(layer)) {
			paraMap.put("layer", layer);
		}

		if (StringUtils.isNotEmpty(number)) {
			paraMap.put("number", number);
		}

		PageHelper.startPage(page, 10);
		List<SchoolLocationArea> schoolLocationAreas = schoolLocationAreaService.listByCondition(paraMap);
		PageInfo<SchoolLocationArea> p = new PageInfo<SchoolLocationArea>(schoolLocationAreas);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", schoolLocationAreas);
		resultMap.put("pageNum", p.getPageNum());
		resultMap.put("pages", p.getPages());
		printJson(response, resultMap);
	}

	@RequestMapping(value = "/saveSchoolLocationArea")
	public void saveSchoolLocationArea(HttpServletRequest request, Model model, HttpServletResponse response)
			throws Exception {
		String result = schoolLocationAreaService.saveSchoolLocationArea(request);
		printText(response, result);
	}

	@RequestMapping(value = "/editSchoolLocationArea")
	public void editSchoolLocationArea(HttpServletRequest request, Model model, HttpServletResponse response)
			throws Exception {
		String result = null;

		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String area = request.getParameter("area");

		try {
			SchoolLocationArea bean = new SchoolLocationArea();
			bean.setId(Integer.valueOf(id));
			bean.setDescription(description);
			bean.setName(name);
			bean.setNumber("333");// TODO 后面修改
			bean.setUpdateTime(new Date());
			bean.setArea(area);

			schoolLocationAreaService.updateSchoolLocationArea(bean);
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			result = "保存异常，请联系管理员";
		}

		printText(response, result);
	}

	@RequestMapping(value = "/detailSchoolLocationArea")
	public void detailSchoolLocationArea(HttpServletRequest request, Model model, HttpServletResponse response)
			throws Exception {
		SchoolLocationArea bean = null;
		String id = request.getParameter("id");
		try {
			bean = schoolLocationAreaService.loadById(Integer.valueOf(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		printJson(response, bean);
	}

	@RequestMapping(value = "/updateSchoolLocation")
	public void updateSchoolLocation(HttpServletRequest request, Model model) throws Exception {
		// schoolLocationService.updateSchoolLocation(bean);
	}

	@RequestMapping(value = "/deleteSchoolLocation")
	public void deleteSchoolLocation(HttpServletRequest request, Model model, HttpServletResponse response)
			throws Exception {
		String schoolLocationId = request.getParameter("schoolLocationId");
		String layer = request.getParameter("layer");

		String result = schoolLocationService.deleteSchoolLocation(schoolLocationId, layer);

		printText(response, result);
	}

	@RequestMapping(value = "/deleteSchoolLocationArea")
	public void deleteSchoolLocationArea(HttpServletRequest request, Model model, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("id");

		String result = schoolLocationAreaService.deleteSchoolLocationArea(id);

		printText(response, result);
	}

	@RequestMapping(value = "/saveSchoolLocation")
	public void saveSchoolLocation(HttpServletRequest request, Model model, HttpServletResponse response)
			throws Exception {
		String result = null;
		try {
			result = schoolLocationService.saveSchoolLocation(request);
		} catch (Exception e) {
			e.printStackTrace();
			result = "保存异常，请联系管理员";
		}

		printText(response, result);
	}

	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, Model model) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String schoolLocationId = request.getParameter("schoolLocationAreaId");// 区域id
		String schoolLocationLayerId = request.getParameter("schoolLocationLayerId");// 楼层id

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

		

		if (StringUtils.isNotEmpty(schoolLocationId)) {
			paramMap.put("schoolLocationId", schoolLocationId);
		}
		if (StringUtils.isNotEmpty(schoolLocationLayerId)) {
			paramMap.put("schoolLocationLayerId", schoolLocationLayerId);
		}
		if (StringUtils.isNotEmpty(schoolId)) {
			paramMap.put("schoolId", Integer.valueOf(schoolId));
			List<SchoolLocation> SchoolLocationList = schoolLocationService.listByCondition(paramMap);
			model.addAttribute("list", SchoolLocationList);
		}
		
		List<Map<String, Object>> listLocationAreaAttribute = dbViewDao.listLocationAreaAttribute();

		page.setPagination(true);

		model.addAttribute("listLocationAreaAttribute", listLocationAreaAttribute);
		
		model.addAttribute("page", page);
		model.addAllAttributes(paramMap);
		model.addAttribute("schoolId", schoolId);
		return "schoolLocation.list";
	}

}