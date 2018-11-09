package com.yhcrt.healthcloud.shiro.realm;

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

import com.yhcrt.healthcloud.common.Constants;
import com.yhcrt.healthcloud.system.entity.SysUser;
import com.yhcrt.healthcloud.system.service.SysResService;
import com.yhcrt.healthcloud.system.service.SysUserService;

public class MyRealm extends AuthorizingRealm {

	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysResService sysResService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String)principals.getPrimaryPrincipal();
		SysUser user = sysUserService.selectByUserCode(username);
		String userId = user.getUserId().toString();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setStringPermissions(sysResService.findPermissionsByUserId(userId));
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName = (String) token.getPrincipal();
		String password = new String((char[]) token.getCredentials());

		SysUser loginUser = sysUserService.selectByUserCode(userName);
		if (loginUser == null) {
			throw new UnknownAccountException("用户名或密码错误");
		}
		if (loginUser.getUserType() != Constants.UserType.USER_TYPE_EMP) {
			throw new UnknownAccountException("用户名或密码错误");
		}
		if (!password.equals(loginUser.getPassword())) {
			throw new IncorrectCredentialsException("用户名或密码错误");
		}
		if (Constants.STATUS_DISABLE == loginUser.getStatus()) {
			throw new DisabledAccountException("该账号已被禁用");
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, password, getName());
		return authenticationInfo;
	}

}
