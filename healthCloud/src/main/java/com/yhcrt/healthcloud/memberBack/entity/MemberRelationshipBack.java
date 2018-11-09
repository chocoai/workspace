package com.yhcrt.healthcloud.memberBack.entity;

public class MemberRelationshipBack {
    private Integer cid;

    private Integer memberId;

    private Integer followerId;

    private String relationDesc;

    private String createTime;

    private Integer status;
    
    private MemberBack member;
    
    private MemberBack follow;

    public MemberBack getMember() {
        return member;
    }

    public void setMember(MemberBack member) {
        this.member = member;
    }

    public MemberBack getFollow() {
        return follow;
    }

    public void setFollow(MemberBack follow) {
        this.follow = follow;
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
        this.relationDesc = relationDesc == null ? null : relationDesc.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}