/**
 * 
 */
package com.whty.assis.manage.service;

import java.util.List;
import java.util.Map;

import com.whty.assis.manage.model.SoftBackDoor;
import com.whty.page.util.HandlerResult;

/**
 * @author zhangzheng
 * @date 2018年9月14日
 */
public interface SoftBackDoorService {

	/**
	 * @param paramMap
	 * @return
	 */
	HandlerResult querySoftBackDoor(Map<String, Object> paramMap);

	/**
	 * @param softBackDoor
	 */
	void save(SoftBackDoor softBackDoor);

	/**
	 * @param valueOf
	 * @return
	 */
	SoftBackDoor loadById(Integer valueOf);

	public List<SoftBackDoor> queryDownUrls(Map<String, Object> param);

	/**
	 * @param softBackDoor
	 */
	void update(SoftBackDoor softBackDoor);

	/**
	 * @param softBackDoor
	 */
	void updateBos(SoftBackDoor softBackDoor);

	/**
	 * @param valueOf
	 */
	void delete(Integer valueOf);

}