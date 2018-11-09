package com.whty.assis.sysrole.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.base.service.BaseService;
import com.whty.assis.manage.dao.SysButtonDao;
import com.whty.assis.sysrole.dao.SysModularDao;
import com.whty.assis.sysrole.dao.SysRoleDao;
import com.whty.assis.sysrole.model.SysModular;
import com.whty.assis.sysrole.model.SysRole;
import com.whty.assis.sysrole.service.SysroleService;
import com.whty.common.util.GUIDGenerator;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * 系统用户角色权限
 * 
 * @author zhujg
 *
 */
@Service(value = "sysroleService")
public class SysroleServiceImpl extends BaseService implements SysroleService {
	private static final Logger logger = LoggerFactory.getLogger(PageContext.class);

	@Autowired
	private SysModularDao sysModularDao;

	@Autowired
	private SysRoleDao sysRoleDao;

	@Autowired
	private SysButtonDao sysButtonDao;

	@Override
	public List<SysModular> queryUserSysModularList(Map map) {
		return sysModularDao.listByCondition(map);
	}

	@Override
	public HandlerResult querySysRole(Map map) {
		logger.info("queryPage:" + map);
		HandlerResult rs = new HandlerResult();
		rs.setResultList(sysRoleDao.querySysRole(map));
		return rs;
	}

	@Override
	public void addSysRole(SysRole sysRole) {
		sysRoleDao.addSysRole(sysRole);
	}

	@Override
	public void updateSysRole(SysRole sysRole) {
		sysRoleDao.updateSysRole(sysRole);
	}

	@Override
	public void updateSysRoleStatus(Map paramap) {
		sysRoleDao.updateSysRoleStatus(paramap);
	}

	@Override
	public void deleteSysRole(Map paramap) {
		sysRoleDao.deleteSysRole(paramap);
	}

	@Override
	public List<Map> queryModularByRoleId(String role_id) {
		return sysRoleDao.queryModularByRoleId(role_id);
	}

	@Override
	public List<Map> queryButtonByRoleId(Map paramap) {
		return sysRoleDao.queryButtonByRoleId(paramap);
	}

	/**
	 * 先删除角色对应的模块和按钮的权限，后插入编辑的权限
	 */
	@Override
	public void saveRolePermission(String role_id, List roleModularList, List roleButtonList) {
		if (StringUtils.isNoneEmpty(role_id)) {
			sysRoleDao.deleteSysRoleButton(role_id);
			sysRoleDao.deleteSysRoleModular(role_id);
		}
		if (roleModularList != null && roleModularList.size() > 0) {
			sysRoleDao.saveSysRoleModularBatch(roleModularList);
		}
		if (roleButtonList != null && roleButtonList.size() > 0) {
			sysRoleDao.saveSysRoleButtonBatch(roleButtonList);
		}
	}

	@Override
	public List<Map> querySysRoleByUserId(String user_id) {
		if (StringUtils.isEmpty(user_id)) {
			return sysRoleDao.queryAllSysRole();
		} else {
			return sysRoleDao.querySysRoleByUserId(user_id);
		}
	}

	/**
	 * 先删除用户所有的角色，后插入编辑的角色
	 */
	@Override
	public void grantRole(List<String> userIdList, List<String> roleIdList) {
		if (userIdList != null && userIdList.size() > 0) {
			sysRoleDao.deleteSysUserRole(userIdList);

			if (roleIdList != null && roleIdList.size() > 0) {
				List list = new ArrayList();
				for (String user_id : userIdList) {
					for (String role_id : roleIdList) {
						Map map = new HashMap();
						map.put("id", GUIDGenerator.getGUID());
						map.put("user_id", user_id);
						map.put("role_id", role_id);
						list.add(map);
					}
				}
				sysRoleDao.saveSysUserRoleBatch(list);
			}
		}

	}

	/**
	 * 查询当前用户的在当前模块的按钮列表
	 */
	@Override
	public List<Map> queryButtonByCurrUser(Map paramap) {
		return sysRoleDao.queryButtonByCurrUser(paramap);
	}

	@Override
	public List<String> queryButtonNamesByUserId(Map<String, Object> paramap) {
		return sysButtonDao.queryButtonNamesByUserId(paramap);
	}

}
