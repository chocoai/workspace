package com.smart.web.action.step15;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.model.Annex;
import com.smart.model.AnnexType;
import com.smart.model.Project;
import com.smart.model.ProjectProcessState;
import com.smart.model.ProjectType;
import com.smart.model.Step15;
import com.smart.service.AnnexService;
import com.smart.service.AnnexTypeService;
import com.smart.service.EmailService;
import com.smart.service.ProjectProcessStateService;
import com.smart.service.ProjectService;
import com.smart.service.ProjectTypeService;
import com.smart.service.Step15Service;
import com.smart.util.Constants;
import com.smart.util.FileRepository;
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
public class Step15Action extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private Step15Service step15Service;

	@Autowired
	private ProjectTypeService projectTypeService;

	@Autowired
	private AnnexService annexService;

	@Autowired
	private AnnexTypeService annexTypeService;

	@Autowired
	private ProjectProcessStateService processStateService;

	private Step15 step15;

	private String annexId;

	private Integer annexTypeId;

	private String annexTypeName;

	private Annex annex;

	private Project project; // 项目

	private Integer projectTypeId;// 工程类型

	private File file;

	private String fileFileName;

	private String description;

	private String fileNum;// 文件字号

	private String fileOwner;// 文件作者

	private String filePage;// 页号

	private Integer nextWorkerId; // 下一步工作指定人

	private Integer projectid;

	private String i ;
	
	/**
	 * 新建
	 */
	@Action("new")
	public String _new() {
		List<AnnexType> annexTypeList = annexTypeService
				.getByStep(Constants.StepCode.STEP15, "yes");
		put("annexTypeList", annexTypeList);

		return "new";
	}

	/**
	 * 保存并执行下一步
	 */
	@Action(value = "toNextStep", results = {
			@Result(name = "workflowNormal", location = "../project/workflow", type = "redirectAction", params = {
					"id", "%{project.id}" }) })
	public String toNextStep() {
		if (project.getId() != null) {
			project = projectService.get(project.getId());
			projectService.save(project);
		}
		if (annexId != null) {
			String[] annexIdARR = annexId.split(",");
			for (int i = 0; i < annexIdARR.length; i++) {
				if (!StringUtil.isBlank(annexIdARR[i].trim())) {
					annex = annexService
							.get(Integer.valueOf(annexIdARR[i].trim()));
					annex.setFiledTime(new Date());
					annexService.save(annex);
				}
			}
		}
		if (step15.getId() == null) { // 新增
		} else { // 更新
			Step15 oldStep15 = step15Service.get(step15.getId());
			// 属性拷贝，把更新内容拷贝到数据库查出的project里，最后的那个参数是不需要拷贝的属性名称
			ReflectionUtil.bean2Bean(oldStep15, step15, "id, ctime, status");
			oldStep15.setUser(this.getUser());
			oldStep15.setDept(this.getMyDept());
			oldStep15.setProject(project);
			oldStep15.setConfirmTime(new Date());
			step15Service.update(oldStep15);
		}
		ProjectProcessState pps = processStateService
				.getOneByProjectIdAndBusinesstype(project.getId(),
						Integer.parseInt(ProjectProcessState.TYPE_PM));
		// 更新项目进程状态表信息
		updatePsd(pps, Constants.StepCode.ARCHIVED, null);
		// 更新项目进程处理历史记录表
		savePph(project, Constants.StepCode.STEP15, null,
				Constants.OperateType.SUBMIT, null);
		return "workflowNormal";

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
		if (step15.getId() != null) { // 新增
			Step15 oldStep15 = step15Service.get(step15.getId());
			// 属性拷贝，把更新内容拷贝到数据库查出的project里，最后的那个参数是不需要拷贝的属性名称
			ReflectionUtil.bean2Bean(oldStep15, step15, "id, ctime, status，dealUserId");
			oldStep15.setUser(this.getUser());
			oldStep15.setDept(this.getMyDept());
			oldStep15.setProject(project);
			step15Service.update(oldStep15);
		}
		boolean is = false;
		ProjectProcessState pps = processStateService
				.getOneByProjectIdAndBusinesstype(project.getId(),
						Integer.parseInt(ProjectProcessState.TYPE_PM));

		if (nextWorkerId != null) {
			String[] strs = StringUtil.str1RemoveStr3(pps.getCurrentUsers(),
					Integer.toString(getUser().getId()),
					Integer.toString(nextWorkerId), ",");
			// 更新项目进程状态表信息
			updatePsd(pps, Constants.StepCode.STEP15,
					StringUtil.array2Str(strs, ","));
			// 更新项目进程处理历史记录表
			savePph(project, Constants.StepCode.STEP15,
					Constants.StepCode.STEP15, Constants.OperateType.TRANSFER,
					StringUtil.array2Str(strs, ","));
			is = false;
			// 发送邮件通知下一步处理人有待办事项需要处理
			EmailService emailService = SpringUtil.getBean(EmailService.class);
			emailService.sendEmail(StringUtil.array2Str(strs, ","), project, Constants.StepCode.STEP15);
		} else {
			// 更新项目进程处理历史记录表
			savePph(project, Constants.StepCode.STEP15,
					Constants.StepCode.STEP15, Constants.OperateType.SAVE,
					pps.getCurrentUsers());
			is = true;
		}
		if (is) {
			return "workflowNormal";
		} else {
			return "myProjectList";
		}
	}

	/**
	 * 审核
	 */
	@Action(value = "toValidate", results = {
			@Result(name = "workflowNormal", location = "../project/workflow", type = "redirectAction", params = {
					"id", "%{project.id}" }),
			@Result(name = "myProjectList", location = "../project/myProjectList", type = "redirectAction") })
	public String toValidate() {
		if (project.getId() != null) {
			project = projectService.get(project.getId());
		}
		System.out.println("step15.validateStatus:"+step15.getValidateStatus());
		System.out.println("nextWorkerId:"+nextWorkerId);
		System.out.println("step15.getReceiveManagerId():"+step15.getReceiveManagerId());
		if (step15.getId() != null) { // 新增
			Step15 oldStep15 = step15Service.get(step15.getId());
			// 属性拷贝，把更新内容拷贝到数据库查出的project里，最后的那个参数是不需要拷贝的属性名称
			ReflectionUtil.bean2Bean(oldStep15, step15, "id, ctime, status");
			oldStep15.setUser(this.getUser());
			oldStep15.setDept(this.getMyDept());
			oldStep15.setProject(project);
			step15Service.update(oldStep15);
		}
		boolean is = false;
		ProjectProcessState pps = processStateService
				.getOneByProjectIdAndBusinesstype(project.getId(),
						Integer.parseInt(ProjectProcessState.TYPE_PM));

		if (step15.getReceiveManagerId() != null) {
			// 更新项目进程状态表信息
			updatePsd(pps, Constants.StepCode.STEP15,
					String.valueOf(step15.getReceiveManagerId()));
			// 更新项目进程处理历史记录表
			savePph(project, Constants.StepCode.STEP15,
					Constants.StepCode.STEP15, Constants.OperateType.APPROVAL,
					String.valueOf(step15.getReceiveManagerId()));
			is = false;
			// 发送邮件通知下一步处理人有待办事项需要处理
			EmailService emailService = SpringUtil.getBean(EmailService.class);
			emailService.sendEmail(String.valueOf(step15.getReceiveManagerId()), project, Constants.StepCode.STEP15);
			if (String.valueOf(step15.getReceiveManagerId()).equals(Integer.toString(getUser().getId()))) {
				is = true;
			}
		} else {
			// 更新项目进程处理历史记录表
			savePph(project, Constants.StepCode.STEP15,
					Constants.StepCode.STEP15, Constants.OperateType.APPROVAL,
					pps.getCurrentUsers());
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
			step15 = step15Service.getByProjectId(project.getId());
			List<Annex> annexList = annexService
					.getByProjectId(step15.getProject().getId(), "");
			put("annexList", annexList);
			project = step15.getProject();
		} else {
			step15 = step15Service.get(step15.getId());
			List<Annex> annexList = annexService
					.getByProjectId(step15.getProject().getId(), "");
			put("annexList", annexList);
			project = step15.getProject();
		}
		String wordUser = Constants.UserType.NEW;
		if(null != step15.getValidateStatus()){
			if (step15.getValidateStatus() == 1) {
			    wordUser = Constants.UserType.VALIDATE;
			}
		}
		return wordUser;
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
		Page<Step15> pageBean = step15Service.getPage(getPageNo(),
				getPageSize(), project.getNo(), project.getName(),
				projectTypeId, Integer.parseInt(Constants.StepCode.STEP15));
		put("pageBean", pageBean);
		return "list";
	}

	@Action("uploadFile")
	public String uploadFile() throws Exception {
		project = projectService.get(project.getId());

		String filedir = "step15/" + project.getId() + "/";
		FileRepository fileRepository = new FileRepository();
		String root = fileRepository.storeByExt(filedir, fileFileName, file);

		Annex annex = new Annex();
		annex.setName(fileFileName);
		annex.setProject(project);
		if (annexTypeId != null) {
			AnnexType annextType = annexTypeService.get(annexTypeId);
			annex.setAnnexType(annextType);
		}
		long size = file.getTotalSpace();
		annex.setSize(size);
		annex.setStepName(Constants.StepCode.STEP15);
		annex.setUser(this.getUser());
		annex.setDept(this.getMyDept());
		annex.setFileNum(fileNum);// 文件字号
		annex.setFileOwner(fileOwner);// 文件作者
		annex.setFilePage(filePage);// 页号
		annex.setFiledTime(new Date());// 页号
		annex.setDescription(description);
		annex.setPath(root);
		annexService.save(annex);
		List<Annex> annexList = annexService.getByProjectId(project.getId(),
				"");
		put("annexList", annexList);
		write("1"); // ajax请求用write
		return null;
	}

	/**
	 * 显示
	 * 
	 * @return
	 */
	@Action("show")
	public String show() {
		if (project != null && project.getId() != null) {
			step15 = step15Service.getByProjectId(project.getId());
			List<Annex> annexList = annexService
					.getByProjectId(step15.getProject().getId(), "");
			put("annexList", annexList);
			project = step15.getProject();
		}
		// else{
		// step15 = step15Service.get(step15.getId());
		// List<Annex> annexList =
		// annexService.getByProjectId(step15.getProject().getId(),"");
		// put("annexList", annexList);
		// project = step15.getProject();
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
		step15 = step15Service.getByProjectId(project.getId());
		List<Annex> annexList = annexService
				.getByProjectId(step15.getProject().getId(), "");
		put("annexList", annexList);
		project = step15.getProject();
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

		return null;
	}

	// ====================== getter and setter ===========================
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Integer getProjectTypeId() {
		return projectTypeId;
	}

	public void setProjectTypeId(Integer projectTypeId) {
		this.projectTypeId = projectTypeId;
	}

	public Step15 getSte15() {
		return step15;
	}

	public void setStep15(Step15 step15) {
		this.step15 = step15;
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

	public Step15 getStep15() {
		return step15;
	}

	public Integer getAnnexTypeId() {
		return annexTypeId;
	}

	public void setAnnexTypeId(Integer annexTypeId) {
		this.annexTypeId = annexTypeId;
	}

	public String getAnnexTypeName() {
		return annexTypeName;
	}

	public void setAnnexTypeName(String annexTypeName) {
		this.annexTypeName = annexTypeName;
	}

	public Annex getAnnex() {
		return annex;
	}

	public void setAnnex(Annex annex) {
		this.annex = annex;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFileNum() {
		return fileNum;
	}

	public void setFileNum(String fileNum) {
		this.fileNum = fileNum;
	}

	public String getFileOwner() {
		return fileOwner;
	}

	public void setFileOwner(String fileOwner) {
		this.fileOwner = fileOwner;
	}

	public String getFilePage() {
		return filePage;
	}

	public void setFilePage(String filePage) {
		this.filePage = filePage;
	}

	public String getAnnexId() {
		return annexId;
	}

	public void setAnnexId(String annexId) {
		this.annexId = annexId;
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

	public String getI() {
		return i;
	}

	public void setI(String i) {
		this.i = i;
	}

}
