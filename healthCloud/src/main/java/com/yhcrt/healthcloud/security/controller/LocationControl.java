package com.yhcrt.healthcloud.security.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhcrt.healthcloud.base.controller.BaseController;
import com.yhcrt.healthcloud.security.entity.Location;
import com.yhcrt.healthcloud.security.service.LocationService;
import com.yhcrt.healthcloud.util.HttpUtil;

@Controller
@RequestMapping("/security")
public class LocationControl extends BaseController {
	
	@Autowired
	private LocationService locationService;

	/**
	 * 得到数据库各个设备最新事件定位经纬度
	 * 
	 * @param request
	 * @return map页面
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/getMap")
	public String getMap(HttpServletRequest request, HttpServletResponse response) {
		// 查询出登录用户所属机构及子机构的ID
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("sid", "SonlineNum");
		jsonParam.put("biz", new JSONObject());
		String jsonStr = HttpUtil.httpPostJson(jsonParam, "http://api.ejyhealth.com:9995/yhcrtSocket/setting/api");
		JSONObject json = JSON.parseObject(jsonStr);
		//判断是否有参数返回
		if(json != null && StringUtils.isNotBlank(json.getString("biz"))){	//有参数
			JSONArray listJson = json.getJSONObject("biz").getJSONArray("onlineList");
			List<String> imeiList = JSONArray.toJavaObject(listJson, List.class);
			map.put("imeiList", imeiList);
		}else{	
			return "/security/map"; //无参数
		}
		List<Integer> orgIds = getOrgList(null);
		map.put("list", orgIds);
		List<Location> locations = locationService.getLocation(map);
		request.setAttribute("locations", locations);
		return "/security/map";
	}

	/**
	 * 得到某天某个设备定位
	 * 
	 * @param request
	 * @param imei
	 * @param locationTime
	 * @return 轨迹页面
	 */
	@RequestMapping("/getOrbit")
	public String getOrbit(HttpServletRequest request, HttpServletResponse response, String imei, String locationTime) {
		String[] locationTimes = locationTime.split(" ");
		List<Location> locations = locationService.getOrbit(imei, locationTimes[0]);
		request.setAttribute("locations", locations);
		request.setAttribute("locationTime", locationTimes[0]);
		request.setAttribute("imei", imei);
		return "/security/mapOrbit";
	}
	
	/**
	 * 实时定位
	 * @param request
	 * @param response
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "/getLocate")
	public String getLocate(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attr) {
		JSONObject jsonParam = new JSONObject();
		JSONObject jsonImei = new JSONObject();
		String imei = request.getParameter("imei");
		jsonImei.put("imei", imei);
		jsonParam.put("sid", "S13");
		jsonParam.put("biz", jsonImei);
		String jsonStr = HttpUtil.httpPostJson(jsonParam, "http://api.ejyhealth.com:9995/yhcrtSocket/setting/api");
		JSONObject json = JSON.parseObject(jsonStr);
		attr.addFlashAttribute("sts", json.getString("sts"));
		attr.addFlashAttribute("msg", request.getParameter("imei"));
		return "redirect:getMap";
	}
	

}
