/**
 * @Title:   DataTable.java 
 * @Package: com.yhcrt.healthcloud.common  
 * @Description: 
 * @author: rpf
 * @date: 2018年1月9日 
 * @version V1.0 
 * Copyright © 2018 Wuhan YHCRT Technology Co.Ltd. All rights reserved.
 */
package com.yhcrt.healthcloud.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: DataTable
 * @Description:
 * @version V1.0
 * @author rpf
 * @date: 2018年1月9日
 */
public class DataTable {

	/**
	 * 绘制计数器。这个是用来确保Ajax从服务器返回的是对应的（Ajax是异步的，因此返回的顺序是不确定的）。 要求在服务器接收到此参数后再返回
	 */
	private Integer draw;

	/**
	 * 第一条数据的起始位置，比如0代表第一条数据
	 */
	private Integer start = 0;

	/**
	 * 告诉服务器每页显示的条数，这个数字会等于返回的 data集合的记录数，可能会大于因为服务器可能没有那么多数据。这个也可能是-1，代表需要返回全部数据
	 */
	private Integer length = 10;

	/**
	 * 全局的搜索条件，条件会应用到每一列（ searchable需要设置为 true ）
	 */
	private String search;

	/**
	 * 没有过滤的记录数（数据库里总共记录数）
	 */
	private Integer recordsTotal;

	/**
	 * 过滤后的记录数（如果有接收到前台的过滤条件，则返回的是过滤后的记录数）
	 */
	private Integer recordsFiltered;

	/**
	 * 表中需要显示的数据。这是一个对象数组，也可以只是数组
	 */
	private List<?> data;

	/**
	 * 可选。你可以定义一个错误来描述服务器出了问题后的友好提示
	 */
	private String error;

	/*
	 * 当前页码
	 */
	private Integer pageNum = 1;

	/*
	 * 每页数据 量
	 */
	private Integer pageSize = 10;

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public Integer getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(Integer recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public Integer getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(Integer recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 
	 */
	public DataTable() {
		super();
		
	}

	/**
	 * @param draw
	 * @param start
	 * @param length
	 * @param search
	 * @param recordsTotal
	 * @param recordsFiltered
	 * @param data
	 * @param error
	 * @param pageNum
	 * @param pageSize
	 */
	public DataTable(Integer draw, Integer start, Integer length, String search, Integer recordsTotal,
			Integer recordsFiltered, List<?> data, String error, Integer pageNum, Integer pageSize) {
		super();
		this.draw = draw;
		this.start = start;
		this.length = length;
		this.search = search;
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsFiltered;
		this.data = data;
		this.error = error;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}
	
	public DataTable(HttpServletRequest request) {
		//开始的数据行数  
        String start = request.getParameter("start");  
        //每页的数据数  
        String length = request.getParameter("length");  
        //DT传递的draw:  
        String draw = request.getParameter("draw"); 
		
        this.setStart(Integer.parseInt(start));  
        this.setLength(Integer.parseInt(length));  
        this.setDraw(Integer.parseInt(draw));  
        //计算页码  
        this.pageNum = (Integer.parseInt(start) / Integer.parseInt(length)) + 1; 
        this.pageSize = Integer.parseInt(length);
        
	}
	
	
	
	

}
