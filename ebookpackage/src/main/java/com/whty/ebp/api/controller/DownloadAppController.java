package com.whty.ebp.api.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whty.common.exception.BusinessException;
import com.whty.common.util.CommonFunction;
import com.whty.common.util.Constants;
import com.whty.ebp.api.utils.PrintResult;
import com.whty.ebp.manage.dao.AppDao;
import com.whty.ebp.manage.dao.DerivativeAppDao;
import com.whty.ebp.manage.dao.SchoolAppDao;
import com.whty.ebp.manage.model.EbpApp;
import com.whty.ebp.manage.model.SchoolApp;
import com.whty.ebp.manage.service.AppService;
import com.whty.ebp.manage.utils.MD5Utils;

/*
 * 断点续传下载
 */
@Controller
@RequestMapping("/api/downloadApp")
public class DownloadAppController {
	private static final Logger logger = LoggerFactory.getLogger(DownloadAppController.class);

	@Autowired
	private AppService AppService;

	@Autowired
	private DerivativeAppDao derivativeAppDao;

	@Autowired
	private AppDao appDao;

	@Autowired
	private SchoolAppDao schoolAppDao;
	
	@RequestMapping(value = "/updateMd5", method = RequestMethod.GET)
	@ResponseBody
	public void updateMd5(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> param = new HashMap<String, Object>();

		List<EbpApp> beans = appDao.listByCondition(param);

		for (EbpApp bean : beans) {
			try {
				File file = new File(bean.getFile_path());

				String fileMd5 = MD5Utils.getFileMD5(file);

				bean.setMd5(fileMd5);

				appDao.updateMd5(bean);
			} catch (Exception e) {

			}

		}

	}

	/**
	 * 续传下载最新apk文件
	 * 
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/app", method = RequestMethod.GET)
	@ResponseBody
	public void app(HttpServletRequest request, HttpServletResponse response) {
		logger.info("--------start app------------");
		Map map = new HashMap();
		try {
			// 检查参数
			map = checkParam(request);
			// 根据id查询应用

			if (map.get("derivativeAppId") != null) {
				String baiduDownUrl = AppService.getbaiduDownUrl(map.get("id").toString());
				response.sendRedirect(baiduDownUrl);
				return;
			}

			if(map.get("id")!=null){
				Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
		        if(pattern.matcher(map.get("id").toString()).matches()){
		        	SchoolApp schoolApp =  schoolAppDao.loadById(Integer.valueOf(map.get("id").toString()));
					response.sendRedirect(schoolApp.getDownUrl());
					return;
		        }
			}
			
//			if(map.get("productType")!=null){
//				if(map.get("productType").toString().equals("0")){
//					SchoolApp schoolApp =  schoolAppDao.loadById(Integer.valueOf(map.get("id").toString()));
//					response.sendRedirect(schoolApp.getDownUrl());
//					return;
//				}
//			}
			
			EbpApp app = AppService.loadProductNewApp(map);
			String filePath = null;
			if (null != app) {
				filePath = app.getFile_path();
				if (null != filePath && !filePath.trim().equals("")) {
					download(app, filePath, response, request);
				} else {
					// 该应用文件路径不存在
					PrintResult.getFailNeedShowResult("该应用文件路径不存在", response);
				}
			} else {
				// 该应用不存在
				PrintResult.getFailNeedShowResult("该应用不存在", response);
			}
		} catch (IOException e) {
			e.printStackTrace();
			PrintResult.getFailNeedShowResult(e.getMessage(), response);
		} catch (BusinessException e) {
			e.printStackTrace();
			PrintResult.getFailNeedShowResult(e.getMessage(), response);
		}
	}

	/**
	 * 下载app
	 * 
	 * @param app
	 * @throws IOException
	 */
	private void download(EbpApp app, String filePath, HttpServletResponse response, HttpServletRequest request)
			throws IOException {
		File file = new File(filePath);
		if (file.exists()) {
			Date startTime = new Date();
			long fileLength = file.length(); // 记录文件大小
			long pastLength = 0; // 记录已下载文件大小
			int rangeSwitch = 0; // 0：从头开始的全文下载；1：从某字节开始的下载（bytes=27000-）；2：从某字节开始到某字节结束的下载（bytes=27000-39000）
			long toLength = 0; // 记录客户端需要下载的字节段的最后一个字节偏移量（比如bytes=27000-39000，则这个值是为39000）
			long contentLength = 0; // 客户端请求的字节总量
			String rangeBytes = ""; // 记录客户端传来的形如“bytes=27000-”或者“bytes=27000-39000”的内容

			response.reset();
			if (request.getHeader("Range") != null) { // 客户端请求的下载的文件块的开始字节
				response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
				logger.info("request.getHeader(\"Range\")=" + request.getHeader("Range"));
				rangeBytes = request.getHeader("Range").replaceAll("bytes=", "");
				if (rangeBytes.indexOf('-') == rangeBytes.length() - 1) {// bytes=969998336-
					rangeSwitch = 1;
					rangeBytes = rangeBytes.substring(0, rangeBytes.indexOf('-'));
					pastLength = Long.parseLong(rangeBytes.trim());
					contentLength = fileLength - pastLength; // 客户端请求的是
																// 969998336
																// 之后的字节
				} else { // bytes=1275856879-1275877358
					rangeSwitch = 2;
					String temp0 = rangeBytes.substring(0, rangeBytes.indexOf('-'));
					String temp2 = rangeBytes.substring(rangeBytes.indexOf('-') + 1, rangeBytes.length());
					pastLength = Long.parseLong(temp0.trim()); // bytes=1275856879-1275877358，从第
																// 1275856879
																// 个字节开始下载
					toLength = Long.parseLong(temp2); // bytes=1275856879-1275877358，到第
														// 1275877358 个字节结束
					contentLength = toLength - pastLength; // 客户端请求的是
															// 1275856879-1275877358
															// 之间的字节
				}
			} else { // 从开始进行下载
				contentLength = fileLength; // 要求全文下载
			}

			String filename = app.getApp_name();
			// 处理中文乱码
			filename = URLEncoder.encode(filename, "UTF-8");
			// 取得文件的后缀名。
			String ext = filePath.substring(filePath.lastIndexOf(".") + 1);
			String realfilename = filename + "_" + app.getVersion_code() + "." + ext;
			// response.setHeader("Content-Disposition", "attachment; filename="
			// + realfilename);
			// response.addHeader("Content-Length", "" + file.length());
			// response.setContentLength(Integer.parseInt(file.length() +""));
			// response.setContentType("application/octet-stream");
			//
			// ServletOutputStream out = response.getOutputStream();
			// FileInputStream fileInputStream = new FileInputStream(filePath);
			// byte[] fileByte = new byte[1024];
			// while (fileInputStream.read(fileByte) > 0){
			//
			// out.write(fileByte, 0, fileByte.length);
			// }
			// out.flush();
			// out.close();
			// out = null;

			String contentType = null;
			if (StringUtils.isNotEmpty(ext)) {
				contentType = Constants.contentTypeMap.get(ext.toLowerCase());
			}
			if (StringUtils.isEmpty(contentType)) {
				contentType = "application/octet-stream";
			}
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Accept-Ranges", "bytes"); // 断点续传
			response.addHeader("Content-Disposition", "attachment; filename=\"" + realfilename + "\"");
			response.setContentType(contentType);
			response.addHeader("Content-Length", String.valueOf(contentLength));

			RandomAccessFile raf = null; // 负责读取数据
			OutputStream os = null; // 写出数据
			OutputStream out = null; // 缓冲
			os = response.getOutputStream();
			out = new BufferedOutputStream(os);
			raf = new RandomAccessFile(file, "r");
			int cachSize = 1024;
			byte b[] = new byte[cachSize]; // 暂存容器
			switch (rangeSwitch) {
			case 0: { // 普通下载，或者从头开始的下载
				int n = 0;
				while ((n = raf.read(b, 0, cachSize)) != -1) {
					out.write(b, 0, n);
				}
				break;
			}
			case 1: { // 针对 bytes=27000- 的请求
				raf.seek(pastLength); // 形如 bytes=969998336- 的客户端请求，跳过 969998336
										// 个字节
				int n = 0;
				while ((n = raf.read(b, 0, cachSize)) != -1) {
					out.write(b, 0, n);
				}
				break;
			}
			case 2: { // 针对 bytes=27000-39000 的请求
				raf.seek(pastLength); // 形如 bytes=1275856879-1275877358
										// 的客户端请求，找到第 1275856879 个字节
				int n = 0;
				long readLength = 0; // 记录已读字节数
				while (readLength <= contentLength - cachSize) {// 大部分字节在这里读取
					n = raf.read(b, 0, cachSize);
					readLength += cachSize;
					out.write(b, 0, n);
				}
				if (readLength <= contentLength) { // 余下的不足 1024
													// 个字节在这里读取
					n = raf.read(b, 0, (int) (contentLength - readLength));
					out.write(b, 0, n);
				}
				break;
			}
			default: {
				break;
			}
			}
			out.flush();
			out.close();
			Date endTime = new Date();
			logger.info("下载时间：------>>>" + (endTime.getTime() - startTime.getTime()));
			logger.info("------------------------------下载结束");
		} else {
			// 文件不存在
			PrintResult.getFailNeedShowResult("文件不存在", response);
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map checkParam(HttpServletRequest request) throws IOException, BusinessException {
		Map para = new HashMap();
		para.put("product_id", request.getParameter("id"));
		// 必填字段检查
		CommonFunction.checkParam(para);
		// para.put("can_update",1);

		para.put("productType", request.getParameter("productType"));
		para.put("id", request.getParameter("appId"));
		para.put("derivativeAppId", request.getParameter("derivativeAppId"));
		
		
		return para;
	}
}
