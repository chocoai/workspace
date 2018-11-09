/**
 * 
 */
package com.smart.web.action.bid.call;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.model.BidApplyAssess;
import com.smart.model.BidBondAssess;
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
* @Description:招标文件模块
* @author hexiang 
* @date 2017年7月6日 上午9:42:52
*/
@ParentPackage("control-user")
public class BidCallAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ProceStepDefService proceStepDefService;
	
	@Autowired
	private ProjectProcessHistoryService projectProcessHistoryService;
	
	@Autowired
	private ProjectInfoService projectInfoService;
	
	@Autowired
	private CallBidAssessService callBidAssessService;
	
	@Autowired
	private BidApplyAssessService bidApplyAssessService;
	
	@Autowired
	private BidBondAssessService bidBondAssessService;
	
	@Autowired
	private T_hremployeeService hremployeeService;
	
	@Autowired
	private ProjectProcessStateService processStateService;
	
	@Autowired
	private UserService userService;
	
	private ProjectInfo projectInfo; // 项目
	
	private CallBidAssess callAssess;

	private String nextOperatorId;
	
	@Action("list")
	public String list(){
		projectInfo = projectInfo == null ? new ProjectInfo() : projectInfo;
		callAssess = callAssess == null ? new CallBidAssess() : callAssess;
		T_Customer customer = projectInfo.getBidmen() == null?new T_Customer() : projectInfo.getBidmen();
		Page<CallBidAssess> pageBean = callBidAssessService.getPage(pageNo, pageSize,
				customer.getCusName(), projectInfo.getProname(),projectInfo.getAgentcompany());
		for (int i = 0; i < pageBean.getList().size(); i++) {
			CallBidAssess assess = pageBean.getList().get(i);
			ProjectInfo proj = assess.getProjectInfo();
			ProjectProcessState pps = processStateService
					.getOneByProjectInfoIdAndStepCode(proj.getId(),
							Constants.StepCode.BID_CALL);
			//是否可以进行编辑操作，当项目已经进行到下一步时，此处为false即不能操作
			if (pps != null) {
				String[] handlers = pps.getCurrentUsers().split(",");
				List<String> userList = Arrays.asList(handlers);
				if (userList.contains(getUser().getId().toString())) {
					assess.setResStates(true);
				}
			} else {
				assess.setResStates(false);
			}
		}
		put("pageBean", pageBean);
		put("callAssess", callAssess);
		put("projectInfo", projectInfo);
		return "list";
	}
	
	@Action("edit")
	public String edit(){
		callAssess = callBidAssessService.getCallAssessByProjectInfo(projectInfo);
		projectInfo = callAssess.getProjectInfo();
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
		put("callAssess",callAssess);
		return "edit";
	}

	@Action("show")
	public String show(){
		callAssess = callBidAssessService.getCallAssessByProjectInfo(projectInfo);
		put("callAssess",callAssess);
		return "show";
	}
	
	@Action(value = "save", results = {
			@Result(name = "list", location = "list", type = "redirectAction") })
	public String save(){
		
		CallBidAssess oldcallAssess = callBidAssessService.get(callAssess.getCid());
		projectInfo = oldcallAssess.getProjectInfo();
		
		projectInfo.setNextOperatorId(nextOperatorId);
		
		projectInfoService.update(projectInfo);
		
		ProjectProcessState pps = processStateService
				.getOneByProjectInfoIdAndStepCode(projectInfo.getId(),
						Constants.StepCode.BID_CALL);
		// 更新项目进程处理历史记录表
		ProceStepDef currentStep = proceStepDefService
				.getStepByStepCode(Constants.StepCode.BID_CALL);
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
		ReflectionUtil.bean2Bean(oldcallAssess, callAssess,
				"cid,projectInfo,consultType,status,createTime");
		callBidAssessService.update(oldcallAssess);
		return "list";
	}

	@Action(value = "toNextStep", results = {
			@Result(name = "list", location = "list", type = "redirectAction") })
	public String toNextStep(){
		
	    CallBidAssess oldcallAssess = callBidAssessService.get(callAssess.getCid());
		projectInfo = oldcallAssess.getProjectInfo();
		
		projectInfo.setNextOperatorId("");
		
		projectInfoService.update(projectInfo);
	
		// 下一环节处理人
		String nextOperatorId = get("nextOperatorId");
		// 更新项目进程状态表信息
		ProjectProcessState pps = processStateService
				.getOneByProjectInfoIdAndStepCode(projectInfo.getId(),
						Constants.StepCode.BID_CALL);
		ProceStepDef nextStep = proceStepDefService
				.getStepByStepCode(Constants.StepCode.BID_BOND);
		if(1!=callAssess.getAssessResult()){
			pps.setCurrentStep(proceStepDefService
					.getStepByStepCode(Constants.StepCode.STOPED));
			pps.setCurrentUsers("");
			pps.setCurrentState(ProjectProcessState.STOPED);
			processStateService.update(pps);
			// 更新项目进程处理历史记录表
			ProjectProcessHistory pph = new ProjectProcessHistory();
			pph.setProjectInfo(projectInfo);
			pph.setOperateStep(proceStepDefService
					.getStepByStepCode(Constants.StepCode.BID_CALL));
			pph.setOperateUser(getUser());
			pph.setOperateTime(DateUtils.getCurrentTime());
			pph.setOperateType(Constants.OperateType.TERMINATE);
			pph.setNextStep(null);
			pph.setNextHandlers("");
			projectProcessHistoryService.save(pph);
		}else{
			pps.setCurrentStep(nextStep);
			pps.setCurrentUsers(nextOperatorId);
			pps.setCurrentState(ProjectProcessState.IN_PROGRESS);
			pps.setLastUpdateTime(DateUtils.getCurrentTime());
			processStateService.update(pps);
			// 更新项目进程处理历史记录表
			ProjectProcessHistory pph = new ProjectProcessHistory();
			pph.setProjectInfo(projectInfo);
			pph.setOperateStep(proceStepDefService
					.getStepByStepCode(Constants.StepCode.BID_CALL));
			pph.setOperateUser(getUser());
			pph.setOperateTime(DateUtils.getCurrentTime());
			pph.setOperateType(Constants.OperateType.SUBMIT);
			pph.setNextStep(nextStep);
			pph.setNextHandlers(nextOperatorId);
			projectProcessHistoryService.save(pph);

			// 属性拷贝，把更新内容拷贝到数据库查出的对象里，第三个参数表示不需要拷贝的属性名称
			ReflectionUtil.bean2Bean(oldcallAssess, callAssess,
					"cid,projectInfo,consultType,status,createTime");
			callBidAssessService.update(oldcallAssess);
			BidApplyAssess applyAssess = bidApplyAssessService.getApplyAssessByProjectInfo(oldcallAssess.getProjectInfo());
			BidBondAssess bondAssess = new BidBondAssess();
			bondAssess.setBidPkg(applyAssess.getBidPkg());
			String applyNo = Calendar.getInstance().get(Calendar.YEAR) + String.format("%04d", (Integer.valueOf(bidBondAssessService.getBondLast().substring(4, 8))+1));
			bondAssess.setApplyNo(Integer.valueOf(applyNo));
			bondAssess.setProjectInfo(projectInfo);
			bondAssess.setCustomer(applyAssess.getCustomer());
			bondAssess.setCreateTime(DateUtils.getCurrentTime());
			bondAssess.setStatus(1);
			bidBondAssessService.save(bondAssess);
		}

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
		callAssess = callBidAssessService.get(callAssess.getCid());
		callAssess.setStatus(0);
		callBidAssessService.update(callAssess);
		// 更新项目进程状态表信息
		ProjectProcessState pps = processStateService
				.getOneByProjectInfoIdAndStepCode(projectInfo.getId(),
						Constants.StepCode.BID_CALL);
		pps.setCurrentStep(proceStepDefService
				.getStepByStepCode(Constants.StepCode.STOPED));
		pps.setCurrentUsers("");
		pps.setCurrentState(ProjectProcessState.STOPED);
		processStateService.update(pps);
		// 更新项目进程处理历史记录表
		ProjectProcessHistory pph = new ProjectProcessHistory();
		pph.setProjectInfo(projectInfo);
		pph.setOperateStep(proceStepDefService
				.getStepByStepCode(Constants.StepCode.BID_CALL));
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

	public CallBidAssess getCallAssess() {
		return callAssess;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	public void setCallAssess(CallBidAssess callAssess) {
		this.callAssess = callAssess;
	}
	
	public String getNextOperatorId() {
		return nextOperatorId;
	}

	public void setNextOperatorId(String nextOperatorId) {
		this.nextOperatorId = nextOperatorId;
	}
	
}

