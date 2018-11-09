package com.whty.page.util;

import java.util.List;

import org.apache.log4j.Logger;

import com.whty.page.Page;



@SuppressWarnings("serial")
public class HandlerResult implements java.io.Serializable
{
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(HandlerResult.class);
	
	ResultState resultState;
	
	protected List resultList;
	
	protected Page page;
	
	protected ClientCallbackInfo clientCallbackInfo;
	
	public ResultState getResultState() {
		return resultState;
	}

	public void setResultState(ResultState resultState) {
		this.resultState = resultState;
	}

	public HandlerResult(){}

	public ClientCallbackInfo getClientCallbackInfo()
	{
		return clientCallbackInfo;
	}

	public void setClientCallbackInfo(ClientCallbackInfo clientCallbackInfo)
	{
		this.clientCallbackInfo = clientCallbackInfo;
	}


	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}
}
