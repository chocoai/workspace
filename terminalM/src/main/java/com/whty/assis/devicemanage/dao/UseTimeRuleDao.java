/**
 * 
 */
package com.whty.assis.devicemanage.dao;

import java.util.List;

import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.devicemanage.model.UseTimeRule;

/**
 * @author zhangzheng
 * @date   2018年4月21日
 */
public interface UseTimeRuleDao extends IBaseDao<UseTimeRule>{

	/**
	 * @param schoolClassId
	 * @return
	 */
	List<UseTimeRule> listBySchoolClassRule(Integer schoolClassId);

}
