package com.smart.web.action.step13;

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
import com.smart.model.Step13;
import com.smart.model.Step13Item;
import com.smart.model.Step3Worker;
import com.smart.service.AnnexService;
import com.smart.service.AnnexTypeService;
import com.smart.service.EmailService;
import com.smart.service.ProjectProcessStateService;
import com.smart.service.ProjectService;
import com.smart.service.ProjectTypeService;
import com.smart.service.Step13ItemService;
import com.smart.service.Step13Service;
import com.smart.service.Step3WorkerService;
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
public class Step13Action extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private Step13Service step13Service;

	@Autowired
	private Step13ItemService step13ItemService;

	@Autowired
	private ProjectTypeService projectTypeService;

	@Autowired
	private Step3WorkerService step3WorkerService;

	private Integer nextWorkerId; // 下一步工作指定人

	@Autowired
	private ProjectProcessStateService processStateService;

	private Project project; // 项目

	private Step13 step13;

	private Integer projectTypeId;// 工程类型

	private Step13Item step13Item;// 征询意见主要内容

	private String content;// 征询意见主要内容

	private String values;// 满意度

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

		step13 = new Step13();
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
		if (!StringUtil.isBlank(content)) { // 评标专家
			arrcontent = content.split(",");
			arrvalues = values.split(",");
		}
		if (project.getId() != null) {
			project = projectService.get(project.getId());
			step13.setProject(project);
		}
		if (step13.getId() == null) { // 新增
		} else { // 更新
			Step13 oldStep13 = step13Service.get(step13.getId());
			// 属性拷贝，把更新内容拷贝到数据库查出的project里，最后的那个参数是不需要拷贝的属性名称
			ReflectionUtil.bean2Bean(oldStep13, step13, "id, ctime, status");
			oldStep13.setConfirmTime(new Date());
			step13Service.update(oldStep13);
			for (int i = 0; i < arrcontent.length; i++) { // 竞争对手
				Step13Item si = new Step13Item();
				si.setContent(arrcontent[i].trim());
				String value = arrvalues[i].trim();
				String result = this.get("result_" + value);
				si.setResult(result);
				si.setUser(this.getUser());
				si.setDept(this.getMyDept());
				si.setStep13(oldStep13);
				step13ItemService.save(si);
			}
		}
		/*上传功能*/
		if (StringUtil.isBlank(isuploads)) {
			return "list";
		}
		String filedir = "step13/" + project.getId() + "/";
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
						annextType.setStepName(Constants.StepCode.STEP13);
						annextType.setForcombo("no");
						annextType.setName(annexTypeNameARR[i].trim());
						annexTypeService.save(annextType);
						annex.setAnnexType(annextType);
					}
					long size = filetemp.getTotalSpace();
					annex.setSize(size);
					annex.setStepName(Constants.StepCode.STEP13);
					annex.setUser(this.getUser());
					annex.setDept(this.getMyDept());

					annex.setFileNum(fileNumARR[i]);// 文件字号
					annex.setFileOwner(fileOwnerARR[i]);// 文件作者
					annex.setFilePage(filePageARR[i]);// 页号
					annex.setDescription(descriptionARR[i]);
					annex.setPath(root);
					annexService.save(annex);
					List<Annex> annexList = annexService.getByProjectId(
							project.getId(), Constants.StepCode.STEP13);
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
				Integer.parseInt(Constants.StepCode.STEP14));
		ProjectProcessState pps = processStateService
				.getOneByProjectIdAndBusinesstype(project.getId(),
						Integer.parseInt(ProjectProcessState.TYPE_PM));
		// 更新项目进程状态表信息
		updatePsd(pps, Constants.StepCode.STEP14, step3Worker.getWorkUserId());
		// 更新项目进程处理历史记录表
		savePph(project, Constants.StepCode.STEP13, Constants.StepCode.STEP14,
				Constants.OperateType.SUBMIT, step3Worker.getWorkUserId());
		
		// 发送邮件通知下一步处理人有待办事项需要处理
		EmailService emailService = SpringUtil.getBean(EmailService.class);
		emailService.sendEmail(step3Worker.getWorkUserId(), project, Constants.StepCode.STEP14);

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
		String[] arrcontent = {};
		String[] arrvalues = {};
		if (!StringUtil.isBlank(content)) {
			arrcontent = content.split(",");
			arrvalues = values.split(",");
		}
		if (project.getId() != null) {
			project = projectService.get(project.getId());
			step13.setProject(project);
		}
		if (step13.getId() == null) { // 新增
		} else { // 更新
			Step13 oldStep13 = step13Service.get(step13.getId());
			// 属性拷贝，把更新内容拷贝到数据库查出的project里，最后的那个参数是不需要拷贝的属性名称
			ReflectionUtil.bean2Bean(oldStep13, step13, "id, ctime, status");
			oldStep13.setConfirmTime(new Date());
			step13Service.update(oldStep13);
			for (int i = 0; i < arrcontent.length; i++) { // 竞争对手
				Step13Item si = new Step13Item();
				si.setContent(arrcontent[i].trim());
				String value = arrvalues[i].trim();
				String result = this.get("result_" + value);
				si.setResult(result);
				si.setUser(this.getUser());
				si.setDept(this.getMyDept());
				si.setStep13(oldStep13);
				step13ItemService.save(si);
			}
		}
		/*上传功能*/
		if (StringUtil.isBlank(isuploads)) {
			return "list";
		}
		String filedir = "step13/" + project.getId() + "/";
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
						annextType.setStepName(Constants.StepCode.STEP13);
						annextType.setForcombo("no");
						annextType.setName(annexTypeNameARR[i].trim());
						annexTypeService.save(annextType);
						annex.setAnnexType(annextType);
					}
					long size = filetemp.getTotalSpace();
					annex.setSize(size);
					annex.setStepName(Constants.StepCode.STEP13);
					annex.setUser(this.getUser());
					annex.setDept(this.getMyDept());

					annex.setFileNum(fileNumARR[i]);// 文件字号
					annex.setFileOwner(fileOwnerARR[i]);// 文件作者
					annex.setFilePage(filePageARR[i]);// 页号
					annex.setDescription(descriptionARR[i]);
					annex.setPath(root);
					annexService.save(annex);
					List<Annex> annexList = annexService.getByProjectId(
							project.getId(), Constants.StepCode.STEP13);
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
			updatePsd(pps, Constants.StepCode.STEP13,
					StringUtil.array2Str(strs, ","));
			// 更新项目进程处理历史记录表
			savePph(project, Constants.StepCode.STEP13,
					Constants.StepCode.STEP13, Constants.OperateType.TRANSFER,
					StringUtil.array2Str(strs, ","));
			is = false;
			// 发送邮件通知下一步处理人有待办事项需要处理
			EmailService emailService = SpringUtil.getBean(EmailService.class);
			emailService.sendEmail(StringUtil.array2Str(strs, ","), project, Constants.StepCode.STEP13);
		} else {
			// 更新项目进程处理历史记录表
			savePph(project, Constants.StepCode.STEP13,
					Constants.StepCode.STEP13, Constants.OperateType.SAVE,
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
			step13 = step13Service.getByProjectId(project.getId());
			List<Step13Item> step13ItemList = step13ItemService
					.getByStep13Id(step13.getId());
			put("step13ItemList", step13ItemList); // 投标策划 成员表
			project = step13.getProject();
			List<Annex> annexList = annexService.getByProjectId(project.getId(),
					Constants.StepCode.STEP13);
			if (annexList.size() < 1) {
				List<AnnexType> annexTypeList = annexTypeService
						.getByStep(Constants.StepCode.STEP13, "yes");
				for (AnnexType annexType : annexTypeList) {
					Annex annex = new Annex();
					annex.setAnnexType(annexType);
					annexList.add(annex);
				}
			}
			put("annexList", annexList);
		} else {
			List<Step13Item> step13ItemList = step13ItemService
					.getByStep13Id(step13.getId());
			put("step13ItemList", step13ItemList); // 投标策划 成员表
			step13 = step13Service.get(step13.getId());
			project = step13.getProject();
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
		Page<Step13> pageBean = step13Service.getPage(getPageNo(),
				getPageSize(), project.getNo(), project.getName(),
				projectTypeId, Integer.parseInt(Constants.StepCode.STEP13));
		put("pageBean", pageBean);
		return "list";
	}

	/*@Action("deletesz")
	public String deletesz() {
		step13ItemService.delete(Integer.parseInt(id));
		return null;
	}*/

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
			step13 = step13Service.getByProjectId(project.getId());
			List<Step13Item> step13ItemList = step13ItemService
					.getByStep13Id(step13.getId());
			put("step13ItemList", step13ItemList); // 投标策划 成员表
			List<Annex> annexList = annexService.getByProjectId(project.getId(),
					Constants.StepCode.STEP13);
			put("annexList", annexList);
			project = step13.getProject();
		}
		// else{
		// List<Step13Item> step13ItemList =
		// step13ItemService.getByStep13Id(step13.getId());
		// put("step13ItemList", step13ItemList); //投标策划 成员表
		// step13 = step13Service.get(step13.getId());
		// project = step13.getProject();
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
		step13 = step13Service.getByProjectId(project.getId());
		List<Step13Item> step13ItemList = step13ItemService
				.getByStep13Id(step13.getId());
		put("step13ItemList", step13ItemList); // 投标策划 成员表
		List<Annex> annexList = annexService.getByProjectId(project.getId(),
				Constants.StepCode.STEP13);
		put("annexList", annexList);
		project = step13.getProject();
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
		step13 = step13Service.get(step13.getId());
		step13.setStatus(-1);
		step13Service.update(step13);
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

	public Step13 getStep13() {
		return step13;
	}

	public void setStep13(Step13 step13) {
		this.step13 = step13;
	}

	public Step13Item getStep13Item() {
		return step13Item;
	}

	public void setStep13Item(Step13Item step13Item) {
		this.step13Item = step13Item;
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
}
