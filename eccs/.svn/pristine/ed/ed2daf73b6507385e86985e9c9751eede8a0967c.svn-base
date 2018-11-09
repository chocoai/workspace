package com.smart.web.action.step8;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.model.Annex;
import com.smart.model.Project;
import com.smart.model.ProjectProcessState;
import com.smart.model.ProjectType;
import com.smart.model.Step3Worker;
import com.smart.model.Step6;
import com.smart.model.Step8;
import com.smart.model.Step8Item;
import com.smart.service.AnnexService;
import com.smart.service.EmailService;
import com.smart.service.ProjectProcessStateService;
import com.smart.service.ProjectService;
import com.smart.service.ProjectTypeService;
import com.smart.service.Step3WorkerService;
import com.smart.service.Step6Service;
import com.smart.service.Step8ItemService;
import com.smart.service.Step8Service;
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
public class Step8Action extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private Step8Service step8Service;

	@Autowired
	private Step6Service step6Service;

	@Autowired
	private Step8ItemService step8ItemService;

	@Autowired
	private ProjectTypeService projectTypeService;

	@Autowired
	private AnnexService annexService;

	@Autowired
	private Step3WorkerService step3WorkerService;

	@Autowired
	private ProjectProcessStateService processStateService;

	private Project project; // 项目

	private Step8 step8;

	private Integer projectTypeId;// 工程类型

	private Step8Item step8Item;// 装载问题情况content values

	private String content;// 主要问题

	private String values;// 修改及执行情况

	private String id;

	private Integer nextWorkerId; // 下一步工作指定人

	private Integer projectid;

	/**
	 * 新建
	 */
	@Action("new")
	public String _new() {
		String ctime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		put("ctime", ctime);
		step8 = new Step8();
		List<Annex> annexList = annexService.getAnnexs(project.getId());
		put("annexList", annexList);
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
		if (step8.getId() == null) { // 新增
		} else { // 更新
			Step8 oldStep8 = step8Service.get(step8.getId());
			// 属性拷贝，把更新内容拷贝到数据库查出的project里，最后的那个参数是不需要拷贝的属性名称
			ReflectionUtil.bean2Bean(oldStep8, step8, "id, ctime, status");
			oldStep8.setUser(this.getUser());
			oldStep8.setDept(this.getMyDept());
			oldStep8.setProject(project);
			oldStep8.setConfirmTime(new Date());
			step8Service.update(oldStep8);
			step8ItemService.deleteByStep8Id(step8.getId());
			for (int i = 0; i < arrcontent.length; i++) { // 竞争对手
				if (!StringUtil.isBlank(arrcontent[i])) {
					Step8Item si = new Step8Item();
					si.setContent(arrcontent[i]);
					String value = arrvalues[i];
					String result = this.get("result_" + value.trim());
					si.setResult(result);
					if ("0".equals(result) || "2".equals(result)) {
						comitFlag = false;
					}
					si.setUser(this.getUser());
					si.setDept(this.getMyDept());
					si.setStep8(oldStep8);
					step8ItemService.save(si);
				}
			}
		}
		boolean is = false;
		ProjectProcessState pps = processStateService
				.getOneByProjectIdAndBusinesstype(project.getId(),
						Integer.parseInt(ProjectProcessState.TYPE_PM));
		if (project.getId() != null) {
			project = projectService.get(project.getId());
			if (comitFlag) {
				Step3Worker step3Worker = step3WorkerService.get(
						project.getId(), ProjectProcessState.TYPE_PM,
						Integer.parseInt(Constants.StepCode.STEP9));
				// 更新项目进程状态表信息
				updatePsd(pps, Constants.StepCode.STEP9,
						step3Worker.getWorkUserId());
				// 更新项目进程处理历史记录表
				savePph(project, Constants.StepCode.STEP8,
						Constants.StepCode.STEP9, Constants.OperateType.SUBMIT,
						step3Worker.getWorkUserId());
				is = StringUtil.str1ToStr2(step3Worker.getWorkUserId(),
						Integer.toString(getUser().getId()), ",");
				// 发送邮件通知下一步处理人有待办事项需要处理
				EmailService emailService = SpringUtil.getBean(EmailService.class);
				emailService.sendEmail(step3Worker.getWorkUserId(), project, Constants.StepCode.STEP9);
			} else {// 审核不通过，打回重新编制，重新回到第六步
				annexService.backupDGBZ(project.getId(),
						Integer.parseInt(Constants.StepCode.STEP6));

				Step6 step6 = step6Service.getByProjectId(project.getId());
				// 更新项目进程状态表信息
				updatePsd(pps, Constants.StepCode.STEP6,
						step6.getCurrentUsers());
				// 更新项目进程处理历史记录表
				savePph(project, Constants.StepCode.STEP8,
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
		Step8 oldStep8 = step8Service.get(step8.getId());
		// 属性拷贝，把更新内容拷贝到数据库查出的project里，最后的那个参数是不需要拷贝的属性名称
		ReflectionUtil.bean2Bean(oldStep8, step8, "id, ctime, status");
		oldStep8.setUser(this.getUser());
		oldStep8.setDept(this.getMyDept());
		oldStep8.setProject(project);
		step8Service.update(oldStep8);
		step8ItemService.deleteByStep8Id(step8.getId());
		for (int i = 0; i < arrcontent.length; i++) { // 竞争对手
			if (!StringUtil.isBlank(arrcontent[i])) {
				Step8Item si = new Step8Item();
				si.setContent(arrcontent[i]);
				String value = arrvalues[i];
				String result = this.get("result_" + value.trim());
				si.setResult(result);
				si.setUser(this.getUser());
				si.setDept(this.getMyDept());
				si.setStep8(oldStep8);
				step8ItemService.save(si);
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
			updatePsd(pps, Constants.StepCode.STEP8,
					StringUtil.array2Str(userIds, ","));
			// 更新项目进程处理历史记录表
			savePph(project, Constants.StepCode.STEP8, Constants.StepCode.STEP8,
					Constants.OperateType.TRANSFER,
					StringUtil.array2Str(userIds, ","));
			is = false;
			// 发送邮件通知下一步处理人有待办事项需要处理
			EmailService emailService = SpringUtil.getBean(EmailService.class);
			emailService.sendEmail(StringUtil.array2Str(userIds, ","), project, Constants.StepCode.STEP8);
		} else {
			// 更新项目进程处理历史记录表
			savePph(project, Constants.StepCode.STEP8, Constants.StepCode.STEP8,
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
		step8ItemService.delete(Integer.parseInt(id));
		return null;
	}

	/**
	 * 编辑
	 */
	@Action("edit")
	public String edit() {
		if (project != null && project.getId() != null) {
			step8 = step8Service.getByProjectId(project.getId());
		}
		List<Step8Item> step8ItemList = step8ItemService
				.getByStep8Id(step8.getId());
		put("step8ItemList", step8ItemList); // 投标策划 成员表
		project = projectService.get(project.getId());

		put("validateDate", DateUtils.getCurrentTime());
		Step6 step6 = step6Service.getByProjectId(project.getId());
		put("step6", step6);
		List<Annex> annexList = annexService.getAnnexs(project.getId());
		put("annexList", annexList);
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
		Page<Step8> pageBean = step8Service.getPage(getPageNo(), getPageSize(),
				project.getNo(), project.getName(), projectTypeId,
				Integer.parseInt(Constants.StepCode.STEP8));
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
			step8 = step8Service.getByProjectId(project.getId());
			put("step8", step8);

			List<Step8Item> step8ItemList = step8ItemService
					.getByStep8Id(step8.getId());
			put("step8ItemList", step8ItemList); // 投标策划 成员表
			project = projectService.get(project.getId());

			Step6 step6 = step6Service.getByProjectId(project.getId());
			put("step6", step6);
			
			List<Annex> annexList = annexService.getAnnexs(project.getId());
			put("annexList", annexList);
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
			step8 = step8Service.getByProjectId(project.getId());
			put("step8", step8);
		}
		List<Step8Item> step8ItemList = step8ItemService
				.getByStep8Id(step8.getId());
		put("step8ItemList", step8ItemList); // 投标策划 成员表
		project = projectService.get(project.getId());

		Step6 step6 = step6Service.getByProjectId(project.getId());
		put("step6", step6);
		List<Annex> annexList = annexService.getAnnexs(project.getId());
		put("annexList", annexList);
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
		step8 = step8Service.get(step8.getId());
		step8.setStatus(-1);
		step8Service.update(step8);

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

	public Step8Item getStep8Item() {
		return step8Item;
	}

	public void setStep8Item(Step8Item step8Item) {
		this.step8Item = step8Item;
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

	public Step8 getStep8() {
		return step8;
	}

	public void setStep8(Step8 step8) {
		this.step8 = step8;
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
