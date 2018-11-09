package com.whty.assis.api.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.whty.assis.api.service.StudentCardInfoService;
import com.whty.assis.base.controller.BaseController;
import com.whty.assis.base.exception.BusinessException;
import com.whty.common.util.CommonFunction;

import net.sf.json.JSONObject;

/*
 * 学生答题卡信息
 */
@Controller
@RequestMapping("/api/configInfo")
public class ConfigInfoController extends BaseController {

	@Autowired
	private StudentCardInfoService studentCardService;

	@RequestMapping(value = "/getConfig")
	public void getConfig(HttpServletRequest request, HttpServletResponse response, @RequestBody String body)
			throws IOException {
		Map<String, Object> result = new HashMap<String, Object>();

		result.put("result", "000000");
		result.put("resultDesc", "success");

		try {
			Map<String, Object> param = checkParamBinding(body);

			String url = studentCardService.getStudentCardConfig(param);

			result.put("studentCardUrl", url);

		} catch (Exception e) {
			e.printStackTrace();
		}
		CommonFunction.writeResp(response, JSONObject.fromObject(result).toString());
	}

	private Map checkParamBinding(String body) throws IOException, BusinessException {
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map para = new HashMap();
		para.put("platformCode", reqJson.get("platformCode"));
		para.put("orgId", reqJson.get("orgId"));
		// 必填字段检查
		CommonFunction.checkParam(para);

		return para;
	}

}
