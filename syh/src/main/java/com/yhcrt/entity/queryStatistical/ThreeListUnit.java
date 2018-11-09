package com.yhcrt.entity.queryStatistical;

public class ThreeListUnit extends ThreeList{
	
	private String unitCid;//单位主键

	public String getUnitCid() {
		return unitCid;
	}

	public void setUnitCid(String unitCid) {
		this.unitCid = unitCid;
	}

	@Override
	public String toString() {
		return "ThreeListProject [unitCid=" + unitCid + ", getUnitCid()=" + getUnitCid() + ", getIndex()=" + getIndex()
				+ ", getGold()=" + getGold() + ", getSilver()=" + getSilver() + ", getCopper()=" + getCopper()
				+ ", getIntrgral()=" + getIntrgral() + ", getTotal()=" + getTotal() + ", getUnitName()=" + getUnitName()
				+ ", getCarryGold()=" + getCarryGold() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	
	
}
