package com.yhcrt.demo.core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.converters.ByteConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.FloatConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.beanutils.converters.ShortConverter;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.context.ApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.yhcrt.demo.util.Constant;
import com.yhcrt.demo.util.ExtJSBaseParameter;
import com.yhcrt.demo.util.Group;
import com.yhcrt.demo.util.Item;
import com.yhcrt.demo.util.JavaUtilDateConverter;
import com.yhcrt.demo.util.SystemCache;


/**
 * @author fengkun
 * @email 231788364@qq.com
 */
public class SystemInitListener implements ServletContextListener, WebApplicationInitializer {

	private static ApplicationContext applicationContext;

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	private void initConvertor() {
		ConvertUtils.register(new JavaUtilDateConverter(), java.util.Date.class);
		ConvertUtils.register(new ByteConverter(null), Byte.class);
		ConvertUtils.register(new ShortConverter(null), Short.class);
		ConvertUtils.register(new IntegerConverter(null), Integer.class);
		ConvertUtils.register(new LongConverter(null), Long.class);
		ConvertUtils.register(new FloatConverter(null), Float.class);
		ConvertUtils.register(new DoubleConverter(null), Double.class);
	}

	public void contextInitialized(ServletContextEvent event) {
		initConvertor();
		loadSystemParameter(event.getServletContext());
		applicationContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
	}

	private void loadSystemParameter(ServletContext servletContext) {
		String strGroup = "group";
		String strItem = "item";
		String strName = "name";
		String strKey = "key";
		String strValue = "value";

		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(getClass().getClassLoader().getResource(Constant.DICTIONARY_PATH));

			Element root = doc.getRootElement();
			Map<String, Group> dict = new HashMap<String, Group>();
			for (Iterator<Element> itGroup = root.elementIterator(strGroup); itGroup.hasNext();) {
				Element eleGroup = itGroup.next();
				String groupName = eleGroup.attributeValue(strName);
				Map<String, Item> map = new LinkedHashMap<String, Item>();
				for (Iterator<Element> itItem = eleGroup.elementIterator(strItem); itItem.hasNext();) {
					Element eleItem = itItem.next();
					Item item = new Item();
					item.setKey(eleItem.attributeValue(strKey));
					item.setValue(eleItem.attributeValue(strValue));
					map.put(item.getKey(), item);
				}
				dict.put(groupName, new Group(groupName, map));

			}
			servletContext.setAttribute(Constant.DICTIONARY, dict);
			SystemCache.DICTIONARY.putAll(dict);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onStartup(ServletContext arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
