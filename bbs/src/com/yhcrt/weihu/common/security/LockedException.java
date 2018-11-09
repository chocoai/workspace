package com.yhcrt.weihu.common.security;

import com.yhcrt.weihu.common.security.AccountStatusException;

/**
 * 账号被锁定异常
 * 
 * @author tom
 * 
 */
@SuppressWarnings("serial")
public class LockedException extends AccountStatusException {
	public LockedException() {
	}

	public LockedException(String msg) {
		super(msg);
	}

	public LockedException(String msg, Object extraInformation) {
		super(msg, extraInformation);
	}
}
