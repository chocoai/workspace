package com.yhcrt.shiro.TokenManager;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import com.yhcrt.entity.system.SysUser;
import com.yhcrt.shiro.ShiroToken;
import com.yhcrt.utils.Constants;



/**
 * 
 * Shiro管理下的Token工具类
 */
public class TokenManager {
	
	/**
	 * 获取当前登录的用户User对象
	 * @return
	 */
	public static SysUser getToken(){
		SysUser token = (SysUser)SecurityUtils.getSubject().getPrincipal();
		return token ;
	}
    /**
     * 从session获取当前登录的ID
     * 
     * @param request
     * @return
     */
    public static Integer getUserCid() {
    	return getToken().getCid();
    }
    /**
     * 从session获取当前登录的账号
     * 
     * @param request
     * @return
     */
    public static String getUserCode() {
    	return getToken().getUserCode();
    }
    /**
     * 从session获取当前登录系统的届数
     * 
     * @param request
     * @return
     */
    public static Integer getErsionNum() {
    	return (Integer) TokenManager.getVal2Session(Constants.ERSION_NUM);
    }
	
	/**
	 * 获取当前用户的Session
	 * @return
	 */
	public static Session getSession(){
		return SecurityUtils.getSubject().getSession();
	}
	/**
	 * 把值放入到当前登录用户的Session里
	 * @param key
	 * @param value
	 */
	public static void setVal2Session(Object key ,Object value){
		getSession().setAttribute(key, value);
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
	 * 获取验证码，获取一次后删除
	 * @return
	 */
	public static String getYZM(){
		String code = (String) getSession().getAttribute("CODE");
		getSession().removeAttribute("CODE");
		return code ;
	}
	
	/**
	 * 登录
	 * @param user
	 * @param rememberMe
	 * @return
	 */
	public static SysUser login(SysUser user,Boolean rememberMe){
		ShiroToken token = new ShiroToken(user.getUserCode(), user.getPassword());
		token.setRememberMe(rememberMe);
		SecurityUtils.getSubject().login(token);
		return getToken();
	}
	
}
