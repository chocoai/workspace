package com.yhcrt.iHealthCloud.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.yhcrt.iHealthCloud.util.StringUtil;

public class Goods {
    private Integer goodsId;

    private String goodsCategory;
    
    private String goodsType;

    private String goodsName;

    private String titleImg;

    private String goodsIntro;

    private Double originalPrice;

    private Double unitPrice;

    private Double discount;

    private String unit;

    private Integer score;

    private Integer stockAmount;

    private String createTime;

    private String createUser;

    private String updateTime;

    private String remark;

    private Integer status;//0为上架，1为下架,根据常量STATUS_XXX来定

    @JSONField(serialize = false)
    private String cext1;

    @JSONField(serialize = false)
    private String cext2;

    @JSONField(serialize = false)
    private String cext3;

    private Integer iext1;   //小程序专用 0不推荐 1推荐

    private Integer iext2;

    @JSONField(serialize = false)
    private Date dext1;

    @JSONField(serialize = false)
    private Date dext2;

    private String content;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsCategory() {
        return goodsCategory == null ? "" : goodsCategory.trim();
    }

    public void setGoodsCategory(String goodsCategory) {
        this.goodsCategory = goodsCategory == null ? null : goodsCategory.trim();
    }

    public String getGoodsName() {
        return goodsName == null ? "" : goodsName.trim();
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getTitleImg() {
        return titleImg == null ? "" : titleImg.trim();
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg == null ? null : titleImg.trim();
    }

    public String getGoodsIntro() {
        return goodsIntro == null ? "" : goodsIntro.trim();
    }

    public void setGoodsIntro(String goodsIntro) {
        this.goodsIntro = goodsIntro == null ? null : goodsIntro.trim();
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getUnit() {
        return unit == null ? "" : unit.trim();
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getStockAmount() {
        return stockAmount;
    }

    public void setStockAmount(Integer stockAmount) {
        this.stockAmount = stockAmount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getCreateUser() {
        return createUser == null ? "" : createUser.trim();
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public String getRemark() {
        return remark == null ? "" : remark.trim();
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
    
    public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getGoodsIntroSub() {
    	return StringUtil.overSubstr(getGoodsIntro(), 14);
    }
	
	public String getGoodsNameSub() {
    	return StringUtil.overSubstr(getGoodsName(), 6);
    }
}