/**
 * 
 */
package com.smart.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @Description:流程环节定义表：定义流程的各个环节(proce_step_def)
 * @author raopanfeng
 * @date 2017年1月19日 下午4:00:16
 */

@Entity
@Table(name = "proce_step_def", catalog = "eccs")
public class ProceStepDef implements java.io.Serializable {

	private static final long serialVersionUID = -3373393946716864480L;

	// 主键ID
	private Integer uuid;

	// 环节名称
	private String stepName;

	// 环节编码
	private String stepCode;

	// 业务类型(0：经营管理，1：项目管理)
	private String businessType;

	// 环节描述
	private String stepDesc;

	private String url;
	
	private Integer sort;

	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "uuid", unique = true, nullable = false)
	public Integer getUuid() {
		return this.uuid;
	}

	@Column(name = "step_name", length = 32)
	public String getStepName() {
		return this.stepName;
	}

	@Column(name = "step_code", length = 8)
	public String getStepCode() {
		return this.stepCode;
	}

	@Column(name = "business_type", length = 1)
	public String getBusinessType() {
		return this.businessType;
	}

	@Column(name = "step_desc", length = 128)
	public String getStepDesc() {
		return this.stepDesc;
	}

	@Column(name = "url", length = 64)
	public String getUrl() {
		return this.url;
	}

	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}

	public void setStepCode(String stepCode) {
		this.stepCode = stepCode;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public void setStepDesc(String stepDesc) {
		this.stepDesc = stepDesc;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	@Column(name = "sort")
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}
