/**
 * 
 */
package com.whty.assis.device.controller;

import java.util.ArrayList;
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

import com.aliyun.iot.util.LogUtil;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.iot.model.v20180120.QueryDeviceDetailRequest;
import com.aliyuncs.iot.model.v20180120.QueryDeviceDetailResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whty.assis.base.controller.BaseController;
import com.whty.assis.basicdata.model.DeviceInfo;
import com.whty.assis.basicdata.model.DeviceMonitorLog;
import com.whty.assis.basicdata.service.AreaService;
import com.whty.assis.basicdata.service.DeviceInfoService;
import com.whty.assis.basicdata.service.DeviceMonitorLogService;
import com.whty.assis.sysres.model.TaManageUserInfo;
import com.whty.common.util.SysConfig;
import com.whty.common.util.SysConstants;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

import net.sf.json.JSONArray;

/**
 * 电子书包
 * 
 * @author zhangzheng
 * @date 2018年4月6日
 */
@Controller
@RequestMapping("/manage/ebookpackage")
public class EbookpackageContrller extends BaseController {
	@Autowired
	private DeviceInfoService deviceInfoService;

	@Autowired
	private AreaService areaService;

	@Autowired
	private DeviceMonitorLogService deviceMonitorLogService;
	
	@RequestMapping(value = "/deviceDetail")
	public String deviceDetail(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String deviceId = request.getParameter("deviceId");

//		DeviceInfo deviceInfo = deviceInfoService.loadById(Integer.valueOf(deviceId));

		DeviceInfo deviceInfo = deviceInfoService.loadEbookpackageById(Integer.valueOf(deviceId));
		
		String regionId = SysConfig.getStrValue("iot.regionId");
		String accessKeyID = SysConfig.getStrValue("user.accessKeyID");
		String accessKeySecret = SysConfig.getStrValue("user.accessKeySecret");
		String domain = SysConfig.getStrValue("iot.domain");
		IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyID, accessKeySecret);
		DefaultAcsClient client = new DefaultAcsClient(profile);
		DefaultProfile.addEndpoint(regionId, regionId, deviceInfo.getAliProductKey(), domain);

		// 根据阿里
		QueryDeviceDetailRequest queryDeviceDetailRequest = new QueryDeviceDetailRequest();
		queryDeviceDetailRequest.setProductKey(deviceInfo.getAliProductKey());
		queryDeviceDetailRequest.setDeviceName(deviceInfo.getAliDeviceName());
		queryDeviceDetailRequest.setIotId(deviceInfo.getAliIotId());
		QueryDeviceDetailResponse registerDeviceResponse = client.getAcsResponse(queryDeviceDetailRequest);

		if (registerDeviceResponse != null && registerDeviceResponse.getSuccess() != false) {
			LogUtil.print("查询设备详情成功！ " + com.alibaba.fastjson.JSONObject.toJSONString(registerDeviceResponse));
		} else {
			LogUtil.error("查询设备详情失败！requestId:" + registerDeviceResponse.getRequestId() + "原因："
					+ registerDeviceResponse.getErrorMessage());
		}
		String status = registerDeviceResponse.getData().getStatus();

		if ("online".equals(status.toLowerCase())) {
			deviceInfo.setOnLineType(1);// 在线
		} else {
			deviceInfo.setOnLineType(0);// 离线
		}

		Map<String, Object> param = new HashMap<String, Object>();

		param.put("deviceId", deviceId);

		PageContext page = PageContext.getContext();
		page.setPagination(false);

		DeviceMonitorLog deviceMonitorLog = deviceMonitorLogService.loadByDeviceLast(param);
		String imgPath = "../../images/terminalM/581x318_1.png";
		if (deviceMonitorLog != null) {
			imgPath = deviceMonitorLog.getPath();
		}
		model.addAttribute("imgPath", imgPath);
		model.addAttribute("deviceInfo", deviceInfo);

		return "ebookpackage.detail";
	}
	
	@RequestMapping(value = "/queryDevice")
	public void queryDevice(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		Map<String, Object> paraMap = this.getParameterMap(request);
		PageContext page = PageContext.getContext();
		page.setCurrentPage(Integer.parseInt(paraMap.get("currPage").toString()));
		page.setPageSize(Integer.parseInt(paraMap.get("pageSize").toString()));

		String schoolId = request.getParameter("schoolId");
		String provinceCode = request.getParameter("provinceCode");
		String cityCode = request.getParameter("cityName");
		String areaCode = request.getParameter("areaCode");

		String brandName = request.getParameter("brandName");
		String modelName = request.getParameter("modelName");

		if (schoolId != null && !"".equals(schoolId)) {
			paraMap.put("schoolId", provinceCode);
		}

		if (cityCode != null && !"".equals(cityCode)) {
			paraMap.put("cityCode", cityCode);
		}

		if (provinceCode != null && !"".equals(provinceCode)) {
			paraMap.put("provinceCode", provinceCode);
		}

		if (areaCode != null && !"".equals(areaCode)) {
			paraMap.put("areaCode", areaCode);
		}

		if (StringUtils.isNotEmpty(brandName)) {
			paraMap.put("brandName", brandName);
		}

		if (StringUtils.isNotEmpty(modelName)) {
			paraMap.put("modelName", modelName);
		}

		HandlerResult pageList = new HandlerResult();
		List<DeviceInfo> deviceInfoList = new ArrayList<DeviceInfo>();
		String regionId = SysConfig.getStrValue("iot.regionId");
		String accessKeyID = SysConfig.getStrValue("user.accessKeyID");
		String accessKeySecret = SysConfig.getStrValue("user.accessKeySecret");
		String domain = SysConfig.getStrValue("iot.domain");
		try {
			page.setPagination(true);
			pageList = deviceInfoService.queryEbookPackagePage(paraMap, page);// 获取电子书包设备信息
			page.setPagination(false);

			for (int i = 0; i < pageList.getResultList().size(); i++) {
				DeviceInfo bean = (DeviceInfo) pageList.getResultList().get(i);
				IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyID, accessKeySecret);
				DefaultAcsClient client = new DefaultAcsClient(profile);
				DefaultProfile.addEndpoint(regionId, regionId, bean.getAliProductKey(), domain);
				// 根据阿里
				QueryDeviceDetailRequest queryDeviceDetailRequest = new QueryDeviceDetailRequest();

				queryDeviceDetailRequest.setProductKey(bean.getAliProductKey());
				queryDeviceDetailRequest.setDeviceName(bean.getAliDeviceName());
				queryDeviceDetailRequest.setIotId(bean.getAliIotId());
				QueryDeviceDetailResponse registerDeviceResponse = client.getAcsResponse(queryDeviceDetailRequest);
				if (registerDeviceResponse != null && registerDeviceResponse.getSuccess() != false) {
					LogUtil.print("查询设备详情成功！ " + com.alibaba.fastjson.JSONObject.toJSONString(registerDeviceResponse));
				} else {
					LogUtil.error("查询设备详情失败！requestId:" + registerDeviceResponse.getRequestId() + "原因："
							+ registerDeviceResponse.getErrorMessage());
				}
				String status = registerDeviceResponse.getData().getStatus();
				if ("online".equals(status)) {
					bean.setOnLineType(0);// 在线
				} else {
					bean.setOnLineType(1);// 离线
				}

				deviceInfoList.add(bean);
			}

			page.setPagination(false);

		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", deviceInfoList);
		resultMap.put("page", pageList.getPage());
		printJson(response, resultMap);
	}

	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, Model model,
			@RequestParam(name="pageValue",defaultValue="1")Integer page) throws Exception {
		
		TaManageUserInfo mUser = (TaManageUserInfo) request.getSession().getAttribute(SysConstants.SESSION_USER);
		
		Map<String, Object> paramMap = this.getParameterMap(request);

		Map<String, Object> areaMap = new HashMap<String, Object>();
		areaMap.put("levelId", 1);
		List<Map<String, Object>> provinceList = areaService.listArea(areaMap);
		model.addAttribute("provinceList", JSONArray.fromObject(provinceList));

		String isLock = "1";

		if (!mUser.getRole().getId().equals("1")) {
			isLock = "2";
			
			if(mUser.getSchool()!=null){
				paramMap.put("provinceCode", mUser.getSchool().getProvinceCode());
				paramMap.put("cityCode", mUser.getSchool().getCityCode());
				paramMap.put("areaCode", mUser.getSchool().getAreaCode());
				paramMap.put("schoolName", mUser.getSchool().getName());
				
				model.addAttribute("provinceCode", mUser.getSchool().getProvinceCode());
				model.addAttribute("cityCode", mUser.getSchool().getCityCode());
				model.addAttribute("areaCode", mUser.getSchool().getAreaCode());
				model.addAttribute("schoolName", mUser.getSchool().getName());
			}else{
				paramMap.put("provinceCode", mUser.getProvinceCode());
				paramMap.put("cityCode", mUser.getCityCode());
				paramMap.put("areaCode", mUser.getAreaCode());
				
				model.addAttribute("provinceCode", mUser.getSchool().getProvinceCode());
				model.addAttribute("cityCode", mUser.getCityCode());
				model.addAttribute("areaCode", mUser.getAreaCode());
			}
		}

		model.addAttribute("isLock", isLock);
		
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

		String terminalDeviceTypeId = request.getParameter("terminalDeviceTypeId");// 区域id

		String brandName = request.getParameter("brandName");
		String modelName = request.getParameter("modelName");
		if (terminalDeviceTypeId != null) {
			paramMap.put("terminalDeviceTypeId", terminalDeviceTypeId);
		}

		if (StringUtils.isNotEmpty(brandName)) {
			paramMap.put("brandName", brandName);
			model.addAttribute("brandName", brandName);
		}

		if (StringUtils.isNotEmpty(modelName)) {
			paramMap.put("modelName", modelName);
			model.addAttribute("modelName", modelName);
		}


		List<DeviceInfo> deviceInfoList = new ArrayList<DeviceInfo>();
		String regionId = SysConfig.getStrValue("iot.regionId");
		String accessKeyID = SysConfig.getStrValue("user.accessKeyID");
		String accessKeySecret = SysConfig.getStrValue("user.accessKeySecret");
		String domain = SysConfig.getStrValue("iot.domain");

		PageHelper.startPage(page, 10);
		List<DeviceInfo> deviceInfoList2 = deviceInfoService.queryEbookPackagePage2(paramMap);// 获取电子书包设备信息
		PageInfo<DeviceInfo>  p = new PageInfo<DeviceInfo>(deviceInfoList2);
		
		for (int i = 0; i < deviceInfoList2.size(); i++) {
			DeviceInfo bean = deviceInfoList2.get(i);
			IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyID, accessKeySecret);
			DefaultAcsClient client = new DefaultAcsClient(profile);
			DefaultProfile.addEndpoint(regionId, regionId, bean.getAliProductKey(), domain);
			// 根据阿里
			QueryDeviceDetailRequest queryDeviceDetailRequest = new QueryDeviceDetailRequest();

			queryDeviceDetailRequest.setProductKey(bean.getAliProductKey());
			queryDeviceDetailRequest.setDeviceName(bean.getAliDeviceName());
			queryDeviceDetailRequest.setIotId(bean.getAliIotId());
			QueryDeviceDetailResponse registerDeviceResponse = client.getAcsResponse(queryDeviceDetailRequest);
			if (registerDeviceResponse != null && registerDeviceResponse.getSuccess() != false) {
				LogUtil.print("查询设备详情成功！ " + com.alibaba.fastjson.JSONObject.toJSONString(registerDeviceResponse));
			} else {
				LogUtil.error("查询设备详情失败！requestId:" + registerDeviceResponse.getRequestId() + "原因："
						+ registerDeviceResponse.getErrorMessage());
			}
			String status = registerDeviceResponse.getData().getStatus();
			if ("online".equals(status.toLowerCase())) {
				bean.setOnLineType(0);// 在线
			} else {
				bean.setOnLineType(1);// 离线
			}

			deviceInfoList.add(bean);
		}

		model.addAttribute("terminalDeviceTypeId", terminalDeviceTypeId);
		// model.addAttribute("terminalDeviceTypeTree", terminalDeviceTypeTree);
		model.addAttribute("list", deviceInfoList);
		model.addAttribute("pageNum", p.getPageNum());
		model.addAttribute("pages", p.getPages());
		model.addAllAttributes(paramMap);
		return "ebookpackage.list";
	}
}
