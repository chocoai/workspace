package com.smart.web.action.information;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.model.BidApplyAssess;
import com.smart.model.ProceStepDef;
import com.smart.model.ProjectInfo;
import com.smart.model.ProjectProcessHistory;
import com.smart.model.ProjectProcessState;
import com.smart.model.T_Customer;
import com.smart.model.T_hremployee;
import com.smart.model.User;
import com.smart.service.BidApplyAssessService;
import com.smart.service.ProceStepDefService;
import com.smart.service.ProjectInfoService;
import com.smart.service.ProjectProcessHistoryService;
import com.smart.service.ProjectProcessStateService;
import com.smart.service.ProjectService;
import com.smart.service.ServiceTypeService;
import com.smart.service.T_CustomerService;
import com.smart.service.T_hremployeeService;
import com.smart.service.UserService;
import com.smart.util.Constants;
import com.smart.util.DateUtils;
import com.smart.util.EmailSendTool;
import com.smart.util.Page;
import com.smart.util.ReflectionUtil;
import com.smart.util.StringUtil;
import com.smart.web.action.BaseAction;

@ParentPackage("control-user")
public class InformationAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ProjectInfoService projectinfoservice;

	@Autowired
	private UserService userService;
	
	@Autowired
	private ServiceTypeService serviceTypeService;

	@Autowired
	private T_CustomerService customerService;

	@Autowired
	private T_hremployeeService hremployeeService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private ProjectProcessHistoryService projectProcessHistoryService;

	@Autowired
	private ProjectProcessStateService processStateService;

	@Autowired
	private ProceStepDefService proceStepDefService;

	@Autowired
	private BidApplyAssessService applyAssessService;

	private ProjectInfo proinfo;

	private Date qsj;// 开始时间

	private Date zsj;// 截止时间

	private String id;

	private String roleIdList;// 收集人员id

	private String list;// 收件人name

	@Action("list")
	public String list() {
		proinfo = proinfo == null ? new ProjectInfo() : proinfo;
		Page<ProjectInfo> pageBean = projectinfoservice.getPage(getPageNo(),
				getPageSize(), proinfo.getProname(), proinfo.getBidway());
		put("pageBean", pageBean);
		return "list";
	}

	@Action("new")
	public String _new() {
		return "new";
	}

	@Action("show")
	public String show() {
		proinfo = projectinfoservice.get(proinfo.getId());
		// 信息收集人员
		if (StringUtils.isNotBlank(proinfo.getCollectionmen())) {
			String[] userIds = proinfo.getCollectionmen().split(",");
			StringBuffer userNames = new StringBuffer();
			for (int i = 0; i < userIds.length; i++) {
				if (StringUtils.isBlank(userIds[i]))
					continue;
				User user = userService.get(Integer.parseInt(userIds[i]));
				if (null != user)
					userNames.append(user.getName()).append(",");
			}
			String collectionmen = StringUtils.removeEnd(userNames.toString(),
					",");
			put("collectionmen", collectionmen);
		}
		return "show";
	}

	@Action("print")
	public String print() {
		proinfo = projectinfoservice.get(proinfo.getId());
		// 信息收集人员
		if (StringUtils.isNotBlank(proinfo.getCollectionmen())) {
			String[] userIds = proinfo.getCollectionmen().split(",");
			StringBuffer userNames = new StringBuffer();
			for (int i = 0; i < userIds.length; i++) {
				if (StringUtils.isBlank(userIds[i]))
					continue;
				User user = userService.get(Integer.parseInt(userIds[i]));
				userNames.append(user.getName()).append(",");
			}
			String collectionmen = StringUtils.removeEnd(userNames.toString(),
					",");
			put("collectionmen", collectionmen);
		}
		return "print";
	}

	@Action("saveUser")
	public String saveUser() {// 返回处理多个用户
		// 下面是处理多个用户
		list = "";
		if (roleIdList != null) {
			String[] ids = roleIdList.split(",");
			for (String id : ids) {
				if (StringUtil.isBlank(id)) {
					continue;
				}
				User s = userService.get(Integer.parseInt(id));
				if (list.equals("")) {
					list = s.getName();
				} else {
					list = s.getName() + "," + list;
				}
			}
		}
		if (id != null && !id.equals("")) {
			proinfo = projectinfoservice.get(Integer.parseInt(id));
			proinfo.setCollectionmen(roleIdList);
			projectinfoservice.update(proinfo);
		}

		write(roleIdList + "*" + list);

		return null;
	}

	@Action("edit")
	public String edit() {
		proinfo = projectinfoservice.get(proinfo.getId());
		// 信息收集人员
		if (StringUtils.isNotBlank(proinfo.getCollectionmen())) {
			String[] userIds = proinfo.getCollectionmen().split(",");
			StringBuffer userNames = new StringBuffer();
			for (int i = 0; i < userIds.length; i++) {
				if (StringUtils.isBlank(userIds[i]))
					continue;
				User user = userService.get(Integer.parseInt(userIds[i]));
				userNames.append(user.getName()).append(",");
			}
			String collectionmenName = StringUtils
					.removeEnd(userNames.toString(), ",");
			put("collectionmenName", collectionmenName);
		}
		return "new";
	}

	@Action(value = "save", results = {
			@Result(name = "list", location = "list", type = "redirectAction") })
	public String save() {
		proinfoSave();
		return "list";
	}

	/**
	 * 项目信息保存或提交预处理
	 */
	public void proinfoSave() {
		String bidMenId = this.get("bidMenId");
		if (StringUtils.isNotBlank(bidMenId)) {
			T_Customer customer = customerService
					.get(Integer.valueOf(bidMenId));
			proinfo.setBidmen(customer);
		}
		if (null == proinfo.getId()) {
			Calendar year = Calendar.getInstance();
			StringBuffer temp = new StringBuffer();
			if (proinfo.getServiceType() != null) {// 根据项目信息咨询类型给project.no编号
				if (projectService.getProjectLast() == null) {
					temp.append("DZ").append("ZJ")
							.append(Integer.toString(year.get(Calendar.YEAR)))
							.append(String.format("%04d", 0001));
				} else {
					if (proinfo.getServiceType().getId() == 1) {
						temp.append("DZ").append("GZ")
								.append(Integer
										.toString(year.get(Calendar.YEAR)))
								.append(String
										.format("%04d",
												(Integer.valueOf(projectService
														.getProjectLast()
														.substring(8, 12))
														+ 1)));
					}
					if (proinfo.getServiceType().getId() == 2) {
						temp.append("DZ").append("PPP")
								.append(Integer
										.toString(year.get(Calendar.YEAR)))
								.append(String.format("%04d", (Integer
										.valueOf(projectService.getProjectLast()
												.substring(
														projectService
																.getProjectLast()
																.length() - 4,
														projectService
																.getProjectLast()
																.length()))
										+ 1)));
					}
					if (proinfo.getServiceType().getId() == 3) {
						temp.append("DZ").append("ZHB")
								.append(Integer
										.toString(year.get(Calendar.YEAR)))
								.append(String.format("%04d", (Integer
										.valueOf(projectService.getProjectLast()
												.substring(
														projectService
																.getProjectLast()
																.length() - 4,
														projectService
																.getProjectLast()
																.length()))
										+ 1)));
					}
					if (proinfo.getServiceType().getId() == 4) {
						temp.append("DZ").append("ZFCG")
								.append(Integer
										.toString(year.get(Calendar.YEAR)))
								.append(String.format("%04d", (Integer
										.valueOf(projectService.getProjectLast()
												.substring(
														projectService
																.getProjectLast()
																.length() - 4,
														projectService
																.getProjectLast()
																.length()))
										+ 1)));
					}
					if (proinfo.getServiceType().getId() == 5) {
						temp.append("DZ").append("ZJ")
								.append(Integer
										.toString(year.get(Calendar.YEAR)))
								.append(String.format("%04d", (Integer
										.valueOf(projectService.getProjectLast()
												.substring(
														projectService
																.getProjectLast()
																.length() - 4,
														projectService
																.getProjectLast()
																.length()))
										+ 1)));
					}
					if (proinfo.getServiceType().getId() == 6) {
						temp.append("DZ").append("SJ")
								.append(Integer
										.toString(year.get(Calendar.YEAR)))
								.append(String.format("%04d", (Integer
										.valueOf(projectService.getProjectLast()
												.substring(
														projectService
																.getProjectLast()
																.length() - 4,
														projectService
																.getProjectLast()
																.length()))
										+ 1)));
					}
				}
			}
			proinfo.setProNo(temp.toString());
			proinfo.setStatus(1);
			proinfo.setCtime(new Date());
			proinfo.setOperationmen(this.getUser().getName());
			projectinfoservice.save(proinfo);
		} else {
			ProjectInfo oldprojectinfo = projectinfoservice
					.get(proinfo.getId());
			proinfo.setCtime(new Date());
			proinfo.setOperationmen(this.getUser().getName());
			ReflectionUtil.bean2Bean(oldprojectinfo, proinfo, "id,status");
			projectinfoservice.update(oldprojectinfo);
		}
	}

	@Action(value = "nextStep", results = {
			@Result(name = "list", location = "list", type = "redirectAction") })
	public String toNextStep() {
		proinfoSave();
		ProceStepDef currentStep = new ProceStepDef();
		if (proinfo.getReceiveType() == 1) {
			ProjectInfo oldprojectinfo = projectinfoservice.get(proinfo.getId());
			proinfo.setCtime(new Date());
			proinfo.setOperationmen(this.getUser().getName());
			proinfo.setStatus(0);
			ReflectionUtil.bean2Bean(oldprojectinfo, proinfo, "id");
			projectinfoservice.update(oldprojectinfo);
			
			currentStep = proceStepDefService.getStepByStepCode(Constants.StepCode.BID_APPLY);
			ProjectProcessState pps = new ProjectProcessState();
			pps.setProjectInfo(proinfo);
			pps.setCurrentStep(currentStep);
			pps.setCurrentState(ProjectProcessState.IN_PROGRESS);
			pps.setCurrentUsers(proinfo.getNextOperatorId());
			pps.setLastUpdateTime(DateUtils.getCurrentTime());
			ProjectProcessState pps2 = processStateService.getOneByProjectInfoId(proinfo.getId());
			if(pps2 ==null ){
				processStateService.save(pps);
			}else{
				pps2.setProjectInfo(proinfo);
				pps2.setCurrentStep(currentStep);
				pps2.setCurrentState(ProjectProcessState.IN_PROGRESS);
				pps2.setCurrentUsers(proinfo.getNextOperatorId());
				pps2.setLastUpdateTime(DateUtils.getCurrentTime());
				processStateService.update(pps2);
			}
			

			// 更新项目进程处理历史记录表
			ProjectProcessHistory pph = new ProjectProcessHistory();
			pph.setProjectInfo(proinfo);
			pph.setOperateUser(getUser());
			pph.setOperateTime(DateUtils.getCurrentTime());
			pph.setOperateType(Constants.OperateType.SUBMIT);
			pph.setNextStep(currentStep);
			pph.setNextHandlers(proinfo.getNextOperatorId());
			projectProcessHistoryService.save(pph);

			// 报名
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

			BidApplyAssess applyAssess = new BidApplyAssess();
			applyAssess.setProjectInfo(proinfo);
			applyAssess.setCustomer(proinfo.getBidmen());
			applyAssess.setConsultType(
					serviceTypeService.get(proinfo.getServiceType().getId()).getName());
			applyAssess.setCallBidFile(proinfo.getBidfile());
			applyAssess.setAgentCompany(proinfo.getAgentcompany());
			applyAssess.setApplyDate(sdf.format(proinfo.getQsj()) + "至"
					+ sdf.format(proinfo.getZsj()));
			applyAssess.setBidOpenDate(sdf.format(proinfo.getOpentime()));
			applyAssess.setConsultFee(proinfo.getZixungusuan());
			applyAssess.setSignupCity(proinfo.getSigncity());
			applyAssess.setCreateTime(sdf.format(new Date()));
			applyAssess.setStatus(1);
			applyAssessService.save(applyAssess);
		}
		if (proinfo.getReceiveType() == 0) {
			currentStep = proceStepDefService.getStepByStepCode(Constants.StepCode.START_UP_PROJECT);
			ProjectProcessState pps = new ProjectProcessState();
			pps.setProjectInfo(proinfo);
			pps.setCurrentStep(currentStep);
			pps.setCurrentState(ProjectProcessState.IN_PROGRESS);
			pps.setCurrentUsers(proinfo.getNextOperatorId());
			pps.setLastUpdateTime(DateUtils.getCurrentTime());
			ProjectProcessState pps2 = processStateService.getOneByProjectInfoId(proinfo.getId());
			if(pps2 ==null ){
				processStateService.save(pps);
			}else{
				pps2.setProjectInfo(proinfo);
				pps2.setCurrentStep(currentStep);
				pps2.setCurrentState(ProjectProcessState.IN_PROGRESS);
				pps2.setCurrentUsers(proinfo.getNextOperatorId());
				pps2.setLastUpdateTime(DateUtils.getCurrentTime());
				processStateService.update(pps2);
			}

			// 更新项目进程处理历史记录表
			ProjectProcessHistory pph = new ProjectProcessHistory();
			pph.setProjectInfo(proinfo);
			pph.setOperateUser(getUser());
			pph.setOperateTime(DateUtils.getCurrentTime());
			pph.setOperateType(Constants.OperateType.SUBMIT);
			pph.setNextStep(currentStep);
			pph.setNextHandlers(proinfo.getNextOperatorId());
			projectProcessHistoryService.save(pph);
		}

		// 发送邮件通知下一步处理人有待办事项需要处理
		// 查询下一步处理人的邮箱地址
		StringBuilder emailAddress = new StringBuilder();
		if (StringUtils.isNotBlank(proinfo.getNextOperatorId())) {
			String[] userIds = proinfo.getNextOperatorId().split(",");
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
			String emailContent = sendEmail.buildContent(proinfo.getProNo(),
					proinfo.getProname(), currentStep.getStepName());
			sendEmail.send(emailAddress.toString(), subject, emailContent);
		}
		return "list";
	}

	@Action("delete")
	public String delete() {
		projectinfoservice.delete(proinfo.getId());
		write("1");
		return null;
	}

	public ProjectInfo getProinfo() {
		return proinfo;
	}

	public void setProinfo(ProjectInfo proinfo) {
		this.proinfo = proinfo;
	}

	public Date getQsj() {
		return qsj;
	}

	public void setQsj(Date qsj) {
		this.qsj = qsj;
	}

	public Date getZsj() {
		return zsj;
	}

	public void setZsj(Date zsj) {
		this.zsj = zsj;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(String roleIdList) {
		this.roleIdList = roleIdList;
	}

	public String getList() {
		return list;
	}

	public void setList(String list) {
		this.list = list;
	}

}
