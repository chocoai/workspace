/**
 * 
 */
package com.whty.assis.mall.dao;

import com.whty.assis.base.dao.IBaseDao;

/**
 * @author zhangzheng
 * @date   2018年4月18日
 */
public interface AppImgDao extends IBaseDao<com.whty.assis.mall.model.AppImg>{

	public void deleteByAppId(Integer appId);
	
}
