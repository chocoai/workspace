
package com.yhcrt.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.yhcrt.entity.system.SysConfig;
import com.yhcrt.service.stsyem.SysConfigService;
import com.yhcrt.utils.SpringUtil;

/**
 * 启动加载 初始化系统数据
 * @author 陈伟
 * 2017年5月23日 下午9:38:58
 * 版权所有：武汉炎黄创新服务有限公司
 */
public class ServletContextLoaderListener implements ServletContextListener {
	private static final Logger log = Logger.getLogger(ServletContextLoaderListener.class);
	
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		log.info("start 系统初始化...");
		
		ServletContext servletContext = servletContextEvent.getServletContext();
		/** 项目路径 **/
		String contextPath = servletContext.getRealPath("/");
		
		/** 配置文件 **/
		//Map<String, String> ctx = PropertiesUtil.getProperties(contextPath + "/WEB-INF/classes/properties/pwd.properties");
		
		/** 读取系统配置信息 **/
		SysConfigService sysConfigService = SpringUtil.getBean(SysConfigService.class);
		SysConfig sysConfig = null;
		try {
			sysConfig = sysConfigService.getByConfig();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		servletContext.setAttribute("contextPath", contextPath);
		servletContext.setAttribute("sysConfig", sysConfig);
		//servletContext.setAttribute("ctx", ctx);
        log.info("end 系统初始化...");
	}

    public void contextDestroyed(ServletContextEvent servletContextEvent) {  
    	//释放资源
    }   

}