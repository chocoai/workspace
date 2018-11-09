/**
 * @Title:   IndexData.java 
 * @Package: com.yhcrt.iHealthCloud.pojo  
 * @Description: 
 * @author: rpf
 * @date: 2017年11月17日 
 * @version V1.0 
 * Copyright © 2017 Wuhan YHCRT Technology Co.Ltd. All rights reserved.
 */
package com.yhcrt.iHealthCloud.pojo;

/**
 * @ClassName: IndexData
 * @Description: APP首页模块数据
 * @version V1.0
 * @author rpf
 * @date: 2017年11月17日
 */
public class IndexData {

	private String memberId;
	
	private String userId;

	private String realName;

	private String nickName;

	private String headPic;
	
	private String score;

	private String pulse;
	
	private String imei;
	
	private String sim;

	private String alias;

	private String stepCount;

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

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getPulse() {
		return pulse;
	}

	public void setPulse(String pulse) {
		this.pulse = pulse;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getStepCount() {
		return stepCount;
	}

	public void setStepCount(String stepCount) {
		this.stepCount = stepCount;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSim() {
		return sim;
	}

	public void setSim(String sim) {
		this.sim = sim;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Override
	public String toString() {
		return "IndexData [memberId=" + memberId + ", userId=" + userId + ", realName=" + realName + ", nickName="
				+ nickName + ", headPic=" + headPic + ", score=" + score + ", pulse=" + pulse + ", imei=" + imei
				+ ", sim=" + sim + ", alias=" + alias + ", stepCount=" + stepCount + "]";
	}
	
	

}
