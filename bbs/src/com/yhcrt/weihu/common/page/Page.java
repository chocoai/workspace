package com.yhcrt.weihu.common.page;

import java.io.Serializable;
import java.util.List;

/**
 * 封装分页查询的参数与结果
 * 
 * @author 充满智慧的威哥
 */
public class Page<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer pageNo = 1; //当前页
	private Integer pageSize = 15; //每页显示数
	private List<T> list = null; //结果集
	private Integer pageCount = 0; //总页数
	private Integer totalCount = 0; //总记录数
	private Object other; //其他
	public static final Integer SIZE = 10;
	
	public Page() {
	}

	public Page(Integer pageNo) {
		setPageNo(pageNo);
	}
	
	public Page(Integer pageNo, Integer pageSize) {
		setPageNo(pageNo);
		setPageSize(pageSize);
	}
	
	/**
	 * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的索引位置.
	 */
	public Integer getFirstIndex() {
		if (pageNo < 1 || pageSize < 1) {
			return 0;
		} else {
			return ((pageNo - 1) * pageSize) < totalCount? ((pageNo - 1) * pageSize) : totalCount;
		}
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		if (pageNo == null) {
			pageNo = 0;
		}
		if (pageNo < 1) {
			this.pageNo = 1;
		} else {
			this.pageNo = pageNo;
		}
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
		pageCount = getPageCount();
		if (pageNo > pageCount) {
			pageNo = pageCount;
		}
	}

	/**
	 * 计算总页数.
	 */
	public Integer getPageCount() {
		if (totalCount < 1) {
			return 0;
		}
		return (totalCount - 1) / pageSize + 1;
	}

	public Object getOther() {
		return other;
	}

	public void setOther(Object other) {
		this.other = other;
	}
	

}
