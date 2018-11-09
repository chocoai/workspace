package com.fxzhj.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 暂时用不到这张表
 * @author jifei
 *
 */
public class Recharge implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private Long id;

    //订单编号
    private String orderNo;

    //用户
    private String userAccount;

    //充值时间
    private Date ctime;

    //手机号
    private String phone;

    //支付方式（0：支付宝 1：微信钱包 2其它）
    private Integer type;

    //
    private Float money;

    private Float rechargeMoney;

    private Float giveMoney;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public Float getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(Float rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    public Float getGiveMoney() {
        return giveMoney;
    }

    public void setGiveMoney(Float giveMoney) {
        this.giveMoney = giveMoney;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", userAccount=").append(userAccount);
        sb.append(", ctime=").append(ctime);
        sb.append(", phone=").append(phone);
        sb.append(", type=").append(type);
        sb.append(", money=").append(money);
        sb.append(", rechargeMoney=").append(rechargeMoney);
        sb.append(", giveMoney=").append(giveMoney);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}