package com.whty.assis.api.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whty.assis.base.exception.BusinessException;
import com.whty.assis.demo.model.Soft;
import com.whty.assis.demo.service.SoftService;
import com.whty.common.util.CommonFunction;
import com.whty.common.util.Constants;

import net.sf.json.JSONObject;

/**
 * 接口---1客户端查询是否存在最新版本
 * 
 * @author zhujg
 */
@Controller
@RequestMapping("/api/whetherNewClient")
public class WhetherNewClientController {
	@Autowired
	private SoftService softService;

	@RequestMapping(value = "/newclient", method = RequestMethod.POST)
	@ResponseBody
	public void whetherNewClient(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		String body = CommonFunction.getRequestBody(request.getInputStream());
		String respObj = getResult(body);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter wr = response.getWriter();
		wr.write(respObj.toString());
		wr.flush();
		wr.close();

	}

	@SuppressWarnings("rawtypes")
	private String getResult(String body) {
		JSONObject respJson = new JSONObject();
		try {
			// 获取参数
			Map para = getParam(body);
			// 先根据update检测后台最新版本
			Soft soft = softService.querySoftByMap(para);
			JSONObject dataJson = new JSONObject();
			if (null != soft && !soft.getVersionCode().trim().equals(para.get("versionCode").toString().trim())) {
				// 有最新可升级的版本
				dataJson.put("isNew", "1");// 有最新版本
				dataJson.put("versionCode", soft.getVersionCode());
				dataJson.put("updateContent", soft.getUpdateContent());
				dataJson.put("forceUpdate", soft.getForceUpdate());
				respJson.put("isNew", "1");
			} else {
				dataJson.put("isNew", "0");// 没有最新版本
				respJson.put("isNew", "0");
			}

			respJson.put("result", Constants.SUCCESS_CODE);
			respJson.put("resultMsg", "查询成功");
			respJson.put("data", dataJson);
		} catch (BusinessException e) {
			respJson.put("result", Constants.FAIL_CODE);
			respJson.put("resultMsg", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			respJson.put("result", Constants.FAIL_CODE);
			respJson.put("resultMsg", e.getMessage());
			e.printStackTrace();
		}
		return respJson.toString();
	}

	/**
	 * 获取参数
	 * 
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws com.whty.assis.base.exception.BusinessException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map getParam(String body) throws BusinessException {
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map para = new HashMap();
		para.put("versionCode", reqJson.get("versionCode"));

		// para.put("softType", reqJson.get("softType"));
		// 必填字段检查
		CommonFunction.checkParam(para);

		para.put("updateType",
				(reqJson.get("updateType") == null || reqJson.get("updateType").toString().trim().equals("")) ? "0"
						: reqJson.get("updateType"));

		para.put("softType", (reqJson.get("softType") == null || reqJson.get("softType").toString().trim().equals(""))
				? "0" : reqJson.get("softType"));

		para.put("update", (reqJson.get("update") == null || reqJson.get("update").toString().trim().equals("")) ? "1"
				: reqJson.get("update"));
		return para;
	}

}
