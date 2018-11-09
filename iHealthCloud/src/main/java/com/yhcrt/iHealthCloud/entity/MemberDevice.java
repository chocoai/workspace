package com.yhcrt.iHealthCloud.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class MemberDevice {
	
	@JSONField(serialize = false)
	private Integer cid;
	
	@JSONField(serialize = false)
	private Integer memberId;

	private String imei;
	
	@JSONField(serialize = false)
	private Integer orgId;

	private String deviceType;

	private String sim;

	private String bindTime;
	
	private Integer isDefault;
	
	@JSONField(serialize = false)
	private Integer status;

	@JSONField(serialize = false)
	private Member member;

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei == null ? null : imei.trim();
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType == null ? null : deviceType.trim();
	}

	public String getSim() {
		return sim;
	}

	public void setSim(String sim) {
		this.sim = sim == null ? null : sim.trim();
	}

	public String getBindTime() {
		return bindTime;
	}

	public void setBindTime(String bindTime) {
		this.bindTime = bindTime == null ? null : bindTime.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	@Override
	public String toString() {
		return "MemberDevice [cid=" + cid + ", memberId=" + memberId + ", imei=" + imei + ", orgId=" + orgId
				+ ", deviceType=" + deviceType + ", sim=" + sim + ", bindTime=" + bindTime + ", isDefault=" + isDefault
				+ ", status=" + status + ", member=" + member + "]";
	}
	
	
	
	
}