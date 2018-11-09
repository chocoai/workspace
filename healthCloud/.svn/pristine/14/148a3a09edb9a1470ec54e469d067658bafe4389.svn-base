package com.yhcrt.healthcloud.device.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yhcrt.healthcloud.base.controller.BaseController;
import com.yhcrt.healthcloud.common.Constants;
import com.yhcrt.healthcloud.common.DataTable;
import com.yhcrt.healthcloud.device.entity.Device;
import com.yhcrt.healthcloud.device.entity.MemberDeviceVideo;
import com.yhcrt.healthcloud.device.service.DeviceService;
import com.yhcrt.healthcloud.device.service.DeviceVideoService;
import com.yhcrt.healthcloud.organization.entity.Organization;
import com.yhcrt.healthcloud.organization.service.OrganizationService;
import com.yhcrt.healthcloud.system.entity.SysUser;
import com.yhcrt.healthcloud.util.DateUtil;
import com.yhcrt.healthcloud.util.FileUtil;
import com.yhcrt.healthcloud.util.ImportExcelUtil;
import com.yhcrt.healthcloud.util.RegExpUtil;
import com.yhcrt.healthcloud.util.exportUtil;

@Controller
@RequestMapping("/device")
public class DeviceController extends BaseController {

	@Autowired
	private DeviceService deviceService;

	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private DeviceVideoService deviceVideoService;

	/**
	 * 跳转设备界面
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String orgId = request.getParameter("orgId");
		orgId = StringUtils.isBlank(orgId) ? getLoginOrg(getLoginUser()).getOrgId().toString() : orgId;
		Organization org = organizationService.selectByPrimaryKey(Integer.parseInt(orgId));
		String imei = request.getParameter("imei");
		String startStatus = request.getParameter("startStatus");
		String isUse = request.getParameter("isUse");
		String deviceType = request.getParameter("deviceType");
		if (StringUtils.isNotBlank(imei)) {
			modelMap.put("imei", imei.trim());
		}
		if (StringUtils.isNotBlank(deviceType)) {
			modelMap.put("deviceType", deviceType.trim());
		}
		if (StringUtils.isNotBlank(startStatus)) {
			modelMap.put("startStatus", startStatus);
		}
		if (StringUtils.isNotBlank(isUse)) {
			modelMap.put("isUse", isUse);
		}
		modelMap.put("org", org);
		return "device/list";
	}

	/**
	 * 返回表格查询数据
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/pageList")
	public void pageList(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String orgId = request.getParameter("orgId");
		String imei = request.getParameter("imei");
		String startStatus = request.getParameter("startStatus");
		String deviceType = request.getParameter("deviceType");
		String isUse = request.getParameter("isUse");
		if (StringUtils.isNotBlank(imei)) {
			map.put("imei", imei.trim());
		}
		if (StringUtils.isNotBlank(deviceType)) {
			map.put("deviceType", deviceType.trim());
		}
		if (StringUtils.isNotBlank(startStatus)) {
			map.put("startStatus", startStatus);
		}
		if (StringUtils.isNotBlank(isUse)) {
			map.put("isUse", isUse);
		}
		try {
			DataTable dataTable = new DataTable(request);
			List<Integer> orgId_list = getOrgList(StringUtils.isNotBlank(orgId) ? Integer.parseInt(orgId) : null);
			map.put("orgId_list", orgId_list);
			PageHelper.startPage(dataTable.getPageNum(), dataTable.getPageSize());
			List<Device> deviceList = deviceService.listDevicesByArgs(map);
			PageInfo<Device> pageInfo = new PageInfo<Device>(deviceList);
			// 封装数据给DataTables
			dataTable.setDraw(dataTable.getDraw());
			dataTable.setData(pageInfo.getList());
			dataTable.setRecordsTotal((int) pageInfo.getTotal());
			dataTable.setRecordsFiltered(dataTable.getRecordsTotal());
			String jsonString = JSON.toJSONString(dataTable);
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/videoControl", method = RequestMethod.GET)
	public String videoControl(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String orgId = request.getParameter("orgId");
		orgId = StringUtils.isBlank(orgId) ? getLoginOrg(getLoginUser()).getOrgId().toString() : orgId;
		List<Integer> orgList = getOrgList(Integer.parseInt(orgId));
		Organization org = organizationService.selectByPrimaryKey(Integer.parseInt(orgId));
		String deviceName = request.getParameter("deviceName");
		String deviceModel = request.getParameter("deviceModel");
		Map<String, Object> args = new HashMap<String, Object>();
		if (!orgList.isEmpty() && orgList.size() > 0) {
			args.put("orgList", orgList);
		}
		if (StringUtils.isNotBlank(deviceName)) {
			args.put("deviceName", deviceName.trim());
		}
		if (StringUtils.isNotBlank(deviceModel)) {
			args.put("deviceModel", deviceModel.trim());
		}
		List<MemberDeviceVideo> list = deviceVideoService.queryPageList(args);
		modelMap.put("list", list);
		modelMap.put("org", org);
		modelMap.put("deviceName", deviceName);
		modelMap.put("deviceModel", deviceModel);
		return "device/videoControl";
	}

	@RequestMapping(value = "/videoList", method = RequestMethod.GET)
	public String videoList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String orgId = request.getParameter("orgId");
		orgId = StringUtils.isBlank(orgId) ? getLoginOrg(getLoginUser()).getOrgId().toString() : orgId;
		List<Integer> orgList = getOrgList(Integer.parseInt(orgId));
		Organization org = organizationService.selectByPrimaryKey(Integer.parseInt(orgId));
		String deviceName = request.getParameter("deviceName");
		String deviceModel = request.getParameter("deviceModel");
		Map<String, Object> args = new HashMap<String, Object>();
		if (!orgList.isEmpty() && orgList.size() > 0) {
			args.put("orgList", orgList);
		}
		if (StringUtils.isNotBlank(deviceName)) {
			args.put("deviceName", deviceName.trim());
		}
		if (StringUtils.isNotBlank(deviceModel)) {
			args.put("deviceModel", deviceModel.trim());
		}
		List<MemberDeviceVideo> list = deviceVideoService.queryPageList(args);
		modelMap.put("list", list);
		modelMap.put("org", org);
		modelMap.put("deviceName", deviceName);
		modelMap.put("deviceModel", deviceModel);
		return "device/videoControlList";
	}

	@RequestMapping(value = "/asyncList")
	@ResponseBody
	public Object asyncList(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {
		String orgId = request.getParameter("orgId");
		orgId = StringUtils.isBlank(orgId) ? getLoginOrg(getLoginUser()).getOrgId().toString() : orgId;
		List<Integer> orgList = getOrgList(Integer.parseInt(orgId));
		Organization org = organizationService.selectByPrimaryKey(Integer.parseInt(orgId));
		String deviceName = request.getParameter("deviceName");
		String deviceModel = request.getParameter("deviceModel");
		Map<String, Object> args = new HashMap<String, Object>();
		if (!orgList.isEmpty() && orgList.size() > 0) {
			args.put("orgList", orgList);
		}
		if (StringUtils.isNotBlank(deviceName)) {
			args.put("deviceName", deviceName.trim());
		}
		if (StringUtils.isNotBlank(deviceModel)) {
			args.put("deviceModel", deviceModel.trim());
		}
		List<MemberDeviceVideo> list = deviceVideoService.queryPageList(args);
		modelMap.put("list", list);
		modelMap.put("org", org);
		modelMap.put("deviceName", deviceName);
		modelMap.put("deviceModel", deviceModel);
		return JSONObject.toJSON(list);
	}

	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String deviceId = request.getParameter("deviceId");
		// 获取当前登录用户的机构
		Organization org = getLoginOrg(getLoginUser());
		Device device = new Device();
		if (StringUtils.isNotBlank(deviceId)) {
			device = deviceService.getDeviceByDeviceId(Integer.parseInt(deviceId));
		}else{
			device.setOrg(org);
		}
		modelMap.put("device", device);
		return "device/add";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String add(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attr, Device device) {
		String deviceId = request.getParameter("deviceId");
		int n = 0; 
		if (StringUtils.isNotBlank(deviceId)) {
			Device entity = deviceService.getDeviceByDeviceId(Integer.parseInt(deviceId));
			BeanUtils.copyProperties(device, entity, "isUse", "createUser", "createTime", "status");
			entity.setUpdateTime(DateUtil.getDateTime());
			n = deviceService.update(entity);
		}else{
			device.setCreateTime(DateUtil.getDateTime());
			device.setCreateUser(getLoginUser().getUserId());
			device.setStatus(Constants.STATUS_NORMAL);
			device.setIsUse(com.yhcrt.healthcloud.common.Constants.Device.UNUSED);
			n = deviceService.insert(device);
		}
		if (n == 1) {
			attr.addFlashAttribute("state", "SUCCESS");
		} else {
			attr.addFlashAttribute("state", "FALSE");
		}
		return "redirect:list";
	}

	/**
	 * 删除终端设备
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String deviceId = request.getParameter("deviceId");
		if (StringUtils.isNotBlank(deviceId)) {
			// 逻辑删除，更新数据状态标识位，不删除数据库中的记录
			Device device = deviceService.getDeviceByDeviceId(Integer.parseInt(deviceId));
			device.setStatus(Constants.STATUS_DISABLE);
			deviceService.update(device);
		}
		return "redirect:list";
	}

	@RequestMapping(value = "/batchDelete", method = RequestMethod.POST)
	public void batchDelete(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String[] deviceIds = request.getParameterValues("deviceIds");
		for (String deviceId : deviceIds) {
			// 物理删除
			// deviceService.deleteByDeviceId(Integer.parseInt(deviceId));
			// 逻辑删除，更新数据状态标识位，不删除数据库中的记录
			Device device = deviceService.getDeviceByDeviceId(Integer.parseInt(deviceId));
			device.setStatus(Constants.STATUS_DISABLE);
			deviceService.update(device);
		}
	}

	@RequestMapping(value = "/assignDevice", method = RequestMethod.POST)
	public void assignDevice(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String[] deviceIds = request.getParameterValues("deviceIds");
		String organizationId = request.getParameter("organizationId");
		for (String deviceId : deviceIds) {
			Device device = deviceService.getDeviceByDeviceId(Integer.parseInt(deviceId));
			if (StringUtils.isNotBlank(organizationId)) {
				device.setOrgId(Integer.parseInt(organizationId));
				deviceService.update(device);
			}
		}
	}

	/**
	 * 检查IMEI是否已使用
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @throws IOException
	 */
	@RequestMapping(value = "/checkIMEI", method = RequestMethod.POST)
	public void checkIMEI(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		String deviceId = request.getParameter("deviceId");
		if(StringUtils.isNotBlank(deviceId)){
			//如果不为空则表示编辑时验证,否则为新增时验证
			map.put("deviceId", Integer.parseInt(deviceId));
		}
		String imei = request.getParameter("imei");
		map.put("imei", imei);
		int count = deviceService.countByMap(map);
		if (count > 0) {
			response.getWriter().print(false);
		} else {
			response.getWriter().print(true);
		}
	}

	/**
	 * 检查SIM是否已使用
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @throws IOException
	 */
	@RequestMapping(value = "/checkSIM", method = RequestMethod.POST)
	public void checkSIM(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		String deviceId = request.getParameter("deviceId");
		if(StringUtils.isNotBlank(deviceId)){
			//如果不为空则表示编辑时验证,否则为新增时验证
			map.put("deviceId", Integer.parseInt(deviceId));
		}
		String sim = request.getParameter("sim");
		if (StringUtils.isNotBlank(sim)) {
			map.put("sim", sim);
		} else {
			response.getWriter().print(true);
		}
		int count = deviceService.countByMap(map);
		if (count > 0) {
			response.getWriter().print(false);
		} else {
			response.getWriter().print(true);
		}
	}

	/**
	 * 导出数据到excel
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @throws IOException
	 */
	@RequestMapping(value = "/exportToExcel", method = RequestMethod.GET)
	public void exportToExcel(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException {
		String orgId = request.getParameter("orgId");
		orgId = StringUtils.isBlank(orgId) ? getLoginOrg(getLoginUser())
				.getOrgId().toString() : orgId;
		String imei = request.getParameter("imei");
		String isUse = request.getParameter("isUse");
		String deviceType = request.getParameter("deviceType");
		HashMap<String, Object> args = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(orgId)) {
			args.put("orgId", orgId);
		}
		if (StringUtils.isNotBlank(imei)) {
			args.put("imei", imei.trim());
		}
		if (StringUtils.isNotBlank(deviceType)) {
			args.put("deviceType", deviceType.trim());
		}
		if (StringUtils.isNotBlank(isUse)) {
			args.put("isUse", isUse);
		}
		List<Device> deviceList = deviceService.listDevicesByArgs(args);

		// 设置excel表头
		String[] header = { "设备名称", "IMEI号", "所属机构", "设备类别", "设备型号", "SIM卡号",
				"是否使用", "录入时间", "备注" };
		List<Map<Integer, Object>> list = new ArrayList<Map<Integer, Object>>();
		for (Device device : deviceList) {
			HashMap<Integer, Object> map = new HashMap<Integer, Object>();
			map.put(0, device.getDeviceName());
			map.put(1, device.getImei());
			map.put(2, device.getOrg().getOrgName());
			map.put(3, device.getDeviceCategory());
			map.put(4, device.getDeviceType());
			map.put(5, device.getSimView());
			map.put(6, device.getIsUseView());
			map.put(7, device.getCreateTime());
			map.put(8, device.getRemark());
			list.add(map);
		}

		String title = "终端设备数据";
		Workbook wb = exportUtil.create(title, header, list);
		String fileName = "【数据】终端设备数据导出.xlsx";
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename="
				+ URLEncoder.encode(fileName, "UTF-8"));
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();

	}

	/**
	 * 批量导入数据预处理
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @throws IOException
	 */
	@RequestMapping(value = "/toBatchImport", method = RequestMethod.GET)
	public String toBatchImport(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) throws IOException {
		return "device/import";
	}

	/**
	 * 批量导入数据预处理
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @throws IOException
	 */
	@RequestMapping(value = "/batchImport", method = RequestMethod.POST)
	public void batchImport(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) throws Exception {
		JSONObject jsonObj = new JSONObject();
		try {
			SysUser sysUser = getLoginUser();
			String orgId = request.getParameter("orgId");
			if (StringUtils.isBlank(orgId)) {
				throw new Exception("请选择导入设备的所属机构");
			}
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile mutiparFile = multipartRequest.getFile("deviceFile");
			if (mutiparFile == null) {
				throw new Exception("文件不存在,请上传Excel文件");
			}
			String suffix = FileUtil.getFileSufix(
					mutiparFile.getOriginalFilename()).toLowerCase();
			if (!suffix.contains("xls") && !suffix.contains("xlsx")) {
				throw new Exception("上传文件只支持(xls、xlsx)文件格式");
			}
			if (mutiparFile.getSize() > 5 * 1024 * 1024) {
				throw new Exception("文件过大,请将大小控制在5M以内");
			}

			InputStream in = mutiparFile.getInputStream();
			List<List<Object>> objList = ImportExcelUtil.getBankListByExcel(in, mutiparFile.getOriginalFilename());
			for (int i = 0; i < objList.size(); i++) {
				List<Object> obj = objList.get(i);
				int index = i + 1;
				if (StringUtils.isBlank(String.valueOf(obj.get(1)))) {
					throw new Exception("成功导入" + i + "条数据,第" + index + "条数据格式有误，IMEI号为必填项");
				}
				if (!RegExpUtil.isIMEI(String.valueOf(obj.get(1)))) {
					throw new Exception("成功导入" + i + "条数据,第" + index + "条数据格式有误，IMEI号格式有误");
				}
				if (null != deviceService.selectByImei(String.valueOf(obj.get(1)))) {
					throw new Exception("成功导入" + i + "条数据,第" + index + "条数据有误，IMEI号重复");
				}
				if (StringUtils.isNotBlank(String.valueOf(obj.get(4))) && !RegExpUtil.isMobile(String.valueOf(obj.get(4)))) {
					throw new Exception("成功导入" + i + "条数据,第" + index + "条数据格式有误，SIM号格式有误");
				}
				Device device = new Device();
				device.setDeviceId(sysSequenceService.getSequenceValue(Constants.SequenceName.DEVICE));
				device.setDeviceName(String.valueOf(obj.get(0)));
				device.setImei(String.valueOf(obj.get(1)));
				device.setOrgId(Integer.parseInt(orgId));
				device.setDeviceType(String.valueOf(obj.get(3)));
				device.setSim(String.valueOf(obj.get(4)));
				device.setIsUse(Constants.Device.UNUSED);
				device.setCreateUser(sysUser.getUserId());
				device.setCreateTime(DateUtil.getDateTime());
				device.setStatus(Constants.STATUS_NORMAL);
				device.setRemark(String.valueOf(obj.get(5)));
				deviceService.insert(device);
			}
			jsonObj.put("result", true);
			jsonObj.put("msg", "导入成功");
		} catch (Exception e) {
			e.printStackTrace();
			jsonObj.put("result", false);
			jsonObj.put("msg", e.getMessage().toString());
		} finally {
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonObj.toString());
		}

	}

}
