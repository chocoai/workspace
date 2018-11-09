package com.smart.web.action.requ;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.model.Project;
import com.smart.model.ProjectProcessState;
import com.smart.model.Requisition;
import com.smart.model.RequisitionType;
import com.smart.model.ServiceContent;
import com.smart.model.ServiceType;
import com.smart.model.Step3Worker;
import com.smart.service.EmailService;
import com.smart.service.ProjectProcessStateService;
import com.smart.service.ProjectService;
import com.smart.service.RequisitionService;
import com.smart.service.RequisitionTypeService;
import com.smart.service.ServiceContentService;
import com.smart.service.ServiceTypeService;
import com.smart.service.Step3WorkerService;
import com.smart.util.Constants;
import com.smart.util.Page;
import com.smart.util.ReflectionUtil;
import com.smart.util.SpringUtil;
import com.smart.web.action.BaseAction;

/**
 * 说明：根据包名的路径自动装配 比如本action路径，com.smart.demo.DemoAction，对应的页面文件夹content/demo
 * return "new" 则会找到content/demo/new.ftl control-user 权限拦截，使用UserInterceptor拦截器
 */
@ParentPackage("control-user")
public class RequAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private RequisitionService requisitionService;

	@Autowired
	private ServiceTypeService serviceTypeService;

	@Autowired
	private RequisitionTypeService requisitionTypeService;

	@Autowired
	private ServiceContentService serviceContentService;

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ProjectProcessStateService processStateService;
	
	@Autowired
	private Step3WorkerService step3WorkerService;

	private Requisition requisition;

	private Project project;

	@Action("new")
	public String _new() {
		List<ServiceType> serviceTypeList = serviceTypeService.getAll();
		put("serviceTypeList", serviceTypeList);
		List<RequisitionType> requisitionTypeList = requisitionTypeService
				.getAll();
		put("requisitionTypeList", requisitionTypeList);
		List<ServiceContent> serviceContentList = serviceContentService
				.getAll();
		put("serviceContentList", serviceContentList);
		requisition = new Requisition();
		put("requisition", requisition);
		return "new";
	}

	@Action(value = "save", results = {
			@Result(name = "list", location = "list", type = "redirectAction") })
	public String save() throws Exception {
		requisition.setUser(this.getUser());
		requisition.setDept(this.getMyDept());
		if (project.getId() != null) {
			project = projectService.get(project.getId());
			requisition.setProject(project);
		}
		if (requisition.getId() == null) { // 新增
			requisitionService.save(requisition);
		} else { // 更新
			Requisition oldrequisition = requisitionService
					.get(requisition.getId());
			// 属性拷贝，把更新内容拷贝到数据库查出的project里，最后的那个参数是不需要拷贝的属性名称
			ReflectionUtil.bean2Bean(oldrequisition, requisition,
					"id, ctime, status,nextOperatorId");
			requisitionService.update(oldrequisition);
		}
			return "list";
	}

	@Action(value = "nextStep",results = {
			@Result(name = "list", location = "list", type = "redirectAction") })
	public String toNextStep() {
		requisition.setUser(this.getUser());
		requisition.setDept(this.getMyDept());
		requisition.setNextOperatorId(null);
		if (project.getId() != null) {
			project = projectService.get(project.getId());
			requisition.setProject(project);
		}
		if (requisition.getId() == null) { // 新增
			requisitionService.save(requisition);
		} else { // 更新
			Requisition oldrequisition = requisitionService
					.get(requisition.getId());
			// 属性拷贝，把更新内容拷贝到数据库查出的project里，最后的那个参数是不需要拷贝的属性名称
			ReflectionUtil.bean2Bean(oldrequisition, requisition,
					"id, ctime, status");
			requisitionService.update(oldrequisition);
		}
		Step3Worker step3Worker = step3WorkerService.get(project.getId(), ProjectProcessState.TYPE_PM,
				Integer.parseInt(Constants.StepCode.STEP13));
		ProjectProcessState pps = processStateService.getOneByProjectIdAndBusinesstype(project.getId(),
				Integer.parseInt(ProjectProcessState.TYPE_PM));
		updatePsd(pps, Constants.StepCode.STEP13, step3Worker.getWorkUserId());
		savePph(project, Constants.StepCode.REQUEST_FUNDS, Constants.StepCode.STEP13, Constants.OperateType.APPROVAL,
				step3Worker.getWorkUserId());
		// 发送邮件通知下一步处理人有待办事项需要处理
		EmailService emailService = SpringUtil.getBean(EmailService.class);
		emailService.sendEmail(step3Worker.getWorkUserId(), project, Constants.StepCode.REQUEST_FUNDS);
		return "list";
	}
	
	@Action("edit")
	public String edit() {
		List<ServiceType> serviceTypeList = serviceTypeService.getAll();
		List<RequisitionType> requisitionTypeList = requisitionTypeService
				.getAll();
		put("serviceTypeList", serviceTypeList);
		put("requisitionTypeList", requisitionTypeList);
		if (project != null && project.getId() != null) {
			requisition = requisitionService.getByProjectId(project.getId());
			project = requisition.getProject();
		} else {
			requisition = requisitionService.get(requisition.getId());
			project = requisition.getProject();
		}
		return "new";
	}

	@Action("show")
	public String show() {
		List<ServiceType> serviceTypeList = serviceTypeService.getAll();
		List<RequisitionType> requisitionTypeList = requisitionTypeService
				.getAll();
		put("serviceTypeList", serviceTypeList);
		put("requisitionTypeList", requisitionTypeList);
		if (project != null && project.getId() != null) {
			requisition = requisitionService.getByProjectId(project.getId());
			project = requisition.getProject();
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
		List<ServiceType> serviceTypeList = serviceTypeService.getAll();
		List<RequisitionType> requisitionTypeList = requisitionTypeService
				.getAll();
		put("serviceTypeList", serviceTypeList);
		put("requisitionTypeList", requisitionTypeList);
		requisition = requisitionService.get(requisition.getId());
		project = requisition.getProject();
		return "print";
	}

	@Action("list")
	public String list() {
		project = project == null ? new Project() : project;
		Page<Requisition> pageBean = requisitionService.getPage(getPageNo(),
				pageSize, project.getNo(), project.getName());
		put("pageBean", pageBean);
		return "list";
	}

	@Action("delete")
	public String delete() {
		Requisition du = requisitionService.get(requisition.getId());
		if (du == null) {
			return "-1";
		}
		du.setStatus(-1);
		requisitionService.update(du);
		write("1"); // ajax请求用write
		return null;
	}

	public Requisition getRequisition() {
		return requisition;
	}

	public void setRequisition(Requisition requisition) {
		this.requisition = requisition;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
