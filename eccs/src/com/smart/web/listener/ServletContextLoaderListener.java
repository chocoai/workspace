package com.smart.web.listener;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.smart.model.Dept;
import com.smart.service.DeptService;
import com.smart.util.Property;
import com.smart.util.SpringUtil;

/**
 * 启动加载 初始化系统数据
 * 
 * @author 充满智慧的威哥
 */
public class ServletContextLoaderListener implements ServletContextListener {
    private static final Logger log = Logger.getLogger(ServletContextLoaderListener.class);

    public void contextInitialized(ServletContextEvent servletContextEvent) {
	log.info("start 系统初始化...");

	ServletContext servletContext = servletContextEvent.getServletContext();

	/** 配置文件 **/
	String rootPath = servletContext.getRealPath("/");
	String path = servletContext.getContextPath();
	Map<String, String> ctx = Property.getProperties(rootPath + "/WEB-INF/classes/config.properties");
	ctx.put("rootPath", rootPath); // 项目路径也放进去
	servletContext.setAttribute("path", path);

	/** dict配置文件 **/
	// DictService dictService = SpringUtil.getBean(DictService.class);
	// List<Dict> dictList = dictService.getList();
	// for (Dict dict : dictList) {
	// String key = dict.getId();
	// String value = dict.getValue();
	// ctx.put(key, value);
	// }

	servletContext.setAttribute("ctx", ctx);

	/** 是否启用memcached **/
	// String memcached = ctxAttr.get("memcached");
	// if (!StringUtil.isBlank(memcached) && "true".equals(memcached)) {
	// CacheManager cacheManager = SpringUtil.getBean(CacheManager.class);
	// Map<String, HashMap<String, Object>> map = new HashMap<String,
	// HashMap<String, Object>>();
	// map.put("User", new HashMap<String, Object>());
	// map.put("Info", new HashMap<String, Object>());
	// cacheManager.put("cache", map);
	// log.info("启用缓存");
	// }

	/** 创建上传文件夹 **/
	// String dirTempPath = ctx.get("TEMP_DIR");
	// AppUtil.createDir(dirTempPath);
	//
	// dirTempPath = ctx.get("FILE_DIR");
	// AppUtil.createDir(dirTempPath);
	//
	// dirTempPath = ctx.get("IMG_DIR");
	// AppUtil.createDir(dirTempPath);
	//
	// dirTempPath = ctx.get("IMG_INFO_DIR");
	// AppUtil.createDir(dirTempPath);
	//
	// dirTempPath = ctx.get("IMG_PHOTO_DIR");
	// AppUtil.createDir(dirTempPath);
	//
	// dirTempPath = ctx.get("USER_HEAD_DIR");
	// AppUtil.createDir(dirTempPath);
	//
	// dirTempPath = ctx.get("KD_IMG_DIR");
	// AppUtil.createDir(dirTempPath);

	/** 部门 **/
	DeptService deptService = SpringUtil.getBean(DeptService.class);
	List<Dept> deptList = deptService.getList();
	servletContext.setAttribute("deptList", deptList);

	/** 权限列表 **/
	// ResService resService = SpringUtil.getBean(ResService.class);
	// List<Res> resList = resService.getList();
	// servletContext.setAttribute("resList", resList);

	log.info("end 系统初始化...");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
	// 释放资源
    }

}
