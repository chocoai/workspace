package com.whty.common.cache.tools;

import java.util.HashMap;

import com.whty.common.util.SysConfig;

/**
 * 缓存容器
 */
public class CacheContainer {

	public static String CACHE_KEY_BASE_SUBJECT = "BASE_SUBJECT";// 学科基础数据

	/**
	 * 缓存容器map
	 */
	private static HashMap<String, XMemCached> caches = new HashMap<String, XMemCached>();

	// private static Map<String, MemcachedClient> mmcs = new HashMap<String,
	// MemcachedClient>();

	// public static MemcachedClient whalinMemcachedCache(String cacheName) {
	// MemcachedClient mmc = mmcs.get(cacheName);
	//
	// if (mmc != null) {
	// return mmc;
	// }
	//
	// BasicConfigurator.configure();
	//
	// String[] servers = { SysConfig.getStrValue("memcache.ips") };
	// SockIOPool pool = SockIOPool.getInstance();
	// pool.setServers(servers);
	// pool.setFailover(true);
	// pool.setInitConn(10);
	// pool.setMinConn(5);
	// pool.setMaxConn(250);
	// pool.setMaintSleep(30);
	// pool.setNagle(false);
	// pool.setSocketTO(3000);
	// pool.setAliveCheck(true);
	// pool.initialize();
	//
	// mmc = new MemcachedClient();
	//
	// mmcs.put(cacheName, mmc);
	// return mmc;
	// }

	/**
	 * 根据缓存名获取Cache
	 * 
	 * @param cacheName
	 * @return
	 */
	public static XMemCached getCache(String cacheName) {
		XMemCached client = caches.get(cacheName);

		if (client != null) {
			return client;
		}

		client = new XMemCached(SysConfig.getStrValue("memcache.ips"), SysConfig.getStrValue("memcache.poolsize"),
				cacheName);
		caches.put(cacheName, client);

		return client;
	}

	/**
	 * 增加Cache
	 * 
	 * @param cacheName
	 * @param cache
	 */
	public static void addCache(String cacheName, XMemCached cache) {
		caches.put(cacheName, cache);
	}

	/**
	 * 移除Cache
	 * 
	 * @param cacheName
	 * @param cache
	 */
	public static void removeCache(String cacheName) {
		caches.remove(cacheName);
	}

	/**
	 * 获取所有缓存信息
	 */
	public static HashMap<String, XMemCached> getCaches() {
		return caches;
	}

	/**
	 * 获取所有缓存信息
	 */
	public static void clear(String cacheName) {
		try {
			XMemCached client = new XMemCached(SysConfig.getStrValue("memcache.ips"),
					SysConfig.getStrValue("memcache.poolsize"), cacheName);
			client.flushAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据参数获取缓存对象
	 * 
	 * @return
	 */
	public static XMemCached getMemCache(String cache_key) {
		return getCache(cache_key);
	}

	/**
	 * 获取接口数据缓存对象
	 * 
	 * @return
	 */
	public static XMemCached getCache_CACHE_KEY_BASE_SUBJECT() {
		return getCache(CACHE_KEY_BASE_SUBJECT);
	}

}
