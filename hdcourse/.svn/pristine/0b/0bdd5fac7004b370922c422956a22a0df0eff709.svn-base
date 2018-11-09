package com.whty.assis.api.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.whty.assis.api.service.ClientUserService;
import com.whty.assis.api.utils.HttpUtils;
import com.whty.assis.base.controller.BaseController;
import com.whty.assis.base.exception.BusinessException;
import com.whty.common.thread.widgetlog.ThreadWidgetLog;
import com.whty.common.util.CommonFunction;
import com.whty.common.util.Constants;
import com.whty.common.util.GUIDGenerator;
import com.whty.common.util.SysConfig;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/api/synUser")
public class SynUserController extends BaseController {

	@Autowired
	private ClientUserService clientUserService;
	// @Autowired
	// private LoginLogsService loginlogsservice;

	/**
	 * 添加积分接口
	 * 
	 * @param request
	 * @param response
	 * @param body
	 * @throws Exception
	 */
	@RequestMapping(value = "/addpoint", method = RequestMethod.POST)
	public void addpoint(HttpServletRequest request, HttpServletResponse response, @RequestBody String body)
			throws Exception {
		// logger.info(body);
		System.out.println(body);
		Map map = checkaddpoint(body);

		String userPlatformCode = map.get("userPlatformCode").toString();

		if ("33".equals(userPlatformCode.substring(0, 2))) {
			String url = SysConfig.getStrValue("zjruif");
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("usercode", map.get("userId"));
			param.put("typecode", map.get("actionNum"));
			param.put("comeFrom", "1");
			param.put("relatedId", SysConfig.getStrValue("zj.app.code"));
			// String result = HttpUtils.getInstance().httpPost(url,
			// JSONObject.fromObject(param).toString());
			// System.out.println(result);
		}

		Map result = new HashMap();
		result.put("result", Constants.SUCCESS_CODE);
		printJson(response, result);
	}

	private Map checkaddpoint(String body) throws BusinessException {
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map para = new HashMap();

		para.put("userId", reqJson.get("userId"));
		para.put("userPlatformCode", reqJson.get("userPlatformCode"));
		para.put("actionNum", reqJson.get("actionNum"));
		// 必填字段检查
		CommonFunction.checkParam(para);
		return para;
	}

	/**
	 * 统计控件使用情况
	 * 
	 * @param request
	 * @param response
	 * @param body
	 * @throws Exception
	 * @throws Exception
	 */
	// @SuppressWarnings({ "rawtypes", "unchecked"})
	// @RequestMapping(value="/widgetInfo2", method = RequestMethod.POST)
	// public void widgetInfo2(HttpServletRequest request,HttpServletResponse
	// response, @RequestBody String body) throws Exception {
	// if (logger.isDebugEnabled()) {
	// logger.debug("控件使用情况");
	// }
	// request.setCharacterEncoding("UTF-8");
	//// response.setCharacterEncoding("utf-8");
	// //检查参数
	// final Map map = checkWidgetInfoParam(body);
	//
	// map.put("id", GUIDGenerator.getUUID32());
	//
	// clientUserService.saveWidgetInfoLog(map);
	//
	// Map result = new HashMap();
	// result.put("result", Constants.SUCCESS_CODE);
	// printJson(response,result);
	// }

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/installLog", method = RequestMethod.POST)
	public void installLog(HttpServletRequest request, HttpServletResponse response, @RequestBody String body)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("控件使用情况");
		}
		request.setCharacterEncoding("UTF-8");
		// response.setCharacterEncoding("utf-8");
		// 检查参数
		final Map map = checkInstallLog(body);
		map.put("id", GUIDGenerator.getUUID32());
		clientUserService.saveInstallLog(map);
		Map result = new HashMap();
		result.put("result", Constants.SUCCESS_CODE);
		printJson(response, result);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map checkInstallLog(String body) throws IOException, BusinessException {
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map para = new HashMap();

		// 必填字段检查
		CommonFunction.checkParam(para);

		para.put("area", reqJson.get("area"));
		para.put("school", reqJson.get("school"));
		para.put("className", reqJson.get("className"));
		para.put("cpu", reqJson.get("cpu"));
		para.put("terminalVersion", reqJson.get("terminalVersion"));
		para.put("memery", reqJson.get("memery"));
		para.put("disk", reqJson.get("disk"));

		para.put("mac", reqJson.get("mac"));
		// para.put("time", reqJson.get("time"));
		para.put("remarks", reqJson.get("remarks"));
		para.put("version", reqJson.get("version"));

		para.put("areacode", reqJson.get("areacode"));
		para.put("schoolcode", reqJson.get("schoolcode"));
		para.put("classcode", reqJson.get("classcode"));
		para.put("username", reqJson.get("username"));
		para.put("userid", reqJson.get("userid"));
		para.put("freedisk", reqJson.get("freedisk"));

		return para;
	}

	/**
	 * 统计控件使用情况
	 * 
	 * @param request
	 * @param response
	 * @param body
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/widgetInfo", method = RequestMethod.POST)
	public void widgetInfo(HttpServletRequest request, HttpServletResponse response, @RequestBody String body)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("控件使用情况");
		}
		request.setCharacterEncoding("UTF-8");
		// response.setCharacterEncoding("utf-8");
		logger.info(body);
		// 检查参数
		final Map map = checkWidgetInfoParam(body);

		// map.put("id", GUIDGenerator.getUUID32());

		// clientUserService.saveWidgetInfoLog(map);

		map.put("id", GUIDGenerator.getUUID32());

		ThreadWidgetLog.add(map);

		Map result = new HashMap();
		result.put("result", Constants.SUCCESS_CODE);
		printJson(response, result);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map checkWidgetInfoParam(String body) throws IOException, BusinessException {
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map para = new HashMap();

		List<Map> list = null;
		try {
			list = JSONArray.toList(reqJson.getJSONArray("list"), HashMap.class);
		} catch (Exception e) {
			throw new BusinessException("无控件列表");
		}

		Iterator iter = list.iterator();

		while (iter.hasNext()) {
			Map map = (Map) iter.next();
			if (map.get("widgetId") == null) {
				throw new BusinessException("无widgetId");
			}
		}

		para.put("machineInfo", reqJson.get("machineInfo"));
		para.put("operationSystemInfo", reqJson.get("operationSystemInfo"));
		para.put("list", reqJson.get("list"));

		// 必填字段检查
		CommonFunction.checkParam(para);

		para.put("terminalVersion", reqJson.get("terminalVersion"));
		para.put("operationSystemsType", reqJson.get("operationSystemsType"));
		para.put("cpuInfo", reqJson.get("cpuInfo"));
		para.put("memory", reqJson.get("memory"));

		para.put("pageCount", reqJson.get("pageCount"));
		para.put("terminalLinkInfo", reqJson.get("terminalLinkInfo"));

		para.put("loginSource", reqJson.get("loginSource"));
		para.put("loginTaking", reqJson.get("loginTaking"));
		para.put("useTaking", reqJson.get("useTaking"));

		para.put("courseNum", reqJson.get("courseNum"));
		para.put("subjectId", reqJson.optString("subjectId"));
		para.put("subjectName", reqJson.optString("subjectName"));
		para.put("chapterId", reqJson.optString("chapterId"));
		para.put("chapterName", reqJson.optString("chapterName"));
		para.put("editionId", reqJson.optString("editionId"));
		para.put("editionName", reqJson.optString("editionName"));
		para.put("loginPlatformCode", reqJson.optString("loginPlatformCode"));
		para.put("platformCode", reqJson.optString("platformCode"));
		para.put("orgId", reqJson.optString("orgId"));
		para.put("orgName", reqJson.optString("orgName"));
		para.put("classId", reqJson.optString("classId"));
		para.put("classType", reqJson.optString("classType"));
		para.put("userId", reqJson.optString("userId"));
		para.put("userName", reqJson.optString("userName"));

		para.put("cpuSn", reqJson.optString("cpuSn"));
		para.put("monthBoardSn", reqJson.optString("monthBoardSn"));
		para.put("biosSn", reqJson.optString("biosSn"));
		para.put("diskSn", reqJson.optString("diskSn"));

		return para;
	}

	/**
	 * 统计开机信息
	 * 
	 * @param request
	 * @param response
	 * @param body
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/powerOff", method = RequestMethod.POST)
	public void powerOff(HttpServletRequest request, HttpServletResponse response, @RequestBody String body)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("统计开机信息");
		}
		request.setCharacterEncoding("UTF-8");
		// response.setCharacterEncoding("utf-8");
		// 检查参数
		final Map map = checkPowerOffParam(body);

		map.put("id", GUIDGenerator.getUUID32());

		clientUserService.powerOff(map);

		Map result = new HashMap();
		result.put("result", Constants.SUCCESS_CODE);
		printJson(response, result);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map checkPowerOffParam(String body) throws IOException, BusinessException {
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map para = new HashMap();
		para.put("machine_info", reqJson.get("machine_info"));

		// 必填字段检查
		CommonFunction.checkParam(para);
		return para;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/syn", method = RequestMethod.POST)
	public void syn(HttpServletRequest request, HttpServletResponse response, @RequestBody String body)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("同步用户信息");
		}
		request.setCharacterEncoding("UTF-8");
		// response.setCharacterEncoding("utf-8");
		// 检查参数
		final Map map = checkParam(body);
		// String login_ip = IpUtils.getIpAddr(request);// 客户登录ip
		// map.put("login_ip", login_ip);

		// 启动线程加载数据
		startWork(map);

		Map result = new HashMap();
		result.put("result", Constants.SUCCESS_CODE);
		printJson(response, result);
	}

	@SuppressWarnings("rawtypes")
	private void startWork(final Map map) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				// 保存登录日志
				// saveLoginLog(map);
				// 管理后台验证
				clientUserService.syncClientUser(map);

			}

		}).start();

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map checkParam(String body) throws IOException, BusinessException {
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map para = new HashMap();

		para.put("userId", reqJson.get("userId"));
		para.put("account", reqJson.get("account"));
		para.put("loginPlatformCode", reqJson.get("loginPlatformCode"));
		para.put("userPlatformCode", reqJson.get("userPlatformCode"));
		// 必填字段检查
		CommonFunction.checkParam(para);
		// 非必填参数
		para.put("realName", reqJson.get("realName"));
		para.put("password", reqJson.get("password"));
		para.put("areaId", reqJson.get("areaId"));
		para.put("areaName", reqJson.get("areaName"));
		para.put("orgaId", reqJson.get("orgaId"));
		para.put("orgaName", reqJson.get("orgaName"));
		para.put("email", reqJson.get("email"));
		para.put("mobilNumber", reqJson.get("mobilNumber"));
		para.put("birthday", reqJson.get("birthday"));
		para.put("gender", reqJson.get("gender"));
		para.put("userType", reqJson.get("userType"));
		para.put("idCardNumber", reqJson.get("idCardNumber"));
		para.put("provinceName", reqJson.get("provinceName"));
		para.put("cityName", reqJson.get("cityName"));
		para.put("userSessionId", reqJson.get("userSessionId"));

		return para;
	}

	// @SuppressWarnings("rawtypes")
	// private void saveLoginLog(Map map) {
	// LoginLogs logs=new LoginLogs();
	// logs.setId(GUIDGenerator.getUUID32());
	// logs.setUser_id((String) map.get("userId"));//用户id
	// logs.setLogin_ip((String) map.get("login_ip"));
	// logs.setCreate_time(new Date());
	// logs.setPlatform_code((String) map.get("userPlatformCode"));
	// logs.setLogin_platform_code((String) map.get("loginPlatformCode"));
	// logs.setUsession_id((null ==
	// map.get("usessionid")||map.get("usessionid").toString().trim().equals("null"))
	// ? null : (String) map.get("usessionid"));
	// loginlogsservice.saveLoginLogs(logs);
	// }

}
