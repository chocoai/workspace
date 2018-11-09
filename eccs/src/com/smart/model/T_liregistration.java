package com.smart.model;

import java.io.Serializable;
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
 * 证照登记
 * 
 * @author lenovo
 *
 */
@Entity
@Table(name = "t_li_registration", catalog = "eccs")
public class T_liregistration implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String licname;// 证照名称

	private String licnumber;// 证照编号

	private Integer licstatus;// 证照状态1.闲置,2.占用,3,其他

	private String predate;// 颁发日期

	private String prebranch;// 颁发部门

	private String anndate;// 下次年检日期

	private String annbranch;// 年检部门

	private String effdate;// 有效期

	private String filsystem;// 网上填报系统

	private Dept dept_id;// 保管部门

	private User kuser_id;// 保管人

	private Integer characteristic;// 正副本标识1.正本,2.副本

	private String material;// 年检材料

	private String record;// 年检记录

	private String explain;// 备注说明

	private User user_id;// 登记人

	private Date ctime;// 创建日期

	private Date rtime;// 登记日期

	private Integer status;// 是否可用状态

	public T_liregistration() {
		ctime = new Date();
		status = 1;
	}

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

	@Column(name = "li_cname")
	public String getLicname() {
		return licname;
	}

	public void setLicname(String licname) {
		this.licname = licname;
	}

	@Column(name = "li_cnumber")
	public String getLicnumber() {
		return licnumber;
	}

	public void setLicnumber(String licnumber) {
		this.licnumber = licnumber;
	}

	@Column(name = "li_cstatus")
	public Integer getLicstatus() {
		return licstatus;
	}

	public void setLicstatus(Integer licstatus) {
		this.licstatus = licstatus;
	}

	@Column(name = "li_predate")
	public String getPredate() {
		return predate;
	}

	public void setPredate(String predate) {
		this.predate = predate;
	}

	@Column(name = "li_prebranch")
	public String getPrebranch() {
		return prebranch;
	}

	public void setPrebranch(String prebranch) {
		this.prebranch = prebranch;
	}

	@Column(name = "li_anndate")
	public String getAnndate() {
		return anndate;
	}

	public void setAnndate(String anndate) {
		this.anndate = anndate;
	}

	@Column(name = "li_annbranch")
	public String getAnnbranch() {
		return annbranch;
	}

	public void setAnnbranch(String annbranch) {
		this.annbranch = annbranch;
	}

	@Column(name = "li_effdate")
	public String getEffdate() {
		return effdate;
	}

	public void setEffdate(String effdate) {
		this.effdate = effdate;
	}

	@Column(name = "li_filsystem")
	public String getFilsystem() {
		return filsystem;
	}

	public void setFilsystem(String filsystem) {
		this.filsystem = filsystem;
	}

	//
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dept_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public Dept getDept_id() {
		return dept_id;
	}

	public void setDept_id(Dept dept_id) {
		this.dept_id = dept_id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "kuser_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public User getKuser_id() {
		return kuser_id;
	}

	public void setKuser_id(User kuser_id) {
		this.kuser_id = kuser_id;
	}

	@Column(name = "li_characteristic")
	public Integer getCharacteristic() {
		return characteristic;
	}

	public void setCharacteristic(Integer characteristic) {
		this.characteristic = characteristic;
	}

	@Column(name = "li_material")
	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	@Column(name = "li_record")
	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	@Column(name = "li_explain")
	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public User getUser_id() {
		return user_id;
	}

	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}

	@Column(name = "ctime")

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	@Column(name = "rtime")
	public Date getRtime() {
		return rtime;
	}

	public void setRtime(Date rtime) {
		this.rtime = rtime;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
