package com.whty.mxbj.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.whty.mxbj.api.service.UpgradeService;
import com.whty.mxbj.base.controller.BaseController;

//@RestController
@RequestMapping(value = "/api/appDownload")
@Controller
public class ApiAppDownloadController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(AppController.class);

	@Autowired
	private UpgradeService upgradeService;

	@Autowired
	private Environment env;

	@RequestMapping(value ="androidDownload",method=RequestMethod.GET)
	public void androidDownload(HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception {
		resp.setContentType("application/json; charset=utf-8");
		String returnAddress = upgradeService.getUpgrade().getDownLoadUrl();
		
		printText(resp, returnAddress);
		
	}
	
	
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void download2(HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("判断请求是ios,还是android");
		}
		resp.setContentType("application/json; charset=utf-8");
		String terminalType = req.getParameter("terminalType");
		// 默认android地址
		String returnAddress = upgradeService.getUpgrade().getDownLoadUrl();
	
		if ("2".equals(terminalType)) {
			returnAddress = env.getProperty("ios_download_url");
		}else if("3".equals(terminalType)){
			returnAddress = upgradeService.getpadUpgrade();
		}else if("4".equals(terminalType)){
			returnAddress = env.getProperty("ipad_download_url");
		}
			
//		printJson(resp, returnAddress);
		printText(resp, returnAddress);
	}
}
