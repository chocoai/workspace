package com.whty.mxbj.api.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.whty.mxbj.api.comparator.BasePropertyComparator;
import com.whty.mxbj.api.dao.BasePropertyDao;
import com.whty.mxbj.api.model.BaseProperty;
import com.whty.mxbj.api.service.BasePropertyService;
import com.whty.mxbj.common.constants.SysConstants;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

@Component("basePropertyService")
public class BasePropertyServiceImpl implements BasePropertyService {

	@Autowired
	private BasePropertyDao basePropertyDao;

	@Autowired
	private MemcachedClient memcachedClient;

	@Override
	public List<BaseProperty> find(Map<String, Object> params) {
		return basePropertyDao.listByCondition(params);
	}

	/**
	 * 防止缓存失效风暴，将缓存的生命周期随机设置成12h至24h
	 * 
	 * @return
	 */
	private int generateExpireTime() {
		Random random = new Random();
		int expTime = random.nextInt(12 * 3600) + 12 * 3600;
		return expTime;
	}

	@Override
	public String getPropertyValue(String key, String platformCode) {
		String value = null;

		StringBuffer keySb = new StringBuffer();
		String memcacheClientKey = keySb.append(SysConstants.BASE_PROPERTY_CACHE_KEY)
				.append(SysConstants.CACHE_KEY_SEPERATER).append(key).append(SysConstants.CACHE_KEY_SEPERATER)
				.append(platformCode).toString();
		try {
			value = memcachedClient.get(memcacheClientKey);
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
		if (value == null) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("platform_code", platformCode);
			param.put("property_key", key);
			value = basePropertyDao.getPropertyValue(param);

			if (value != null) {
				try {
					memcachedClient.add(memcacheClientKey, generateExpireTime(), value);
				} catch (TimeoutException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (MemcachedException e) {
					e.printStackTrace();
				}
			}
		}

		return value;
	}

//	@Override
//	public HandlerResult queryBaseProperty(Map<String, Object> para) {
//		HandlerResult rs = new HandlerResult();
//		List<BaseProperty> resultList = basePropertyDao.queryBaseProperty(para);
//		// 改用Comparator实现按update_time降序排列
//		// Collections.sort(resultList, new BasePropertyComparator());
//		rs.setResultList(resultList);
//		return rs;
//	}

	@Override
	public List<BaseProperty> queryAllBaseProperty(Map<String, Object> para) {
		List<BaseProperty> list = basePropertyDao.queryBaseProperty(para);
		// 改用Comparator实现按update_time降序排列
		Collections.sort(list, new BasePropertyComparator());
		return list;
	}

	@Override
	public BaseProperty loadBaseProById(String id) {
		return basePropertyDao.loadById(id);
	}

	@Override
	public void addBaseProperty(BaseProperty baseProperty) {
		basePropertyDao.addBaseProperty(baseProperty);
	}

	@Override
	public void updateBaseProperty(BaseProperty baseProperty) {
		basePropertyDao.updateBaseProperty(baseProperty);
	}

	@Override
	public void deleteBaseProperty(Map<String, Object> para) {
		basePropertyDao.deleteBaseProperty(para);
	}

	@Override
	public BaseProperty getPlatformProperty(Map<String, Object> map) throws Exception {
		BaseProperty bean = null;
		List<BaseProperty> list = basePropertyDao.queryBaseProperty(map);
		// 改用Comparator实现按update_time降序排列
//		Collections.sort(list, new BasePropertyComparator());
		if (list != null && list.size() > 0) {
			bean = list.get(0);
		}
		return bean;
	}

	@Override
	public List<BaseProperty> queryBasePropertyList(Map<String, Object> paramMap) {
		return basePropertyDao.listByCondition(paramMap);
	}

	@Override
	public List<?> getPlatformList() {
		List<BaseProperty> list = basePropertyDao.queryBaseProperty(new HashMap<String, Object>());
		// Collections.sort(list, new BasePropertyComparator());
		return list;
	}

}
