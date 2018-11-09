package com.whty.assis.sysres.model;

import java.io.Serializable;
import java.util.List;


public class SysModular implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 主键
     */
    private Integer id;

    /**
     * 模块名称
     */
    private String modularName;

    /**
     * 模块地址
     */
    private String modularPath;

    /**
     * 模块排序
     */
    private Integer modularSort;

    /**
     * 上级模块ID
     */
    private String parentId;

    /**
     * 0父节点，1叶子
     */
    private Integer isParent;

    /**
     * 按钮ID（会有多个ID）
     */
    private String buttons;

    /**
     * 0有效，1无效
     */
    private Integer status;

    /**
     * 图片样式
     */
    private String imgcss;
    
    private String parentName;
    
    private List<SysModular> modulars;
    
    public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public List<SysModular> getModulars() {
		return modulars;
	}

	public void setSysModular(List<SysModular> modulars) {
		this.modulars = modulars;
	}

    /**
     * 主键
     * @return id 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 模块名称
     * @return modular_name 模块名称
     */
    public String getModularName() {
        return modularName;
    }

    /**
     * 模块名称
     * @param modularName 模块名称
     */
    public void setModularName(String modularName) {
        this.modularName = modularName == null ? null : modularName.trim();
    }

    /**
     * 模块地址
     * @return modular_path 模块地址
     */
    public String getModularPath() {
        return modularPath;
    }

    /**
     * 模块地址
     * @param modularPath 模块地址
     */
    public void setModularPath(String modularPath) {
        this.modularPath = modularPath == null ? null : modularPath.trim();
    }

    /**
     * 模块排序
     * @return modular_sort 模块排序
     */
    public Integer getModularSort() {
        return modularSort;
    }

    /**
     * 模块排序
     * @param modularSort 模块排序
     */
    public void setModularSort(Integer modularSort) {
        this.modularSort = modularSort;
    }

    /**
     * 上级模块ID
     * @return parent_id 上级模块ID
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 上级模块ID
     * @param parentId 上级模块ID
     */
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    /**
     * 0父节点，1叶子
     * @return is_parent 0父节点，1叶子
     */
    public Integer getIsParent() {
        return isParent;
    }

    /**
     * 0父节点，1叶子
     * @param isParent 0父节点，1叶子
     */
    public void setIsParent(Integer isParent) {
        this.isParent = isParent;
    }

    /**
     * 按钮ID（会有多个ID）
     * @return buttons 按钮ID（会有多个ID）
     */
    public String getButtons() {
        return buttons;
    }

    /**
     * 按钮ID（会有多个ID）
     * @param buttons 按钮ID（会有多个ID）
     */
    public void setButtons(String buttons) {
        this.buttons = buttons == null ? null : buttons.trim();
    }

    /**
     * 0有效，1无效
     * @return status 0有效，1无效
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 0有效，1无效
     * @param status 0有效，1无效
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 图片样式
     * @return imgcss 图片样式
     */
    public String getImgcss() {
        return imgcss;
    }

    /**
     * 图片样式
     * @param imgcss 图片样式
     */
    public void setImgcss(String imgcss) {
        this.imgcss = imgcss == null ? null : imgcss.trim();
    }
}