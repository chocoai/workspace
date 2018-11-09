/**
 * 
 */
package com.whty.assis.basicdata.controller;

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
import com.whty.assis.basicdata.model.SchoolAreaTreeNode;
import com.whty.assis.basicdata.service.AreaService;
import com.whty.assis.basicdata.service.SchoolUserService;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

import net.sf.json.JSONArray;

/**
 * @author zhangzheng
 * @date   2018年5月21日
 */
@Controller
@RequestMapping("/manage/schoolUser")
public class SchoolUserController extends BaseController{

	@Autowired
	private SchoolUserService schoolUserService;
	
	@Autowired
	private AreaService areaService;
	
	/**
	 * 根据区域编码查询学校
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/querySchoolUser")
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
			pageList = schoolUserService.querySchoolUserPage(paraMap, page);
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
	 * 学生列表
	 */
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, HttpServletResponse response, Model model) {
		// 查询省列表
		String userType = request.getParameter("userType");
		
		Map<String, Object> areaMap = new HashMap<String, Object>();
		areaMap.put("levelId", 1);
		List<Map<String, Object>> provinceList = areaService.queryArea(areaMap);
		
		model.addAttribute("provinceList", JSONArray.fromObject(provinceList));

		if (logger.isDebugEnabled()) {
			logger.debug("管理员列表数据加载");
		}
		try {
			String search_type = request.getParameter("search_type");
			String currentPage = request.getParameter("currPage");
			String pageSize = request.getParameter("pageSize");
			String totalPage = request.getParameter("totalPage");

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("area_code", request.getParameter("area_code"));

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
			
			
			List<SchoolAreaTreeNode> nodeTree = areaService.queryAreaTree();

			model.addAttribute("nodeTree", nodeTree);
			
			model.addAttribute("page", page);
			model.addAllAttributes(paramMap);
			page.setPagination(false);
			String str = request.getParameter("msg");
			
			model.addAttribute("msg", str);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return "schoolUser.list";
	}
	
	
	
}
