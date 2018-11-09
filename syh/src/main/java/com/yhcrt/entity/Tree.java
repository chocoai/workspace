package com.yhcrt.entity;

public class Tree {
	private int id;//节点id
	private int parentId;//父节点id
	private String nodeValue;//节点名称
	private boolean checked;//是否选中
	
	public  Tree(){
		this.checked = false;
	}
	
	/*********************************************************************/
	/**
	 *  【描述】：0：个人赛；1：团体赛
	 */
	private int isTeam;
	/**
	 *  【描述】：0:分类节点 1:项目节点
	 */
	private int isClass;
	/**
	 *  【描述】：分类类型
	 */
	private int classType;
	/**
	 *  【描述】：0：男；1：女；2：混合
	 */
	private int isSex;
	
	
	public int getIsTeam() {
		return isTeam;
	}
	public void setIsTeam(int isTeam) {
		this.isTeam = isTeam;
	}
	
	
	public int getIsClass() {
		return isClass;
	}
	public void setIsClass(int isClass) {
		this.isClass = isClass;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getNodeValue() {
		return nodeValue;
	}
	public void setNodeValue(String nodeValue) {
		this.nodeValue = nodeValue;
	}
	public boolean getChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	public int getIsSex() {
		return isSex;
	}
	public void setIsSex(int isSex) {
		this.isSex = isSex;
	}
	
	public int getClassType() {
		return classType;
	}
	public void setClassType(int classType) {
		this.classType = classType;
	}
	@Override
	public String toString() {
		return "Tree [id=" + id + ", parentId=" + parentId + ", nodeValue=" + nodeValue + ", checked=" + checked + "]";
	}
	
	@Override
    public int hashCode() {
        return 1;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Tree) {
        	Tree tree = (Tree) obj;
            if (this.id==tree.getId()&& this.parentId==tree.getParentId()&&this.nodeValue.equals(tree.nodeValue)) {
                return true;
            }
        }
        return false;
    }
	
}
