package com.whty.assis.demo.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.base.interceptor.CommonInterceptor;
import com.whty.assis.base.service.BaseService;
import com.whty.assis.demo.dao.DemoDao;
import com.whty.assis.demo.model.Demo;
import com.whty.assis.demo.service.DemoService;
import com.whty.page.util.HandlerResult;

/**
 * 示例Service实现
 * 
 * @author shijiapeng
 * @date 2015年8月11日 下午3:37:17
 */
@Service
public class DemoServiceImpl extends BaseService implements DemoService {
	private static Logger logger = LoggerFactory.getLogger(DemoServiceImpl.class);
	@Autowired
	private DemoDao demoDao;

	/**
	 * 根据条件加载Demo列表
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<Demo> listDemo(Map<String, Object> paramMap) {

		if (logger.isDebugEnabled()) {
			logger.debug("根据条件加载Demo列表Service执行，参数：" + paramMap);
		}

		return demoDao.listByCondition(paramMap);
	}

	@Override
	public HandlerResult listByConditionListPage(Map paramMap) throws Exception {
		logger.info("queryPage:" + paramMap);
		HandlerResult rs = new HandlerResult();
		rs.setResultList(demoDao.listByConditionListPage(paramMap));
		return rs;
	}

}
