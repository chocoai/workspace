/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.whty.common.util;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * Cache工具类
 * @author ThinkGem
 * @version 2013-5-29
 */
public class CacheUtils {
	
	private static Logger logger = LoggerFactory.getLogger(CacheUtils.class);
	private static CacheManager cacheManager = CacheManager.getInstance();
	
	private static final String SYS_CACHE = "areaCache";

	/**
	 * 获取SYS_CACHE缓存
	 * @param key
	 * @return
	 */
	public static Object get(String key) {
		return get(SYS_CACHE, key);
	}
	
	/**
	 * 获取SYS_CACHE缓存
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static Object get(String key, Object defaultValue) {
		Object value = get(key);
		return value != null ? value : defaultValue;
	}
	
	/**
	 * 写入SYS_CACHE缓存
	 * @param key
	 * @return
	 */
	public static void put(String key, Object value) {
		put(SYS_CACHE, key, value);
	}
	
	/**
	 * 从SYS_CACHE缓存中移除
	 * @param key
	 * @return
	 */
	public static void remove(String key) {
		remove(SYS_CACHE, key);
	}
	
	/**
	 * 获取缓存
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public static Object get(String cacheName, String key) {
		if(getCache(cacheName).get(key)==null){
			return null;
		}
		return getCache(cacheName).get(key).getObjectValue();
	}
	
	/**
	 * 获取缓存
	 * @param cacheName
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static Object get(String cacheName, String key, Object defaultValue) {
		Object value = get(cacheName, getKey(key));
		return value != null ? value : defaultValue;
	}
	
	/**
	 * 写入缓存
	 * @param cacheName
	 * @param key
	 * @param value
	 */
	public static void put(String cacheName, String key, Object value) {
		Element element = new Element(key, value);
		getCache(cacheName).put(element);
	}

	/**
	 * 从缓存中移除
	 * @param cacheName
	 * @param key
	 */
	public static void remove(String cacheName, String key) {
		getCache(cacheName).remove(getKey(key));
	}

	/**
	 * 从缓存中移除所有
	 * @param cacheName
	 */
	public static void removeAll(String cacheName) {
		Cache cache = getCache(cacheName);
		List<String> keys = cache.getKeys();
		for (Iterator<String> it = keys.iterator(); it.hasNext();){
			cache.remove(it.next());
		}
		logger.info("清理缓存： {} => {}", cacheName, keys);
	}
	
	/**
	 * 获取缓存键名，多数据源下增加数据源名称前缀
	 * @param key
	 * @return
	 */
	private static String getKey(String key){
//		String dsName = DataSourceHolder.getDataSourceName();
//		if (StringUtils.isNotBlank(dsName)){
//			return dsName + "_" + key;
//		}
		return key;
	}
	
	/**
	 * 获得一个Cache，没有则显示日志。
	 * @param cacheName
	 * @return
	 */
	public static Cache getCache(String cacheName){
		Cache cache = cacheManager.getCache(cacheName);
		if (cache == null){
			throw new RuntimeException("当前系统中没有定义“"+cacheName+"”这个缓存。");
		}
		return cache;
	}

}