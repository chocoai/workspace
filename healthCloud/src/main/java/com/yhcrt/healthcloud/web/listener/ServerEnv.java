package com.yhcrt.healthcloud.web.listener;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.springframework.web.context.WebApplicationContext;

/**
 * @ClassName: ServerEnv
 * @Description:系统环境变量<br/>
 * @version V1.0
 * @author rpf
 * @date: 2017年8月23日
 */
public final class ServerEnv {
	private ServerEnv() {
		serviceHome = null;
	}

	public static ServerEnv getInstance() {
		return INSTANCE;
	}

	public String getRapVersion() {
		return "1.0.0";
	}

	public String getServiceHome() {
		return serviceHome;
	}

	public void setServiceHome(String serviceHome) {
		if (this.serviceHome == null) {
			this.serviceHome = serviceHome;
		}
	}

	public void init(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public DataSource getDataSource() {
		return (DataSource) getBean("dataSource");
	}

	public Object getBean(String name) {
		WebApplicationContext webcontext = (WebApplicationContext) servletContext
				.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		return webcontext.getBean(name);
	}

	public Object getBean(Class<?> name) {
		WebApplicationContext webcontext = (WebApplicationContext) servletContext
				.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		return webcontext.getBean(name);
	}

	private static final ServerEnv INSTANCE = new ServerEnv();
	private ServletContext servletContext;
	private String serviceHome;
}
