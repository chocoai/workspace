/**
 * 
 */
package com.whty.common.aaa;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.whty.assis.manage.dao.SysErrorLogDao;
import com.whty.assis.manage.model.SysErrorLog;

/** 
 * @ClassName: SpringMVCSimpleMappingExceptionResolver 
 * @author: zjd
 * @date: 2018年6月11日 上午11:35:11  
 */
public class SpringHandlerExceptionResolver implements HandlerExceptionResolver {
	
	private static Logger log = Logger.getLogger(SpringHandlerExceptionResolver.class);

	@Autowired
	private SysErrorLogDao sysErrorLogDao;
	/* 
	 * @Title: 全局异常捕获
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @return 
	 */ 
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		Map<String, Object> model = new HashMap<String, Object>();  
        model.put("ex", ex);  
		SysErrorLog SysErrorLog = new SysErrorLog();
		if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;  
            String className = handlerMethod.getBeanType().getName();  
            String methodName = handlerMethod.getMethod().getName(); 
            StringWriter sw = new StringWriter();  
            ex.printStackTrace(new PrintWriter(sw, true));  
  
			SysErrorLog.setClassName(className);
			SysErrorLog.setMethodName(methodName);
			SysErrorLog.setCreateTime(new Date());
			SysErrorLog.setDescription(sw.toString());
			sysErrorLogDao.save(SysErrorLog);  
			
			log.info(">>>>>>系统异常，记录异常信息到数据库------end------");
		}
		 String viewName = "error/500";  
	        if (ex instanceof UnauthorizedException) {  // 后台系统用的shiro做权限控制，该异常也是shiro的异常  
	            viewName = "error/403";  
	        }  
	    return new ModelAndView(viewName, model);  
	}

}
