package com.whty.assis.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.whty.assis.base.controller.BaseController;
import com.whty.assis.demo.model.AreaMonthActivityCount;
import com.whty.assis.demo.model.AreaMonthUsageCount;
import com.whty.assis.demo.model.OrgMonthActivityCount;
import com.whty.assis.demo.model.OrgMonthUsageCount;
import com.whty.assis.demo.model.Ta_user;
import com.whty.assis.demo.service.ActivityCountService;
import com.whty.assis.demo.service.AreaService;
import com.whty.assis.demo.service.TaUserService;
import com.whty.assis.demo.service.UseCountService;
import com.whty.assis.manage.model.BaseProperty;
import com.whty.assis.manage.service.BasePropertyService;
import com.whty.common.excel.PoiUtil;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 普通用户后台管理controller
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/managerTaUser")
public class TaUserController extends BaseController {
	// 表头
	private final static String[] headers = { "用户名", "姓名", "性别", "区域", "学校", "用户类型", "用户来源", "创建时间", "最近登录时间", "登录次数" };
	// 文件名
	private final static String filename = "用户信息表.xls";

	@Autowired
	private TaUserService taUserService;

	@Autowired
	private BasePropertyService basePropertyService;

	@Autowired
	private AreaService areaService;

	@Autowired
	private UseCountService useCountService;

	@Autowired
	private ActivityCountService activityCountService;

	private Map<String, Object> getParameter(HttpServletRequest request) {

		Map<String, Object> paraMap = new HashMap<String, Object>();
		if (request.getParameter("provinceCode") != null
				&& StringUtils.isNotEmpty(request.getParameter("provinceCode"))) {
			String provinceCode = request.getParameter("provinceCode");
			paraMap.put("provinceCode", provinceCode);
			paraMap.put("areaid", provinceCode.substring(0, 2));
		}
		if (request.getParameter("cityCode") != null && StringUtils.isNotEmpty(request.getParameter("cityCode"))) {
			String cityCode = request.getParameter("cityCode");
			paraMap.put("cityCode", cityCode);
			paraMap.put("areaid", cityCode.substring(0, 4));

		}
		if (request.getParameter("areaCode") != null && StringUtils.isNotEmpty(request.getParameter("areaCode"))) {
			String areaCode = request.getParameter("areaCode");
			paraMap.put("areaCode", areaCode);
			paraMap.put("areaid", areaCode);
		}
		if (request.getParameter("org_name") != null && StringUtils.isNotEmpty(request.getParameter("org_name"))) {
			paraMap.put("org_name", request.getParameter("org_name"));
		}
		if (request.getParameter("user_type") != null && StringUtils.isNotEmpty(request.getParameter("user_type"))) {
			paraMap.put("user_type", request.getParameter("user_type"));
		}
		if (request.getParameter("status") != null && StringUtils.isNotEmpty(request.getParameter("status"))) {
			paraMap.put("status", request.getParameter("status"));
		}
		if (request.getParameter("user_account") != null
				&& StringUtils.isNotEmpty(request.getParameter("user_account"))) {
			paraMap.put("user_account", request.getParameter("user_account"));
		}
		if (request.getParameter("platform_id") != null
				&& StringUtils.isNotEmpty(request.getParameter("platform_id"))) {
			paraMap.put("platform_id", request.getParameter("platform_id"));
		}

		return paraMap;
	}

	/**
	 * 普通用户列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("普通用户列表数据加载");
		}
		String search_type = request.getParameter("search_type");
		String currentPage = request.getParameter("currPage");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");
		String orderField = request.getParameter("orderField") == null ? "create_time"
				: request.getParameter("orderField");
		String orderState = request.getParameter("orderState") == null ? "desc" : request.getParameter("orderState");

		Map<String, Object> paramMap = this.getParameter(request);
		paramMap.put("orderField", orderField);
		paramMap.put("orderState", orderState);

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
		HandlerResult handlerResult = taUserService.queryUser(paramMap);
		model.addAttribute("list", handlerResult.getResultList());
		model.addAttribute("page", page);
		model.addAllAttributes(paramMap);
		page.setPagination(false);

		// 获取平台列表
		List platformList = basePropertyService.getPlatformList();
		model.addAttribute("platformList", platformList);

		// 查询省列表
		Map<String, Object> areaMap = new HashMap<String, Object>();
		areaMap.put("levelId", 1);
		List<Map> provinceList = areaService.queryArea(areaMap);
		model.addAttribute("provinceList", JSONArray.fromObject(provinceList));

		// //查询市列表
		if (paramMap.get("provinceCode") != null && StringUtils.isNotEmpty(paramMap.get("provinceCode").toString())) {
			areaMap.put("parentId", paramMap.get("provinceCode").toString());
			areaMap.put("levelId", 2);
			List<Map> cityList = areaService.queryArea(areaMap);
			model.addAttribute("cityList", JSONArray.fromObject(cityList));
		}

		// //查询区列表
		if (paramMap.get("cityCode") != null && StringUtils.isNotEmpty(paramMap.get("cityCode").toString())) {
			areaMap.put("parentId", paramMap.get("cityCode").toString());
			areaMap.put("levelId", 3);
			List<Map> areaList = areaService.queryArea(areaMap);
			model.addAttribute("areaList", JSONArray.fromObject(areaList));
		}

		return "user/list";
	}

	// /**
	// * 导出区域月报表
	// *
	// * @param request
	// * @param response
	// */
	// @RequestMapping(value = "/exportAreaActivityExcel")
	// public void exportAreaActivityExcel(HttpServletRequest request,
	// HttpServletResponse response) {
	//
	// try {
	// Map<String, Object> paramMap = new HashMap<String, Object>();
	// paramMap.put("contractId", request.getParameter("contractId"));
	//
	// // 表头
	// String[] tableHeader = { "机构ID|orgId", "机构名称|orgName", "区域编码|areaCode",
	// "区域名称|areaName", "活跃度|useActivity",
	// "年|year", "月|month", "上月活跃度|previousUseActivity", "平台编码|platformCode" };
	// // 文件名
	// String filename = "区域月活跃度.xls";
	//
	// // HandlerResult handlerResult =
	// // digitalPenLicenceService.queryDigitalPenLicencePage(paramMap);
	// Map cmap = null;
	// List reportList = new ArrayList();
	// // DigitalPenLicence digitalPenLicence = null;
	//
	// Map<String, Object> parm = new HashMap<String, Object>();
	//
	// List<AreaMonthActivityCount> list =
	// activityCountService.getAreaMonthActivityCount(parm);
	//
	// // 填充
	// for (int i = 0; i < list.size(); i++) {
	// cmap = new HashMap();
	// AreaMonthActivityCount bean = list.get(i);
	// // cmap.put("orgId", bean.getOrgId());
	// // cmap.put("orgName", bean.getOrgName());
	// cmap.put("areaCode", bean.getAreaCode());
	// cmap.put("areaName", bean.getAreaName());
	//
	// if ("0".equals(bean.getUseActivity())) {
	// cmap.put("useActivity", "否");
	// } else if ("1".equals(bean.getUseActivity())) {
	// cmap.put("useActivity", "是");
	// }
	// // cmap.put("useCount", bean.getUseCount());
	//
	// cmap.put("year", bean.getYear());
	// cmap.put("month", bean.getMonth());
	// // cmap.put("rate", bean.getRate());
	// // cmap.put("previousUseCount", bean.getPreviousUseCount());
	//
	// if ("0".equals(bean.getPreviousUseActivity())) {
	// cmap.put("previousUseActivity", "否");
	// } else if ("1".equals(bean.getPreviousUseActivity())) {
	// cmap.put("previousUseActivity", "是");
	// }
	//
	// cmap.put("platformCode", bean.getPlatformCode());
	//
	// reportList.add(cmap);
	// }
	//
	// String fileName = new String(filename.getBytes("GBK"), "ISO8859-1");
	//
	// ServletOutputStream os = null;
	// response.setContentType("application/vnd.ms-excel");
	// response.addHeader("Content-disposition", "attachment" + ";filename=" +
	// fileName);
	// os = response.getOutputStream();
	// HSSFWorkbook wb = new HSSFWorkbook();
	// PoiUtil.createExcelSheet(wb, tableHeader, reportList);
	// wb.write(os);
	// os.flush();
	// os.close();
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// }

	// /**
	// * 导出区域月报表
	// *
	// * @param request
	// * @param response
	// */
	// @RequestMapping(value = "/exportOrgActivityExcel")
	// public void exportOrgActivityExcel(HttpServletRequest request,
	// HttpServletResponse response) {
	// try {
	// Map<String, Object> paramMap = new HashMap<String, Object>();
	// paramMap.put("contractId", request.getParameter("contractId"));
	//
	// // 表头
	// String[] tableHeader = { "机构ID|orgId", "机构名称|orgName", "用户ID|userId",
	// "用户名|userName", "平台编码|platformCode",
	// "上月活跃度|previousUseActivity", "活跃度|useActivity", "年|year", "月|month",
	// "区域编码|areaCode",
	// "区域名称|areaName" };
	// // 文件名
	// String filename = "学校月活跃度.xls";
	//
	// // HandlerResult handlerResult =
	// // digitalPenLicenceService.queryDigitalPenLicencePage(paramMap);
	// Map cmap = null;
	// List reportList = new ArrayList();
	// // DigitalPenLicence digitalPenLicence = null;
	//
	// Map<String, Object> parm = new HashMap<String, Object>();
	// List<OrgMonthActivityCount> list =
	// activityCountService.getOrgMonthActivityCount(parm);
	//
	// // 填充
	// for (int i = 0; i < list.size(); i++) {
	// cmap = new HashMap();
	// OrgMonthActivityCount bean = list.get(i);
	// cmap.put("orgId", bean.getOrgId());
	// cmap.put("orgName", bean.getOrgName());
	// // cmap.put("userId", bean.getUserId());
	// // cmap.put("userName", bean.getUserName());
	// cmap.put("platformCode", bean.getPlatformCode());
	// // cmap.put("rate", bean.getRate());
	//
	// if ("0".equals(bean.getPreviousUseActivity())) {
	// cmap.put("previousUseActivity", "否");
	// } else if ("1".equals(bean.getPreviousUseActivity())) {
	// cmap.put("previousUseActivity", "是");
	// }
	//
	// if ("0".equals(bean.getUseActivity())) {
	// cmap.put("useActivity", "否");
	// } else if ("1".equals(bean.getUseActivity())) {
	// cmap.put("useActivity", "是");
	// }
	//
	// cmap.put("year", bean.getYear());
	// cmap.put("month", bean.getMonth());
	// cmap.put("areaCode", bean.getAreaCode());
	// cmap.put("areaName", bean.getAreaName());
	// reportList.add(cmap);
	// }
	//
	// String fileName = new String(filename.getBytes("GBK"), "ISO8859-1");
	//
	// ServletOutputStream os = null;
	// response.setContentType("application/vnd.ms-excel");
	// response.addHeader("Content-disposition", "attachment" + ";filename=" +
	// fileName);
	// os = response.getOutputStream();
	// HSSFWorkbook wb = new HSSFWorkbook();
	// PoiUtil.createExcelSheet(wb, tableHeader, reportList);
	// wb.write(os);
	// os.flush();
	// os.close();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	/**
	 * 导出区域月报表
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/exportAreaCountExcel")
	public void exportAreaCountExcel(HttpServletRequest request, HttpServletResponse response) {
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("contractId", request.getParameter("contractId"));

			// 表头
			String[] tableHeader = { "机构ID|orgId", "机构名称|orgName", "区域编码|areaCode", "区域名称|areaName", "使用次数|useCount",
					"年|year", "月|month", "增长率|rate", "上月使用次数|previousUseCount", "平台编码|platformCode" };
			// 文件名
			String filename = "区域月使用次数.xls";

			// HandlerResult handlerResult =
			// digitalPenLicenceService.queryDigitalPenLicencePage(paramMap);
			Map cmap = null;
			List reportList = new ArrayList();
			// DigitalPenLicence digitalPenLicence = null;

			Map<String, Object> param = new HashMap<String, Object>();
			List<AreaMonthUsageCount> list = useCountService.getAreaMonthUsageCount(param);

			// 填充
			for (int i = 0; i < list.size(); i++) {
				cmap = new HashMap();
				AreaMonthUsageCount bean = list.get(i);

				cmap.put("areaCode", bean.getAreaCode());
				cmap.put("areaName", bean.getAreaName());
				cmap.put("useCount", bean.getUseCount());
				cmap.put("year", bean.getYear());
				cmap.put("month", bean.getMonth());
				cmap.put("rate", bean.getRate());
				cmap.put("previousUseCount", bean.getPreviousUseCount());

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
	 * 导出区域月报表
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/exportOrgCuntExcel")
	public void exportOrgCountExcel(HttpServletRequest request, HttpServletResponse response) {
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("contractId", request.getParameter("contractId"));

			// 表头
			String[] tableHeader = { "机构ID|orgId", "机构名称|orgName", "用户ID|userId", "用户名|userName", "平台编码|platformCode",
					"增长率|rate", "上月使用次数|previousUseCount", "使用次数|useCount", "年|year", "月|month", "区域编码|areaCode",
					"区域名称|areaName" };
			// 文件名
			String filename = "学校月使用次数.xls";

			// HandlerResult handlerResult =
			// digitalPenLicenceService.queryDigitalPenLicencePage(paramMap);
			Map cmap = null;
			List reportList = new ArrayList();
			// DigitalPenLicence digitalPenLicence = null;

			Map<String, Object> param = new HashMap<String, Object>();
			List<OrgMonthUsageCount> list = useCountService.getOrgMonthUsageCount(param);

			// 填充
			for (int i = 0; i < list.size(); i++) {
				cmap = new HashMap();
				OrgMonthUsageCount bean = list.get(i);
				cmap.put("orgId", bean.getOrgId());
				cmap.put("orgName", bean.getOrgName());

				cmap.put("rate", bean.getRate());
				cmap.put("previousUseCount", bean.getPreviousUseCount());
				cmap.put("useCount", bean.getUseCount());
				cmap.put("year", bean.getYear());
				cmap.put("month", bean.getMonth());
				cmap.put("areaCode", bean.getAreaCode());
				cmap.put("areaName", bean.getAreaName());
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
	 * 启用和禁用
	 */
	@RequestMapping(value = "/enableAndDisable")
	public void enableAndDisable(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		String ids = request.getParameter("ids");
		String status = request.getParameter("status");
		List idList = Arrays.asList(ids.split(","));

		Map paramap = new HashMap();
		paramap.put("idList", idList);
		paramap.put("status", status);

		taUserService.updateUserStatus(paramap);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write("success");
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String ids = request.getParameter("ids");
		List idList = Arrays.asList(ids.split(","));

		Map paramap = new HashMap();
		paramap.put("idList", idList);

		taUserService.deleteUser(paramap);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write("success");
	}

	/**
	 * 导出用户数据
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/exportexcel")
	public void exportexcel(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setHeader("Content-Disposition",
					"attachment;filename=" + new String(filename.getBytes("UTF-8"), "ISO8859-1"));
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");

			ServletOutputStream output = response.getOutputStream();
			// 文件名称与路径
			String realPath = request.getRealPath("/");
			File file = new File(realPath + "/excel/tempPath");
			if (!file.exists()) {
				file.mkdirs();
			}
			realPath = realPath + "/excel/tempPath/" + filename;
			HSSFWorkbook workbook = new HSSFWorkbook();

			// excel的 一页
			HSSFSheet sheet = workbook.createSheet("用户信息");
			// 头部
			Row row = sheet.createRow(0);
			for (int i = 0; i < headers.length; i++) {
				row.createCell(i).setCellValue(headers[i]);
			}
			// 体部
			Map<String, Object> map = this.getParameter(request);
			List<Ta_user> ta_users = taUserService.queryUser(map).getResultList();

			if (null != ta_users) {
				int r = 1;
				for (Ta_user user : ta_users) {
					row = sheet.createRow(r);
					row.createCell(0).setCellValue(user.getUser_account());
					row.createCell(1).setCellValue(user.getReal_name());
					if ("0".equals(user.getGender())) {
						row.createCell(2).setCellValue("女");
					} else {
						row.createCell(2).setCellValue("男");
					}
					row.createCell(3).setCellValue(user.getArea_name());
					row.createCell(4).setCellValue(user.getOrg_name());
					if ("0".equals(user.getUser_type())) {
						row.createCell(5).setCellValue("学生");
					}
					if ("1".equals(user.getUser_type())) {
						row.createCell(5).setCellValue("老师");
					}
					if ("2".equals(user.getUser_type())) {
						row.createCell(5).setCellValue("学校管理员");
					}
					if ("3".equals(user.getUser_type())) {
						row.createCell(5).setCellValue("机构管理员");
					}
					if ("4".equals(user.getUser_type())) {
						row.createCell(5).setCellValue("超级管理员");
					}
					if ("5".equals(user.getUser_type())) {
						row.createCell(5).setCellValue("家长");
					}
					row.createCell(6).setCellValue(user.getPlatformName());
					DateFormat format = new SimpleDateFormat("yyyy-MM-d HH:mm:ss");
					row.createCell(7).setCellValue(format.format(user.getCreate_time()));
					row.createCell(8).setCellValue(format.format(user.getLast_time()));
					row.createCell(9).setCellValue(user.getLogin_count());
					r++;
				}
			}

			OutputStream outStream = new FileOutputStream(new File(realPath));
			workbook.write(outStream);
			outStream.flush();
			outStream.close();

			InputStream in = new FileInputStream(new File(realPath));
			byte[] buffer = new byte[4 * 1024];
			int length = 0;
			while ((length = in.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}
			output.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 查看详情
	 */
	@RequestMapping(value = "/detail")
	public void detail(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String id = request.getParameter("id");

		Map resultMap = new HashMap();

		Map paramMap = new HashMap();
		paramMap.put("id", id);

		Ta_user mUser = taUserService.getUserByParam(paramMap);

		if (StringUtils.isNoneEmpty(mUser.getPlatform_id())) {
			Map map = new HashMap();
			map.put("platform_code", mUser.getPlatform_id());
			BaseProperty baseProperty = basePropertyService.getPlatformProperty(map);
			if (baseProperty != null) {
				resultMap.put("platform_name", baseProperty.getPlatform_name());
			}

		}

		resultMap.put("user", mUser);
		resultMap.put("create_time", DateFormatUtils.format(mUser.getCreate_time(), "yyyy-MM-dd HH:mm:ss"));
		resultMap.put("use_time", DateFormatUtils.format(mUser.getUse_time(), "yyyy-MM-dd HH:mm:ss"));
		resultMap.put("last_time", DateFormatUtils.format(mUser.getLast_time(), "yyyy-MM-dd HH:mm:ss"));

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write(JSONObject.fromObject(resultMap).toString());
	}

}
