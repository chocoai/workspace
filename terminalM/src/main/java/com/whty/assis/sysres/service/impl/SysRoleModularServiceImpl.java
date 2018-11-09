/**
 * 
 */
package com.whty.assis.sysres.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.util.StringUtil;
import com.whty.assis.sysres.dao.SysRoleModularMapper;
import com.whty.assis.sysres.model.SysRoleModular;
import com.whty.assis.sysres.model.SysRoleModularExample;
import com.whty.assis.sysres.service.SysRoleModularService;
import com.whty.common.util.GUIDGenerator;

/** 
 * @ClassName: SysRoleModularServiceImpl 
 * @author: zjd
 * @date: 2018年6月5日 下午2:05:22  
 */
@Service
public class SysRoleModularServiceImpl implements SysRoleModularService{

	@Autowired
	private SysRoleModularMapper mapper;

	/* 
	 * @Title: selectByRoleId
	 * @return 
	 */ 
	@Override
	public List<SysRoleModular> selectByRoleId(Integer roleId) {
		SysRoleModularExample example = new SysRoleModularExample();
		example.createCriteria().andRoleIdEqualTo(roleId.toString());
		return mapper.selectByExample(example);
	}

	/* 
	 * @Title: insert
	 * @param roleId
	 * @param resId 
	 */ 
	@SuppressWarnings("null")
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public String insert(String roleId, String[] resIds) {
		String result ="";
		try{
			SysRoleModularExample example = new SysRoleModularExample();
			example.createCriteria().andRoleIdEqualTo(roleId);
			mapper.deleteByExample(example);
			if((resIds!=null || resIds.length!=0) && StringUtil.isNotEmpty(resIds[0])){
				mapper.insertRes(GUIDGenerator.getGUID(),roleId,resIds);
			}
			result="success";
		}catch(Exception e){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			System.out.println("出错了，要回滚");
			result="fail";
			e.printStackTrace();
		}
		return result;
		
		
	}
}
