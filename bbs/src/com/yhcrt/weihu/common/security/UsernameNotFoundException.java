package com.yhcrt.weihu.common.security;

import com.yhcrt.weihu.common.security.AuthenticationException;

/**
 * 用户名没有找到异常
 * 
 * @author tom
 * 
 */
@SuppressWarnings("serial")
public class UsernameNotFoundException extends AuthenticationException {
	public UsernameNotFoundException() {
	}

	public UsernameNotFoundException(String msg) {
		super(msg);
	}
}