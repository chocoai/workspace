package com.smart.web.action.person;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.model.BidPlan;
import com.smart.model.PersonFenpei;
import com.smart.model.ProceStepDef;
import com.smart.model.Project;
import com.smart.model.ProjectProcessHistory;
import com.smart.model.ProjectProcessState;
import com.smart.model.T_hremployee;
import com.smart.model.User;
import com.smart.service.BidPlanService;
import com.smart.service.PersonFenpeiService;
import com.smart.service.ProceStepDefService;
import com.smart.service.ProjectProcessHistoryService;
import com.smart.service.ProjectProcessStateService;
import com.smart.service.ProjectService;
import com.smart.service.T_hremployeeService;
import com.smart.service.UserService;
import com.smart.util.Constants;
import com.smart.util.DateUtils;
import com.smart.util.EmailSendTool;
import com.smart.util.Page;
import com.smart.web.action.BaseAction;

@ParentPackage("control-user")
public class PersonAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private UserService userService;

	@Autowired
	private BidPlanService bidPlanService;

	@Autowired
	private PersonFenpeiService personfenpeiService;

	@Autowired
	private ProceStepDefService proceStepDefService;

	@Autowired
	private ProjectProcessStateService processStateService;

	@Autowired
	private ProjectProcessHistoryService projectProcessHistoryService;
	
	@Autowired
	private T_hremployeeService hremployeeService;

	private Project project;

	@Action("list")
	public String list() {
		project = project == null ? new Project() : project;
		Page<ProjectProcessState> pageBean = personfenpeiService.getPage(
				getPageNo(), getPageSize(), project.getNo(), project.getName());
		put("pageBean", pageBean);
		return "list";
	}

	@Action(value = "save", results = {
			@Result(name = "list", location = "list", type = "redirectAction") })
	public String save() {
		project = project == null ? new Project() : project;
		project = projectService.get(project.getId());
		// 投标策划阶段处理人
		StringBuffer bidPlanHandler = new StringBuffer();
		bidPlanHandler.append(get("userid1")).append(",");
		bidPlanHandler.append(get("userid2")).append(",");
		bidPlanHandler.append(get("userid3")).append(",");
		bidPlanHandler.append(get("userid4")).append(",");
		bidPlanHandler.append(get("userid5")).append(",");
		bidPlanHandler.append(get("userid6"));

		StringBuffer pstart = new StringBuffer();
		pstart.append(get("pstart1")).append("&&");
		pstart.append(get("pstart2")).append("&&");
		pstart.append(get("pstart3")).append("&&");
		pstart.append(get("pstart4")).append("&&");
		pstart.append(get("pstart5")).append("&&");
		pstart.append(get("pstart6"));

		StringBuffer pcontinue = new StringBuffer();
		pcontinue.append(get("pcontinue1")).append("&&");
		pcontinue.append(get("pcontinue2")).append("&&");
		pcontinue.append(get("pcontinue3")).append("&&");
		pcontinue.append(get("pcontinue4")).append("&&");
		pcontinue.append(get("pcontinue5")).append("&&");
		pcontinue.append(get("pcontinue6"));
		// 更新投标策划人员分配表
		PersonFenpei personFenpei = personfenpeiService
				.getOneByProjectId(project.getId());
		personFenpei.setHandlers(bidPlanHandler.toString());
		personFenpei.setPstart(pstart.toString());
		personFenpei.setPcontinue(pcontinue.toString());
		personfenpeiService.update(personFenpei);

		BidPlan bidPlan = bidPlanService.getBidPlanByProjectId(project.getId());
		if (bidPlan == null) {
			bidPlan = new BidPlan();
			bidPlan.setProject(project);
			bidPlan.setHandler(bidPlanHandler.toString());
			bidPlanService.save(bidPlan);
		}
		// project.setNextOperator(bidPlanHandler.toString());
		projectService.update(project);

		ProceStepDef nextStep = proceStepDefService.getStepByStepCode(Constants.StepCode.BID_PLAN);
		// 更新项目进程状态表信息
		ProjectProcessState pps = processStateService
				.getOneByProjectIdAndStepCode(project.getId(),
						Constants.StepCode.BID_DISPATCH);
		pps.setCurrentStep(nextStep);
		pps.setCurrentUsers(bidPlanHandler.toString());
		processStateService.update(pps);
		// 更新项目进程处理历史记录表
		ProjectProcessHistory pph = new ProjectProcessHistory();
		pph.setProject(project);
		pph.setOperateStep(proceStepDefService
				.getStepByStepCode(Constants.StepCode.BID_DISPATCH));
		pph.setOperateUser(getUser());
		pph.setOperateTime(DateUtils.getCurrentTime());
		pph.setOperateType(Constants.OperateType.SUBMIT);
		pph.setNextStep(nextStep);
		pph.setNextHandlers(bidPlanHandler.toString());
		projectProcessHistoryService.save(pph);
		
		// 发送邮件通知下一步处理人有待办事项需要处理
		// 查询下一步处理人的邮箱地址
		StringBuilder emailAddress = new StringBuilder(); 
		if (StringUtils.isNotBlank(bidPlanHandler.toString())) {
			String[] userIds = bidPlanHandler.toString().split(",");
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
		
		return "list";
	}

	@Action("edit")
	public String edit() {
		project = project == null ? new Project() : project;
		PersonFenpei personFenpei = personfenpeiService
				.getOneByProjectId(project.getId());
		if (personFenpei != null) {
			put("personFenpei", personFenpei);
			if (StringUtils.isNotBlank(personFenpei.getHandlers())) {
				String[] userIds = personFenpei.getHandlers().split(",");
				String[] pstarts = personFenpei.getPstart().split("&&");
				String[] pcontinues = personFenpei.getPcontinue().split("&&");
				for (int i = 0; i < userIds.length; i++) {
					int index = i + 1;
					if (StringUtils.isNotBlank(userIds[i])) {
						User user = userService.get(Integer.valueOf(userIds[i]));
						put("user" + index, user);
					}
				}
				for (int i = 0; i < pstarts.length; i++) {
					int index = i + 1;
					put("pstart" + index, pstarts[i]);
				}
				for (int i = 0; i < pcontinues.length; i++) {
					int index = i + 1;
					put("pcontinue" + index, pcontinues[i]);
				}
				
			}
		}
		return "new";
	}

	@Action("show")
	public String show() {
		if (StringUtils.isNotBlank(get("projectId"))) {
			int projectId = Integer.valueOf(get("projectId"));
			PersonFenpei personFenpei = personfenpeiService.getOneByProjectId(projectId);
			if (personFenpei != null) {
				put("personFenpei", personFenpei);
				if (StringUtils.isNotBlank(personFenpei.getHandlers())) {
					String[] userIds = personFenpei.getHandlers().split(",");
					String[] pstarts = personFenpei.getPstart().split("&&");
					String[] pcontinues = personFenpei.getPcontinue().split("&&");
					for (int i = 0; i < userIds.length; i++) {
						int index = i + 1;
						if (StringUtils.isNotBlank(userIds[i])) {
							User user = userService.get(Integer.valueOf(userIds[i]));
							put("user" + index, user);
						}
					}
					for (int i = 0; i < pstarts.length; i++) {
						int index = i + 1;
						put("pstart" + index, pstarts[i]);
					}
					for (int i = 0; i < pcontinues.length; i++) {
						int index = i + 1;
						put("pcontinue" + index, pcontinues[i]);
					}
				}
			}
		}
		return "show";
	}

	@Action("print")
	public String print() {
		if (StringUtils.isNotBlank(get("projectId"))) {
			int projectId = Integer.valueOf(get("projectId"));
			PersonFenpei personFenpei = personfenpeiService
					.getOneByProjectId(projectId);
			if (personFenpei != null) {
				put("personFenpei", personFenpei);
				if (StringUtils.isNotBlank(personFenpei.getHandlers())) {
					String[] userIds = personFenpei.getHandlers().split(",");
					String[] pstarts = personFenpei.getPstart().split("&&");
					String[] pcontinues = personFenpei.getPcontinue().split("&&");
					for (int i = 0; i < userIds.length; i++) {
						int index = i + 1;
						if (StringUtils.isNotBlank(userIds[i])) {
							User user = userService.get(Integer.valueOf(userIds[i]));
							put("user" + index, user);
						}
					}
					for (int i = 0; i < pstarts.length; i++) {
						int index = i + 1;
						put("pstart" + index, pstarts[i]);
					}
					for (int i = 0; i < pcontinues.length; i++) {
						int index = i + 1;
						put("pcontinue" + index, pcontinues[i]);
					}
				}
			}
		}
		return "print";
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
