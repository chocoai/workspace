/**
 * 
 */
package com.whty.assis.demo.service;

import java.util.List;
import java.util.Map;

import com.whty.assis.demo.model.OverseasEbpSoftLicence;
import com.whty.page.util.HandlerResult;

/**
 * @author zhangzheng
 * @date 2018年5月17日
 */
public interface OverseasEbpSoftLicenceService {

	public HandlerResult querySoftLicencePage(Map<String, Object> paramMap);

	public List<OverseasEbpSoftLicence> querySoftLicence(Map<String, Object> paramMap);

	public OverseasEbpSoftLicence saveEbpSoftLicence(OverseasEbpSoftLicence soft);

	public void updateEbpSoftLicence(OverseasEbpSoftLicence soft);

	public OverseasEbpSoftLicence getSoftLicenceById(String id);

	public void save(OverseasEbpSoftLicence soft);

	public HandlerResult queryLicenceCode(Map<String, Object> paramMap);

	public void cancelLicenceCode(String ids);
}
