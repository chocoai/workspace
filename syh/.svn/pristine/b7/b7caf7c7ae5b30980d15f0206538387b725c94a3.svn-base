
package com.yhcrt.service.stsyem;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yhcrt.dao.DaoSupport;
import com.yhcrt.entity.system.SysMuserInfo;
import com.yhcrt.entity.system.SysUser;

/**
 * 系统用户的service
 * @author 陈伟
 * 2017年5月23日 下午1:11:02
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Service("sysUserService")
public class SysUserService {
	@Resource
	private DaoSupport dao;
	
	
	/* 
	 * @Title: isExist
	 * @Description: 是否存在该账号  存在 返回：true
	 * 
	 */
	public boolean isExist(String userCode) throws Exception {
		SysUser user = new SysUser(userCode);
		Integer count = (Integer) dao.findForObject("SysUserMapper.countByParam", user);
		return count == 0 ? false : true;
	}
	/* 
	 * @Title: getByUsreCodeAndPwd
	 * @Description: 根据条件查询
	 * 
	 */
	public SysUser getByUsreCodeAndPwd(String userCode, String password) throws Exception {
		SysUser user = new SysUser(userCode,password);
		return (SysUser) dao.findForObject("SysUserMapper.getByParam", user);
	}
	/**
	 * @Title: updateByPrimaryKeySelective
	 * @Description: 更新信息
	 * @return: void
	 */
	public void updateByPrimaryKeySelective(SysUser sysUser) throws Exception{
		dao.update("SysUserMapper.updateByPrimaryKeySelective", sysUser);
	}
	/**
	 * 
	 * @Title: getByUsreCid
	 * @Description: 根据ID查询
	 * @return: SysUser
	 */
	public SysUser getByCid(Integer cid) throws Exception {
		return (SysUser) dao.findForObject("SysUserMapper.getByCid", cid);
	}
}
