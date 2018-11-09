package com.whty.assis.base.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.whty.assis.demo.service_ht.ManageUserService;
import com.whty.assis.sysres.model.SysModular;
import com.whty.assis.sysres.service.SysModularService;
import com.whty.assis.sysrole.service.SysroleService;

/**
 * 拦截逻辑是：在未登录前，任何访问url都跳转到login页面；登录成功后跳转至先前的url
 * 
 * @author zhujg
 *
 */
public class CommonInterceptor extends HandlerInterceptorAdapter {

	// private static Logger logger =
	// Logger.getLogger(FrameworkController.class);
	protected static Logger logger = LoggerFactory.getLogger(CommonInterceptor.class);
	@Autowired
	private SysroleService sysroleService;

	@Autowired
	private ManageUserService manageUserService;
	
	@Autowired
	private SysModularService modularService;

	/**
	 * 在业务处理器处理请求之前被调用 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
	 * 如果返回true 执行下一个拦截器,直到所有的拦截器都执行完毕 再执行被拦截的Controller 然后进入拦截器链,
	 * 从最后一个拦截器往回执行所有的postHandle() 接着再从最后一个拦截器往回执行所有的afterCompletion()
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestType = request.getHeader("X-Requested-With");
		if("XMLHttpRequest".equals(requestType)){//判断是不是Ajax请求
            return true;
        }
		// logger.info("==============执行顺序: 1、preHandle================");
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length()+1);
		@SuppressWarnings("unchecked")
		List<SysModular> modularList = (List<SysModular>) request.getSession().getAttribute("modularList2");
		String buttons = "";
		for(SysModular sysModular : modularList){
				if(sysModular.getModularPath().equals(url)){
					buttons =sysModular.getButtons();
			}
		}
		if(buttons!=""){
			String[] buttonList = buttons.split(",");
			for(String button :buttonList){
				if(button.equals("添加")){
					request.setAttribute("add", "add");
				}
				if(button.equals("删除")){
					request.setAttribute("del", "del");
				}
				if(button.equals("修改")){
					request.setAttribute("upd", "upd");
				}
				if(button.equals("查询")){
					request.setAttribute("sel", "sel");
				}
				if(button.equals("导入")){
					request.setAttribute("imp", "imp");
				}
				if(button.equals("导出")){
					request.setAttribute("exp", "exp");
				}
			}
		}
		// logger.info("requestUri:" + requestUri);
		// logger.info("contextPath:" + contextPath);
		// logger.info("url:" + url);

//		String login_page = "login.html";
//		if (url.contains(login_page)) {
//			return true;
//		} else if (url.split("/").length == 3) {
//			login_page = "../" + login_page;
//		} else if (url.split("/").length == 4) {
//			login_page = "../../" + login_page;
//		}

		// ManageUserInfo mUser =
		// (ManageUserInfo)request.getSession().getAttribute("manageUser");
		//
		// request.getSession().setAttribute("manageUser", mUser);
		// mUser.setLast_time(new Date());
		// mUser.setLogin_count(1);
		// mUser.setUpdate_time(new Date());
		// manageUserService.updateManageUser(mUser);
		// Map map = new HashMap();
		// map.put("user_id", mUser.getId());
		// List<SysModular> sysModularList =
		// sysroleService.queryUserSysModularList(map);
		// request.getSession().removeAttribute("sysModularList");
		// request.getSession().setAttribute("sysModularList", sysModularList);

//		ManageUserInfo mUser = (ManageUserInfo) request.getSession().getAttribute("manageUser");
//		if (mUser == null) {
			// logger.info("Interceptor：跳转到login页面！");
			// request.getRequestDispatcher("login.html").forward(request,
			// response);
			// response.sendRedirect(login_page);
//			response.setCharacterEncoding("utf-8");
//			response.setContentType("text/html");
//			response.getWriter().print("<script>window.top.location.href='" + contextPath + "/login.html';</script>");
//			return false;
//		} else {
			return true;
//		}
	}

	/**
	 * 在业务处理器处理请求执行完成后,生成视图之前执行的动作 可在modelAndView中加入数据，比如当前时间
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		/*List<Modular> modularList = modularService.queryAllModular();
		modelAndView.addObject("modularList", modularList);*/
		// logger.info("==============执行顺序: 2、postHandle================");
		// if(modelAndView != null){ //加入当前时间
		// modelAndView.addObject("var", "测试postHandle");
		// }
	}

	/**
	 * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
	 * 
	 * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// logger.info("==============执行顺序: 3、afterCompletion================");
	}

}
