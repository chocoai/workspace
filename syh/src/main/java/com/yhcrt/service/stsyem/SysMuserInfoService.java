
package com.yhcrt.service.stsyem;

import static com.yhcrt.utils.Constants.SYS_USER;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yhcrt.dao.DaoSupport;
import com.yhcrt.entity.system.SysMuserInfo;
import com.yhcrt.entity.system.SysUser;
import com.yhcrt.utils.GetSequence;
import com.yhcrt.utils.Md5PwdUtil;
import com.yhcrt.utils.StringUtils;

/**
 * TODO
 * @author 陈伟
 * 2017年5月23日 下午1:33:14
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Service("sysMuserInfoService")
public class SysMuserInfoService {
	@Resource
	private DaoSupport dao;
	/**
	 * 
	 * @Title: saveSelective
	 * @Description: 新增信息
	 * @return: void
	 */
	public void saveSelective(SysMuserInfo sysMuserInfo) throws Exception{
		int Cid = GetSequence.getSequenceByName(dao, SYS_USER);
		SysUser sysUser = sysMuserInfo.getSysUser();
		sysUser.setCid(Cid);
		sysUser.setPassword(Md5PwdUtil.encodePassword(sysUser.getPassword()));
		
		sysMuserInfo.setCid(Cid);
		sysMuserInfo.setUserId(Cid);
		
		dao.save("SysUserMapper.saveSelective", sysUser);
		dao.save("SysMuserInfoMapper.saveSelective", sysMuserInfo);
		
		//添加角色
		Integer roleCids = sysMuserInfo.getSysRole().getCid();
		if(roleCids!= null){
			Integer [] ids = {roleCids};
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userCid", Cid);
			map.put("roleCids", ids);
			dao.save("SysUserRoleMapper.saveSelectives",map);
		}
	}
	/**
	 * 
	 * @Title: updateByPrimaryKeySelective
	 * @Description: 更新信息和修改权限
	 * @return: void
	 * @throws Exception 
	 */
	public void updateUserAndRole(SysMuserInfo sysMuserInfo) throws Exception{
		
		Integer userCid = sysMuserInfo.getCid();
		Integer roleCids = sysMuserInfo.getSysRole().getCid();
		dao.delete("SysUserRoleMapper.removeByUserCid",userCid);
		if(roleCids!=null){
			Integer [] ids = {roleCids};
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userCid", userCid);
			map.put("roleCids", ids);
			dao.save("SysUserRoleMapper.saveSelectives",map);
		}
		if(!StringUtils.isBlank(sysMuserInfo.getSysUser().getPassword())){
			SysUser sysUser = (SysUser) dao.findForObject("SysUserMapper.getByCid",sysMuserInfo.getUserId());
			sysUser.setPassword(Md5PwdUtil.encodePassword(sysMuserInfo.getSysUser().getPassword()));
			dao.update("SysUserMapper.updateByPrimaryKeySelective", sysUser);
		}
		dao.update("SysMuserInfoMapper.updateByPrimaryKeySelective", sysMuserInfo);
	}
	/**
	 * 
	 * @Title: updateByPrimaryKeySelective
	 * @Description: 更新信息
	 * @return: void
	 * @throws Exception 
	 */
	public void updateByPrimaryKeySelective(SysMuserInfo sysMuserInfo) throws Exception{
		dao.update("SysMuserInfoMapper.updateByPrimaryKeySelective", sysMuserInfo);
	}
	/**
	 * 
	 * @Title: updateByCidSates
	 * @Description: 更新用户的状态
	 * @return: void
	 * @throws Exception 
	 */
	public void updateByCidSates(Integer id,Integer states) throws Exception{
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("id", id);
		map.put("state", states);
		dao.update("SysUserMapper.updateByCidSates", map);
		dao.update("SysMuserInfoMapper.updateByCidSates", map);
	}
	/**
	 * 
	 * @Title: quertByParam
	 * @Description: 多条件查询
	 * @return: List<SysMuserInfo>
	 */
	@SuppressWarnings("unchecked")
	public List<SysMuserInfo> quertByParam(String findContent) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("findContent", findContent);
		ArrayList<SysMuserInfo> list = (ArrayList<SysMuserInfo>) dao.findForList("SysMuserInfoMapper.quertByParam",map);
		return list;
	}
	/**
	 * 
	 * @Title: getByCid
	 * @Description: 根据ID查询
	 * @return: SysMuserInfo
	 */
	public SysMuserInfo getByCid(Integer Cid) throws Exception{
		SysMuserInfo sysMuserInfo =  (SysMuserInfo) dao.findForObject("SysMuserInfoMapper.getByCid", Cid);
		return sysMuserInfo;
	}
	/**
	 * @Title: findByUserId
	 * @Description: 根据账号信息表用户ID查询
	 * @return: SysMuserInfo
	 */
	public SysMuserInfo findByUserId(Integer userCid) throws Exception {
		return (SysMuserInfo) dao.findForObject("SysMuserInfoMapper.getByUserCid", userCid);
	}
	/**
	 * 批量删除资源
	 * @param getById
	 * @return
	 * @throws Exception
	 */
	public String deleteAll(Integer[] cids) throws Exception {
		dao.delete("SysUserRoleMapper.removeByUserCids",cids);
		dao.delete("SysUserMapper.deleteAll",cids);
		dao.delete("SysMuserInfoMapper.deleteAll",cids);
		return "success";
	}
}
