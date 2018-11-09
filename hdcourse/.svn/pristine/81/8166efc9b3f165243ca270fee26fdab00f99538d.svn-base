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

import com.whty.assis.base.controller.BaseController;
import com.whty.assis.base.exception.BusinessException;
import com.whty.assis.manage.model.SoftBackDoor;
import com.whty.assis.manage.service.SoftBackDoorService;
import com.whty.common.util.CommonFunction;
import com.whty.common.util.Constants;

import net.sf.json.JSONObject;

/**
 * 下载路径接口
 */
@Controller
@RequestMapping("/api/softFix")
public class DownUrlController extends BaseController {

	@Autowired
	private SoftBackDoorService softBackDoorService;

	/**
	 * 获取下载路径及版本号
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/queryDownUrl")
	public void queryDownUrl(HttpServletRequest request, HttpServletResponse response, @RequestBody String body)
			throws IOException {
		Map respMap = new HashMap();
		if (logger.isDebugEnabled()) {
			logger.debug("获取下载路径及版本号");
		}
		JSONObject dataJson = new JSONObject();
		try {
			Map map = new HashMap();
			map = checkSearchVersion(body);

			String version_code = map.get("versionCode").toString();
			String zip_code_version = map.get("zip_code_version").toString();
			int soft_type = Integer.parseInt(map.get("softType").toString());
			boolean cbt = Boolean.parseBoolean(map.get("cbt").toString());

			// String cbt = map.get("cbt").toString();

			Map<String, Object> param = new HashMap<>();
			// if("false".equals(cbt)){
			// param.put("cbt", "0");
			// }
			//
			// if("true".equals(cbt)){
			// param.put("cbt", "1");
			// }

			param.put("cbt", cbt ? "1" : "0");
			param.put("version_code", version_code);
			param.put("soft_type", soft_type);
			param.put("zip_code_version", zip_code_version);

			List<SoftBackDoor> softLists = softBackDoorService.queryDownUrls(param);

			if (softLists.size() <= 0) {
				respMap.put("result", Constants.FAIL_CODE);
				respMap.put("resultMsg", "无返回结果");
			} else {
				respMap.put("result", "000000");
				respMap.put("resultMsg", "success");
				SoftBackDoor sbd = softLists.get(0);
				dataJson.put("downUrl", sbd.getDownUrl());
				dataJson.put("code", sbd.getZipCodeVersion());
				dataJson.put("md5", sbd.getMd5());
			}

		} catch (IOException e) {
			respMap.put("result", Constants.FAIL_CODE);
			respMap.put("resultMsg", e.getMessage());
			e.printStackTrace();
		} catch (BusinessException e) {
			respMap.put("result", Constants.FAIL_CODE);
			respMap.put("resultMsg", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			respMap.put("result", Constants.FAIL_CODE);
			respMap.put("resultMsg", e.getMessage());
			e.printStackTrace();
		}
		respMap.put("data", dataJson);
		printJson(response, respMap);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map checkSearchVersion(String body) throws IOException, BusinessException {
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map para = new HashMap();
		// 必填字段检查
		CommonFunction.checkParam(para);
		para.put("cbt", reqJson.get("cbt"));
		para.put("versionCode", reqJson.get("versionCode"));
		para.put("softType", reqJson.get("softType"));
		para.put("zip_code_version", reqJson.get("fixVersion"));

		return para;

	}
}
