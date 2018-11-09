package com.yhcrt.weihu.cms.action.front;

import static com.yhcrt.weihu.cms.Constants.TPLDIR_SPECIAL;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;
import com.yhcrt.weihu.cms.entity.assist.CmsBaoLiao;
import com.yhcrt.weihu.cms.manager.assist.CmsBaoLiaoMng;
import com.yhcrt.weihu.cms.manager.assist.CmsFileMng;
import com.yhcrt.weihu.common.web.ResponseUtils;
import com.yhcrt.weihu.common.web.session.SessionProvider;
import com.yhcrt.weihu.core.entity.CmsSite;
import com.yhcrt.weihu.core.entity.CmsUser;
import com.yhcrt.weihu.core.web.util.CmsUtils;
import com.yhcrt.weihu.core.web.util.FrontUtils;

@Controller
public class BaoLiaoAct {
	private static final Logger log = LoggerFactory
			.getLogger(BaoLiaoAct.class);

	public static final String BL_INDEX = "tpl.blIndex";
	public static final String BL_DETAIL = "tpl.blDetail";
	public static final String BL_FORM = "tpl.blForm";

	/**
	 * 报料台首页
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/baoliao*.jspx", method = RequestMethod.GET)
	public String index(String queryTitle, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		model.addAttribute("queryTitle", queryTitle);
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_SPECIAL, BL_INDEX);
	}
	
	@RequestMapping(value = "/baoliaoform.jspx", method = RequestMethod.GET)
	public String form(String queryTitle,HttpServletRequest request,HttpServletResponse response,ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		model.addAttribute("queryTitle", queryTitle);
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_SPECIAL, BL_FORM);
	}

	@RequestMapping(value = "/baoliao/{id}.jspx", method = RequestMethod.GET)
	public String detail(@PathVariable Integer id, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsBaoLiao baoliao = null;
		if (id != null) {
			baoliao = baoliaoMng.findById(id);
		}
		//浏览数+1
		if(baoliao.getReadCount() == null){
			baoliao.setReadCount(1);
		}else{
			baoliao.setReadCount(baoliao.getReadCount() + 1);
		}
		baoliaoMng.update(baoliao);
		model.addAttribute("baoliao", baoliao);
		FrontUtils.frontData(request, model, site);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_SPECIAL, BL_DETAIL);

	}

	/**
	 * 提交报料。ajax提交。
	 * 
	 * @param pageNo
	 * @param request
	 * @param response
	 * @param model
	 * @throws JSONException
	 * @throws IOException 
	 */
	
	@RequestMapping(value = "/baoliao.jspx", method = RequestMethod.POST)
	public void submit(CmsBaoLiao baoliao,String captcha,String[] picPaths, String[] picDescs,
			HttpServletRequest request,HttpServletResponse response, ModelMap model) throws JSONException, IOException {
	
		CmsSite site = CmsUtils.getSite(request);
		CmsUser member = CmsUtils.getUser(request);
		JSONObject json = new JSONObject();
		try {
			if (!imageCaptchaService.validateResponseForID(session
					.getSessionId(request, response), captcha)) {
				json.put("success", false);
				json.put("status", 1);
				ResponseUtils.renderJson(response, json.toString());
				return;
			}
		} catch (CaptchaServiceException e) {
			json.put("success", false);
			json.put("status", 1);
			ResponseUtils.renderJson(response, json.toString());
			log.warn("", e);
			return;
		}
		baoliao.setSite(site);
		baoliao.setUser(member);
		baoliaoMng.save(baoliao, picPaths, picDescs);
		//处理附件
		fileMng.updateFileByPathsBL(picPaths, null, true, baoliao);
		json.put("success", true);
		json.put("status", 0);
		ResponseUtils.renderJson(response, json.toString());
	}
	@Autowired
	private CmsFileMng fileMng;
	@Autowired
	private CmsBaoLiaoMng baoliaoMng;
	@Autowired
	private SessionProvider session;
	@Autowired
	private ImageCaptchaService imageCaptchaService;
}
