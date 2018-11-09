package com.whty.ebp.api.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whty.common.exception.BusinessException;
import com.whty.common.util.CommonFunction;
import com.whty.common.util.DESUtil;
import com.whty.common.util.SysConfig;
import com.whty.ebp.api.respvo.ClientFile;
import com.whty.ebp.api.respvo.NewSoft;
import com.whty.ebp.api.respvo.NewVersionFilesRet;
import com.whty.ebp.api.utils.PrintResult;
import com.whty.ebp.base.controller.BaseController;
import com.whty.ebp.manage.service.SoftService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/api/clientversion")
public class ClientVersionController extends BaseController {

	@Autowired
	private SoftService softService;

	
	private static List<String> mac_key_list;

	static {
		// 5组密钥
		mac_key_list = new ArrayList<String>();
		mac_key_list.add("CDC5B6BBC3AC3DB3105C5D3B69DD5B1E");
		mac_key_list.add("86E662D12B929599FB8EE856FD480B57");
		mac_key_list.add("73685640797E8D0E323632B17A960552");
		mac_key_list.add("C49D147BF27C691D146227AF09B1F224");
		mac_key_list.add("B5A595AAF1CA384735A722931D7F1124");
	}

	@RequestMapping(value = "/check", method = RequestMethod.POST)
	@ResponseBody
	public void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> resultAppMap = new HashMap<String, Object>();
		String body = CommonFunction.getRequestBody(request.getInputStream());

		Map para = this.getQueryParam(body);

		String randStr = (String) para.get("rand");
		String versionStr = (String) para.get("version");

//		byte[] rand = DESUtil.HexString2Bytes(randStr);
		byte[] version = DESUtil.HexString2Bytes(versionStr);

		
		int macIndex = Integer.valueOf(randStr.substring(randStr.length()-2, randStr.length()));

		String mackey = mac_key_list.get(macIndex - 1);

		String flashVersion = SysConfig.getStrValue("flash_version");

		String newVersionStr = DESUtil.MD5(flashVersion);

		byte[] tempVersion = DESUtil.ByteXOR(DESUtil.HexString2Bytes(randStr), DESUtil.HexString2Bytes(newVersionStr));

		byte[] mac = DESUtil.aesEncrypt(tempVersion, DESUtil.HexString2Bytes(mackey));

		resultAppMap.put("result", "000000");
		resultAppMap.put("version", newVersionStr);
		resultAppMap.put("mac", DESUtil.bytesToHexString(mac).toUpperCase());

		PrintResult.printWriter(response, JSONObject.fromObject(resultAppMap).toString());
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
	private Map getQueryParam(String body) throws BusinessException {
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map para = new HashMap();
		// 必填字段检查
		CommonFunction.checkParam(para);
		if (reqJson.get("version") != null && StringUtils.isNotEmpty(reqJson.get("version").toString())) {
			para.put("version", reqJson.get("version"));
		}

		if (reqJson.get("rand") != null && StringUtils.isNotEmpty(reqJson.get("rand").toString())) {
			para.put("rand", reqJson.get("rand"));
		}

		return para;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * 增量升级和静默升级的文件列表
	 * 参数-versionCode
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/newVersionFiles", method = RequestMethod.POST)
	@ResponseBody
	public void newVersionFiles(HttpServletRequest req, HttpServletResponse resp, @RequestBody String body) throws IOException{
		// 检查参数
		//新版本文件
		NewVersionFilesRet newVersionFilesRet = new NewVersionFilesRet();
		try {
			Map map = new HashMap();
			map = checkNewVersionFilesParam(body); 
			
			NewSoft newSoft = new NewSoft();
			newSoft.setId(map.get("id").toString());
			//根据用户版本号,最新版本id查询新版本文件列表
			List<ClientFile> clientFileList = softService.clientFileList(map);
			String  file_path_pre = SysConfig.getStrValue("file_path_pre");
			String file_path_http = SysConfig.getStrValue("file_path_http");
			for(ClientFile clientFile : clientFileList){
				clientFile.setDownloadUrl(file_path_http+clientFile.getAbsoluteUrl().replace(file_path_pre, ""));
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
		return para;
	}
	
	
	
	
	
	
	
	
	
	
}
