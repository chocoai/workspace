package com.whty.assis.api.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.api.service.SoftLicenceService;
import com.whty.assis.demo.dao.EbpSoftLicenceDao;
import com.whty.assis.demo.model.EbpSoftLicence;

import schemasMicrosoftComOfficeOffice.STInsetMode;

/*
 * 电子书包软件授权码
 */
@Service
public class SoftLicenceServiceImpl implements SoftLicenceService {

	@Autowired
	private EbpSoftLicenceDao ebpSoftLicenceDao;

	/*
	 * 
	 * 绑定授权码信息
	 */
	@Override
	public void bindingLicence(EbpSoftLicence ebpSoftLicence) {
		ebpSoftLicence.setStatus(1);
		ebpSoftLicenceDao.update(ebpSoftLicence);
	}

	/*
	 * 查询软件授权码实体
	 */
	@Override
	public EbpSoftLicence getEbpSoftLicence(Map map) {
		return ebpSoftLicenceDao.loadByLicence(map);
	}

	@Override
	public List<EbpSoftLicence> findEbpSoftLicenceBYMch(Map map) {
		return ebpSoftLicenceDao.queryByMch(map);
	}

	/*
	 * 根据mch查询查询软件授权码信息
	 */
	@Override
	public EbpSoftLicence getEbpSoftLicenceBYMch(Map map) {
		EbpSoftLicence licence = null;
		List<EbpSoftLicence> licenseList = ebpSoftLicenceDao.queryByMch(map);
		if (null != licenseList && licenseList.size() > 0) {
			licence = licenseList.get(0);
		}
		return licence;
	}

}
