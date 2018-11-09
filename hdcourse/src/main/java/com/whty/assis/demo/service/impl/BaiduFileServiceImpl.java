/**
 * 
 */
package com.whty.assis.demo.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.demo.dao.BaiduFileDao;
import com.whty.assis.demo.model.BaiduFile;
import com.whty.assis.demo.service.BaiduFileService;
import com.whty.page.util.HandlerResult;

/**
 * @author zhangzheng
 * @date 2018年8月8日
 */
@Service
public class BaiduFileServiceImpl implements BaiduFileService {

	@Autowired
	private BaiduFileDao baiduFileDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.demo.service.BaiduFileService#save(com.whty.assis.demo.
	 * model.BaiduFile)
	 */
	@Override
	public void save(BaiduFile baiduFile) {
		baiduFileDao.save(baiduFile);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.demo.service.BaiduFileService#update(com.whty.assis.demo.
	 * model.BaiduFile)
	 */
	@Override
	public void update(BaiduFile baiduFile) {
		baiduFileDao.update(baiduFile);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.demo.service.BaiduFileService#listByConditionListPage(java
	 * .util.Map)
	 */
	@Override
	public HandlerResult listByConditionListPage(Map<String, Object> paramMap) throws Exception {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(baiduFileDao.listByCondition(paramMap));
		return rs;
	}

}
