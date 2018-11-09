/**
 * 
 */
package com.smart.web.action.bid.apply;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.model.BidApplyAssess;
import com.smart.model.CallBidAssess;
import com.smart.model.ProceStepDef;
import com.smart.model.ProjectInfo;
import com.smart.model.ProjectProcessHistory;
import com.smart.model.ProjectProcessState;
import com.smart.model.T_Customer;
import com.smart.model.T_hremployee;
import com.smart.model.User;
import com.smart.service.BidApplyAssessService;
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
import com.smart.web.action.BaseAction;

/**  
* @Description:报名评估模块
* @author hexiang
* @date 2017年7月5日 上午10:02:58
*/
@ParentPackage("control-user")
public class BidApplyAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private BidApplyAssessService bidApplyAssessService;

	@Autowired
	private ProceStepDefService proceStepDefService;
	
	@Autowired
	private ProjectProcessStateService processStateService;

	@Autowired
	private ProjectProcessHistoryService projectProcessHistoryService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProjectInfoService projectInfoService;
	
	@Autowired
	private T_hremployeeService hremployeeService;
	
	@Autowired
	private CallBidAssessService callBidAssessService;

	private ProjectInfo projectInfo; // 项目
	
	private BidApplyAssess applyAssess;//报名评估
	
	private String nextOperatorId;

	@Action("list")
	public String list(){
		projectInfo = projectInfo == null ? new ProjectInfo() : projectInfo;
		applyAssess = applyAssess == null ? new BidApplyAssess() : applyAssess;
		T_Customer customer = projectInfo.getBidmen() == null?new T_Customer() : projectInfo.getBidmen();
		Page<BidApplyAssess> pageBean = bidApplyAssessService.getPage(pageNo, pageSize,
				customer.getCusName(), projectInfo.getProname(), applyAssess.getAgentCompany());
		for (int i = 0; i < pageBean.getList().size(); i++) {
			BidApplyAssess assess = pageBean.getList().get(i);
			ProjectInfo proj = assess.getProjectInfo();
			ProjectProcessState pps = processStateService
					.getOneByProjectInfoIdAndStepCode(proj.getId(),
							Constants.StepCode.BID_APPLY);
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
								Constants.StepCode.BID_APPLY_CASE);
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
		put("applyAssess", applyAssess);
		put("projectInfo", projectInfo);
		return "list";
	}
	
	@Action("edit")
	public String edit(){
		applyAssess = bidApplyAssessService.getApplyAssessByProjectInfo(projectInfo);
		projectInfo = applyAssess.getProjectInfo();
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
		put("applyAssess",applyAssess);
		return "edit";
	}
	
	@Action("check")
	public String check(){
		applyAssess = bidApplyAssessService.getApplyAssessByProjectInfo(projectInfo);
		projectInfo = applyAssess.getProjectInfo();
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
		put("applyAssess",applyAssess);
		return "check";
	}
	
	@Action("show")
	public String show(){
		applyAssess = bidApplyAssessService.getApplyAssessByProjectInfo(projectInfo);
		put("applyAssess",applyAssess);
		return "show";
	}

	@Action(value = "save", results = {
			@Result(name = "list", location = "list", type = "redirectAction") })
	public String save(){
		
		BidApplyAssess oldapplyAssess = bidApplyAssessService.get(applyAssess.getCid());
		projectInfo = oldapplyAssess.getProjectInfo();
		
		projectInfo.setNextOperatorId(nextOperatorId);
		
		projectInfoService.update(projectInfo);
		
		ProjectProcessState pps = processStateService
				.getOneByProjectInfoIdAndStepCode(projectInfo.getId(),
						Constants.StepCode.BID_APPLY);
		// 更新项目进程处理历史记录表
		ProceStepDef currentStep = proceStepDefService
				.getStepByStepCode(Constants.StepCode.BID_APPLY);
		ProjectProcessHistory pph = new ProjectProcessHistory();
		pph.setProjectInfo(projectInfo);
		pph.setOperateStep(currentStep);
		pph.setOperateUser(getUser());
		pph.setOperateTime(DateUtils.getCurrentTime());
		pph.setOperateType(Constants.OperateType.SAVE);
		pph.setNextStep(currentStep);
		pph.setNextHandlers(pps.getCurrentUsers());
		projectProcessHistoryService.save(pph);
		
		oldapplyAssess.setBidPkg(applyAssess.getBidPkg());
		oldapplyAssess.setAssessResult(applyAssess.getAssessResult());
		oldapplyAssess.setAppraisalnotes(applyAssess.getAppraisalnotes());
		bidApplyAssessService.update(oldapplyAssess);
		return "list";
	}
	
	@Action(value = "save2", results = {
			@Result(name = "list", location = "list", type = "redirectAction") })
	public String save2(){
		
		BidApplyAssess oldapplyAssess = bidApplyAssessService.get(applyAssess.getCid());
		projectInfo = oldapplyAssess.getProjectInfo();
		
		projectInfo.setNextOperatorId(nextOperatorId);
		
		projectInfoService.update(projectInfo);
		
		ProjectProcessState pps = processStateService
				.getOneByProjectInfoIdAndStepCode(projectInfo.getId(),
						Constants.StepCode.BID_APPLY_CASE);
		// 更新项目进程处理历史记录表
		ProceStepDef currentStep = proceStepDefService
				.getStepByStepCode(Constants.StepCode.BID_APPLY_CASE);
		ProjectProcessHistory pph = new ProjectProcessHistory();
		pph.setProjectInfo(projectInfo);
		pph.setOperateStep(currentStep);
		pph.setOperateUser(getUser());
		pph.setOperateTime(DateUtils.getCurrentTime());
		pph.setOperateType(Constants.OperateType.SAVE);
		pph.setNextStep(currentStep);
		pph.setNextHandlers(pps.getCurrentUsers());
		projectProcessHistoryService.save(pph);
		
		oldapplyAssess.setCompleteStatus(applyAssess.getCompleteStatus());
		oldapplyAssess.setGetFileDate(applyAssess.getGetFileDate());
		oldapplyAssess.setRemark(applyAssess.getRemark());
		bidApplyAssessService.update(oldapplyAssess);
		return "list";
	}
	
	
	@Action(value = "toNextStep", results = {
			@Result(name = "list", location = "list", type = "redirectAction") })
	public String toNextStep(){
		
	    BidApplyAssess oldapplyAssess = bidApplyAssessService.get(applyAssess.getCid());
		projectInfo = oldapplyAssess.getProjectInfo();
		
		projectInfo.setNextOperatorId("");
		
		projectInfoService.update(projectInfo);
	
		// 下一环节处理人
		String nextOperatorId = get("nextOperatorId");
		// 更新项目进程状态表信息
		ProjectProcessState pps = processStateService
				.getOneByProjectInfoIdAndStepCode(projectInfo.getId(),
						Constants.StepCode.BID_APPLY);
		if(1!=applyAssess.getAssessResult()){
			pps.setCurrentStep(proceStepDefService
					.getStepByStepCode(Constants.StepCode.STOPED));
			pps.setCurrentUsers("");
			pps.setCurrentState(ProjectProcessState.STOPED);
			processStateService.update(pps);
			// 更新项目进程处理历史记录表
			ProjectProcessHistory pph = new ProjectProcessHistory();
			pph.setProjectInfo(projectInfo);
			pph.setOperateStep(proceStepDefService
					.getStepByStepCode(Constants.StepCode.BID_APPLY));
			pph.setOperateUser(getUser());
			pph.setOperateTime(DateUtils.getCurrentTime());
			pph.setOperateType(Constants.OperateType.TERMINATE);
			pph.setNextStep(null);
			pph.setNextHandlers("");
			projectProcessHistoryService.save(pph);
			
			oldapplyAssess.setBidPkg(applyAssess.getBidPkg());
			oldapplyAssess.setAssessResult(applyAssess.getAssessResult());
			oldapplyAssess.setAppraisalnotes(applyAssess.getAppraisalnotes());
			bidApplyAssessService.update(oldapplyAssess);
		}else{
			ProceStepDef nextStep = proceStepDefService
					.getStepByStepCode(Constants.StepCode.BID_APPLY_CASE);
			pps.setCurrentStep(nextStep);
			pps.setCurrentUsers(nextOperatorId);
			pps.setCurrentState(ProjectProcessState.IN_PROGRESS);
			pps.setLastUpdateTime(DateUtils.getCurrentTime());
			processStateService.update(pps);
			// 更新项目进程处理历史记录表
			ProjectProcessHistory pph = new ProjectProcessHistory();
			pph.setProjectInfo(projectInfo);
			pph.setOperateStep(proceStepDefService
					.getStepByStepCode(Constants.StepCode.BID_APPLY));
			pph.setOperateUser(getUser());
			pph.setOperateTime(DateUtils.getCurrentTime());
			pph.setOperateType(Constants.OperateType.SUBMIT);
			pph.setNextStep(nextStep);
			pph.setNextHandlers(nextOperatorId);
			projectProcessHistoryService.save(pph);

			
			oldapplyAssess.setBidPkg(applyAssess.getBidPkg());
			oldapplyAssess.setAssessResult(applyAssess.getAssessResult());
			oldapplyAssess.setAppraisalnotes(applyAssess.getAppraisalnotes());
			bidApplyAssessService.update(oldapplyAssess);
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
		}
		return "list";
	}
	
	@Action(value = "toNextStep2", results = {
			@Result(name = "list", location = "list", type = "redirectAction") })
	public String toNextStep2(){
		
		BidApplyAssess oldapplyAssess = bidApplyAssessService.get(applyAssess.getCid());
		projectInfo = oldapplyAssess.getProjectInfo();
		
		projectInfo.setNextOperatorId("");
		
		projectInfoService.update(projectInfo);
		
		// 下一环节处理人
		String nextOperatorId = get("nextOperatorId");
		
		ProceStepDef nextStep = proceStepDefService
				.getStepByStepCode(Constants.StepCode.BID_CALL);
		// 更新项目进程状态表信息
		ProjectProcessState pps = processStateService
				.getOneByProjectInfoIdAndStepCode(projectInfo.getId(),
						Constants.StepCode.BID_APPLY_CASE);
		pps.setCurrentStep(nextStep);
		pps.setCurrentUsers(nextOperatorId);
		pps.setCurrentState(ProjectProcessState.IN_PROGRESS);
		processStateService.update(pps);
		// 更新项目进程处理历史记录表
		ProjectProcessHistory pph = new ProjectProcessHistory();
		pph.setProjectInfo(projectInfo);
		pph.setOperateStep(proceStepDefService
				.getStepByStepCode(Constants.StepCode.BID_APPLY_CASE));
		pph.setOperateUser(getUser());
		pph.setOperateTime(DateUtils.getCurrentTime());
		pph.setOperateType(Constants.OperateType.SUBMIT);
		pph.setNextStep(nextStep);
		pph.setNextHandlers(nextOperatorId);
		projectProcessHistoryService.save(pph);

		oldapplyAssess.setCompleteStatus(applyAssess.getCompleteStatus());
		oldapplyAssess.setGetFileDate(applyAssess.getGetFileDate());
		oldapplyAssess.setRemark(applyAssess.getRemark());
		bidApplyAssessService.update(oldapplyAssess);
		
		CallBidAssess callAssess = new CallBidAssess();
	    callAssess.setProjectInfo(projectInfo);
	    callAssess.setConsultType(applyAssess.getConsultType());
	    callAssess.setCreateTime(DateUtils.getCurrentTime());
	    callAssess.setStatus(1);
	    callBidAssessService.save(callAssess);
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
		applyAssess = bidApplyAssessService.get(applyAssess.getCid());
		applyAssess.setStatus(0);
		bidApplyAssessService.update(applyAssess);
		// 更新项目进程状态表信息
		ProjectProcessState pps = processStateService
				.getOneByProjectInfoIdAndStepCode(projectInfo.getId(),
						Constants.StepCode.BID_APPLY);
		pps.setCurrentStep(proceStepDefService
				.getStepByStepCode(Constants.StepCode.STOPED));
		pps.setCurrentUsers("");
		pps.setCurrentState(ProjectProcessState.STOPED);
		processStateService.update(pps);
		// 更新项目进程处理历史记录表
		ProjectProcessHistory pph = new ProjectProcessHistory();
		pph.setProjectInfo(projectInfo);
		pph.setOperateStep(proceStepDefService
				.getStepByStepCode(Constants.StepCode.BID_APPLY));
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

	public BidApplyAssess getApplyAssess() {
		return applyAssess;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	public void setApplyAssess(BidApplyAssess applyAssess) {
		this.applyAssess = applyAssess;
	}

	public String getNextOperatorId() {
		return nextOperatorId;
	}

	public void setNextOperatorId(String nextOperatorId) {
		this.nextOperatorId = nextOperatorId;
	}
}