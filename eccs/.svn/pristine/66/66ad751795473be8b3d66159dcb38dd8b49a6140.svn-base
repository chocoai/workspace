package com.smart.web.action.step4;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.model.ConsultType;
import com.smart.model.ProceStepDef;
import com.smart.model.Project;
import com.smart.model.ProjectProcessState;
import com.smart.model.ProjectType;
import com.smart.model.Step3Worker;
import com.smart.model.Step4;
import com.smart.model.Step4File;
import com.smart.model.Step5;
import com.smart.model.T_hremployee;
import com.smart.service.ConsultTypeService;
import com.smart.service.ProceStepDefService;
import com.smart.service.ProjectProcessStateService;
import com.smart.service.ProjectService;
import com.smart.service.ProjectTypeService;
import com.smart.service.Step3WorkerService;
import com.smart.service.Step4FileService;
import com.smart.service.Step4Service;
import com.smart.service.Step5Service;
import com.smart.service.T_hremployeeService;
import com.smart.util.Constants;
import com.smart.util.EmailSendTool;
import com.smart.util.Page;
import com.smart.util.ReflectionUtil;
import com.smart.util.StringUtil;

import com.smart.web.action.BaseAction;

/**
 * 说明：根据包名的路径自动装配 比如本action路径，com.smart.demo.DemoAction，对应的页面文件夹content/demo
 * return "new" 则会找到content/demo/new.ftl
 * 
 * control-user 权限拦截，使用UserInterceptor拦截器
 */
@ParentPackage("control-user")
public class Step4Action extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private Step4Service step4Service;

	@Autowired
	private Step5Service step5Service;

	@Autowired
	private Step4FileService step4FileService;

	@Autowired
	private ProjectTypeService projectTypeService;

	@Autowired
	private ConsultTypeService consultTypeService; // 咨询类别

	@Autowired
	private Step3WorkerService step3WorkerService;

	@Autowired
	private ProjectProcessStateService processStateService;
	
	@Autowired
	private T_hremployeeService hremployeeService;
	
	@Autowired
	private ProceStepDefService proceStepDefService;


	private Project project; // 项目

	private Step4 step4;

	private Integer projectTypeId;// 工程类型

	private Integer consultTypeId;// 暂无用

	private Step4File step4File;

	private String name;

	private String count;

	private Integer projectid;

	private Integer nextWorkerId; // 下一步工作指定人

	private Integer fileId;

	/**
	 * 新建
	 */
	@Action("new")
	public String _new() {
		List<Object> numList = new ArrayList<Object>();
		for (int i = 0; i < 7; i++) {
			Step4File[] step4arr = new Step4File[2];
			numList.add(step4arr);
		}
		put("numList", numList);
		List<ConsultType> consultTypeList = consultTypeService.getAll();
		put("consultTypeList", consultTypeList);
		step4 = new Step4();
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
		String[] arrename = {};
		String[] arrcount = {};
		if (!StringUtil.isBlank(name)) { // 评标专家
			arrename = name.split(",");
			arrcount = count.split(",");
		}
		if (project.getId() != null) {
			project = projectService.get(project.getId());
		}
		Step4 oldstep4 = step4Service.get(step4.getId());
		// 属性拷贝，把更新内容拷贝到数据库查出的project里，最后的那个参数是不需要拷贝的属性名称
		ReflectionUtil.bean2Bean(oldstep4, step4, "id, ctime, status");
		oldstep4.setUser(this.getUser());
		oldstep4.setDept(this.getMyDept());
		oldstep4.setProject(project);
		oldstep4.setConfirmTime(new Date());
		step4Service.update(oldstep4);
		// step4FileService.deleteByStep4Id(step4.getId());
		for (int i = 0; i < arrename.length; i++) { // 竞争对手
			Step4File sf = new Step4File();
			if (!StringUtil.isBlank(arrename[i].trim())) {
				sf.setName(arrename[i]);
				sf.setCount(arrcount[i]);
				sf.setStep4(oldstep4);
				sf.setUser(this.getUser());
				sf.setDept(this.getMyDept());
				step4FileService.save(sf);
			}
		}
		Step3Worker step3Worker = step3WorkerService.get(project.getId(),
				ProjectProcessState.TYPE_PM,
				Integer.parseInt(Constants.StepCode.STEP5));
		Step5 step5 = step5Service.getByProjectId(project.getId());
		step5.setCurrentUsers(step3Worker.getWorkUserId());
		step5Service.update(step5);

		ProjectProcessState pps = processStateService
				.getOneByProjectIdAndBusinesstype(project.getId(),
						Integer.parseInt(ProjectProcessState.TYPE_PM));
		// 更新项目进程状态表信息
		updatePsd(pps, Constants.StepCode.STEP5, step3Worker.getWorkUserId());
		// 更新项目进程处理历史记录表
		savePph(project, Constants.StepCode.STEP4, Constants.StepCode.STEP5,
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
					.getStepByStepCode(Constants.StepCode.STEP5);
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
		String[] arrename = {};
		String[] arrcount = {};
		if (!StringUtil.isBlank(name)) {
			arrename = name.split(",");
			arrcount = count.split(",");
		}
		if (project.getId() != null) {
			project = projectService.get(project.getId());
		}
		Step4 oldstep4 = step4Service.get(step4.getId());
		// 属性拷贝，把更新内容拷贝到数据库查出的project里，最后的那个参数是不需要拷贝的属性名称
		ReflectionUtil.bean2Bean(oldstep4, step4, "id, ctime, status");
		oldstep4.setUser(this.getUser());
		oldstep4.setDept(this.getMyDept());
		oldstep4.setProject(project);
		for (int i = 0; i < arrename.length; i++) {
			Step4File sf = new Step4File();
			if (!StringUtil.isBlank(arrename[i].trim())) {
				sf.setName(arrename[i]);
				sf.setCount(arrcount[i]);
				sf.setStep4(oldstep4);
				sf.setUser(this.getUser());
				sf.setDept(this.getMyDept());
				step4FileService.save(sf);
			}
		}
		boolean is = false;
		ProjectProcessState pps = processStateService
				.getOneByProjectIdAndBusinesstype(project.getId(),
						Integer.parseInt(ProjectProcessState.TYPE_PM));

		if (nextWorkerId != null) { //转交给其它用户
			String[] userIds = StringUtil.str1RemoveStr3(pps.getCurrentUsers(),
					Integer.toString(getUser().getId()),
					Integer.toString(nextWorkerId), ",");
			// 更新项目进程状态表信息
			updatePsd(pps, Constants.StepCode.STEP4,
					StringUtil.array2Str(userIds, ","));
			// 更新项目进程处理历史记录表
			savePph(project, Constants.StepCode.STEP4, Constants.StepCode.STEP4,
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
					.getStepByStepCode(Constants.StepCode.STEP4);
			EmailSendTool sendEmail = new EmailSendTool();
			String subject = sendEmail.getSubject();
			String emailContent = sendEmail.buildContent(project.getNo(),
					project.getName(), nextStep.getStepName());
			sendEmail.send(emailAddress.toString(), subject, emailContent);
		} else {
			// 更新项目进程处理历史记录表
			savePph(project, Constants.StepCode.STEP4, Constants.StepCode.STEP4,
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
			step4 = step4Service.getByProjectId(project.getId());
			List<Step4File> step4FileList = step4FileService
					.getByStep4Id(step4.getId());
			put("numList", step4FileList);
			List<ConsultType> consultTypeList = consultTypeService.getAll();
			put("consultTypeList", consultTypeList);

			project = step4.getProject();
		} else {
			List<Step4File> step4FileList = step4FileService
					.getByStep4Id(step4.getId());
			put("numList", step4FileList);
			List<ConsultType> consultTypeList = consultTypeService.getAll();
			put("consultTypeList", consultTypeList);
			step4 = step4Service.get(step4.getId());
			project = step4.getProject();
		}
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
		Page<Step4> pageBean = step4Service.getPage(getPageNo(), getPageSize(),
				project.getNo(), project.getName(), projectTypeId,
				Integer.parseInt(Constants.StepCode.STEP4));
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
			step4 = step4Service.getByProjectId(project.getId());
			List<Step4File> step4FileList = step4FileService
					.getByStep4Id(step4.getId());
			put("numList", step4FileList);
			List<ConsultType> consultTypeList = consultTypeService.getAll();
			put("consultTypeList", consultTypeList);

			project = step4.getProject();
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
		step4 = step4Service.getByProjectId(project.getId());
		List<Step4File> step4FileList = step4FileService
				.getByStep4Id(step4.getId());
		put("numList", step4FileList);
		List<ConsultType> consultTypeList = consultTypeService.getAll();
		put("consultTypeList", consultTypeList);

		project = step4.getProject();
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
		step4 = step4Service.get(step4.getId());
		step4.setStatus(-1);
		step4Service.update(step4);

		write("1"); // ajax请求用write返回数据
		return null;
	}

	@Action("deleteFile")
	public String deleteFile() {
		step4FileService.delete(fileId);
		return null;
	}

	// ====================== getter and setter ===========================
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Step4 getStep4() {
		return step4;
	}

	public void setStep4(Step4 step4) {
		this.step4 = step4;
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

	public Step4File getStep4File() {
		return step4File;
	}

	public void setStep4File(Step4File step4File) {
		this.step4File = step4File;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
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

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

}
