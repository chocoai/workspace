package com.whty.assis.api.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whty.assis.api.service.ClientUserService;
import com.whty.assis.base.controller.BaseController;
import com.whty.assis.base.exception.BusinessException;
import com.whty.assis.demo.model.Ta_user;
import com.whty.assis.demo.model.WidgetLog;
import com.whty.assis.demo.service.AreaService;
import com.whty.assis.demo.service.LoginHistoryService;
import com.whty.common.cache.data.GetCacheBaseData;
import com.whty.common.util.CommonFunction;
import com.whty.common.util.Constants;
import com.whty.common.util.GUIDGenerator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/api/data")
public class DataController extends BaseController {

	@Autowired
	private ClientUserService clientUserService;

	@Autowired
	private AreaService areaService;

	@Autowired
	private LoginHistoryService loginHistoryService;

	// public static Set<String> set = new HashSet<String>();

	@RequestMapping(value = "test")
	public void test() {
		loginHistoryService.countTask();
	}

	@RequestMapping(value = "uploadMac")
	public void uploadMac(HttpServletRequest request, HttpServletResponse response, @RequestBody String body)
			throws IOException {
		Map<String, Object> paramMap;
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			paramMap = checkInstallMac(body);
			paramMap.put("id", GUIDGenerator.getUUID32());
			clientUserService.saveInstallMac(paramMap);
			result.put("result", "000000");
		} catch (BusinessException e) {
			result.put("result", "000001");
			e.printStackTrace();
		}
		response.getWriter().print(result);
	}

	@RequestMapping(value = "getOp")
	public void getOp(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<WidgetLog> oplst = clientUserService.getOp();

		JSONArray Json = new JSONArray();

		for (WidgetLog op : oplst) {
			JSONObject object = new JSONObject();
			object.put("id", op.getId());
			object.put("name", op.getUserId());
			Json.add(object);

		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(Json);
	}

	@RequestMapping(value = "getClassesInfo")
	public void getClassesInfo(HttpServletResponse response, HttpServletRequest request, @RequestBody String body) {

		Map<String, Object> paramMap;
		try {
			paramMap = checkgetClassesInfo(body);

			// Map<String, Object> areaMap = new HashMap<String, Object>();
			// areaMap.put("levelId", 2);
			// areaMap.put("parentId", paramMap.get("areacode").toString());
			// 武汉教育云
			String url = GetCacheBaseData.getPropertyValue("platform_url", "420100") + "classinfo/query";

			Map<String, Object> param = new HashMap<String, Object>();
			param.put("orgaid", paramMap.get("orgaid"));

			int start = 0;
			int end = 40;

			JSONArray schoolJson = new JSONArray();
			String answer = clientUserService.listClassesInfo(param, Integer.toString(start), Integer.toString(end),
					url);
			// String answer = clientUserService.listOgra(param,
			// Integer.toString(start), Integer.toString(end), url);
			String result = JSONObject.fromObject(answer).optString("result");
			if ("000000".equals(result)) {
				int count = JSONObject.fromObject(answer).optInt("count");
				// if (count > end) {
				// for (; end <= count; start = start + 40, end = end + 40) {
				String answerStr = clientUserService.listClassesInfo(param, Integer.toString(start),
						Integer.toString(count), url);

				JSONArray dataJsonArray = JSONObject.fromObject(answerStr).optJSONArray("classinfolist");

				if (dataJsonArray.size() > 0) {
					for (int i = 0; i < dataJsonArray.size(); i++) {
						JSONObject data = dataJsonArray.optJSONObject(i);
						Map<String, Object> schoolMap = new HashMap<String, Object>();
						schoolMap.put("classid", data.optString("classid"));
						schoolMap.put("classname", data.optString("classname"));
						schoolJson.add(schoolMap);
					}
				}
				// }
				// }
				// JSONArray dataJsonArray =
				// JSONObject.fromObject(answer).optJSONArray("classinfolist");
				// if (dataJsonArray.size() > 0) {
				// for (int i = 0; i < dataJsonArray.size(); i++) {
				// JSONObject data = dataJsonArray.optJSONObject(i);
				// Map<String, Object> schoolMap = new HashMap<String,
				// Object>();
				// schoolMap.put("classid", data.optString("classid"));
				// schoolMap.put("classname", data.optString("classname"));
				// schoolJson.add(schoolMap);
				// }
				// }
			}
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print(schoolJson);
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (BusinessException e1) {
			e1.printStackTrace();
		}

	}

	private Map<String, Object> checkInstallMac(String body) throws BusinessException {
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map para = new HashMap();
		para.put("mac", reqJson.get("mac"));
		// 必填字段检查
		CommonFunction.checkParam(para);

		para.put("version", reqJson.optString("version"));
		return para;
	}

	private Map<String, Object> checkgetClassesInfo(String body) throws BusinessException {
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map para = new HashMap();
		para.put("orgaid", reqJson.get("orgaid"));
		// 必填字段检查
		CommonFunction.checkParam(para);
		return para;
	}

	@RequestMapping(value = "getSchool")
	public void getSchool(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {

		Map<String, Object> paramMap;
		try {
			paramMap = checkGetSchool(body);

			// Map<String, Object> areaMap = new HashMap<String, Object>();
			// areaMap.put("levelId", 2);
			// areaMap.put("parentId", paramMap.get("areacode").toString());
			// 武汉教育云
			String url = GetCacheBaseData.getPropertyValue("platform_url", "420100") + "ogra/query";
			// String url =
			// "http://www.wuhaneduyun.cn:10013/aamif/rest/ogra/query";
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("provincecode", paramMap.get("provincecode"));
			param.put("areacode", paramMap.get("areacode"));
			param.put("citycode", paramMap.get("citycode"));
			param.put("type", "1");

			int start = 0;
			int end = 40;

			JSONArray schoolJson = new JSONArray();
			String answer = clientUserService.listOgra(param, Integer.toString(start), Integer.toString(end), url);
			String result = JSONObject.fromObject(answer).optString("result");
			if ("000000".equals(result)) {
				int count = JSONObject.fromObject(answer).optInt("count");
				// if (count > end) {
				// for (; end <= count; start = start + 40, end = end + 40) {
				String answerStr = clientUserService.listOgra(param, Integer.toString(start), Integer.toString(count),
						url);

				JSONArray dataJsonArray = JSONObject.fromObject(answerStr).optJSONArray("organ");

				if (dataJsonArray.size() > 0) {
					for (int i = 0; i < dataJsonArray.size(); i++) {
						JSONObject data = dataJsonArray.optJSONObject(i);
						Map<String, Object> schoolMap = new HashMap<String, Object>();
						schoolMap.put("orgacode", data.optString("orgaid"));
						schoolMap.put("organame", data.optString("organame"));
						schoolJson.add(schoolMap);
					}
				}
			}
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print(schoolJson);
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (BusinessException e1) {
			e1.printStackTrace();
		}

	}

	@RequestMapping(value = "getAreaCode")
	public void getAreaCode(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {

		Map<String, Object> paramMap;
		try {
			paramMap = checkGetcity(body);

			Map<String, Object> areaMap = new HashMap<String, Object>();
			// areaMap.put("levelId", 2);
			areaMap.put("parentId", paramMap.get("areaCode").toString());
			List<Map> provinceList = areaService.getTAreaByCity(areaMap);
			JSONArray list = new JSONArray();
			for (Map area : provinceList) {
				JSONObject s = new JSONObject();
				s.put("areaCode", area.get("areaCode"));
				s.put("areaName", area.get("areaName"));
				list.add(s);
			}
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json; charset=utf-8");
			try {
				response.getWriter().print(list);
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (BusinessException e1) {
			e1.printStackTrace();
		}
	}

	@RequestMapping(value = "/getProvince")
	@ResponseBody
	public void getProvince(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> areaMap = new HashMap<String, Object>();
		areaMap.put("levelId", 1);
		List<Map> provinceList = areaService.queryArea(areaMap);
		JSONArray list = new JSONArray();
		for (Map province : provinceList) {
			JSONObject s = new JSONObject();
			s.put("areaCode", province.get("areaCode"));
			s.put("areaName", province.get("areaName"));

			list.add(s);

		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		try {
			response.getWriter().print(list);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@RequestMapping(value = "/visitCount", method = RequestMethod.POST)
	@ResponseBody
	public void visitCount(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Map map = checkVisitCount(body);

			String count = clientUserService.visitCount(map);

			if (count == null) {
				result.put("useCount", "0");
			} else {
				result.put("useCount", count);
			}

			result.put("result", Constants.SUCCESS_CODE);
			result.put("resultMsg", "success");

		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", Constants.FAIL_CODE);
			result.put("resultMsg", "fail");
		}
		printJson(response, result);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map checkVisitCount(String body) throws IOException, BusinessException {
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map para = new HashMap();

		para.put("startTime", reqJson.get("startTime"));
		para.put("endTime", reqJson.get("endTime"));

		// 必填字段检查
		CommonFunction.checkParam(para);

		para.put("platformCode", reqJson.get("platformCode"));

		return para;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map checkGetSchool(String body) throws IOException, BusinessException {
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map para = new HashMap();

		para.put("provincecode", reqJson.get("provincecode"));
		para.put("areacode", reqJson.get("areacode"));
		para.put("citycode", reqJson.get("citycode"));
		// 必填字段检查
		CommonFunction.checkParam(para);

		return para;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map checkGetcity(String body) throws IOException, BusinessException {
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map para = new HashMap();

		para.put("areaCode", reqJson.get("areaCode"));

		// 必填字段检查
		CommonFunction.checkParam(para);

		return para;
	}

}
