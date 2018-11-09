package com.whty.assis.sysres.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.whty.assis.sysres.model.TaManageUserInfo;
import com.whty.assis.sysres.service.SysUserService;
public class MyRealm extends AuthorizingRealm {

	@Autowired
	private SysUserService sysUserService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String account = (String)principals.getPrimaryPrincipal();
		TaManageUserInfo taManageUserInfo = sysUserService.selectByAccount(account);
		//String userId = user.getUserId().toString();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		//authorizationInfo.setStringPermissions(sysResService.findPermissionsByUserId(userId));
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String account = (String) token.getPrincipal();
		String password = new String((char[]) token.getCredentials());

		TaManageUserInfo loginUser = sysUserService.selectByAccount(account);
		if (loginUser == null) {
			throw new UnknownAccountException("用户名或密码错误");
		}
		if (!password.equals(loginUser.getPassword())) {
			throw new IncorrectCredentialsException("用户名或密码错误");
		}
		if (1 == loginUser.getStatus()) {
			throw new DisabledAccountException("该账号已被禁用");
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(account, password, getName());
		return authenticationInfo;
	}

}
