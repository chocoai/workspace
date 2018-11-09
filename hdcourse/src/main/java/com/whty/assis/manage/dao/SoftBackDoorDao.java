/**
 * 
 */
package com.whty.assis.manage.dao;

import java.util.List;
import java.util.Map;

import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.manage.model.SoftBackDoor;

/**
 * @author zhangzheng
 * @date 2018年9月14日
 */
public interface SoftBackDoorDao extends IBaseDao<SoftBackDoor> {

	public List<SoftBackDoor> queryDownUrls(Map<String, Object> param);

	/**
	 * @param softBackDoor
	 */
	public void updateBos(SoftBackDoor softBackDoor);
}
