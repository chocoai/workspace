/**
 * 
 */
package com.whty.assis.basicdata.controller;

import java.util.ArrayList;
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
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.KeyRepresentation;
import com.whty.assis.base.controller.BaseController;
import com.whty.assis.basicdata.dao.DbViewDao;
import com.whty.assis.basicdata.model.DbView;
import com.whty.assis.basicdata.model.DeviceInfo;
import com.whty.assis.basicdata.model.Order;
import com.whty.assis.basicdata.model.OrderSchool;
import com.whty.assis.basicdata.model.OrderSchoolTerminalDevice;
import com.whty.assis.basicdata.model.OrderTerminalDeviceType;
import com.whty.assis.basicdata.model.SupplierInfo;
import com.whty.assis.basicdata.model.TerminalDeviceType;
import com.whty.assis.basicdata.service.AreaService;
import com.whty.assis.basicdata.service.DeviceInfoService;
import com.whty.assis.basicdata.service.OrderSchoolService;
import com.whty.assis.basicdata.service.OrderSchoolTerminalDeviceService;
import com.whty.assis.basicdata.service.OrderService;
import com.whty.assis.basicdata.service.OrderTerminalDeviceTypeService;
import com.whty.assis.basicdata.service.SchoolService;
import com.whty.assis.basicdata.service.SupplierInfoService;
import com.whty.assis.basicdata.service.TerminalDeviceTypeService;
import com.whty.common.util.SysConstants;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

import net.sf.json.JSONArray;

/**
 * @author zhangzheng
 * @date 2018年4月1日
 */
@Controller
@RequestMapping("/manage/order")
public class OrderController extends BaseController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private SupplierInfoService supplierInfoService;

	@Autowired
	private DbViewDao dbViewDao;

	@Autowired
	private TerminalDeviceTypeService terminalDeviceTypeService;

	@Autowired
	private SchoolService schoolService;

	@Autowired
	private OrderSchoolService orderSchoolService;

	@Autowired
	private OrderSchoolTerminalDeviceService orderSchoolTerminalDeviceService;

	@Autowired
	private OrderTerminalDeviceTypeService orderTerminalDeviceTypeService;

	@Autowired
	private DeviceInfoService deviceInfoService;
	
	@Autowired
	private AreaService areaService;
	// @Autowired
	// P

	/**
	 * 初始化设备
	 * 
	 * @param request
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "createDevice")
	public void createDevice(HttpServletRequest request, Model model, HttpServletResponse response) {

		String orderId = request.getParameter("orderId");

		String result = orderService.createDevice(orderId);

		printText(response, result);

	}

	/**
	 * 给学校分配激活码
	 * 
	 * @param request
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "/setSchoolLicence")
	public void setSchoolLicence(HttpServletRequest request, Model model, HttpServletResponse response)
			throws Exception {
		String orderSchoolId = request.getParameter("orderSchoolId");

		Map<String, Object> paramMap = new HashMap<String, Object>();

		paramMap.put("id", orderSchoolId);

		List<OrderSchool> orderSchoolList = orderSchoolService.listByCondition(paramMap);

		Map<String, Object> result = new HashMap<String, Object>();

		if (orderSchoolList != null && orderSchoolList.size() > 0) {
			OrderSchool bean = orderSchoolList.get(0);

			String licenceCode = null;

			boolean flag = true;
			while (flag) {
				GoogleAuthenticatorConfigBuilder gacb = new GoogleAuthenticatorConfigBuilder()
						.setKeyRepresentation(KeyRepresentation.BASE64);
				GoogleAuthenticator googleAuthenticator = new GoogleAuthenticator(gacb.build());

				final GoogleAuthenticatorKey key = googleAuthenticator.createCredentials();

				final List<Integer> scratchCodes = key.getScratchCodes();

				Map<String, Object> listParam = new HashMap<String, Object>();

				listParam.put("licenceCode", scratchCodes.get(0).toString());

				List<OrderSchool> licenceCodeOrderSchoolList = orderSchoolService.listByCondition(listParam);

				if (licenceCodeOrderSchoolList == null || licenceCodeOrderSchoolList.size() == 0) {
					licenceCode = scratchCodes.get(0).toString();
					flag = false;
				}
			}

			bean.setLicenceCode(licenceCode);
			bean.setUpdateTime(new Date());

			result.put("licenceCode", licenceCode);
			result.put("result", SysConstants.SUCCESS_CODE);
			orderSchoolService.updateOrderSchool(bean);
			printText(response, SysConstants.SUCCESS_CODE);
			return;
		}
		// result.put("result", SysConstants.FAIL_CODE);
		printText(response, SysConstants.FAIL_CODE);
	}

	/**
	 * 获取学校信息
	 * 
	 * @param request
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSchool")
	public void getSchool(HttpServletRequest request, Model model, HttpServletResponse response) throws Exception {
		String orderId = request.getParameter("orderId");
		Order order = orderService.getOrderById(Integer.valueOf(orderId));// 订单信息

		String schoolName = request.getParameter("schoolName");

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("schoolName", schoolName);
		param.put("provinceCode", order.getProvinceCode());
		param.put("cityCode", order.getCityCode());
		param.put("areaCode", order.getAreaCode());

		List<Map<String, Object>> schoolList = schoolService.listSchooleMap(param);

		printJson(response, schoolList);// 返回所有学校
	}

	@RequestMapping(value = "/saveOrupdateOrderSchoolTerminalDevice")
	public void saveOrupdateOrderSchoolTerminalDevice(HttpServletRequest request, Model model,
			HttpServletResponse response) throws Exception {
		String result = orderSchoolTerminalDeviceService.saveOrderSchoolTerminalDevice(request);

		printText(response, result);
	}

	@RequestMapping(value = "/updateSchoolTerminalDevice")
	public void updateSchoolTerminalDevice(HttpServletRequest request, Model model, HttpServletResponse response)
			throws Exception {

		String result = orderService.updateSchoolTerminalDevice(request);

		printText(response, result);
	}

	@RequestMapping(value = "/getSchoolTerminalDeviceDetail")
	public void getSchoolTerminalDeviceDetail(HttpServletRequest request, Model model, HttpServletResponse response)
			throws Exception {

		String orderId = request.getParameter("orderId");
		String orderSchoolId = request.getParameter("orderSchoolId");

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("orderSchoolId", orderSchoolId);
		param.put("orderId", orderId);
		List<OrderSchoolTerminalDevice> list = orderSchoolTerminalDeviceService.listCountByOrderAndSchool(param);

		String schoolName = null;

		List<OrderSchoolTerminalDevice> resultList = new ArrayList<OrderSchoolTerminalDevice>();

		for (OrderSchoolTerminalDevice bean : list) {
			schoolName = bean.getSchoolName();
			if (bean.getQuan() == null) {
				bean.setQuan(0);
			}

			if (bean.getUseDeviceTotalQuan() == null) {
				bean.setUseDeviceTotalQuan(0);
			}

			Map<String, Object> aaParam = new HashMap<String, Object>();

			aaParam.put("terminalDeviceTypeId", bean.getTerminalDeviceTypeId());
			aaParam.put("orderId", bean.getOrderId());

			Integer orderSchoolTerminalDeviceTotalQuan = orderSchoolTerminalDeviceService.getTotalQuanBy(aaParam);// 已分配的设备
			Integer orderTerminalDeviceTotalQuan = orderTerminalDeviceTypeService.getTotalQuanByOrderAndType(aaParam);// 设备总数

			aaParam.put("schoolId", bean.getSchoolId());
			aaParam.put("deviceStatus", 2);// 已激活绑定的设备
			List<DeviceInfo> bindDeviceNum = deviceInfoService.listByCondition(aaParam);

			bean.setBindDeviceNum(bindDeviceNum.size());
			bean.setOrderTerminalDeviceTotalQuan(orderTerminalDeviceTotalQuan);
			bean.setOrderSchoolTerminalDeviceTotalQuan(orderSchoolTerminalDeviceTotalQuan);
			bean.setNotOrderSchoolTerminalDeviceTotalQuan(
					orderTerminalDeviceTotalQuan - orderSchoolTerminalDeviceTotalQuan);
			bean.setNotUseDeviceTotalQuan(bean.getQuan() - bean.getUseDeviceTotalQuan());

			resultList.add(bean);
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("schoolName", schoolName);
		resultMap.put("list", resultList);
		printJson(response, resultMap);
	}

	@RequestMapping(value = "/setBuildSchoolPage")
	public String setBuildSchoolPage(HttpServletRequest request, Model model) throws Exception {

		String orderId = request.getParameter("orderId");
		Order order = orderService.getOrderById(Integer.valueOf(orderId));// 订单信息

		String schoolName = request.getParameter("schoolName");

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("schoolName", schoolName);
		param.put("provinceCode", order.getProvinceCode());
		param.put("cityCode", order.getCityCode());
		param.put("areaCode", order.getAreaCode());

		List<Map<String, Object>> schoolList = schoolService.listSchooleMap(param);
		model.addAttribute("schoolList", schoolList);

		int aioNotUseCount = 0;// 一体机未授权数
		int aioUseCount = 0;// 一体机授权数
		int aioCount = 0;// 一体机授权总数

		int ebookpackageNotUseCount = 0;// 电子书包未授权数
		int ebookpackageUseCount = 0;// 电子书包授权数
		int ebookpackageCount = 0;// 电子书包授权总数

		int schoolCount = 0;// 授权学校总数

		Map<String, Object> orderTerminalDeviceTypeParam = new HashMap<String, Object>(5);

		orderTerminalDeviceTypeParam.put("orderId", orderId);

		List<OrderTerminalDeviceType> orderTerminalDeviceTypeList = orderTerminalDeviceTypeService
				.listByCondition(orderTerminalDeviceTypeParam);

		List<OrderSchool> orderSchoolList = orderSchoolService.listByOrder(orderTerminalDeviceTypeParam);
		schoolCount = orderSchoolList.size();

		model.addAttribute("orderSchoolList", orderSchoolList);

		List<OrderTerminalDeviceType> pageOrderTerminalDeviceTypeList = new ArrayList<OrderTerminalDeviceType>();
		for (OrderTerminalDeviceType bean : orderTerminalDeviceTypeList) {
			if (bean.getTerminalDeviceTypeId() == 1) {// 一体机
				orderTerminalDeviceTypeParam.put("terminalDeviceTypeId", bean.getTerminalDeviceTypeId());

				Integer schoolIdTerminalTotalQuan = orderSchoolTerminalDeviceService
						.getTotalQuanBy(orderTerminalDeviceTypeParam);

				if (schoolIdTerminalTotalQuan == null) {
					schoolIdTerminalTotalQuan = 0;
				}

				if (bean.getQuan() == null) {
					bean.setQuan(0);
				}

				bean.setSchoolDeviceTotalQuan(schoolIdTerminalTotalQuan.intValue());
				bean.setSchoolNotDeviceTotalQuan(bean.getQuan().intValue() - schoolIdTerminalTotalQuan.intValue());

				Integer orderSchoolTerminalDeviceTotalQuan = orderSchoolTerminalDeviceService
						.getTotalQuanBy(orderTerminalDeviceTypeParam);// 已分配的设备
				Integer orderTerminalDeviceTotalQuan = orderTerminalDeviceTypeService
						.getTotalQuanByOrderAndType(orderTerminalDeviceTypeParam);// 设备总数

				if (orderSchoolTerminalDeviceTotalQuan == null) {
					orderSchoolTerminalDeviceTotalQuan = 0;
				}

				if (orderTerminalDeviceTotalQuan == null) {
					orderTerminalDeviceTotalQuan = 0;
				}

				aioUseCount = orderSchoolTerminalDeviceTotalQuan;
				aioCount = orderTerminalDeviceTotalQuan;
				aioNotUseCount = aioCount - aioUseCount;
			}
			if (bean.getTerminalDeviceTypeId() == 2) {// 电子书包
				orderTerminalDeviceTypeParam.put("terminalDeviceTypeId", bean.getTerminalDeviceTypeId());
				Integer schoolIdTerminalTotalQuan = orderSchoolTerminalDeviceService
						.getTotalQuanBy(orderTerminalDeviceTypeParam);

				if (schoolIdTerminalTotalQuan == null) {
					schoolIdTerminalTotalQuan = 0;
				}

				if (bean.getQuan() == null) {
					bean.setQuan(0);
				}

				bean.setSchoolDeviceTotalQuan(schoolIdTerminalTotalQuan.intValue());
				bean.setSchoolNotDeviceTotalQuan(bean.getQuan().intValue() - schoolIdTerminalTotalQuan.intValue());

				Integer orderSchoolTerminalDeviceTotalQuan = orderSchoolTerminalDeviceService
						.getTotalQuanBy(orderTerminalDeviceTypeParam);// 已分配的设备
				Integer orderTerminalDeviceTotalQuan = orderTerminalDeviceTypeService
						.getTotalQuanByOrderAndType(orderTerminalDeviceTypeParam);// 设备总数

				if (orderSchoolTerminalDeviceTotalQuan == null) {
					orderSchoolTerminalDeviceTotalQuan = 0;
				}

				if (orderTerminalDeviceTotalQuan == null) {
					orderTerminalDeviceTotalQuan = 0;
				}

				ebookpackageUseCount = orderSchoolTerminalDeviceTotalQuan;
				ebookpackageCount = orderTerminalDeviceTotalQuan;
				aioNotUseCount = ebookpackageCount - ebookpackageUseCount;
			}
			pageOrderTerminalDeviceTypeList.add(bean);
		}

		model.addAttribute("orderTerminalDeviceTypeList", orderTerminalDeviceTypeList);

		model.addAttribute("schoolCount", schoolCount);
		model.addAttribute("aioNotUseCount", aioNotUseCount);
		model.addAttribute("aioUseCount", aioUseCount);
		model.addAttribute("aioCount", aioCount);
		model.addAttribute("ebookpackageNotUseCount", ebookpackageNotUseCount);
		model.addAttribute("ebookpackageUseCount", ebookpackageUseCount);
		model.addAttribute("ebookpackageCount", ebookpackageCount);

		model.addAttribute("orderId", orderId);

		return "order.setBuildSchoolPage";
	}

	@RequestMapping(value = "/addSchoolDeviceNum")
	public void addSchoolDeviceNum(HttpServletRequest request, Model model, HttpServletResponse response)
			throws Exception {
		String result = orderService.addSchoolDeviceNum(request);
		printText(response, result);
	}

	@RequestMapping(value = "/setSchoolPage")
	public String setSchoolPage(HttpServletRequest request, Model model) throws Exception {

		String orderId = request.getParameter("orderId");

		String schoolId = request.getParameter("schoolId");

		String orderSchoolId = request.getParameter("orderSchoolId");
		model.addAttribute("orderSchoolId", orderSchoolId);

		if (StringUtils.isEmpty(orderId)) {
			return list(request, model,1);
		}

		List<Map<String, Object>> terminalDeviceTypeQuanList = new ArrayList<Map<String, Object>>();
		if (StringUtils.isEmpty(schoolId)) {

			Map<String, Object> orderTerminalDeviceTypeParam = new HashMap<String, Object>();
			orderTerminalDeviceTypeParam.put("orderId", Integer.valueOf(orderId));

			terminalDeviceTypeQuanList = orderTerminalDeviceTypeService
					.listByConditionByOrderId(Integer.valueOf(orderId));

		} else {

			terminalDeviceTypeQuanList = orderSchoolTerminalDeviceService
					.listByConditionBySchoolAndOrder(Integer.valueOf(schoolId), Integer.valueOf(orderId));

		}
		model.addAttribute("schoolId", schoolId);
		model.addAttribute("terminalDeviceTypeQuanList", terminalDeviceTypeQuanList);

		Map<String, Object> para = new HashMap<String, Object>();

		para.put("orderId", orderId);

		List<OrderSchool> orderSchoolList = orderSchoolService.listByCondition(para);
		List<OrderSchool> resultOrderSchoolList = new ArrayList<OrderSchool>();
		for (OrderSchool orderSchool : orderSchoolList) {
			Map<String, Object> orderSchoolTerminalDeviceParam = new HashMap<String, Object>(1);

			orderSchoolTerminalDeviceParam.put("orderSchoolId", orderSchool.getId());

			List<OrderSchoolTerminalDevice> orderSchoolTerminalDevice = orderSchoolTerminalDeviceService
					.listByCondition(orderSchoolTerminalDeviceParam);

			orderSchool.setOrderSchoolTerminalDevice(orderSchoolTerminalDevice);
			resultOrderSchoolList.add(orderSchool);
		}

		model.addAttribute("orderSchoolList", resultOrderSchoolList);

		Order order = orderService.getOrderById(Integer.valueOf(orderId));// 订单信息

		Map<String, Object> schoolListParam = new HashMap<String, Object>();
		schoolListParam.put("provinceCode", order.getProvinceCode());
		schoolListParam.put("cityCode", order.getCityCode());
		schoolListParam.put("areaCode", order.getAreaCode());

		List<Map<String, Object>> schoolList = schoolService.listSchooleMap(schoolListParam);

		model.addAttribute("schoolList", schoolList);
		model.addAttribute("orderId", orderId);
		model.addAttribute("order", order);

		return "order.schoolPage";
	}

	/**
	 * 添加学校
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addSchool")
	public void addSchool(HttpServletRequest request, Model model, HttpServletResponse response) throws Exception {
		String result = orderSchoolService.saveOrderSchool(request);
		printText(response, result);
	}

	@RequestMapping(value = "/deleteOrder")
	public void deleteOrder(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String result = orderService.deleteOrder(request);
		printText(response, result);
	}

	@RequestMapping(value = "/deleteOrderSchool")
	public void deleteOrderSchool(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		String result = orderService.deleteOrderSchool(request);
		printText(response, result);
	}

	@RequestMapping(value = "/add")
	public void add(HttpServletRequest request, Model model, HttpServletResponse response) throws Exception {

		String result = orderService.saveOrder(request);

		printText(response, result);
	}

	@RequestMapping(value = "/addPage")
	public String addPage(HttpServletRequest request, Model model) throws Exception {

		List<SupplierInfo> supplierInfoList = supplierInfoService.listByCondition(new HashMap<String, Object>());

		List<DbView> orderType = dbViewDao.listOrderType();

		Map<String, Object> param = new HashMap<String, Object>(1);
		param.put("levelId", "1");

		List<TerminalDeviceType> terminalDeviceTypeList = terminalDeviceTypeService
				.listByCondition(new HashMap<String, Object>());

		model.addAttribute("terminalDeviceTypeList", terminalDeviceTypeList);
		model.addAttribute("supplierInfoList", supplierInfoList);
		model.addAttribute("orderTypeList", orderType);
		return "order.add";
	}

	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, Model model,
			@RequestParam(name="pageValue",defaultValue="1")Integer page) throws Exception {
		Map<String, Object> paramMap = this.getParameterMap(request);
		String terminalDeviceTypeId = request.getParameter("terminalDeviceTypeId");// 区域id
		String orderNumber = request.getParameter("orderNumber"); 
		String contactNumber = request.getParameter("contactNumber");
		
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
		
		if (terminalDeviceTypeId != null) {
			paramMap.put("terminalDeviceTypeId", terminalDeviceTypeId);
		}
		
		if(StringUtils.isNotEmpty(orderNumber)){
			paramMap.put("orderNumber", orderNumber);
			model.addAttribute("orderNumber", orderNumber);
		}
		
		if(StringUtils.isNotEmpty(contactNumber)){
			paramMap.put("contactNumber", contactNumber);
			model.addAttribute("contactNumber", contactNumber);
		}

		PageHelper.startPage(page, 10);
		List<Order> orders = orderService.listByCondition(paramMap);
		PageInfo<Order> p = new PageInfo<Order>(orders);
		model.addAttribute("list", orders);
		model.addAttribute("pageNum", p.getPageNum());
		model.addAttribute("pages", p.getPages());
		model.addAllAttributes(paramMap);
		return "order.list";
	}
}
