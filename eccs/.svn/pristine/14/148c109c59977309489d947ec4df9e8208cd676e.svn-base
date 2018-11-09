package com.smart.web.action.step1;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.model.Annex;
import com.smart.model.AnnexType;
import com.smart.model.ProceStepDef;
import com.smart.model.Project;
import com.smart.model.ProjectProcessState;
import com.smart.model.ProjectType;
import com.smart.model.Step1;
import com.smart.model.Step1Contact;
import com.smart.model.Step3Worker;
import com.smart.model.T_Customer;
import com.smart.model.T_hremployee;
import com.smart.service.AnnexService;
import com.smart.service.AnnexTypeService;
import com.smart.service.ProceStepDefService;
import com.smart.service.ProjectProcessStateService;
import com.smart.service.ProjectService;
import com.smart.service.ProjectTypeService;
import com.smart.service.Step1ContactService;
import com.smart.service.Step1Service;
import com.smart.service.Step3WorkerService;
import com.smart.service.T_CustomerService;
import com.smart.service.T_hremployeeService;
import com.smart.util.Constants;
import com.smart.util.EmailSendTool;
import com.smart.util.FileRepository;
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
public class Step1Action extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private Step1Service step1Service;

	@Autowired
	private Step3WorkerService step3WorkerService;

	@Autowired
	private AnnexService annexService;

	@Autowired
	private AnnexTypeService annexTypeService;

	@Autowired
	private ProjectTypeService projectTypeService;

	@Autowired
	private Step1ContactService step1ContactService;

	@Autowired
	private T_CustomerService t_CustomerService;

	@Autowired
	private ProjectProcessStateService processStateService;
	
	@Autowired
	private T_hremployeeService hremployeeService;
	
	@Autowired
	private ProceStepDefService proceStepDefService;

	private String id;

	private File file;

	private String fileFileName;

	private Integer annexTypeId;

	private Project project; // 项目

	private Step1 step1;

	private Integer projectTypeId;// 工程类型

	private Integer nextWorkerId; // 下一步工作指定人
	// 添加联系人

	private String name;// 名字

	private String officeTel;// 办公电话

	private String tel;// 电话

	private String fax;// 传真

	private String qqemail;// qq

	private String fileNum;// 文件字号

	private String fileOwner;// 文件作者

	private String description;// 归档时间

	private String filePage;// 页号

	private int aid;// 上传附件的id

	private Integer projectid;

	/**
	 * 新建
	 */
	@Action("new")
	public String _new() {
		step1 = new Step1();
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
		String[] arrename = {};
		String[] arrofficeTel = {};
		String[] arrtel = {};
		String[] arrfax = {};
		String[] arremail = {};
		if (!StringUtil.isBlank(name)) { // 评标专家
			arrename = name.split(",");
			arrofficeTel = officeTel.split(",");
			arrtel = tel.split(",");
			arrfax = fax.split(",");
			arremail = qqemail.split(",");
		}
		T_Customer tCustomer = t_CustomerService
				.get(project.getCustomer().getId());
		tCustomer.setAddress(step1.getSenderAddress());
		t_CustomerService.update(tCustomer);
		Step1 oldStep1 = step1Service.get(step1.getId());
		// 属性拷贝，把更新内容拷贝到数据库查出的project里，最后的那个参数是不需要拷贝的属性名称
		ReflectionUtil.bean2Bean(oldStep1, step1, "id, ctime, status");
		oldStep1.setUser(this.getUser());
		oldStep1.setDept(this.getMyDept());
		oldStep1.setProject(project);
		oldStep1.setConfirmTime(new Date());
		step1Service.update(oldStep1);
		for (int i = 0; i < arrename.length; i++) { // 竞争对手
			Step1Contact sc = new Step1Contact();
			sc.setName(arrename[i]);
			sc.setOfficeTel(arrofficeTel[i]);
			sc.setTel(arrtel[i]);
			sc.setFax(arrfax[i]);
			sc.setEmail(arremail[i]);
			sc.setStep1(oldStep1);
			sc.setUser(this.getUser());
			sc.setDept(this.getMyDept());
			step1ContactService.save(sc);
		}
		Step3Worker step3Worker = step3WorkerService.get(project.getId(),
				ProjectProcessState.TYPE_PM,
				Integer.parseInt(Constants.StepCode.STEP2));
		ProjectProcessState pps = processStateService
				.getOneByProjectIdAndBusinesstype(project.getId(),
						Integer.parseInt(ProjectProcessState.TYPE_PM));
		// 更新项目进程状态表信息
		pps.setProjectInfo(project.getProjectInfo());
		updatePsd(pps, Constants.StepCode.STEP2, step3Worker.getWorkUserId());
		// 更新项目进程处理历史记录表
		savePph(project, Constants.StepCode.STEP1, Constants.StepCode.STEP2,
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
					.getStepByStepCode(Constants.StepCode.STEP2);
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
		String[] arrofficeTel = {};
		String[] arrtel = {};
		String[] arrfax = {};
		String[] arremail = {};
		if (!StringUtil.isBlank(name)) { // 评标专家
			arrename = name.split(",");
			arrofficeTel = officeTel.split(",");
			arrtel = tel.split(",");
			arrfax = fax.split(",");
			arremail = qqemail.split(",");
		}
		if (project.getId() != null) {
			project = projectService.get(project.getId());
		}
		T_Customer tCustomer = t_CustomerService
				.get(project.getCustomer().getId());
		tCustomer.setAddress(step1.getSenderAddress());
		t_CustomerService.update(tCustomer);
		Step1 oldStep1 = step1Service.get(step1.getId());
		// 属性拷贝，把更新内容拷贝到数据库查出的project里，最后的那个参数是不需要拷贝的属性名称
		ReflectionUtil.bean2Bean(oldStep1, step1, "id, ctime, status");
		oldStep1.setUser(this.getUser());
		oldStep1.setDept(this.getMyDept());
		oldStep1.setProject(project);
		step1Service.update(oldStep1);
		for (int i = 0; i < arrename.length; i++) {
			Step1Contact sc = new Step1Contact();
			sc.setName(arrename[i]);
			sc.setOfficeTel(arrofficeTel[i]);
			sc.setTel(arrtel[i]);
			sc.setFax(arrfax[i]);
			sc.setEmail(arremail[i]);
			sc.setStep1(oldStep1);
			sc.setUser(this.getUser());
			sc.setDept(this.getMyDept());
			step1ContactService.save(sc);
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
			pps.setProjectInfo(project.getProjectInfo());
			updatePsd(pps, Constants.StepCode.STEP1,
					StringUtil.array2Str(userIds, ","));
			// 更新项目进程处理历史记录表
			savePph(project, Constants.StepCode.STEP1, Constants.StepCode.STEP1,
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
					.getStepByStepCode(Constants.StepCode.STEP1);
			EmailSendTool sendEmail = new EmailSendTool();
			String subject = sendEmail.getSubject();
			String emailContent = sendEmail.buildContent(project.getNo(),
					project.getName(), nextStep.getStepName());
			sendEmail.send(emailAddress.toString(), subject, emailContent);
			
		} else {
			// 更新项目进程处理历史记录表
			savePph(project, Constants.StepCode.STEP1, Constants.StepCode.STEP1,
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
			step1 = step1Service.getByProjectId(project.getId());
			List<Step1Contact> step1ContactList = step1ContactService
					.getByStep1Id(step1.getId());
			put("step1ContactList", step1ContactList); // 成员信息表
			List<Annex> annexList = annexService.getByProjectId(
					step1.getProject().getId(), Constants.StepCode.STEP1);
			put("annexList", annexList);
			project = step1.getProject();
		} else {
			List<Step1Contact> step1ContactList = step1ContactService
					.getByStep1Id(step1.getId());
			put("step1ContactList", step1ContactList); // 成员信息表
			step1 = step1Service.get(step1.getId());
			List<Annex> annexList = annexService.getByProjectId(
					step1.getProject().getId(), Constants.StepCode.STEP1);
			put("annexList", annexList);
			project = step1.getProject();
		}
		return "new";
	}

	@Action("deleteannex")
	public String deleteannex() {
		Annex annex = annexService.get(aid);
		annex.setStatus(-1);
		annexService.update(annex);
		write("1");
		return null;
	}

	/**
	 * 列表
	 */
	@Action("list")
	public String list() {// 用户id 项目id
		List<ProjectType> projectTypeList = projectTypeService.getList();
		put("projectTypeList", projectTypeList);
		project = project == null ? new Project() : project;
		if (projectid != null && projectid != 0) {
			project = projectService.get(projectid);
		}
		Page<Step1> pageBean = step1Service.getPage(getPageNo(), getPageSize(),
				project.getNo(), project.getName(), projectTypeId,
				Integer.parseInt(Constants.StepCode.STEP1));
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
			step1 = step1Service.getByProjectId(project.getId());
			List<Step1Contact> step1ContactList = step1ContactService
					.getByStep1Id(step1.getId());
			put("step1ContactList", step1ContactList); // 投标策划 成员表
			List<Annex> annexList = annexService.getByProjectId(
					step1.getProject().getId(), Constants.StepCode.STEP1);
			put("annexList", annexList);
			project = step1.getProject();
		}
		return "show";
	}

	@Action("print")
	public String print() {
		if (project != null && project.getId() != null) {
			step1 = step1Service.getByProjectId(project.getId());
			List<Step1Contact> step1ContactList = step1ContactService
					.getByStep1Id(step1.getId());
			put("step1ContactList", step1ContactList); // 投标策划 成员表
			List<Annex> annexList = annexService.getByProjectId(
					step1.getProject().getId(), Constants.StepCode.STEP1);
			put("annexList", annexList);
			project = step1.getProject();
		}
		return "print";
	}

	@Action("uploadFile")
	public String uploadFile() throws Exception {
		project = projectService.get(project.getId());
		String filedir = "step1/" + project.getId() + "/";
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
		annex.setStepName(Constants.StepCode.STEP1);
		// ----------------------
		annex.setFileNum(fileNum);// 文件字号
		annex.setFileOwner(fileOwner);// 文件作者
		annex.setDescription(description); // 描述
		annex.setFilePage(filePage);// 页号

		annex.setUser(this.getUser());
		annex.setDept(this.getMyDept());
		annex.setPath(root);
		annexService.save(annex);
		List<Annex> annexList = annexService.getByProjectId(project.getId(),
				Constants.StepCode.STEP1);
		put("annexList", annexList);
		StringBuffer data = new StringBuffer();
		data.append("[tr]");
		data.append(
				"[td style='text-align: center; font-weight: bold; background-color: #d3e0f1; width: 100px;']");
		data.append("文件名称");
		data.append("[/td]");
		data.append(
				"[td style='text-align: center; font-weight: bold; background-color: #d3e0f1;']");
		data.append("文件类型");
		data.append("[/td]");
		data.append(
				"[td style='text-align: center; font-weight: bold; background-color: #d3e0f1;']");
		data.append("上传人");
		data.append("[/td]");
		data.append(
				"[td style='text-align: center; font-weight: bold; background-color: #d3e0f1;']");
		data.append("上传时间");
		data.append("[/td]");
		data.append(
				"[td style='text-align: center; font-weight: bold; background-color: #d3e0f1; width: 42px;']");
		data.append("操作");
		data.append("[/td]");
		data.append("[/tr]");
		for (Annex ax : annexList) {
			StringBuffer tr = new StringBuffer();
			tr.append("[tr]");
			tr.append("[td style='text-align:center;background-color:#fff;']");
			tr.append(ax.getName());
			tr.append("[/td]");
			tr.append("[td style='text-align:center;background-color:#fff;']");
			tr.append(ax.getAnnexType().getName());
			tr.append("[/td]");
			tr.append("[td style='text-align:center;background-color:#fff;']");
			tr.append(ax.getUser().getName());
			tr.append("[/td]");
			tr.append("[td style='text-align:center;background-color:#fff;']");
			String ctime = "";
			if (ax.getCtime() != null) {
				ctime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(ax.getCtime());
			}
			tr.append(ctime);
			tr.append("[/td]");
			tr.append("[td style='text-align:center;background-color:#fff;']");
			tr.append(
					"[a href='javascript:delProjectInfo(" + ax.getId() + ")']");
			tr.append("删除");
			tr.append("[/a]");
			tr.append("[/td]");
			tr.append("[/tr]");
			data.append(tr.toString());
		}
		write(data.toString()); // ajax请求用write
		return null;
	}

	@Action("deletesz")
	public String deletesz() {
		step1ContactService.delete(Integer.parseInt(id));
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
		step1 = step1Service.get(step1.getId());
		step1.setStatus(-1);
		step1Service.update(step1);

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOfficeTel() {
		return officeTel;
	}

	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getQqemail() {
		return qqemail;
	}

	public void setQqemail(String qqemail) {
		this.qqemail = qqemail;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFilePage() {
		return filePage;
	}

	public void setFilePage(String filePage) {
		this.filePage = filePage;
	}

	public Integer getNextWorkerId() {
		return nextWorkerId;
	}

	public void setNextWorkerId(Integer nextWorkerId) {
		this.nextWorkerId = nextWorkerId;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Step1 getStep1() {
		return step1;
	}

	public void setStep1(Step1 step1) {
		this.step1 = step1;
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
