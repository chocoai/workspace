package com.whty.common.exception;

public class BusinessException extends Exception {
	
	private String message;
	

	public BusinessException(String message ,Throwable t) {
		super(message,t);
		this.message = message;
	}

	public BusinessException(Throwable t) {
		super(t);
	}

	public BusinessException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}