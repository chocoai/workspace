package com.whty.ebp.api.respvo;

public class CheckNewSilentRet {

	String result = "000000";
	String resultDesc = "success";
	String isNew = "0";//是否存在最新版本，1—存在，0—不存在
	
	CheckNewSilentSoft data;

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

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

	public CheckNewSilentSoft getData() {
		return data;
	}

	public void setData(CheckNewSilentSoft data) {
		this.data = data;
	}
}
