package com.yhcrt.entity;

public class Tree2 extends Tree{
	
	private boolean isParent;//是否展开

	
	public Tree2(){
		this.isParent = false;
	}
	
	public boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}
	

}
