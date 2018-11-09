package com.smart.web.action.review;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.model.Annex;
import com.smart.model.AnnexType;
import com.smart.model.Contract;
import com.smart.model.ContractReview;
import com.smart.model.ContractReviewItem;
import com.smart.model.ProceStepDef;
import com.smart.model.Project;
import com.smart.model.ProjectProcessHistory;
import com.smart.model.ProjectProcessState;
import com.smart.model.T_hremployee;
import com.smart.model.User;
import com.smart.service.AnnexService;
import com.smart.service.AnnexTypeService;
import com.smart.service.ContractReviewItemService;
import com.smart.service.ContractReviewService;
import com.smart.service.ContractService;
import com.smart.service.ProceStepDefService;
import com.smart.service.ProjectProcessHistoryService;
import com.smart.service.ProjectProcessStateService;
import com.smart.service.T_hremployeeService;
import com.smart.service.UserService;
import com.smart.util.Constants;
import com.smart.util.DateUtils;
import com.smart.util.EmailSendTool;
import com.smart.util.FileRepository;
import com.smart.util.Page;
import com.smart.util.ReflectionUtil;
import com.smart.web.action.BaseAction;

/**
 * 
 * 合同评审action
 * 
 */
@ParentPackage("control-user")
public class ContractReviewAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ContractService contractService;

	@Autowired
	private ContractReviewService contractReviewService;

	// @Autowired
	// private ReviewFileItemService reviewFileItemService; //合同评审附件
	@Autowired
	private AnnexService annexService;

	@Autowired
	private AnnexTypeService annexTypeService;

	@Autowired
	private ContractReviewItemService contractReviewItemService; // 合同评审内容

	@Autowired
	private UserService userService;

	@Autowired
	private ProceStepDefService proceStepDefService;

	@Autowired
	private ProjectProcessStateService processStateService;

	@Autowired
	private ProjectProcessHistoryService projectProcessHistoryService;
	
	@Autowired
	private T_hremployeeService hremployeeService;

	private ContractReview contractReview;

	private Contract contract;

	private File file;

	private String fileFileName;

	private Integer annexTypeId;

	private String fileNum;// 文件字号

	private String fileOwner;// 文件作者

	private String description;// 归档时间

	private String filePage;// 页号

	private String bidId;// 删除投标管理信息ID

	private Integer types;

	private Project project; // 项目

	/**
	 * 列表
	 */
	@Action("list")
	public String list() {
		contractReview = contractReview == null ? new ContractReview()
				: contractReview;
		contract = contract == null ? new Contract() : contract;
		Page<ContractReview> pageBean = contractReviewService.getPage(
				getPageNo(), pageSize, contract.getNo(), contract.getName());
		for (int i = 0; i < pageBean.getList().size(); i++) {
			ContractReview contractReview = pageBean.getList().get(i);
			Project proj = contractReview.getContract().getProject();
			ProjectProcessState pps = processStateService
					.getOneByProjectIdAndStepCode(proj.getId(),
							Constants.StepCode.CONTRACT_REVIEW);
			if (pps != null) {
				String[] handlers = pps.getCurrentUsers().split(",");
				List<String> userList = Arrays.asList(handlers);
				if (userList.contains(getUser().getId().toString())) {
					contractReview.setOperable(true);
				}
			} else {
				contractReview.setOperable(false);
			}
		}
		put("pageBean", pageBean);
		return "list";
	}

	@Action("edit")
	public String edit() {
		project = project == null ? new Project() : project;
		contractReview = contractReviewService
				.getOneByPorjectId(project.getId());
		contract = contractService.get(contractReview.getContract().getId());
		Project project = contract.getProject();
		contract.setName(project.getName());
		List<ContractReviewItem> contractReviewItemList = contractReviewItemService
				.getByReviewId(contractReview.getId());
		if (contractReviewItemList.size() == 0) {
			String[] itemArr = { "项目概况", "工作周期", "委托方要求", "取费标准及付款方式",
					"委托方其它特别要求" };
			for (String itemStr : itemArr) {
				ContractReviewItem item = new ContractReviewItem();
				item.setReviewName(itemStr);
				contractReviewItemList.add(item);
			}
		}
		put("contractReviewItemList", contractReviewItemList); // 合同评审内容
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

		List<Annex> annexList = annexService.getByProjectId(project.getId(), "-2");
		put("annexList", annexList);
		return "new";
	}

	@Action("new")
	public String _new() {
		contractReview = new ContractReview();
		List<AnnexType> annexTypeList = annexTypeService.getByStep("-2", "yes"); //
		put("annexTypeList", annexTypeList);
		return "new";
	}

	@Action("newreview")
	public String newreview() {
		contractReview = new ContractReview();
		put("cr", contractReview);
		return "new";
	}

	@Action(value = "toNextStep", results = {
			@Result(name = "list", location = "list", type = "redirectAction") })
	public String toNextStep() throws Exception {
		// 合同名称、合同编号
		String name = contract.getName();
		contract = contractService.get(contract.getId());
		contract.setName(name);
		contract.setNo(contractReview.getContractNo());
		contractService.save(contract);
		// 审批人
		if (StringUtils.isNotBlank(get("approverId"))) {
			User user = userService.get(Integer.parseInt(get("approverId")));
			contractReview.setApprover(user);
		}

		Project project = contract.getProject();

		if (contractReview.getOperaterType().equalsIgnoreCase("1")) {
			// 终止项目，更新项目状态
			ProjectProcessState pps = processStateService
					.getOneByProjectIdAndStepCode(project.getId(),
							Constants.StepCode.CONTRACT_REVIEW);
			pps.setCurrentStep(proceStepDefService
					.getStepByStepCode(Constants.StepCode.STOPED));
			pps.setCurrentUsers("");
			pps.setCurrentState(ProjectProcessState.STOPED);
			processStateService.update(pps);
			// 更新项目进程处理历史记录表
			ProjectProcessHistory pph = new ProjectProcessHistory();
			pph.setProject(project);
			pph.setProjectInfo(project.getProjectInfo());
			pph.setOperateStep(proceStepDefService
					.getStepByStepCode(Constants.StepCode.CONTRACT_REVIEW));
			pph.setOperateUser(getUser());
			pph.setOperateTime(DateUtils.getCurrentTime());
			pph.setOperateType(Constants.OperateType.TERMINATE);
			pph.setNextStep(null);
			pph.setNextHandlers("");
			projectProcessHistoryService.save(pph);
		} else {
			// 下一环节处理人
			String nextOperatorId = get("nextOperatorId");

			ProceStepDef nextStep = proceStepDefService.getStepByStepCode(
					Constants.StepCode.CONTRACT_REGISTRATION);
			// 更新项目进程状态表信息
			ProjectProcessState pps = processStateService
					.getOneByProjectIdAndStepCode(project.getId(),
							Constants.StepCode.CONTRACT_REVIEW);
			pps.setCurrentStep(nextStep);
			pps.setCurrentUsers(nextOperatorId);
			pps.setCurrentState(ProjectProcessState.IN_PROGRESS);
			processStateService.update(pps);
			// 更新项目进程处理历史记录表
			ProjectProcessHistory pph = new ProjectProcessHistory();
			pph.setProject(project);
			pph.setProjectInfo(project.getProjectInfo());
			pph.setOperateStep(proceStepDefService
					.getStepByStepCode(Constants.StepCode.CONTRACT_REVIEW));
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
				EmailSendTool sendEmail = new EmailSendTool();
				String subject = sendEmail.getSubject();
				String emailContent = sendEmail.buildContent(project.getNo(), project.getName(), nextStep.getStepName());
				sendEmail.send(emailAddress.toString(), subject, emailContent);
			}

		}
		contract.setProject(project);
		contractReview.setContract(contract);

		ContractReview oldcontractReview = contractReviewService
				.get(contractReview.getId());
		if (oldcontractReview.getUser() == null) {// 只在第一次进入时
			oldcontractReview.setUser(this.getUser());
			oldcontractReview.setDept(this.getMyDept());
		}
		// 属性拷贝，把更新内容拷贝到数据库查出的对象里，第三个参数表示不需要拷贝的属性名称
		ReflectionUtil.bean2Bean(oldcontractReview, contractReview,
				"id, ctime,user,dept,status");
		contractReviewService.update(oldcontractReview);
		// 评审内容
		if (null != getHttpServletRequest().getParameterValues("content")) {
			contractReviewItemService.deleteByReviewId(contractReview.getId());
			String[] arrContent = getHttpServletRequest()
					.getParameterValues("content");
//			String[] arrSatisfaction = getHttpServletRequest()
//					.getParameterValues("satisfaction");
			String[] arrReviewName = getHttpServletRequest()
					.getParameterValues("reviewName");
			for (int i = 0; i < arrContent.length; i++) {
				ContractReviewItem cri = new ContractReviewItem();
				cri.setContent(arrContent[i]);
//				cri.setSatisfaction(arrSatisfaction[i]);
				cri.setReviewName(arrReviewName[i]);
				cri.setContractReview(oldcontractReview);
				contractReviewItemService.save(cri);
			}
		}

		return "list";
	}

	@Action(value = "save", results = {
			@Result(name = "list", location = "list", type = "redirectAction") })
	public String save() throws Exception {
		// 合同名称、合同编号
		String name = contract.getName();
		contract = contractService.get(contract.getId());
		contract.setName(name);
		contract.setNo(contractReview.getContractNo());

		// 审批人
		if (StringUtils.isNotBlank(get("approverId"))) {
			User user = userService.get(Integer.parseInt(get("approverId")));
			contractReview.setApprover(user);
		}
		Project project = contract.getProject();
		contractService.save(contract);
		contractReview.setContract(contract);

		ContractReview oldcontractReview = contractReviewService
				.get(contractReview.getId());
		String isToApprover = this.get("toApprover"); // 提交审批
		if ("true".equalsIgnoreCase(isToApprover)) {
			oldcontractReview.setStatus(2);
		}

		// 属性拷贝，把更新内容拷贝到数据库查出的对象里，第三个参数表示不需要拷贝的属性名称
		ReflectionUtil.bean2Bean(oldcontractReview, contractReview,
				"id, ctime,user,dept,status");
		contractReviewService.update(oldcontractReview);

		// 更新项目进程处理历史记录表
		ProjectProcessState pps = processStateService
				.getOneByProjectIdAndStepCode(project.getId(),
						Constants.StepCode.CONTRACT_REVIEW);
		ProceStepDef currentStep = proceStepDefService.getStepByStepCode(Constants.StepCode.CONTRACT_REVIEW);
		ProjectProcessHistory pph = new ProjectProcessHistory();
		pph.setProject(project);
		pph.setProjectInfo(project.getProjectInfo());
		pph.setOperateStep(currentStep);
		pph.setOperateUser(getUser());
		pph.setOperateTime(DateUtils.getCurrentTime());
		pph.setOperateType(Constants.OperateType.SAVE);
		pph.setNextStep(currentStep);
		pph.setNextHandlers(pps.getCurrentUsers());

		if ("true".equalsIgnoreCase(isToApprover)) {
			// 更新项目进程状态表信息
			pps.setCurrentUsers(get("approverId"));
			processStateService.update(pps);

			pph.setOperateType(Constants.OperateType.APPROVAL);
			pph.setNextHandlers(get("approverId"));
			
			// 发送邮件通知下一步处理人有待办事项需要处理
			// 查询下一步处理人的邮箱地址
			StringBuilder emailAddress = new StringBuilder();
			if (StringUtils.isNotBlank(get("approverId"))) {
				String[] userIds = get("approverId").split(",");
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
						project.getName(), currentStep.getStepName());
				sendEmail.send(emailAddress.toString(), subject, emailContent);
			}
			
		}
		projectProcessHistoryService.save(pph);
		// 评审内容
		if (null != getHttpServletRequest().getParameterValues("content")) {
			contractReviewItemService.deleteByReviewId(contractReview.getId());
			String[] arrContent = getHttpServletRequest()
					.getParameterValues("content");
//			String[] arrSatisfaction = getHttpServletRequest()
//					.getParameterValues("satisfaction");
			String[] arrReviewName = getHttpServletRequest()
					.getParameterValues("reviewName");
			for (int i = 0; i < arrContent.length; i++) {
				ContractReviewItem cri = new ContractReviewItem();
				cri.setContent(arrContent[i]);
		//		cri.setSatisfaction(arrSatisfaction[i]);
				cri.setReviewName(arrReviewName[i]);
				cri.setContractReview(oldcontractReview);
				contractReviewItemService.save(cri);
			}
		}
		return "list";
	}

	@Action("check")
	public String check() {
		contract = contractService.get(contract.getId());
		contractReview = contractReviewService
				.getByContractId(contract.getId());
		return "new";
	}

	@Action("show")
	public String show() {
		contractReview = contractReviewService.get(contractReview.getId());
		contract = contractReview.getContract();
		Project project = contract.getProject();
		List<ContractReviewItem> contractReviewItemList = contractReviewItemService
				.getByReviewId(contractReview.getId());
		put("contractReviewItemList", contractReviewItemList); // 合同评审内容
		List<Annex> annexList = annexService.getByProjectId(project.getId(),
				"-2");
		put("annexList", annexList);
		return "new";
	}

	@Action("print")
	public String print() {
		contractReview = contractReviewService.get(contractReview.getId());
		contract = contractReview.getContract();
		Project project = contract.getProject();
		List<ContractReviewItem> contractReviewItemList = contractReviewItemService
				.getByReviewId(contractReview.getId());
		put("contractReviewItemList", contractReviewItemList); // 合同评审内容
		List<Annex> annexList = annexService.getByProjectId(project.getId(),
				"-2");
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
		ContractReview du = contractReviewService.get(contractReview.getId());
		if (du == null) {
			return "-1";
		}
		du.setStatus(-1);
		contractReviewService.update(du);
		write("1"); // ajax请求用write
		return null;
	}

	/**
	 * 删除合同评审中的信息 1-评审类容 2-附件
	 * 
	 * @param id
	 * @return
	 */
	@Action("deletecls")
	public String deletecls() {
		if (types == 1) {
			ContractReviewItem cri = contractReviewItemService
					.get(Integer.parseInt(bidId));
			cri.setStatus(-1);
			contractReviewItemService.update(cri);
			write("1"); // ajax请求用write返回数据
		}
		if (types == 2) {
			Annex annex = annexService.get(Integer.parseInt(bidId));
			annex.setStatus(-1);
			annexService.update(annex);
			write("1"); // ajax请求用write返回数据
		}
		return null;
	}

	@Action("uploadFile")
	public String uploadFile() throws Exception {
		contractReview = contractReviewService.get(contractReview.getId());
		Project project = contractReview.getContract().getProject();

		String filedir = "review/" + project.getId() + "/";
		FileRepository fileRepository = new FileRepository();
		String root = fileRepository.storeByExt(filedir, fileFileName, file);

		Annex annex = new Annex();
		if (annexTypeId != null) {
			AnnexType annexType = annexTypeService.get(annexTypeId);
			annex.setAnnexType(annexType);
		}
		annex.setName(fileFileName);
		annex.setUser(this.getUser());
		annex.setDept(this.getMyDept());
		annex.setProject(project);
		annex.setStepName("-2"); // -3代表 投标总结
		// ----------------------
		annex.setFileNum(fileNum);// 文件字号
		annex.setFileOwner(fileOwner);// 文件作者
		annex.setDescription(description); // 描述
		annex.setFilePage(filePage);// 页号

		long size = file.getTotalSpace();
		annex.setSize(size);
		annex.setPath(root);
		annexService.save(annex);
		List<Annex> annexList = annexService.getByProjectId(project.getId(),
				"-2"); // -合同评审
		put("annexList", annexList);
		StringBuffer data = new StringBuffer();
		data.append("[tr]");
		data.append(
				"[td width='254' style='text-align:center; font-weight:bold; background-color:#d3e0f1;']");
		data.append("附件名称");
		data.append("[/td]");
		data.append(
				"[td width='100' style='text-align:center; font-weight:bold; background-color:#d3e0f1;']");
		data.append("附件类型");
		data.append("[/td]");
		data.append(
				"[td width='205' style='text-align:center; font-weight:bold; background-color:#d3e0f1;']");
		data.append("上传人");
		data.append("[/td]");
		data.append(
				"[td width='120' style='text-align:center; font-weight:bold; background-color:#d3e0f1;']");
		data.append("上传时间");
		data.append("[/td]");
		data.append(
				"[td width='100' style='text-align:center; font-weight:bold; background-color:#d3e0f1;']");
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
			tr.append("[a onclick='javascript:deleteRow(" + ax.getId()
					+ " ,2)']");
			tr.append("删除");
			tr.append("[/a]");
			tr.append("[/td]");
			tr.append("[/tr]");
			data.append(tr.toString());
		}
		System.out.println("-----------------------------" + data);
		write(data.toString()); // ajax请求用write
		return null;
	}

	public ContractService getContractService() {
		return contractService;
	}

	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}

	public ContractReviewService getContractReviewService() {
		return contractReviewService;
	}

	public void setContractReviewService(
			ContractReviewService contractReviewService) {
		this.contractReviewService = contractReviewService;
	}

	public ContractReview getContractReview() {
		return contractReview;
	}

	public void setContractReview(ContractReview contractReview) {
		this.contractReview = contractReview;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
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

	public String getBidId() {
		return bidId;
	}

	public void setBidId(String bidId) {
		this.bidId = bidId;
	}

	public Integer getTypes() {
		return types;
	}

	public void setTypes(Integer types) {
		this.types = types;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
