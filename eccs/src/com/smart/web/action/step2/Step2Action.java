package com.smart.web.action.step2;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.model.Annex;
import com.smart.model.AnnexType;
import com.smart.model.ConsultType;
import com.smart.model.ProceStepDef;
import com.smart.model.Project;
import com.smart.model.ProjectProcessState;
import com.smart.model.ProjectType;
import com.smart.model.Step2File;
import com.smart.model.Step3Worker;
import com.smart.model.T_hremployee;
import com.smart.model.Step2;
import com.smart.service.AnnexService;
import com.smart.service.AnnexTypeService;
import com.smart.service.ConsultTypeService;
import com.smart.service.ProceStepDefService;
import com.smart.service.ProjectProcessStateService;
import com.smart.service.ProjectService;
import com.smart.service.ProjectTypeService;
import com.smart.service.Step2FileService;
import com.smart.service.Step3WorkerService;
import com.smart.service.T_hremployeeService;
import com.smart.service.Step2Service;
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
public class Step2Action extends BaseAction {

	private static final long serialVersionUID = -1316570009361104760L;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private Step2Service step2Service;

	@Autowired
	private Step2FileService step2FileService;

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
	
	private Project project; // 项目

	private Step2 step2;

	private Integer projectTypeId;// 工程类型

	private Integer consultTypeId;// 暂无用

	//private List<Step2File> step2Filelist;

	private Step2File step2File;

	/*private String count;*/

	private Integer nextWorkerId; // 下一步工作指定人

	private Integer projectid;

	private Integer fileId;// 文件id
	
	private String id;

	/**
	 * 新建
	 */
	@Action("new")
	public String _new() {
		List<Object> numList = new ArrayList<Object>();
		for (int i = 0; i < 7; i++) {
			Step2File[] step2arr = new Step2File[2];
			numList.add(step2arr);
		}
		put("numList", numList);
		List<ConsultType> consultTypeList = consultTypeService.getAll();
		put("consultTypeList", consultTypeList);
		step2 = new Step2();
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
		/*String[] arrename = {};
		String[] arrcount = {};
		if (!StringUtil.isBlank(name)) {
			arrename = name.split(",");
			arrcount = count.split(",");
		}*/
		Step2 oldStep2 = step2Service.get(step2.getId());
		// 属性拷贝，把更新内容拷贝到数据库查出的project里，最后的那个参数是不需要拷贝的属性名称
		ReflectionUtil.bean2Bean(oldStep2, step2, "id, ctime, status");
		oldStep2.setUser(this.getUser());
		oldStep2.setDept(this.getMyDept());
		oldStep2.setProject(project);
		oldStep2.setConfirmTime(new Date());
		step2Service.update(oldStep2);
		/*for (int i = 0; i < arrename.length; i++) {
			Step2File sf = new Step2File();
			if (!StringUtil.isBlank(arrename[i].trim())) {
				sf.setName(arrename[i]);
				sf.setCount(arrcount[i]);
				sf.setStep2(oldStep2);
				sf.setUser(this.getUser());
				sf.setDept(this.getMyDept());
				step2FileService.save(sf);
			}
		}*/
		
		/*上传功能*/
		if (StringUtil.isBlank(isuploads)) {
			return "list";
		}
		String filedir = "step2/" + project.getId() + "/";
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
						annextType.setStepName(Constants.StepCode.STEP2);
						annextType.setForcombo("no");
						annextType.setName(annexTypeNameARR[i].trim());
						annexTypeService.save(annextType);
						annex.setAnnexType(annextType);
					}
					long size = filetemp.getTotalSpace();
					annex.setSize(size);
					annex.setStepName(Constants.StepCode.STEP2);
					annex.setUser(this.getUser());
					annex.setDept(this.getMyDept());

					annex.setFileNum(fileNumARR[i]);// 文件字号
					annex.setFileOwner(fileOwnerARR[i]);// 文件作者
					annex.setFilePage(filePageARR[i]);// 页号
					annex.setDescription(descriptionARR[i]);
					annex.setPath(root);
					annexService.save(annex);
					List<Annex> annexList = annexService.getByProjectId(
							project.getId(), Constants.StepCode.STEP2);
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
				Integer.parseInt(Constants.StepCode.STEP3));
		ProjectProcessState pps = processStateService
				.getOneByProjectIdAndBusinesstype(project.getId(),
						Integer.parseInt(ProjectProcessState.TYPE_PM));
		// 更新项目进程状态表信息
		updatePsd(pps, Constants.StepCode.STEP3, step3Worker.getWorkUserId());
		// 更新项目进程处理历史记录表
		savePph(project, Constants.StepCode.STEP2, Constants.StepCode.STEP3,
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
					.getStepByStepCode(Constants.StepCode.STEP3);
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
		/*String[] arrename = {};
		String[] arrcount = {};
		if (!StringUtil.isBlank(name)) { // 评标专家
			arrename = name.split(",");
			arrcount = count.split(",");
		}*/
		if (project.getId() != null) {
			project = projectService.get(project.getId());
		}
		Step2 oldStep2 = step2Service.get(step2.getId());
		// 属性拷贝，把更新内容拷贝到数据库查出的project里，最后的那个参数是不需要拷贝的属性名称
		ReflectionUtil.bean2Bean(oldStep2, step2, "id, ctime, status");
		oldStep2.setUser(this.getUser());
		oldStep2.setDept(this.getMyDept());
		oldStep2.setProject(project);
		oldStep2.setConfirmTime(new Date());
		step2Service.update(oldStep2);
		// step2FileService.deleteByStep2Id(step2.getId());
		/*for (int i = 0; i < arrename.length; i++) { // 竞争对手
			Step2File sf = new Step2File();
			if (!StringUtil.isBlank(arrename[i].trim())) {
				sf.setName(arrename[i]);
				sf.setCount(arrcount[i]);
				sf.setStep2(oldStep2);
				sf.setUser(this.getUser());
				sf.setDept(this.getMyDept());
				step2FileService.save(sf);
			}
		}*/
		
		/*上传功能*/
		if (StringUtil.isBlank(isuploads)) {
			return "list";
		}
		String filedir = "step2/" + project.getId() + "/";
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
						annextType.setStepName(Constants.StepCode.STEP2);
						annextType.setForcombo("no");
						annextType.setName(annexTypeNameARR[i].trim());
						annexTypeService.save(annextType);
						annex.setAnnexType(annextType);
					}
					long size = filetemp.getTotalSpace();
					annex.setSize(size);
					annex.setStepName(Constants.StepCode.STEP2);
					annex.setUser(this.getUser());
					annex.setDept(this.getMyDept());

					annex.setFileNum(fileNumARR[i]);// 文件字号
					annex.setFileOwner(fileOwnerARR[i]);// 文件作者
					annex.setFilePage(filePageARR[i]);// 页号
					annex.setDescription(descriptionARR[i]);
					annex.setPath(root);
					annexService.save(annex);
					List<Annex> annexList = annexService.getByProjectId(
							project.getId(), Constants.StepCode.STEP2);
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

		if (nextWorkerId != null) { //转交给其它用户
			String[] userIds = StringUtil.str1RemoveStr3(pps.getCurrentUsers(),
					Integer.toString(getUser().getId()),
					Integer.toString(nextWorkerId), ",");
			// 更新项目进程状态表信息
			updatePsd(pps, Constants.StepCode.STEP2,
					StringUtil.array2Str(userIds, ","));
			// 更新项目进程处理历史记录表
			savePph(project, Constants.StepCode.STEP2, Constants.StepCode.STEP2,
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
					.getStepByStepCode(Constants.StepCode.STEP2);
			EmailSendTool sendEmail = new EmailSendTool();
			String subject = sendEmail.getSubject();
			String emailContent = sendEmail.buildContent(project.getNo(),
					project.getName(), nextStep.getStepName());
			sendEmail.send(emailAddress.toString(), subject, emailContent);
			
		} else {
			// 更新项目进程处理历史记录表
			savePph(project, Constants.StepCode.STEP2, Constants.StepCode.STEP2,
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
			step2 = step2Service.getByProjectId(project.getId());
			//step2Filelist = step2FileService.getByStep2Id(step2.getId());
			List<Annex> annexList = annexService.getByProjectId(project.getId(),
					Constants.StepCode.STEP2);
			if (annexList.size() < 1) {
				List<AnnexType> annexTypeList = annexTypeService
						.getByStep(Constants.StepCode.STEP2, "yes");
				for (AnnexType annexType : annexTypeList) {
					Annex annex = new Annex();
					annex.setAnnexType(annexType);
					annexList.add(annex);
				}
			}
			put("annexList", annexList);
			List<ConsultType> consultTypeList = consultTypeService.getAll();
			put("consultTypeList", consultTypeList);
			project = step2.getProject();
		} else {
			List<ConsultType> consultTypeList = consultTypeService.getAll();
			put("consultTypeList", consultTypeList);
			step2 = step2Service.get(step2.getId());
			project = step2.getProject();
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
		List<Annex> annexList = annexService.getByProjectId(project.getId(),
				Constants.StepCode.STEP2);
		put("annexList", annexList);
		Page<Step2> pageBean = step2Service.getPage(getPageNo(), getPageSize(),
				project.getNo(), project.getName(), projectTypeId,
				Integer.parseInt(Constants.StepCode.STEP2));
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
			step2 = step2Service.getByProjectId(project.getId());
			//List<Step2File> step2FileList = step2FileService.getByStep2Id(step2.getId());
			// List<Object> numList = new ArrayList<Object>();
			// for(int i=0;i<step2FileList.size();i=i+2){
			// Step2File[] step2arr = new Step2File[2];
			// step2arr[0]=step2FileList.get(i);
			// if(i+1<step2FileList.size()){
			// step2arr[1]=step2FileList.get(i+1);
			// }
			// numList.add(step2arr);
			// }
			//put("numList", step2FileList);
			List<Annex> annexList = annexService.getByProjectId(project.getId(),
					Constants.StepCode.STEP2);
			put("annexList", annexList);
			List<ConsultType> consultTypeList = consultTypeService.getAll();
			put("consultTypeList", consultTypeList);
			project = step2.getProject();
		}
		// else{
		// List<Step2File> step2FileList =
		// step2FileService.getByStep2Id(step2.getId());
		// put("step2FileList", step2FileList);
		// List<Object> numList = new ArrayList<Object>();
		// for(int i=0;i<step2FileList.size();i=i+2){
		// Step2File[] step2arr = new Step2File[2];
		// step2arr[0]=step2FileList.get(i);
		// if(i+1<step2FileList.size()){
		// step2arr[1]=step2FileList.get(i+1);
		// }
		// numList.add(step2arr);
		// }
		// put("numList", step2FileList);
		// List<ConsultType> consultTypeList = consultTypeService.getAll();
		// put("consultTypeList", consultTypeList);
		// step2 = step2Service.get(step2.getId());
		// project = step2.getProject();
		// }
		return "show";
	}

	/*
	 * 打印
	 * 
	 */
	@Action("print")
	public String print() {
		step2 = step2Service.getByProjectId(project.getId());
		//List<Step2File> step2FileList = step2FileService.getByStep2Id(step2.getId());
		//put("numList", step2FileList);
		List<Annex> annexList = annexService.getByProjectId(project.getId(),
				Constants.StepCode.STEP2);
		put("annexList", annexList);
		List<ConsultType> consultTypeList = consultTypeService.getAll();
		put("consultTypeList", consultTypeList);
		project = step2.getProject();
		return "print";
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	/*@Action("delete")
	public String delete() {
		step2 = step2Service.get(step2.getId());
		step2.setStatus(-1);
		step2Service.update(step2);

		write("1"); // ajax请求用write返回数据
		return null;
	}*/
	
	@Action("deletesz")
	public String deletesz() {
		annexService.delete(Integer.parseInt(id));
		write("1");
		return null;
	}


	@Action("deleteFile")
	public String deleteFile() {
		step2FileService.delete(fileId);
		return null;
	}

	// ====================== getter and setter ===========================
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Step2 getStep2() {
		return step2;
	}

	public void setStep2(Step2 step2) {
		this.step2 = step2;
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

	public Step2File getStep2File() {
		return step2File;
	}

	public void setStep2File(Step2File step2File) {
		this.step2File = step2File;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}*/

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

	/*public List<Step2File> getStep2Filelist() {
		return step2Filelist;
	}

	public void setStep2Filelist(List<Step2File> step2Filelist) {
		this.step2Filelist = step2Filelist;
	}*/

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
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
