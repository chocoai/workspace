package com.yhcrt.healthcloud.system.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhcrt.healthcloud.common.Constants;
import com.yhcrt.healthcloud.system.entity.UserRole;
import com.yhcrt.healthcloud.system.entity.UserRoleExample;
import com.yhcrt.healthcloud.system.mapper.UserRoleMapper;
import com.yhcrt.healthcloud.system.service.SysSequenceService;
import com.yhcrt.healthcloud.system.service.UserRoleService;
import com.yhcrt.healthcloud.util.DateUtil;

/* @Description: 系统用户角色关联SERVICE实现
 * @version	1.0		2017年6月14日
 * @author jimmy
*/
@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private SysSequenceService sysSequenceService;
    
    /* 查询该用户的所有角色
     * @see com.yhcrt.healthcloud.system.service.UserRoleService#selAll(java.lang.Integer)
     */
    @Override
    public List<UserRole> selAll(Integer userId) {
        UserRoleExample example = new UserRoleExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return userRoleMapper.selectByExample(example);
    }

    /* 添加记录
     * @see com.yhcrt.healthcloud.system.service.UserRoleService#insert(com.yhcrt.healthcloud.system.entity.UserRole)
     */
    @Override
    public int insert(UserRole userRole) {
        return userRoleMapper.insert(userRole);
    }

    /* 删除当前用户的所有角色记录
     * @see com.yhcrt.healthcloud.system.service.UserRoleService#delete(com.yhcrt.healthcloud.system.entity.UserRole)
     */
    @Override
    public int delete(Integer userId) {
        UserRoleExample example = new UserRoleExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return userRoleMapper.deleteByExample(example);
    }

    /* 事务控制重新分配角色
     * @see com.yhcrt.healthcloud.system.service.UserRoleService#update(java.lang.Integer[], java.lang.Integer)
     */
    @Override
    public void update(Integer roleId, Integer userId) {
    	UserRole userRole = new UserRole();
    	//根据userId查询是否有权限
        int count = userRoleMapper.countByUid(userId);
        if(count > 0){
        	//表示修改
        	userRole.setUserId(userId);
        	userRole.setRoleId(roleId);
        	userRoleMapper.updateByUid(userRole);
        }else{
            userRole.setCid(sysSequenceService.getSequenceValue(Constants.SequenceName.USER_ROLE));
            userRole.setRoleId(roleId);
            userRole.setCreateTime(DateUtil.getDateTime());
            userRole.setUserId(userId);
            insert(userRole);
        }
    }

    //根据userId查询roleId集合
	@Override
	public List<Integer> queryByUid(Integer userId) {
		return userRoleMapper.queryByUid(userId);
	}

}
