package com.yhcrt.demo.service;

import java.util.List;

import com.yhcrt.demo.model.RoleAuthority;

/**
 * @author fengkun
 * @email 231788364@qq.com
 */
public interface RoleAuthorityService  {
    List<RoleAuthority> queryByRole(Short role);
    
    void deleteByRole(Short role);
    
    void insert(RoleAuthority roleAuthority);
}
