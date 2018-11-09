package com.smart.web.action.step3;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.model.ProceStepDef;
import com.smart.model.Project;
import com.smart.model.ProjectProcessState;
import com.smart.model.ProjectType;
import com.smart.model.Step3;
import com.smart.model.Step3Worker;
import com.smart.model.Step3WorkerTemplate;
import com.smart.model.T_hremployee;
import com.smart.service.ProceStepDefService;
import com.smart.service.ProjectProcessStateService;
import com.smart.service.ProjectService;
import com.smart.service.ProjectTypeService;
import com.smart.service.Step3Service;
import com.smart.service.Step3WorkerService;
import com.smart.service.T_hremployeeService;
import com.smart.util.Constants;
import com.smart.util.EmailSendTool;
import com.smart.util.Page;
import com.smart.util.ReflectionUtil;
import com.smart.util.StringUtil;
import com.smart.util.TemplateUtil;
import com.smart.web.action.BaseAction;

/**
 * 说明：根据包名的路径自动装配 比如本action路径，com.smart.demo.DemoAction，对应的页面文件夹content/demo
 * return "new" 则会找到content/demo/new.ftl
 * 
 * control-user 权限拦截，使用UserInterceptor拦截器
 */
@ParentPackage("control-user")
public class Step3Action extends BaseAction {

	private static final long serialVersionUID = -5268202866310768364L;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private Step3Service step3Service;

	@Autowired
	private ProjectTypeService projectTypeService;

	@Autowired
	private Step3WorkerService step3WorkerService;

	@Autowired
	private ProjectProcessStateService processStateService;

	@Autowired
	private ProceStepDefService proceStepDefService;
	
	@Autowired
	private T_hremployeeService hremployeeService;

	private Project project; // 项目

	private Step3 step3;

	private Integer projectTypeId;// 工程类型

	private Integer consultTypeId;// 暂无用

	private Integer nextWorkerId; // 下一步工作指定人

	private Integer projectid;

	/**
	 * 新建
	 */
	@Action("new")
	public String _new() {
		String ctime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		put("ctime", ctime);

		step3 = new Step3();
		return "new";
	}

	/**
	 * 保存并执行下一步
	 */
	@Action(value = "toNextStep", results = {
			@Result(name = "workflowNormal", location = "../project/workflow", type = "redirectAction", params = {
					"id", "%{project.id}" }),
			@Result(name = "myProjectList", location = "../project/myProjectList", type = "redirectAction") })
	public String toNextStep() {
		if (project.getId() != null) {
			project = projectService.get(project.getId());
		}
		Step3 oldStep3 = step3Service.get(step3.getId());
		// 属性拷贝，把更新内容拷贝到数据库查出的project里，最后的那个参数是不需要拷贝的属性名称
		ReflectionUtil.bean2Bean(oldStep3, step3, "id, ctime, status");
		oldStep3.setUser(this.getUser());
		oldStep3.setDept(this.getMyDept());
		oldStep3.setProject(project);
		oldStep3.setConfirmTime(new Date());
		step3Service.update(oldStep3);

		Step3Worker step3Worker = step3WorkerService.get(project.getId(),
				ProjectProcessState.TYPE_PM,
				Integer.parseInt(Constants.StepCode.STEP4));
		ProjectProcessState pps = processStateService
				.getOneByProjectIdAndBusinesstype(project.getId(),
						Integer.parseInt(ProjectProcessState.TYPE_PM));
		// 更新项目进程状态表信息
		updatePsd(pps, Constants.StepCode.STEP4, step3Worker.getWorkUserId());
		// 更新项目进程处理历史记录表
		savePph(project, Constants.StepCode.STEP3, Constants.StepCode.STEP4,
				Constants.OperateType.SUBMIT, step3Worker.getWorkUserId());
		
		
		// 发送邮件通知下一步处理人有待办事项需要处理
		// 查询下一步处理人的邮箱地址
		StringBuilder emailAddress = new StringBuilder();
		if (StringUtils.isNotBlank(step3Worker.getWorkUserId())) {
			String[] userIds = step3Worker.getWorkUserId().split(",");
			for (int i = 0; i < userIds.length; i++) {
				if (StringUtils.isNumeric(userIds[i])) {
					T_hremployee hrEmployee = hremployeeService
							.getSysCode(Integer.valueOf(userIds[i]));
					if (hrEmployee != null) {
						emailAddress.append(hrEmployee.getEmail()).append(",");
					}
				}
			}
			ProceStepDef nextStep = proceStepDefService
					.getStepByStepCode(Constants.StepCode.STEP4);
			EmailSendTool sendEmail = new EmailSendTool();
			String subject = sendEmail.getSubject();
			String emailContent = sendEmail.buildContent(project.getNo(),
					project.getName(), nextStep.getStepName());
			sendEmail.send(emailAddress.toString(), subject, emailContent);
		}

		if (StringUtil.str1ToStr2(step3Worker.getWorkUserId(),
				Integer.toString(getUser().getId()), ",")) {
			return "workflowNormal";
		} else {
			return "myProjectList";
		}
	}

	/**
	 * 保存
	 */
	@Action(value = "save", results = {
			@Result(name = "workflowNormal", location = "../project/workflow", type = "redirectAction", params = {
					"id", "%{project.id}" }),
			@Result(name = "myProjectList", location = "../project/myProjectList", type = "redirectAction") })
	public String save() {
		if (project.getId() != null) {
			project = projectService.get(project.getId());
		}
		Step3 oldStep3 = step3Service.get(step3.getId());
		// 属性拷贝，把更新内容拷贝到数据库查出的project里，最后的那个参数是不需要拷贝的属性名称
		ReflectionUtil.bean2Bean(oldStep3, step3, "id, ctime, status");
		oldStep3.setUser(this.getUser());
		oldStep3.setDept(this.getMyDept());
		oldStep3.setProject(project);
		step3Service.update(oldStep3);

		boolean is = false;
		ProjectProcessState pps = processStateService
				.getOneByProjectIdAndBusinesstype(project.getId(),
						Integer.parseInt(ProjectProcessState.TYPE_PM));

		if (nextWorkerId != null) { //转交给其它用户
			String[] userIds = StringUtil.str1RemoveStr3(pps.getCurrentUsers(),
					Integer.toString(getUser().getId()),
					Integer.toString(nextWorkerId), ",");
			// 更新项目进程状态表信息
			updatePsd(pps, Constants.StepCode.STEP3,
					StringUtil.array2Str(userIds, ","));
			// 更新项目进程处理历史记录表
			savePph(project, Constants.StepCode.STEP3, Constants.StepCode.STEP3,
					Constants.OperateType.TRANSFER,
					StringUtil.array2Str(userIds, ","));
			is = false;
			// 发送邮件通知下一步处理人有待办事项需要处理
			// 查询下一步处理人的邮箱地址
			StringBuilder emailAddress = new StringBuilder();
			for (int i = 0; i < userIds.length; i++) {
				if (StringUtils.isNumeric(userIds[i])) {
					T_hremployee hrEmployee = hremployeeService
							.getSysCode(Integer.valueOf(userIds[i]));
					if (hrEmployee != null) {
						emailAddress.append(hrEmployee.getEmail()).append(",");
					}
				}
			}
			ProceStepDef nextStep = proceStepDefService
					.getStepByStepCode(Constants.StepCode.STEP3);
			EmailSendTool sendEmail = new EmailSendTool();
			String subject = sendEmail.getSubject();
			String emailContent = sendEmail.buildContent(project.getNo(),
					project.getName(), nextStep.getStepName());
			sendEmail.send(emailAddress.toString(), subject, emailContent);
		} else {
			// 更新项目进程处理历史记录表
			savePph(project, Constants.StepCode.STEP3, Constants.StepCode.STEP3,
					Constants.OperateType.SAVE, pps.getCurrentUsers());
			is = true;
		}
		if (is) {
			return "workflowNormal";
		} else {
			return "myProjectList";
		}
	}

	/**
	 * 编辑
	 */
	@Action("edit")
	public String edit() {
		if (project != null && project.getId() != null) {
			step3 = step3Service.getByProjectId(project.getId());
		} else {
			step3 = step3Service.get(step3.getId());
		}
		project = step3.getProject();
		List<ProceStepDef> psdList = proceStepDefService
				.getProjectByStep(ProjectProcessState.TYPE_PM, false);
		psdList.get(0).setStepName("项目负责人");
		List<Step3Worker> swList = step3WorkerService.getByProjectId(
				project.getId(), ProjectProcessState.TYPE_PM, null);
		List<Step3WorkerTemplate> swtList = TemplateUtil
				.Step3WorkerTemplateList(psdList, swList);
		put("swtList", swtList);
		return "new";
	}

	/**
	 * 列表
	 */
	@Action("list")
	public String list() {
		List<ProjectType> projectTypeList = projectTypeService.getList();
		put("projectTypeList", projectTypeList);
		project = project == null ? new Project() : project;
		if (projectid != null && projectid != 0) {
			project = projectService.get(projectid);
		}
		Page<Step3> pageBean = step3Service.getPage(getPageNo(), getPageSize(),
				project.getNo(), project.getName(), projectTypeId,
				Integer.parseInt(Constants.StepCode.STEP3));
		put("pageBean", pageBean);
		return "list";
	}

	/**
	 * 显示
	 * 
	 * @return
	 */
	@Action("show")
	public String show() {
		if (project != null && project.getId() != null) {
			step3 = step3Service.getByProjectId(project.getId());
			project = step3.getProject();

			List<ProceStepDef> psdList = proceStepDefService
					.getProjectByStep(ProjectProcessState.TYPE_PM, false);
			psdList.get(0).setStepName("项目负责人");
			List<Step3Worker> swList = step3WorkerService.getByProjectId(
					project.getId(), ProjectProcessState.TYPE_PM, null);
			List<Step3WorkerTemplate> swtList = TemplateUtil
					.Step3WorkerTemplateList(psdList, swList);
			put("swtList", swtList);
		}
		return "show";
	}

	/**
	 * 打印
	 * 
	 * @return
	 */
	@Action("print")
	public String print() {
		if (project != null && project.getId() != null) {
			step3 = step3Service.getByProjectId(project.getId());
			project = step3.getProject();

			List<ProceStepDef> psdList = proceStepDefService
					.getProjectByStep(ProjectProcessState.TYPE_PM, false);
			psdList.get(0).setStepName("项目负责人");
			List<Step3Worker> swList = step3WorkerService.getByProjectId(
					project.getId(), ProjectProcessState.TYPE_PM, null);
			List<Step3WorkerTemplate> swtList = TemplateUtil
					.Step3WorkerTemplateList(psdList, swList);
			put("swtList", swtList);
		}
		return "print";
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@Action("delete")
	public String delete() {
		step3 = step3Service.get(step3.getId());
		step3.setStatus(-1);
		step3Service.update(step3);

		write("1"); // ajax请求用write返回数据
		return null;
	}

	// ====================== getter and setter ===========================
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Step3 getStep3() {
		return step3;
	}

	public void setStep3(Step3 step3) {
		this.step3 = step3;
	}

	public Integer getProjectTypeId() {
		return projectTypeId;
	}

	public void setProjectTypeId(Integer projectTypeId) {
		this.projectTypeId = projectTypeId;
	}

	public Integer getConsultTypeId() {
		return consultTypeId;
	}

	public void setConsultTypeId(Integer consultTypeId) {
		this.consultTypeId = consultTypeId;
	}

	public Integer getNextWorkerId() {
		return nextWorkerId;
	}

	public void setNextWorkerId(Integer nextWorkerId) {
		this.nextWorkerId = nextWorkerId;
	}

	public Integer getProjectid() {
		return projectid;
	}

	public void setProjectid(Integer projectid) {
		this.projectid = projectid;
	}

}
