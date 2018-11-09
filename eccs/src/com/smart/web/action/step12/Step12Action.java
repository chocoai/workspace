package com.smart.web.action.step12;

import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.model.Project;
import com.smart.model.ProjectProcessState;
import com.smart.model.ProjectType;
import com.smart.model.Requisition;
import com.smart.model.Step12;
import com.smart.model.Step3Worker;
import com.smart.model.User;
import com.smart.service.EmailService;
import com.smart.service.ProjectProcessStateService;
import com.smart.service.ProjectService;
import com.smart.service.ProjectTypeService;
import com.smart.service.RequisitionService;
import com.smart.service.Step12Service;
import com.smart.service.Step3WorkerService;

import com.smart.service.UserService;
import com.smart.util.Constants;
import com.smart.util.Page;
import com.smart.util.ReflectionUtil;
import com.smart.util.SpringUtil;
import com.smart.util.StringUtil;
import com.smart.web.action.BaseAction;

/**
 * 说明：根据包名的路径自动装配 比如本action路径，com.smart.demo.DemoAction，对应的页面文件夹content/demo
 * return "new" 则会找到content/demo/new.ftl
 * 
 * control-user 权限拦截，使用UserInterceptor拦截器
 */
@ParentPackage("control-user")
public class Step12Action extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private UserService userService;
	
	@Autowired
	private RequisitionService requisitionService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private Step12Service step12Service;

	@Autowired
	private ProjectTypeService projectTypeService;

	@Autowired
	private Step3WorkerService step3WorkerService;

	@Autowired
	private ProjectProcessStateService processStateService;

	private Project project; // 项目

	private Step12 step12;

	private Integer projectTypeId;// 工程类型

	private Integer nextWorkerId; // 下一步工作指定人

	private Integer projectid;
	
	private Requisition requisition;

	/**
	 * 新建
	 */
	@Action("new")
	public String _new() {

		step12 = new Step12();
		return "new";
	}

	/**
	 * 保存并执行下一步
	 */
	@Action(value = "toNextStep", results = {
			@Result(name = "workflowNormal", location = "../project/workflow", type = "redirectAction",params={"id","%{project.id}"}),
			@Result(name = "myProjectList", location = "../project/myProjectList", type = "redirectAction") })
	public String toNextStep() {
		String nextOperatorId = get("nextOperatorId");
		requisition = requisitionService.getByProjectId(project.getId());
		requisition.setNextOperatorId(String.valueOf(this.getUser().getId()));
		requisitionService.update(requisition);
		boolean is = false;
		ProjectProcessState pps = processStateService.getOneByProjectIdAndBusinesstype(project.getId(),
				Integer.parseInt(ProjectProcessState.TYPE_PM));
		project = projectService.get(project.getId());
		if (project.getId() != null) {
			Step3Worker step3Worker = step3WorkerService.get(project.getId(), ProjectProcessState.TYPE_PM,
					Integer.parseInt(Constants.StepCode.REQUEST_FUNDS));
			// 更新项目进程状态表信息
			updatePsd(pps, Constants.StepCode.REQUEST_FUNDS, step3Worker.getWorkUserId());
			// 更新项目进程处理历史记录表
			savePph(project, Constants.StepCode.STEP12, Constants.StepCode.REQUEST_FUNDS, Constants.OperateType.SUBMIT,
					step3Worker.getWorkUserId());
			is = StringUtil.str1ToStr2(step3Worker.getWorkUserId(), Integer.toString(getUser().getId()), ",");
			// 发送邮件通知下一步处理人有待办事项需要处理
			EmailService emailService = SpringUtil.getBean(EmailService.class);
			emailService.sendEmail(step3Worker.getWorkUserId(), project, Constants.StepCode.STEP12);
			if (step3Worker.getWorkUserId().equals(Integer.toString(getUser().getId()))) {
				is = true;
			}
		}
		Step12 oldStep12 = step12Service.get(step12.getId());
		// 属性拷贝，把更新内容拷贝到数据库查出的project里，最后的那个参数是不需要拷贝的属性名称
		ReflectionUtil.bean2Bean(oldStep12, step12, "id,ctime,status,step,nextWorker");
		oldStep12.setConfirmTime(new Date());
		oldStep12.setProject(project);
		if (oldStep12.getStep() == 1) {
			oldStep12.setStep(2);
			if (!StringUtil.isBlank(nextOperatorId)) {
				// 将下一步处理人保存进来
				User user = userService.get(Integer.parseInt(nextOperatorId));
				oldStep12.setNextWorker(user);
			}
		}
		//projectService.update(project);
		step12Service.update(oldStep12);
		if (is) {
			return "workflowNormal";
		} else {
			return "myProjectList";
		}
	}

	/**
	 * 保存
	 */
	@Action(value = "save", results = {
			@Result(name = "workflowNormal", location = "../project/workflow", type = "redirectAction",params={"id","%{project.id}"}),
			@Result(name = "myProjectList", location = "../project/myProjectList", type = "redirectAction") })
	public String save() {

		if (project.getId() != null) {
			project = projectService.get(project.getId());
			step12.setProject(project);
		}

		if (step12.getId() == null) { // 新增
			step12.setUser(this.getUser());
			step12.setDept(this.getMyDept());
			step12.setConfirmTime(new Date());
			step12Service.save(step12);
		} else { // 更新
			Step12 oldStep12 = step12Service.get(step12.getId());
			// 属性拷贝，把更新内容拷贝到数据库查出的project里，最后的那个参数是不需要拷贝的属性名称
			ReflectionUtil.bean2Bean(oldStep12, step12, "id,ctime,status,step,nextWorker");
			oldStep12.setConfirmTime(new Date());
			if (!StringUtil.isBlank(get("nextOperatorId"))) {
				// 将下一步处理人保存进来
				User user = userService.get(Integer.parseInt(get("nextOperatorId")));
				oldStep12.setNextWorker(user);
				oldStep12.setStep(1);
			}
			projectService.update(project);
			step12Service.update(oldStep12);
		}
		boolean is = false;
		ProjectProcessState pps = processStateService.getOneByProjectIdAndBusinesstype(project.getId(),Integer.parseInt(ProjectProcessState.TYPE_PM));
		if (project.getId() != null && step12.getStep() == 1) {
			if (nextWorkerId != null) {
				String[] strs = StringUtil.str1RemoveStr3(pps.getCurrentUsers(), Integer.toString(getUser().getId()), Integer.toString(nextWorkerId), ",");
				// 更新项目进程状态表信息
			    updatePsd(pps,Constants.StepCode.STEP12,StringUtil.array2Str(strs,","));
			    // 更新项目进程处理历史记录表
			    savePph(project,Constants.StepCode.STEP12,Constants.StepCode.STEP12,Constants.OperateType.TRANSFER,StringUtil.array2Str(strs,","));
			    is = false;
				// 发送邮件通知下一步处理人有待办事项需要处理
				EmailService emailService = SpringUtil.getBean(EmailService.class);
				emailService.sendEmail(StringUtil.array2Str(strs,","), project, Constants.StepCode.STEP12);
			} else {
				// 更新项目进程处理历史记录表
				savePph(project,Constants.StepCode.STEP12,Constants.StepCode.STEP12,Constants.OperateType.SAVE,pps.getCurrentUsers());
				is = true;
			}
		} else {
			if (nextWorkerId != null) {
				Step12 nextStep12 = step12Service.get(step12.getId());
				User user = userService.get(nextWorkerId);
				nextStep12.setNextWorker(user);
				step12Service.update(nextStep12);
				
				String[] strs = StringUtil.str1RemoveStr3(pps.getCurrentUsers(), Integer.toString(getUser().getId()), Integer.toString(nextWorkerId), ",");
				// 更新项目进程状态表信息
			    updatePsd(pps,Constants.StepCode.STEP12,StringUtil.array2Str(strs,","));
			    // 更新项目进程处理历史记录表
			    savePph(project,Constants.StepCode.STEP12,Constants.StepCode.STEP12,Constants.OperateType.TRANSFER,Integer.toString(nextWorkerId));
			    is = false;
			    // 发送邮件通知下一步处理人有待办事项需要处理
				EmailService emailService = SpringUtil.getBean(EmailService.class);
				emailService.sendEmail(StringUtil.array2Str(strs,","), project, Constants.StepCode.STEP12);
			} else {
				// 更新项目进程处理历史记录表
				savePph(project,Constants.StepCode.STEP12,Constants.StepCode.STEP12,Constants.OperateType.SAVE,pps.getCurrentUsers());
				is = true;
			}
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
			step12 = step12Service.getByProjectId(project.getId());
			project = step12.getProject();
		} else {
			step12 = step12Service.get(step12.getId());
			project = step12.getProject();
		}
		if (step12.getStep() == 1) {
			return "new";
		} else {
			if (step12.getNextWorker().getId().equals(getUser().getId())) {
				return "new";
			} else {
				return "show";
			}
		}
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
		Page<Step12> pageBean = step12Service.getPage(getPageNo(), getPageSize(), project.getNo(), project.getName(),
				projectTypeId, Integer.parseInt(Constants.StepCode.STEP12));
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
			step12 = step12Service.getByProjectId(project.getId());
			project = step12.getProject();
		}
		// else{
		// step12 = step12Service.get(step12.getId());
		// project = step12.getProject();
		// }
		return "show";
	}

	/**
	 * 打印
	 * 
	 * @return
	 */
	@Action("print")
	public String print() {
		step12 = step12Service.getByProjectId(project.getId());
		project = step12.getProject();
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
		step12 = step12Service.get(step12.getId());
		step12.setStatus(-1);
		step12Service.update(step12);

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

	public Step12 getStep12() {
		return step12;
	}

	public void setStep12(Step12 step12) {
		this.step12 = step12;
	}

	public Integer getNextWorkerId() {
		return nextWorkerId;
	}

	public void setNextWorkerId(Integer nextWorkerId) {
		this.nextWorkerId = nextWorkerId;
	}

	public Integer getProjectTypeId() {
		return projectTypeId;
	}

	public void setProjectTypeId(Integer projectTypeId) {
		this.projectTypeId = projectTypeId;
	}

	public Integer getProjectid() {
		return projectid;
	}

	public void setProjectid(Integer projectid) {
		this.projectid = projectid;
	}
	
	public Requisition getRequisition() {
		return requisition;
	}

	public void setRequisition(Requisition requisition) {
		this.requisition = requisition;
	}

}
