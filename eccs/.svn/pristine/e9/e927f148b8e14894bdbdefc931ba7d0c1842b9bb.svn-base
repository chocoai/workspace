package com.smart.web.action.contract;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.model.Contract;
import com.smart.model.ContractCustomer;
import com.smart.model.Dept;
import com.smart.model.DesignStage;
import com.smart.model.EditorialType;
import com.smart.model.ProceStepDef;
import com.smart.model.Project;
import com.smart.model.ProjectNature;
import com.smart.model.ProjectProcessHistory;
import com.smart.model.ProjectProcessState;
import com.smart.model.ProjectType;
import com.smart.model.ServiceType;
import com.smart.model.T_Contact;
import com.smart.model.T_Customer;
import com.smart.model.T_hremployee;
import com.smart.model.User;
import com.smart.service.ContractCustomerService;
import com.smart.service.ContractService;
import com.smart.service.DeptService;
import com.smart.service.DesignStageService;
import com.smart.service.EditorialTypeService;
import com.smart.service.ProceStepDefService;
import com.smart.service.ProjectNatureService;
import com.smart.service.ProjectProcessHistoryService;
import com.smart.service.ProjectProcessStateService;
import com.smart.service.ProjectService;
import com.smart.service.ProjectTypeService;
import com.smart.service.ServiceTypeService;
import com.smart.service.T_ContactService;
import com.smart.service.T_hremployeeService;
import com.smart.service.UserService;
import com.smart.util.Constants;
import com.smart.util.DateUtils;
import com.smart.util.EmailSendTool;
import com.smart.util.Page;
import com.smart.util.ReflectionUtil;
import com.smart.web.action.BaseAction;

/**
 * 
 * 合同管理action
 * 
 */
@ParentPackage("control-user")
public class ContractAction extends BaseAction { // 合同登记

	private static final long serialVersionUID = 1L;

	@Autowired
	private ContractService contractService;

	@Autowired
	private ProjectTypeService projectTypeService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private ProjectNatureService projectNatureService;

	@Autowired
	private ServiceTypeService serviceTypeService;

	@Autowired
	private EditorialTypeService editorialTypeService;

	@Autowired
	private DesignStageService designStageService;

	@Autowired
	private ContractCustomerService contractCustomerService;

	@Autowired
	private UserService userService;

	@Autowired
	private DeptService deptService;

	@Autowired
	private T_ContactService tContactService;

	@Autowired
	private ProceStepDefService proceStepDefService;

	@Autowired
	private ProjectProcessStateService processStateService;

	@Autowired
	private ProjectProcessHistoryService projectProcessHistoryService;
	
	@Autowired
	private T_hremployeeService hremployeeService;

	private Contract contract;

	private Project project; // 项目

	/**
	 * 新建
	 */
	@Action("new")
	public String _new() {
		List<ProjectType> projectTypeList = projectTypeService.getAll();
		List<ProjectNature> projectNatureList = projectNatureService.getAll();
		List<ServiceType> serviceTypeList = serviceTypeService.getAll();
		List<DesignStage> designStageList = designStageService.getAll();
		put("projectTypeList", projectTypeList);
		put("projectNatureList", projectNatureList);
		put("serviceTypeList", serviceTypeList);
		put("designStageList", designStageList);
		put("user", this.getUser());
		put("dept", this.getMyDept());
		String ctime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		put("ctime", ctime);
		return "new";
	}

	@Action("show")
	public String show() {
		// 客户信息
		List<ContractCustomer> contractCustomerList = contractCustomerService
				.getByContractId(contract.getId());
		put("contractCustomerList", contractCustomerList);
		// 建设阶段
		List<DesignStage> designStageList = designStageService.getAll();
		put("designStageList", designStageList);
		contract = contractService.get(contract.getId());
		project = contract.getProject();
		return "show";
	}

	@Action("print")
	public String print() {
		// 客户信息
		List<ContractCustomer> contractCustomerList = contractCustomerService
				.getByContractId(contract.getId());
		put("contractCustomerList", contractCustomerList);
		// 建设阶段
		List<DesignStage> designStageList = designStageService.getAll();
		put("designStageList", designStageList);
		contract = contractService.get(contract.getId());
		project = contract.getProject();
		return "print";
	}

	@Action(value = "toNextStep", results = {
			@Result(name = "list", location = "list", type = "redirectAction") })
	public String toNextStep() {
		// 工程类别
		if (StringUtils.isNotBlank(get("projectTypeId"))) {
			ProjectType projectType = projectTypeService
					.get(Integer.parseInt(get("projectTypeId")));
			contract.setProjectType(projectType);
		}
		// 工程性质
		if (StringUtils.isNotBlank(get("projectNatureId"))) {
			ProjectNature projectNature = projectNatureService
					.get(Integer.parseInt(get("projectNatureId")));
			contract.setProjectNature(projectNature);
		}
		// 签约部门
		if (StringUtils.isNotBlank(get("contractDeptId"))) {
			Dept contractDept = deptService.get(get("contractDeptId"));
			contract.setContractDept(contractDept);
		}
		// 签约人
		if (StringUtils.isNotBlank(get("managerId"))) {
			User contractuser = userService
					.get(Integer.parseInt(get("managerId")));
			contract.setManager(contractuser);
		}
		// 咨询类型、业务范围
		String nextOperatorId = get("nextOperatorId");
		if (project.getId() != null) {
			project = projectService.get(project.getId());
			contract.setServiceType(project.getServiceType());
			contract.setEditorialType(project.getEditorialType());
			// project.setNextOperator(nextOperatorId);// 下一环节处理人
			if (project.getProcessType()
					.equals(Constants.ProcessType.IN_ORDER)) {
				project.setPmOperator(nextOperatorId);
			}
			contract.setProject(project);
		}

		Contract oldcontract = contractService.get(contract.getId());
		if (oldcontract.getUser() == null) {// 只在第一次进入时
			oldcontract.setUser(this.getUser());
			oldcontract.setDept(this.getMyDept());
		}
		// 属性拷贝，把更新内容拷贝到数据库查出的对象里，第三个参数表示不需要拷贝的属性名称
		ReflectionUtil.bean2Bean(oldcontract, contract,
				"id, ctime, user,dept,status");
		contractService.update(oldcontract);

		if (project.getProcessType().equals(Constants.ProcessType.IN_ORDER)) {

			projectService.saveToStep(project);

			// 更新项目进程状态表信息
			ProjectProcessState pps = processStateService
					.getOneByProjectIdAndStepCode(project.getId(),
							Constants.StepCode.CONTRACT_REGISTRATION);
			ProceStepDef nextStep = proceStepDefService.getStepByStepCode(Constants.StepCode.PM_DISPATCH);
			// 记录项目管理环节的状态
			pps.setProject(project);
			pps.setProjectInfo(project.getProjectInfo());
			pps.setCurrentStep(nextStep);
			pps.setCurrentState(ProjectProcessState.IN_PROGRESS);
			pps.setCurrentUsers(nextOperatorId);
			pps.setType(ProjectProcessState.TYPE_PM);
			pps.setLastUpdateTime(DateUtils.getCurrentTime());
			processStateService.update(pps);

			// 更新项目进程处理历史记录表
			ProjectProcessHistory pph = new ProjectProcessHistory();
			pph.setProject(project);
			pph.setProjectInfo(project.getProjectInfo());
			pph.setOperateStep(proceStepDefService.getStepByStepCode(
					Constants.StepCode.CONTRACT_REGISTRATION));
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
						T_hremployee hrEmployee = hremployeeService
								.getSysCode(Integer.valueOf(userIds[i]));
						if (hrEmployee != null) {
							emailAddress.append(hrEmployee.getEmail())
									.append(",");
						}
					}
				}
				EmailSendTool sendEmail = new EmailSendTool();
				String subject = sendEmail.getSubject();
				String emailContent = sendEmail.buildContent(project.getNo(),
						project.getName(), nextStep.getStepName());
				sendEmail.send(emailAddress.toString(), subject, emailContent);
			}
			
		}
		if (project.getProcessType()
				.equals(Constants.ProcessType.INDEPENDENTLY)) {
			// 更新项目进程状态表信息
			ProjectProcessState pps = processStateService
					.getOneByProjectIdAndStepCode(project.getId(),
							Constants.StepCode.CONTRACT_REGISTRATION);
			pps.setCurrentStep(null);
			pps.setCurrentUsers("");
			pps.setCurrentState(ProjectProcessState.FINISHED);
			pps.setLastUpdateTime(DateUtils.getCurrentTime());
			processStateService.update(pps);

			// 更新项目进程处理历史记录表
			ProjectProcessHistory pph = new ProjectProcessHistory();
			pph.setProject(project);
			pph.setProjectInfo(project.getProjectInfo());
			pph.setOperateStep(proceStepDefService.getStepByStepCode(
					Constants.StepCode.CONTRACT_REGISTRATION));
			pph.setOperateUser(getUser());
			pph.setOperateTime(DateUtils.getCurrentTime());
			pph.setOperateType(Constants.OperateType.SUBMIT);
			pph.setNextStep(null);
			pph.setNextHandlers("");
			projectProcessHistoryService.save(pph);
		}

		if (null != getHttpServletRequest().getParameterValues("name")) {
			String[] arrNo = getHttpServletRequest().getParameterValues("no");
			String[] arrName = getHttpServletRequest()
					.getParameterValues("name");
			String[] arrContactName = getHttpServletRequest()
					.getParameterValues("contactName");
			String[] arrTel = getHttpServletRequest().getParameterValues("tel");
			for (int i = 0; i < arrName.length; i++) {
				ContractCustomer cc = new ContractCustomer();
				cc.setNo(arrNo[i]);
				cc.setName(arrName[i]);
				cc.setContactName(arrContactName[i]);
				cc.setTel(arrTel[i]);
				cc.setContract(oldcontract);
				contractCustomerService.save(cc);
			}
		}
		return "list";
	}

	/**
	 * 保存 修改
	 * 
	 * @throws UnsupportedEncodingException
	 */
	@Action(value = "save", results = {
			@Result(name = "list", location = "list", type = "redirectAction") })
	public String save() {
		// 工程类别
		if (StringUtils.isNotBlank(get("projectTypeId"))) {
			ProjectType projectType = projectTypeService
					.get(Integer.parseInt(get("projectTypeId")));
			contract.setProjectType(projectType);
		}
		// 工程性质
		if (StringUtils.isNotBlank(get("projectNatureId"))) {
			ProjectNature projectNature = projectNatureService
					.get(Integer.parseInt(get("projectNatureId")));
			contract.setProjectNature(projectNature);
		}
		// 咨询类型、业务范围
		if (project.getId() != null) {
			project = projectService.get(project.getId());
			contract.setServiceType(project.getServiceType());
			contract.setEditorialType(project.getEditorialType());
			contract.setProject(project);
		}
		// 签约部门
		if (StringUtils.isNotBlank(get("contractDeptId"))) {
			Dept contractDept = deptService.get(get("contractDeptId"));
			contract.setContractDept(contractDept);
		}
		// 签约人
		if (StringUtils.isNotBlank(get("managerId"))) {
			User contractuser = userService
					.get(Integer.parseInt(get("managerId")));
			contract.setManager(contractuser);
		}

		Contract oldcontract = contractService.get(contract.getId());
		if (oldcontract.getUser() == null) {// 只在第一次进入时
			oldcontract.setUser(this.getUser());
			oldcontract.setDept(this.getMyDept());
		}
		// 属性拷贝，把更新内容拷贝到数据库查出的对象里，第三个参数表示不需要拷贝的属性名称
		ReflectionUtil.bean2Bean(oldcontract, contract,
				"id, ctime,,user,dept,status");
		contractService.update(oldcontract);

		ProceStepDef nextStep = proceStepDefService
				.getStepByStepCode(Constants.StepCode.CONTRACT_REGISTRATION);
		// 更新项目进程处理历史记录表
		ProjectProcessState pps = processStateService
				.getOneByProjectIdAndStepCode(project.getId(),
						Constants.StepCode.CONTRACT_REGISTRATION);
		ProjectProcessHistory pph = new ProjectProcessHistory();
		pph.setProject(project);
		pph.setProjectInfo(project.getProjectInfo());
		pph.setOperateStep(proceStepDefService
				.getStepByStepCode(Constants.StepCode.CONTRACT_REGISTRATION));
		pph.setOperateUser(getUser());
		pph.setOperateTime(DateUtils.getCurrentTime());
		pph.setOperateType(Constants.OperateType.SAVE);
		pph.setNextStep(nextStep);
		pph.setNextHandlers(pps.getCurrentUsers());
		projectProcessHistoryService.save(pph);

		if (null != getHttpServletRequest().getParameterValues("name")) {
			String[] arrNo = getHttpServletRequest().getParameterValues("no");
			String[] arrName = getHttpServletRequest()
					.getParameterValues("name");
			String[] arrContactName = getHttpServletRequest()
					.getParameterValues("contactName");
			String[] arrTel = getHttpServletRequest().getParameterValues("tel");
			for (int i = 0; i < arrName.length; i++) {
				ContractCustomer cc = new ContractCustomer();
				cc.setNo(arrNo[i]);
				cc.setName(arrName[i]);
				cc.setContactName(arrContactName[i]);
				cc.setTel(arrTel[i]);
				cc.setContract(oldcontract);
				contractCustomerService.save(cc);
			}
		}

		return "list";
	}

	/**
	 * 编辑
	 */
	@Action("edit")
	public String edit() {
		project = project == null ? new Project() : project;
		contract = contractService.getOneByPorjectId(project.getId());
		// 客户信息
		List<ContractCustomer> contractCustomerList = contractCustomerService
				.getByContractId(contract.getId());
		put("contractCustomerList", contractCustomerList);
		// 工程类别
		List<ProjectType> projectTypeList = projectTypeService.getAll();
		put("projectTypeList", projectTypeList);
		// 工程性质
		List<ProjectNature> projectNatureList = projectNatureService.getAll();
		put("projectNatureList", projectNatureList);
		// 咨询类型
		List<ServiceType> serviceTypeList = serviceTypeService.getAll();
		put("serviceTypeList", serviceTypeList);
		// 业务范围
		List<EditorialType> editorialTypeList = editorialTypeService
				.getPid(contract.getProject().getServiceType().getId());
		put("editorialTypeList", editorialTypeList);
		// 建设阶段
		List<DesignStage> designStageList = designStageService.getAll();
		put("designStageList", designStageList);
		project = contract.getProject();

		// 提取招标人信息(客户信息)
		T_Customer customer = project.getCustomer();
		put("customer", customer);
		// 提取招标人联系人即客户单位默认联系人
		if (null != customer) {
			T_Contact t_Contact = tContactService
					.getDefaultContactByCustomerId(customer.getId());
			put("t_Contact", t_Contact);
		}

		// 下一环节处理人
		/*if (StringUtils.isNotBlank(project.getNextOperator())) {
			String[] userIds = project.getNextOperator().split(",");
			StringBuffer userNames = new StringBuffer();
			for (int i = 0; i < userIds.length; i++) {
				User user = userService.get(Integer.parseInt(userIds[i]));
				userNames.append(user.getName()).append(",");
			}
			String nextOperatorName = StringUtils
					.removeEnd(userNames.toString(), ",");
			put("nextOperatorName", nextOperatorName);
		}*/
		return "new";
	}

	/**
	 * 列表
	 */
	@Action("list")
	public String list() {
		contract = contract == null ? new Contract() : contract;
		Page<Contract> pageBean = contractService.getPage(getPageNo(), pageSize,
				contract.getNo(), contract.getName());
		for (int i = 0; i < pageBean.getList().size(); i++) {
			Contract contract = pageBean.getList().get(i);
			Project proj = contract.getProject();
			ProjectProcessState pps = processStateService
					.getOneByProjectIdAndStepCode(proj.getId(),
							Constants.StepCode.CONTRACT_REGISTRATION);
			if (pps != null) {
				String[] handlers = pps.getCurrentUsers().split(",");
				List<String> userList = Arrays.asList(handlers);
				if (userList.contains(getUser().getId().toString())) {
					contract.setOperable(true);
				}
			} else {
				contract.setOperable(false);
			}
		}
		put("pageBean", pageBean);
		return "list";
	}

	/**
	 * 列表
	 */
	@Action("customerList")
	public String customerList() {
		contract = contract == null ? new Contract() : contract;
		Page<Contract> pageBean = contractService.getPage(getPageNo(), pageSize,
				contract.getNo(), contract.getName());
		put("pageBean", pageBean);
		return "customerList";
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@Action("delete")
	public String delete() {
		Contract du = contractService.get(contract.getId());
		if (du == null) {
			return "-1";
		}
		du.setStatus(-1);
		contractService.update(du);
		write("1"); // ajax请求用write
		return null;
	}

	/**
	 * 删除客户信息
	 * 
	 * @param id
	 * @return
	 */
	@Action("deletecls")
	public String deletecls() {
		if (StringUtils.isNotBlank(get("customerId"))) {
			ContractCustomer cc = contractCustomerService
					.get(Integer.parseInt(get("customerId")));
			cc.setStatus(-1);
			contractCustomerService.update(cc);
			write("1"); // ajax请求用write
		}
		return null;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
