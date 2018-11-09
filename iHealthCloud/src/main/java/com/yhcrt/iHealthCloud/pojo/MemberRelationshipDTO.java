package com.yhcrt.iHealthCloud.pojo;

public class MemberRelationshipDTO {

    private Integer memberId;
    
    private String realName;
    
    private String nickName;
    
    private String headPic;
    
    private String tel;
    
    // 备注名
    private String alias;

    // 关系描述
    private String relationDesc;

    // 关注时间
    private String createTime;

    private String imei;


    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getRelationDesc() {
        return relationDesc;
    }

    public void setRelationDesc(String relationDesc) {
        this.relationDesc = relationDesc == null ? "" : relationDesc.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? "" : createTime.trim();
    }

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei == null ? "" : imei.trim();
	}

	public String getHeadPic() {
		return headPic == null ? "" : headPic.trim();
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	public String getRealName() {
		return realName == null ? "" : realName.trim();
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

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

}