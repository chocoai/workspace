
package com.yhcrt.service.stsyem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yhcrt.dao.DaoSupport;

/**
 * 角色关系的servic
 * @author 陈伟
 * 2017年5月23日 下午3:21:24
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Service("sysUserRoleService")
public class SysUserRoleService {
	@Resource
	private DaoSupport dao;
	/**
	 * 
	 * @Title: saveSelectives
	 * @Description: 建立用户角色关系
	 * @return: void
	 * @throws Exception 
	 */
	public void saveSelectives(Integer userCid, Integer[] roleCids) throws Exception{
		dao.delete("SysUserRoleMapper.removeByUserCid", userCid);
		if(roleCids.length!=0){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userCid", userCid);
			map.put("roleCids", roleCids);
			dao.save("SysUserRoleMapper.saveSelectives",map);
		}
	}
}
