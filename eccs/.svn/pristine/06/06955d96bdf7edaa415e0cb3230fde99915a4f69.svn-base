/**
 * 
 */
package com.smart.web.action.bid.bond;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.model.BidApplyAssess;
import com.smart.model.BidBondAssess;
import com.smart.model.BidFileAssess;
import com.smart.model.CallBidAssess;
import com.smart.model.ProceStepDef;
import com.smart.model.ProjectInfo;
import com.smart.model.ProjectProcessHistory;
import com.smart.model.ProjectProcessState;
import com.smart.model.T_Customer;
import com.smart.model.T_hremployee;
import com.smart.model.User;
import com.smart.service.BidApplyAssessService;
import com.smart.service.BidBondAssessService;
import com.smart.service.BidFileAssessService;
import com.smart.service.CallBidAssessService;
import com.smart.service.ProceStepDefService;
import com.smart.service.ProjectInfoService;
import com.smart.service.ProjectProcessHistoryService;
import com.smart.service.ProjectProcessStateService;
import com.smart.service.T_hremployeeService;
import com.smart.service.UserService;
import com.smart.util.Constants;
import com.smart.util.DateUtils;
import com.smart.util.EmailSendTool;
import com.smart.util.Page;
import com.smart.util.ReflectionUtil;
import com.smart.web.action.BaseAction;

/**  
* @Description:投标保证金模块
* @author hexiang
* @date 2017年7月5日 下午5:38:21
*/
@ParentPackage("control-user")
public class BidBondAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private BidBondAssessService bidBondAssessService;
	
	@Autowired
	private ProjectProcessStateService processStateService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProjectInfoService projectInfoService;
	
	@Autowired
	private BidApplyAssessService bidApplyAssessService;
	
	@Autowired
	private CallBidAssessService callBidAssessService;
	
	@Autowired
	private BidFileAssessService bidFileAssessService;

	@Autowired
	private ProceStepDefService proceStepDefService;
	
	@Autowired
	private T_hremployeeService hremployeeService;

	@Autowired
	private ProjectProcessHistoryService projectProcessHistoryService;
	
	private ProjectInfo projectInfo; // 项目
	
	private BidBondAssess bondAssess;
	
	private String nextOperatorId;

	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");  

	@Action("list")
	public String list(){
		projectInfo = projectInfo == null ? new ProjectInfo() : projectInfo;
		bondAssess = bondAssess == null ? new BidBondAssess() : bondAssess;
		T_Customer customer = projectInfo.getBidmen() == null?new T_Customer() : projectInfo.getBidmen();
		Page<BidBondAssess> pageBean = bidBondAssessService.getPage(pageNo, pageSize,
				customer.getCusName(), projectInfo.getProname(),projectInfo.getAgentcompany());
		for (int i = 0; i < pageBean.getList().size(); i++) {
			BidBondAssess assess = pageBean.getList().get(i);
			ProjectInfo proj = assess.getProjectInfo();
			ProjectProcessState pps = processStateService
					.getOneByProjectInfoIdAndStepCode(proj.getId(),
							Constants.StepCode.BID_BOND);
			//是否可以进行编辑操作，当项目已经进行到下一步时，此处为false即不能操作
			if (pps != null) {
				String[] handlers = pps.getCurrentUsers().split(",");
				List<String> userList = Arrays.asList(handlers);
				if (userList.contains(getUser().getId().toString())) {
					assess.setResStates(true);
				}
			} else {
				assess.setResStates(false);
				pps = processStateService
						.getOneByProjectInfoIdAndStepCode(proj.getId(),
								Constants.StepCode.BID_BOND_CHECK);
				//是否可以进行审核操作，当项目已经进行到下一步时，此处为false即不能操作
				if(null != pps){
					String[] handlers = pps.getCurrentUsers().split(",");
					List<String> userList = Arrays.asList(handlers);
					if (userList.contains(getUser().getId().toString())) {
						assess.setCheckStates(true);
					}
				}else{
					assess.setCheckStates(false);
				}
			}
		}
		put("pageBean", pageBean);
		put("bondAssess", bondAssess);
		put("projectInfo", projectInfo);
		return "list";
	}
	
	@Action("edit")
	public String edit(){
		bondAssess = bidBondAssessService.getBondAssessByProjectInfo(projectInfo);
		projectInfo = bondAssess.getProjectInfo();
		if (StringUtils.isNotBlank(projectInfo.getNextOperatorId())) {
			String[] userIds = projectInfo.getNextOperatorId().split(",");
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
			put("nextOperatorId",projectInfo.getNextOperatorId());
		}
		put("bondAssess",bondAssess);
		return "edit";
	}

	@Action("check")
	public String check(){
		bondAssess = bidBondAssessService.getBondAssessByProjectInfo(projectInfo);
		projectInfo = bondAssess.getProjectInfo();
		if (StringUtils.isNotBlank(projectInfo.getNextOperatorId())) {
			String[] userIds = projectInfo.getNextOperatorId().split(",");
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
			put("nextOperatorId",projectInfo.getNextOperatorId());
		}
		put("bondAssess",bondAssess);
		return "check";
	}
	
	@Action("show")
	public String show(){
		bondAssess = bidBondAssessService.getBondAssessByProjectInfo(projectInfo);
		put("bondAssess",bondAssess);
		return "show";
	}

	@Action(value = "save", results = {
			@Result(name = "list", location = "list", type = "redirectAction") })
	public String save(){
		
		BidBondAssess oldbondAssess = bidBondAssessService.get(bondAssess.getCid());
		projectInfo = oldbondAssess.getProjectInfo();
		
		projectInfo.setNextOperatorId(nextOperatorId);
		
		projectInfoService.update(projectInfo);
		
		ProjectProcessState pps = processStateService
				.getOneByProjectInfoIdAndStepCode(projectInfo.getId(),
						Constants.StepCode.BID_BOND);
		// 更新项目进程处理历史记录表
		ProceStepDef currentStep = proceStepDefService
				.getStepByStepCode(Constants.StepCode.BID_BOND);
		ProjectProcessHistory pph = new ProjectProcessHistory();
		pph.setProjectInfo(projectInfo);
		pph.setOperateStep(currentStep);
		pph.setOperateUser(getUser());
		pph.setOperateTime(DateUtils.getCurrentTime());
		pph.setOperateType(Constants.OperateType.SAVE);
		pph.setNextStep(currentStep);
		pph.setNextHandlers(pps.getCurrentUsers());
		projectProcessHistoryService.save(pph);

		// 属性拷贝，把更新内容拷贝到数据库查出的对象里，第三个参数表示不需要拷贝的属性名称
		ReflectionUtil.bean2Bean(oldbondAssess, bondAssess,
				"cid,applyNo,status,customer,projectInfo,createTime,remark");
		bidBondAssessService.update(oldbondAssess);
		return "list";
	}
	
	@Action(value = "save2", results = {
			@Result(name = "list", location = "list", type = "redirectAction") })
	public String save2(){
		
		BidBondAssess oldbondAssess = bidBondAssessService.get(bondAssess.getCid());
		projectInfo = oldbondAssess.getProjectInfo();
		
		projectInfo.setNextOperatorId(nextOperatorId);
		
		projectInfoService.update(projectInfo);
		
		ProjectProcessState pps = processStateService
				.getOneByProjectInfoIdAndStepCode(projectInfo.getId(),
						Constants.StepCode.BID_BOND_CHECK);
		// 更新项目进程处理历史记录表
		ProceStepDef currentStep = proceStepDefService
				.getStepByStepCode(Constants.StepCode.BID_BOND_CHECK);
		ProjectProcessHistory pph = new ProjectProcessHistory();
		pph.setProjectInfo(projectInfo);
		pph.setOperateStep(currentStep);
		pph.setOperateUser(getUser());
		pph.setOperateTime(DateUtils.getCurrentTime());
		pph.setOperateType(Constants.OperateType.SAVE);
		pph.setNextStep(currentStep);
		pph.setNextHandlers(pps.getCurrentUsers());
		projectProcessHistoryService.save(pph);
		
		oldbondAssess.setAuditOpinion(bondAssess.getAuditOpinion());
		oldbondAssess.setFinanceHandleSituation(bondAssess.getFinanceHandleSituation());
		bidBondAssessService.update(oldbondAssess);
		return "list";
	}

	@Action(value = "toNextStep", results = {
			@Result(name = "list", location = "list", type = "redirectAction") })
	public String toNextStep(){
		
		BidBondAssess oldbondAssess = bidBondAssessService.get(bondAssess.getCid());
		projectInfo = oldbondAssess.getProjectInfo();
		
		projectInfo.setNextOperatorId("");
		
		projectInfoService.update(projectInfo);
		
		// 下一环节处理人
		String nextOperatorId = get("nextOperatorId");
	
		ProceStepDef nextStep = proceStepDefService
				.getStepByStepCode(Constants.StepCode.BID_BOND_CHECK);
		// 更新项目进程状态表信息
		ProjectProcessState pps = processStateService
				.getOneByProjectInfoIdAndStepCode(projectInfo.getId(),
						Constants.StepCode.BID_BOND);
		pps.setCurrentStep(nextStep);
		pps.setCurrentUsers(nextOperatorId);
		pps.setCurrentState(ProjectProcessState.IN_PROGRESS);
		pps.setLastUpdateTime(DateUtils.getCurrentTime());
		processStateService.update(pps);
		// 更新项目进程处理历史记录表
		ProjectProcessHistory pph = new ProjectProcessHistory();
		pph.setProjectInfo(projectInfo);
		pph.setOperateStep(proceStepDefService
				.getStepByStepCode(Constants.StepCode.BID_BOND));
		pph.setOperateUser(getUser());
		pph.setOperateTime(DateUtils.getCurrentTime());
		pph.setOperateType(Constants.OperateType.SUBMIT);
		pph.setNextStep(nextStep);
		pph.setNextHandlers(nextOperatorId);
		projectProcessHistoryService.save(pph);

		// 属性拷贝，把更新内容拷贝到数据库查出的对象里，第三个参数表示不需要拷贝的属性名称
		ReflectionUtil.bean2Bean(oldbondAssess, bondAssess,
				"cid,applyNo,status,customer,projectInfo,createTime,remark");
		bidBondAssessService.update(oldbondAssess);
		//没有项目编号先空着
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
			String emailContent = sendEmail.buildContent(projectInfo.getProNo(), projectInfo.getProname(), nextStep.getStepName());
			sendEmail.send(emailAddress.toString(), subject, emailContent);
		}
		return "list";
	}
	
	@Action(value = "toNextStep2", results = {
			@Result(name = "list", location = "list", type = "redirectAction") })
	public String toNextStep2(){
		
		BidBondAssess oldbondAssess = bidBondAssessService.get(bondAssess.getCid());
		projectInfo = oldbondAssess.getProjectInfo();
		
		projectInfo.setNextOperatorId("");
		
		projectInfoService.update(projectInfo);
		
		// 下一环节处理人
		String nextOperatorId = get("nextOperatorId");
		
		ProceStepDef nextStep = proceStepDefService
				.getStepByStepCode(Constants.StepCode.BID_FILE);
		// 更新项目进程状态表信息
		ProjectProcessState pps = processStateService
				.getOneByProjectInfoIdAndStepCode(projectInfo.getId(),
						Constants.StepCode.BID_BOND_CHECK);
		pps.setCurrentStep(nextStep);
		pps.setCurrentUsers(nextOperatorId);
		pps.setCurrentState(ProjectProcessState.IN_PROGRESS);
		pps.setLastUpdateTime(DateUtils.getCurrentTime());
		processStateService.update(pps);
		// 更新项目进程处理历史记录表
		ProjectProcessHistory pph = new ProjectProcessHistory();
		pph.setProjectInfo(projectInfo);
		pph.setOperateStep(proceStepDefService
				.getStepByStepCode(Constants.StepCode.BID_BOND_CHECK));
		pph.setOperateUser(getUser());
		pph.setOperateTime(DateUtils.getCurrentTime());
		pph.setOperateType(Constants.OperateType.SUBMIT);
		pph.setNextStep(nextStep);
		pph.setNextHandlers(nextOperatorId);
		projectProcessHistoryService.save(pph);

		oldbondAssess.setAuditOpinion(bondAssess.getAuditOpinion());
		oldbondAssess.setFinanceHandleSituation(bondAssess.getFinanceHandleSituation());
		bidBondAssessService.update(oldbondAssess);
		
		BidApplyAssess applyAssess = bidApplyAssessService.getApplyAssessByProjectInfo(projectInfo);
		CallBidAssess callAssess = callBidAssessService.getCallAssessByProjectInfo(projectInfo);
		
		BidFileAssess fileAssess = new BidFileAssess();
		fileAssess.setBidOpenDate(sdf.format(projectInfo.getOpentime()));
		fileAssess.setConsultType(applyAssess.getConsultType());
		fileAssess.setCreateTime(DateUtils.getCurrentTime());
		fileAssess.setCustomer(oldbondAssess.getCustomer());
		fileAssess.setProjectInfo(projectInfo);
		fileAssess.setStatus(1);
		fileAssess.setResponsiblePerson(callAssess.getResponsiblePerson());
		bidFileAssessService.save(fileAssess);
		
		//没有项目编号先空着
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
			String emailContent = sendEmail.buildContent(projectInfo.getProNo(), projectInfo.getProname(), nextStep.getStepName());
			sendEmail.send(emailAddress.toString(), subject, emailContent);
		}
		return "list";
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@Action("delete")
	public String delete() {
		bondAssess = bidBondAssessService.get(bondAssess.getCid());
		bondAssess.setStatus(0);
		bidBondAssessService.update(bondAssess);
		// 更新项目进程状态表信息
		ProjectProcessState pps = processStateService
				.getOneByProjectInfoIdAndStepCode(projectInfo.getId(),
						Constants.StepCode.BID_BOND);
		pps.setCurrentStep(proceStepDefService
				.getStepByStepCode(Constants.StepCode.STOPED));
		pps.setCurrentUsers("");
		pps.setCurrentState(ProjectProcessState.STOPED);
		processStateService.update(pps);
		// 更新项目进程处理历史记录表
		ProjectProcessHistory pph = new ProjectProcessHistory();
		pph.setProjectInfo(projectInfo);
		pph.setOperateStep(proceStepDefService
				.getStepByStepCode(Constants.StepCode.BID_BOND));
		pph.setOperateUser(getUser());
		pph.setOperateTime(DateUtils.getCurrentTime());
		pph.setOperateType(Constants.OperateType.TERMINATE);
		pph.setNextStep(null);
		pph.setNextHandlers("");
		projectProcessHistoryService.save(pph);
		write("1"); // ajax请求用write返回数据
		return null;
	}
	
	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}

	public BidBondAssess getBondAssess() {
		return bondAssess;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	public void setBondAssess(BidBondAssess bondAssess) {
		this.bondAssess = bondAssess;
	}
	
	public String getNextOperatorId() {
		return nextOperatorId;
	}

	public void setNextOperatorId(String nextOperatorId) {
		this.nextOperatorId = nextOperatorId;
	}

}
