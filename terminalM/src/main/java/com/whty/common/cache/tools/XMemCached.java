/**
 * 
 */
package com.whty.common.cache.tools;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.whty.common.util.SysConfig;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;
import net.rubyeye.xmemcached.utils.AddrUtil;

/**
 * @Description:缓存操作接口，提供基本缓存存取操作
 * @version: 2011-5-27
 * @author: yangkun
 */
public class XMemCached {

	/**
	 * logger.
	 */
//	private static final Logger LOGGER = Logger.getLogger(XMemCached.class);
	private static Logger LOGGER = LoggerFactory.getLogger(XMemCached.class);
	public static final String KEY_SEPERATER = "|";

	public static final String KEY_SEPERATER2="_"; //防止memcache |字符转义成 %7C
	
	private String keyType = "commonCache";

	/**
	 * xmemcache jar提供的客户端对象
	 */
	private MemcachedClient client;

	String ips = "localhost:21211";
	String connectionPoolSize = "2";

	public XMemCached(String ips, String connectionPoolSize, String keyType) {
		// 初始化服务端的IP和端口
		if (StringUtils.isNotEmpty(ips)) {
			this.ips = ips;
		}
		if (StringUtils.isNotEmpty(connectionPoolSize)) {
			this.connectionPoolSize = connectionPoolSize;
		}
		if (StringUtils.isNotEmpty(keyType)) {
			this.keyType = keyType;
		}

		MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(ips));

		// 启用此语句需要检查Xmemcached和memcached的版本号；支持二进制协议
		// builder.setCommandFactory(new BinaryCommandFactory());

		// 指定客户端在 做分布式服务器算法时，采用"一致性哈希"。
		builder.setSessionLocator(new KetamaMemcachedSessionLocator());

		try {
			// 设置连接池大小
			builder.setConnectionPoolSize(Integer.parseInt(connectionPoolSize));

			// 创建XMemCached客户端
			client = builder.build();
			client.setOpTimeout(SysConfig.getIntValue("memcache.cacheOpTimeOut"));
			// client.setEnableHeartBeat(false);
		} catch (IOException e) {
			LOGGER.error("初始化XMemcache错误：", e);
		}
	}

	/**
	 * 默认缓存1个月
	 * 
	 * @param key
	 * @param value
	 *            缓存对象（需要序列化）
	 * @return
	 * @throws Exception
	 */
	public boolean put(String key, Object value) throws Exception {
		return client.set(keyType + KEY_SEPERATER + key, 0, value);
	}

	/**
	 * 缓存数据
	 * 
	 * @param key
	 * @param value
	 *            缓存对象（需要序列化）
	 * @param second
	 *            缓存多少秒
	 * @return
	 * @throws Exception
	 */
	public boolean put(String key, Object value, int second) throws Exception {
		return client.set(keyType + KEY_SEPERATER + key, second, value);
	}

	/**
	 * 根据Key取缓存
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public <T> T get(String key) throws Exception {		
		return client.get(keyType + KEY_SEPERATER + key);
	}

	/**
	 * 根据Keys批量取缓存
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public <T> Map<String, T> get(List<String> keys) throws Exception {
		if (keys == null) {
			return null;
		}

		return client.get(keyType + KEY_SEPERATER + keys);
	}

	/**
	 * 更新缓存，缓存时间默认一个月
	 * 
	 * @param key
	 * @param value
	 *            缓存对象（需要序列化）
	 * @return
	 * @throws Exception
	 */
	public boolean update(String key, Object value) throws Exception {

		return client.replace(keyType + KEY_SEPERATER + key, 0, value);
	}

	/**
	 * 更新缓存
	 * 
	 * @param key
	 * @param value
	 *            缓存对象（需要序列化）
	 * @param second
	 *            缓存多少秒
	 * @return
	 * @throws Exception
	 */
	public boolean update(String key, Object value, int second) throws Exception {

		return client.replace(keyType + KEY_SEPERATER + key, second, value);
	}

	/**
	 * 删除缓存
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public boolean delete(String key) throws Exception {
		return client.delete(keyType + KEY_SEPERATER + key);
	}

	/**
	 * 默认缓存1个月
	 * 
	 * @param key
	 * @param value
	 *            缓存对象（需要序列化）
	 * @return
	 * @throws Exception
	 */
	public boolean putNoPerffix(String key, Object value) throws Exception {

		return client.set(key, 0, value);
	}

	/**
	 * 缓存数据
	 * 
	 * @param key
	 * @param value
	 *            缓存对象（需要序列化）
	 * @param second
	 *            缓存多少秒
	 * @return
	 * @throws Exception
	 */
	public boolean putNoPerffix(String key, Object value, int second) throws Exception {

		return client.set(key, second, value);
	}

	/**
	 * 根据Key取缓存
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public <T> T getNoPerffix(String key) throws Exception {

		return client.get(key);
	}

	/**
	 * 根据Keys批量取缓存
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public <T> Map<String, T> getNoPerffix(List<String> keys) throws Exception {
		if (keys == null) {
			return null;
		}

		return client.get(keys);
	}

	/**
	 * 更新缓存，缓存时间默认一个月
	 * 
	 * @param key
	 * @param value
	 *            缓存对象（需要序列化）
	 * @return
	 * @throws Exception
	 */
	public boolean updateNoPerffix(String key, Object value) throws Exception {

		return client.replace(key, 0, value);
	}

	/**
	 * 更新缓存
	 * 
	 * @param key
	 * @param value
	 *            缓存对象（需要序列化）
	 * @param second
	 *            缓存多少秒
	 * @return
	 * @throws Exception
	 */
	public boolean updateNoPerffix(String key, Object value, int second) throws Exception {

		return client.replace(key, second, value);
	}

	/**
	 * 删除缓存
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public boolean deleteNoPerffix(String key) throws Exception {
		return client.delete(key);
	}

	/**
	 * 使所有缓存内容失效
	 * 
	 * @throws MemcachedException
	 * @throws InterruptedException
	 * @throws TimeoutException
	 */
	public void flushAll() throws Exception {
		client.flushAll();
	}

	/**
	 * 缓存实例关闭，服务器关闭时调用
	 * 
	 * @throws IOException
	 */
	public void shutdown() throws IOException {
		client.shutdown();
	}

	public MemcachedClient getCacheClient() {
		return client;
	}

}
