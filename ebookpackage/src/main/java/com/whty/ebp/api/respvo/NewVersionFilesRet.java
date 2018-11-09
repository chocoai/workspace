package com.whty.ebp.api.respvo;

public class NewVersionFilesRet {

	String result = "000000";
	String resultDesc = "success";
	
	NewSoft data;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	public NewSoft getData() {
		return data;
	}

	public void setData(NewSoft data) {
		this.data = data;
	}
	
}
