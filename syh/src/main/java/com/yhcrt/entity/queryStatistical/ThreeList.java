package com.yhcrt.entity.queryStatistical;

public class ThreeList {

	
	private Integer index;//排名
	private String unitName;//单位名称
	private String intrgral;//积分
	private String gold;//金牌
	private String silver;//银牌
	private String copper;//铜牌
	private String total;//奖牌数
	private Integer carryGold;//输送奖励金牌
	
	
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public String getGold() {
		return gold;
	}
	public void setGold(String gold) {
		this.gold = gold;
	}
	public String getSilver() {
		return silver;
	}
	public void setSilver(String silver) {
		this.silver = silver;
	}
	public String getCopper() {
		return copper;
	}
	public void setCopper(String copper) {
		this.copper = copper;
	}
	public String getIntrgral() {
		return intrgral;
	}
	public void setIntrgral(String intrgral) {
		this.intrgral = intrgral;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public Integer getCarryGold() {
		return carryGold;
	}
	public void setCarryGold(Integer carryGold) {
		this.carryGold = carryGold;
	}
	
}
