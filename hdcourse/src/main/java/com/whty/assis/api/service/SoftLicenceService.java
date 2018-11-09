package com.whty.assis.api.service;

import java.util.List;
import java.util.Map;

import com.whty.assis.demo.model.EbpSoftLicence;

public interface SoftLicenceService {

	// 绑定授权码信息
	public void bindingLicence(EbpSoftLicence jsonToObj);

	public EbpSoftLicence getEbpSoftLicence(Map map);

	public EbpSoftLicence getEbpSoftLicenceBYMch(Map map);

	public List<EbpSoftLicence> findEbpSoftLicenceBYMch(Map map);

}
