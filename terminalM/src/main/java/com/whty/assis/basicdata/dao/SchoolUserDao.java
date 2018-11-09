/**
 * 
 */
package com.whty.assis.basicdata.dao;

import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.basicdata.model.SchoolUser;

/**
 * @author zhangzheng
 * @date   2018年5月21日
 */
public interface SchoolUserDao extends IBaseDao<SchoolUser>{

	/**
	 * @param schoolId
	 */
	void deleteBySchoolId(Integer schoolId);

}
