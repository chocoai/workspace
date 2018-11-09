package com.yhcrt.iHealthCloud.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.yhcrt.iHealthCloud.util.Arith;

public class OrderDetail {
	
	private Integer cid;

	private Integer orderId;

	private Integer goodsId;

	private Double price;

	private Integer amount;

	private Double totalPay;

	private Double actualPayment;

	private String createTime;

	private Integer status; // -1结束，1为已发货（待收货），2为待发货（已支付），3为待支付

	@JSONField(serialize = false)
	private Goods goods;

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Double getTotalPay() {
		return totalPay;
	}

	public void setTotalPay(Double totalPay) {
		this.totalPay = totalPay;
	}

	public Double getActualPayment() {
		return actualPayment;
	}

	public void setActualPayment(Double actualPayment) {
		this.actualPayment = actualPayment;
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

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public String getGoodsName() {
		if (getGoods() != null) {
			return getGoods().getGoodsName();
		}
		return "";
	}

	public String getGoodsTitleImg() {
		if (getGoods() != null) {
			return getGoods().getTitleImg();
		}
		return "";
	}

	public Double getDiscount() {
		Double discount = 1d;
		if (getGoods() != null) {
			discount = getGoods().getDiscount();
		}
		return discount;
	}

	public Double getDiscountPrice() {
		if (getGoods() != null) {
			return Arith.round(Arith.mul(getGoods().getUnitPrice(), getDiscount()), 2);
		}
		return 0d;
	}
	
	public String getGoodsUnit() {
		if (getGoods() != null) {
			return getGoods().getUnit();
		}
		return "";
	}
}