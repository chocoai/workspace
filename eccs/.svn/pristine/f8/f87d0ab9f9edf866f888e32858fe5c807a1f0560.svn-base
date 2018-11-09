package com.smart.web.action.bid;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.model.Annex;
import com.smart.model.AnnexType;
import com.smart.model.BidMember;
import com.smart.model.BidPlan;
import com.smart.model.BidSpeed;
import com.smart.model.BidSummary;
import com.smart.model.BidSummaryExpert;
import com.smart.model.BidSummaryOpponent;
import com.smart.model.Contract;
import com.smart.model.ContractReview;
import com.smart.model.ProceStepDef;
import com.smart.model.Project;
import com.smart.model.ProjectProcessHistory;
import com.smart.model.ProjectProcessState;
import com.smart.model.T_Contact;
import com.smart.model.T_Customer;
import com.smart.model.T_hremployee;
import com.smart.service.AnnexService;
import com.smart.service.AnnexTypeService;
import com.smart.service.BidMemberService;
import com.smart.service.BidPlanService;
import com.smart.service.BidSpeedService;
import com.smart.service.BidSummaryExpertService;
import com.smart.service.BidSummaryOpponentService;
import com.smart.service.BidSummaryService;
import com.smart.service.ContractReviewService;
import com.smart.service.ContractService;
import com.smart.service.DeptService;
import com.smart.service.ProceStepDefService;
import com.smart.service.ProjectProcessHistoryService;
import com.smart.service.ProjectProcessStateService;
import com.smart.service.ProjectService;
import com.smart.service.T_ContactService;
import com.smart.service.T_hremployeeService;
import com.smart.service.UserService;
import com.smart.util.Constants;
import com.smart.util.DateUtils;
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
public class BidAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private BidPlanService bidPlanService;

	@Autowired
	private BidSummaryService bidSummaryService;

	@Autowired
	private UserService userService;

	@Autowired
	private AnnexService annexService;

	@Autowired
	private DeptService deptService;

	@Autowired
	private AnnexTypeService annexTypeService;

	@Autowired
	private BidMemberService bidMemberService;

	@Autowired
	private BidSpeedService bidSpeedService;

	@Autowired
	private ContractService contractService;

	@Autowired
	private T_ContactService tContactService;

	@Autowired
	private ContractReviewService contractReviewService;

	@Autowired
	private BidSummaryOpponentService bidSummaryOpponentService;

	@Autowired
	private BidSummaryExpertService bidSummaryExpertService;

	@Autowired
	private ProceStepDefService proceStepDefService;

	@Autowired
	private ProjectProcessStateService processStateService;

	@Autowired
	private ProjectProcessHistoryService projectProcessHistoryService;
	
	@Autowired
	private T_hremployeeService hremployeeService;

	private BidPlan bidPlan; // 投标策划

	private BidSummary bidSummary; // 投标策划

	private Project project; // 项目

	private File file;

	private String fileFileName;

	// private Integer bidFileId;
	private Integer annexTypeId;

	private BidMember bidMember;

	private BidSpeed bidSpeed; // 进度

	private String bidId;// 删除投标管理信息ID

	private Integer types;

	private BidSummaryOpponent bidSummaryOpponent; // 竞争对手

	private String o_name;

	private String o_amountType;

	private String o_amount;

	private String o_count;

	private String o_rank;

	private String o_bidResult;

	private BidSummaryExpert bidSummaryExpert; // 评标专家

	private String e_name;

	private String e_deptName;

	private String e_tel;

	private String e_remark;

	private String fileNum;// 文件字号

	private String fileOwner;// 文件作者

	private String description;// 归档时间

	private String filePage;// 页号

	/**
	 * 列表
	 */
	@Action("list")
	public String list() {
		project = project == null ? new Project() : project;
		bidPlan = bidPlan == null ? new BidPlan() : bidPlan;
		Page<BidPlan> pageBean = bidPlanService.getPage(pageNo, pageSize,
				project.getNo(), project.getName(), bidPlan.getBidDeptName());
		for (int i = 0; i < pageBean.getList().size(); i++) {
			BidPlan plan = pageBean.getList().get(i);
			Project proj = plan.getProject();
			ProjectProcessState pps = processStateService
					.getOneByProjectIdAndStepCode(proj.getId(),
							Constants.StepCode.BID_PLAN);
			if (pps != null) {
				String[] handlers = pps.getCurrentUsers().split(",");
				List<String> userList = Arrays.asList(handlers);
				if (userList.contains(getUser().getId().toString())) {
					plan.setResStates(true);
				}
			} else {
				plan.setResStates(false);
			}
		}
		put("pageBean", pageBean);
		return "listplan";
	}

	/**
	 * 编辑
	 */
	@Action("edit")
	public String edit() {
		project = project == null ? new Project() : project;
		bidPlan = bidPlanService.getBidPlanByProjectId(project.getId());
		project = bidPlan.getProject();
		if (bidPlan != null) {
			List<BidMember> bidMemberList = bidMemberService
					.getByPlanId(bidPlan.getId());
			put("bidMemberList", bidMemberList); // 投标策划 项目组成员表
			List<BidSpeed> bidSpeedList = bidSpeedService
					.getByPlanId(bidPlan.getId());
			put("bidSpeedList", bidSpeedList); // 投标策划 进度计划表
		}
		List<AnnexType> annexTypeList = annexTypeService.getByStep("-3", "yes"); //
		put("annexTypeList", annexTypeList);

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
				if (StringUtils.isNotBlank(userIds[i])) {
					User user = userService.get(Integer.parseInt(userIds[i]));
					userNames.append(user.getName()).append(",");	
				}
			}
			String nextOperatorName = StringUtils
					.removeEnd(userNames.toString(), ",");
			put("nextOperatorName", nextOperatorName);
		}*/

		return "newplan";
	}

	/**
	 * 保存
	 */
	@Action(value = "save", results = {
			@Result(name = "list", location = "list", type = "redirectAction") })
	public String save() throws Exception {
		// 投标部门
		if (StringUtils.isNotBlank(get("bidDeptId"))) {
			bidPlan.setBidDept(deptService.get(get("bidDeptId")));
		}
		if (StringUtils.isNotBlank(get("projectId"))) {
			project = projectService.get(Integer.parseInt(get("projectId")));
		}
		bidPlan.setProject(project);
		BidPlan oldBidPlan = bidPlanService.get(bidPlan.getId());
		
		// 下一环节处理人
		// String nextOperatorId = get("nextOperatorId");
		
		String isToApprover = this.get("toApprover");// 提交审批
		if (StringUtils.isNotBlank(get("approverId"))) {
			oldBidPlan.setApprover(
					userService.get(Integer.parseInt(get("approverId"))));
		}
		if ("true".equalsIgnoreCase(isToApprover)) {
			oldBidPlan.setStatus(2);

		}

		// 属性拷贝，把更新内容拷贝到数据库查出的对象里，第三个参数表示不需要拷贝的属性名称
		ReflectionUtil.bean2Bean(oldBidPlan, bidPlan,
				"id,ctime,status,approver,handler,createUser,createDept");
		bidPlanService.update(oldBidPlan);

		ProjectProcessState pps = processStateService
				.getOneByProjectIdAndStepCode(project.getId(),
						Constants.StepCode.BID_PLAN);
		// 更新项目进程处理历史记录表
		ProceStepDef currentStep = proceStepDefService
				.getStepByStepCode(Constants.StepCode.BID_PLAN);
		ProjectProcessHistory pph = new ProjectProcessHistory();
		pph.setProject(project);
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
							emailAddress.append(hrEmployee.getEmail()).append(",");
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

		// 项目组成员
		saveBidMember(oldBidPlan);
		// 项目进度计划
		saveBidSpeed(oldBidPlan);
		return "list";
	}

	/**
	 * 提交
	 */
	@Action(value = "toNextStep", results = {
			@Result(name = "list", location = "list", type = "redirectAction") })
	public String toNextStep() throws Exception { // 从投标策划派发给投标总结
		// 投标部门
		if (StringUtils.isNotBlank(get("bidDeptId"))) {
			bidPlan.setBidDept(deptService.get(get("bidDeptId")));
		}
		// 审批人
		if (StringUtils.isNotBlank(get("approverId"))) {
			bidPlan.setApprover(
					userService.get(Integer.parseInt(get("approverId"))));
		}

		if (StringUtils.isNotBlank(get("projectId"))) {
			project = projectService.get(Integer.parseInt(get("projectId")));
		}

		if (bidPlan.getOperaterType().equalsIgnoreCase("1")) {
			// 终止项目，更新项目状态
			// 更新项目进程状态表信息
			ProjectProcessState pps = processStateService
					.getOneByProjectIdAndStepCode(project.getId(),
							Constants.StepCode.BID_PLAN);
			pps.setCurrentStep(proceStepDefService
					.getStepByStepCode(Constants.StepCode.STOPED));
			pps.setCurrentUsers("");
			pps.setCurrentState(ProjectProcessState.STOPED);
			processStateService.update(pps);
			// 更新项目进程处理历史记录表
			ProjectProcessHistory pph = new ProjectProcessHistory();
			pph.setProject(project);
			pph.setOperateStep(proceStepDefService
					.getStepByStepCode(Constants.StepCode.BID_PLAN));
			pph.setOperateUser(getUser());
			pph.setOperateTime(DateUtils.getCurrentTime());
			pph.setOperateType(Constants.OperateType.TERMINATE);
			pph.setNextStep(null);
			pph.setNextHandlers("");
			projectProcessHistoryService.save(pph);
		} else {
			// 下一环节处理人
			String nextOperatorId = get("nextOperatorId");
			// 流转到合同评审阶段，投标总结随时可处理
			Contract contract = new Contract(); // 合同
			ContractReview review = new ContractReview(); // 合同评审
			//自动获取合同编号
			int serviceTypeId = project.getServiceType().getId();
			String typeName = "";
			if(1 == serviceTypeId){
				typeName = "QQ";
			}else if (2 == serviceTypeId){
				typeName = "PP";
			}else if (3 == serviceTypeId){
				typeName = "ZB";
			}else if (4 == serviceTypeId){
				typeName = "ZF";
			}else if (5 == serviceTypeId){
				typeName = "ZJ";
			}else{
				typeName = "TD";
			}
			Calendar year=Calendar.getInstance();
			StringBuffer temp = new StringBuffer();
			temp.append(Integer.toString(year.get(Calendar.YEAR))).append(typeName).
			append(String.format("%04d", (Integer.valueOf(contractReviewService.getContractLast().substring(6, 10))+1)));
			contract.setNo(temp.toString());
			contract.setProject(project);
			review.setContract(contract);
			contractService.save(contract);
			contractReviewService.save(review);

			ProceStepDef nextStep = proceStepDefService
					.getStepByStepCode(Constants.StepCode.CONTRACT_REVIEW);
			// 更新项目进程状态表信息
			ProjectProcessState pps = processStateService
					.getOneByProjectIdAndStepCode(project.getId(),
							Constants.StepCode.BID_PLAN);
			pps.setCurrentStep(nextStep);
			pps.setCurrentUsers(nextOperatorId);
			pps.setCurrentState(ProjectProcessState.IN_PROGRESS);
			processStateService.update(pps);
			// 更新项目进程处理历史记录表
			ProjectProcessHistory pph = new ProjectProcessHistory();
			pph.setProject(project);
			pph.setOperateStep(proceStepDefService
					.getStepByStepCode(Constants.StepCode.BID_PLAN));
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

		bidPlan.setProject(project);
		BidSummary bidSummary = new BidSummary();
		bidSummary.setProject(project);
		bidSummaryService.save(bidSummary);

		BidPlan oldBidPlan = bidPlanService.get(bidPlan.getId());

		// 属性拷贝，把更新内容拷贝到数据库查出的对象里，第三个参数表示不需要拷贝的属性名称
		ReflectionUtil.bean2Bean(oldBidPlan, bidPlan,
				"id, ctime, status, handler,createUser,createDept");
		bidPlanService.update(oldBidPlan);

		// 项目组成员
		saveBidMember(oldBidPlan);
		// 项目进度计划
		saveBidSpeed(oldBidPlan);

		return "list";
	}

	/**
	 * 投标策划，保存项目组成员
	 * 
	 * @param bidPlan
	 */
	public void saveBidMember(BidPlan bidPlan) {
		if (null != getHttpServletRequest().getParameterValues("name")) {
			String[] arrname = getHttpServletRequest()
					.getParameterValues("name");
			String[] arrpostion = getHttpServletRequest()
					.getParameterValues("position");
			String[] arrcontact = getHttpServletRequest()
					.getParameterValues("contact");
			for (int i = 0; i < arrname.length; i++) {
				BidMember bm = new BidMember();
				bm.setName(arrname[i]);
				bm.setPosition(arrpostion[i]);
				bm.setContact(arrcontact[i]);
				bm.setBidPlan(bidPlan);
				bm.setUser(this.getUser());
				bm.setDept(this.getMyDept());
				bidMemberService.save(bm);
			}
		}
	}

	/**
	 * 投标策划，保存进度计划
	 * 
	 * @param bidPlan
	 * @throws ParseException
	 */
	public void saveBidSpeed(BidPlan bidPlan) throws ParseException {
		if (null != getHttpServletRequest().getParameterValues("workContent")) {
			String[] arrworkContent = getHttpServletRequest()
					.getParameterValues("workContent");
			String[] arrcompleteDate = getHttpServletRequest()
					.getParameterValues("completeDate");
			String[] arrmasterName = getHttpServletRequest()
					.getParameterValues("masterName");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			for (int i = 0; i < arrworkContent.length; i++) {
				BidSpeed bs = new BidSpeed();
				bs.setWorkContent(arrworkContent[i]);
				bs.setMasterName(arrmasterName[i]);
				if (StringUtils.isNotBlank(arrcompleteDate[i])) {
					bs.setCompleteDate(sdf.parse(arrcompleteDate[i]));
				}
				bs.setBidPlan(bidPlan);
				bs.setUser(this.getUser());
				bs.setDept(this.getMyDept());
				bidSpeedService.save(bs);
			}
		}
	}

	// 投标实施
	@Action("newImp")
	public String newImp() {
		return "newImp";
	}

	@Action("newSummy")
	public String newSummy() {
		bidSummary = new BidSummary();
		return "newsummy";
	}

	@Action(value = "toNextStep3", results = {
			@Result(name = "listImp", location = "listImp", type = "redirectAction") })
	public String toNextStep3() {// 投标实施
		return "listImp";
	}

	@Action(value = "toNextStep2", results = {
			@Result(name = "listSummy", location = "listSummy", type = "redirectAction") })
	public String toNextStep2() {
		String[] arrename = {};
		String[] arredeptName = {};
		String[] arretel = {};
		String[] arreremark = {};
		if (!StringUtil.isBlank(e_name)) { // 评标专家
			arrename = e_name.split(",");
			arredeptName = e_deptName.split(",");
			arretel = e_tel.split(",");
			arreremark = e_remark.split(",");
		}
		String[] arroname = {};
		String[] arroamountType = {};
		String[] arroamount = {};
		String[] arrocount = {};
		String[] arrorank = {};
		String[] arrobidResult = {};
		if (!StringUtil.isBlank(o_name)) { // 竞争对手
			arroname = o_name.split(",");
			arroamountType = o_amountType.split(",");
			arroamount = o_amount.split(",");
			arrocount = o_count.split(",");
			arrorank = o_rank.split(",");
			arrobidResult = o_bidResult.split(",");
		}
		if (project.getId() != null) {
			project = projectService.get(project.getId());
			bidSummary.setProject(project);
			/*
			 * Contract contract = new Contract(); ContractReview review = new
			 * ContractReview(); project.setStep(-2);
			 * contract.setProject(project); review.setContract(contract);
			 * contractService.save(contract);
			 * contractReviewService.save(review);
			 */

		}
		if (bidSummary.getId() == null) { // 新增
		} else { // 更新
			BidSummary oldBidSummary = bidSummaryService
					.get(bidSummary.getId());
			// 属性拷贝，把更新内容拷贝到数据库查出的project里，最后的那个参数是不需要拷贝的属性名称
			ReflectionUtil.bean2Bean(oldBidSummary, bidSummary,
					"id, ctime, status");
			bidSummaryService.update(oldBidSummary);

			for (int i = 0; i < arrename.length; i++) { // 竞争对手
				BidSummaryExpert expert = new BidSummaryExpert();
				expert.setName(arrename[i]);
				expert.setDeptName(arredeptName[i]);
				expert.setTel(arretel[i]);
				expert.setRemark(arreremark[i]);
				expert.setBidSummary(bidSummary);
				expert.setUser(this.getUser());
				expert.setDept(this.getMyDept());
				bidSummaryExpertService.save(expert);
			}
			for (int i = 0; i < arroname.length; i++) { // 评标专家
				BidSummaryOpponent opponent = new BidSummaryOpponent();
				opponent.setName(arroname[i]);
				opponent.setAmountType(arroamountType[i]);
				opponent.setAmount(arroamount[i]);
				if (!StringUtil.isBlank(arrocount[i])) {
					opponent.setCount(Integer.valueOf(arrocount[i]));
				}
				if (!StringUtil.isBlank(arrorank[i])) {
					opponent.setRank(Integer.valueOf(arrorank[i]));
				}
				opponent.setBidResult(arrobidResult[i]);
				opponent.setBidSummary(bidSummary);
				opponent.setUser(this.getUser());
				opponent.setDept(this.getMyDept());
				bidSummaryOpponentService.save(opponent);
			}
		}
		return "listSummy";
	}

	@Action(value = "saveImp", results = {
			@Result(name = "listImp", location = "listImp", type = "redirectAction") })
	public String saveImp() {
		return "listImp";

	}

	@Action(value = "saveSummy", results = {
			@Result(name = "listSummy", location = "listSummy", type = "redirectAction") })
	public String saveSummy() {

		try {
			String[] arrename = {};
			String[] arredeptName = {};
			String[] arretel = {};
			String[] arreremark = {};
			if (!StringUtil.isBlank(e_name)) { // 评标专家
				arrename = e_name.split(",");
				arredeptName = e_deptName.split(",");
				arretel = e_tel.split(",");
				arreremark = e_remark.split(",");
			}
			String[] arroname = {};
			String[] arroamountType = {};
			String[] arroamount = {};
			String[] arrocount = {};
			String[] arrorank = {};
			String[] arrobidResult = {};
			if (!StringUtil.isBlank(o_name)) { // 竞争对手
				arroname = o_name.split(",");
				arroamountType = o_amountType.split(",");
				arroamount = o_amount.split(",");
				arrocount = o_count.split(",");
				arrorank = o_rank.split(",");
				arrobidResult = o_bidResult.split(",");

			}
			if (project.getId() != null) {
				project = projectService.get(project.getId());
				bidSummary.setProject(project);
			}
			if (bidSummary.getId() == null) { // 新增

			} else { // 更新
				BidSummary oldBidSummary = bidSummaryService
						.get(bidSummary.getId());
				// 属性拷贝，把更新内容拷贝到数据库查出的project里，最后的那个参数是不需要拷贝的属性名称
				ReflectionUtil.bean2Bean(oldBidSummary, bidSummary,
						"id, ctime, status");
				bidSummaryService.update(oldBidSummary);
				for (int i = 0; i < arrename.length; i++) { // 竞争对手
					BidSummaryExpert expert = new BidSummaryExpert();
					expert.setName(arrename[i]);
					expert.setDeptName(arredeptName[i]);
					expert.setTel(arretel[i]);
					expert.setRemark(arreremark[i]);
					expert.setBidSummary(bidSummary);
					expert.setUser(this.getUser());
					expert.setDept(this.getMyDept());
					bidSummaryExpertService.save(expert);
				}
				for (int i = 0; i < arroname.length; i++) { // 评标专家
					BidSummaryOpponent opponent = new BidSummaryOpponent();
					opponent.setName(arroname[i]);
					opponent.setAmountType(arroamountType[i]);
					opponent.setAmount(arroamount[i]);
					if (!StringUtil.isBlank(arrocount[i])) {
						opponent.setCount(Integer.valueOf(arrocount[i]));
					}
					if (!StringUtil.isBlank(arrorank[i])) {
						opponent.setRank(Integer.valueOf(arrorank[i]));
					}
					opponent.setBidResult(arrobidResult[i]);
					opponent.setBidSummary(bidSummary);
					opponent.setUser(this.getUser());
					opponent.setDept(this.getMyDept());
					bidSummaryOpponentService.save(opponent);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "listSummy";
	}

	@Action("editImp")
	public String editImp() {// 投标实施
		return "newImp";
	}

	@Action("editSummy")
	public String editSummy() {
		bidSummary = bidSummaryService.get(bidSummary.getId());
		project = bidSummary.getProject();
		List<BidSummaryExpert> bidSummaryExpertList = bidSummaryExpertService
				.getBySummaryId(bidSummary.getId());
		put("bidSummaryExpertList", bidSummaryExpertList); // 评标专家
		List<BidSummaryOpponent> bidSummaryOpponentList = bidSummaryOpponentService
				.getBySummaryId(bidSummary.getId());
		put("bidSummaryOpponentList", bidSummaryOpponentList); // 竞争对手
		//
		// List<BidFileItem> bidFileItemList =
		// bidFileItemService.getByBidSummaryId(bidSummary.getId());
		// put("bidFileItemList", bidFileItemList);
		List<Annex> annexList = annexService.getByProjectId(project.getId(),
				"-3");
		put("annexList", annexList);
		return "newsummy";
	}

	@Action("listImp")
	public String listImp() {// 投标实施
		return "listImp";
	}

	@Action("listSummy")
	public String listSummy() {
		String mydeptid = this.getMyDept().getId();
		project = project == null ? new Project() : project;
		bidSummary = bidSummary == null ? new BidSummary() : bidSummary;
		Page<BidSummary> pageBean = bidSummaryService.getPage(pageNo, pageSize,
				project.getNo(), project.getName(), bidSummary.getBidDeptName(),
				mydeptid);
		put("pageBean", pageBean);
		return "listsummy";
	}

	/**
	 * 显示
	 * 
	 * @return
	 */
	@Action("show")
	public String show() {
		List<BidMember> bidMemberList = bidMemberService
				.getByPlanId(bidPlan.getId());
		put("bidMemberList", bidMemberList); // 投标策划 成员表
		List<BidSpeed> bidSpeedList = bidSpeedService
				.getByPlanId(bidPlan.getId());
		put("bidSpeedList", bidSpeedList); // 投标策划 进度计划表
		List<AnnexType> annexTypeList = annexTypeService.getByStep("-3", "yes"); //
		put("annexTypeList", annexTypeList);
		bidPlan = bidPlanService.get(bidPlan.getId());
		project = bidPlan.getProject();
		return "showplan";
	}

	/**
	 * 打印
	 * 
	 * @return
	 */
	@Action("print")
	public String print() {
		List<BidMember> bidMemberList = bidMemberService
				.getByPlanId(bidPlan.getId());
		put("bidMemberList", bidMemberList); // 投标策划 成员表
		List<BidSpeed> bidSpeedList = bidSpeedService
				.getByPlanId(bidPlan.getId());
		put("bidSpeedList", bidSpeedList); // 投标策划 进度计划表
		List<AnnexType> annexTypeList = annexTypeService.getByStep("-3", "yes"); //
		put("annexTypeList", annexTypeList);
		bidPlan = bidPlanService.get(bidPlan.getId());
		project = bidPlan.getProject();
		return "printPlan";
	}

	@Action("showImp")
	public String showImp() {// 投标实施
		return "showImp";
	}

	@Action("showSummy")
	public String showSummy() {
		bidSummary = bidSummaryService.get(bidSummary.getId());
		project = bidSummary.getProject();
		List<BidSummaryExpert> bidSummaryExpertList = bidSummaryExpertService
				.getBySummaryId(bidSummary.getId());
		put("bidSummaryExpertList", bidSummaryExpertList); // 评标专家
		List<BidSummaryOpponent> bidSummaryOpponentList = bidSummaryOpponentService
				.getBySummaryId(bidSummary.getId());
		put("bidSummaryOpponentList", bidSummaryOpponentList); // 竞争对手
		List<Annex> annexList = annexService.getByProjectId(project.getId(),
				"-3");
		put("annexList", annexList);
		return "showsummy";
	}

	@Action("printSummy")
	public String printSummy() {
		bidSummary = bidSummaryService.get(bidSummary.getId());
		project = bidSummary.getProject();
		List<BidSummaryExpert> bidSummaryExpertList = bidSummaryExpertService
				.getBySummaryId(bidSummary.getId());
		put("bidSummaryExpertList", bidSummaryExpertList); // 评标专家
		List<BidSummaryOpponent> bidSummaryOpponentList = bidSummaryOpponentService
				.getBySummaryId(bidSummary.getId());
		put("bidSummaryOpponentList", bidSummaryOpponentList); // 竞争对手
		List<Annex> annexList = annexService.getByProjectId(project.getId(),
				"-3");
		put("annexList", annexList);
		return "printSummy";
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@Action("delete")
	public String delete() {
		bidPlan = bidPlanService.get(bidPlan.getId());
		bidPlan.setStatus(-1);
		bidPlanService.update(bidPlan);

		write("1"); // ajax请求用write返回数据
		return null;
	}

	/**
	 * 删除投标管理中的信息 1-项目成员 2-进度计划 3- 竞争对手情况 4-评标专家 5-文件
	 * 
	 * @param id
	 * @return
	 */
	@Action("deletecls")
	public String deletecls() {
		if (types == 1) {
			BidMember bm = bidMemberService.get(Integer.parseInt(bidId));
			bm.setStatus(-1);
			bidMemberService.update(bm);
			write("1"); // ajax请求用write返回数据
		}
		if (types == 2) {
			BidSpeed bs = bidSpeedService.get(Integer.parseInt(bidId));
			bs.setStatus(-1);
			bidSpeedService.update(bs);
			write("1"); // ajax请求用write返回数据
		}
		if (types == 3) {
			BidSummaryOpponent bso = bidSummaryOpponentService
					.get(Integer.parseInt(bidId));
			bso.setStatus(-1);
			bidSummaryOpponentService.update(bso);
			write("1"); // ajax请求用write返回数据
		}
		if (types == 4) {
			BidSummaryExpert bse = bidSummaryExpertService
					.get(Integer.parseInt(bidId));
			bse.setStatus(-1);
			bidSummaryExpertService.update(bse);
			write("1"); // ajax请求用write返回数据
		}
		if (types == 5) {
			Annex annex = annexService.get(Integer.parseInt(bidId));
			annex.setStatus(-1);
			annexService.update(annex);
			write("1"); // ajax请求用write返回数据
		}
		return null;
	}

	@Action("deleteImp")
	public String deleteImp() {// 投标实施
		return null;

	}

	@Action("deleteSummy")
	public String deleteSummy() {
		bidSummary = bidSummaryService.get(bidSummary.getId());
		bidSummary.setStatus(-1);
		bidSummaryService.update(bidSummary);
		write("1"); // ajax请求用write返回数据
		return null;
	}

	@Action("uploadBidSummaryFile")
	public String uploadBidSummaryFile() throws Exception {
		bidSummary = bidSummaryService.get(bidSummary.getId());
		project = bidSummary.getProject();

		String filedir = "bid/" + project.getId() + "/";
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
		annex.setStepName("-3"); // -3代表 投标总结
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
				"-3");
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
			tr.append("-");
			tr.append("[/td]");
			tr.append("[/tr]");
			data.append(tr.toString());
		}
		System.out.println("-----------------------------" + data);
		write(data.toString()); // ajax请求用write
		return null;
	}

	// ====================== getter and setter ===========================

	public BidPlan getBidPlan() {
		return bidPlan;
	}

	public void setBidPlan(BidPlan bidPlan) {
		this.bidPlan = bidPlan;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public BidSummary getBidSummary() {
		return bidSummary;
	}

	public void setBidSummary(BidSummary bidSummary) {
		this.bidSummary = bidSummary;
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

	public BidMember getBidMember() {
		return bidMember;
	}

	public void setBidMember(BidMember bidMember) {
		this.bidMember = bidMember;
	}

	public BidSpeed getBidSpeed() {
		return bidSpeed;
	}

	public void setBidSpeed(BidSpeed bidSpeed) {
		this.bidSpeed = bidSpeed;
	}

	public Integer getAnnexTypeId() {
		return annexTypeId;
	}

	public void setAnnexTypeId(Integer annexTypeId) {
		this.annexTypeId = annexTypeId;
	}

	public BidSummaryOpponent getBidSummaryOpponent() {
		return bidSummaryOpponent;
	}

	public void setBidSummaryOpponent(BidSummaryOpponent bidSummaryOpponent) {
		this.bidSummaryOpponent = bidSummaryOpponent;
	}

	public BidSummaryExpert getBidSummaryExpert() {
		return bidSummaryExpert;
	}

	public void setBidSummaryExpert(BidSummaryExpert bidSummaryExpert) {
		this.bidSummaryExpert = bidSummaryExpert;
	}

	public String getO_name() {
		return o_name;
	}

	public void setO_name(String oName) {
		o_name = oName;
	}

	public String getO_amountType() {
		return o_amountType;
	}

	public void setO_amountType(String oAmountType) {
		o_amountType = oAmountType;
	}

	public String getO_amount() {
		return o_amount;
	}

	public void setO_amount(String oAmount) {
		o_amount = oAmount;
	}

	public String getO_count() {
		return o_count;
	}

	public void setO_count(String oCount) {
		o_count = oCount;
	}

	public String getO_rank() {
		return o_rank;
	}

	public void setO_rank(String oRank) {
		o_rank = oRank;
	}

	public String getO_bidResult() {
		return o_bidResult;
	}

	public void setO_bidResult(String oBidResult) {
		o_bidResult = oBidResult;
	}

	public String getE_name() {
		return e_name;
	}

	public void setE_name(String eName) {
		e_name = eName;
	}

	public String getE_deptName() {
		return e_deptName;
	}

	public void setE_deptName(String eDeptName) {
		e_deptName = eDeptName;
	}

	public String getE_tel() {
		return e_tel;
	}

	public void setE_tel(String eTel) {
		e_tel = eTel;
	}

	public String getE_remark() {
		return e_remark;
	}

	public void setE_remark(String eRemark) {
		e_remark = eRemark;
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

}
