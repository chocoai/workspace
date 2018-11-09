/**
 * @Title:   SysResServiceImpl.java 
 * @Package: com.yhcrt.healthcloud.system.service.impl  
 * @Description: 
 * @author: rpf
 * @date: 2017年5月16日 
 * @version V1.0 
 * Copyright © 2017 Wuhan YHCRT Technology Co.Ltd. All rights reserved.
 */
package com.yhcrt.healthcloud.system.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yhcrt.healthcloud.common.Constants;
import com.yhcrt.healthcloud.system.entity.SysRes;
import com.yhcrt.healthcloud.system.entity.SysResExample;
import com.yhcrt.healthcloud.system.mapper.SysResMapper;
import com.yhcrt.healthcloud.system.service.SysResService;

/**
 * @ClassName: SysResServiceImpl
 * @Description:
 * @version V1.0
 * @author rpf
 * @date: 2017年5月16日
 */

@Service
@Transactional
public class SysResServiceImpl implements SysResService {

	@Autowired
	private SysResMapper sysResMapper;

	@Override
	public SysRes getSysResByResId(Integer resId) {
		return sysResMapper.selectByPrimaryKey(resId);
	}

	@Override
	public SysRes getSysResRootNode() {
		return sysResMapper.getSysResRootNode();
	}

	@Override
	public int insert(SysRes record) {
		return sysResMapper.insert(record);
	}

	@Override
	public List<SysRes> getChildResByParentId(String parentId) {
		List<SysRes> resList = sysResMapper.getChildResByParentId(parentId);
		return resList;
	}

	@Override
	public int deleteByPrimaryKey(Integer resId) {
		return sysResMapper.deleteByPrimaryKey(resId);
	}

	@Override
	public int updateByPrimaryKey(SysRes sysRes) {
		return sysResMapper.updateByPrimaryKeySelective(sysRes);
	}

	@Override
	public List<SysRes> listAllSysRes() {
		SysResExample example = new SysResExample();
		example.createCriteria().andStatusEqualTo(Constants.STATUS_NORMAL);
		return sysResMapper.selectByExample(example);
	}

	@Override
	public String getLastResCodeByParentId(String parentId) {
		return sysResMapper.getLastResCodeByParentId(parentId);
	}

	@Override
	public Set<String> findPermissionsByUserId(String userId) {
		List<String> list = sysResMapper.findPermissionsByUserId(userId);
		Set<String> permissions = new HashSet<String>(list);
		return permissions;
	}

}
