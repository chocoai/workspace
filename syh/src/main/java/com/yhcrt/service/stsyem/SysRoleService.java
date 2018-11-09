
package com.yhcrt.service.stsyem;

import static com.yhcrt.utils.Constants.SYS_ROLE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yhcrt.dao.DaoSupport;
import com.yhcrt.entity.system.SysRole;
import com.yhcrt.utils.GetSequence;

/**
 * 系统角色的service
 * @author 陈伟
 * 2017年5月23日 下午1:11:13
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Service("sysRoleService")
public class SysRoleService {
	@Resource
	private DaoSupport dao;
	
	
	/* 
	 * @Title: saveSelective
	 * @Description: 新增信息
	 */
	public void saveSelective(SysRole sysRole) throws Exception {
		int roleCid = GetSequence.getSequenceByName(dao, SYS_ROLE);
		sysRole.setCid(roleCid);
		dao.save("SysRoleMapper.saveSelective", sysRole);
	}
	
	/**
	 * 
	 * @Title: updateByPrimaryKeySelective
	 * @Description: 更新信息
	 * @return: void
	 * @throws Exception 
	 */
	public void updateByPrimaryKeySelective(SysRole sysRole) throws Exception{
		dao.update("SysRoleMapper.updateByPrimaryKeySelective",sysRole);
	}
	/**
	 * 
	 * @Title: quertByParam
	 * @Description: 多条件查询 所有
	 * @return: List<SysRole>
	 */
	@SuppressWarnings("unchecked")
	public List<SysRole> quertByParam(String findContent) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("findContent", findContent);
		return (List<SysRole>) dao.findForList("SysRoleMapper.quertByParam",map);
	}
	/**
	 * 
	 * @Title: quertByParam
	 * @Description: 多条件查询 非禁用
	 * @return: List<SysRole>
	 */
	@SuppressWarnings("unchecked")
	public List<SysRole> quertByNotDisabledParam(String findContent) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("findContent", findContent);
		return (List<SysRole>) dao.findForList("SysRoleMapper.quertByNotDisabledParam",map);
	}
	/**
	 * 
	 * @Title: getByCid
	 * @Description: 根据ID查询
	 * @return: SysRole
	 */
	public SysRole getByCid(Integer roleCid) throws Exception{
		return (SysRole) dao.findForObject("SysRoleMapper.getByCid", roleCid);
	}

	/**
	 * @Title: updateByCidSates
	 * @Description: 角色的禁用和激活
	 * @return: void
	 * @throws Exception 
	 */
	public void updateByCidSates(Integer id, Integer states) throws Exception {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("id", id);
		map.put("state", states);
		dao.update("SysRoleMapper.updateByCidSates", map);
	}

	/**
	 * 批量删除资源
	 * @param getById
	 * @return
	 * @throws Exception
	 */
	public String deleteAll(Integer[] cids) throws Exception {
		dao.delete("SysRolePowerMapper.removeByRoleCids",cids);
		dao.delete("SysRoleMapper.deleteAll",cids);
		return "success";
	}
	
}
