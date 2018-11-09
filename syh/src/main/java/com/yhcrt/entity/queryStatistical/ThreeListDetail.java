package com.yhcrt.entity.queryStatistical;

public class ThreeListDetail {

	
	private String unitCid;//单位id
	
	private String unitName;//单位名称
	
	private String classCid;//项目大项的id
	
	private String className;//项目大项的名称
	
	private String gold;//金牌
	
	private String silver;//银牌
	
	private String copper;//铜牌
	
	private String intrgral;//总分
	
	private String total;//奖牌数
	
	
	
	public String getUnitCid() {
		return unitCid;
	}
	public void setUnitCid(String unitCid) {
		this.unitCid = unitCid;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getClassCid() {
		return classCid;
	}
	public void setClassCid(String classCid) {
		this.classCid = classCid;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
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
	@Override
	public String toString() {
		return "ThreeListDetail [unitCid=" + unitCid + ", unitName=" + unitName + ", classCid=" + classCid
				+ ", className=" + className + ", gold=" + gold + ", silver=" + silver + ", copper=" + copper
				+ ", intrgral=" + intrgral + ", total=" + total + "]";
	}
}
