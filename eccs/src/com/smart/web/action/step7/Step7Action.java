package com.smart.web.action.step7;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.model.Project;
import com.smart.model.ProjectProcessState;
import com.smart.model.ProjectType;
import com.smart.model.Step3Worker;
import com.smart.model.Step6;
import com.smart.model.Step7;
import com.smart.model.Step7Item;
import com.smart.service.AnnexService;
import com.smart.service.EmailService;
import com.smart.service.ProjectProcessStateService;
import com.smart.service.ProjectService;
import com.smart.service.ProjectTypeService;
import com.smart.service.Step3WorkerService;
import com.smart.service.Step6Service;
import com.smart.service.Step7ItemService;
import com.smart.service.Step7Service;
import com.smart.util.Constants;
import com.smart.util.DateUtils;
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
public class Step7Action extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private Step3WorkerService step3WorkerService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private Step7Service step7Service;

	@Autowired
	private Step6Service step6Service;

	@Autowired
	private Step7ItemService step7ItemService;

	@Autowired
	private ProjectTypeService projectTypeService;

	@Autowired
	private AnnexService annexService;

	@Autowired
	private ProjectProcessStateService processStateService;

	private Project project; // 项目

	private Step7 step7;

	private Integer projectTypeId;// 工程类型

	private Step7Item step7Item;// 装载问题情况content values

	private String content;// 主要问题

	private String values;// 修改及执行情况

	private Integer nextWorkerId; // 下一步工作指定人

	private String id;

	private Integer projectid;

	/**
	 * 新建
	 */
	@Action("new")
	public String _new() {
		String ctime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		put("ctime", ctime);
		step7 = new Step7();
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
		String[] arrcontent = {};
		String[] arrvalues = {};
		boolean comitFlag = true; // 提交或者打回的标记
		if (!StringUtil.isBlank(content)) { // 评标专家
			arrcontent = content.split(",");
			arrvalues = values.split(",");
		}
		if (step7.getId() == null) { // 新增
		} else { // 更新
			Step7 oldStep7 = step7Service.get(step7.getId());
			// 属性拷贝，把更新内容拷贝到数据库查出的project里，最后的那个参数是不需要拷贝的属性名称
			ReflectionUtil.bean2Bean(oldStep7, step7, "id, ctime, status");
			oldStep7.setUser(this.getUser());
			oldStep7.setDept(this.getMyDept());
			oldStep7.setProject(project);
			oldStep7.setConfirmTime(new Date());
			step7Service.update(oldStep7);
			step7ItemService.deleteByStep7Id(step7.getId());
			for (int i = 0; i < arrcontent.length; i++) {
				if (!StringUtil.isBlank(arrcontent[i])) {
					Step7Item si = new Step7Item();
					si.setContent(arrcontent[i]);
					String value = arrvalues[i];
					String result = this.get("result_" + value.trim());
					si.setResult(result);
					if ("0".equals(result) || "2".equals(result)) {
						comitFlag = false;
					}
					si.setUser(this.getUser());
					si.setDept(this.getMyDept());
					si.setStep7(oldStep7);
					step7ItemService.save(si);
				}
			}
		}
		boolean is = false;
		if (project.getId() != null) {
			project = projectService.get(project.getId());
			ProjectProcessState pps = processStateService
					.getOneByProjectIdAndBusinesstype(project.getId(),
							Integer.parseInt(ProjectProcessState.TYPE_PM));
			if (comitFlag) {
				Step3Worker step3Worker = step3WorkerService.get(
						project.getId(), ProjectProcessState.TYPE_PM,
						Integer.parseInt(Constants.StepCode.STEP8));
				// 更新项目进程状态表信息
				updatePsd(pps, Constants.StepCode.STEP8,
						step3Worker.getWorkUserId());
				// 更新项目进程处理历史记录表
				savePph(project, Constants.StepCode.STEP7,
						Constants.StepCode.STEP8, Constants.OperateType.SUBMIT,
						step3Worker.getWorkUserId());
				is = StringUtil.str1ToStr2(step3Worker.getWorkUserId(),
						Integer.toString(getUser().getId()), ",");
				// 发送邮件通知下一步处理人有待办事项需要处理
				EmailService emailService = SpringUtil.getBean(EmailService.class);
				emailService.sendEmail(step3Worker.getWorkUserId(), project, Constants.StepCode.STEP8);
			} else {// 审核不通过，打回重新编制，重新回到第六步
				annexService.backupDGBZ(project.getId(),
						Integer.parseInt(Constants.StepCode.STEP6));

				Step6 step6 = step6Service.getByProjectId(project.getId());
				// 更新项目进程状态表信息
				updatePsd(pps, Constants.StepCode.STEP6,
						step6.getCurrentUsers());
				// 更新项目进程处理历史记录表
				savePph(project, Constants.StepCode.STEP7,
						Constants.StepCode.STEP6, Constants.OperateType.REPULS,
						step6.getCurrentUsers());
				is = StringUtil.str1ToStr2(step6.getCurrentUsers(),
						Integer.toString(getUser().getId()), ",");
				// 发送邮件通知下一步处理人有待办事项需要处理
				EmailService emailService = SpringUtil.getBean(EmailService.class);
				emailService.sendEmail(step6.getCurrentUsers(), project, Constants.StepCode.STEP6);
			}
			projectService.save(project);
		}

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
			@Result(name = "workflowNormal", location = "../project/workflow", type = "redirectAction", params = {
					"id", "%{project.id}" }),
			@Result(name = "myProjectList", location = "../project/myProjectList", type = "redirectAction") })
	public String save() {
		String[] arrcontent = {};
		String[] arrvalues = {};
		if (!StringUtil.isBlank(content)) { // 评标专家
			arrcontent = content.split(",");
			arrvalues = values.split(",");
		}
		if (project.getId() != null) {
			project = projectService.get(project.getId());
		}

		Step7 oldStep7 = step7Service.get(step7.getId());
		// 属性拷贝，把更新内容拷贝到数据库查出的project里，最后的那个参数是不需要拷贝的属性名称
		ReflectionUtil.bean2Bean(oldStep7, step7, "id, ctime, status");
		oldStep7.setUser(this.getUser());
		oldStep7.setDept(this.getMyDept());
		oldStep7.setProject(project);
		step7Service.update(oldStep7);
		step7ItemService.deleteByStep7Id(step7.getId());
		for (int i = 0; i < arrcontent.length; i++) { // 竞争对手
			if (!StringUtil.isBlank(arrcontent[i])) {
				Step7Item si = new Step7Item();
				si.setContent(arrcontent[i]);
				String value = arrvalues[i];
				String result = this.get("result_" + value.trim());
				si.setResult(result);
				si.setUser(this.getUser());
				si.setDept(this.getMyDept());
				si.setStep7(oldStep7);
				step7ItemService.save(si);
			}
		}
		boolean is = false;
		ProjectProcessState pps = processStateService
				.getOneByProjectIdAndBusinesstype(project.getId(),
						Integer.parseInt(ProjectProcessState.TYPE_PM));

		if (nextWorkerId != null) {
			String[] userIds = StringUtil.str1RemoveStr3(pps.getCurrentUsers(),
					Integer.toString(getUser().getId()),
					Integer.toString(nextWorkerId), ",");
			// 更新项目进程状态表信息
			updatePsd(pps, Constants.StepCode.STEP7,
					StringUtil.array2Str(userIds, ","));
			// 更新项目进程处理历史记录表
			savePph(project, Constants.StepCode.STEP7, Constants.StepCode.STEP7,
					Constants.OperateType.TRANSFER,
					StringUtil.array2Str(userIds, ","));
			is = false;
			
			// 发送邮件通知下一步处理人有待办事项需要处理
			EmailService emailService = SpringUtil.getBean(EmailService.class);
			emailService.sendEmail(StringUtil.array2Str(userIds, ","), project, Constants.StepCode.STEP7);
		} else {
			// 更新项目进程处理历史记录表
			savePph(project, Constants.StepCode.STEP7, Constants.StepCode.STEP7,
					Constants.OperateType.SAVE, pps.getCurrentUsers());
			is = true;
		}
		if (is) {
			return "workflowNormal";
		} else {
			return "myProjectList";
		}
	}

	@Action("deletesz")
	public String deletesz() {
		step7ItemService.delete(Integer.parseInt(id));
		return null;
	}

	/**
	 * 编辑
	 */
	@Action("edit")
	public String edit() {
		if (project != null && project.getId() != null) {
			step7 = step7Service.getByProjectId(project.getId());
		}
		List<Step7Item> step7ItemList = step7ItemService
				.getByStep7Id(step7.getId());
		put("step7ItemList", step7ItemList); // 投标策划 成员表
		project = projectService.get(project.getId());

		put("validateDate", DateUtils.getCurrentTime());
		Step6 step6 = step6Service.getByProjectId(project.getId());
		put("step6", step6);

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
		Page<Step7> pageBean = step7Service.getPage(getPageNo(), getPageSize(),
				project.getNo(), project.getName(), projectTypeId,
				Integer.parseInt(Constants.StepCode.STEP7));
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
			step7 = step7Service.getByProjectId(project.getId());
			put("step7", step7);

			project = projectService.get(project.getId());
			List<Step7Item> step7ItemList = step7ItemService
					.getByStep7Id(step7.getId());
			put("step7ItemList", step7ItemList); // 投标策划 成员表

			Step6 step6 = step6Service.getByProjectId(project.getId());
			put("step6", step6);
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
		step7 = step7Service.getByProjectId(project.getId());
		put("step7", step7);
		List<Step7Item> step7ItemList = step7ItemService
				.getByStep7Id(step7.getId());
		put("step7ItemList", step7ItemList); // 投标策划 成员表
		project = projectService.get(project.getId());

		Step6 step6 = step6Service.getByProjectId(project.getId());
		put("step6", step6);
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
		step7 = step7Service.get(step7.getId());
		step7.setStatus(-1);
		step7Service.update(step7);

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

	public Step7Item getStep7Item() {
		return step7Item;
	}

	public void setStep7Item(Step7Item step7Item) {
		this.step7Item = step7Item;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

	public Integer getNextWorkerId() {
		return nextWorkerId;
	}

	public void setNextWorkerId(Integer nextWorkerId) {
		this.nextWorkerId = nextWorkerId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Step7 getStep7() {
		return step7;
	}

	public void setStep7(Step7 step7) {
		this.step7 = step7;
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

}
