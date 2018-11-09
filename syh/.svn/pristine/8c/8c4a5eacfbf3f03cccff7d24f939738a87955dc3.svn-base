
package com.yhcrt.service.stsyem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yhcrt.dao.DaoSupport;
import com.yhcrt.utils.StringUtils;

/**
 * 角色关系的servic
 * @author 陈伟
 * 2017年5月23日 下午3:21:24
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Service("sysRoleResService")
public class SysRoleResService {
	@Resource
	private DaoSupport dao;
	/**
	 * 
	 * @Title: saveSelectives
	 * @Description: 建立角色权限关系
	 * @return: void
	 * @throws Exception 
	 */
	@SuppressWarnings("null")
	public void saveSelectives(Integer roleId, Integer[] resIds) throws Exception{
		dao.delete("SysRolePowerMapper.removeByRoleCid", roleId);
		if(resIds != null && resIds.length!=0){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("roleId", roleId);
			map.put("resIds", resIds);
			dao.save("SysRolePowerMapper.saveSelectives",map);
		}
	}
	/**
	 * @Title: findResByUserId
	 * @Description: 查询用户所拥有的权限
	 * @return: Set<String>
	 * @throws Exception 
	 */
	public Set<String> findResByUserId(Integer userId) throws Exception {
		List<Integer> roleList = (List<Integer>) dao.findForList("SysUserRoleMapper.getByMUserCid",userId);
		if(roleList.size()==0){
			return null;
		}
		List<String> list = (List<String>) dao.findForList("SysRolePowerMapper.getByRoleCids",roleList);
		return StringUtils.List2Set(list);
	}
}
