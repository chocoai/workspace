package com.smart.web.action.step5;

import java.io.File;
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
import com.smart.model.Step3Worker;
import com.smart.model.Step5;
import com.smart.model.Step5Item;
import com.smart.model.Step5Logo;
import com.smart.model.Step6;
import com.smart.model.T_hremployee;
import com.smart.service.ProceStepDefService;
import com.smart.service.ProjectProcessStateService;
import com.smart.service.ProjectService;
import com.smart.service.ProjectTypeService;
import com.smart.service.Step3WorkerService;
import com.smart.service.Step5ItemService;
import com.smart.service.Step5LogoService;
import com.smart.service.Step5Service;
import com.smart.service.Step6Service;
import com.smart.service.T_hremployeeService;
import com.smart.util.Constants;
import com.smart.util.EmailSendTool;
import com.smart.util.Page;
import com.smart.util.StringUtil;
import com.smart.web.action.BaseAction;

/**
 * 说明：根据包名的路径自动装配 比如本action路径，com.smart.demo.DemoAction，对应的页面文件夹content/demo
 * return "new" 则会找到content/demo/new.ftl
 * 
 * control-user 权限拦截，使用UserInterceptor拦截器
 */
@ParentPackage("control-user")
public class Step5Action extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private Step5Service step5Service;

	@Autowired
	private Step6Service step6Service;

	@Autowired
	private Step5ItemService step5ItemService;

	@Autowired
	private Step5LogoService step5LogoService;

	@Autowired
	private ProjectTypeService projectTypeService;

	@Autowired
	private Step3WorkerService step3WorkerService;

	@Autowired
	private ProjectProcessStateService processStateService;
	
	@Autowired
	private T_hremployeeService hremployeeService;
	
	@Autowired
	private ProceStepDefService proceStepDefService;

	private File file;

	private String fileFileName;

	private Integer annexTypeId;

	private Project project; // 项目

	private Step5 step5;

	private Step5Logo step5Logo;

	private Integer projectTypeId;

	private Integer nextWorkerId; // 下一步工作指定人

	private Integer projectid;

	private String pany;// 单位

	private String panyRen;// 法人

	private String step5ItemId;

	private String company;

	private String companyRen;

	/**
	 * 新建
	 */
	@Action("new")
	public String _new() {
		step5 = new Step5();
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
		Step5 oldStep5 = step5Service.get(step5.getId());
		oldStep5.setConfirmTime(step5Logo.getCtime());
		oldStep5.setProject(project);
		oldStep5.setProjectName(project.getName());
		oldStep5.setProjectAddress(step5.getProjectAddress());
		oldStep5.setHistoryContent(step5Logo.getLogoNext().trim());
		step5Service.update(oldStep5);

		step5Logo.setDept(getMyDept());
		step5Logo.setUser(getUser());
		step5Logo.setStep5(step5);
		step5Logo.setStatus(0);
		step5LogoService.save(step5Logo);

		String[] companys = {};
		String[] companyRens = {};
		if (!StringUtil.isBlank(pany)) {
			companys = pany.split(",");
			companyRens = panyRen.split(",");
		}
		for (int i = 0; i < companys.length; i++) { // 单位
			Step5Item step5Item = new Step5Item();
			if (!StringUtil.isBlank(companys[i].trim())) {
				step5Item.setCompany(companys[i]);
				step5Item.setCompanyRen(companyRens[i]);
				step5Item.setStep5(step5);
				step5ItemService.save(step5Item);
			}
		}
		String[] step5ItemIds = {};
		String[] panys = {};
		String[] panyRens = {};
		if (!StringUtil.isBlank(step5ItemId)) {
			step5ItemIds = step5ItemId.split(",");
			panys = company.split(",");
			panyRens = companyRen.split(",");
		}
		for (int i = 0; i < step5ItemIds.length; i++) { // 单位
			if (!StringUtil.isBlank(panys[i].trim())) {
				Step5Item step5Item = step5ItemService
						.get(Integer.parseInt(step5ItemIds[i].trim()));
				step5Item.setCompany(panys[i]);
				step5Item.setCompanyRen(panyRens[i]);
				step5ItemService.update(step5Item);
			} else {
				step5ItemService
						.delete(Integer.parseInt(step5ItemIds[i].trim()));
			}
		}
		Step3Worker step3Worker = step3WorkerService.get(project.getId(),
				ProjectProcessState.TYPE_PM,
				Integer.parseInt(Constants.StepCode.STEP6));

		Step6 step6 = step6Service.getByProjectId(project.getId());
		step6.setCurrentUsers(step3Worker.getWorkUserId());
		step6Service.update(step6);

		ProjectProcessState pps = processStateService
				.getOneByProjectIdAndBusinesstype(project.getId(),
						Integer.parseInt(ProjectProcessState.TYPE_PM));
		// 更新项目进程状态表信息
		updatePsd(pps, Constants.StepCode.STEP6, step3Worker.getWorkUserId());
		// 更新项目进程处理历史记录表
		savePph(project, Constants.StepCode.STEP5, Constants.StepCode.STEP6,
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
					.getStepByStepCode(Constants.StepCode.STEP6);
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
		Step5 oldStep5 = step5Service.get(step5.getId());
		oldStep5.setConfirmTime(step5Logo.getCtime());
		oldStep5.setProject(project);
		oldStep5.setProjectName(project.getName());
		oldStep5.setProjectAddress(step5.getProjectAddress());
		oldStep5.setHistoryContent(step5Logo.getLogoNext().trim());

		step5Service.update(oldStep5);

		step5Logo.setDept(getMyDept());
		step5Logo.setUser(getUser());
		step5Logo.setStep5(step5);
		step5Logo.setStatus(0);
		step5LogoService.save(step5Logo);

		String[] companys = {};
		String[] companyRens = {};
		if (!StringUtil.isBlank(pany)) {
			companys = pany.split(",");
			companyRens = panyRen.split(",");
		}
		for (int i = 0; i < companys.length; i++) { // 单位
			Step5Item step5Item = new Step5Item();
			if (!StringUtil.isBlank(companys[i].trim())) {
				step5Item.setCompany(companys[i]);
				step5Item.setCompanyRen(companyRens[i]);
				step5Item.setStep5(step5);
				step5ItemService.save(step5Item);
			}
		}
		String[] step5ItemIds = {};
		String[] panys = {};
		String[] panyRens = {};
		if (!StringUtil.isBlank(step5ItemId)) {
			step5ItemIds = step5ItemId.split(",");
			panys = company.split(",");
			panyRens = companyRen.split(",");
		}
		for (int i = 0; i < step5ItemIds.length; i++) { // 单位
			if (!StringUtil.isBlank(panys[i].trim())) {
				Step5Item step5Item = step5ItemService
						.get(Integer.parseInt(step5ItemIds[i].trim()));
				step5Item.setCompany(panys[i]);
				step5Item.setCompanyRen(panyRens[i]);
				step5ItemService.update(step5Item);
			} else {
				step5ItemService
						.delete(Integer.parseInt(step5ItemIds[i].trim()));
			}
		}
		Step3Worker step3Worker = step3WorkerService.get(project.getId(),
				ProjectProcessState.TYPE_PM,
				Integer.parseInt(Constants.StepCode.STEP6));

		Step6 step6 = step6Service.getByProjectId(project.getId());
		step6.setCurrentUsers(step3Worker.getWorkUserId());
		step6Service.update(step6);

		ProjectProcessState pps = processStateService
				.getOneByProjectIdAndBusinesstype(project.getId(),
						Integer.parseInt(ProjectProcessState.TYPE_PM));
		// 更新项目进程状态表信息
		updatePsd(pps, Constants.StepCode.STEP6, step3Worker.getWorkUserId());
		// 更新项目进程处理历史记录表
		savePph(project, Constants.StepCode.STEP5, Constants.StepCode.STEP6,
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
					.getStepByStepCode(Constants.StepCode.STEP6);
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
	 * 编辑
	 */
	@Action("edit")
	public String edit() {
		if (project != null && project.getId() != null) {
			step5 = step5Service.getByProjectId(project.getId());
		} else {
			step5 = step5Service.get(step5.getId());
		}
		project = step5.getProject();
		List<Step5Logo> step5LogoList = step5LogoService
				.getByStep5Id(step5.getId());
		put("step5LogoList", step5LogoList);

		List<Step5Item> step5ItemList = step5ItemService
				.getByStep5Id(step5.getId());
		if (step5ItemList.size() < 1) {
			step5ItemList.add(new Step5Item("审核单位"));
			step5ItemList.add(new Step5Item("建设单位"));
			step5ItemList.add(new Step5Item("施工单位"));
		}
		put("step5ItemList", step5ItemList);

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
		Page<Step5> pageBean = step5Service.getPage(getPageNo(), getPageSize(),
				project.getNo(), project.getName(), projectTypeId,
				Integer.parseInt(Constants.StepCode.STEP5));
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
			step5 = step5Service.getByProjectId(project.getId());
			project = step5.getProject();
			List<Step5Logo> step5LogoList = step5LogoService
					.getByStep5Id(step5.getId());
			put("step5LogoList", step5LogoList);

			List<Step5Item> step5ItemList = step5ItemService
					.getByStep5Id(step5.getId());
			put("step5ItemList", step5ItemList);
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
			step5 = step5Service.getByProjectId(project.getId());
		} else {
			step5 = step5Service.get(step5.getId());
		}
		project = step5.getProject();
		List<Step5Logo> step5LogoList = step5LogoService
				.getByStep5Id(step5.getId());
		put("step5LogoList", step5LogoList);

		List<Step5Item> step5ItemList = step5ItemService
				.getByStep5Id(step5.getId());
		put("step5ItemList", step5ItemList);
		return "print";
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@Action("deleteItem")
	public String deleteItem() {
		String stepItemId = get("stepItemId");
		if (!StringUtil.isBlank(stepItemId)) {
			step5ItemService.delete(Integer.parseInt(stepItemId));
			write("1"); // ajax请求用write返回数据
		}
		return null;
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@Action("delete")
	public String delete() {
		step5 = step5Service.get(step5.getId());
		step5.setStatus(-1);
		step5Service.update(step5);

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

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public Integer getAnnexTypeId() {
		return annexTypeId;
	}

	public void setAnnexTypeId(Integer annexTypeId) {
		this.annexTypeId = annexTypeId;
	}

	public Integer getNextWorkerId() {
		return nextWorkerId;
	}

	public void setNextWorkerId(Integer nextWorkerId) {
		this.nextWorkerId = nextWorkerId;
	}

	public Step5 getStep5() {
		return step5;
	}

	public void setStep5(Step5 step5) {
		this.step5 = step5;
	}

	public Step5Logo getStep5Logo() {
		return step5Logo;
	}

	public void setStep5Logo(Step5Logo step5Logo) {
		this.step5Logo = step5Logo;
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

	public String getPany() {
		return pany;
	}

	public void setPany(String pany) {
		this.pany = pany;
	}

	public String getPanyRen() {
		return panyRen;
	}

	public void setPanyRen(String panyRen) {
		this.panyRen = panyRen;
	}

	public String getStep5ItemId() {
		return step5ItemId;
	}

	public void setStep5ItemId(String step5ItemId) {
		this.step5ItemId = step5ItemId;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompanyRen() {
		return companyRen;
	}

	public void setCompanyRen(String companyRen) {
		this.companyRen = companyRen;
	}

}
