package com.whty.mxbj.common.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.mxbj.api.model.BaseProperty;
import com.whty.mxbj.api.service.BasePropertyService;
import com.whty.mxbj.common.constants.SysConstants;

import net.rubyeye.xmemcached.MemcachedClient;

@Service
public class CacheInit {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BasePropertyService basePropertyService;

	@Autowired
	private MemcachedClient memcachedClient;

	public void init() {
		logger.info("-----------------------清除以前的数据缓存...---------------------");
		// CacheContainer.getCaches().clear();

		try {
			memcachedClient.flushAll();

			// memcachedClient.

			logger.info("-----------------------开始加载基础数据缓存...---------------------");
			addBasePropertyCache();// 缓存配置文件表T_BASE_PROPERTY数据
			logger.info("---------------------Finish 加载基础数据缓存-------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 加载参数配置表缓存数据
	 */
	public void addBasePropertyCache() {
		// 查询数据库里的数据
		List<BaseProperty> list = basePropertyService.find(new HashMap<String, Object>());

		try {
			for (BaseProperty bp : list) {
				StringBuffer keySb = new StringBuffer();
				keySb.append(SysConstants.BASE_PROPERTY_CACHE_KEY).append(SysConstants.CACHE_KEY_SEPERATER)
						.append(bp.getProperty_key()).append(SysConstants.CACHE_KEY_SEPERATER)
						.append(bp.getPlatform_code());
				System.out.println(keySb.toString());

				memcachedClient.add(keySb.toString(), generateExpireTime(), bp.getProperty_value());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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

}
