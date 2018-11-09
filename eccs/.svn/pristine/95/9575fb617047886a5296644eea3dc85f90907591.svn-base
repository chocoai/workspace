package com.smart.web.action;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.smart.model.Dept;
import com.smart.model.ProceStepDef;
import com.smart.model.Project;
import com.smart.model.ProjectProcessHistory;
import com.smart.model.ProjectProcessState;
import com.smart.model.User;
import com.smart.service.ProceStepDefService;
import com.smart.service.ProjectProcessHistoryService;
import com.smart.service.ProjectProcessStateService;
import com.smart.util.DateUtils;
import com.smart.util.Property;
import com.smart.util.StringUtil;

public abstract class BaseAction extends ActionSupport {

	protected static final Logger log = Logger.getLogger(BaseAction.class);

	private static final long serialVersionUID = 1;

	protected int pageNo = 1;

	protected int pageSize = 10;

	@Autowired
	private ProceStepDefService proceStepDefService;

	@Autowired
	private ProjectProcessStateService processStateService;

	@Autowired
	private ProjectProcessHistoryService projectProcessHistoryService;

	/**
	 * 
	 * 获得Session
	 */
	public Map<String, Object> getSession() {
		return ActionContext.getContext().getSession();
	}

	/**
	 * 
	 * 获得ActionContext
	 */
	public ActionContext getActionContext() {
		return ActionContext.getContext();
	}

	/**
	 * 获得HttpServletRequest
	 */
	public HttpServletRequest getHttpServletRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 获得HttpServletResponse
	 */
	public HttpServletResponse getHttpServletResponse() {
		return ServletActionContext.getResponse();
	}

	public HttpSession getHttpSession() {
		return getHttpServletRequest().getSession();
	}

	/**
	 * 获得ServletContext
	 */
	public ServletContext getServletContext() {
		return ServletActionContext.getServletContext();
	}

	/**
	 * 获取传递的数据
	 * 
	 * @param param
	 * @return
	 */
	public String get(String param) {
		return getHttpServletRequest().getParameter(param);
	}

	/**
	 * 把变量放入actionContext中
	 * 
	 * @param key
	 * @param value
	 */
	public void put(String key, Object value) {
		getActionContext().put(key, value);
	}

	public void putRequest(String key, Object value) {
		getHttpServletRequest().setAttribute(key, value);
	}

	/**
	 * 向客户端写入信息
	 * 
	 * @param str
	 * @throws Exception
	 */
	public void write(String str) {
		try {
			getHttpServletResponse().getWriter().write(str);
		} catch (IOException e) {
			log.error("------------------ 返回数据错误 -----------------");
			e.printStackTrace();
		}
	}

	/**
	 * 向客户端写入json数据
	 * 
	 * @param jsonData
	 */
	public void writeJson(String jsonData) {
		try {
			getHttpServletResponse()
					.setContentType("application/json; charset=UTF-8");
			getHttpServletResponse().getWriter().write(jsonData);
		} catch (IOException e) {
			log.error("------------------ 返回数据错误 -----------------");
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public String getAttr(String key) {
		Map<String, String> map = (Map<String, String>) getServletContext()
				.getAttribute("Ctx");
		return map.get(key);
	}

	/**
	 * 登录用户
	 * 
	 * @return
	 */
	public User getUser() {
		return (User) getSession().get("user");
	}

	/**
	 * 登录用户的部门
	 * 
	 * @return
	 */
	public Dept getMyDept() {
		return (Dept) getSession().get("myDept");
	}

	/**
	 * 登录用户的部门id
	 * 
	 * @return
	 */
	public String getMyDeptId() {
		Dept dept = getMyDept();
		return dept != null ? dept.getId() : "";
	}

	/**
	 * 登录用户的公司
	 * 
	 * @return
	 */
	public Dept getMyCompany() {
		return (Dept) getSession().get("myCompany");
	}

	/**
	 * 获取保存附件的物理路径，如：E:\\tomcat-8.0.30-windows-x64\\8080\\webapps\\up_file\\
	 * 
	 * @return
	 */
	public String getAttachDir() {
		return Property.getProperty("/eccs.properties", "file.dir");
	}

	/**
	 * 登录用户的公司ID
	 * 
	 * @return
	 */
	public String getMyCompanyId() {
		Dept company = getMyCompany();
		return company != null ? company.getComid() : "";
	}

	/**
	 * 登录用户的账号
	 * 
	 * @return
	 */
	public Integer getUseId() {
		User user = getUser();
		return user != null ? user.getId() : 0;
	}

	/**
	 * 更新项目进程状态表信息
	 * 
	 * @paramer:projectId 项目id
	 * @paramer:StepCode 当前环节编码
	 * @paramer:type 项目所处类型
	 * @paramer:currentUsers 当前环节处理用户
	 * @return: void
	 */
	public void updatePsd(ProjectProcessState pps, String StepCode,
			String currentUsers) {
		// 更新项目进程状态表信息
		ProceStepDef nextStep = proceStepDefService.getStepByStepCode(StepCode);
		pps.setCurrentStep(nextStep);
		if (StringUtil.isBlank(currentUsers)) {
			pps.setCurrentState(ProjectProcessState.ARCHIVED);
		}
		pps.setCurrentUsers(currentUsers);
		pps.setLastUpdateTime(DateUtils.getCurrentTime());
		processStateService.update(pps);
	}

	/**
	 * 插入项目进程处理历史记录表
	 * 
	 * @paramer: project 项目
	 * @paramer: StepCode 当前环节编码
	 * @paramer: nextStepCode 下一环节编码
	 * @paramer: operateType 当前操作
	 * @paramer: nextHandlers 下一环节处理人
	 * @return: void
	 */
	public void savePph(Project project, String StepCode, String nextStepCode,
			String operateType, String nextHandlers) {
		ProceStepDef step = proceStepDefService.getStepByStepCode(StepCode);
		ProjectProcessHistory pph = new ProjectProcessHistory();
		if (!StringUtil.isBlank(nextStepCode)) {
			ProceStepDef nextStep = proceStepDefService
					.getStepByStepCode(nextStepCode);
			pph.setNextStep(nextStep);
			pph.setNextHandlers(nextHandlers);
		}
		pph.setProject(project);
		pph.setProjectInfo(project.getProjectInfo());
		pph.setOperateStep(step);
		pph.setOperateUser(getUser());
		pph.setOperateTime(DateUtils.getCurrentTime());
		pph.setOperateType(operateType);
		projectProcessHistoryService.save(pph);
	}

	public int getPageNo() {
		return Math.max(1, pageNo);
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
