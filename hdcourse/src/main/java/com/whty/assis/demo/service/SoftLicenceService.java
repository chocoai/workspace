package com.whty.assis.demo.service;

import java.util.List;
import java.util.Map;

import com.whty.assis.demo.model.EbpSoftLicence;
import com.whty.assis.demo.model.SoftLicence;
import com.whty.page.util.HandlerResult;

/**
 * 客户端激活码授权信息Service
 * 
 * @author zhangc
 */
public interface SoftLicenceService {

	public HandlerResult querySoftLicencePage(Map<String, Object> paramMap);

	public List<SoftLicence> querySoftLicence(Map<String, Object> paramMap);

	public SoftLicence saveEbpSoftLicence(SoftLicence soft);

	public void updateEbpSoftLicence(SoftLicence soft);

	public SoftLicence getSoftLicenceById(String id);

	public void save(SoftLicence soft);

	SoftLicence getById(String id);
}
