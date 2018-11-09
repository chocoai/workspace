package com.whty.ebp.api.respvo;

import java.util.List;

public class MessageRet {

	String result = "000000";
	String resultDesc = "success";

	Integer currPage=0;
	Integer pageSize=0;
	Integer count=0;
	
	List<ApiMessage> data;

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

	public Integer getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<ApiMessage> getData() {
		return data;
	}

	public void setData(List<ApiMessage> data) {
		this.data = data;
	}

}
