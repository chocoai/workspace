package com.whty.assis.api.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whty.assis.base.exception.BusinessException;
import com.whty.assis.demo.model.Soft;
import com.whty.assis.demo.model.SoftFile;
import com.whty.assis.demo.service.SoftService;
import com.whty.common.util.CommonFunction;
import com.whty.common.util.Constants;
import com.whty.common.util.SysConfig;

/**
 * 接口---1客户端查询是否存在最新版本，并返回更新文件列表
 * 
 * @author xuedong
 */
@Controller
@RequestMapping("/api/getNewClient")
public class GetNewClientController {

	@Autowired
	private SoftService softService;

	@RequestMapping(value = "/client", method = RequestMethod.POST)
	@ResponseBody
	public void getNewClient(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		String body = CommonFunction.getRequestBody(request.getInputStream());
		String respObj = getResult(body, "0");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter wr = response.getWriter();
		wr.write(respObj.toString());
		wr.flush();
		wr.close();

	}

	@RequestMapping(value = "/checkNewSilent", method = RequestMethod.POST)
	@ResponseBody
	public void checkNewSilent(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		String body = CommonFunction.getRequestBody(request.getInputStream());
		String respObj = getResult(body, "1");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter wr = response.getWriter();
		wr.write(respObj.toString());
		wr.flush();
		wr.close();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String getResult(String body, String updateType) {
		JSONObject respJson = new JSONObject();
		try {
			// 获取参数
			Map para = getParam(body, updateType);

			Map paramMap = new HashMap();
			paramMap.put("softType", para.get("softType"));
			paramMap.put("fileType", "1");// 查询绿色安装包信息
			paramMap.put("isleveup", para.get("update"));// 是否可升级(0:不可以升级，1：全部用户可以升级，2根据参数升级)
			paramMap.put("status", "0");// 有效
			paramMap.put("updateType", para.get("updateType"));

			List<Soft> softList = softService.querySoft(paramMap);

			if (softList != null && softList.size() > 0) {
				Soft newSoft = softList.get(0);

				JSONObject dataJson = new JSONObject();
				JSONObject softJson = new JSONObject();
				softJson.put("softId", newSoft.getId());
				softJson.put("softName", newSoft.getSoftName());
				softJson.put("softType", newSoft.getSoftType());
				softJson.put("versionCode", newSoft.getVersionCode());
				softJson.put("updateContent", newSoft.getUpdateContent());
				softJson.put("createTime", CommonFunction.getTimeSampleString(newSoft.getCreateTime()));
				softJson.put("status", newSoft.getStatus());
				softJson.put("fileUrl", newSoft.getFileUrl());
				softJson.put("fileName", newSoft.getFileName());
				softJson.put("fileRename", newSoft.getFileRename());
				softJson.put("fileType", newSoft.getFileType());
				softJson.put("updateTime", CommonFunction.getTimeSampleString(newSoft.getUpdateTime()));

				/*
				 * 查询当前版本信息: 1.当前版本信息不存在时，返回最新版本所有文件列表；
				 * 2.当前版本信息存在，当前版本号不等于最新版本号时，查询当前版本升级到最新版本的升级文件列表；
				 * 3.当前版本信息存在，当前版本号等于最新版本号时，即当前版本已经为最新版本，无需返回文件列表
				 */
				Map currMap = new HashMap();
				currMap.put("softType", "0");
				currMap.put("versionCode", para.get("versionCode").toString());
				currMap.put("fileType", "1");// 文件类型(0：安装包1：绿色包)
				currMap.put("status", "0");// 有效
				List<Soft> list = softService.querySoft(currMap);
				Soft currSoft = null;
				if (list != null && list.size() > 0) {
					currSoft = list.get(0);
				}
				List<SoftFile> softFileList = new ArrayList<SoftFile>();
				Map map = new HashMap();
				if (currSoft != null) {
					if (!currSoft.getVersionCode().equals(newSoft.getVersionCode())) {
						map.put("oldSoftId", currSoft.getId());
						map.put("newSoftId", newSoft.getId());
						softFileList = softService.querySoftUpgradeFile(map);
					}
				} else {
					map.put("softId", newSoft.getId());
					softFileList = softService.querySoftFile(map);
				}

				JSONArray softFileListJson = new JSONArray();
				String file_path_pre = SysConfig.getStrValue("file_path_pre");
				String file_path_http = SysConfig.getStrValue("file_path_http");
				for (SoftFile softFile : softFileList) {
					JSONObject softFileJson = new JSONObject();
					softFileJson.put("pkId", softFile.getId());
					softFileJson.put("softId", softFile.getSoftId());
					softFileJson.put("downloadUrl",
							file_path_http + softFile.getAbsoluteUrl().replace(file_path_pre, ""));
					softFileJson.put("fileName", softFile.getFileName());
					softFileJson.put("filePath", softFile.getFilePath());
					softFileJson.put("fileSize", softFile.getFileSize());
					softFileJson.put("fileMd5", softFile.getFileMd5());
					softFileJson.put("updateType", softFile.getUpdateType());
					softFileJson.put("createTime", CommonFunction.getTimeSampleString(softFile.getCreateTime()));

					softFileListJson.add(softFileJson);
				}

				softJson.put("fileList", softFileListJson);

				dataJson.put("soft", softJson);
				respJson.put("data", dataJson);
			}

			respJson.put("result", Constants.SUCCESS_CODE);
			respJson.put("resultMsg", "查询成功");
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map getParam(String body, String updateType) throws BusinessException {
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
		para.put("updateType", updateType);
		para.put("softType", (reqJson.get("softType") == null || reqJson.get("softType").toString().trim().equals(""))
				? "0" : reqJson.get("softType"));
		para.put("update", (reqJson.get("update") == null || reqJson.get("update").toString().trim().equals("")) ? "1"
				: reqJson.get("update"));
		return para;
	}

}
