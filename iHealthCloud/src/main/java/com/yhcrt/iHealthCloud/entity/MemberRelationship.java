package com.yhcrt.iHealthCloud.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class MemberRelationship {
	
	@JSONField(serialize = false)
    private Integer cid;

    private Integer memberId;

    private Integer followerId;

    private String relationDesc;

    private String createTime;

    @JSONField(serialize = false)
    private Integer status;
    
    @JSONField(serialize = false)
    private Member member;
    
    @JSONField(serialize = false)
    private Member follower;

    public Member getFollower() {
		return follower;
	}

	public void setFollower(Member follower) {
		this.follower = follower;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

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

    public Integer getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Integer followerId) {
        this.followerId = followerId;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    
}