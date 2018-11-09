package com.whty.ebp.api.utils;


public class ResultPage extends Result{
 
	ResultPage(String result) {
		super(result);
	}

	public int totalPage;

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
}
