package com.smart.model;

// default package
// Generated 2017-7-5 11:01:11 by Hibernate Tools 4.0.0.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * BidInfo generated by hbm2java
 */
@Entity
@Table(name = "bid_info", catalog = "eccs")
public class BidInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer cid;
	private ProjectInfo projectInfo;
	private String bidOpenDate;
	private String bidOpenPerson;
	private Integer bidNumber;
	private String bidWinner;
	private Double bidWinnerPrice;
	private Integer isSuccess;
	private Integer status;
	private String createTime;
	private String remark;
	
	//非数据库字段
	private boolean resStates; // 编辑操作权限

	public BidInfo() {
	}

	public BidInfo(Integer cid) {
		this.cid = cid;
	}

	public BidInfo(Integer cid, ProjectInfo projectInfo, String bidOpenDate,
			String bidOpenPerson, Integer bidNumber, String bidWinner,
			Integer isSuccess, Integer status, String createTime,Double bidWinnerPrice,
			String remark) {
		this.cid = cid;
		this.projectInfo = projectInfo;
		this.bidOpenDate = bidOpenDate;
		this.bidOpenPerson = bidOpenPerson;
		this.bidNumber = bidNumber;
		this.bidWinner = bidWinner;
		this.bidWinnerPrice = bidWinnerPrice;
		this.isSuccess = isSuccess;
		this.status = status;
		this.createTime = createTime;
		this.remark = remark;
	}

	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "cid", unique = true, nullable = false)
	public Integer getCid() {
		return this.cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "projectInfo_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public ProjectInfo getProjectInfo() {
		return this.projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	@Column(name = "bid_open_date", length = 19)
	public String getBidOpenDate() {
		return this.bidOpenDate;
	}

	public void setBidOpenDate(String bidOpenDate) {
		this.bidOpenDate = bidOpenDate;
	}

	@Column(name = "bid_open_person", length = 32)
	public String getBidOpenPerson() {
		return this.bidOpenPerson;
	}

	public void setBidOpenPerson(String bidOpenPerson) {
		this.bidOpenPerson = bidOpenPerson;
	}

	@Column(name = "bid_number")
	public Integer getBidNumber() {
		return this.bidNumber;
	}

	public void setBidNumber(Integer bidNumber) {
		this.bidNumber = bidNumber;
	}

	@Column(name = "bid_winner", length = 32)
	public String getBidWinner() {
		return this.bidWinner;
	}

	public void setBidWinner(String bidWinner) {
		this.bidWinner = bidWinner;
	}

	@Column(name = "bid_winner_price")
	public Double getBidWinnerPrice() {
		return bidWinnerPrice;
	}

	public void setBidWinnerPrice(Double bidWinnerPrice) {
		this.bidWinnerPrice = bidWinnerPrice;
	}

	@Column(name = "is_success")
	public Integer getIsSuccess() {
		return this.isSuccess;
	}

	public void setIsSuccess(Integer isSuccess) {
		this.isSuccess = isSuccess;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "create_time", length = 19)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "remark", length = 128)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Transient
	public boolean getResStates() {
		return resStates;
	}

	public void setResStates(boolean resStates) {
		this.resStates = resStates;
	}

}
