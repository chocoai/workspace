package com.yhcrt.iHealthCloud.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.yhcrt.iHealthCloud.base.BaseService;
import com.yhcrt.iHealthCloud.service.ShareService;
import com.yhcrt.iHealthCloud.util.Const;

@Service
public class ShareServiceImpl extends BaseService implements ShareService {

	@Override
	public String getShareUrl(JSONObject pdataObj, HttpServletRequest request) {
		
		// String memberId = getMemberId(pdataObj);
		
		String url = request.getRequestURL().toString().replace("services", "");
		JSONObject biz = new JSONObject();
		biz.put("title", "孝康通—健康生活幸福养老");
		biz.put("description", "多一点时间关怀父母，造福老人，关爱老人构建和谐幸福养老");
		biz.put("url", url + Const.TAG_DIRECTION_APP_URL);
		pdataObj.put(Const.TAG_BIZ, biz);
		pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
		pdataObj.put(Const.TAG_RMK, "");
//		requestSucceed(pdataObj, biz, "");
		return toJsonStringWithOutNull(pdataObj);
	}

}
