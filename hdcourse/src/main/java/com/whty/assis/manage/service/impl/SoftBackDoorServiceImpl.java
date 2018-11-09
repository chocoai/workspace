/**
 * 
 */
package com.whty.assis.manage.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.manage.dao.SoftBackDoorDao;
import com.whty.assis.manage.dao.SoftBackDoorUserDao;
import com.whty.assis.manage.model.SoftBackDoor;
import com.whty.assis.manage.service.SoftBackDoorService;
import com.whty.page.util.HandlerResult;

/**
 * @author zhangzheng
 * @date 2018年9月14日
 */
@Service
public class SoftBackDoorServiceImpl implements SoftBackDoorService {

	private static Logger logger = LoggerFactory.getLogger(SoftBackDoorServiceImpl.class);

	@Autowired
	private SoftBackDoorDao softBackDoorDao;

	@Autowired
	private SoftBackDoorUserDao softBackDoorUserDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.manage.service.SoftBackDoorService#querySoftBackDoor(java.
	 * util.Map)
	 */
	@Override
	public HandlerResult querySoftBackDoor(Map<String, Object> paramMap) {
		logger.info("queryPage:" + paramMap);
		HandlerResult rs = new HandlerResult();
		rs.setResultList(softBackDoorDao.listByCondition(paramMap));
		return rs;
	}

	@Override
	public List<SoftBackDoor> queryDownUrls(Map<String, Object> param) {
		return softBackDoorDao.queryDownUrls(param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.manage.service.SoftBackDoorService#save(com.whty.assis.
	 * manage.model.SoftBackDoor)
	 */
	@Override
	public void save(SoftBackDoor softBackDoor) {
		softBackDoorDao.save(softBackDoor);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.manage.service.SoftBackDoorService#loadById(java.lang.
	 * Integer)
	 */
	@Override
	public SoftBackDoor loadById(Integer id) {
		return softBackDoorDao.loadById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.manage.service.SoftBackDoorService#update(com.whty.assis.
	 * manage.model.SoftBackDoor)
	 */
	@Override
	public void update(SoftBackDoor softBackDoor) {
		softBackDoorDao.update(softBackDoor);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.manage.service.SoftBackDoorService#updateBos(com.whty.
	 * assis.manage.model.SoftBackDoor)
	 */
	@Override
	public void updateBos(SoftBackDoor softBackDoor) {
		softBackDoorDao.updateBos(softBackDoor);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.manage.service.SoftBackDoorService#delete(java.lang.
	 * Integer)
	 */
	@Override
	public void delete(Integer id) {

		softBackDoorDao.deleteById(id);
	}

}
