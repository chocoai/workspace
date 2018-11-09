package com.whty.assis.api.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whty.assis.api.respvo.CheckNewSilentSoft;
import com.whty.assis.api.respvo.CheckNewSoft;
import com.whty.assis.api.respvo.ClientFile;
import com.whty.assis.api.respvo.NewSoft;
import com.whty.assis.api.respvo.NewVersionFilesRet;
import com.whty.assis.base.controller.BaseController;
import com.whty.assis.base.exception.BusinessException;
import com.whty.assis.demo.model.Soft;
import com.whty.assis.demo.service.SoftService;
import com.whty.common.util.CommonFunction;
import com.whty.common.util.Constants;
import com.whty.common.util.SysConfig;

import net.sf.json.JSONObject;

/**
 * 接口-客户端版本管理接口
 * 
 * @author zhujg
 * @date 2016-3-2 14:20:00
 */
@Controller
@RequestMapping("/api/clientVersion")
public class ClientVersionController extends BaseController {

	public static final String FAIL_USER_CODE = "000001";// 操作失败
	public static final String FAIL_USER_MSG = "数据问题-指定用户可升级的最新版本超过1个";

	public static final String FAIL_ALL_USER_CODE = "000002";// 操作失败
	public static final String FAIL_ALL_USER_MSG = "数据问题-全部用户可升级的最新版本超过1个";

	public static final String DATA_FAIL_CODE = "001001";// 操作失败
	public static final String DATA_FAIL_MSG = "数据问题-可升级的最新版本超过1个";

	@Autowired
	private SoftService softService;

	/*
	 * 增量升级和静默升级的文件列表 参数-versionCode
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/newVersionFiles", method = RequestMethod.POST)
	@ResponseBody
	public void newVersionFiles(HttpServletRequest req, HttpServletResponse resp, @RequestBody String body)
			throws IOException {
		// 检查参数
		// 新版本文件
		NewVersionFilesRet newVersionFilesRet = new NewVersionFilesRet();
		try {
			Map map = new HashMap();
			map = checkNewVersionFilesParam(body);

			NewSoft newSoft = new NewSoft();
			newSoft.setId(map.get("id").toString());
			// 根据用户版本号,最新版本id查询新版本文件列表
			List<ClientFile> clientFileList = softService.clientFileList(map);

			// 找到差异文件包，只下载差异文件
			// List<Map<String,Object>> filePackage =
			// softService.getFilePackage(map);

			Map<String, Object> softParam = new HashMap<String, Object>();

			softParam.put("id", map.get("id").toString());

			List<Soft> softList = softService.querySoft(softParam);

			Soft soft = softList.get(0);

			Map<String, Object> packageResultMap = null;
			if (clientFileList != null && clientFileList.size() != 0) {

				packageResultMap = softService.savePackageUrl(map, clientFileList);

			}

			// String file_path_pre = SysConfig.getStrValue("file_path_pre");
			// String file_path_http = SysConfig.getStrValue("file_path_http");
			for (ClientFile clientFile : clientFileList) {

				// clientFile.setDownloadUrl(file_path_http +
				// clientFile.getAbsoluteUrl().replace(file_path_pre, ""));

				clientFile.setDownloadUrl(clientFile.getBosUrl());
			}

			// newSoft.setPackageUrl(packageUrl);

			if (packageResultMap != null) {
				newSoft.setPackageSize(packageResultMap.get("fileSize").toString());
				newSoft.setPackageMd5(packageResultMap.get("md5").toString());
				newSoft.setPackageId(packageResultMap.get("packageId").toString());

				// newSoft.setPackageSize(Integer.toString(soft.getFileSize()));
				// newSoft.setPackageMd5(soft.getMd5());

			} else {
				newSoft.setPackageMd5("");
				newSoft.setPackageId("");
				newSoft.setPackageSize("");
			}

			newSoft.setCount(clientFileList.size());
			newSoft.setFileList(clientFileList);
			newVersionFilesRet.setData(newSoft);
		} catch (BusinessException e) {
			newVersionFilesRet.setResult("111111");
			newVersionFilesRet.setResultDesc(e.getMessage());
			e.printStackTrace();
		}
		CommonFunction.writeResp(resp, JSONObject.fromObject(newVersionFilesRet).toString());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map checkNewVersionFilesParam(String body) throws BusinessException {
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map para = new HashMap();
		para.put("id", reqJson.get("id"));
		para.put("versionCode", reqJson.get("versionCode"));

		// 必填字段检查
		CommonFunction.checkParam(para);

		String softType = reqJson.get("softType") == null ? null : reqJson.get("softType") + "";

		if ("2.4.0.570000".equals(reqJson.get("versionCode"))) {
			softType = "4";
		}

		if (softType != null) {
			para.put("softType", softType.trim());
		} else {
			para.put("softType", (softType == null || !softType.trim().equals("1")) ? "0" : "1");
		}

		return para;
	}

}
