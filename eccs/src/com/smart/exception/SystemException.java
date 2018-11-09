package com.smart.exception;

public class SystemException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SystemException(String frdMessage) {
		super(createFriendlyErrMsg(frdMessage));
	}

	public SystemException(Throwable throwable) {
		super(throwable);
	}

	public SystemException(Throwable throwable, String frdMessage) {
		super(throwable);
	}

	private static String createFriendlyErrMsg(String msgBody) {
		String prefixStr = "抱歉，";
		String suffixStr = " ...";
		StringBuffer friendlyErrMsg = new StringBuffer("");
		friendlyErrMsg.append(prefixStr);
		friendlyErrMsg.append(msgBody);
		friendlyErrMsg.append(suffixStr);
		return friendlyErrMsg.toString();
	}

}
