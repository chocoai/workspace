package com.whty.assis.demo.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.base.service.BaseService;
import com.whty.assis.demo.dao.EbpSoftLicenceDao;
import com.whty.assis.demo.dao.SoftLicenceDao;
import com.whty.assis.demo.model.EbpSoftLicence;
import com.whty.assis.demo.model.SoftLicence;
import com.whty.assis.demo.service.EbpSoftLicenceService;
import com.whty.assis.demo.service.SoftLicenceService;
import com.whty.common.util.EbpSoftLicenceUtils;
import com.whty.page.util.HandlerResult;

/**
 * 客户端激活码授权管理Service实现
 * 
 * @author zhangc
 */
@Service
public class SsoftLicenceServiceImpl extends BaseService implements SoftLicenceService {
	private static Logger logger = LoggerFactory.getLogger(SsoftLicenceServiceImpl.class);
	@Autowired
	private SoftLicenceDao softDao;

	@Override
	public void save(SoftLicence soft) {
		softDao.save(soft);
	}

	@Override
	public SoftLicence getById(String id) {
		return softDao.loadById(id);
	}

	/**
	 * 分页查询激活码授权管理
	 */
	@Override
	public HandlerResult querySoftLicencePage(Map<String, Object> paramMap) {
		logger.info("queryPage:" + paramMap);
		HandlerResult rs = new HandlerResult();
		rs.setResultList(softDao.querySoftLicence(paramMap));
		return rs;
	}

	@Override
	public SoftLicence saveEbpSoftLicence(SoftLicence soft) {
		// 先生成授权码并去数据库查询是否已存在
		String licence = EbpSoftLicenceUtils.getLicence();
		int count = softDao.licenceCount(licence);
		while (count > 0) {
			licence = EbpSoftLicenceUtils.getLicence();
			softDao.licenceCount(licence);
		}
		// 新的授权码，保存
		soft.setLicence_code(licence);
		softDao.save(soft);
		return soft;
	}

	@Override
	public SoftLicence getSoftLicenceById(String id) {
		return softDao.loadById(id);
	}

	@Override
	public void updateEbpSoftLicence(SoftLicence soft) {
		softDao.update(soft);
	}

	@Override
	public List<SoftLicence> querySoftLicence(Map<String, Object> paramMap) {
		return softDao.querySoftLicence(paramMap);
	}
}