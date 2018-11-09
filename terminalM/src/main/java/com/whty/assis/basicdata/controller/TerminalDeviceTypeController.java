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

import com.whty.assis.base.controller.BaseController;
import com.whty.assis.basicdata.dao.DbViewDao;
import com.whty.assis.basicdata.model.DeviceInfo;
import com.whty.assis.basicdata.model.TerminalDeviceType;
import com.whty.assis.basicdata.service.DeviceInfoService;
import com.whty.assis.basicdata.service.TerminalDeviceTypeService;
import com.whty.assis.sysres.model.TaManageUserInfo;
import com.whty.common.util.SysConstants;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * 终端设备类型
 * 
 * @author zhangzheng
 *
 */
@Controller
@RequestMapping("/manage/terminalDeviceType")
public class TerminalDeviceTypeController extends BaseController {

	@Autowired
	private TerminalDeviceTypeService terminalDeviceTypeService;

	@Autowired
	private DbViewDao dbViewDao;

	@Autowired
	private DeviceInfoService deviceInfoService;

	/**
	 * 删除设备类型
	 */
	@RequestMapping(value = "/deleteTerminalDeviceType")
	public void deleteTerminalDeviceType(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {

		String id = request.getParameter("id");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("terminalDeviceTypeId", id);
		List<DeviceInfo> deviceInfolist = deviceInfoService.listByCondition(paramMap);

		if (deviceInfolist != null && deviceInfolist.size() != 0) {
			printText(response, "001");
			return;
		}

		terminalDeviceTypeService.deletTerminalDeviceType(Integer.valueOf(id));
		printText(response, "success");
	}

	/**
	 * 保存设备类型
	 */
	@RequestMapping(value = "/saveTerminalDeviceType")
	public void saveTerminalDeviceType(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		String name = request.getParameter("name");
		String terminalUserType = request.getParameter("terminalUserType");
		String memo = request.getParameter("memo");
		String screenType = request.getParameter("screenType");
		String screenSize = request.getParameter("screenSize");
		String touchType = request.getParameter("touchType");
		
		TerminalDeviceType bean = new TerminalDeviceType();
		bean.setName(name);
		bean.setTerminalUserType(Integer.valueOf(terminalUserType));
		bean.setMemo(memo);
		bean.setScreenSize(Double.valueOf(screenSize));
		bean.setScreenType(Integer.valueOf(screenType));
		bean.setTouchType(Integer.valueOf(touchType));
		
		TaManageUserInfo mUser = (TaManageUserInfo) request.getSession().getAttribute(SysConstants.SESSION_USER);
		
		bean.setCreator(mUser.getId());
		bean.setCreateTime(new Date());
		bean.setUpdateTime(new Date());
		bean.setIconPath("../../images/terminalM/terminallist02.png");

		terminalDeviceTypeService.saveTerminalDeviceType(bean);
		printText(response, "success");
	}

	@RequestMapping(value = "/list")
	private String list(HttpServletRequest request, HttpServletResponse response, Model model) {

		String terminalUserType = request.getParameter("terminalUserType");

		int terminalUserTypeInt = 1;

		if (terminalUserType != null) {
			terminalUserTypeInt = Integer.valueOf(terminalUserType);
		}

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("terminalUserType", terminalUserTypeInt);

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
		page.setPagination(true);
		HandlerResult handlerResult = terminalDeviceTypeService.queryTerminalDeviceTypePage(paramMap);

		List<Map<String, Object>> terminalTypeList = terminalDeviceTypeService.listTerminalType();

		List<Map<String, Object>> screenTypeList = dbViewDao.listScreenType();
		List<Map<String, Object>> listTerminalUserTypeList = dbViewDao.listTerminalUserType();
		List<Map<String, Object>> listTouchTypeList = dbViewDao.listTouchType();

		model.addAttribute("listTouchTypeList", listTouchTypeList);
		model.addAttribute("screenTypeList", screenTypeList);
		model.addAttribute("listTerminalUserTypeList", listTerminalUserTypeList);

		model.addAttribute("terminalUserTypeInt", terminalUserTypeInt);
		model.addAttribute("terminalTypeList", terminalTypeList);
		model.addAttribute("list", handlerResult.getResultList());
		model.addAttribute("page", page);
		model.addAllAttributes(paramMap);
		return "terminalDeviceType.list";

	}

}
