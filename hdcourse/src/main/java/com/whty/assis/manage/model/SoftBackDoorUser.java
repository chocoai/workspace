/**
 * 
 */
package com.whty.assis.manage.model;

import java.io.Serializable;

/**
 * @author zhangzheng
 * @date 2018年9月14日
 */
public class SoftBackDoorUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6220469500945790571L;

	private Integer id;
	private Integer softBackDoorId;
	private String userId;
	private String userPlatformCode;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSoftBackDoorId() {
		return softBackDoorId;
	}

	public void setSoftBackDoorId(Integer softBackDoorId) {
		this.softBackDoorId = softBackDoorId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPlatformCode() {
		return userPlatformCode;
	}

	public void setUserPlatformCode(String userPlatformCode) {
		this.userPlatformCode = userPlatformCode;
	}

}
