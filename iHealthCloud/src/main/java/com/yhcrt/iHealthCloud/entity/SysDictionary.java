package com.yhcrt.iHealthCloud.entity;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.annotation.JSONField;

public class SysDictionary {
	
	@JSONField(serialize=false)
    private Integer dictId;

	@JSONField(serialize=false)
    private String parentId;

    private String dictZhName;

    private String dictEnName;

    private String dictKey;

    private String dictValue;

    @JSONField(serialize=false)
    private String dictDesc;

    private String dictIcon;

    @JSONField(serialize=false)
    private String createTime;

    @JSONField(serialize=false)
    private String createUser;

    @JSONField(serialize=false)
    private Integer orderNum;

    @JSONField(serialize=false)
    private Integer status;

    @JSONField(serialize=false)
    private String remark;

    public Integer getDictId() {
        return dictId;
    }

    public void setDictId(Integer dictId) {
        this.dictId = dictId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getDictZhName() {
        return dictZhName;
    }

    public void setDictZhName(String dictZhName) {
        this.dictZhName = dictZhName == null ? null : dictZhName.trim();
    }

    public String getDictEnName() {
        return dictEnName;
    }

    public void setDictEnName(String dictEnName) {
        this.dictEnName = dictEnName == null ? null : dictEnName.trim();
    }

    public String getDictKey() {
        return dictKey;
    }
    
    public String getKey() {
    	if (StringUtils.isNotBlank(getDictKey()) && getDictKey().length() > 4) {
			return getDictKey().substring(0, 4);
		}
    	return getDictKey();
    }

    public void setDictKey(String dictKey) {
        this.dictKey = dictKey == null ? null : dictKey.trim();
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue == null ? null : dictValue.trim();
    }

    public String getDictDesc() {
        return dictDesc;
    }

    public void setDictDesc(String dictDesc) {
        this.dictDesc = dictDesc == null ? null : dictDesc.trim();
    }

    public String getDictIcon() {
        return dictIcon;
    }

    public void setDictIcon(String dictIcon) {
        this.dictIcon = dictIcon == null ? null : dictIcon.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
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
}