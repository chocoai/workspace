package com.yhcrt.weihu.bbs.action.front;

import static com.yhcrt.weihu.bbs.Constants.TPLDIR_MEMBER;
import static org.apache.shiro.web.filter.authc.FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yhcrt.weihu.bbs.web.CmsUtils;
import com.yhcrt.weihu.bbs.web.FrontUtils;
import com.yhcrt.weihu.core.entity.CmsSite;
import com.yhcrt.weihu.core.manager.UnifiedUserMng;

@Controller
public class CasLoginAct {

	public static final String LOGIN_INPUT = "tpl.loginInput";
	public static final String LOGIN_STATUS = "tpl.loginStatus";
	public static final String REGISTER_ACTIVE_SUCCESS = "tpl.registerActiveSuccess";
	
	@RequestMapping(value = "/login.jspx")
	public String login(HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		String sol = site.getSolutionPath();
		model.addAttribute("site", site);
		FrontUtils.frontData(request, model, site);
		String returnUrl = FrontUtils.getTplPath(request, site, TPLDIR_MEMBER, LOGIN_INPUT);
		return returnUrl;
	}


	@RequestMapping(value = "/login.jspx", method = RequestMethod.POST)
	public String submit(String username,HttpServletRequest request, ModelMap model)  {
		CmsSite site = CmsUtils.getSite(request);
		String sol = site.getSolutionPath();
		Object error = request.getAttribute(DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		if (error != null) {
			if(error.equals("org.apache.shiro.authc.UnknownAccountException")){
				model.addAttribute("error","用户不存在");
			}else if(error.equals("com.yhcrt.weihu.common.security.DisabledException")){
				model.addAttribute("error","用户被禁用");
			}else if(error.equals("com.yhcrt.weihu.common.security.CaptchaErrorException")){
				model.addAttribute("error","验证码错误");
			}else if(error.equals("org.apache.shiro.authc.IncorrectCredentialsException")){
				model.addAttribute("error","密码错误");
			}else if(error.equals("com.yhcrt.weihu.common.security.InactiveException")){
				model.addAttribute("error","用户未激活");
			}
			model.addAttribute("errorRemaining", unifiedUserMng.errorRemaining(username));
		}
		Integer errorRemaining = unifiedUserMng.errorRemaining(username);
		model.put("haveCount", errorRemaining);
		FrontUtils.frontData(request, model, site);
		String returnUrl = FrontUtils.getTplPath(request, site, TPLDIR_MEMBER, LOGIN_INPUT);
		return returnUrl;
	}
	@Autowired
	private UnifiedUserMng unifiedUserMng;
}
