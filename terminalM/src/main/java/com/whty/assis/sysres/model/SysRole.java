package com.whty.assis.sysres.model;

public class SysRole {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 描述信息
     */
    private String roleComment;

    /**
     * 角色等级(1.校级；2.区级；3.省级；4.超级管理员)
     */
    private Integer roleLevel;

    /**
     * 排序
     */
    private Integer roleSort;

    /**
     * 状态（0是，1否）
     */
    private Integer status;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

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
     * 角色名称
     * @return role_name 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 角色名称
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    /**
     * 描述信息
     * @return role_comment 描述信息
     */
    public String getRoleComment() {
        return roleComment;
    }

    /**
     * 描述信息
     * @param roleComment 描述信息
     */
    public void setRoleComment(String roleComment) {
        this.roleComment = roleComment == null ? null : roleComment.trim();
    }

    /**
     * 角色等级(1.校级；2.区级；3.省级；4.超级管理员)
     * @return role_level 角色等级(1.校级；2.区级；3.省级；4.超级管理员)
     */
    public Integer getRoleLevel() {
        return roleLevel;
    }

    /**
     * 角色等级(1.校级；2.区级；3.省级；4.超级管理员)
     * @param roleLevel 角色等级(1.校级；2.区级；3.省级；4.超级管理员)
     */
    public void setRoleLevel(Integer roleLevel) {
        this.roleLevel = roleLevel;
    }

    /**
     * 排序
     * @return role_sort 排序
     */
    public Integer getRoleSort() {
        return roleSort;
    }

    /**
     * 排序
     * @param roleSort 排序
     */
    public void setRoleSort(Integer roleSort) {
        this.roleSort = roleSort;
    }

    /**
     * 状态（0是，1否）
     * @return status 状态（0是，1否）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0是，1否）
     * @param status 状态（0是，1否）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }
}