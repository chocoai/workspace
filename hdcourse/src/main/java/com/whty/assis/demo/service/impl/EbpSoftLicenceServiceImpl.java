package com.whty.assis.demo.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.stereotype.Service;

import com.whty.assis.base.service.BaseService;
import com.whty.assis.demo.dao.EbpSoftLicenceDao;
import com.whty.assis.demo.model.EbpSoftLicence;
import com.whty.assis.demo.service.EbpSoftLicenceService;
import com.whty.common.util.EbpSoftLicenceUtils;
import com.whty.page.util.HandlerResult;

/**
 * 客户端激活码授权管理Service实现
 * 
 * @author zhangc
 */
@Service
public class EbpSoftLicenceServiceImpl extends BaseService implements EbpSoftLicenceService {
	private static Logger logger = LoggerFactory.getLogger(EbpSoftLicenceServiceImpl.class);
	@Autowired
	private EbpSoftLicenceDao softDao;

	@Override
	public void save(EbpSoftLicence soft) {
		softDao.save(soft);
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
	public EbpSoftLicence saveEbpSoftLicence(EbpSoftLicence soft) {
		// 先生成授权码并去数据库查询是否已存在
		String licence = EbpSoftLicenceUtils.getLicence();
		int count = softDao.licenceCount(licence);
		while (count > 0) {
			licence = EbpSoftLicenceUtils.getLicence();
			count = softDao.licenceCount(licence);
		}
		// 新的授权码，保存
		soft.setLicence_code(licence);
		softDao.save(soft);
		return soft;
	}

	@Override
	public EbpSoftLicence getSoftLicenceById(String id) {
		return softDao.loadById(id);
	}

	@Override
	public void updateEbpSoftLicence(EbpSoftLicence soft) {
		softDao.update(soft);
	}

	@Override
	public List<EbpSoftLicence> querySoftLicence(Map<String, Object> paramMap) {
		return softDao.querySoftLicence(paramMap);
	}

	@Override
	public HandlerResult queryLicenceCode(Map<String, Object> paramMap) {
		logger.info("queryPage:" + paramMap);
		HandlerResult rs = new HandlerResult();
		rs.setResultList(softDao.querySoftLicence(paramMap));
		return rs;
	}

	@Override
	public void cancelLicenceCode(String ids) {

		List idList = Arrays.asList(ids);

		softDao.cancelLicenceCode(idList);

	}

	@Override
	public EbpSoftLicence getById(String id) {
		return softDao.loadById(id);
	}
}
