/**
 * 
 */
package com.whty.assis.api.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whty.assis.api.dao.UserBackGroundDao;
import com.whty.assis.api.model.UserBackGround;
import com.whty.assis.base.controller.BaseController;
import com.whty.assis.base.exception.BusinessException;
import com.whty.common.util.CommonFunction;

import net.sf.json.JSONObject;

/**
 * @author zhangzheng
 * @date 2018年8月2日
 */
@Controller
@RequestMapping("/api/userBackGround")
public class UserBackGroundController extends BaseController {

	@Autowired
	private UserBackGroundDao userBackGroundDao;

	@RequestMapping(value = "getBackGround", method = RequestMethod.POST)
	@ResponseBody
	public void getBackGround(HttpServletRequest request, HttpServletResponse response, @RequestBody String body)
			throws IOException {
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			Map<String, Object> paramMap;
			paramMap = checkGetBackGround(body);
			Map<String, Object> param = new HashMap<String, Object>();

			String userId = paramMap.get("userId").toString();
			String platformCode = paramMap.get("platformCode").toString();

			param.put("userId", userId);
			param.put("platformCode", platformCode);
			List<UserBackGround> userBackGroundList = userBackGroundDao.listByCondition(paramMap);

			if (userBackGroundList == null || userBackGroundList.size() == 0) {

			} else {
				result.put("backGroundId", userBackGroundList.get(0).getBackGroundId());
			}

			result.put("result", "000000");
		} catch (Exception e) {
			result.put("result", "000001");
			e.printStackTrace();
		}
		printJson(response, JSONObject.fromObject(result));

	}

	@RequestMapping(value = "setBackGround", method = RequestMethod.POST)
	@ResponseBody
	public void setBackGround(HttpServletRequest request, HttpServletResponse response, @RequestBody String body)
			throws IOException {
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			Map<String, Object> paramMap;
			paramMap = checkSetBackGround(body);
			Map<String, Object> param = new HashMap<String, Object>();

			String userId = paramMap.get("userId").toString();
			String platformCode = paramMap.get("platformCode").toString();
			String loginPlatformCode = paramMap.get("loginPlatformCode").toString();
			String backGroundId = paramMap.get("backGroundId").toString();

			param.put("userId", userId);
			param.put("platformCode", platformCode);
			List<UserBackGround> userBackGroundList = userBackGroundDao.listByCondition(paramMap);

			if (userBackGroundList == null || userBackGroundList.size() == 0) {
				UserBackGround userBackGround = new UserBackGround();
				userBackGround.setUserId(userId);
				userBackGround.setPlatformCode(platformCode);
				userBackGround.setLoginPlatformCode(loginPlatformCode);
				userBackGround.setBackGroundId(Integer.valueOf(backGroundId));
				userBackGroundDao.save(userBackGround);
			} else {
				for (UserBackGround userBackGround : userBackGroundList) {
					userBackGround.setBackGroundId(Integer.valueOf(paramMap.get("backGroundId").toString()));
					userBackGroundDao.update(userBackGround);
				}
			}
			result.put("result", "000000");
		} catch (Exception e) {
			result.put("result", "000001");
			e.printStackTrace();
		}
		printJson(response, JSONObject.fromObject(result));
	}

	private Map<String, Object> checkSetBackGround(String body) throws BusinessException {
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", reqJson.get("userId"));
		para.put("platformCode", reqJson.get("platformCode"));
		para.put("loginPlatformCode", reqJson.get("loginPlatformCode"));
		para.put("backGroundId", reqJson.get("backGroundId"));
		// 必填字段检查
		CommonFunction.checkParam(para);

		return para;
	}

	private Map<String, Object> checkGetBackGround(String body) throws BusinessException {
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", reqJson.get("userId"));
		para.put("platformCode", reqJson.get("platformCode"));
		para.put("loginPlatformCode", reqJson.get("loginPlatformCode"));

		// 必填字段检查
		CommonFunction.checkParam(para);

		return para;
	}
}
