/**
 * 
 */
package com.smart.web.action.bid.transfer;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.model.ProceStepDef;
import com.smart.model.ProjectInfo;
import com.smart.model.ProjectProcessHistory;
import com.smart.model.ProjectProcessState;
import com.smart.model.ProjectTransferRecord;
import com.smart.model.T_Customer;
import com.smart.model.T_hremployee;
import com.smart.model.User;
import com.smart.service.ProceStepDefService;
import com.smart.service.ProjectInfoService;
import com.smart.service.ProjectProcessHistoryService;
import com.smart.service.ProjectProcessStateService;
import com.smart.service.ProjectTransferRecordService;
import com.smart.service.T_hremployeeService;
import com.smart.service.UserService;
import com.smart.util.Constants;
import com.smart.util.DateUtils;
import com.smart.util.EmailSendTool;
import com.smart.util.Page;
import com.smart.util.ReflectionUtil;
import com.smart.web.action.BaseAction;

/**  
* @Description:项目移交模块
* @author hexiang
* @date 2017年7月5日 下午5:38:21
*/
@ParentPackage("control-user")
public class BidTransferAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ProjectTransferRecordService projectTransferRecordService;
	
	@Autowired
	private ProjectProcessStateService processStateService;
	
	@Autowired
	private ProceStepDefService proceStepDefService;

	@Autowired
	private ProjectProcessHistoryService projectProcessHistoryService;
	
	@Autowired
	private T_hremployeeService hremployeeService;
	
	@Autowired
	private ProjectInfoService projectInfoService;
	
	@Autowired
	private UserService userService;
	
	private ProjectInfo projectInfo; // 项目
	
	private ProjectTransferRecord transfer;
	
	private String nextOperatorId;

	@Action("list")
	public String list(){
		projectInfo = projectInfo == null ? new ProjectInfo() : projectInfo;
		transfer = transfer == null ? new ProjectTransferRecord() : transfer;
		T_Customer customer = projectInfo.getBidmen() == null?new T_Customer() : projectInfo.getBidmen();
		Page<ProjectTransferRecord> pageBean = projectTransferRecordService.getPage(pageNo, pageSize,
				customer.getCusName(), projectInfo.getProname(),projectInfo.getAgentcompany());
		for (int i = 0; i < pageBean.getList().size(); i++) {
			ProjectTransferRecord assess = pageBean.getList().get(i);
			ProjectInfo proj = assess.getProjectInfo();
			ProjectProcessState pps = processStateService
					.getOneByProjectInfoIdAndStepCode(proj.getId(),
							Constants.StepCode.BID_TRANSFER);
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
		put("transfer", transfer);
		put("projectInfo", projectInfo);
		return "list";
	}
	
	@Action("edit")
	public String edit(){
		transfer = projectTransferRecordService.getTransferByProjectInfo(projectInfo);
		projectInfo = transfer.getProjectInfo();
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
		put("transfer",transfer);
		return "edit";
	}

	@Action("show")
	public String show(){
		transfer = projectTransferRecordService.getTransferByProjectInfo(projectInfo);
		projectInfo = transfer.getProjectInfo();
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
		put("transfer",transfer);
		return "edit";
	}

	@Action(value = "save", results = {
			@Result(name = "list", location = "list", type = "redirectAction") })
	public String save(){
		
		ProjectTransferRecord oldtransfer = projectTransferRecordService.get(transfer.getCid());
		projectInfo = oldtransfer.getProjectInfo();
		
		projectInfo.setNextOperatorId(nextOperatorId);
		
		projectInfoService.update(projectInfo);
		
		ProjectProcessState pps = processStateService
				.getOneByProjectInfoIdAndStepCode(projectInfo.getId(),
						Constants.StepCode.BID_TRANSFER);
		// 更新项目进程处理历史记录表
		ProceStepDef currentStep = proceStepDefService
				.getStepByStepCode(Constants.StepCode.BID_TRANSFER);
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
		ReflectionUtil.bean2Bean(oldtransfer, transfer,
				"cid,projectInfo,consultType,transferNo,bidPrice,status,createTime");
		projectTransferRecordService.update(oldtransfer);
		return "list";
	}

	@Action(value = "toNextStep", results = {
			@Result(name = "list", location = "list", type = "redirectAction") })
	public String toNextStep(){

		ProjectTransferRecord oldtransfer = projectTransferRecordService.get(transfer.getCid());
		projectInfo = oldtransfer.getProjectInfo();
		
		projectInfo.setNextOperatorId(nextOperatorId);
		projectInfo.setStatus(2);
		
		projectInfoService.update(projectInfo);
		
		// 下一环节处理人
		String nextOperatorId = get("nextOperatorId");

		ProceStepDef nextStep = proceStepDefService
				.getStepByStepCode(Constants.StepCode.START_UP_PROJECT);
		// 更新项目进程状态表信息
		ProjectProcessState pps = processStateService
				.getOneByProjectInfoIdAndStepCode(projectInfo.getId(),
						Constants.StepCode.BID_TRANSFER);
		pps.setCurrentStep(nextStep);
		pps.setCurrentUsers(nextOperatorId);
		pps.setCurrentState(ProjectProcessState.IN_PROGRESS);
		pps.setLastUpdateTime(DateUtils.getCurrentTime());
		processStateService.update(pps);
		// 更新项目进程处理历史记录表
		ProjectProcessHistory pph = new ProjectProcessHistory();
		pph.setProjectInfo(projectInfo);
		pph.setOperateStep(proceStepDefService
				.getStepByStepCode(Constants.StepCode.BID_TRANSFER));
		pph.setOperateUser(getUser());
		pph.setOperateTime(DateUtils.getCurrentTime());
		pph.setOperateType(Constants.OperateType.SUBMIT);
		pph.setNextStep(nextStep);
		pph.setNextHandlers(nextOperatorId);
		projectProcessHistoryService.save(pph);
		
		// 属性拷贝，把更新内容拷贝到数据库查出的对象里，第三个参数表示不需要拷贝的属性名称
		ReflectionUtil.bean2Bean(oldtransfer, transfer,
				"cid,projectInfo,consultType,transferNo,bidPrice,status,createTime");
		projectTransferRecordService.update(oldtransfer);

	    //没有项目编号先空着
	    // 发送邮件通知下一步处理人有待办事项需要处理
		// 查询下一步处理人的邮箱地址
		StringBuilder emailAddress = new StringBuilder(); 
		if (StringUtils.isNotBlank(nextOperatorId)) {
			String[] userIds = nextOperatorId.split(",");
			for (int x = 0; x < userIds.length; x++) {
				if (StringUtils.isNumeric(userIds[x])) {
					T_hremployee hrEmployee = hremployeeService.getSysCode(Integer.valueOf(userIds[x]));
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
		transfer = projectTransferRecordService.get(transfer.getCid());
		transfer.setStatus(0);
		projectTransferRecordService.update(transfer);
		// 更新项目进程状态表信息
		ProjectProcessState pps = processStateService
				.getOneByProjectInfoIdAndStepCode(projectInfo.getId(),
						Constants.StepCode.BID_TRANSFER);
		pps.setCurrentStep(proceStepDefService
				.getStepByStepCode(Constants.StepCode.STOPED));
		pps.setCurrentUsers("");
		pps.setCurrentState(ProjectProcessState.STOPED);
		processStateService.update(pps);
		// 更新项目进程处理历史记录表
		ProjectProcessHistory pph = new ProjectProcessHistory();
		pph.setProjectInfo(projectInfo);
		pph.setOperateStep(proceStepDefService
				.getStepByStepCode(Constants.StepCode.BID_TRANSFER));
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

	public ProjectTransferRecord getTransferRecord() {
		return transfer;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	public void setTransferRecord(ProjectTransferRecord transfer) {
		this.transfer = transfer;
	}

	public ProjectTransferRecord getTransfer() {
		return transfer;
	}

	public String getNextOperatorId() {
		return nextOperatorId;
	}

	public void setTransfer(ProjectTransferRecord transfer) {
		this.transfer = transfer;
	}
	
	public void setNextOperatorId(String nextOperatorId) {
		this.nextOperatorId = nextOperatorId;
	}

}




