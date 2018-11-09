/**
 * 
 */
package com.whty.assis.api.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.api.service.OverseasSoftLicenceService;
import com.whty.assis.demo.dao.OverseasEbpSoftLicenceDao;
import com.whty.assis.demo.model.OverseasEbpSoftLicence;

/**
 * @author zhangzheng
 * @date 2018年5月17日
 */
@Service
public class OverseasSoftLicenceServiceImpl implements OverseasSoftLicenceService {

	@Autowired
	private OverseasEbpSoftLicenceDao overseasEbpSoftLicenceDao;

	/*
	 * 
	 * 绑定授权码信息
	 */
	@Override
	public void bindingLicence(OverseasEbpSoftLicence ebpSoftLicence) {
		ebpSoftLicence.setStatus(1);
		overseasEbpSoftLicenceDao.update(ebpSoftLicence);
	}

	/*
	 * 查询软件授权码实体
	 */
	@Override
	public OverseasEbpSoftLicence getOverseasEbpSoftLicence(Map map) {
		return overseasEbpSoftLicenceDao.loadByLicence(map);
	}

	@Override
	public List<OverseasEbpSoftLicence> findOverseasEbpSoftLicenceBYMch(Map map) {
		return overseasEbpSoftLicenceDao.queryByMch(map);
	}

	/*
	 * 根据mch查询查询软件授权码信息
	 */
	@Override
	public OverseasEbpSoftLicence getOverseasEbpSoftLicenceBYMch(Map map) {
		OverseasEbpSoftLicence licence = null;
		List<OverseasEbpSoftLicence> licenseList = overseasEbpSoftLicenceDao.queryByMch(map);
		if (null != licenseList && licenseList.size() > 0) {
			licence = licenseList.get(0);
		}
		return licence;
	}

}
