package com.smart.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * Account entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "performance_user", catalog = "eccs")
public class PerformanceUser implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields

	private Integer id;

	private Performance performance;

	private Double ctime;

	private User user;

	private String fenpeiproportion;

	private String hedingproportion;

	private Double sum;

	private String archive;

	private String daozhang;

	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "performan_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public Performance getPerformance() {
		return performance;
	}

	public void setPerformance(Performance performance) {
		this.performance = performance;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "fenpeiproportion")
	public String getFenpeiproportion() {
		return fenpeiproportion;
	}

	public void setFenpeiproportion(String fenpeiproportion) {
		this.fenpeiproportion = fenpeiproportion;
	}

	@Column(name = "hedingproportion")
	public String getHedingproportion() {
		return hedingproportion;
	}

	public void setHedingproportion(String hedingproportion) {
		this.hedingproportion = hedingproportion;
	}

	@Column(name = "sum")
	public Double getSum() {
		return sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

	@Column(name = "archive")
	public String getArchive() {
		return archive;
	}

	public void setArchive(String archive) {
		this.archive = archive;
	}

	@Column(name = "daozhang")
	public String getDaozhang() {
		return daozhang;
	}

	public void setDaozhang(String daozhang) {
		this.daozhang = daozhang;
	}

	@Column(name = "ctime")
	public Double getCtime() {
		return ctime;
	}

	public void setCtime(Double ctime) {
		this.ctime = ctime;
	}

}