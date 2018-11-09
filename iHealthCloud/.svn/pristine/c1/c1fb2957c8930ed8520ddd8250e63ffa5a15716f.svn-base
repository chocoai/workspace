package com.yhcrt.iHealthCloud.service.impl;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.yhcrt.iHealthCloud.base.BaseService;
import com.yhcrt.iHealthCloud.service.UserDirectionService;
import com.yhcrt.iHealthCloud.util.Const;

@Service
public class UserDirectionServiceImpl extends BaseService implements UserDirectionService {
	

	@Override
	public String getDirectionUrl4App(JSONObject pdataObj, HttpServletRequest request) {
//		String deviceType = getDeviceType(pdataObj);
//		
//		
//		if(judgeAgumentsIsLegal(pdataObj, deviceType)){
//			
//			SysResExample example = new SysResExample();
//			example.createCriteria().andResNameEqualTo("appUserDirection");
//			List<SysRes> list = resMapper.selectByExample(example);
//			if(list.size() > 0){
//				requestSucceed(pdataObj, list.get(0).getResUrl(), "");
//			}else{
//				requestSucceed(pdataObj, Const.TAG_DIRECTION_APP_URL, "default res");
//			}
//			
//		}else{
//			requestSucceed(pdataObj, Const.TAG_DIRECTION_APP_URL, "'device_type' is null");
//		}
		String url = request.getRequestURL().toString().replace("services", "");
		requestSucceed(pdataObj, url + Const.HELP_FOR_APP_URL, "");
		
		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String getDirectionUrl4Watch(JSONObject pdataObj) {
		requestSucceed(pdataObj, Const.TAG_DIRECTION_WATCH_URL, "");
		return pdataObj.toJSONString();
	}

}
