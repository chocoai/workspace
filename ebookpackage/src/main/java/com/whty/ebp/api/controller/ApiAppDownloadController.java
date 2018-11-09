package com.whty.ebp.api.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whty.common.util.HeaderUtil;
import com.whty.common.util.SysConfig;
import com.whty.ebp.api.utils.PrintResult;
import com.whty.ebp.base.controller.BaseController;
import com.whty.ebp.manage.model.EbpApp;
import com.whty.ebp.manage.service.AppService;

@Controller
@RequestMapping(value = "/api/appDownload")
public class ApiAppDownloadController extends BaseController {
	private static Logger logger = Logger.getLogger(ApiAppDownloadController.class);

	@Autowired
	private AppService appService;

	@RequestMapping(value = "/download3", method = RequestMethod.GET)
	@ResponseBody
	public void download3(HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("判断请求是ios,还是android");
		}

		EbpApp ebpapp = appService.getNewyidongjiangtaiEbpApp();

		// File file = new File(ebpapp.getFile_path());
		// String filePath =
		// "d://home\\wecourse\\ebookpackagefiles\\app\\2017-03-10\\1489112035669.apk";
		InputStream fis = new BufferedInputStream(new FileInputStream(ebpapp.getFile_path()));
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		// 清空response
		resp.reset();
		// 设置response的Header
		resp.addHeader("Content-Disposition", "attachment;filename=" + new String(ebpapp.getOld_file_name()));
		resp.addHeader("Content-Length", "" + ebpapp.getFile_size());
		OutputStream toClient = new BufferedOutputStream(resp.getOutputStream());
		resp.setContentType("application/octet-stream");
		toClient.write(buffer);
		toClient.flush();
		toClient.close();

		// returnAddress =SysConfig.getStrValue("android_download_url");

	}

	@RequestMapping(value = "/download2", method = RequestMethod.GET)
	@ResponseBody
	public void download2(HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("判断请求是ios,还是android");
		}

		String terminalType = req.getParameter("terminalType");
		String returnAddress = SysConfig.getStrValue("android_download_url");// 默认android地址
		if ("1".equals(terminalType)) {

//			EbpApp ebpapp = appService.getNewyidongjiangtaiEbpApp();

			// File file = new File(ebpapp.getFile_path());
			// String filePath =
			// "d://home\\wecourse\\ebookpackagefiles\\app\\2017-03-10\\1489112035669.apk";
//			InputStream fis = new BufferedInputStream(new FileInputStream(ebpapp.getFile_path()));
//			byte[] buffer = new byte[fis.available()];
//			fis.read(buffer);
//			fis.close();
//			// 清空response
//			resp.reset();
//			// 设置response的Header
//			resp.addHeader("Content-Disposition", "attachment;filename=" + new String(ebpapp.getOld_file_name()));
//			resp.addHeader("Content-Length", "" + ebpapp.getFile_size());
//			OutputStream toClient = new BufferedOutputStream(resp.getOutputStream());
//			resp.setContentType("application/octet-stream");
//			toClient.write(buffer);
//			toClient.flush();
//			toClient.close();

			 returnAddress =   SysConfig.getStrValue("file_path_http");
		} else if ("2".equals(terminalType)) {
			returnAddress = SysConfig.getStrValue("ios_download_url");
		}
		PrintResult.printWriter(resp, returnAddress);
	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public String download(HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("判断请求是ios,还是android");
		}
		String userAgent = req.getHeader("user-agent");
		Map<String, String> info = HeaderUtil.getMobilOS(userAgent);
		model.addAttribute("deviceType", info.get("deviceType"));
		model.addAttribute("android_download_url", SysConfig.getStrValue("android_download_url"));
		model.addAttribute("ios_download_url", SysConfig.getStrValue("ios_download_url"));
		return "download";
	}
}
