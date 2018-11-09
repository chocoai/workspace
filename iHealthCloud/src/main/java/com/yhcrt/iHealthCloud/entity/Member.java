package com.yhcrt.iHealthCloud.entity;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.annotation.JSONField;
import com.yhcrt.iHealthCloud.common.Constants;

public class Member {
	
    private Integer memberId;

    private String headPic;

    private String realName;

    private Integer gender;

    private String nickName;

    private String identityCard;

    private String phoneNo;

    private String email;

    @JSONField(serialize=false)
    private String createUser;

    @JSONField(serialize=false)
    private String createTime;

    @JSONField(serialize=false)
    private String updateTime;

    private Integer memberType;

    @JSONField(serialize=false)
    private Integer orgId;

    private Integer userId;

    @JSONField(serialize=false)
    private Integer empId;

    @JSONField(serialize=false)
    private Integer docId;

    @JSONField(serialize=false)
    private Integer status;

    @JSONField(serialize=false)
    private String remark;
    
    @JSONField(serialize=false)
    private Employee emp;
    
    @JSONField(serialize=false)
    private Doctor doctor;
    
    @JSONField(serialize=false)
    private Organization org;
    
    @JSONField(serialize=false)
    private SysUser user;
    
    private String orgName;
    
    private String userAccount;
    
    private String wxGzhOpenId;//微信公众号OpenId

	public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getHeadPic() {
    	if (StringUtils.isNotBlank(this.headPic)) {
			return headPic;
		}else{
			return "";
		}
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic == null ? "" : headPic.trim();
    }

    public String getRealName() {
    	if (StringUtils.isNotBlank(this.realName)) {
			return realName;
		}else{
			return "";
		}
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? "" : realName.trim();
    }

    public Integer getGender() {
        return gender;
    }
    
    public String getGenderView(){
    	if (null != getGender() && getGender() == 0) {
			return Constants.Gender.FEMALE;
		}else{
			return Constants.Gender.MALE;
		}
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getNickName() {
    	if (StringUtils.isNotBlank(this.nickName)) {
			return nickName;
		}else{
			return "";
		}
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? "" : nickName.trim();
    }

    public String getIdentityCard() {
    	if (StringUtils.isNotBlank(this.identityCard)) {
			return identityCard;
		}else{
			return "";
		}
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard == null ? "" : identityCard.trim();
    }

    public String getPhoneNo() {
    	if (StringUtils.isNotBlank(this.phoneNo)) {
			return phoneNo;
		}else{
			return "";
		}
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo == null ? "" : phoneNo.trim();
    }

    public String getEmail() {
    	if (StringUtils.isNotBlank(this.email)) {
			return email;
		}else{
			return "";
		}
    }

    public void setEmail(String email) {
        this.email = email == null ? "" : email.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? "" : createUser.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? "" : createTime.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? "" : updateTime.trim();
    }

    public Integer getMemberType() {
        return memberType;
    }

    public void setMemberType(Integer memberType) {
        this.memberType = memberType;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? "" : remark.trim();
    }

    public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}

	public String getWxGzhOpenId() {
		return wxGzhOpenId;
	}

	public void setWxGzhOpenId(String wxGzhOpenId) {
		this.wxGzhOpenId = wxGzhOpenId;
	}

	public String getUserAccount() {
		if (getUser() != null) {
			userAccount = getUser().getUserCode();
		}
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount == null ? "" : userAccount.trim();
	}

	public String getOrgName() {
		if (getOrg() != null) {
			orgName = getOrg().getOrgName();
		}
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName == null ? "" : orgName.trim();
	}
	
	
	
	
}