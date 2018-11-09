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
 * ProjectContact entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "person_fenpei", catalog = "eccs")
public class PersonFenpei implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Project project;

	private Integer proid;

	// private User user;

	private String handlers;

	private String pstart;

	private String pcontinue;

	private String realstart;

	private String realcontinue;

	private String complet;

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
	@JoinColumn(name = "project_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Column(name = "pro_id")
	public Integer getProid() {
		return proid;
	}

	public void setProid(Integer proid) {
		this.proid = proid;
	}

	@Column(name = "user_id")
	public String getHandlers() {
		return this.handlers;
	}

	public void setHandlers(String handlers) {
		this.handlers = handlers;
	}

	@Column(name = "pstart")
	public String getPstart() {
		return pstart;
	}

	public void setPstart(String pstart) {
		this.pstart = pstart;
	}

	@Column(name = "pcontinue")
	public String getPcontinue() {
		return pcontinue;
	}

	public void setPcontinue(String pcontinue) {
		this.pcontinue = pcontinue;
	}

	@Column(name = "realstart")
	public String getRealstart() {
		return realstart;
	}

	public void setRealstart(String realstart) {
		this.realstart = realstart;
	}

	@Column(name = "realcontinue")
	public String getRealcontinue() {
		return realcontinue;
	}

	public void setRealcontinue(String realcontinue) {
		this.realcontinue = realcontinue;
	}

	@Column(name = "complet")
	public String getComplet() {
		return complet;
	}

	public void setComplet(String complet) {
		this.complet = complet;
	}

}