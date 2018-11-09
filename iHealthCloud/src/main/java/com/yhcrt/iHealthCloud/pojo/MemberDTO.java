/**
 * @Title:   Member.java 
 * @Package: com.yhcrt.iHealthCloud.pojo  
 * @Description: 
 * @author: rpf
 * @date: 2017年11月15日 
 * @version V1.0 
 * Copyright © 2017 Wuhan YHCRT Technology Co.Ltd. All rights reserved.
 */
package com.yhcrt.iHealthCloud.pojo;

/**
 * @ClassName: Member
 * @Description:
 * @version V1.0
 * @author rpf
 * @date: 2017年11月15日
 */
public class MemberDTO {

	private String memberId;

	private String realName;

	private String nickName;

	private String headPic;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	/**
	 * 
	 */
	public MemberDTO() {
		
	}

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", realName=" + realName + ", nickName=" + nickName + ", headPic="
				+ headPic + "]";
	}

}
