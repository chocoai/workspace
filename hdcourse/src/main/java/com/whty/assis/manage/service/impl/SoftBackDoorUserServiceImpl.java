/**
 * 
 */
package com.whty.assis.manage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.manage.dao.SoftBackDoorDao;
import com.whty.assis.manage.dao.SoftBackDoorUserDao;
import com.whty.assis.manage.service.SoftBackDoorUserService;

/**
 * @author zhangzheng
 * @date 2018年9月14日
 */
@Service
public class SoftBackDoorUserServiceImpl implements SoftBackDoorUserService {

	@Autowired
	private SoftBackDoorDao softBackDoorDao;

	@Autowired
	private SoftBackDoorUserDao softBackDoorUserDao;

}