package com.yhcrt.demo.service.impl;

import java.util.List;

import javax.annotation.Resource;






import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhcrt.demo.dao.RoleAuthorityDao;
import com.yhcrt.demo.model.RoleAuthority;
import com.yhcrt.demo.service.RoleAuthorityService;

/**
 * @author fengkun
 * @email 231788364@qq.com
 */
@Service
public class RoleAuthorityServiceImpl implements RoleAuthorityService {

	@Autowired
	private RoleAuthorityDao roleAuthorityDao;

	public List<RoleAuthority> queryByRole(Short role){
    	return roleAuthorityDao.queryByRole(role);
    }
    
    public void deleteByRole(Short role){
    	roleAuthorityDao.deleteByRole(role);
    }
    
    public void insert(RoleAuthority roleAuthority){
    	roleAuthorityDao.insert(roleAuthority);
    }
	

}
