package com.yhcrt.healthcloud.mall.entity;

import java.util.Date;

public class UserComment {
    private Integer commentId;

    private Integer refId;

    private String commentType;

    private Integer stars;

    private String memberId;

    private Integer content;

    private Date createTime;

    private Integer status;

    private String remark;

    private String cext1;

    private String cext2;

    private String cext3;

    private Integer iext1;

    private Integer iext2;

    private Date dext1;

    private Date dext2;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getRefId() {
        return refId;
    }

    public void setRefId(Integer refId) {
        this.refId = refId;
    }

    public String getCommentType() {
        return commentType;
    }

    public void setCommentType(String commentType) {
        this.commentType = commentType == null ? null : commentType.trim();
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    public Integer getContent() {
        return content;
    }

    public void setContent(Integer content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCext1() {
        return cext1;
    }

    public void setCext1(String cext1) {
        this.cext1 = cext1 == null ? null : cext1.trim();
    }

    public String getCext2() {
        return cext2;
    }

    public void setCext2(String cext2) {
        this.cext2 = cext2 == null ? null : cext2.trim();
    }

    public String getCext3() {
        return cext3;
    }

    public void setCext3(String cext3) {
        this.cext3 = cext3 == null ? null : cext3.trim();
    }

    public Integer getIext1() {
        return iext1;
    }

    public void setIext1(Integer iext1) {
        this.iext1 = iext1;
    }

    public Integer getIext2() {
        return iext2;
    }

    public void setIext2(Integer iext2) {
        this.iext2 = iext2;
    }

    public Date getDext1() {
        return dext1;
    }

    public void setDext1(Date dext1) {
        this.dext1 = dext1;
    }

    public Date getDext2() {
        return dext2;
    }

    public void setDext2(Date dext2) {
        this.dext2 = dext2;
    }
}