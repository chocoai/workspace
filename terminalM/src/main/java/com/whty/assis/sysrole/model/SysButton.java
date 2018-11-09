package com.whty.assis.sysrole.model;

public class SysButton {

	private String id;
	private String buttons_name;
	private Integer bottons_sort;
	private Integer status;
	private String modular_id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getButtons_name() {
		return buttons_name;
	}

	public void setButtons_name(String buttons_name) {
		this.buttons_name = buttons_name;
	}

	public Integer getBottons_sort() {
		return bottons_sort;
	}

	public void setBottons_sort(Integer bottons_sort) {
		this.bottons_sort = bottons_sort;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getModular_id() {
		return modular_id;
	}

	public void setModular_id(String modular_id) {
		this.modular_id = modular_id;
	}

}
