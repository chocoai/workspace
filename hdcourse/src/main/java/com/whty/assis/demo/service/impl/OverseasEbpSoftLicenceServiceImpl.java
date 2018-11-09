/**
 * 
 */
package com.whty.assis.demo.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.demo.dao.OverseasEbpSoftLicenceDao;
import com.whty.assis.demo.model.OverseasEbpSoftLicence;
import com.whty.assis.demo.service.OverseasEbpSoftLicenceService;
import com.whty.common.util.EbpSoftLicenceUtils;
import com.whty.page.util.HandlerResult;

/**
 * @author zhangzheng
 * @date 2018年5月17日
 */
@Service
public class OverseasEbpSoftLicenceServiceImpl implements OverseasEbpSoftLicenceService {

	private static Logger logger = LoggerFactory.getLogger(EbpSoftLicenceServiceImpl.class);
	@Autowired
	private OverseasEbpSoftLicenceDao softDao;

	@Override
	public void save(OverseasEbpSoftLicence soft) {
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
	public OverseasEbpSoftLicence saveEbpSoftLicence(OverseasEbpSoftLicence soft) {
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
	public OverseasEbpSoftLicence getSoftLicenceById(String id) {
		return softDao.loadById(id);
	}

	@Override
	public void updateEbpSoftLicence(OverseasEbpSoftLicence soft) {
		softDao.update(soft);
	}

	@Override
	public List<OverseasEbpSoftLicence> querySoftLicence(Map<String, Object> paramMap) {
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

}
