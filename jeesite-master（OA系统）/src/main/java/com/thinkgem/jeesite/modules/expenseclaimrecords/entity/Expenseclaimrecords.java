/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.expenseclaimrecords.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.Area;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 单表生成Entity
 * @author ThinkGem
 * @version 2017-12-25
 */
public class Expenseclaimrecords extends DataEntity<Expenseclaimrecords> {
	
	private static final long serialVersionUID = 1L;
	private String projectName;		// 工程名
	private Area area;		// 区域名
	private String treeId;		// 多选
	private Date date;		// 时间
	private String total;		// 金额
	
	public Expenseclaimrecords() {
		super();
	}

	public Expenseclaimrecords(String id){
		super(id);
	}

	@Length(min=1, max=30, message="工程名长度必须介于 1 和 30 之间")
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	@Length(min=0, max=64, message="多选长度必须介于 0 和 64 之间")
	public String getTreeId() {
		return treeId;
	}

	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="时间不能为空")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
	
}