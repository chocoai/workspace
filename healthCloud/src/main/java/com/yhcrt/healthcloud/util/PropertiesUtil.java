package com.yhcrt.healthcloud.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 资源文件读取工具读取工具类
 *
 */
public final class PropertiesUtil extends PropertyPlaceholderConfigurer {
	private static final byte[] KEY = { 9, -1, 0, 5, 39, 8, 6, 19 };
	private static Map<String, String> ctxPropertiesMap;
	private List<String> decryptProperties;

	@Override
	protected void loadProperties(Properties props) throws IOException {
		super.loadProperties(props);
		ctxPropertiesMap = new HashMap<String, String>();
		for (Object key : props.keySet()) {
			String keyStr = key.toString();
			String value = props.getProperty(keyStr);
			if (decryptProperties != null && decryptProperties.contains(keyStr)) {
				value = SecurityUtil.decryptDes(value, KEY);
				props.setProperty(keyStr, value);
			}
			ctxPropertiesMap.put(keyStr, value);
		}
	}

	/**
	 * @param decryptPropertiesMap
	 *            the decryptPropertiesMap to set
	 */
	public void setDecryptProperties(List<String> decryptProperties) {
		this.decryptProperties = decryptProperties;
	}

	/**
	 * Get a value based on key , if key does not exist , null is returned
	 * 
	 * @param key
	 * @return
	 */
	public static String getString(String key) {
		try {
			return ctxPropertiesMap.get(key);
		} catch (MissingResourceException e) {
			return null;
		}
	}

	/**
	 * 根据key获取值
	 * 
	 * @param key
	 * @return
	 */
	public static int getInt(String key) {
		return Integer.parseInt(ctxPropertiesMap.get(key));
	}

	/**
	 * 根据key获取值
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static int getInt(String key, int defaultValue) {
		String value = ctxPropertiesMap.get(key);
		if (StringUtils.isBlank(value)) {
			return defaultValue;
		}
		return Integer.parseInt(value);
	}

	/**
	 * 根据key获取值
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static boolean getBoolean(String key, boolean defaultValue) {
		String value = ctxPropertiesMap.get(key);
		if (StringUtils.isBlank(value)) {
			return defaultValue;
		}
		return new Boolean(value);
	}

	/**
	 * 根据主键查找配置文件中的信息
	 * 
	 * @param configPath
	 *            配置文件路径 例如: 当前项目跟路径 + /WEB-INF/config.properties
	 * @param key
	 *            根据键找值
	 * @return
	 */
	public static String getProperty(String configPath, String key) {
		Properties pro = new Properties();
		InputStream in = null;
		String content = null;
		try {
			in = PropertiesUtil.class.getResourceAsStream(configPath);
			pro.load(in);
			content = pro.getProperty(key);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		return content;
	}

	public static int getProperty(HttpServletRequest request, String key) {
		String path = "/WEB-INF/config.properties";
		String realPath = request.getSession().getServletContext().getRealPath("/");
		String value = PropertiesUtil.getProperty(realPath + path, key);
		return Integer.parseInt(value);
	}

	/**
	 * 查找配置文件中所有信息
	 * 
	 * @param configPath
	 *            配置文件路径
	 * @return
	 */
	public static Map<String, String> getProperties(String configPath) {
		Map<String, String> map = new HashMap<String, String>();
		Properties pro = new Properties();
		FileInputStream fin = null;
		try {
			fin = new FileInputStream(configPath);
			pro.load(fin);
			Set<Object> set = pro.keySet();
			for (Iterator<Object> iterator = set.iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				map.put(key, pro.getProperty(key));
			}
			fin.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	// 写入Properties信息
	public static void WriteProperties(String filePath, String pKey, String pValue) {

		try {
			Properties pps = new Properties();
			InputStream in = new FileInputStream(filePath);
			// 从输入流中读取属性列表（键和元素对）
			pps.load(in);
			// 构造输出流
			OutputStream out = new FileOutputStream(filePath);
			pps.setProperty(pKey, pValue);
			// 以适合使用 load 方法加载到 Properties 表中的格式，
			// 将此 Properties 表中的属性列表（键和元素对）写入输出流
			pps.store(out, "Update " + pKey);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		String encrypt = SecurityUtil.encryptDes("buzhidao", KEY);
		System.out.println(encrypt);
		System.out.println(SecurityUtil.decryptDes(encrypt, KEY));
	}
}
