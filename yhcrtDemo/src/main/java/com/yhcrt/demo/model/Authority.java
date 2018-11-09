package com.yhcrt.demo.model;

import com.yhcrt.demo.util.ExtJSBaseParameter;



/**
 * @author fengkun
 * @email 231788364@qq.com
 * @权限管理实体类
 */

public class Authority extends ExtJSBaseParameter  {
	
	private Long id; // ID
	
	private Integer sortOrder; //排序
	
	private String menuCode; // 菜单代号
	
	private String menuName; // 菜单名称
	
	private String menuConfig; //菜单配置参数
	
	private String buttons; // 按钮显示项
	
	private Boolean expanded; //可伸展的
	
	private Boolean checked; //可勾选的
	
	private Boolean leaf; //是否是叶子项
	
	private String url; //创建Tab的路径
	
	private String iconCls; //按钮样式
	
	private Long parentId; //父节点ID

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuConfig() {
		return menuConfig;
	}

	public void setMenuConfig(String menuConfig) {
		this.menuConfig = menuConfig;
	}

	public String getButtons() {
		return buttons;
	}

	public void setButtons(String buttons) {
		this.buttons = buttons;
	}

	public Boolean getExpanded() {
		return expanded;
	}

	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Boolean getLeaf() {
		return leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}



}
