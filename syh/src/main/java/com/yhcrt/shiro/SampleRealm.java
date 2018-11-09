package com.yhcrt.shiro;

import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.yhcrt.entity.system.SysUser;
import com.yhcrt.service.stsyem.SysRoleResService;
import com.yhcrt.service.stsyem.SysStsyemLogService;
import com.yhcrt.service.stsyem.SysUserRoleService;
import com.yhcrt.service.stsyem.SysUserService;
import com.yhcrt.utils.Constants;
import com.yhcrt.utils.ConstantsLog;
import com.yhcrt.utils.DateUtil;
import com.yhcrt.utils.PublicUtil;


/**
 * 
 * shiro 认证 + 授权   重写
 * 
 */
public class SampleRealm extends AuthorizingRealm {

	@Autowired
	SysUserService sysUserService;
	@Autowired
	SysRoleResService sysRoleResService;
	@Autowired
	SysUserRoleService sysUserRoleService;
	@Autowired
	SysStsyemLogService sysStsyemLogService;
	
	public SampleRealm() {
		super();
	}
	/**
	 *  认证信息，主要针对用户登录， 
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		SysUser user = null;
		ShiroToken token = (ShiroToken) authcToken;
			try {
				user = sysUserService.getByUsreCodeAndPwd(token.getUsername(),token.getPswd());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(null == user){
			throw new AccountException(ConstantsLog.USER_ACCOUNT);
		}else if(Constants.Middle_01.equals(user.getState())){
			throw new DisabledAccountException(ConstantsLog.USER_DISABLED);
		}else{
			//更新登录时间 last login time
			user.setLastLoginTime(DateUtil.getTime());
			user.setLastLoginIp(PublicUtil.getIp());
			try {
				sysUserService.updateByPrimaryKeySelective(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new SimpleAuthenticationInfo(user,user.getPassword(), getName());
    }

	 /** 
     * 授权 
     */  
    @Override  
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {  
    	SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
    	Integer userId = sysUser.getCid();
		SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
		//根据用户ID查询权限（permission），放入到Authorization里。
		Set<String> permissions = null;
		try {
			permissions = sysRoleResService.findResByUserId(userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		info.setStringPermissions(permissions);
        return info;  
    }  
    /**
     * 清空当前用户权限信息
     */
	public  void clearCachedAuthorizationInfo() {
		PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principalCollection, getName());
		super.clearCachedAuthorizationInfo(principals);
	}
	/**
	 * 指定principalCollection 清除
	 */
	public void clearCachedAuthorizationInfo(PrincipalCollection principalCollection) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principalCollection, getName());
		super.clearCachedAuthorizationInfo(principals);
	}
}
