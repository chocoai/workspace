/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.expenseclaimrecords.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.expenseclaimrecords.entity.Expenseclaimrecords;

/**
 * 单表生成DAO接口
 * @author ThinkGem
 * @version 2017-12-25
 */
@MyBatisDao
public interface ExpenseclaimrecordsDao extends CrudDao<Expenseclaimrecords> {
	
}