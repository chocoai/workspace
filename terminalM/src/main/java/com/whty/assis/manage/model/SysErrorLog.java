package com.whty.assis.manage.model;

import java.util.Date;

import com.whty.assis.base.model.BaseModel;

public class SysErrorLog extends BaseModel {

	private static final long serialVersionUID = 247243991580296977L;

	private int id;
	private String description;
	private String className;
	private String methodName;
	private String parameter;
	private String memo;

	public SysErrorLog(String className, String methodName, String description, Date createTime, String parameter,
			String memo) {
		this.description = description;
		this.className = className;
		this.methodName = methodName;
		this.parameter = parameter;
		this.setCreateTime(createTime);
		this.memo = memo;
	}

	public SysErrorLog(){
		
	}
	
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

}
