package com.whty.assis.demo.model;

import java.util.Date;

public class Demo {

	/** 状态-未发布 */
	public static final int STATUS_UN_PUBLISH = 1;
	/** 状态-已发布 */
	public static final int STATUS_HAVE_PUBLISH = 2;

	private String id;// varchar2(32) n 主键
	private String title;// varchar2(200) y 标题
	private String content;// varchar2(2000) y 内容
	private String createrId;// varchar2(50) y 创建者id
	private String createrName;// varchar2(50) y 创建者姓名
	private Date createrTime = new Date();// date y 创建时间
	private Integer status;// NUMBER(2) Y 状态 1-未发布 2-已发布
	private Integer deleted = 0;// number(1) y 删除标示1是0否

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreaterId() {
		return createrId;
	}

	public void setCreaterId(String createrId) {
		this.createrId = createrId;
	}

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	public Date getCreaterTime() {
		return createrTime;
	}

	public void setCreaterTime(Date createrTime) {
		this.createrTime = createrTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

}
