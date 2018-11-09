package com.yhcrt.weihu.common.security;

import com.yhcrt.weihu.common.security.AuthenticationException;

/**
 * 账号状态异常
 */
@SuppressWarnings("serial")
public class AccountStatusException extends AuthenticationException {
	public AccountStatusException() {
	}

	public AccountStatusException(String msg) {
		super(msg);
	}

	public AccountStatusException(String msg, Object extraInformation) {
		super(msg, extraInformation);
	}
}
