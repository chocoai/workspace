package com.yhcrt.demo.model;

import java.util.Date;

public class YwOrder {
    /**
     * 主键
     */
    private Integer orderId;

    /**
     * 会员编号
     */
    private Integer memberId;

    /**
     * 订单总价
     */
    private Double orderAmount;

    /**
     * 运费
     */
    private Double freight;

    /**
     * 优惠额
     */
    private Double discounts;

    /**
     * 实际付款
     */
    private Double actualPayment;

    /**
     * 收货人
     */
    private String recipient;

    /**
     * 联系电话
     */
    private String phoneNum;

    /**
     * 收货地址
     */
    private String address;

    /**
     * 收货地址
     */
    private Integer addressId;

    /**
     * 订单状态(0待支付，1待发货，2待收货，3待评价,4已完成 -1取消订单)
     */
    private Integer orderStatus;

    /**
     * 下单时间
     */
    private String createTime;

    /**
     * 支付类型(small 小程序 /wechat 微信app支付/alipay支付宝app支付/ alipay_pc  支付宝网页/wechat_h5 微信H5支付/alipay_h5 支付宝H5支付/wechat_gzh 微信公众号支付/wechat_pc PC端微信扫码)
     */
    private String payType;

    /**
     * 交易号
     */
    private String payAccount;

    /**
     * 支付金额
     */
    private Double payValue;

    /**
     * 支付时间
     */
    private String payTime;

    /**
     * 物流公司
     */
    private String logistics;

    /**
     * 货运单号
     */
    private String waybill;

    /**
     * 工单编号
     */
    private String cext1;

    /**
     * 与支付宝,微信交易号
     */
    private String cext2;

    /**
     * 
     */
    private String cext3;

    /**
     * 备用4
     */
    private Integer iext1;

    /**
     * 备用5
     */
    private Integer iext2;

    /**
     * 备用6
     */
    private Date dext1;

    /**
     * 备用7
     */
    private Date dext2;

    /**
     * 主键
     * @return order_id 主键
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 主键
     * @param orderId 主键
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 会员编号
     * @return member_id 会员编号
     */
    public Integer getMemberId() {
        return memberId;
    }

    /**
     * 会员编号
     * @param memberId 会员编号
     */
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * 订单总价
     * @return order_amount 订单总价
     */
    public Double getOrderAmount() {
        return orderAmount;
    }

    /**
     * 订单总价
     * @param orderAmount 订单总价
     */
    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    /**
     * 运费
     * @return freight 运费
     */
    public Double getFreight() {
        return freight;
    }

    /**
     * 运费
     * @param freight 运费
     */
    public void setFreight(Double freight) {
        this.freight = freight;
    }

    /**
     * 优惠额
     * @return discounts 优惠额
     */
    public Double getDiscounts() {
        return discounts;
    }

    /**
     * 优惠额
     * @param discounts 优惠额
     */
    public void setDiscounts(Double discounts) {
        this.discounts = discounts;
    }

    /**
     * 实际付款
     * @return actual_payment 实际付款
     */
    public Double getActualPayment() {
        return actualPayment;
    }

    /**
     * 实际付款
     * @param actualPayment 实际付款
     */
    public void setActualPayment(Double actualPayment) {
        this.actualPayment = actualPayment;
    }

    /**
     * 收货人
     * @return recipient 收货人
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * 收货人
     * @param recipient 收货人
     */
    public void setRecipient(String recipient) {
        this.recipient = recipient == null ? null : recipient.trim();
    }

    /**
     * 联系电话
     * @return phone_num 联系电话
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * 联系电话
     * @param phoneNum 联系电话
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    /**
     * 收货地址
     * @return address 收货地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 收货地址
     * @param address 收货地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 收货地址
     * @return address_id 收货地址
     */
    public Integer getAddressId() {
        return addressId;
    }

    /**
     * 收货地址
     * @param addressId 收货地址
     */
    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    /**
     * 订单状态(0待支付，1待发货，2待收货，3待评价,4已完成 -1取消订单)
     * @return order_status 订单状态(0待支付，1待发货，2待收货，3待评价,4已完成 -1取消订单)
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * 订单状态(0待支付，1待发货，2待收货，3待评价,4已完成 -1取消订单)
     * @param orderStatus 订单状态(0待支付，1待发货，2待收货，3待评价,4已完成 -1取消订单)
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 下单时间
     * @return create_time 下单时间
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 下单时间
     * @param createTime 下单时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    /**
     * 支付类型(small 小程序 /wechat 微信app支付/alipay支付宝app支付/ alipay_pc  支付宝网页/wechat_h5 微信H5支付/alipay_h5 支付宝H5支付/wechat_gzh 微信公众号支付/wechat_pc PC端微信扫码)
     * @return pay_type 支付类型(small 小程序 /wechat 微信app支付/alipay支付宝app支付/ alipay_pc  支付宝网页/wechat_h5 微信H5支付/alipay_h5 支付宝H5支付/wechat_gzh 微信公众号支付/wechat_pc PC端微信扫码)
     */
    public String getPayType() {
        return payType;
    }

    /**
     * 支付类型(small 小程序 /wechat 微信app支付/alipay支付宝app支付/ alipay_pc  支付宝网页/wechat_h5 微信H5支付/alipay_h5 支付宝H5支付/wechat_gzh 微信公众号支付/wechat_pc PC端微信扫码)
     * @param payType 支付类型(small 小程序 /wechat 微信app支付/alipay支付宝app支付/ alipay_pc  支付宝网页/wechat_h5 微信H5支付/alipay_h5 支付宝H5支付/wechat_gzh 微信公众号支付/wechat_pc PC端微信扫码)
     */
    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    /**
     * 交易号
     * @return pay_account 交易号
     */
    public String getPayAccount() {
        return payAccount;
    }

    /**
     * 交易号
     * @param payAccount 交易号
     */
    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount == null ? null : payAccount.trim();
    }

    /**
     * 支付金额
     * @return pay_value 支付金额
     */
    public Double getPayValue() {
        return payValue;
    }

    /**
     * 支付金额
     * @param payValue 支付金额
     */
    public void setPayValue(Double payValue) {
        this.payValue = payValue;
    }

    /**
     * 支付时间
     * @return pay_time 支付时间
     */
    public String getPayTime() {
        return payTime;
    }

    /**
     * 支付时间
     * @param payTime 支付时间
     */
    public void setPayTime(String payTime) {
        this.payTime = payTime == null ? null : payTime.trim();
    }

    /**
     * 物流公司
     * @return logistics 物流公司
     */
    public String getLogistics() {
        return logistics;
    }

    /**
     * 物流公司
     * @param logistics 物流公司
     */
    public void setLogistics(String logistics) {
        this.logistics = logistics == null ? null : logistics.trim();
    }

    /**
     * 货运单号
     * @return waybill 货运单号
     */
    public String getWaybill() {
        return waybill;
    }

    /**
     * 货运单号
     * @param waybill 货运单号
     */
    public void setWaybill(String waybill) {
        this.waybill = waybill == null ? null : waybill.trim();
    }

    /**
     * 工单编号
     * @return cext1 工单编号
     */
    public String getCext1() {
        return cext1;
    }

    /**
     * 工单编号
     * @param cext1 工单编号
     */
    public void setCext1(String cext1) {
        this.cext1 = cext1 == null ? null : cext1.trim();
    }

    /**
     * 与支付宝,微信交易号
     * @return cext2 与支付宝,微信交易号
     */
    public String getCext2() {
        return cext2;
    }

    /**
     * 与支付宝,微信交易号
     * @param cext2 与支付宝,微信交易号
     */
    public void setCext2(String cext2) {
        this.cext2 = cext2 == null ? null : cext2.trim();
    }

    /**
     * 
     * @return cext3 
     */
    public String getCext3() {
        return cext3;
    }

    /**
     * 
     * @param cext3 
     */
    public void setCext3(String cext3) {
        this.cext3 = cext3 == null ? null : cext3.trim();
    }

    /**
     * 备用4
     * @return iext1 备用4
     */
    public Integer getIext1() {
        return iext1;
    }

    /**
     * 备用4
     * @param iext1 备用4
     */
    public void setIext1(Integer iext1) {
        this.iext1 = iext1;
    }

    /**
     * 备用5
     * @return iext2 备用5
     */
    public Integer getIext2() {
        return iext2;
    }

    /**
     * 备用5
     * @param iext2 备用5
     */
    public void setIext2(Integer iext2) {
        this.iext2 = iext2;
    }

    /**
     * 备用6
     * @return dext1 备用6
     */
    public Date getDext1() {
        return dext1;
    }

    /**
     * 备用6
     * @param dext1 备用6
     */
    public void setDext1(Date dext1) {
        this.dext1 = dext1;
    }

    /**
     * 备用7
     * @return dext2 备用7
     */
    public Date getDext2() {
        return dext2;
    }

    /**
     * 备用7
     * @param dext2 备用7
     */
    public void setDext2(Date dext2) {
        this.dext2 = dext2;
    }
}