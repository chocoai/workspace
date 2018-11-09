package com.yhcrt.iHealthCloud.entity;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.annotation.JSONField;
import com.yhcrt.iHealthCloud.common.Constants;

public class Doctor {
    private Integer docId;

    private Integer userId;

    private Integer orgId;

    private String realName;

    private String specialty;

    private String identityCard;
    
    @JSONField(serialize=false)
    private Integer sex;

    private String phoneNo;

    private String email;

    private String identityPhoto;

    @JSONField(serialize=false)
    private String createUser;

    @JSONField(serialize=false)
    private String createTime;

    @JSONField(serialize=false)
    private String updateTime;
    
	private String nickName;

	private String headPic;

	@JSONField(serialize=false)
    private Integer status;

	@JSONField(serialize=false)
    private String remark;
	
	@JSONField(serialize=false)
	private Organization org;
	
	private String orgName;
	
	@JSONField(serialize=false)
	private SysUser user;
	
	private String userAccount;
	
    public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getRealName() {
		if (StringUtils.isNotBlank(this.realName)) {
			return realName;
		} else {
			return "";
		}
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? "" : realName.trim();
    }

    public String getSpecialty() {
		if (StringUtils.isNotBlank(this.specialty)) {
			return specialty;
		} else {
			return "";
		}
    }
    

    public void setSpecialty(String specialty) {
        this.specialty = specialty == null ? "" : specialty.trim();
    }

    public String getIdentityCard() {
		if (StringUtils.isNotBlank(this.identityCard)) {
			return identityCard;
		} else {
			return "";
		}
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard == null ? "" : identityCard.trim();
    }

    public Integer getSex() {
        return sex;
    }
    
    public String getGender(){
    	if (null != getSex() && getSex() == 0) {
			return Constants.Gender.FEMALE;
		}else{
			return Constants.Gender.MALE;
		}
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhoneNo() {
		if (StringUtils.isNotBlank(this.phoneNo)) {
			return phoneNo;
		} else {
			return "";
		}
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo == null ? "" : phoneNo.trim();
    }

    public String getEmail() {
		if (StringUtils.isNotBlank(this.email)) {
			return email;
		} else {
			return "";
		}
    }

    public void setEmail(String email) {
        this.email = email == null ? "" : email.trim();
    }

    public String getIdentityPhoto() {
		if (StringUtils.isNotBlank(this.identityPhoto)) {
			return identityPhoto;
		} else {
			return "";
		}
    }

    public void setIdentityPhoto(String identityPhoto) {
        this.identityPhoto = identityPhoto == null ? "" : identityPhoto.trim();
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
    
	public String getNickName() {
		if (StringUtils.isNotBlank(this.nickName)) {
			return nickName;
		} else {
			return "";
		}
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadPic() {
		if (StringUtils.isNotBlank(this.headPic)) {
			return headPic;
		} else {
			return "";
		}
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	public String getOrgName() {
		if (getOrg()!=null) {
			orgName = getOrg().getOrgName();
		}
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
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
	
	
}