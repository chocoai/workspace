package com.yhcrt.healthcloud.system.service;

import java.util.List;

import com.yhcrt.healthcloud.system.entity.UserRole;

/* @Description: 系统用户角色关联表
 * @version	1.0		2017年6月14日
 * @author jimmy
*/
public interface UserRoleService {

    List<UserRole> selAll(Integer userId);
    
    int insert(UserRole userRole);
    
    int delete(Integer userId);
    
    void update(Integer roleId,Integer userId);

    //根据userId查询roleId集合
	List<Integer> queryByUid(Integer userId);
    
}
