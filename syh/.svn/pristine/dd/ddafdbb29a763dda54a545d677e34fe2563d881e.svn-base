package com.yhcrt.shiro.filter;


import static com.yhcrt.utils.Constants.LOGIN_USER;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.AccessControlFilter;

import com.yhcrt.entity.system.SysMuserInfo;
import com.yhcrt.entity.system.SysUser;
import com.yhcrt.service.stsyem.SysMuserInfoService;
import com.yhcrt.shiro.TokenManager.TokenManager;
import com.yhcrt.shiro.utils.ShiroFilterUtils;
import com.yhcrt.utils.LoggerUtils;

/**
 * 
 * 判断登录
 * 
 */
public class LoginFilter  extends AccessControlFilter {
	
	@Resource(name="sysMuserInfoService")
	private SysMuserInfoService sysMuserInfoService;
	
	final static Class<LoginFilter> CLASS = LoginFilter.class;
	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		
		SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
		
		
		if(null != sysUser || isLoginRequest(request, response)){// && isEnabled()
			SysMuserInfo muserInfo =(SysMuserInfo) TokenManager.getVal2Session(LOGIN_USER);
			if(muserInfo==null){
				SysMuserInfo sysMuserInfo = sysMuserInfoService.findByUserId(sysUser.getCid());
				TokenManager.setVal2Session(LOGIN_USER,sysMuserInfo);
			}
            return Boolean.TRUE;
        }
		return Boolean.FALSE ;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
			throws Exception {
		if (isLoginRequest(request, response)) {//当前请求是否是登录请求  
                return true;
        } else {
            if (ShiroFilterUtils.isAjax(request)) {
    			Map<String,String> resultMap = new HashMap<String, String>();
    			LoggerUtils.debug(getClass(), "登录超时或没有登录，并且是Ajax请求！");
    			resultMap.put("states", "405");
    			resultMap.put("message", "\u767B\u5F55\u8D85\u65F6\u6216\u6CA1\u6709\u767B\u5F55");//登录超时或没有登录
    			ShiroFilterUtils.out(response, resultMap);
            }else{
            	saveRequestAndRedirectToLogin(request, response); 
            }
        }
		return Boolean.FALSE ;
	}
	

}
