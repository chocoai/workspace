package com.whty.ebp.api.controller;

import java.io.IOException;
import java.sql.Clob;
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

import com.whty.common.exception.BusinessException;
import com.whty.common.util.CommonFunction;
import com.whty.common.util.Constants;
import com.whty.common.util.SysConfig;
import com.whty.ebp.base.controller.BaseController;
import com.whty.ebp.manage.service.SoftComponentService;
import com.whty.ebp.manage.service.SoftService;

import net.sf.json.JSONObject;

/**
 * 接口-客户端版本管理接口
 * 
 * @author zhujg
 * @date 2016-3-2 14:20:00
 */
@Controller
@RequestMapping("/api/clientVersion_1")
public class ClientVersionController_1 extends BaseController {

	public static final String FAIL_USER_CODE = "000001";// 操作失败
	public static final String FAIL_USER_MSG = "数据问题-指定用户可升级的最新版本超过1个";

	public static final String FAIL_ALL_USER_CODE = "000002";// 操作失败
	public static final String FAIL_ALL_USER_MSG = "数据问题-全部用户可升级的最新版本超过1个";

	public static final String DATA_FAIL_CODE = "001001";// 操作失败
	public static final String DATA_FAIL_MSG = "数据问题-可升级的最新版本超过1个";

	@Autowired
	private SoftService softService;
	@Autowired
	private SoftComponentService softComponentService;

	/*
	 * 正常增量升级，检测新版本
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/checkNew", method = RequestMethod.POST)
	@ResponseBody
	public void checkNew(HttpServletRequest req, HttpServletResponse resp, @RequestBody String body)
			throws IOException {
		// 默认没有新版本
		JSONObject resultJson = new JSONObject();
		resultJson.put("result", Constants.SUCCESS_CODE);
		resultJson.put("resultDesc", "success");
		resultJson.put("isNew", "0");
		JSONObject dataJson = new JSONObject();
		try {
			// 检查参数
			Map param = new HashMap();
			param = checkParam(body);

			// 根据用户id，所属平台编码，用户版本号查询新版本列表
			List<Map> softList = softService.newSoftList_1(param);

			Map newSoft = null;// 非指定用户的最新版本
			Map newSoftToUser = null;// 指定用户的最新版本
			// 是否有指定用户升级的版本-是否指定用户更新(0:否，1：是)
			for (Map map : softList) {
				if ("1".equals(map.get("USER_UPDATE").toString())) {
					if (newSoftToUser == null) {
						newSoftToUser = map;
					} else {
						// 数据问题-指定用户可升级的最新版本超过1个
						resultJson.put("result", FAIL_USER_CODE);
						resultJson.put("resultDesc", FAIL_USER_MSG);
					}
				} else if ("1".equals(map.get("ALL_USER_UPGRADE").toString())) {
					if (newSoft == null) {
						newSoft = map;
					} else {
						// 数据问题-用户可升级的最新版本超过1个
						resultJson.put("result", FAIL_ALL_USER_CODE);
						resultJson.put("resultDesc", FAIL_ALL_USER_MSG);
					}
				}
			}

			Map softMap = null;

			if (null != newSoftToUser) {
				// 有指定用户升级
				softMap = newSoftToUser;
			} else if (null != newSoft) {
				// 全部用户升级
				softMap = newSoft;
			}

			if (softMap != null) {
				resultJson.put("isNew", "1");

				dataJson.put("id", softMap.get("ID"));
				dataJson.put("softName", softMap.get("SOFT_NAME"));
				dataJson.put("versionCode", softMap.get("VERSION_CODE"));
				if (softMap.get("UPDATE_CONTENT") != null) {
					dataJson.put("updateContent", CommonFunction.clobToString((Clob) softMap.get("UPDATE_CONTENT")));
				} else {
					dataJson.put("updateContent", null);
				}
				dataJson.put("forceUpdate", softMap.get("FORCE_UPDATE"));
				dataJson.put("userUpdate", softMap.get("USER_UPDATE"));
				dataJson.put("fileUrl", softMap.get("FILE_URL"));
				dataJson.put("allUserUpgrade", softMap.get("ALL_USER_UPGRADE"));

				if (softMap.get("UPGRADE_PACKAGE") != null) {
					String file_path_pre = SysConfig.getStrValue("file_path_pre");
					String file_path_http = SysConfig.getStrValue("file_path_http");
					String upgradePackagePath = softMap.get("UPGRADE_PACKAGE").toString();
					dataJson.put("upgradePackage", file_path_http + upgradePackagePath.replace(file_path_pre, ""));
				} else {
					dataJson.put("upgradePackage", null);
				}
				dataJson.put("fileSize", softMap.get("FILE_SIZE"));

			}
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("result", Constants.FAIL_CODE);
			resultJson.put("resultDesc", e.getMessage());
		}

		resultJson.put("data", dataJson);
		CommonFunction.writeResp(resp, resultJson.toString());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map checkParam(String body) throws IOException, BusinessException {
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map para = new HashMap();
		para.put("userId", reqJson.get("userId"));
		para.put("userPlatformCode", reqJson.get("userPlatformCode"));
		para.put("versionCode", reqJson.get("versionCode"));
		// 必填字段检查
		CommonFunction.checkParam(para);
		return para;
	}

	/*
	 * 静默升级，检测新版本 参数-tag:0全部升级，1根据参数升级
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/checkNewSilent", method = RequestMethod.POST)
	@ResponseBody
	public void checkNewSilent(HttpServletRequest req, HttpServletResponse resp, @RequestBody String body)
			throws IOException {
		// 默认没有新版本
		JSONObject resultJson = new JSONObject();
		resultJson.put("result", Constants.SUCCESS_CODE);
		resultJson.put("resultDesc", "success");
		resultJson.put("isNew", "0");
		JSONObject dataJson = new JSONObject();
		try {
			// 检查参数
			Map param = new HashMap();
			param = checkNewSilentParam(body);

			// 根据用户版本号,参数tag查询新版本列表
			List<Map> softList = softService.newSilentSoftList_1(param);

			// Map newSoft = null;// 非指定用户的最新版本
			// Map newSoftToUser = null;// 指定用户的最新版本

			// for (Map map : softList) {
			// if ("1".equals(map.get("USER_UPDATE").toString())) {
			// if (newSoftToUser == null) {
			// newSoftToUser = map;
			// } else {
			// // 数据问题-指定用户可升级的最新版本超过1个
			// resultJson.put("result", FAIL_USER_CODE);
			// resultJson.put("resultDesc", FAIL_USER_MSG);
			// }
			// } else if ("1".equals(map.get("ALL_USER_UPGRADE").toString())) {
			// if (newSoft == null) {
			// newSoft = map;
			// } else {
			// // 数据问题-用户可升级的最新版本超过1个
			// resultJson.put("result", FAIL_ALL_USER_CODE);
			// resultJson.put("resultDesc", FAIL_ALL_USER_MSG);
			// }
			// }
			// }

			Map softMap = null;

			// if (null != newSoftToUser) {
			// // 有指定用户升级
			// softMap = newSoftToUser;
			// } else if (null != newSoft) {
			// // 全部用户升级
			// softMap = newSoft;
			// }

			if (null != softList && softList.size() > 0) {
				if (softList.size() > 1) {
					// 数据问题-可升级的最新版本超过1个
					resultJson.put("result", DATA_FAIL_CODE);
					resultJson.put("resultDesc", DATA_FAIL_MSG);
				} else {
					// 有静默升级新版本
					softMap = softList.get(0);
				}
			}

			if (softMap != null) {
				resultJson.put("isNew", "1");

				dataJson.put("id", softMap.get("ID"));
				dataJson.put("softName", softMap.get("SOFT_NAME"));
				dataJson.put("versionCode", softMap.get("VERSION_CODE"));
				if (softMap.get("UPDATE_CONTENT") != null) {
					dataJson.put("updateContent", CommonFunction.clobToString((Clob) softMap.get("UPDATE_CONTENT")));
				} else {
					dataJson.put("updateContent", null);
				}
				dataJson.put("fileUrl", softMap.get("FILE_URL"));

				if (softMap.get("UPGRADE_PACKAGE") != null) {
					String file_path_pre = SysConfig.getStrValue("file_path_pre");
					String file_path_http = SysConfig.getStrValue("file_path_http");
					String upgradePackagePath = softMap.get("UPGRADE_PACKAGE").toString();
					dataJson.put("upgradePackage", file_path_http + upgradePackagePath.replace(file_path_pre, ""));
				} else {
					dataJson.put("upgradePackage", null);
				}
				dataJson.put("fileSize", softMap.get("FILE_SIZE"));
			}

		} catch (Exception e) {
			e.printStackTrace();

			resultJson.put("result", Constants.FAIL_CODE);
			resultJson.put("resultDesc", e.getMessage());
		}

		resultJson.put("data", dataJson);
		CommonFunction.writeResp(resp, resultJson.toString());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map checkNewSilentParam(String body) throws BusinessException {
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map para = new HashMap();
		para.put("versionCode", reqJson.get("versionCode"));
		// 必填字段检查
		CommonFunction.checkParam(para);

		String tag = reqJson.get("tag") == null ? null : reqJson.get("tag") + "";
		para.put("tag", (tag == null || !tag.trim().equals("1")) ? "0" : "1");
		return para;
	}
}
