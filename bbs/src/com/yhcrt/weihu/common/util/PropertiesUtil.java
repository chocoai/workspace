package com.yhcrt.weihu.common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * 读取properties配置文件信息
 * 
 * @author 充满智慧的威哥
 */
public class PropertiesUtil {

    private PropertiesUtil() {
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

    public static String getPropertyValue(String propertyPath, String key) {
	String content = "";
	try {
	    DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
	    Resource resource = resourceLoader.getResource(propertyPath);
	    Properties properties = PropertiesLoaderUtils.loadProperties(resource);
	    content = properties.getProperty(key);
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return content;
    }

    public static int getProperty(HttpServletRequest request, String key) {
	String path = "/WEB-INF/config.properties";
	String value = PropertiesUtil.getProperty(request.getSession().getServletContext().getRealPath("/") + path,
		key);
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

}
