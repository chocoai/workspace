/**
 * 
 */
package com.whty.assis.devicemanage.dao;

import java.util.List;

import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.devicemanage.model.AppNetBlacklist;

/**
 * @author zhangzheng
 * @date   2018年6月14日
 */
public interface AppNetBlacklistDao extends IBaseDao<AppNetBlacklist>{

	/**
	 * @param schoolClassId
	 * @return
	 */
	List<?> listBySchoolClassRule(Integer schoolClassId);

}
