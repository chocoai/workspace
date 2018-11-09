package com.smart.web.action.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.model.Contract;
import com.smart.model.ContractReview;
import com.smart.model.Dept;
import com.smart.model.EditorialType;
import com.smart.model.ProceStepDef;
import com.smart.model.Project;
import com.smart.model.ProjectContact;
import com.smart.model.ProjectInfo;
import com.smart.model.ProjectProcessHistory;
import com.smart.model.ProjectProcessState;
import com.smart.model.ProjectType;
import com.smart.model.ServiceType;
import com.smart.model.Share;
import com.smart.model.Step3Worker;
import com.smart.model.Step3WorkerTemplate;
import com.smart.model.Step5;
import com.smart.model.StepTemplete;
import com.smart.model.T_Customer;
import com.smart.model.T_hremployee;
import com.smart.model.UrgentType;
import com.smart.model.User;
import com.smart.service.ContractReviewService;
import com.smart.service.ContractService;
import com.smart.service.DeptService;
import com.smart.service.EditorialTypeService;
import com.smart.service.ProceStepDefService;
import com.smart.service.ProjectContactService;
import com.smart.service.ProjectInfoService;
import com.smart.service.ProjectProcessHistoryService;
import com.smart.service.ProjectProcessStateService;
import com.smart.service.ProjectService;
import com.smart.service.ProjectTypeService;
import com.smart.service.ServiceTypeService;
import com.smart.service.ShareService;
import com.smart.service.Step3WorkerService;
import com.smart.service.Step5Service;
import com.smart.service.StepTempleteService;
import com.smart.service.T_CustomerService;
import com.smart.service.T_hremployeeService;
import com.smart.service.UrgentTypeService;
import com.smart.service.UserService;
import com.smart.util.AppUtil;
import com.smart.util.Constants;
import com.smart.util.DateUtils;
import com.smart.util.EmailSendTool;
import com.smart.util.Page;
import com.smart.util.ReflectionUtil;
import com.smart.util.StringUtil;
import com.smart.util.TemplateUtil;
import com.smart.web.action.BaseAction;

import net.sf.json.JSONObject;

/**
 * 说明：根据包名的路径自动装配 比如本action路径，com.smart.demo.DemoAction，对应的页面文件夹content/demo
 * return "new" 则会找到content/demo/new.ftl
 * 
 * control-user 权限拦截，使用UserInterceptor拦截器
 */
@ParentPackage("control-user")
public class ProjectAction extends BaseAction {

	private static final long serialVersionUID = 5254709696992764601L;

	@Autowired
	private DeptService deptService;

	@Autowired
	private UserService userService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private ProjectTypeService projectTypeService;

	@Autowired
	private ServiceTypeService serviceTypeService;

	@Autowired
	private EditorialTypeService editorialTypeService;

	@Autowired
	private ShareService shareService;

	@Autowired
	private UrgentTypeService urgentTypeService;

	@Autowired
	private StepTempleteService stepTempleteService;

	@Autowired
	private ProjectContactService projectContactService;

	@Autowired
	private ContractService contractService;

	@Autowired
	private ContractReviewService contractReviewService;

	// @Autowired
	// private BidPlanService bidPlanService;

	/*@Autowired
	private PersonFenpeiService personFenpeiService;*/

	@Autowired
	private T_CustomerService customerService;

	@Autowired
	private Step3WorkerService step3WorkerService;

	@Autowired
	private ProjectInfoService projectInfoService;

	@Autowired
	private T_CustomerService t_CustomerService;

	@Autowired
	private Step5Service step5Service;

	@Autowired
	private ProceStepDefService proceStepDefService;

	@Autowired
	private ProjectProcessStateService processStateService;

	@Autowired
	private ProjectProcessHistoryService projectProcessHistoryService;
	
	@Autowired
	private T_hremployeeService hremployeeService;

	private Project project; // 项目

	private Integer id; // 项目id

	private Integer i;// radio的值 项目信息过来直接立项的情况

	private String senderDeptId; // 委托单位(部门id)

	private String customerId; // 委托单位(客户ID)

	private String handleDeptId; // 协作部门id

	//private Integer handleManagerId; // 协作负责人id

	private String handleManagerId; // 协作负责人id

	private String receiveDeptId; // 实施部门id

	private Integer receiveManagerId; // 实施负责人id

	private Integer projectTypeId; // 项目类型id

	private String serviceTypeId; // 咨询类型id

	private String editorialTypeId; // 业务范围id

	private String urgentTypeId; // 紧急程度id

	private String stepTempleteId; // 流程模板id

	private String processType; // 流程类型(0表示先经营管理后项目实施，1表示经营管理和项目实施可同时独立进行)

	private ProjectContact projectContact; // 联系人

	private String deptName; // 单位名称

	private String deptType; // 单位性质

	private String userName; // 联系人

	private String officeTel; // 电话

	private String tel; // 手机
	
	private String imNo;

	private String isTrack; // 是否跟踪

	private String all;

	private int ids;// 二级关联
	// 客户添加

	private T_Customer t_Customer;

	private Step3Worker step3Worker;

	private ProjectInfo projectInfo;

	private List<ProjectInfo> proin;

	private Integer projectInfoId; // 项目信息Id

	private String cusName;

	private String proname;

	private String shareid;// 共享项目id

	private String usersid;// 共享用户id

	private String name;// 查询项目名

	// 分页多选
	private String values;

	private String start;// 1 选中 0取消

	private String type;// session状态 0 创建 1 清除

	private Map<String, String> map;//

	private String roleIdList;// 人员id

	private String liname;// 人员name
	// --------------------------人员分配--------------------------
	private String[] workUserName;

	private String[] workUserId;

	private String[] typeId;

	private String[] workLevel;

	private String[] workLevelNo;
	
	/**
	 * 列表
	 */
	@Action("list")
	public String list() {
		project = project == null ? new Project() : project;
		Page<Project> pageBean = projectService.getPageByNoAndName(getPageNo(),
				getPageSize(), project.getNo(), project.getName());
		put("pageBean", pageBean);
		return "list";
	}

	/**
	 * 新建
	 */
	@Action("new")
	public String _new() {
		// 流程模板
		List<StepTemplete> stepTempleteList = stepTempleteService.getAll();
		// 咨询类型
		List<ServiceType> serviceTypeList = serviceTypeService.getAll();
		// 紧急程度
		List<UrgentType> urgentTypeList = urgentTypeService.getAll();

		put("stepTempleteList", stepTempleteList);
		put("serviceTypeList", serviceTypeList);
		put("urgentTypeList", urgentTypeList);
		
		project = new Project();
		if (projectInfoId != null) {
			projectInfo = projectInfoService.get(projectInfoId);
			if(projectService.getByinfoId(projectInfoId)!=null){
				project = projectService.getByinfoId(projectInfoId);
			}
			project.setServiceType(projectInfo.getServiceType());
			project.setReceiveType(projectInfo.getReceiveType());
			project.setName(projectInfo.getProname());
			project.setCustomer(projectInfo.getBidmen());
			i = 2;
			project.setNo(projectInfo.getProNo());
		}
		if (projectInfo != null) {
			projectInfo = projectInfoService.get(projectInfo.getId());
			if(projectService.getByinfoId(projectInfo.getId())!=null){
				project = projectService.getByinfoId(projectInfo.getId());
			}
			project.setServiceType(projectInfo.getServiceType());
			project.setReceiveType(projectInfo.getReceiveType());
			project.setName(projectInfo.getProname());
			project.setCustomer(projectInfo.getBidmen());
			i = 2;
			project.setNo(projectInfo.getProNo());
		}
		return "new";
	}

	/**
	 * 项目立项保存或提交预处理
	 */
	public void prepSaveOrToNextStep() {

		// 咨询类型
		if (StringUtils.isNotBlank(serviceTypeId)) {
			ServiceType serviceType = serviceTypeService
					.get(StringUtil.str2Integer(serviceTypeId));
			project.setServiceType(serviceType);
		}
		// 业务范围
		if (StringUtils.isNotBlank(editorialTypeId)) {
			EditorialType editorialType = editorialTypeService
					.get(StringUtil.str2Integer(editorialTypeId));
			project.setEditorialType(editorialType);
		}
		// 流程模版
		if (StringUtils.isNotBlank(stepTempleteId)) {
			StepTemplete stepTemplete = stepTempleteService
					.get(StringUtil.str2Integer(stepTempleteId));
			project.setStepTemplete(stepTemplete);
		}
		// 委托单位
		if (StringUtils.isNotBlank(customerId)) {
			T_Customer customer = customerService
					.get(StringUtil.str2Integer(customerId));
			project.setCustomer(customer);
		}
		// 紧急程度
		if (!StringUtil.isBlank(urgentTypeId)) {
			UrgentType urgentType = urgentTypeService
					.get(StringUtil.str2Integer(urgentTypeId));
			project.setUrgentType(urgentType);
		}
		// 立项日期
		if (project.getRecordDate() == null) {
			project.setRecordDate(new Date());
		}
		// 协作部门
		if (!StringUtil.isBlank(handleDeptId)) {
			project.setHandleDept(handleDeptId);
		}
		// 协作部门负责人
		if (handleManagerId != null) {
			project.setHandleManagerId(handleManagerId);
		}
		// 实施部门
		if (!StringUtil.isBlank(receiveDeptId)) {
			Dept receiveDept = deptService.get(receiveDeptId);
			project.setReceiveDept(receiveDept);
		}
		// 实施负责人
		if (receiveManagerId != null) {
			User receiveManager = userService.get(receiveManagerId);
			project.setReceiveManager(receiveManager);
		}

		// 下一环节处理人
		// String nextOperatorId = get("nextOperatorId");

		// 项目管理人员分配处理人
		String pmOperatorId = get("pmOperatorId");
		project.setPmOperator(pmOperatorId);

		// project.setStep(Integer.valueOf(Constants.StepCode.START_UP_PROJECT));//
		// 项目立项阶段
		project.setCreateUser(this.getUser());// 创建人
		project.setDept(this.getMyDept());// 创建人所属部门
		project.setPerformanceid(-1);// 绩效标示 -1标示未做绩效，1标示已做绩效

		// 项目立项后更新项目信息表中项目的状态
		if (null != projectInfoId) {
			projectInfo = projectInfoService.get(projectInfoId);
		}
		
		if (null != projectInfo && null != projectInfo.getId()) {
			projectInfo = projectInfoService.get(projectInfo.getId());
		}
		projectInfo.setStatus(0);
		projectInfoService.update(projectInfo);
		project.setProjectInfo(projectInfo);
		if (project.getId() == null) {
			projectService.save(project);
		} else {
			Project oldProject = projectService.get(project.getId());
			// 项目立项时更新项目信息表中的状态
			updateProjectInfoState();
			// 属性拷贝，把更新内容拷贝到数据库查出的project里，最后的那个参数是不需要拷贝的属性名称
			ReflectionUtil.bean2Bean(oldProject, project,
					"id, ctime,step, status,performanceid,projectInfo");
			projectService.update(oldProject);
		}

		// 往来单位信息
		saveProjectContact();
	}

	/**
	 * 项目立项时更新项目信息表中的状态(0表示已立项，1表示未立项)
	 */
	public void updateProjectInfoState() {
		Project oldProject = projectService.get(project.getId());
		if ("1".equalsIgnoreCase(this.get("projectSource"))
				&& null != projectInfo) {
			if (null != oldProject.getProjectInfo()
					&& projectInfo != oldProject.getProjectInfo()) {
				ProjectInfo proInfo = oldProject.getProjectInfo();
				proInfo.setStatus(1);
				projectInfoService.update(proInfo);
			}
			oldProject.setProjectInfo(projectInfo);
		} else if ("0".equalsIgnoreCase(this.get("projectSource"))
				&& null != oldProject.getProjectInfo()) {
			ProjectInfo proInfo = oldProject.getProjectInfo();
			proInfo.setStatus(1);
			projectInfoService.update(proInfo);
			oldProject.setProjectInfo(null);
		}
	}

	/**
	 * 项目立项时保存往来单位信息
	 */
	public void saveProjectContact() {
		if (!StringUtil.isBlank(deptName)) {
			String[] arrdeptName = deptName.split(",");
			String[] arrdeptType = deptType.split(",");
			String[] arruserName = userName.split(",");
			String[] arrofficeTel = officeTel.split(",");
			String[] arrtel = tel.split(",");
			String[] arrimNo = imNo.split(",");
			String[] arrisTrack = isTrack.split(",");
			for (int i = 0; i < arrdeptName.length; i++) {
				if (StringUtil.isBlank(arrdeptName[i].trim())
						&& StringUtil.isBlank(arruserName[i].trim()))
					continue;
				// 项目成员
				ProjectContact pc = new ProjectContact();
				pc.setDeptName(arrdeptName[i]);
				pc.setDeptType(arrdeptType[i]);
				pc.setUserName(arruserName[i]);
				pc.setOfficeTel(arrofficeTel[i]);
				pc.setTel(arrtel[i]);
				pc.setImNo(arrimNo[i]);
				pc.setIsTrack(arrisTrack[i]);
				pc.setProject(project);
				projectContactService.save(pc);
			}
		}
	}

	/**
	 * 保存
	 */
	@Action(value = "save", results = {
			@Result(name = "list", location = "list", type = "redirectAction") })
	public String save() {

		prepSaveOrToNextStep();
		ProceStepDef currentStep = proceStepDefService
				.getStepByStepCode(Constants.StepCode.START_UP_PROJECT);
		// 更新项目进程处理历史记录表
		ProjectProcessHistory pph = new ProjectProcessHistory();
		pph.setProject(project);
		pph.setProjectInfo(project.getProjectInfo());
		pph.setOperateStep(currentStep);
		pph.setOperateUser(getUser());
		pph.setOperateTime(DateUtils.getCurrentTime());
		pph.setOperateType(Constants.OperateType.SAVE);
		pph.setNextStep(currentStep);
		pph.setNextHandlers(getUser().getId().toString());
		projectProcessHistoryService.save(pph);

		return "list";
	}

	@Action(value = "toNextStep")
	public String toNextStep() {
		try {
			prepSaveOrToNextStep();

			project = projectService.get(project.getId());
			project.setStatus(2); // 状态2代表启动 ,0代表删除 1代表未启动 2 代表已启动

			// 下一环节处理人
			String nextOperatorId = get("nextOperatorId");
			// 项目管理人员分配处理人
			String pmOperatorId = get("pmOperatorId");

			EmailSendTool sendEmail = new EmailSendTool();
			// 项目承接类型为招投标，项目流向：项目立项 ---> 投标管理
			// --->合同管理--->进入项目流程(step1~step15)
			/*if (project.getReceiveType() == Constants.ReceiveType.BID) {

				PersonFenpei personFenpei = new PersonFenpei();
				personFenpei.setProject(project);
				personFenpeiService.save(personFenpei);

				ProceStepDef nextStep = proceStepDefService
						.getStepByStepCode(Constants.StepCode.BID_DISPATCH);
				// 更新项目进程状态表信息
				ProjectProcessState processState = new ProjectProcessState();
				processState.setProject(project);
				processState.setCurrentStep(nextStep);
				processState.setCurrentState(ProjectProcessState.IN_PROGRESS);
				processState.setCurrentUsers(nextOperatorId);
				processState.setType(ProjectProcessState.TYPE_OM);
				processState.setLastUpdateTime(DateUtils.getCurrentTime());

				ProjectProcessState pps = processStateService
						.getOneByProjectIdAndStepCode(project.getId(),
								Constants.StepCode.START_UP_PROJECT);
				if (pps == null) {
					processStateService.save(processState);
				} else {
					pps.setCurrentStep(nextStep);
					pps.setCurrentUsers(nextOperatorId);
					pps.setType(ProjectProcessState.TYPE_OM);
					pps.setLastUpdateTime(DateUtils.getCurrentTime());
					processStateService.update(pps);
				}

				// 更新项目进程处理历史记录表
				ProjectProcessHistory pph = new ProjectProcessHistory();
				pph.setProject(project);
				pph.setOperateStep(proceStepDefService.getStepByStepCode(Constants.StepCode.START_UP_PROJECT));
				pph.setOperateUser(getUser());
				pph.setOperateTime(DateUtils.getCurrentTime());
				pph.setOperateType(Constants.OperateType.SUBMIT);
				pph.setNextStep(nextStep);
				pph.setNextHandlers(nextOperatorId);
				projectProcessHistoryService.save(pph);
				
				// 发送邮件通知下一步处理人有待办事项需要处理
				// 查询下一步处理人的邮箱地址
				StringBuilder emailAddress = new StringBuilder(); 
				if (StringUtils.isNotBlank(nextOperatorId)) {
					String[] userIds = nextOperatorId.split(",");
					for (int i = 0; i < userIds.length; i++) {
						if (StringUtils.isNumeric(userIds[i])) {
							T_hremployee hrEmployee = hremployeeService.getSysCode(Integer.valueOf(userIds[i]));
							if (hrEmployee != null) {
								emailAddress.append(hrEmployee.getEmail()).append(",");
							}
						}
					}
					String subject = sendEmail.getSubject();
					String emailContent = sendEmail.buildContent(project.getNo(), project.getName(), nextStep.getStepName());
					sendEmail.send(emailAddress.toString(), subject, emailContent);
				}
			}*/

			// 项目承接类型为直接委托，项目流向: 项目立项 --> 合同管理-->进入项目流程(step1~step15)
			//if (project.getReceiveType() == Constants.ReceiveType.ENTRUST) {
				Contract contract = new Contract(); // 合同
				ContractReview review = new ContractReview(); // 合同评审
				
				//自动获取合同编号
				contract.setNo(project.getNo());
				
				// 合同评审阶段
				contract.setProject(project);
				review.setContract(contract);
				contractService.save(contract);
				contractReviewService.save(review);

				ProceStepDef nextStep = proceStepDefService.getStepByStepCode(Constants.StepCode.CONTRACT_REVIEW);
				// 更新项目进程状态表信息
				ProjectProcessState processState = new ProjectProcessState();
				processState.setProject(project);
				processState.setProjectInfo(projectInfo);
				processState.setCurrentStep(nextStep);
				processState.setCurrentState(ProjectProcessState.IN_PROGRESS);
				processState.setCurrentUsers(nextOperatorId);
				processState.setType(ProjectProcessState.TYPE_OM);
				processState.setLastUpdateTime(DateUtils.getCurrentTime());
				ProjectProcessState pps = processStateService.getOneByProjectInfoId(projectInfo.getId());
				if (pps == null) {
					processStateService.save(processState);
				} else {
					pps.setProject(project);
					pps.setProjectInfo(projectInfo);
					pps.setCurrentStep(nextStep);
					pps.setCurrentState(ProjectProcessState.IN_PROGRESS);
					pps.setCurrentUsers(nextOperatorId);
					pps.setType(ProjectProcessState.TYPE_OM);
					pps.setLastUpdateTime(DateUtils.getCurrentTime());
					processStateService.update(pps);
				}

				// 更新项目进程处理历史记录表
				ProjectProcessHistory pph = new ProjectProcessHistory();
				pph.setProject(project);
				pph.setProjectInfo(project.getProjectInfo());
				pph.setOperateStep(proceStepDefService.getStepByStepCode(
						Constants.StepCode.START_UP_PROJECT));
				pph.setOperateUser(getUser());
				pph.setOperateTime(DateUtils.getCurrentTime());
				pph.setOperateType(Constants.OperateType.SUBMIT);
				pph.setNextStep(nextStep);
				pph.setNextHandlers(nextOperatorId);
				projectProcessHistoryService.save(pph);

				// 发送邮件通知下一步处理人有待办事项需要处理
				// 查询下一步处理人的邮箱地址
				StringBuilder emailAddress = new StringBuilder(); 
				if (StringUtils.isNotBlank(nextOperatorId)) {
					String[] userIds = nextOperatorId.split(",");
					for (int i = 0; i < userIds.length; i++) {
						if (StringUtils.isNumeric(userIds[i])) {
							T_hremployee hrEmployee = hremployeeService.getSysCode(Integer.valueOf(userIds[i]));
							if (hrEmployee != null) {
								emailAddress.append(hrEmployee.getEmail()).append(",");
							}
						}
					}
					String subject = sendEmail.getSubject();
					String emailContent = sendEmail.buildContent(project.getNo(), project.getName(), nextStep.getStepName());
					sendEmail.send(emailAddress.toString(), subject, emailContent);
				}
			//}
			
			if (project.getProcessType().equalsIgnoreCase(Constants.ProcessType.INDEPENDENTLY)) {
				ProceStepDef nextStep1 = proceStepDefService.getStepByStepCode(Constants.StepCode.PM_DISPATCH);
				// 更新项目进程状态表信息
				ProjectProcessState projectProcessState = new ProjectProcessState();
				projectProcessState.setProject(project);
				projectProcessState.setProjectInfo(project.getProjectInfo());
				projectProcessState.setCurrentStep(nextStep1);
				projectProcessState.setCurrentState(ProjectProcessState.IN_PROGRESS);
				projectProcessState.setCurrentUsers(pmOperatorId);
				projectProcessState.setType(ProjectProcessState.TYPE_PM);
				projectProcessState.setLastUpdateTime(DateUtils.getCurrentTime());
				processStateService.save(projectProcessState);
				// 更新项目进程处理历史记录表
				ProjectProcessHistory processHistory = new ProjectProcessHistory();
				processHistory.setProject(project);
				processHistory.setProjectInfo(project.getProjectInfo());
				processHistory.setOperateStep(proceStepDefService.getStepByStepCode(Constants.StepCode.START_UP_PROJECT));
				processHistory.setOperateUser(getUser());
				processHistory.setOperateTime(DateUtils.getCurrentTime());
				processHistory.setOperateType(Constants.OperateType.SUBMIT);
				processHistory.setNextStep(nextStep1);
				processHistory.setNextHandlers(pmOperatorId);
				projectProcessHistoryService.save(processHistory);
				// 直接进入项目流程
				projectService.saveToStep(project);
				
				// 发送邮件通知下一步处理人有待办事项需要处理
				// 查询下一步处理人的邮箱地址
				StringBuilder emailAddress1 = new StringBuilder(); 
				if (StringUtils.isNotBlank(nextOperatorId)) {
					String[] userIds = nextOperatorId.split(",");
					for (int i = 0; i < userIds.length; i++) {
						if (StringUtils.isNumeric(userIds[i])) {
							T_hremployee hrEmployee = hremployeeService.getSysCode(Integer.valueOf(userIds[i]));
							if (hrEmployee != null) {
								emailAddress1.append(hrEmployee.getEmail()).append(",");
							}
						}
					}
					String subject = sendEmail.getSubject();
					String emailContent = sendEmail.buildContent(project.getNo(), project.getName(), nextStep1.getStepName());
					sendEmail.send(emailAddress1.toString(), subject, emailContent);
				}
			}
			
			Page<Project> pageBean = projectService.getPageByNoAndName(getPageNo(), getPageSize(), null, null);
			put("pageBean", pageBean);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	@Action("listShare")
	public String listShare() {// 共享界面
		getSession().remove("showPro");
		getSession().put("showShare", 1);
		int userid = this.getUser().getId();
		project = project == null ? new Project() : project;
		Page<Share> pageBean = shareService.getAll(getPageNo(), 10, name,
				userid, project.getNo());
		put("pageBean", pageBean);
		return "listShare";
	}

	@Action("deleteShare")
	public String deleteShare() {// 删除共享
		Share s = shareService.get(id);
		s.setStatus(-1);
		shareService.update(s);
		write("1");
		return null;
	}

	@Action("saveShare")
	public String saveShare() {// 保存共享
		if (usersid.equals("-")) {
			String[] shareids = shareid.split(",");// 项目id 多个
			List<User> us = userService.getAll();
			for (String id : shareids) {
				if (StringUtil.isBlank(id)) {
					continue;
				} else {
					for (User id1 : us) {
						Share s = new Share();
						if (!shareService.getUnique(Integer.parseInt(id),
								id1.getId())) {// 有重复记录
							s.setProject(
									projectService.get(Integer.parseInt(id)));
							s.setUser(id1);
							s.setName(projectService.get(Integer.parseInt(id))
									.getName());
							s.setUser1(this.getUser());
							shareService.save(s);
						}
					}
				}
			}
		} else {
			String[] shareids = shareid.split(",");// 项目id 多个
			String[] usersids = usersid.split(",");// 用户id 多个
			for (String id : shareids) {
				if (StringUtil.isBlank(id)) {
					continue;
				} else {
					for (String id1 : usersids) {
						if (StringUtil.isBlank(id1)) {
							continue;
						}
						Share s = new Share();
						if (!shareService.getUnique(Integer.parseInt(id),
								Integer.parseInt(id1))) {// 有重复记录
							s.setProject(
									projectService.get(Integer.parseInt(id)));
							s.setUser(userService.get(Integer.parseInt(id1)));
							s.setName(projectService.get(Integer.parseInt(id))
									.getName());
							s.setUser1(this.getUser());
							shareService.save(s);
						}
					}
				}
			}
		}
		return null;
	}

	@Action("newcustomer")
	public String newcustomer() {// 新增客户
		return "newcustomer";
	}

	@Action("savecustomer")
	public String savecustomer() {// 保存新增客户
		try {
			t_Customer.setStatus(1);
			t_Customer.setRtime(new Date());
			t_Customer.setCtime(new Date());
			t_Customer.setUser(this.getUser());
			t_CustomerService.save(t_Customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Action("selectCustomer")
	public String selectCustomer() {
		Page<T_Customer> pageBean = customerService.getAll1(getPageNo(), 10,
				cusName); // 委托单位(客户信息)
		put("pageBean", pageBean);
		return "selectcustomer";
	}

	@Action("selectProject")
	public String selectProject() {
		Page<ProjectInfo> pageBean = projectInfoService.getAll(getPageNo(),
				pageSize, proname);
		put("pageBean", pageBean);
		return "selectProject";
	}

	@Action("select")
	public String select() {
		List<EditorialType> editorialType = editorialTypeService.getPid(ids);
		List<Integer> is = new ArrayList<Integer>();
		List<String> ns = new ArrayList<String>();
		JSONObject jsonObject = new JSONObject();
		for (EditorialType s : editorialType) {
			ns.add(s.getName());
			is.add(s.getId());
		}
		jsonObject.put("id", is);
		jsonObject.put("name", ns);
		writeJson(jsonObject.toString());
		return null;
	}

	@Action(value = "viewProcessHistory")
	public String viewProcessHistory() {
		if(projectInfo != null && projectInfo.getId() !=null){
			List<ProjectProcessState> ppsList = processStateService
					.getListByProjectInfoId(projectInfo.getId());
			put("ppsList", ppsList);
			// 查询该项目的流程处理历史记录
			List<ProjectProcessHistory> pphList = projectProcessHistoryService
					.getListByProjectInfoId(projectInfo.getId());
			put("pphList", pphList);
		}
		return "processHistory";
	}

	/**
	 * 编辑
	 */
	@Action("edit")
	public String edit() {
		project = projectService.get(project.getId());
		// 基本客户信息
		List<ProjectContact> projectContactList = projectContactService
				.getByProjectId(project.getId());
		put("projectContactList", projectContactList);
		// 委托单位(客户信息)
		List<T_Customer> customerList = customerService.getAll();
		put("customerList", customerList);
		// 咨询类型
		List<ServiceType> serviceTypeList = serviceTypeService.getAll();
		put("serviceTypeList", serviceTypeList);
		// 业务范围
		List<EditorialType> editorialTypeList = editorialTypeService
				.getPid(project.getServiceType().getId());
		put("editorialTypeList", editorialTypeList);
		// 紧急程度
		List<UrgentType> urgentTypeList = urgentTypeService.getAll();
		put("urgentTypeList", urgentTypeList);
		// 协作部门
		String HandleDeptIds = project.getHandleDept();
		String deptIds = HandleDeptIds;
		String deptNames = "";
		if (!StringUtil.isBlank(HandleDeptIds)) {
			if (HandleDeptIds.indexOf(",") != -1) {
				String[] ids = HandleDeptIds.split(",");
				for (int i = 0; i < ids.length; i++) {
					Dept dept = deptService.get(ids[i]);
					ids[i] = dept.getName();
					deptNames = StringUtil.array2Str(ids, ",");
				}
			} else {
				Dept dept = deptService.get(HandleDeptIds);
				deptNames = dept.getName();
			}
		}
		put("deptIds", deptIds);
		put("deptNames", deptNames);
		// 协作负责人
		String handleManagerIds = project.getHandleManagerId();
		String handleManagerNames = "";
		if (!StringUtil.isBlank(handleManagerIds)) {
			if (handleManagerIds.indexOf(",") != -1) {
				String[] ids = handleManagerIds.split(",");
				for (int i = 0; i < ids.length; i++) {
					User user = userService.get(Integer.valueOf(ids[i]));
					ids[i] = user.getName();
					handleManagerNames = StringUtil.array2Str(ids, ",");
				}
			} else {
				User user = userService.get(Integer.valueOf(handleManagerIds));
				handleManagerNames = user.getName();
			}
		}
		put("handleManagerNames", handleManagerNames);
		

		// 下一环节处理人
		/*if (StringUtils.isNotBlank(project.getNextOperator())) {
			String[] userIds = project.getNextOperator().split(",");
			StringBuffer userNames = new StringBuffer();
			for (int i = 0; i < userIds.length; i++) {
				if (StringUtils.isBlank(userIds[i]))
					continue;
				User user = userService.get(Integer.parseInt(userIds[i]));
				userNames.append(user.getName()).append(",");
			}
			String nextOperatorName = StringUtils
					.removeEnd(userNames.toString(), ",");
			put("nextOperatorName", nextOperatorName);
		}*/

		// 下一环节处理人
		if (StringUtils.isNotBlank(project.getPmOperator())) {
			String[] userIds = project.getPmOperator().split(",");
			StringBuffer userNames = new StringBuffer();
			for (int i = 0; i < userIds.length; i++) {
				if (StringUtils.isBlank(userIds[i]))
					continue;
				User user = userService.get(Integer.parseInt(userIds[i]));
				userNames.append(user.getName()).append(",");
			}
			String pmOperatorName = StringUtils.removeEnd(userNames.toString(),
					",");
			put("pmOperatorName", pmOperatorName);
		}
		return "new";
	}

	@Action("myProjectList")
	public String myProjectList() {
		getSession().remove("showShare");
		getSession().put("showPro", 0);
		Integer userid = this.getUseId();
		put("userid", String.valueOf(userid));
		project = project == null ? new Project() : project;
		put("value", all);
		if (StringUtil.isBlank(all)) { // 待办项目
			Page<ProjectProcessState> pageBean = processStateService
					.getPageState(getPageNo(), getPageSize(), project.getNo(),
							project.getName(), userid,
							ProjectProcessState.TYPE_PM);
			put("pageBean", pageBean);
			return "myprojectlist";
		} else if ("done".equals(all)) { // 已处理项目
			Page<Project> pageBean = projectService.getPageHistory(getPageNo(),
					getPageSize(), project.getNo(), project.getName(), userid);
			put("pageBean", pageBean);
			return "myprojectlistdone";
		} else if ("true".equals(all)) { // 项目管理所有项目
			Page<ProjectProcessState> pageBean = processStateService
					.getPageState(getPageNo(), getPageSize(), project.getNo(),
							project.getName(), null,
							ProjectProcessState.TYPE_PM);
			put("pageBean", pageBean);
			return "myprojectlistall";
		} else if ("market".equals(all)) { // 经营管理所有项目
			Page<Project> pageBean = projectService.getProjectPageAll(
					getPageNo(), getPageSize(), project.getNo(),
					project.getName(), userid, 1);
			put("pageBean", pageBean);
			return "projectlistall";
		}
		return null;
	}
	
	@Action("getAllProject")
	public String getAllProject() {
		project = project == null ? new Project() : project;
		int userId = this.getUseId();
		Page<Project> pageBean = projectService.getAllProject(pageNo, pageSize,
				project.getNo(), project.getName(), userId);
		put("pageBean", pageBean);
		return "allProject";
	}

	@Action("workflow")
	public String workflow() {
		if (id != null) {
			project = projectService.get(id);
		} else {
			project = projectService.get(project.getId());
		}
		String username = this.getUser().getUsername();
		put("userid", this.getUser().getId());
		put("project", project);
		List<ProceStepDef> psdList = proceStepDefService
				.getProjectByStep(ProjectProcessState.TYPE_PM, true);
		ProjectProcessState pps = processStateService
				.getOneByProjectIdAndBusinesstype(project.getId(),
						Integer.parseInt(ProjectProcessState.TYPE_PM));
		if (Integer.parseInt(pps.getCurrentStep().getStepCode()) >= Integer
				.parseInt(Constants.StepCode.STEP5)) {
			Step5 step5 = step5Service.getByProjectId(project.getId());
			boolean isHandle = Arrays.asList(step5.getCurrentUsers().split(","))
					.contains(getUseId().toString());
			put("isHandle", isHandle);
		}
		put("psdList", psdList);
		put("pps", pps);
		if ((AppUtil.SUPERADMIN).equals(username)) {// 特殊账户
			return "workflowAdmin";
		} else {
			return "workflowNormal";
		}
	}

	@Action("workflowShow")
	public String workflowShow() {
		project = projectService.get(project.getId());
		return "workflowShow";
	}

	@Action("checkPerson")
	public String checkPerson() {
		project = projectService.get(project.getId());
		put("project", project);
		List<ProceStepDef> psdList = proceStepDefService
				.getProjectByStep(ProjectProcessState.TYPE_PM, false);
		psdList.get(0).setStepName("项目负责人");
		List<Step3Worker> swList = step3WorkerService.getByProjectId(
				project.getId(), ProjectProcessState.TYPE_PM, null);
		List<Step3WorkerTemplate> swtList = TemplateUtil.Step3WorkerTemplateList(psdList, swList);
		put("swtList", swtList);
		return "checkPerson";
	}

	@Action("showcheckPerson")
	public String showcheckPerson() {
		project = projectService.get(project.getId());
		put("project", project);
		List<ProceStepDef> psdList = proceStepDefService
				.getProjectByStep(ProjectProcessState.TYPE_PM, false);
		psdList.get(0).setStepName("项目负责人");
		List<Step3Worker> swList = step3WorkerService.getByProjectId(
				project.getId(), ProjectProcessState.TYPE_PM, null);
		List<Step3WorkerTemplate> swtList = TemplateUtil.Step3WorkerTemplateList(psdList, swList);
		put("swtList", swtList);
		return "showcheckPerson";
	}

	@Action("printCheckPerson")
	public String printCheckPerson() {
		project = projectService.get(project.getId());
		put("project", project);
		List<ProceStepDef> psdList = proceStepDefService
				.getProjectByStep(ProjectProcessState.TYPE_PM, false);
		psdList.get(0).setStepName("项目负责人");
		List<Step3Worker> swList = step3WorkerService.getByProjectId(
				project.getId(), ProjectProcessState.TYPE_PM, null);
		List<Step3WorkerTemplate> swtList = TemplateUtil
				.Step3WorkerTemplateList(psdList, swList);
		put("swtList", swtList);
		return "printCheckPerson";
	}

	@Action(value = "savePerson", results = {
			@Result(name = "myProjectList", location = "../project/myProjectList", type = "redirectAction", params = {
					"all", "true" }) })
	public String savePerson() {
		project = projectService.get(project.getId());
		step3WorkerService.deleteByProjectId(project.getId(),
				ProjectProcessState.TYPE_PM);
		for (int i = 0; i < typeId.length; i++) {
			Step3Worker sw = new Step3Worker();
			sw.setProject(project);
			sw.setType(Integer.parseInt(typeId[i]));
			sw.setWorkType(ProjectProcessState.TYPE_PM);
			sw.setWorkUserId(workUserId[i]);
			sw.setWorkUserName(workUserName[i]);
			sw.setWorkLevel(workLevel[i]);
			sw.setWorkLevelNo(workLevelNo[i]);
			sw.setDept(getMyDept());
			sw.setUser(getUser());
			Step3Worker sWork = step3WorkerService.get(project.getId(),
					ProjectProcessState.TYPE_PM, Integer.parseInt(typeId[i]));
			if (sWork != null) {
				ReflectionUtil.bean2Bean(sWork, sw,
						"id,project,workType,dept,user,ctime,status");
				step3WorkerService.update(sWork);
			} else {
				step3WorkerService.save(sw);
			}

		}
		ProjectProcessState pps = processStateService
				.getOneByProjectIdAndBusinesstype(project.getId(),
						Integer.parseInt(ProjectProcessState.TYPE_PM));
		// 更新项目进程状态表信息
		updatePsd(pps, Constants.StepCode.STEP1, workUserId[1]);
		// 更新项目进程处理历史记录表
		savePph(project, Constants.StepCode.PM_DISPATCH,
				Constants.StepCode.STEP1, Constants.OperateType.SAVE,workUserId[1]);
		
		// 发送邮件通知下一步处理人有待办事项需要处理
		// 查询下一步处理人的邮箱地址
		StringBuilder emailAddress = new StringBuilder();
		if (StringUtils.isNotBlank(workUserId[1])) {
			String[] userIds = workUserId[1].split(",");
			for (int i = 0; i < userIds.length; i++) {
				if (StringUtils.isNumeric(userIds[i])) {
					T_hremployee hrEmployee = hremployeeService
							.getSysCode(Integer.valueOf(userIds[i]));
					if (hrEmployee != null) {
						emailAddress.append(hrEmployee.getEmail()).append(",");
					}
				}
			}
			ProceStepDef nextStep = proceStepDefService.getStepByStepCode(
					Constants.StepCode.STEP1);
			EmailSendTool sendEmail = new EmailSendTool();
			String subject = sendEmail.getSubject();
			String emailContent = sendEmail.buildContent(project.getNo(),
					project.getName(), nextStep.getStepName());
			sendEmail.send(emailAddress.toString(), subject, emailContent);
		}
		return "myProjectList";
	}

	@Action("saveSwitch")
	public String saveSwitch() {
	    liname = "";
		if (roleIdList != null) {
			String[] ids = roleIdList.split(",");
			for (String id : ids) {
				if (StringUtil.isBlank(id)) {
					continue;
				}
				User s = userService.get(Integer.parseInt(id));
				liname = s.getName() + "," + liname;

			}
			write(roleIdList + "*" + liname);
			getSession().remove("user_session");
		}
		return null;
	}

	@Action("gantt")
	public String gantt() {
		project = projectService.get(project.getId());
		String source = projectService.getGanttSource(project);
		put("source", source);
		return "ganttView";
	}

	@Action("listForSelect")
	public String listForSelect() {
		// String mydeptid = this.getMyDept().getId();
		// 项目分类
		List<ProjectType> projectTypeList = projectTypeService.getList();
		put("projectTypeList", projectTypeList);
		project = project == null ? new Project() : project;
		Page<Project> pageBean = projectService.getPage(getPageNo(),
				getPageSize(), project.getNo(), project.getName(),
				projectTypeId);
		for (int i = 0; i < pageBean.getList().size(); i++) {
			List<Contract> list = contractService
					.getList(pageBean.getList().get(i).getId());
			String Contacton = list.size() > 0 ? list.get(0).getNo() : "";
			pageBean.getList().get(i).setContacton(Contacton);
		}
		put("pageBean", pageBean);
		return "listforselect";
	}

	/**
	 * 显示
	 * 
	 * @return
	 */
	@Action("show")
	public String show() {
		project = projectService.get(project.getId());
		// 往来单位信息
		List<ProjectContact> projectContactList = projectContactService
				.getByProjectId(project.getId());
		put("projectContactList", projectContactList);
		// 协作部门
		String HandleDeptIds = project.getHandleDept();
		String deptNames = "";
		if (!StringUtil.isBlank(HandleDeptIds)) {
			if (HandleDeptIds.indexOf(",") != -1) {
				String[] ids = HandleDeptIds.split(",");
				for (int i = 0; i < ids.length; i++) {
					Dept dept = deptService.get(ids[i]);
					ids[i] = dept.getName();
					deptNames = StringUtil.array2Str(ids, ",");
				}
			} else {
				Dept dept = deptService.get(HandleDeptIds);
				deptNames = dept.getName();
			}
		}
		put("deptNames", deptNames);
		//协作负责人
		String handleManagerIds = project.getHandleManagerId();
		String handleManagerNames = "";
		if (!StringUtil.isBlank(handleManagerIds)) {
			if (handleManagerIds.indexOf(",") != -1) {
				String[] ids = handleManagerIds.split(",");
				for (int i = 0; i < ids.length; i++) {
					User user = userService.get(Integer.valueOf(ids[i]));
					ids[i] = user.getName();
					handleManagerNames = StringUtil.array2Str(ids, ",");
				}
			} else {
				User user = userService.get(Integer.valueOf(handleManagerIds));
				handleManagerNames = user.getName();
			}
		}
		put("handleManagerNames", handleManagerNames);
		
		
		return "show";
	}

	@Action("showmyproject")
	public String showmyproject() {
		List<ProjectContact> projectContactList = projectContactService
				.getByProjectId(project.getId());
		put("projectContactList", projectContactList); // 投标策划 成员表
		project = projectService.get(project.getId());
		return "showmyproject";
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@Action("delete")
	public String delete() {
		project = projectService.get(project.getId());
		project.setStatus(0);
		projectService.update(project);

		write("1"); // ajax请求用write返回数据
		return null;
	}

	@Action("delProjectContact")
	public String delProjectContact() {
		String result = "";
		try {
			String projectContactId = this.get("projectContactId");
			if (StringUtils.isNotBlank(projectContactId)) {
				ProjectContact projectContact = projectContactService
						.get(Integer.parseInt(projectContactId));
				projectContact.setStatus(0);
				projectContactService.update(projectContact);
			}
			result = "true";
		} catch (Exception e) {
			result = e.toString();
			e.printStackTrace();
		} finally {
			write(result);
		}
		return null;
	}

	@Action("checkNO")
	public String checkNO() {
		String flag = projectService.checkNO(project.getNo());

		write(flag); // ajax请求用write返回数据
		return null;
	}

	@SuppressWarnings("unchecked")
	@Action("selectProject1")
	public String selectProject1() {
		if (type.equals("0")) {// 将id保存到session
			if ((Map<String, String>) getSession()
					.get("project_session") == null) {
				map = new HashMap<String, String>();
			} else {
				map = (Map<String, String>) getSession().get("project_session");
			}
			if (start != null && values != null) {
				if (start.equals("1") && map.get(values) == null) {
					map.put(values, values);
				}
				if (map.get(values) != null && start.equals("0")) {
					map.remove(values);
				}
			}
			getSession().put("project_session", map);
			String ids = "";
			if (map != null) {
				Iterator<Entry<String, String>> it = map.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry<?, ?> entry = (Map.Entry<?, ?>) it.next();
					String value = (String) entry.getKey();
					// String name = (String)entry.getValue();
					if (ids.equals("")) {
						ids = value;
					} else {
						ids = ids + "," + value;
					}
				}
			}
			write(ids);
		} else if (type.equals("1")) {// 清除session
			getSession().remove("project_session");
			write("1");
		}
		return null;
	}

	/**
	 * 打印
	 * 
	 * @return
	 */
	@Action("print")
	public String print() {
		List<ProjectContact> projectContactList = projectContactService
				.getByProjectId(project.getId());
		put("projectContactList", projectContactList);
		project = projectService.get(project.getId());
		// 协作部门
		String HandleDeptIds = project.getHandleDept();
		String deptNames = "";
		if (!StringUtil.isBlank(HandleDeptIds)) {
			if (HandleDeptIds.indexOf(",") != -1) {
				String[] ids = HandleDeptIds.split(",");
				for (int i = 0; i < ids.length; i++) {
					Dept dept = deptService.get(ids[i]);
					ids[i] = dept.getName();
					deptNames = StringUtil.array2Str(ids, ",");
				}
			} else {
				Dept dept = deptService.get(HandleDeptIds);
				deptNames = dept.getName();
			}
		}
		put("deptNames", deptNames);
		//协作负责人
		String handleManagerIds = project.getHandleManagerId();
		String handleManagerNames = "";
		if (!StringUtil.isBlank(handleManagerIds)) {
			if (handleManagerIds.indexOf(",") != -1) {
				String[] ids = handleManagerIds.split(",");
				for (int i = 0; i < ids.length; i++) {
					User user = userService.get(Integer.valueOf(ids[i]));
					ids[i] = user.getName();
					handleManagerNames = StringUtil.array2Str(ids, ",");
				}
			} else {
				User user = userService.get(Integer.valueOf(handleManagerIds));
				handleManagerNames = user.getName();
			}
		}
		put("handleManagerNames", handleManagerNames);
		return "print";
	}

	/**
	 * 当前用户的处理详情
	 * 
	 * @return
	 */
	@Action("processHistory")
	public String processHistory() {

		return "";
	}

	// getter and setter
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSenderDeptId() {
		return senderDeptId;
	}

	public void setSenderDeptId(String senderDeptId) {
		this.senderDeptId = senderDeptId;
	}

	public Integer getProjectTypeId() {
		return projectTypeId;
	}

	public void setProjectTypeId(Integer projectTypeId) {
		this.projectTypeId = projectTypeId;
	}

	public String getServiceTypeId() {
		return serviceTypeId;
	}

	public void setServiceTypeId(String serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}

	public String getEditorialTypeId() {
		return editorialTypeId;
	}

	public void setEditorialTypeId(String editorialTypeId) {
		this.editorialTypeId = editorialTypeId;
	}

	public String getUrgentTypeId() {
		return urgentTypeId;
	}

	public void setUrgentTypeId(String urgentTypeId) {
		this.urgentTypeId = urgentTypeId;
	}

	public ProjectContact getProjectContact() {
		return projectContact;
	}

	public void setProjectContact(ProjectContact projectContact) {
		this.projectContact = projectContact;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptType() {
		return deptType;
	}

	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getIsTrack() {
		return isTrack;
	}

	public void setIsTrack(String isTrack) {
		this.isTrack = isTrack;
	}

	public String getStepTempleteId() {
		return stepTempleteId;
	}

	public void setStepTempleteId(String stepTempleteId) {
		this.stepTempleteId = stepTempleteId;
	}

	public String getHandleDeptId() {
		return handleDeptId;
	}

	public void setHandleDeptId(String handleDeptId) {
		this.handleDeptId = handleDeptId;
	}

	public String getAll() {
		return all;
	}

	public void setAll(String all) {
		this.all = all;
	}

	public String getReceiveDeptId() {
		return receiveDeptId;
	}

	public void setReceiveDeptId(String receiveDeptId) {
		this.receiveDeptId = receiveDeptId;
	}

	public int getIds() {
		return ids;
	}

	public void setIds(int ids) {
		this.ids = ids;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Step3Worker getStep3Worker() {
		return step3Worker;
	}

	public void setStep3Worker(Step3Worker step3Worker) {
		this.step3Worker = step3Worker;
	}

	public String getHandleManagerId() {
		return handleManagerId;
	}

	public void setHandleManagerId(String handleManagerId) {
		this.handleManagerId = handleManagerId;
	}

	public Integer getReceiveManagerId() {
		return receiveManagerId;
	}

	public void setReceiveManagerId(Integer receiveManagerId) {
		this.receiveManagerId = receiveManagerId;
	}

	public List<ProjectInfo> getProin() {
		return proin;
	}

	public void setProin(List<ProjectInfo> proin) {
		this.proin = proin;
	}

	public Integer getProjectInfoId() {
		return projectInfoId;
	}

	public void setProjectInfoId(Integer projectInfoId) {
		this.projectInfoId = projectInfoId;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getProname() {
		return proname;
	}

	public void setProname(String proname) {
		this.proname = proname;
	}

	public T_Customer getT_Customer() {
		return t_Customer;
	}

	public void setT_Customer(T_Customer tCustomer) {
		t_Customer = tCustomer;
	}

	public Integer getI() {
		return i;
	}

	public void setI(Integer i) {
		this.i = i;
	}

	public String getShareid() {
		return shareid;
	}

	public void setShareid(String shareid) {
		this.shareid = shareid;
	}

	public String getUsersid() {
		return usersid;
	}

	public void setUsersid(String usersid) {
		this.usersid = usersid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	public String getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(String roleIdList) {
		this.roleIdList = roleIdList;
	}

	public String getLiname() {
		return liname;
	}

	public void setLiname(String liname) {
		this.liname = liname;
	}

	public String getProcessType() {
		return this.processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}

	public String[] getWorkUserName() {
		return workUserName;
	}

	public void setWorkUserName(String[] workUserName) {
		this.workUserName = workUserName;
	}

	public String[] getWorkUserId() {
		return workUserId;
	}

	public void setWorkUserId(String[] workUserId) {
		this.workUserId = workUserId;
	}

	public String[] getTypeId() {
		return typeId;
	}

	public void setTypeId(String[] typeId) {
		this.typeId = typeId;
	}

	public String[] getWorkLevel() {
		return workLevel;
	}

	public void setWorkLevel(String[] workLevel) {
		this.workLevel = workLevel;
	}

	public String[] getWorkLevelNo() {
		return workLevelNo;
	}

	public void setWorkLevelNo(String[] workLevelNo) {
		this.workLevelNo = workLevelNo;
	}

	public String getImNo() {
		return imNo;
	}

	public void setImNo(String imNo) {
		this.imNo = imNo;
	}
	
	

}