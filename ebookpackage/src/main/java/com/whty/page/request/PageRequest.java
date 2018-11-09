package com.whty.page.request;

import com.whty.page.PageSize;


public class PageRequest implements java.io.Serializable{
	private static final long serialVersionUID = -7028781226092050831L;
	 private int pageSize; // 每页默认10条数据
	 private int currPage; // 当前页
	 private int totalPage; // 总页数
	public int getPageSize() {
		if(pageSize==0){
			currPage=1;
			pageSize=PageSize.ten.getSize();
		}
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrPage() {
		if(currPage==0){
			currPage=1;
			pageSize=PageSize.ten.getSize();
		}
		return currPage;
	}
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	 
	 
}
