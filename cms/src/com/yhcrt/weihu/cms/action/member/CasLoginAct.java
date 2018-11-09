package com.yhcrt.weihu.cms.action.member;

import static com.yhcrt.weihu.cms.Constants.TPLDIR_MEMBER;
import static org.apache.shiro.web.filter.authc.FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yhcrt.weihu.common.web.RequestUtils;
import com.yhcrt.weihu.core.entity.CmsSite;
import com.yhcrt.weihu.core.manager.ConfigMng;
import com.yhcrt.weihu.core.manager.UnifiedUserMng;
import com.yhcrt.weihu.core.web.util.CmsUtils;
import com.yhcrt.weihu.core.web.util.FrontUtils;

@Controller
public class CasLoginAct {
    public static final String COOKIE_ERROR_REMAINING = "_error_remaining";

    public static final String LOGIN_INPUT = "tpl.loginInput";

    public static final String LOGIN_STATUS = "tpl.loginStatus";

    public static final String TPL_INDEX = "tpl.index";

    @RequestMapping(value = "/login.jspx", method = RequestMethod.GET)
    public String input(HttpServletRequest request, ModelMap model) {
	CmsSite site = CmsUtils.getSite(request);
	String sol = site.getSolutionPath();
	String returnUrl = RequestUtils.getQueryParam(request, "returnUrl");
	Integer errorTimes = configMng.getConfigLogin().getErrorTimes();
	model.addAttribute("errorTimes", errorTimes);
	model.addAttribute("site", site);
	model.addAttribute("returnUrl", returnUrl);
	FrontUtils.frontData(request, model, site);
	return FrontUtils.getTplPath(request, sol, TPLDIR_MEMBER, LOGIN_INPUT);
    }

    @RequestMapping(value = "/login.jspx", method = RequestMethod.POST)
    public String submit(String username, HttpServletRequest request, ModelMap model) {
	String returnUrl = RequestUtils.getQueryParam(request, "returnUrl");
	model.addAttribute("returnUrl", returnUrl);
	CmsSite site = CmsUtils.getSite(request);
	String sol = site.getSolutionPath();
	Object error = request.getAttribute(DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
	if (error != null) {
	    model.addAttribute("error", error);
	    model.addAttribute("errorRemaining", unifiedUserMng.errorRemaining(username));
	    FrontUtils.frontData(request, model, site);
	    return FrontUtils.getTplPath(request, sol, TPLDIR_MEMBER, LOGIN_INPUT);
	} else {
	    FrontUtils.frontData(request, model, site);
	    return FrontUtils.showSuccess(request, model, returnUrl);
	}

    }

    @Autowired
    private UnifiedUserMng unifiedUserMng;

    @Autowired
    private ConfigMng configMng;
}
