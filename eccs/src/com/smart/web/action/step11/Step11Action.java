package com.smart.web.action.step11;

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
import com.smart.model.Step11;
import com.smart.model.Step3Worker;

import com.smart.service.AnnexService;
import com.smart.service.AnnexTypeService;
import com.smart.service.EmailService;
import com.smart.service.ProjectProcessStateService;
import com.smart.service.ProjectService;
import com.smart.service.ProjectTypeService;
import com.smart.service.Step11Service;
import com.smart.service.Step3WorkerService;

import com.smart.util.Constants;
import com.smart.util.FileRepository;
import com.smart.util.Page;
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
public class Step11Action extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private Step11Service step11Service;

	@Autowired
	private ProjectTypeService projectTypeService;

	@Autowired
	private AnnexService annexService;

	@Autowired
	private AnnexTypeService annexTypeService;

	@Autowired
	private Step3WorkerService step3WorkerService;

	@Autowired
	private ProjectProcessStateService processStateService;

	private Step11 step11;

	private List<File> file;

	private List<String> fileFileName;

	private List<String> fileContentType;

	private String annexId;

	private String annexTypeId;

	private String annexTypeName;

	private Annex annex;

	private Project project; // 项目

	private Integer projectTypeId;

	private String isuploads;

	private String name;

	private String description;

	private String fileNum;// 文件字号

	private String fileOwner;// 文件作者

	private String filePage;// 页号

	private Integer nextWorkerId; // 下一步工作指定人

	private String id;

	private Integer projectid;

	/**
	 * 新建
	 */
	@Action("new")
	public String _new() {
		List<AnnexType> annexTypeList = annexTypeService
				.getByStep(Constants.StepCode.STEP11, "yes");
		put("annexTypeList", annexTypeList);
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
			projectService.save(project);
			step11 = step11Service.getByProjectId(project.getId());
			step11.setConfirmTime(new Date());
			step11Service.save(step11);
		}
		if (StringUtil.isBlank(isuploads)) {
			return "workflowNormal";
		}
		String filedir = "step11/" + project.getId() + "/";
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
						annextType.setStepName(Constants.StepCode.STEP11);
						annextType.setForcombo("no");
						annextType.setName(annexTypeNameARR[i].trim());
						annexTypeService.save(annextType);
						annex.setAnnexType(annextType);
					}
					long size = filetemp.getTotalSpace();
					annex.setSize(size);
					annex.setStepName(Constants.StepCode.STEP11);
					annex.setUser(this.getUser());
					annex.setDept(this.getMyDept());

					annex.setFileNum(fileNumARR[i]);// 文件字号
					annex.setFileOwner(fileOwnerARR[i]);// 文件作者
					annex.setFilePage(filePageARR[i]);// 页号
					annex.setDescription(descriptionARR[i]);

					annex.setPath(root);
					annexService.save(annex);
					List<Annex> annexList = annexService.getByProjectId(
							project.getId(), Constants.StepCode.STEP11);
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
				Integer.parseInt(Constants.StepCode.STEP12));
		ProjectProcessState pps = processStateService
				.getOneByProjectIdAndBusinesstype(project.getId(),
						Integer.parseInt(ProjectProcessState.TYPE_PM));
		// 更新项目进程状态表信息
		updatePsd(pps, Constants.StepCode.STEP12, step3Worker.getWorkUserId());
		// 更新项目进程处理历史记录表
		savePph(project, Constants.StepCode.STEP11, Constants.StepCode.STEP12,
				Constants.OperateType.SUBMIT, step3Worker.getWorkUserId());
		
		// 发送邮件通知下一步处理人有待办事项需要处理
		EmailService emailService = SpringUtil.getBean(EmailService.class);
		emailService.sendEmail(step3Worker.getWorkUserId(), project, Constants.StepCode.STEP12);

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
		if (StringUtil.isBlank(isuploads)) {
			return "list";
		}
		project = projectService.get(project.getId());
		String filedir = "step11/" + project.getId() + "/";
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
				// 这个属性为1才表示有附件添加,负责不做处理
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
						annextType.setStepName(Constants.StepCode.STEP11);
						annextType.setForcombo("no");
						annextType.setName(annexTypeNameARR[i].trim());
						annexTypeService.save(annextType);
						annex.setAnnexType(annextType);
					}
					long size = filetemp.getTotalSpace();
					annex.setSize(size);
					annex.setStepName(Constants.StepCode.STEP11);
					annex.setUser(this.getUser());
					annex.setDept(this.getMyDept());

					annex.setFileNum(fileNumARR[i]);// 文件字号
					annex.setFileOwner(fileOwnerARR[i]);// 文件作者
					annex.setFilePage(filePageARR[i]);// 页号
					annex.setDescription(descriptionARR[i]);

					annex.setPath(root);
					annexService.save(annex);
					List<Annex> annexList = annexService.getByProjectId(
							project.getId(), Constants.StepCode.STEP11);
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
			updatePsd(pps, Constants.StepCode.STEP11,
					StringUtil.array2Str(strs, ","));
			// 更新项目进程处理历史记录表
			savePph(project, Constants.StepCode.STEP11,
					Constants.StepCode.STEP11, Constants.OperateType.TRANSFER,
					StringUtil.array2Str(strs, ","));
			is = false;
			// 发送邮件通知下一步处理人有待办事项需要处理
			EmailService emailService = SpringUtil.getBean(EmailService.class);
			emailService.sendEmail(StringUtil.array2Str(strs, ","), project, Constants.StepCode.STEP11);
		} else {
			// 更新项目进程处理历史记录表
			savePph(project, Constants.StepCode.STEP11,
					Constants.StepCode.STEP11, Constants.OperateType.SAVE,
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
			step11 = step11Service.getByProjectId(project.getId());
			List<Annex> annexList = annexService.getByProjectId(project.getId(),
					Constants.StepCode.STEP11);
			if (annexList.size() < 1) {
				List<AnnexType> annexTypeList = annexTypeService
						.getByStep(Constants.StepCode.STEP11, "yes");
				for (AnnexType annexType : annexTypeList) {
					Annex annex = new Annex();
					annex.setAnnexType(annexType);
					annexList.add(annex);
				}
			}
			put("annexList", annexList);
			project = step11.getProject();
		}
		// else{
		// step11 = step11Service.get(step11.getId());
		// List<Annex> annexList =
		// annexService.getByProjectId(step11.getProject().getId(),STEP+"");
		// List<AnnexType> annexTypeList = annexTypeService.getByStep(STEP+"",
		// "yes");
		// put("annexTypeList", annexTypeList);
		// for(AnnexType annexType : annexTypeList){
		// boolean flag = false;
		// for(Annex annex : annexList){
		// if(annexType.getId().equals(annex.getAnnexType().getId())){
		// flag = true;
		// break;
		// }
		// }
		// if(!flag){
		// Annex annex = new Annex();
		// annex.setAnnexType(annexType);
		// annexList.add(annex);
		// }
		// }
		// put("annexList", annexList);
		// project = step11.getProject();
		// }
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
		Page<Step11> pageBean = step11Service.getPage(getPageNo(),
				getPageSize(), project.getNo(), project.getName(),
				projectTypeId, Integer.parseInt(Constants.StepCode.STEP11));
		put("pageBean", pageBean);
		return "list";
	}

	@Action("deletesz")
	public String deletesz() {
		annexService.delete(Integer.parseInt(id));
		write("1");
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
			step11 = step11Service.getByProjectId(project.getId());
			List<Annex> annexList = annexService.getByProjectId(project.getId(),
					Constants.StepCode.STEP11);
			put("annexList", annexList);
			project = step11.getProject();
		}
		// else{
		// step11 = step11Service.get(step11.getId());
		// List<Annex> annexList =
		// annexService.getByProjectId(step11.getProject().getId(),STEP+"");
		// List<AnnexType> annexTypeList = annexTypeService.getByStep(STEP+"",
		// "yes");
		// put("annexTypeList", annexTypeList);
		// for(AnnexType annexType : annexTypeList){
		// boolean flag = false;
		// for(Annex annex : annexList){
		// if(annexType.getId().equals(annex.getAnnexType().getId())){
		// flag = true;
		// break;
		// }
		// }
		// if(!flag){
		// Annex annex = new Annex();
		// annex.setAnnexType(annexType);
		// annexList.add(annex);
		// }
		// }
		// put("annexList", annexList);
		// project = step11.getProject();
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
		step11 = step11Service.getByProjectId(project.getId());
		List<Annex> annexList = annexService.getByProjectId(project.getId(),
				Constants.StepCode.STEP11);
		put("annexList", annexList);
		project = step11.getProject();
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

	public Step11 getSte11() {
		return step11;
	}

	public void setStep11(Step11 step11) {
		this.step11 = step11;
	}

	public List<File> getFile() {
		return file;
	}

	public void setFile(List<File> file) {
		this.file = file;
	}

	public List<String> getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(List<String> fileFileName) {
		this.fileFileName = fileFileName;
	}

	public List<String> getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(List<String> fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getAnnexTypeId() {
		return annexTypeId;
	}

	public void setAnnexTypeId(String annexTypeId) {
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

	public String getIsuploads() {
		return isuploads;
	}

	public void setIsuploads(String isuploads) {
		this.isuploads = isuploads;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getProjectid() {
		return projectid;
	}

	public void setProjectid(Integer projectid) {
		this.projectid = projectid;
	}

}
