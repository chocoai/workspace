/**
 * 
 */
package com.whty.assis.device.controller;

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
import com.whty.assis.basicdata.model.DeviceMonitorLog;
import com.whty.assis.basicdata.service.DeviceInfoService;
import com.whty.assis.basicdata.service.DeviceMonitorLogService;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * @author zhangzheng
 * @date 2018年4月6日
 */
@Controller
@RequestMapping("/manage/monitor")
public class MonitorController extends BaseController {

	@Autowired
	private DeviceMonitorLogService deviceMonitorLogService;

	@Autowired
	private DeviceInfoService deviceInfoService;

	@RequestMapping(value = "getAllMonitor")
	public void getAllMonitor(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String,Object> param = new HashMap<String,Object>();
		
		String deviceId = request.getParameter("deviceId");
		
		param.put("deviceId", deviceId);
		
		List<DeviceMonitorLog> s = deviceMonitorLogService.listByCondition(param);

		resultMap.put("list", s);

		printJson(response, resultMap);
	}

	@RequestMapping(value = "getMonitor")
	public void getMonitor(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		Map<String, Object> paraMap = this.getParameterMap(request);
		PageContext page = PageContext.getContext();
		page.setCurrentPage(Integer.parseInt(paraMap.get("currPage").toString()));
		page.setPageSize(Integer.parseInt(paraMap.get("pageSize").toString()));

		String deviceId = request.getParameter("deviceId");

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("deviceId", deviceId);

		HandlerResult pageList = new HandlerResult();

		// List<DeviceInfo> deviceInfoList = new ArrayList<DeviceInfo>();
		try {
			page.setPagination(true);
			pageList = deviceMonitorLogService.queryMonitor(param, page);
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
	 * 批量锁屏
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "checkRule")
	public void checkRule(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String schoolId = request.getParameter("schoolId");
		
		if(StringUtils.isEmpty(schoolId)){
			printText(response, "请选择学校");
			return ;
		}
			
		String result = deviceInfoService.checkRule(schoolId);// 设备锁屏
		printText(response, result);
	}

	
	/**
	 * 批量锁屏
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "batchLockScreen")
	public void batchLockScreen(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String ids = request.getParameter("ids");
		String result = deviceInfoService.batchLockScreen(ids);// 设备锁屏
		printText(response, result);
	}

	/**
	 * 批量解锁
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "batchUnLockScreen")
	public void batchUnLockScreen(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String ids = request.getParameter("ids");
		String result = deviceInfoService.batchUnLockScreen(ids);// 设备锁屏
		printText(response, result);
	}
	
	/**
	 * 锁屏
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "lockScreen")
	public void lockScreen(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String deviceId = request.getParameter("deviceId");
		String result = deviceInfoService.lockScreen(deviceId);// 设备锁屏
		printText(response, result);
	}
	
	
	/**
	 * 下载配置信息
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "sendDeviceConfig")
	public void sendDeviceConfig(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		String result = deviceInfoService.sendDeviceConfig();//
		printText(response, result);
	}

	/**
	 * 截图
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "updateImage")
	public void updateImage(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String deviceId = request.getParameter("deviceId");
		String result = deviceInfoService.updateImage(deviceId);// 设备锁屏
		printText(response, result);
	}

	/**
	 * 解锁
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "lockUnScreen")
	public void lockUnScreen(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String deviceId = request.getParameter("deviceId");
		String result = deviceInfoService.unLockScreen(deviceId);// 设备锁屏
		printText(response, result);
	}

	/**
	 * 上传应用
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "uploadAppInfo")
	public void uploadAppInfo(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String deviceId = request.getParameter("deviceId");
		String result = deviceInfoService.uploadAppInfo(deviceId);// 设备锁屏
		printText(response, result);
	}

	/**
	 * 关机
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "shutDown")
	public void shutDown(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String deviceId = request.getParameter("deviceId");
		String result = deviceInfoService.shutDown(deviceId);// 设备锁屏
		printText(response, result);
	}

	/**
	 * 重启
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "restart")
	public void restart(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String deviceId = request.getParameter("deviceId");
		String result = deviceInfoService.restart(deviceId);// 设备锁屏
		printText(response, result);
	}

	/**
	 * 重启
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "batchShutdown")
	public void batchShutdown(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String ids = request.getParameter("ids");
		String result = deviceInfoService.batchShutdown(ids);// 设备锁屏
		printText(response, result);
	}

	/**
	 * 重启
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "batchRestart")
	public void batchRestart(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String ids = request.getParameter("ids");
		String result = deviceInfoService.batchRestart(ids);// 设备锁屏
		printText(response, result);
	}

	/**
	 * 批量发送消息
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "batchSendMessage")
	public void batchSendMessage(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		String ids = request.getParameter("ids");
		String excuteTime = request.getParameter("excuteTime");
		String sendText = request.getParameter("sendText");

		String result = deviceInfoService.batchSendMessage(ids, excuteTime, sendText);// 设备锁屏
		printText(response, result);
	}

	
	/**
	 * 批量发送消息
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "sendMessage")
	public void sendMessage(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		String deviceId = request.getParameter("deviceId");
		String excuteTime = request.getParameter("excuteTime");
		String sendText = request.getParameter("sendText");

		
		String result = deviceInfoService.batchSendMessage(deviceId, excuteTime, sendText);// 设备锁屏
		printText(response, result);
	}
	
	/**
	 * 更新配置文件
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "updateConfig")
	public void updateConfig(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String deviceId = request.getParameter("deviceId");
		String result = deviceInfoService.updateConfig(deviceId);// 设备锁屏
		printText(response, result);
	}

}
