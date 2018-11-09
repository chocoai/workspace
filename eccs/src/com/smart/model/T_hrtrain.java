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
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * 培训管理
 * 
 * @author lenovo
 * 
 */
@Entity
@Table(name = "t_hr_train", catalog = "eccs")
public class T_hrtrain implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String theme;// 主题

	private Integer mode;// 培训方式1,内训 2,外训

	private String tdate;// 培训时间

	private String tplace;// 培训地点

	private String content;// 培训内容

	private String teach;// 授课人

	private String trainee;// 受训人员

	private String traineestr;// 受训人员s

	private String remark;// 备注

	private String t_file_id;// 相关附件

	private User user_id;// 登记人

	private String rtime;// 登记时间

	private Date ctime;

	private Integer status;

	public T_hrtrain() {
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

	@Column(name = "theme")
	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	@Column(name = "mode")
	public Integer getMode() {
		return mode;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	}

	@Column(name = "tdate")
	public String getTdate() {
		return tdate;
	}

	public void setTdate(String tdate) {
		this.tdate = tdate;
	}

	@Column(name = "tplace")
	public String getTplace() {
		return tplace;
	}

	public void setTplace(String tplace) {
		this.tplace = tplace;
	}

	@Column(name = "content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "teach")
	public String getTeach() {
		return teach;
	}

	public void setTeach(String teach) {
		this.teach = teach;
	}

	@Column(name = "trainee")
	public String getTrainee() {
		return trainee;
	}

	public void setTrainee(String trainee) {
		this.trainee = trainee;
	}

	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "t_file_id")
	public String getT_file_id() {
		return t_file_id;
	}

	public void setT_file_id(String t_file_id) {
		this.t_file_id = t_file_id;
	}

	@Column(name = "ctime")
	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	@Column(name = "rtime")
	public String getRtime() {
		return rtime;
	}

	public void setRtime(String rtime) {
		this.rtime = rtime;
	}

	@Transient
	public String getTraineestr() {
		return traineestr;
	}

	public void setTraineestr(String traineestr) {
		this.traineestr = traineestr;
	}

}
