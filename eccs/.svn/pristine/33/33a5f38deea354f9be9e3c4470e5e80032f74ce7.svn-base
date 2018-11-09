/**
 * 
 */
package com.smart.web.action.bid.summary;

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
import com.smart.model.ProjectSummary;
import com.smart.model.T_Customer;
import com.smart.model.User;
import com.smart.service.ProceStepDefService;
import com.smart.service.ProjectInfoService;
import com.smart.service.ProjectProcessHistoryService;
import com.smart.service.ProjectProcessStateService;
import com.smart.service.ProjectSummaryService;
import com.smart.service.UserService;
import com.smart.util.Constants;
import com.smart.util.DateUtils;
import com.smart.util.Page;
import com.smart.util.ReflectionUtil;
import com.smart.web.action.BaseAction;

/**  
* @Description:投标总结模块
* @author hexiang
* @date 2017年7月5日 下午5:38:21
*/
@ParentPackage("control-user")
public class BidSummaryAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ProjectProcessStateService processStateService;
	
	@Autowired
	private ProceStepDefService proceStepDefService;

	@Autowired
	private ProjectProcessHistoryService projectProcessHistoryService;
	
	@Autowired
	private ProjectSummaryService projectSummaryService;
	
	@Autowired
	private ProjectInfoService projectInfoService;
	
	@Autowired
	private UserService userService;
	
	private ProjectInfo projectInfo; // 项目
	
	private String nextOperatorId;
	
	private ProjectSummary summary;
	
	@Action("list")
	public String list(){
		projectInfo = projectInfo == null ? new ProjectInfo() : projectInfo;
		summary = summary == null ? new ProjectSummary() : summary;
		T_Customer customer = projectInfo.getBidmen() == null?new T_Customer() : projectInfo.getBidmen();
		Page<ProjectSummary> pageBean = projectSummaryService.getPage(pageNo, pageSize,
				customer.getCusName(), projectInfo.getProname(),projectInfo.getAgentcompany());
		for (int i = 0; i < pageBean.getList().size(); i++) {
			ProjectSummary assess = pageBean.getList().get(i);
			ProjectInfo proj = assess.getProjectInfo();
			ProjectProcessState pps = processStateService
					.getOneByProjectInfoIdAndStepCode(proj.getId(),
							Constants.StepCode.BID_SUMMARY);
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
		put("summary", summary);
		put("projectInfo", projectInfo);
		return "list";
	}
	
	@Action("edit")
	public String edit(){
		summary = projectSummaryService.getSummaryByProjectInfo(projectInfo);
		projectInfo = summary.getProjectInfo();
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
		put("summary",summary);
		return "edit";
	}

	@Action("show")
	public String show(){
		summary = projectSummaryService.getSummaryByProjectInfo(projectInfo);
		put("summary",summary);
		return "show";
	}

	@Action(value = "save", results = {
			@Result(name = "list", location = "list", type = "redirectAction") })
	public String save(){
		
		ProjectSummary oldsummary = projectSummaryService.get(summary.getCid());
		projectInfo = oldsummary.getProjectInfo();
		
		projectInfo.setNextOperatorId(nextOperatorId);
		
		projectInfoService.update(projectInfo);
		
		ProjectProcessState pps = processStateService
				.getOneByProjectInfoId(projectInfo.getId());
		// 更新项目进程处理历史记录表
		ProceStepDef currentStep = proceStepDefService
				.getStepByStepCode(Constants.StepCode.BID_SUMMARY);
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
		ReflectionUtil.bean2Bean(oldsummary, summary,
				"cid,projectInfo,status,createTime");
		projectSummaryService.update(oldsummary);
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
		summary = projectSummaryService.get(summary.getCid());
		summary.setStatus(0);
		projectSummaryService.update(summary);
		// 更新项目进程状态表信息
		ProjectProcessState pps = processStateService
				.getOneByProjectInfoIdAndStepCode(projectInfo.getId(),
						Constants.StepCode.BID_SUMMARY);
		pps.setCurrentStep(proceStepDefService
				.getStepByStepCode(Constants.StepCode.STOPED));
		pps.setCurrentUsers("");
		pps.setCurrentState(ProjectProcessState.STOPED);
		processStateService.update(pps);
		// 更新项目进程处理历史记录表
		ProjectProcessHistory pph = new ProjectProcessHistory();
		pph.setProjectInfo(projectInfo);
		pph.setOperateStep(proceStepDefService
				.getStepByStepCode(Constants.StepCode.BID_SUMMARY));
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

	public ProjectSummary getSummary() {
		return summary;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	public void setSummary(ProjectSummary summary) {
		this.summary = summary;
	}

	public String getNextOperatorId() {
		return nextOperatorId;
	}

	public void setNextOperatorId(String nextOperatorId) {
		this.nextOperatorId = nextOperatorId;
	}

}



