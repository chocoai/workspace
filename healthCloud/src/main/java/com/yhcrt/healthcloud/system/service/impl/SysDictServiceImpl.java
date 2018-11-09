/**
 * @Title:   SysDictServiceImpl.java 
 * @Package: com.yhcrt.healthcloud.system.service.impl  
 * @Description: 
 * @author: rpf
 * @date: 2017年5月26日 
 * @version V1.0 
 * Copyright © 2017 Wuhan YHCRT Technology Co.Ltd. All rights reserved.
 */
package com.yhcrt.healthcloud.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yhcrt.healthcloud.common.Constants;
import com.yhcrt.healthcloud.system.entity.SysDictionary;
import com.yhcrt.healthcloud.system.entity.SysDictionaryExample;
import com.yhcrt.healthcloud.system.mapper.SysDictionaryMapper;
import com.yhcrt.healthcloud.system.service.SysDictService;

/**
 * @ClassName: SysDictServiceImpl
 * @Description:
 * @version V1.0 
 * @author rpf
 * @date: 2017年5月26日 
 */

@Service
@Transactional
public class SysDictServiceImpl implements SysDictService {

    @Autowired
    private SysDictionaryMapper sysDictionaryMapper;

	/* (non-Javadoc)
	 * @see com.yhcrt.healthcloud.system.service.SysDictService#insert(com.yhcrt.healthcloud.system.entity.SysDictionary)
	 */
	@Override
	public int insert(SysDictionary sysDictionary) {
		return sysDictionaryMapper.insert(sysDictionary);
	}

	/* (non-Javadoc)
	 * @see com.yhcrt.healthcloud.system.service.SysDictService#deleteByDictId(java.lang.Integer)
	 */
	@Override
	public int deleteByDictId(Integer dictId) {
		return sysDictionaryMapper.deleteByPrimaryKey(dictId);
	}

	/* (non-Javadoc)
	 * @see com.yhcrt.healthcloud.system.service.SysDictService#updateByDictId(com.yhcrt.healthcloud.system.entity.SysDictionary)
	 */
	@Override
	public int updateByDictId(SysDictionary sysDictionary) {
		return sysDictionaryMapper.updateByPrimaryKey(sysDictionary);
	}

	/* (non-Javadoc)
	 * @see com.yhcrt.healthcloud.system.service.SysDictService#listAllSysDict()
	 */
	@Override
	public List<SysDictionary> listAllSysDict() {
		SysDictionaryExample example = new SysDictionaryExample();
		example.createCriteria().andStatusEqualTo(Constants.STATUS_NORMAL);
		example.setOrderByClause("create_time desc");
		return sysDictionaryMapper.selectByExample(example);
	}

	/* (non-Javadoc)
	 * @see com.yhcrt.healthcloud.system.service.SysDictService#getSysDictByDictId(java.lang.Integer)
	 */
	@Override
	public SysDictionary getSysDictByDictId(Integer dictId) {
		return sysDictionaryMapper.selectByPrimaryKey(dictId);
	}

	//根据dict_en_name查询
	@Override
	public List<SysDictionary> queryByDname(String dict_en_name) {
		return sysDictionaryMapper.queryByDname(dict_en_name);
	}

}
