package com.whty.assis.demo.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.whty.common.util.TimeUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bigdata.assis.manage.model.UserOperate;
import com.bigdata.assis.manage.service.CountOperateService;
import com.whty.assis.base.controller.BaseController;
import com.whty.assis.demo.service.AreaService;
import com.whty.common.excel.PoiUtil;
import com.whty.oraclepage.PageContext;
import com.whty.oraclepage.util.HandlerResult;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/manage/countOperate")
public class CountOperateController extends BaseController {

	@Autowired
	private AreaService areaService;

	@Autowired
	private CountOperateService countOperate;

	/*
	 * 导出EXCEL
	 */
	@RequestMapping(value = "/exportSearchExcel")
	public void exportSearchExcel(HttpServletRequest request, HttpServletResponse response) {
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();

			paramMap.put("PROV_CODE", request.getParameter("PROV_CODE"));
			paramMap.put("CITY_CODE", request.getParameter("CITY_CODE"));
			paramMap.put("ORGA_NAME", request.getParameter("ORGA_NAME"));
			paramMap.put("USER_NAME", request.getParameter("USER_NAME"));
			paramMap.put("USER_ACCOUNT", request.getParameter("USER_ACCOUNT"));

			// 表头
			String[] tableHeader = { "教师|USER_NAME", "账号|USER_ACCOUNT", "学校|ORGA_NAME", "登录次数|ALL_LOGIN_NUM",
					"最后登录时间|LAST_LOGIN_TIME", "使用总时长|ALL_LOGIN_USE_TIME", "有效使用次数|ALL_LOGIN_VALID_NUM",
					"有效使用时长|ALL_LOGIN_VALID_TIME", "板书数|ALL_JXZS_BS_NUM", "实录数|ALL_JXZS_KTSL_NUM",
					"电子书包授课|ALL_TERMINAL_DZSB_NUM", "移动讲台授课|ALL_TERMINAL_YDJT_NUM", "掌中黑板授课|ALL_TERMINAL_ZZHB_NUM",
					"答题器授课|ALL_TERMINAL_DTQ_NUM" };
			// 文件名
			String filename = "运营情况统计.xls";

			Map cmap = null;
			List reportList = new ArrayList();

			HandlerResult handlerResult = countOperate.getUserOperate(paramMap);
			List<UserOperate> list = handlerResult.getResultList();
			// list = convert(list);
			// 填充
			for (int i = 0; i < list.size(); i++) {
				cmap = new HashMap();
				UserOperate bean = list.get(i);
				cmap = new HashMap();
				cmap.put("USER_NAME", bean.getUSER_NAME());
				cmap.put("USER_ACCOUNT", bean.getUSER_ACCOUNT());
				cmap.put("ORGA_NAME", bean.getORGA_NAME());
				cmap.put("ALL_LOGIN_NUM", bean.getALL_LOGIN_NUM());
				cmap.put("LAST_LOGIN_TIME", TimeUtil.date2String(bean.getLAST_LOGIN_TIME()));
				cmap.put("ALL_LOGIN_USE_TIME", bean.getALL_LOGIN_USE_TIME());
				cmap.put("ALL_LOGIN_VALID_NUM", bean.getALL_LOGIN_VALID_NUM());
				cmap.put("ALL_LOGIN_VALID_TIME", bean.getALL_LOGIN_VALID_TIME());
				cmap.put("ALL_JXZS_BS_NUM", bean.getALL_JXZS_BS_NUM());
				cmap.put("ALL_JXZS_KTSL_NUM", bean.getALL_JXZS_KTSL_NUM());
				cmap.put("ALL_TERMINAL_DZSB_NUM", bean.getALL_TERMINAL_DZSB_NUM());
				cmap.put("ALL_TERMINAL_YDJT_NUM", bean.getALL_TERMINAL_YDJT_NUM());
				cmap.put("ALL_TERMINAL_ZZHB_NUM", bean.getALL_TERMINAL_ZZHB_NUM());
				cmap.put("ALL_TERMINAL_DTQ_NUM", bean.getALL_TERMINAL_DTQ_NUM());

				reportList.add(cmap);
			}

			String fileName = new String(filename.getBytes("GBK"), "ISO8859-1");

			ServletOutputStream os = null;
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-disposition", "attachment" + ";filename=" + fileName);
			os = response.getOutputStream();
			HSSFWorkbook wb = new HSSFWorkbook();
			PoiUtil.createExcelSheet(wb, tableHeader, reportList);
			wb.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 运营情况统计
	 */
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, Model model) throws Exception {
		Map<String, Object> paramMap = this.getParameter(request);

		// 查询省列表
		Map<String, Object> areaMap = new HashMap<String, Object>();
		areaMap.put("levelId", 1);
		List<Map> provinceList = areaService.queryArea(areaMap);
		model.addAttribute("provinceList", JSONArray.fromObject(provinceList));

		// //查询市列表
		if (paramMap.get("PROV_CODE") != null && StringUtils.isNotEmpty(paramMap.get("PROV_CODE").toString())) {
			areaMap.put("parentId", paramMap.get("PROV_CODE").toString());
			areaMap.put("levelId", 2);
			List<Map> cityList = areaService.queryArea(areaMap);
			model.addAttribute("cityList", JSONArray.fromObject(cityList));
		}

		Map<String, Object> param = new HashMap<String, Object>();
		if (paramMap.get("USER_NAME") != null) {
			param.put("USER_NAME", paramMap.get("USER_NAME"));
		}
		if (paramMap.get("USER_ACCOUNT") != null) {
			param.put("USER_ACCOUNT", paramMap.get("USER_ACCOUNT"));
		}
		if (paramMap.get("PROV_CODE") != null) {
			param.put("PROV_CODE", paramMap.get("PROV_CODE"));
		}
		if (paramMap.get("CITY_CODE") != null) {
			param.put("CITY_CODE", paramMap.get("CITY_CODE"));
		}
		if (paramMap.get("ORGA_NAME") != null) {
			param.put("ORGA_NAME", paramMap.get("ORGA_NAME"));
		}
		String search_type = request.getParameter("search_type");
		String currentPage = request.getParameter("currPage");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");
		PageContext page = PageContext.getContext();

		// 请自行验证
		if (null == currentPage) {
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
		HandlerResult handlerResult = countOperate.getUserOperate(param);
		page.setPagination(false);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String currentTime = simpleDateFormat.format(new Date());
		model.addAttribute("currentTime", currentTime);
		model.addAttribute("userOperateList", handlerResult.getResultList());
		model.addAttribute("page", page);
		model.addAttribute("PROV_CODE", param.get("PROV_CODE"));
		model.addAttribute("CITY_CODE", param.get("CITY_CODE"));
		model.addAllAttributes(paramMap);
		return "countOperate/list";
	}

	private Map<String, Object> getParameter(HttpServletRequest request) {
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if (request.getParameter("provinceCode") != null
				&& StringUtils.isNotEmpty(request.getParameter("provinceCode"))) {
			String provinceCode = request.getParameter("provinceCode");
			paraMap.put("PROV_CODE", provinceCode);
			paraMap.put("areaid", provinceCode.substring(0, 2));
		}
		if (request.getParameter("cityCode") != null && StringUtils.isNotEmpty(request.getParameter("cityCode"))) {
			String cityCode = request.getParameter("cityCode");
			paraMap.put("CITY_CODE", cityCode);
			paraMap.put("areaid", cityCode.substring(0, 4));
		}

		if (request.getParameter("organame") != null && StringUtils.isNotEmpty(request.getParameter("organame"))) {
			paraMap.put("ORGA_NAME", request.getParameter("organame"));
		}

		if (request.getParameter("username") != null && StringUtils.isNotEmpty(request.getParameter("username"))) {
			paraMap.put("USER_NAME", request.getParameter("username"));
		}

		if (request.getParameter("usercode") != null && StringUtils.isNotEmpty(request.getParameter("usercode"))) {
			paraMap.put("USER_ACCOUNT", request.getParameter("usercode"));
		}
		return paraMap;
	}

	/**
	 * 查询区域下的所有学校列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/queryUserEvent")
	public void queryUserEvent(HttpServletRequest request, HttpServletResponse response, Model model, String user_id)
			throws Exception {
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("USER_ID", user_id);
			List<UserOperate> userFuncList = countOperate.getUserFunc(param);

			Map resultMap = new HashMap();
			resultMap.put("list", userFuncList);
			printJson(response, resultMap);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
