package com.yhcrt.controller;


import static com.yhcrt.utils.Constants.LOGIN_USER;

import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;

import com.yhcrt.entity.system.SysMuserInfo;
import com.yhcrt.service.stsyem.SysStsyemLogService;
import com.yhcrt.shiro.TokenManager.TokenManager;
import com.yhcrt.utils.SpringUtil;



/**
 * 
 * TODO
 * @author 陈伟
 * 2017年5月19日 下午6:35:06
 * 版权所有：武汉炎黄创新服务有限公司
 */
public class BaseController {

	@Resource(name="sysStsyemLogService")
	protected SysStsyemLogService sysStsyemLogService;
	
	public static  int pageSize = 10;
	public static  int scorePageSize = 8;
	protected Map<String, Object> model = new LinkedHashMap<String, Object>();
	
	
	/**
	 * 将当前登录用户User存入session
	 * 
	 * @param request
	 * @return
	 */
	public void setSessionUser(SysMuserInfo entity) {
		setValue2Request(LOGIN_USER,entity);
	}
    /**
     * 从session获取当前登录用户SysMuserInfo
     * 
     * @param request
     * @return
     */
    public static SysMuserInfo getSessionUser() {
	return (SysMuserInfo) getVal2Session(LOGIN_USER);
    }
    /**
     * 从session获取当前登录的账号
     * 
     * @param request
     * @return
     */
    public static String getUserCode() {
    	return TokenManager.getUserCode();
    }
    
    /**
     * 从session获取当前登录的ID
     * 
     * @param request
     * @return
     */
    public Integer getSessionCid() {
    	return getSessionUser().getCid();
    }
    /**
     * 从session获取当前登录用户的姓名
     * 
     * @param request
     * @return
     */
    public static  String getSessionName() {
    	return getSessionUser().getUserSname();
    }
	/**
	 * 往request中传入参数
	 * @param request
	 * @param key
	 * @param value
	 */
	protected static void setValue2Request(String key,Object value){
		TokenManager.setVal2Session(key,value);
	}
	/**
	 * 从当前登录用户的Session里取值
	 * @param key
	 * @return
	 */
	public static Object getVal2Session(Object key){
		return getSession().getAttribute(key);
	}
	
	/**
	 * [获取session]
	 * @param request
	 * @return
	 */
	public static Session getSession(){
		return TokenManager.getSession();
	}
	
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * 从config中取值
	 * @param key
	 * @return
	 */
	public String getConfig(String key) {
		return SpringUtil.getConfig(key);
	}
}
