/**
 * @Title:   OrgServicer.java 
 * @Package: com.yhcrt.iHealthCloud.pojo  
 * @Description: 
 * @author: rpf
 * @date: 2017年11月15日 
 * @version V1.0 
 * Copyright © 2017 Wuhan YHCRT Technology Co.Ltd. All rights reserved.
 */
package com.yhcrt.iHealthCloud.pojo;

/**
 * @ClassName: OrgServicer
 * @Description:
 * @version V1.0
 * @author rpf
 * @date: 2017年11月15日
 */
public class OrgServicer {

	private Integer id;

	private String nickName;

	private String realName;

	private String headPic;

	private String userType;
	
	private String specialty;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	/**
	 * 
	 */
	public OrgServicer() {
	}

	@Override
	public String toString() {
		return "OrgServicer [id=" + id + ", nickName=" + nickName + ", realName=" + realName + ", headPic=" + headPic
				+ ", userType=" + userType + ", specialty=" + specialty + "]";
	}

}
