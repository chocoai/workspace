package com.smart.web.action.step14;

import java.io.File;
import java.text.SimpleDateFormat;
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
import com.smart.model.Step1;
import com.smart.model.Step14;
import com.smart.model.Step3Worker;
import com.smart.service.AnnexService;
import com.smart.service.AnnexTypeService;
import com.smart.service.EmailService;
import com.smart.service.ProjectProcessStateService;
import com.smart.service.ProjectService;
import com.smart.service.ProjectTypeService;
import com.smart.service.Step1Service;
import com.smart.service.Step3WorkerService;
import com.smart.service.Step14Service;
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
public class Step14Action extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private Step1Service step1Service;

	@Autowired
	private Step14Service step14Service;

	@Autowired
	private Step3WorkerService step3WorkerService;

	@Autowired
	private ProjectTypeService projectTypeService;

	@Autowired
	private ProjectProcessStateService processStateService;

	private Project project; // 项目

	private Step14 step14;

	private Integer projectTypeId;// 工程类型

	private Integer nextWorkerId; // 下一步工作指定人

	private Integer projectid;
	
	@Autowired
	private AnnexService annexService;

	@Autowired
	private AnnexTypeService annexTypeService;
	
	private String isuploads;

	private String description;

	private String fileNum;// 文件字号

	private String fileOwner;// 文件作者

	private String filePage;// 页号
	
	private String annexId;

	private String annexTypeId;

	private String annexTypeName;
	
	private List<File> file;

	private List<String> fileFileName;
	
	private String name;
	
	private String id;

	/**
	 * 新建
	 */
	@Action("new")
	public String _new() {

		step14 = new Step14();
		String ctime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		put("user", this.getUser());
		put("ctime", ctime);
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
			step14.setProject(project);
		}
		if (step14.getId() == null) { // 新增
		} else { // 更新
			Step14 oldStep14 = step14Service.get(step14.getId());
			// 属性拷贝，把更新内容拷贝到数据库查出的project里，最后的那个参数是不需要拷贝的属性名称
			ReflectionUtil.bean2Bean(oldStep14, step14, "id, ctime, status");
			oldStep14.setUser(this.getUser());
			oldStep14.setDept(this.getMyDept());
			oldStep14.setConfirmTime(new Date());
			step14Service.update(oldStep14);
		}
		/*上传功能*/
		if (StringUtil.isBlank(isuploads)) {
			return "list";
		}
		String filedir = "step14/" + project.getId() + "/";
		FileRepository fileRepository = new FileRepository();
		String[] isuploadArr = isuploads.split(",");
		String[] annexTypeIdARR = annexTypeId.split(",");
		String[] annexTypeNameARR = annexTypeName.split(",");
		String[] nameARR = name.split(",");
		String[] annexIdARR = annexId.split(",");
		String[] descriptionARR = description.split(",");
		String[] fileNumARR = fileNum.split(",");// 文件字号
		String[] fileOwnerARR = fileOwner.split(",");// 文件作者
		String[] filePageARR = filePage.split(",");// 页号
		for (int i = 0, j = 0; i < isuploadArr.length; i++) {
			if ("1".equals(isuploadArr[i].trim())) { // 页面中
				// 这个属性为1才表示有附件添加,否则不做处理
				try {
					File filetemp = file.get(j);
					String filenametemp = fileFileName.get(j);
					j++; // 文件位置
					String root = fileRepository.storeByExt(filedir,
							filenametemp, filetemp);
					Annex annex = new Annex();
					if (!StringUtil.isBlank(annexIdARR[i].trim())) {
						annex = annexService
								.get(Integer.valueOf(annexIdARR[i].trim()));
					}
					annex.setName(filenametemp);
					annex.setProject(project);
					if (!StringUtil.isBlank(annexTypeIdARR[i].trim())) {
						AnnexType annextType = annexTypeService
								.get(Integer.valueOf(annexTypeIdARR[i].trim()));
						annex.setAnnexType(annextType);
					} else {
						AnnexType annextType = new AnnexType();
						annextType.setStepName(Constants.StepCode.STEP14);
						annextType.setForcombo("no");
						annextType.setName(annexTypeNameARR[i].trim());
						annexTypeService.save(annextType);
						annex.setAnnexType(annextType);
					}
					long size = filetemp.getTotalSpace();
					annex.setSize(size);
					annex.setStepName(Constants.StepCode.STEP14);
					annex.setUser(this.getUser());
					annex.setDept(this.getMyDept());

					annex.setFileNum(fileNumARR[i]);// 文件字号
					annex.setFileOwner(fileOwnerARR[i]);// 文件作者
					annex.setFilePage(filePageARR[i]);// 页号
					annex.setDescription(descriptionARR[i]);
					annex.setPath(root);
					annexService.save(annex);
					List<Annex> annexList = annexService.getByProjectId(
							project.getId(), Constants.StepCode.STEP14);
					put("annexList", annexList);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (!StringUtil.isBlank(annexIdARR[i].trim())) {
				Annex annex = annexService
						.get(Integer.valueOf(annexIdARR[i].trim()));
				annex.setName(nameARR[i]);
				annex.setFileNum(fileNumARR[i]);// 文件字号
				annex.setFileOwner(fileOwnerARR[i]);// 文件作者
				annex.setFilePage(filePageARR[i]);// 页号
				annex.setDescription(descriptionARR[i]);
				annexService.save(annex);
			} 
		}
		Step3Worker step3Worker = step3WorkerService.get(project.getId(),
				ProjectProcessState.TYPE_PM,
				Integer.parseInt(Constants.StepCode.STEP15));
		ProjectProcessState pps = processStateService
				.getOneByProjectIdAndBusinesstype(project.getId(),
						Integer.parseInt(ProjectProcessState.TYPE_PM));
		// 更新项目进程状态表信息
		updatePsd(pps, Constants.StepCode.STEP15, step3Worker.getWorkUserId());
		// 更新项目进程处理历史记录表
		savePph(project, Constants.StepCode.STEP14, Constants.StepCode.STEP15,
				Constants.OperateType.SUBMIT, step3Worker.getWorkUserId());
		
		// 发送邮件通知下一步处理人有待办事项需要处理
		EmailService emailService = SpringUtil.getBean(EmailService.class);
		emailService.sendEmail(step3Worker.getWorkUserId(), project, Constants.StepCode.STEP15);
		
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
			step14.setProject(project);
		}
		if (step14.getId() == null) { // 新增
		} else { // 更新
			Step14 oldStep14 = step14Service.get(step14.getId());
			// 属性拷贝，把更新内容拷贝到数据库查出的project里，最后的那个参数是不需要拷贝的属性名称
			ReflectionUtil.bean2Bean(oldStep14, step14, "id, ctime, status");
			oldStep14.setUser(this.getUser());
			oldStep14.setDept(this.getMyDept());
			oldStep14.setConfirmTime(new Date());
			step14Service.update(oldStep14);
		}
		/*上传功能*/
		if (StringUtil.isBlank(isuploads)) {
			return "list";
		}
		String filedir = "step14/" + project.getId() + "/";
		FileRepository fileRepository = new FileRepository();
		String[] isuploadArr = isuploads.split(",");
		String[] annexTypeIdARR = annexTypeId.split(",");
		String[] annexTypeNameARR = annexTypeName.split(",");
		String[] nameARR = name.split(",");
		String[] annexIdARR = annexId.split(",");
		String[] descriptionARR = description.split(",");
		String[] fileNumARR = fileNum.split(",");// 文件字号
		String[] fileOwnerARR = fileOwner.split(",");// 文件作者
		String[] filePageARR = filePage.split(",");// 页号
		for (int i = 0, j = 0; i < isuploadArr.length; i++) {
			if ("1".equals(isuploadArr[i].trim())) { // 页面中
				// 这个属性为1才表示有附件添加,否则不做处理
				try {
					File filetemp = file.get(j);
					String filenametemp = fileFileName.get(j);
					j++; // 文件位置
					String root = fileRepository.storeByExt(filedir,
							filenametemp, filetemp);
					Annex annex = new Annex();
					if (!StringUtil.isBlank(annexIdARR[i].trim())) {
						annex = annexService
								.get(Integer.valueOf(annexIdARR[i].trim()));
					}
					annex.setName(filenametemp);
					annex.setProject(project);
					if (!StringUtil.isBlank(annexTypeIdARR[i].trim())) {
						AnnexType annextType = annexTypeService
								.get(Integer.valueOf(annexTypeIdARR[i].trim()));
						annex.setAnnexType(annextType);
					} else {
						AnnexType annextType = new AnnexType();
						annextType.setStepName(Constants.StepCode.STEP14);
						annextType.setForcombo("no");
						annextType.setName(annexTypeNameARR[i].trim());
						annexTypeService.save(annextType);
						annex.setAnnexType(annextType);
					}
					long size = filetemp.getTotalSpace();
					annex.setSize(size);
					annex.setStepName(Constants.StepCode.STEP14);
					annex.setUser(this.getUser());
					annex.setDept(this.getMyDept());

					annex.setFileNum(fileNumARR[i]);// 文件字号
					annex.setFileOwner(fileOwnerARR[i]);// 文件作者
					annex.setFilePage(filePageARR[i]);// 页号
					annex.setDescription(descriptionARR[i]);
					annex.setPath(root);
					annexService.save(annex);
					List<Annex> annexList = annexService.getByProjectId(
							project.getId(), Constants.StepCode.STEP14);
					put("annexList", annexList);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (!StringUtil.isBlank(annexIdARR[i].trim())) {
				Annex annex = annexService
						.get(Integer.valueOf(annexIdARR[i].trim()));
				annex.setName(nameARR[i]);
				annex.setFileNum(fileNumARR[i]);// 文件字号
				annex.setFileOwner(fileOwnerARR[i]);// 文件作者
				annex.setFilePage(filePageARR[i]);// 页号
				annex.setDescription(descriptionARR[i]);
				annexService.save(annex);
			} 
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
			updatePsd(pps, Constants.StepCode.STEP14,
					StringUtil.array2Str(strs, ","));
			// 更新项目进程处理历史记录表
			savePph(project, Constants.StepCode.STEP14,
					Constants.StepCode.STEP14, Constants.OperateType.TRANSFER,
					StringUtil.array2Str(strs, ","));
			is = false;
			// 发送邮件通知下一步处理人有待办事项需要处理
			EmailService emailService = SpringUtil.getBean(EmailService.class);
			emailService.sendEmail(StringUtil.array2Str(strs, ","), project, Constants.StepCode.STEP14);
		} else {
			// 更新项目进程处理历史记录表
			savePph(project, Constants.StepCode.STEP14,
					Constants.StepCode.STEP14, Constants.OperateType.SAVE,
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
	 * 编辑
	 */
	@Action("edit")
	public String edit() {
		if (project != null && project.getId() != null) {
			Step1 step1 = step1Service.getByProjectId(project.getId());
			step14 = step14Service.getByProjectId(project.getId());
			if (step14.getCtime() != null) {
				String ctime = new SimpleDateFormat("yyyy-MM-dd")
						.format(step14.getCtime());
				put("ctime", ctime);
			}
			put("step1", step1);
			project = step14.getProject();
			List<Annex> annexList = annexService.getByProjectId(project.getId(),
					Constants.StepCode.STEP14);
			if (annexList.size() < 1) {
				List<AnnexType> annexTypeList = annexTypeService
						.getByStep(Constants.StepCode.STEP14, "yes");
				for (AnnexType annexType : annexTypeList) {
					Annex annex = new Annex();
					annex.setAnnexType(annexType);
					annexList.add(annex);
				}
			}
			put("annexList", annexList);
			// project.setProjectType(contractService.getList(project.getId()).get(0).getProjectType());
		} else {
			step14 = step14Service.get(step14.getId());
			if (step14.getCtime() != null) {
				String ctime = new SimpleDateFormat("yyyy-MM-dd")
						.format(step14.getCtime());
				put("ctime", ctime);
			}
			project = step14.getProject();
			// project.setProjectType(contractService.getList(project.getId()).get(0).getProjectType());
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
		Page<Step14> pageBean = step14Service.getPage(getPageNo(),
				getPageSize(), project.getNo(), project.getName(),
				projectTypeId, Integer.parseInt(Constants.StepCode.STEP14));
		put("pageBean", pageBean);
		List<Annex> annexList = annexService.getByProjectId(project.getId(),
				Constants.StepCode.STEP14);
		put("annexList", annexList);
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
			Step1 step1 = step1Service.getByProjectId(project.getId());
			step14 = step14Service.getByProjectId(project.getId());
			if (step14.getCtime() != null) {
				String ctime = new SimpleDateFormat("yyyy-MM-dd")
						.format(step14.getCtime());
				put("ctime", ctime);
			}
			List<Annex> annexList = annexService.getByProjectId(project.getId(),
					Constants.StepCode.STEP14);
			put("annexList", annexList);
			project = step14.getProject();
			put("step1", step1);
			// project.setProjectType(contractService.getList(project.getId()).get(0).getProjectType());
		}
		// else{
		// step14 = step14Service.get(step14.getId());
		// if(step14.getCtime()!=null){
		// String ctime = new
		// SimpleDateFormat("yyyy-MM-dd").format(step14.getCtime());
		// put("ctime", ctime);
		// }
		// project = step14.getProject();
		// //project.setProjectType(contractService.getList(project.getId()).get(0).getProjectType());
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
		step14 = step14Service.getByProjectId(project.getId());
		Step1 step1 = step1Service.getByProjectId(project.getId());
		if (step14.getCtime() != null) {
			String ctime = new SimpleDateFormat("yyyy-MM-dd")
					.format(step14.getCtime());
			put("ctime", ctime);
		}
		put("step1", step1);
		List<Annex> annexList = annexService.getByProjectId(project.getId(),
				Constants.StepCode.STEP14);
		put("annexList", annexList);
		project = step14.getProject();
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
		step14 = step14Service.get(step14.getId());
		step14.setStatus(-1);
		step14Service.update(step14);

		write("1"); // ajax请求用write返回数据
		return null;
	}
	
	@Action("deletesz")
	public String deletesz() {
		annexService.delete(Integer.parseInt(id));
		write("1");
		return null;
	}

	// ====================== getter and setter ===========================
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Step14 getStep14() {
		return step14;
	}

	public void setStep14(Step14 step14) {
		this.step14 = step14;
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

	/**
	 * @return the isuploads
	 */
	public String getIsuploads() {
		return isuploads;
	}

	/**
	 * @param isuploads the isuploads to set
	 */
	public void setIsuploads(String isuploads) {
		this.isuploads = isuploads;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the fileNum
	 */
	public String getFileNum() {
		return fileNum;
	}

	/**
	 * @param fileNum the fileNum to set
	 */
	public void setFileNum(String fileNum) {
		this.fileNum = fileNum;
	}

	/**
	 * @return the fileOwner
	 */
	public String getFileOwner() {
		return fileOwner;
	}

	/**
	 * @param fileOwner the fileOwner to set
	 */
	public void setFileOwner(String fileOwner) {
		this.fileOwner = fileOwner;
	}

	/**
	 * @return the filePage
	 */
	public String getFilePage() {
		return filePage;
	}

	/**
	 * @param filePage the filePage to set
	 */
	public void setFilePage(String filePage) {
		this.filePage = filePage;
	}

	/**
	 * @return the annexId
	 */
	public String getAnnexId() {
		return annexId;
	}

	/**
	 * @param annexId the annexId to set
	 */
	public void setAnnexId(String annexId) {
		this.annexId = annexId;
	}

	/**
	 * @return the annexTypeId
	 */
	public String getAnnexTypeId() {
		return annexTypeId;
	}

	/**
	 * @param annexTypeId the annexTypeId to set
	 */
	public void setAnnexTypeId(String annexTypeId) {
		this.annexTypeId = annexTypeId;
	}

	/**
	 * @return the annexTypeName
	 */
	public String getAnnexTypeName() {
		return annexTypeName;
	}

	/**
	 * @param annexTypeName the annexTypeName to set
	 */
	public void setAnnexTypeName(String annexTypeName) {
		this.annexTypeName = annexTypeName;
	}

	/**
	 * @return the file
	 */
	public List<File> getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(List<File> file) {
		this.file = file;
	}

	/**
	 * @return the fileFileName
	 */
	public List<String> getFileFileName() {
		return fileFileName;
	}

	/**
	 * @param fileFileName the fileFileName to set
	 */
	public void setFileFileName(List<String> fileFileName) {
		this.fileFileName = fileFileName;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	

}
