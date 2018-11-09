/**
 * 
 */
package com.whty.assis.mall.dao;

import java.util.List;
import java.util.Map;

import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.mall.model.AppGrade;
import com.whty.assis.mall.model.AppInfo;
import com.whty.assis.mall.model.AppSubject;

/**
 * @author zhangzheng
 * @date   2018年4月18日
 */
public interface AppInfoDao extends IBaseDao<AppInfo>{

	public void saveAppGrade(AppGrade bean);
	
	public void saveAppSubject(AppSubject bean);

	/**
	 * @param id
	 */
	public void deleteAppGradeByAppId(Integer id);

	/**
	 * @param id
	 */
	public void deleteAppSubjectByAppId(Integer id);

	/**
	 * @param paramMap
	 * @return
	 */
	public List<AppInfo> listByParam(Map<String, Object> paramMap);
	
}
