package com.yhcrt.entity.queryStatistical;

public class ThreeCondition {
	private int ranking;
	private String unitName;
	private String gold;
	private String silver;
	private String copper;
	private String total;
	private Integer exp2;//奖励金牌
	
	public int getRanking() {
		return ranking;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
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
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public Integer getExp2() {
		return exp2;
	}
	public void setExp2(Integer exp2) {
		this.exp2 = exp2;
	}
	
}