package com.whty.ebp.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whty.common.exception.BusinessException;
import com.whty.common.util.CommonFunction;
import com.whty.common.util.SysConfig;
import com.whty.ebp.api.utils.PrintResult;
import com.whty.ebp.manage.model.EbpApp;
import com.whty.ebp.manage.model.EbpAppFile;
import com.whty.ebp.manage.service.AppService;

/**
 * 接口--查询更新文件列表
 */
@Controller
@RequestMapping("/api/newClient")
public class NewClientController {
	

	@Autowired
	private AppService appService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/queryFile", method = RequestMethod.POST)
	@ResponseBody
	public void queryFile(HttpServletRequest request, HttpServletResponse response, @RequestBody String body){
		Map resultAppMap = new HashMap();
		
		try {
			//获取参数
			Map para = getParam(body);

			Map paramMap = new HashMap();
			paramMap.put("product_id", para.get("product_id").toString());// 已经生成升级文件的版本
			paramMap.put("zip_can_update", "1");//表示可升级
			EbpApp newApp = appService.loadProductNewApp(paramMap);
			
			if(newApp!=null){
				
				resultAppMap.put("id", newApp.getId());
				resultAppMap.put("app_name", newApp.getApp_name());
				resultAppMap.put("version_code", newApp.getVersion_code());
				resultAppMap.put("description", newApp.getDescription());
				
				/*
				 * 查询当前版本信息:
				 *  1.当前版本信息不存在时，返回最新版本所有文件列表；
				 *  2.当前版本信息存在，当前版本号不等于最新版本号时，查询当前版本升级到最新版本的升级文件列表；
				 *  3.当前版本信息存在，当前版本号等于最新版本号时，即当前版本已经为最新版本，无需返回文件列表
				 */
				EbpApp currApp = appService.loadProductNewApp(para);
				List<EbpAppFile> appFileList = new ArrayList<EbpAppFile>();
				Map map = new HashMap();
				if(currApp!=null){
					if(!currApp.getVersion_code().equals(newApp.getVersion_code())){
						map.put("oldAppId", currApp.getId());
						map.put("newAppId", newApp.getId());
						appFileList = appService.queryAppUpgradeFile(map);
					}
				}else{
					map.put("appId", newApp.getId());
					appFileList = appService.queryAppFile(map);
				}
				
				
				List resultAppFileList = new ArrayList();
				String file_path_pre = SysConfig.getStrValue("file_path_pre");
				String file_path_http = SysConfig.getStrValue("file_path_http");
				for(EbpAppFile appFile : appFileList){
					Map resultAppFileMap = new HashMap();
					resultAppFileMap.put("id", appFile.getId());
					resultAppFileMap.put("appId", appFile.getAppId());
					resultAppFileMap.put("downloadUrl", file_path_http+appFile.getAbsoluteUrl().replace(file_path_pre, ""));
					resultAppFileMap.put("fileName", appFile.getFileName());
					resultAppFileMap.put("filePath", appFile.getFilePath());
					resultAppFileMap.put("fileSize", appFile.getFileSize());
					resultAppFileMap.put("fileMd5", appFile.getFileMd5());
					resultAppFileMap.put("updateType", appFile.getUpdateType());
					resultAppFileMap.put("createTime", CommonFunction.getTimeSampleString(appFile.getCreateTime()));
					
					resultAppFileList.add(resultAppFileMap);
				}
				
				resultAppMap.put("fileList", resultAppFileList);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			PrintResult.getFailNeedShowResult(e.getMessage(), response);
		}
		
		PrintResult.getSuccessResult(resultAppMap, response);
		
	}
	
	/**
	 * 获取参数
	 * @param req
	 * @return
	 * @throws BusinessException 
	 * @throws com.whty.assis.base.exception.BusinessException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map getParam(String body) throws BusinessException{
		JSONObject reqJson=null;
		reqJson = JSONObject.fromObject((body==null||body.equals(""))?"{}":body);
		//参数检验
		if(!CommonFunction.isNotNull(reqJson)){
			throw new BusinessException("参数不存在");
		}
		Map para = new HashMap();
		para.put("version_code", reqJson.get("version_code"));
		para.put("product_id", reqJson.get("product_id"));
		//必填字段检查
		CommonFunction.checkParam(para);
		return para;
	}
	

}
