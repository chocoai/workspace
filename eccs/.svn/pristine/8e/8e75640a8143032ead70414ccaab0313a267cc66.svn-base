package com.smart.model;

import java.util.Date;
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
 * BidFileItem entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "bid_file_item", catalog = "eccs")
public class BidFileItem implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;

	private Integer id;

	private BidSummary bidSummary;

	private BidFile bidFile;

	private String name;

	private String path;

	private Long size;

	private User user;

	private Dept dept;

	private Date ctime;

	private Integer status;

	// Constructors

	/** default constructor */
	public BidFileItem() {
		ctime = new Date();
		status = 1;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bid_summary_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public BidSummary getBidSummary() {
		return bidSummary;
	}

	public void setBidSummary(BidSummary bidSummary) {
		this.bidSummary = bidSummary;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bid_file_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public BidFile getBidFile() {
		return bidFile;
	}

	public void setBidFile(BidFile bidFile) {
		this.bidFile = bidFile;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "path", length = 200)
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(name = "size")
	public Long getSize() {
		return this.size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dept_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	@Column(name = "ctime", length = 19)
	public Date getCtime() {
		return this.ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}