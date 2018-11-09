package com.whty.ebp.api.model;

import java.io.Serializable;
import java.util.Date;

/**
 * ta_soft表对应的model
 * 
 * @author xuedong
 * @date 2015年8月27日下午12:35:52
 */
public class Softs implements Serializable {

	private static final long serialVersionUID = -8861578027875749953L;
	private String id;       // 软件主键ID
	private String soft_type;// 软件类型(0:教师助手)
	private String soft_name;// 软件名称
	private String version_code;// 软件版本
	private Date create_time;// 文件上传时间
	private Date update_time;// 更新时间
	private String status;// 状态(0:正常，1：已删除)
	private String file_url;// 文件绝对路径
	private String file_name;// 文件名称
	private String file_rename;// 重命名文件名称
	private String force_update;// 是否强制更新(0:否，1：是)
	private String user_update;// 是否指定用户更新(0:否，1：是)
	private String file_size; //文件大小
	
	public Softs() {
	}

	public String getSoft_name() {
		return soft_name;
	}

	public void setSoft_name(String soft_name) {
		this.soft_name = soft_name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSoft_type() {
		return soft_type;
	}

	public void setSoft_type(String soft_type) {
		this.soft_type = soft_type;
	}

	public String getVersion_code() {
		return version_code;
	}

	public void setVersion_code(String version_code) {
		this.version_code = version_code;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFile_url() {
		return file_url;
	}

	public void setFile_url(String file_url) {
		this.file_url = file_url;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_rename() {
		return file_rename;
	}

	public void setFile_rename(String file_rename) {
		this.file_rename = file_rename;
	}

	public String getForce_update() {
		return force_update;
	}

	public void setForce_update(String force_update) {
		this.force_update = force_update;
	}

	public String getUser_update() {
		return user_update;
	}

	public void setUser_update(String user_update) {
		this.user_update = user_update;
	}

	public String getFile_size() {
		return file_size;
	}

	public void setFile_size(String file_size) {
		this.file_size = file_size;
	}

}
