package com.whty.common.cache.data;

import com.whty.common.cache.tools.CacheContainer;

/*
 * 缓存数据工具
 * zhujg
 */
public class CacheDataUtils {

	/*
	 * 设置缓存数据
	 */
	public static void setData(String cacheName, String cacheKey, String cacheData) throws Exception {
		CacheContainer.getCache(cacheName).put(cacheKey, cacheData);
	}

	/*
	 * 获取缓存数据
	 */
	public static String getData(String cacheName, String cacheKey) throws Exception {
		return CacheContainer.getCache(cacheName).get(cacheKey);
	}

	/*
	 * 设置Object缓存数据
	 */
	public static void setObjectData(String cacheName, String cacheKey, Object cacheData) throws Exception {
		CacheContainer.getCache(cacheName).put(cacheKey, cacheData);
	}

	/*
	 * 设置Object缓存数据 :second 缓存时间（秒）
	 */
	public static void setObjectData(String cacheName, String cacheKey, Object cacheData, int second) throws Exception {
		CacheContainer.getCache(cacheName).put(cacheKey, cacheData, second);
	}

	/*
	 * 获取Object缓存数据
	 */
	public static Object getObjectData(String cacheName, String cacheKey) throws Exception {
		return CacheContainer.getCache(cacheName).get(cacheKey);
	}

	/*
	 * 删除Object缓存数据
	 */
	public static void removeObjectData(String cacheName, String cacheKey) throws Exception {
		CacheContainer.getCache(cacheName).delete(cacheKey);
	}

	/**
	 * 缓存数据工具
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
