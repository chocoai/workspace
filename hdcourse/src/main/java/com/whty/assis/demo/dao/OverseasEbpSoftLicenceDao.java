/**
 * 
 */
package com.whty.assis.demo.dao;

import java.util.List;
import java.util.Map;

import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.demo.model.EbpSoftLicence;
import com.whty.assis.demo.model.OverseasEbpSoftLicence;

/**
 * @author zhangzheng
 * @date 2018年5月17日
 */
public interface OverseasEbpSoftLicenceDao extends IBaseDao<OverseasEbpSoftLicence> {

	/**
	 * @param map
	 * @return
	 */
	OverseasEbpSoftLicence loadByLicence(Map<String, Object> map);

	/**
	 * @param map
	 * @return
	 */
	List<OverseasEbpSoftLicence> queryByMch(Map<String, Object> map);

	public List<OverseasEbpSoftLicence> querySoftLicence(Map<String, Object> paramMap);

	public int licenceCount(String licence);

	public List<Map<String, Object>> queryLicenceCode(Map<String, Object> paramMap);

	public void cancelLicenceCode(List<Map<String, Object>> list);

}
