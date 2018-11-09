package com.yhcrt.demo.service.impl;

import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhcrt.demo.dao.AuthorityDao;
import com.yhcrt.demo.model.Authority;
import com.yhcrt.demo.model.RoleAuthority;
import com.yhcrt.demo.service.AuthorityService;
import com.yhcrt.demo.util.QueryResult;

/**
 * @author fengkun
 * @email 231788364@qq.com
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	private AuthorityDao authorityDao;

	@Override
	public List<Authority> queryByRole(Short role) {
		return authorityDao.queryByRole(role);
	}

	@Override
	public List<Authority> queryChildrenByParentIdAndRole(Long parentId, Short role) {
		return authorityDao.queryChildrenByParentIdAndRole(parentId, role);
	}

	@Override
	public String querySurfaceAuthorityList(List<RoleAuthority> queryByProerties, Long id, String buttons) {
		StringBuilder sb = new StringBuilder();
		String[] buttonsArray = buttons.split(",");
		for (RoleAuthority roleAuthority : queryByProerties) {
			if (!isNumeric(roleAuthority.getAuthorityId())) {
				for (int z = 0; z < buttonsArray.length; z++) {
					if ((id + buttonsArray[z]).equalsIgnoreCase(roleAuthority.getAuthorityId())) {
						sb.append(buttonsArray[z] + ",");
					}
				}
			}
		}
		return sb.toString();
	}

	private static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	@Override
	public List<Authority> queryByParentId(Long parentId) {
		return authorityDao.queryByParentId(parentId);
	}

	@Override
	public Authority getByMenuCode(String menuCode) {
		return authorityDao.getByMenuCode(menuCode);
	}

	@Override
	public boolean deleteByPK(Long[] ids) {
		authorityDao.deleteByPK(ids);
		return true;
	}

	@Override
	public void update(Authority authority) {
		authorityDao.update(authority);
	}
	
	@Override
	public void updateBySelected(Authority authority) {
		authorityDao.updateBySelected(authority);
	}

	@Override
	public void insert(Authority authority) {
		authorityDao.insert(authority);
	}

	@Override
	public QueryResult<Authority> doPaginationQuery(Authority authority) {
		List<Authority> list = authorityDao.doPaginationQuery(authority);
		QueryResult<Authority> result = new QueryResult<Authority>();
		result.setResultList(list);
		result.setTotalCount(list.size()+0l);
		return result;
	}
	
	

}
