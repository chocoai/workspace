package com.yhcrt.healthcloud.memberBack.entity;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.annotation.JSONField;
import com.yhcrt.healthcloud.health.entity.HdPulse;
import com.yhcrt.healthcloud.organization.entity.Organization;

public class MemberDeviceBack {
    private Integer cid;

    private Integer memberId;

    private String imei;

    private Integer orgId;

    private String deviceType;

    private String sim;

    private String bindTime;

    private Integer status;
    
    @JSONField(serialize=false)
    private MemberBack member;
    
    private Organization org;
    
    private HdPulse hdPulse;

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

	public MemberBack getMember() {
		return member;
	}

	public void setMember(MemberBack member) {
		this.member = member;
	}
    
    public String getMemberName() {
		String memberName = getMember().getRealName();
		if (member != null) {
			return StringUtils.isBlank(memberName) ? getMember().getNickName() : memberName;
		}
		return "";
    }

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public HdPulse getHdPulse() {
		return hdPulse;
	}

	public void setHdPulse(HdPulse hdPulse) {
		this.hdPulse = hdPulse;
	}
    
    
}