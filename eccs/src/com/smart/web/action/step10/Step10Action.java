package com.smart.web.action.step10;

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
import com.smart.model.Step10;
import com.smart.model.Step10DFYJ;
import com.smart.model.Step10FKYJ;
import com.smart.model.Step3Worker;
import com.smart.model.Step6;
import com.smart.model.Step9;
import com.smart.model.User;
import com.smart.service.AnnexService;
import com.smart.service.AnnexTypeService;
import com.smart.service.EmailService;
import com.smart.service.ProjectProcessStateService;
import com.smart.service.ProjectService;
import com.smart.service.ProjectTypeService;
import com.smart.service.Step10DFYJService;
import com.smart.service.Step10FKYJService;
import com.smart.service.Step10Service;
import com.smart.service.Step3WorkerService;
import com.smart.service.Step6Service;
import com.smart.service.Step9Service;
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
public class Step10Action extends BaseAction {

    private static final long serialVersionUID = 1L;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private Step10Service step10Service;

    @Autowired
    private Step9Service step9Service;
    @Autowired
    private Step6Service step6Service;
    @Autowired
    private Step10DFYJService step10DFYJService;

    @Autowired
    private Step10FKYJService step10FKYJService;

    @Autowired
    private ProjectTypeService projectTypeService;

    @Autowired
    private Step3WorkerService step3WorkerService;

	@Autowired
	private ProjectProcessStateService processStateService;

    private Project project; // 项目

    private Step10 step10;

    private Integer projectTypeId;

    // (征求意见稿)意见反馈表
    private String contentFK;// 反馈意见内容

    private String reasonFK;// 理由或依据
    // (征求意见稿)答复意见表

    private String numZX;// 参阅规范或条款编号

    private String contentZX;// 修改意见内容

    private String reasonZX;// 理由或依据

    private Integer nextWorkerId; // 下一步工作指定人

    private String id;

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
	
    
    /**
     * 新建
     */
    @Action("new")
    public String _new() {

	step10 = new Step10();
	return "new";
    }

    /**
     * 编制信息保存
     */
    @Action(value = "writeSave", results = {
	    @Result(name = "workflowNormal", location = "../project/workflow", type = "redirectAction",params={"id","%{project.id}"}),
	    @Result(name = "myProjectList", location = "../project/myProjectList", type = "redirectAction") })
    public String writeSave() {
	if (project.getId() != null) {
	    project = projectService.get(project.getId());
	}

	String[] arrcontentFK = {};
	String[] arrreasonFK = {};
	if (!StringUtil.isBlank(contentFK)) {
	    arrcontentFK = contentFK.split(",");
	    arrreasonFK = reasonFK.split(",");
	}
	Step10 oldStep10 = step10Service.get(step10.getId());
	// 属性拷贝，把更新内容拷贝到数据库查出的project里，最后的那个参数是不需要拷贝的属性名称
	ReflectionUtil.bean2Bean(oldStep10, step10, "id, ctime, status");
	oldStep10.setUser(this.getUser());
	oldStep10.setDept(this.getMyDept());
	oldStep10.setProject(project);
	oldStep10.setConfirmTime(new Date());
	projectService.update(project);
	step10Service.update(oldStep10);

	for (int i = 0; i < arrcontentFK.length; i++) { // 反馈意见
	    Step10FKYJ fkyj = new Step10FKYJ();
	    if (!StringUtil.isBlank(arrcontentFK[i].trim())) {
		fkyj.setContent(arrcontentFK[i].trim());
		fkyj.setReason(arrreasonFK[i].trim());
		fkyj.setStep10(oldStep10);
		step10FKYJService.save(fkyj);
	    }
	}
	/*上传功能*/
	if (StringUtil.isBlank(isuploads)) {
		return "list";
	}
	String filedir = "step10/" + project.getId() + "/";
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
					annextType.setStepName(Constants.StepCode.STEP10);
					annextType.setForcombo("no");
					annextType.setName(annexTypeNameARR[i].trim());
					annexTypeService.save(annextType);
					annex.setAnnexType(annextType);
				}
				long size = filetemp.getTotalSpace();
				annex.setSize(size);
				annex.setStepName(Constants.StepCode.STEP10);
				annex.setUser(this.getUser());
				annex.setDept(this.getMyDept());

				annex.setFileNum(fileNumARR[i]);// 文件字号
				annex.setFileOwner(fileOwnerARR[i]);// 文件作者
				annex.setFilePage(filePageARR[i]);// 页号
				annex.setDescription(descriptionARR[i]);
				annex.setPath(root);
				annexService.save(annex);
				List<Annex> annexList = annexService.getByProjectId(
						project.getId(), Constants.StepCode.STEP10);
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
	ProjectProcessState pps = processStateService.getOneByProjectIdAndBusinesstype(project.getId(),Integer.parseInt(ProjectProcessState.TYPE_PM));
	 
	if (nextWorkerId != null) {
		String[] strs = StringUtil.str1RemoveStr3(pps.getCurrentUsers(), Integer.toString(getUser().getId()), Integer.toString(nextWorkerId), ",");
		// 更新项目进程状态表信息
	    updatePsd(pps,Constants.StepCode.STEP10,StringUtil.array2Str(strs,","));
	    // 更新项目进程处理历史记录表
	    savePph(project,Constants.StepCode.STEP10,Constants.StepCode.STEP10,Constants.OperateType.TRANSFER,StringUtil.array2Str(strs,","));
	    is = false;
		// 发送邮件通知下一步处理人有待办事项需要处理
		EmailService emailService = SpringUtil.getBean(EmailService.class);
		emailService.sendEmail(StringUtil.array2Str(strs,","), project, Constants.StepCode.STEP10);
	} else {
		// 更新项目进程处理历史记录表
		savePph(project,Constants.StepCode.STEP10,Constants.StepCode.STEP10,Constants.OperateType.SAVE,pps.getCurrentUsers());
		is = true;
	}
    if(is){
    	return "workflowNormal";
    }else{
    	return "myProjectList";
    }
    }

    /**
     * 提交审定
     */
    @Action(value = "toValidate", results = {
	    @Result(name = "workflowNormal", location = "../project/workflow", type = "redirectAction",params={"id","%{project.id}"}),
	    @Result(name = "myProjectList", location = "../project/myProjectList", type = "redirectAction") })
    public String toValidate() {
	String[] arrcontentFK = {};
	String[] arrreasonFK = {};
	if (!StringUtil.isBlank(contentFK)) {
	    arrcontentFK = contentFK.split(",");
	    arrreasonFK = reasonFK.split(",");
	}
	// 审定人。。。。
//	if (project.getId() != null) {
//	    project = projectService.get(project.getId());
//	    Step9 step9 = step9Service.getByProjectId(project.getId());
//	    project.setNextWorker(step9.getUser());
//	    put("project", project);
//	    projectService.update(project);
//	}
	Step10 oldStep10 = step10Service.get(step10.getId());
	// 属性拷贝，把更新内容拷贝到数据库查出的project里，最后的那个参数是不需要拷贝的属性名称
	ReflectionUtil.bean2Bean(oldStep10, step10, "id, ctime, status");
	oldStep10.setUser(this.getUser());
	oldStep10.setDept(this.getMyDept());
	oldStep10.setProject(project);
	oldStep10.setValidateStatus(1);
	oldStep10.setConfirmTime(new Date());
	
	Step9 step9 = step9Service.getByProjectId(project.getId());
	User currentUser = step9.getUser();
	oldStep10.setValidateUser(Integer.toString(currentUser.getId()));
	step10Service.update(oldStep10);

	for (int i = 0; i < arrcontentFK.length; i++) { // 反馈意见
	    Step10FKYJ fkyj = new Step10FKYJ();
	    if (!StringUtil.isBlank(arrcontentFK[i].trim())) {
		fkyj.setContent(arrcontentFK[i].trim());
		fkyj.setReason(arrreasonFK[i].trim());
		fkyj.setStep10(oldStep10);
		step10FKYJService.save(fkyj);
	    }
	}
	/*上传功能*/
	if (StringUtil.isBlank(isuploads)) {
		return "list";
	}
	String filedir = "step10/" + project.getId() + "/";
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
					annextType.setStepName(Constants.StepCode.STEP10);
					annextType.setForcombo("no");
					annextType.setName(annexTypeNameARR[i].trim());
					annexTypeService.save(annextType);
					annex.setAnnexType(annextType);
				}
				long size = filetemp.getTotalSpace();
				annex.setSize(size);
				annex.setStepName(Constants.StepCode.STEP10);
				annex.setUser(this.getUser());
				annex.setDept(this.getMyDept());

				annex.setFileNum(fileNumARR[i]);// 文件字号
				annex.setFileOwner(fileOwnerARR[i]);// 文件作者
				annex.setFilePage(filePageARR[i]);// 页号
				annex.setDescription(descriptionARR[i]);
				annex.setPath(root);
				annexService.save(annex);
				List<Annex> annexList = annexService.getByProjectId(
						project.getId(), Constants.StepCode.STEP10);
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
	ProjectProcessState pps = processStateService.getOneByProjectIdAndBusinesstype(project.getId(),Integer.parseInt(ProjectProcessState.TYPE_PM));
    // 更新项目进程状态表信息
	updatePsd(pps,Constants.StepCode.STEP10,Integer.toString(currentUser.getId()));
	// 更新项目进程处理历史记录表
	savePph(project, Constants.StepCode.STEP10, Constants.StepCode.STEP10, Constants.OperateType.APPROVAL,
			Integer.toString(currentUser.getId()));
	
	// 发送邮件通知下一步处理人有待办事项需要处理
	EmailService emailService = SpringUtil.getBean(EmailService.class);
	emailService.sendEmail(Integer.toString(currentUser.getId()), project, Constants.StepCode.STEP10);

    if(getUser().getId()==currentUser.getId()){
   	return "workflowNormal";
    }else{
   	return "myProjectList";
    }
    }

    /**
     * 审定信息保存
     */
    @Action(value = "validateSave", results = {
	    @Result(name = "workflowNormal", location = "../project/workflow", type = "redirectAction",params={"id","%{project.id}"}),
	    @Result(name = "myProjectList", location = "../project/myProjectList", type = "redirectAction") })
    public String validateSave() {
	if (project.getId() != null) {
	    project = projectService.get(project.getId());
	}

	String[] arrnumDF = {};
	String[] arrcontentDF = {};
	String[] arrreasonDF = {};
	if (!StringUtil.isBlank(numZX)) {
	    arrnumDF = numZX.split(",");
	    arrcontentDF = contentZX.split(",");
	    arrreasonDF = reasonZX.split(",");
	}
	Step10 oldStep10 = step10Service.get(step10.getId());
	oldStep10.setStatus(Integer.parseInt(get("status")));
	

	boolean is = false;
	ProjectProcessState pps = processStateService.getOneByProjectIdAndBusinesstype(project.getId(),Integer.parseInt(ProjectProcessState.TYPE_PM));
	 
	if (nextWorkerId != null) {
		oldStep10.setValidateUser(Integer.toString(nextWorkerId));
		// 更新项目进程状态表信息
	    updatePsd(pps,Constants.StepCode.STEP10,Integer.toString(nextWorkerId));
	    // 更新项目进程处理历史记录表
	    savePph(project,Constants.StepCode.STEP10,Constants.StepCode.STEP10,Constants.OperateType.TRANSFER,Integer.toString(nextWorkerId));
	    is = false;
		// 发送邮件通知下一步处理人有待办事项需要处理
		EmailService emailService = SpringUtil.getBean(EmailService.class);
		emailService.sendEmail(Integer.toString(nextWorkerId), project, Constants.StepCode.STEP10);
	} else {
		// 更新项目进程处理历史记录表
		savePph(project,Constants.StepCode.STEP10,Constants.StepCode.STEP10,Constants.OperateType.SAVE,pps.getCurrentUsers());
		is = true;
	}
	
	projectService.update(project);
	step10Service.update(oldStep10);

	for (int i = 0; i < arrnumDF.length; i++) { // 答复意见
	    Step10DFYJ dfyj = new Step10DFYJ();
	    if (!StringUtil.isBlank(arrnumDF[i].trim())) {
		dfyj.setNum(arrnumDF[i].trim());
		dfyj.setContent(arrcontentDF[i].trim());
		dfyj.setReason(arrreasonDF[i].trim());
		dfyj.setStep10(oldStep10);
		step10DFYJService.save(dfyj);
	    }
	}
    if(is){
    	return "workflowNormal";
    }else{
    	return "myProjectList";
    }
    }

    /**
     * 审定保存并执行下一步
     */
    @Action(value = "toNextStep", results = {
	    @Result(name = "workflowNormal", location = "../project/workflow", type = "redirectAction",params={"id","%{project.id}"}),
	    @Result(name = "myProjectList", location = "../project/myProjectList", type = "redirectAction") })
    public String toNextStep() {
	String[] arrnumDF = {};
	String[] arrcontentDF = {};
	String[] arrreasonDF = {};
	if (!StringUtil.isBlank(numZX)) {
	    arrnumDF = numZX.split(",");
	    arrcontentDF = contentZX.split(",");
	    arrreasonDF = reasonZX.split(",");
	}
	project = projectService.get(project.getId());
	Step10 oldStep10 = step10Service.get(step10.getId());
	String status = get("status");
	oldStep10.setStatus(Integer.parseInt(status));
	oldStep10.setValidateStatus(0);
	oldStep10.setConfirmTime(new Date());
	step10Service.update(oldStep10);

	for (int i = 0; i < arrnumDF.length; i++) { // 答复意见
	    Step10DFYJ dfyj = new Step10DFYJ();
	    if (!StringUtil.isBlank(arrnumDF[i].trim())) {
		dfyj.setNum(arrnumDF[i].trim());
		dfyj.setContent(arrcontentDF[i].trim());
		dfyj.setReason(arrreasonDF[i].trim());
		dfyj.setStep10(oldStep10);
		step10DFYJService.save(dfyj);
	    }
	}
	boolean is = false;
	ProjectProcessState pps = processStateService.getOneByProjectIdAndBusinesstype(project.getId(),Integer.parseInt(ProjectProcessState.TYPE_PM));
    if (status.equals("-1")) {//审定不通过
    	Step6 step6 = step6Service.getByProjectId(project.getId());
    	// 更新项目进程状态表信息
    	updatePsd(pps,Constants.StepCode.STEP6,step6.getCurrentUsers());
	    // 更新项目进程处理历史记录表
	    savePph(project,Constants.StepCode.STEP10,Constants.StepCode.STEP6,Constants.OperateType.REPULS,step6.getCurrentUsers());
	    is = StringUtil.str1ToStr2(step6.getCurrentUsers(), Integer.toString(getUser().getId()), ",");
		// 发送邮件通知下一步处理人有待办事项需要处理
		EmailService emailService = SpringUtil.getBean(EmailService.class);
		emailService.sendEmail(step6.getCurrentUsers(), project, Constants.StepCode.STEP6);
    } else {
    	Step3Worker step3Worker = step3WorkerService.get(project.getId(), ProjectProcessState.TYPE_PM,
				Integer.parseInt(Constants.StepCode.STEP11));
	    // 更新项目进程状态表信息
		updatePsd(pps,Constants.StepCode.STEP11,step3Worker.getWorkUserId());
		// 更新项目进程处理历史记录表
		savePph(project, Constants.StepCode.STEP10, Constants.StepCode.STEP11, Constants.OperateType.SUBMIT,
				step3Worker.getWorkUserId());
    	is = StringUtil.str1ToStr2(step3Worker.getWorkUserId(), Integer.toString(getUser().getId()), ",");
		// 发送邮件通知下一步处理人有待办事项需要处理
		EmailService emailService = SpringUtil.getBean(EmailService.class);
		emailService.sendEmail(step3Worker.getWorkUserId(), project, Constants.StepCode.STEP11);
	}
    if(is){
    	return "workflowNormal";
    }else{
    	return "myProjectList";
    }
    }

    /**
     * 编辑
     */
    @Action("edit")
    public String edit() {
	// 查询审定用户集合 判断当前用户时审定人还是编审人
	String wordUser = Constants.UserType.WRITE;
	step10 = step10Service.getByProjectId(project.getId());
	project = step10.getProject();
	
	if (step10.getValidateStatus() == 1 && step10.getValidateUser().equals(Integer.toString(getUser().getId()))) {
	    wordUser = Constants.UserType.VALIDATE;
	}
	List<Annex> annexList = annexService.getByProjectId(project.getId(),
			Constants.StepCode.STEP10);
	if (annexList.size() < 1) {
		List<AnnexType> annexTypeList = annexTypeService
				.getByStep(Constants.StepCode.STEP10, "yes");
		for (AnnexType annexType : annexTypeList) {
			Annex annex = new Annex();
			annex.setAnnexType(annexType);
			annexList.add(annex);
		}
	}
	put("annexList", annexList);
	List<Step10FKYJ> step10FKYJList = step10FKYJService.getByStep10Id(step10.getId());
	put("step10FKYJList", step10FKYJList);
	if (wordUser.equals(Constants.UserType.VALIDATE)) {
	    List<Step10DFYJ> step10DFYJList = step10DFYJService.getByStep10Id(step10.getId());
	    put("step10DFYJList", step10DFYJList);
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
	Page<Step10> pageBean = step10Service.getPage(getPageNo(), getPageSize(), project.getNo(), project.getName(),
		projectTypeId, Integer.parseInt(Constants.StepCode.STEP10));
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
	    step10 = step10Service.getByProjectId(project.getId());
	    List<Step10FKYJ> step10FKYJList = step10FKYJService.getByStep10Id(step10.getId());
	    put("step10FKYJList", step10FKYJList);
	    List<Step10DFYJ> step10DFYJList = step10DFYJService.getByStep10Id(step10.getId());
	    put("step10DFYJList", step10DFYJList);
	    List<Annex> annexList = annexService.getByProjectId(project.getId(),
				Constants.StepCode.STEP10);
		put("annexList", annexList);
	    project = step10.getProject();
	}
	// else{
	// step10 = step10Service.get(step10.getId());
	// List<Step10FKYJ> step10FKYJList =
	// step10FKYJService.getByStep10Id(step10.getId());
	// put("step10FKYJList",step10FKYJList);
	// List<Step10DFYJ> step10DFYJList =
	// step10DFYJService.getByStep10Id(step10.getId());
	// put("step10DFYJList",step10DFYJList);
	// project = step10.getProject();
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
	step10 = step10Service.getByProjectId(project.getId());
	List<Step10FKYJ> step10FKYJList = step10FKYJService.getByStep10Id(step10.getId());
	put("step10FKYJList", step10FKYJList);
	List<Step10DFYJ> step10DFYJList = step10DFYJService.getByStep10Id(step10.getId());
	put("step10DFYJList", step10DFYJList);
	project = step10.getProject();
	List<Annex> annexList = annexService.getByProjectId(project.getId(),
			Constants.StepCode.STEP10);
	put("annexList", annexList);
	return "print";
    }

    @Action("deleteFKYJ")
    public String deleteFKYJ() {
	step10FKYJService.delete(Integer.parseInt(id));
	return null;
    }

    @Action("deleteDFYJ")
    public String deleteDFYJ() {
	step10DFYJService.delete(Integer.parseInt(id));
	return null;
    }

    @Action("deletesz")
	public String deletesz() {
		annexService.delete(Integer.parseInt(id));
		write("1");
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
	step10 = step10Service.get(step10.getId());
	step10.setStatus(-1);
	step10Service.update(step10);

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

    public Step10 getStep10() {
	return step10;
    }

    public void setStep10(Step10 step10) {
	this.step10 = step10;
    }

    public String getContentFK() {
	return contentFK;
    }

    public void setContentFK(String contentFK) {
	this.contentFK = contentFK;
    }

    public String getReasonFK() {
	return reasonFK;
    }

    public void setReasonFK(String reasonFK) {
	this.reasonFK = reasonFK;
    }

    public String getNumZX() {
	return numZX;
    }

    public void setNumZX(String numZX) {
	this.numZX = numZX;
    }

    public String getContentZX() {
	return contentZX;
    }

    public void setContentZX(String contentZX) {
	this.contentZX = contentZX;
    }

    public String getReasonZX() {
	return reasonZX;
    }

    public void setReasonZX(String reasonZX) {
	this.reasonZX = reasonZX;
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
