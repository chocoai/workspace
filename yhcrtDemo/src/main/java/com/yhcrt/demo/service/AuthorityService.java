package com.yhcrt.demo.service;

import java.util.List;

import com.yhcrt.demo.model.Authority;
import com.yhcrt.demo.model.RoleAuthority;
import com.yhcrt.demo.util.QueryResult;


/**
 * @author fengkun
 * @email 231788364@qq.com
 */
public interface AuthorityService {

	List<Authority> queryByRole(Short role);

	List<Authority> queryChildrenByParentIdAndRole(Long parentId, Short role);

	String querySurfaceAuthorityList(List<RoleAuthority> queryByProerties, Long id, String buttons);
	
	List<Authority> queryByParentId(Long parentId);
	
	Authority getByMenuCode(String menuCode) ;
	
	boolean deleteByPK(Long[] ids);
	
	void update(Authority authority);
	
	void updateBySelected(Authority authority);
	
	void insert(Authority authority);
	
	QueryResult<Authority> doPaginationQuery(Authority authority);

}
