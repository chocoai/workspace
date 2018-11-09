package com.yhcrt.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yhcrt.demo.model.Authority;

/**
 * @author fengkun
 * @email 231788364@qq.com
 */
public interface AuthorityDao  {

	List<Authority> queryByRole(Short role);

	List<Authority> queryChildrenByParentIdAndRole(@Param("parentId") Long parentId, @Param("role") Short role);

	List<Authority> queryByParentId(@Param("parentId")Long parentId); 
	
	Authority getByMenuCode(String menuCode);
	
	void deleteByPK(Long[] ids);
	
	void update(Authority authority);
	
	void updateBySelected(Authority authority);
	
	void insert(Authority authority);
	
	List<Authority> doPaginationQuery(Authority authority);
}
