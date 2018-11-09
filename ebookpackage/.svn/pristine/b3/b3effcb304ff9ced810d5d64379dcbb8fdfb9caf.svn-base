package com.whty.ebp.manage.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.whty.common.excel.PoiUtil;
import com.whty.common.util.CommonFunction;
import com.whty.ebp.base.controller.BaseController;
import com.whty.ebp.manage.model.UserOptLog;
import com.whty.ebp.manage.service.UserOptLogService;
import com.whty.ebp.manage.utils.TimeUtil;
import com.whty.ebp.sys.model.SysUser;

/*
 * 统计
 */
@Controller
@RequestMapping("/manage/userCount")
public class UserCountController extends BaseController {

	@Autowired
	private UserOptLogService userOptLogService;

	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		paramMap.put("startTime", startTime);
		paramMap.put("endTime", endTime);
		Map<String, Object> parm = new HashMap<String, Object>();

		if (startTime == null || "".equals(startTime)) {
			startTime = CommonFunction.getDateSampleString(TimeUtil.getCurrentMonthStartDate());
		}

		if (endTime == null || "".equals(endTime)) {
			endTime = CommonFunction.getDateSampleString(TimeUtil.getCurrentMonthEndDate());
		}

		parm.put("startTime", startTime);
		parm.put("endTime", endTime);

		List<UserOptLog> list = userOptLogService.getUserCount(parm);

		Map<String, UserObject> date = new LinkedHashMap<String, UserObject>();

		Collections.sort(list, new Comparator<UserOptLog>() {
			@Override
			public int compare(UserOptLog bean1, UserOptLog bean2) {
				return bean1.getCountTime().compareTo(bean2.getCountTime());
			}
		});

		Set<String> userSet = new HashSet<String>();

		System.out.println("---------userSet size: " + userSet.size());
		for (UserOptLog bean : list) {

			UserObject eg = date.get(bean.getCountTime());

			if (eg == null) {// 初始化日期
				UserObject userObject = new UserObject();
				userObject.setUserCount(1);
				userObject.setNewUserCount(0);
				date.put(bean.getCountTime(), userObject);

				if (!userSet.contains(bean.getUserId())) {// 已经存在该用户则不是新增用户
					userSet.add(bean.getUserId());// 将新增用户存到用户表
					userObject.setNewUserCount(userObject.getNewUserCount() + 1);
				}

			} else {
				eg.setUserCount(eg.getUserCount() + 1);
				if (!userSet.contains(bean.getUserId())) {// 已经存在该用户则不是新增用户
					userSet.add(bean.getUserId());// 将新增用户存到用户表
					eg.setNewUserCount(eg.getNewUserCount() + 1);
				}
			}
		}

		List dataList = new ArrayList();
		Map cmap = null;

		int i = 0;

		for (String key : date.keySet()) {
			UserObject value = date.get(key);

			cmap = new HashMap();

			cmap.put("useTime", key);
			cmap.put("userCount", value.getUserCount());
			cmap.put("newUserCount", value.getNewUserCount());

			dataList.add(cmap);
		}

		model.addAttribute("dataList", dataList);
		model.addAllAttributes(paramMap);
		return "useCount/list";

	}

	/**
	 * 跳转到版本管理
	 */
	@RequestMapping(value = "/exportExcel")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		paramMap.put("startTime", startTime);
		paramMap.put("endTime", endTime);
		Map<String, Object> parm = new HashMap<String, Object>();

		if (startTime == null || "".equals(startTime)) {
			startTime = CommonFunction.getDateSampleString(TimeUtil.getCurrentMonthStartDate());
		}

		if (endTime == null || "".equals(endTime)) {
			endTime = CommonFunction.getDateSampleString(TimeUtil.getCurrentMonthEndDate());
		}

		parm.put("startTime", startTime);
		parm.put("endTime", endTime);

		List<UserOptLog> list = userOptLogService.getUserCount(parm);

		Map<String, UserObject> date = new LinkedHashMap<String, UserObject>();

		Collections.sort(list, new Comparator<UserOptLog>() {
			@Override
			public int compare(UserOptLog bean1, UserOptLog bean2) {
				return bean1.getCountTime().compareTo(bean2.getCountTime());
			}
		});

		Set<String> userSet = new HashSet<String>();

//		System.out.println("---------userSet size: " + userSet.size());
		for (UserOptLog bean : list) {

			UserObject eg = date.get(bean.getCountTime());

			if (eg == null) {// 初始化日期
				UserObject userObject = new UserObject();
				userObject.setUserCount(1);
				userObject.setNewUserCount(0);
				date.put(bean.getCountTime(), userObject);

				if (!userSet.contains(bean.getUserId())) {// 已经存在该用户则不是新增用户
					userSet.add(bean.getUserId());// 将新增用户存到用户表
					userObject.setNewUserCount(userObject.getNewUserCount() + 1);
				}

			} else {
				eg.setUserCount(eg.getUserCount() + 1);
				if (!userSet.contains(bean.getUserId())) {// 已经存在该用户则不是新增用户
					userSet.add(bean.getUserId());// 将新增用户存到用户表
					eg.setNewUserCount(eg.getNewUserCount() + 1);
				}
			}
		}

		String filename = "数据";

		String[] tableHeader = { "日期|useTime", "登录用户数|userCount", "新增用户数|newUserCount" };
		List reportList = new ArrayList();
		Map cmap = null;

		int i = 0;

		for (String key : date.keySet()) {
			UserObject value = date.get(key);

			cmap = new HashMap();

			cmap.put("useTime", key);
			cmap.put("userCount", value.getUserCount());
			cmap.put("newUserCount", value.getNewUserCount());

			reportList.add(cmap);
		}
		String fileName = new String(filename.getBytes("GBK"), "ISO8859-1");
		ServletOutputStream os = null;
		response.setContentType("application/vnd.ms-excel");
		response.addHeader("Content-disposition", "attachment" + ";filename=" + fileName + ".xls");
		os = response.getOutputStream();
		HSSFWorkbook wb = new HSSFWorkbook();
		PoiUtil.createExcelSheet(wb, tableHeader, reportList);
		wb.write(os);
		os.flush();
		os.close();

	}

	class UserObject {
		private int userCount;// 启动次数
		private int newUserCount;// 新增用户(每日)

		public int getUserCount() {
			return userCount;
		}

		public void setUserCount(int userCount) {
			this.userCount = userCount;
		}

		public int getNewUserCount() {
			return newUserCount;
		}

		public void setNewUserCount(int newUserCount) {
			this.newUserCount = newUserCount;
		}

	}

}
