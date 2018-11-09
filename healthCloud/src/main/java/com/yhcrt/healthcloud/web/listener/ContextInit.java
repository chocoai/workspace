package com.yhcrt.healthcloud.web.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ContextLoaderListener;

import com.yhcrt.healthcloud.system.entity.SysDictionary;
import com.yhcrt.healthcloud.system.service.SysDictService;
import com.yhcrt.healthcloud.system.service.impl.SysDictServiceImpl;
import com.yhcrt.healthcloud.util.DictSet;

/**
 * @ClassName: ContextInit
 * @Description:Spring启动类,扩展将数据字典存入Application
 * @version V1.0
 * @author rpf
 * @date: 2017年8月23日
 */
public class ContextInit extends ContextLoaderListener implements ServletContextListener {
	public ContextInit() {
	}

	public static ServletContext getServletContext() {
		return servletContext;
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		servletContext = sce.getServletContext();
		ServerEnv env = ServerEnv.getInstance();
		env.init(servletContext);
		env.setServiceHome(servletContext.getRealPath("/"));
		super.contextInitialized(sce);

		try {
			// 字典
			SysDictService dicMgr = (SysDictService) env.getBean(SysDictServiceImpl.class);
			List<SysDictionary> dictList = dicMgr.listAllSysDict();
			DictSet.getInstanse(dictList, servletContext);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

	@SuppressWarnings("unused")
	private static Log log = LogFactory.getLog(ContextInit.class);
	private static ServletContext servletContext = null;
}
