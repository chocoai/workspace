package com.yhcrt.demo.util;

import java.util.List;

/**
 * @author fengkun
 * @email 231788364@qq.com
 */
public class ListView<E> {

	private Long totalRecord;
	private List<E> data;

	public Long getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(Long totalRecord) {
		this.totalRecord = totalRecord;
	}

	public List<E> getData() {
		return data;
	}

	public void setData(List<E> data) {
		this.data = data;
	}

}
