package com.whty.page.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OperationInfo {
//	private static final Logger logger = Logger.getLogger(OperationInfo.class);

	private static final Logger logger = LoggerFactory.getLogger(OperationInfo.class);

	
	protected int operationType;

	protected String businessCode;

	protected boolean businessOpResult;

	public String getBusinessCode() {
		return businessCode;
	}

	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}

	public boolean isBusinessOpResult() {
		return businessOpResult;
	}

	public void setBusinessOpResult(boolean businessOpResult) {
		this.businessOpResult = businessOpResult;
	}

	public int getOperationType() {
		return operationType;
	}

	public void setOperationType(int operationType) {
		this.operationType = operationType;
	}
}
