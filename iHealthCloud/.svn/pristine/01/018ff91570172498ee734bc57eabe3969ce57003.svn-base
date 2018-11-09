package com.yhcrt.iHealthCloud.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.yhcrt.iHealthCloud.common.Constants;
import com.yhcrt.iHealthCloud.entity.Location;
import com.yhcrt.iHealthCloud.entity.MemberDevice;
import com.yhcrt.iHealthCloud.mapper.LocationMapper;
import com.yhcrt.iHealthCloud.mapper.MemberDeviceMapper;
import com.yhcrt.iHealthCloud.service.SysSequenceService;
import com.yhcrt.iHealthCloud.util.Const;
import com.yhcrt.iHealthCloud.util.DateUtils;

@Controller
@RequestMapping("/location")
public class LocationController {
	@Autowired
	private MemberDeviceMapper memberDeviceMapper;
	@Autowired
	private LocationMapper locationMapper;
	@Autowired
	private SysSequenceService sysSequenceService;
	/**
	 * 上传会员的实时定位位置数据
	 * @param request
	 * @param response
	 * @param modelMap
	 * @throws IOException 
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public void upload(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException {
		String imei = request.getParameter("imei");
		String longitude = request.getParameter("longitude");
		String latitude = request.getParameter("latitude");
		String addr = request.getParameter("addr");
		JSONObject jsonObj = new JSONObject();
		// 检查IMEI是否是有效的imei,即已被会员绑定使用
		if (isValidIMEI(imei)) {
			Location location = new Location();
			location.setLocationId(sysSequenceService.getSequenceValue(Constants.SequenceName.LOCATION));
			location.setImei(imei);
			location.setLatitude(latitude);
			location.setLongitude(longitude);
			location.setAddr(addr);
			location.setLocationTime(DateUtils.getCurrentTime());
			locationMapper.insert(location);
			jsonObj.put(Const.TAG_RESULT, Const.TAG_SUCCESS);
			jsonObj.put(Const.ERROR_DESC, "");
		}else{
			jsonObj.put(Const.TAG_RESULT, Const.TAG_FAIL);
			jsonObj.put(Const.ERROR_DESC,  Constants.ExceptionMsg.INVALID_IMEI);
		}
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().write(jsonObj.toString());
		
	}
	
	/**
	 * 检查IMEI是否是有效的imei,即已被会员绑定使用
	 * @param imei
	 * @return
	 */
	public boolean isValidIMEI(String imei){
		MemberDevice md = memberDeviceMapper.selectByImei(imei, Constants.STATUS_NORMAL);
		if (null != md) {
			return true;
		}
		return false;
	}

}
