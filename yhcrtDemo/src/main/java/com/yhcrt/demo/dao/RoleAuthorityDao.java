package com.yhcrt.demo.dao;

import java.util.List;

import com.yhcrt.demo.model.RoleAuthority;

/**
 * @author fengkun
 * @email 231788364@qq.com
 */
public interface RoleAuthorityDao{
	public List<RoleAuthority> queryByRole(Short role);
    
    public void deleteByRole(Short role);
    
    public void insert(RoleAuthority roleAuthority);
}
