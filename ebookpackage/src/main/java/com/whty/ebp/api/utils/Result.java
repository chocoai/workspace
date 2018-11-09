package com.whty.ebp.api.utils;


public class Result {

	/**
	 * 操作结果。成功：1，失败：0
	 */
	private String result;
	/**
	 *  
	 */
	private String desc;

	/**
	 * 附加数据
	 */
	private Object data;

	
	Result(String result) {
		this.result = result;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
