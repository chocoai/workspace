package com.yhcrt.healthcloud.device.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;
import com.yhcrt.healthcloud.base.controller.BaseController;
import com.yhcrt.healthcloud.common.Constants;
import com.yhcrt.healthcloud.device.entity.DSFSetting;
import com.yhcrt.healthcloud.device.entity.Device;
import com.yhcrt.healthcloud.device.entity.DsSosLinkman;
import com.yhcrt.healthcloud.device.entity.HeartRateSetting;
import com.yhcrt.healthcloud.device.entity.RemindSetting;
import com.yhcrt.healthcloud.device.entity.SecureLocationSetting;
import com.yhcrt.healthcloud.device.service.DSFSettingService;
import com.yhcrt.healthcloud.device.service.DeviceService;
import com.yhcrt.healthcloud.device.service.HeartRateSettingService;
import com.yhcrt.healthcloud.device.service.RemindSettingService;
import com.yhcrt.healthcloud.device.service.SecureLocationSettingService;
import com.yhcrt.healthcloud.device.service.SosLinkmanService;
import com.yhcrt.healthcloud.system.service.SysSequenceService;
import com.yhcrt.healthcloud.util.HttpUtil;
import com.yhcrt.healthcloud.util.RegExpUtil;

/**
 * 
 * @author rpf
 *
 */
@Controller
@RequestMapping("/devicesetting")
public class DeviceSettingController extends BaseController {
	private static final Logger logger = Logger.getLogger(DeviceSettingController.class);
	@Autowired
	private SysSequenceService sysSequenceService;
	@Autowired
	private SosLinkmanService sosLinkmanService;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private RemindSettingService remindSettingService;
	@Autowired
	private DSFSettingService dsfSettingService;
	@Autowired
	private HeartRateSettingService heartRateSettingService;
	@Autowired
	private SecureLocationSettingService secureLocationService;

	@Autowired
	private HttpUtil httpUtil;

	/**
	 * 终端设置列表页
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String imei = request.getParameter("imei");
		modelMap.put("imei", imei);
		return "device/setting";
	}

	/**
	 * 终端设置-联系人设置预处理
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/toLinkmanSetting", method = RequestMethod.GET)
	public String toLinkmanSetting(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String imei = request.getParameter("imei");
		List<DsSosLinkman> linkmans = sosLinkmanService.listByArgs(imei);
		for (int i = linkmans.size(); i < 8; i++) {
			DsSosLinkman dsSosLinkman = new DsSosLinkman();
			linkmans.add(dsSosLinkman);
		}
		modelMap.put("imei", imei);
		modelMap.put("linkmans", linkmans);
		return "device/linkman_setting";
	}

	@RequestMapping(value = "/settingAll", method = RequestMethod.POST)
	@ResponseBody
	public String settingAll(@RequestParam Map<String, String> modelMap) {
		JSONObject jsonObj = new JSONObject();
		logger.info("settingAll设置：" + modelMap);
		String sendType = (String) modelMap.get("sendType");
		String organizationId = (String) modelMap.get("organizationId");
		String imei = (String) modelMap.get("imei");
		String checkDevice = (String) modelMap.get("check_device");
		String[] setTypeArr = checkDevice.split(",");
		if ("0".equals(sendType)) { // 0终端
			for (String setType : setTypeArr) { // 1电话本 2提醒设置 3数据采集频率 4安全定位设置
												// 5心率安全范围设置
				switch (setType) {
				case "1": {
					sendContactsByImei(imei);
					break;
				}
				case "2": { // 暂不处理
					break;
				}
				case "3": {
					sendDataRateByImei(imei);
					break;
				}
				case "4": { // 暂不处理
					break;
				}
				case "5": {
					sendHeartRangeByImei(imei);
					break;
				}
				default:
					break;
				}
			}
		} else if ("1".equals(sendType)) { // 1机构
			HashMap<String, Object> args = new HashMap<String, Object>();
			if (StringUtils.isNotBlank(organizationId)) {
				args.put("orgId", organizationId);
			}
			List<Device> deivceList = deviceService.listDevicesByArgs(args);
			setfromOrg(deivceList, setTypeArr);
		}
		jsonObj.put("result", true);
		jsonObj.put("msg", "设置完毕！");
		return jsonObj.toString();
	}

	private void setfromOrg(List<Device> deivceList, String[] setTypeArr) {
		for (Device device : deivceList) {
			String currentImei = device.getImei();
			if (StringUtils.isBlank(currentImei)) {
				continue;
			}
			for (String setType : setTypeArr) { // 1电话本 2提醒设置 3数据采集频率 4安全定位设置
												// 5心率安全范围设置
				switch (setType) {
				case "1": {
					sendContactsByImei(currentImei);
					break;
				}
				case "2": { // 暂不处理
					break;
				}
				case "3": {
					sendDataRateByImei(currentImei);
					break;
				}
				case "4": { // 暂不处理
					break;
				}
				case "5": {
					sendHeartRangeByImei(currentImei);
					break;
				}
				default:
					break;
				}
			}
		}
	}

	private void sendContactsByImei(String imei) {
		try {
			List<DsSosLinkman> linkmans = sosLinkmanService.listByArgs(imei);
			JSONObject pdata = new JSONObject();
			JSONObject contact = new JSONObject();
			pdata.put("sid", Constants.FUN_TEL);
			contact.put("imei", imei);
			contact.put("contacts", linkmans);
			pdata.put("biz", contact);
			logger.info("联系人设置：" + pdata);
			httpUtil.httpPostWithJson(pdata, HttpUtil.SOCKET_URL);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("联系人设置出错，IMEI：" + imei);
		}
	}

	private void sendDataRateByImei(String imei) {
		try {
			HashMap<String, Object> args = new HashMap<String, Object>();
			args.put("imei", imei);
			List<DSFSetting> dsfsetting = dsfSettingService.getListByParams(args);
			JSONObject pdata = new JSONObject();
			JSONObject setting = new JSONObject();
			pdata.put("sid", Constants.FUN_DATA_RATE);
			setting.put("imei", imei);
			setting.put("dsfsetting", dsfsetting);
			pdata.put("biz", setting);
			httpUtil.httpPostWithJson(pdata, HttpUtil.SOCKET_URL);
		} catch (Exception e) {
			logger.error("频率设置出错，IMEI：" + imei);
		}
	}

	private void sendHeartRangeByImei(String imei) {
		try {
			HashMap<String, Object> args = new HashMap<String, Object>();
			args.put("imei", imei);
			List<HeartRateSetting> heartRateSetting = heartRateSettingService.selectByParams(args);
			JSONObject pdata = new JSONObject();
			JSONObject setting = new JSONObject();
			pdata.put("sid", Constants.FUN_HEART_RATE_LIMIT);
			setting.put("imei", imei);
			setting.put("heartRateSetting", heartRateSetting);
			pdata.put("biz", setting);
			httpUtil.httpPostWithJson(pdata, HttpUtil.SOCKET_URL);
		} catch (Exception e) {
			logger.error("心率范围设置出错，IMEI：" + imei);
		}
	}

	/**
	 * 终端设置-联系人设置
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param attr
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/linkmanSetting", method = RequestMethod.POST)
	public void linkmanSetting(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			RedirectAttributes attr) throws IOException {
		String imei = request.getParameter("imei");
		String[] cid = request.getParameterValues("cid");
		String[] lankmans = request.getParameterValues("lankman");
		String[] phoneNums = request.getParameterValues("phoneNum");
		JSONObject jsonObj = new JSONObject();
		try {
			for (int i = 0; i < phoneNums.length; i++) {
				if (StringUtils.isNotBlank(phoneNums[i]) && !RegExpUtil.isMobile(phoneNums[i])) {
					throw new Exception("手机号码不合法，请输入正确的手机号码");
				}
			}
			for (int i = 0; i < phoneNums.length; i++) {
				if (StringUtils.isBlank(cid[i]) && StringUtils.isNotBlank(lankmans[i])
						&& StringUtils.isNotBlank(phoneNums[i])) {
					// cid为空,联系人,联系人电话不为空时新增记录
					DsSosLinkman linkman = new DsSosLinkman();
					linkman.setCid(sysSequenceService.getSequenceValue(Constants.SequenceName.SOS_LINKMAN));
					linkman.setImei(imei);
					linkman.setLankman(lankmans[i]);
					linkman.setPhoneNum(phoneNums[i]);
					sosLinkmanService.insert(linkman);
				} else if (StringUtils.isNotBlank(cid[i]) && StringUtils.isNotBlank(lankmans[i])
						&& StringUtils.isNotBlank(phoneNums[i])) {
					// cid,联系人,联系人电话三者都不为空时更新记录
					DsSosLinkman dsSosLinkman = sosLinkmanService.getLinkmanByCid(Integer.parseInt(cid[i]));
					dsSosLinkman.setLankman(lankmans[i]);
					dsSosLinkman.setPhoneNum(phoneNums[i]);
					sosLinkmanService.update(dsSosLinkman);
				} else if (StringUtils.isNotBlank(cid[i]) && StringUtils.isBlank(phoneNums[i])) {
					// cid不为空，电话号码为空时删除此无效数据
					sosLinkmanService.delete(Integer.parseInt(cid[i]));
				}
			}
			jsonObj.put("result", true);
			jsonObj.put("msg", "联系人设置成功");
			attr.addAttribute("imei", imei);
		} catch (Exception e) {
			e.printStackTrace();
			jsonObj.put("result", false);
			jsonObj.put("msg", e.getMessage().toString());
		} finally {
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonObj.toString());
		}

		// return "redirect:../devicesetting";
	}

	/**
	 * 终端设置-提醒设置预处理
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/toRemindSetting", method = RequestMethod.GET)
	public String toRemindSetting(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String imei = request.getParameter("imei");
		HashMap<String, Object> params = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(imei)) {
			params.put("imei", imei);
		}
		params.put("remindType", Constants.RemindSetting.REMIND_TYEP_STEP);
		List<RemindSetting> remindSettings = remindSettingService.selectByParams(params);
		if (remindSettings.size() > 0) {
			modelMap.put("remindSetting", remindSettings.get(0));
		}
		modelMap.put("imei", imei);
		return "device/remind_setting";
	}

	/**
	 * 终端设置-提醒设置
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param remindSetting
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "/remindSetting", method = RequestMethod.POST)
	public String remindSetting(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			RemindSetting remindSetting, RedirectAttributes attr) {
		String imei = request.getParameter("imei");
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("imei", imei);
		params.put("remindType", remindSetting.getRemindType());
		List<RemindSetting> remindSettings = remindSettingService.selectByParams(params);
		if (remindSettings.size() > 0) {
			// 更新设置记录
			RemindSetting bean = remindSettings.get(0);
			BeanUtils.copyProperties(remindSetting, bean, "cid");
			remindSettingService.update(bean);
		} else {
			// 不存在则新增记录
			remindSetting.setCid(sysSequenceService.getSequenceValue(Constants.SequenceName.REMIND_SET));
			remindSettingService.insert(remindSetting);
		}
		attr.addAttribute("imei", remindSetting.getImei());
		return "redirect:../devicesetting";
	}

	/**
	 * 根据终端设备IMEI、提醒类型获取该类型的提醒设置
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @throws IOException
	 */
	@RequestMapping(value = "/getRemindSetting", method = RequestMethod.POST)
	public void getRemindSetting(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
			throws IOException {
		String imei = request.getParameter("imei");
		String remindType = request.getParameter("remindType");
		HashMap<String, Object> params = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(imei)) {
			params.put("imei", imei);
		}
		if (StringUtils.isNotBlank(remindType)) {
			params.put("remindType", Integer.parseInt(remindType));
		}
		List<RemindSetting> remindSettings = remindSettingService.selectByParams(params);
		if (remindSettings.size() > 0) {
			response.setContentType("application/json; charset=UTF-8");
			String result = JSONObject.toJSONString(remindSettings.get(0));
			response.getWriter().write(result);
		}
	}

	/**
	 * 数据采集频率预处理
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/toDsfSetting", method = RequestMethod.GET)
	public String toDsfSetting(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String imei = request.getParameter("imei");
		HashMap<String, Object> params = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(imei)) {
			params.put("imei", imei);
		}
		params.put("dataType", Constants.DSFSetting.DATA_TYPE_LOCATION);
		List<DSFSetting> dsfSettings = dsfSettingService.getListByParams(params);
		if (dsfSettings.size() > 0) {
			modelMap.put("dsfSetting", dsfSettings.get(0));
		}
		modelMap.put("imei", imei);
		return "device/dsf_setting";
	}

	@RequestMapping(value = "/dsfSetting", method = RequestMethod.POST)
	public String dsfSetting(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			DSFSetting dsfSetting, RedirectAttributes attr) {
		String imei = request.getParameter("imei");
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("imei", imei);
		params.put("dataType", dsfSetting.getDataType());
		List<DSFSetting> dsfSettings = dsfSettingService.getListByParams(params);
		if (dsfSettings.size() > 0) {
			// 更新设置记录
			DSFSetting bean = dsfSettings.get(0);
			BeanUtils.copyProperties(dsfSetting, bean, "cid");
			dsfSettingService.update(bean);
		} else {
			// 不存在则新增记录
			dsfSetting.setCid(sysSequenceService.getSequenceValue(Constants.SequenceName.DSF_SETING));
			dsfSettingService.insert(dsfSetting);
		}
		attr.addAttribute("imei", dsfSetting.getImei());
		return "redirect:../devicesetting";
	}

	@RequestMapping(value = "/getDsfSetting", method = RequestMethod.POST)
	public void getDsfSetting(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
			throws IOException {
		String imei = request.getParameter("imei");
		String dataType = request.getParameter("dataType");
		HashMap<String, Object> params = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(imei)) {
			params.put("imei", imei);
		}
		if (StringUtils.isNotBlank(dataType)) {
			params.put("dataType", Integer.parseInt(dataType));
		}
		List<DSFSetting> dsfSettings = dsfSettingService.getListByParams(params);
		if (dsfSettings.size() == 1) {
			response.setContentType("application/json; charset=UTF-8");
			String result = JSONObject.toJSONString(dsfSettings.get(0));
			response.getWriter().write(result);
		}
	}

	@RequestMapping(value = "/toHeartrateSetting", method = RequestMethod.GET)
	public String toHeartrateSetting(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String imei = request.getParameter("imei");
		HashMap<String, Object> params = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(imei)) {
			params.put("imei", imei);
		}
		List<HeartRateSetting> heartRateSettings = heartRateSettingService.selectByParams(params);
		if (heartRateSettings.size() > 0) {
			modelMap.put("heartRateSetting", heartRateSettings.get(0));
		}
		modelMap.put("imei", imei);
		return "device/heartrate_setting";
	}

	@RequestMapping(value = "/heartrateSetting", method = RequestMethod.POST)
	public String heartrateSetting(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			HeartRateSetting heartRateSetting, RedirectAttributes attr) {
		String imei = request.getParameter("imei");
		HashMap<String, Object> params = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(imei)) {
			params.put("imei", imei);
		}
		List<HeartRateSetting> heartRateSettings = heartRateSettingService.selectByParams(params);
		if (heartRateSettings.size() > 0) {
			// 更新设置记录
			HeartRateSetting bean = heartRateSettings.get(0);
			BeanUtils.copyProperties(heartRateSetting, bean, "cid");
			heartRateSettingService.update(bean);
		} else {
			// 不存在则新增记录
			heartRateSetting.setCid(sysSequenceService.getSequenceValue(Constants.SequenceName.HEART_SECURITY_SETTING));
			heartRateSettingService.insert(heartRateSetting);
		}

		attr.addAttribute("imei", heartRateSetting.getImei());
		return "redirect:../devicesetting";
	}

	@RequestMapping(value = "/toLocationSetting", method = RequestMethod.GET)
	public String toLocationSetting(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String imei = request.getParameter("imei");
		HashMap<String, Object> params = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(imei)) {
			params.put("imei", imei);
		}
		List<SecureLocationSetting> locationSettings = secureLocationService.selectByParams(params);
		if (locationSettings.size() > 0) {
			modelMap.put("locationSetting", locationSettings.get(0));
		}
		modelMap.put("imei", imei);
		return "device/location_setting";
	}

	@RequestMapping(value = "/locationSetting", method = RequestMethod.POST)
	public String locationSetting(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			SecureLocationSetting locationSetting, RedirectAttributes attr) {
		String imei = request.getParameter("imei");
		HashMap<String, Object> params = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(imei)) {
			params.put("imei", imei);
		}
		List<SecureLocationSetting> locationSettings = secureLocationService.selectByParams(params);
		if (locationSettings.size() > 0) {
			// 更新设置记录
			SecureLocationSetting bean = locationSettings.get(0);
			BeanUtils.copyProperties(locationSetting, bean, "cid");
			secureLocationService.update(bean);
		} else {
			// 不存在则新增记录
			locationSetting.setCid(sysSequenceService.getSequenceValue(Constants.SequenceName.SECURE_LOCATION));
			secureLocationService.insert(locationSetting);
		}
		attr.addAttribute("imei", locationSetting.getImei());
		return "redirect:../devicesetting";
	}

}
