package com.smart.web.action.backlog;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.model.Project;
import com.smart.model.ProjectProcessHistory;
import com.smart.model.ProjectProcessState;
import com.smart.service.ProjectProcessHistoryService;
import com.smart.service.ProjectProcessStateService;
import com.smart.service.ProjectService;
import com.smart.util.Page;
import com.smart.web.action.BaseAction;

/**
 * 说明：根据包名的路径自动装配 比如本action路径，com.smart.demo.DemoAction，对应的页面文件夹content/demo
 * return "new" 则会找到content/demo/new.ftl
 * 
 * control-user 权限拦截，使用UserInterceptor拦截器
 */
@ParentPackage("control-user")
public class BacklogAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ProjectProcessStateService processStateService;

	@Autowired
	private ProjectProcessHistoryService processHistoryService;

	@Autowired
	private ProjectService projectService;

	private Project project;

	/**
	 * 列表
	 */
	@Action("list")
	public String list() {
		Integer userId = this.getUser().getId();
		Page<ProjectProcessState> pageBean = processStateService.getPageState(
				getPageNo(), getPageSize(), null, null,
				userId, null);
		put("pageBean", pageBean);
		return "list";
	}
	
	@Action("countBacklog")
	public void countBacklog(){
		Integer userId = this.getUser().getId();
		int amount = processStateService.countBacklog(userId);
		write(String.valueOf(amount));
	}

	/**
	 * 已完成的待办事项列表
	 */
	@Action("getMyFinishedBacklog")
	public String finishedList() {
		project = project == null ? new Project() : project;
		int userId = this.getUseId();
		Page<Project> pageBean = projectService.getDonePage(pageNo, pageSize,
				project.getNo(), project.getName(), userId);
		put("pageBean", pageBean);
		return "myFinishedBacklog";
	}

	/**
	 * 所有事项
	 * 
	 * @return
	 */
	@Action("getAll")
	public String getAll() {
		project = project == null ? new Project() : project;
		int userId = this.getUseId();
		Page<Project> pageBean = projectService.getAllPage(pageNo, pageSize,
				project.getNo(), project.getName(), userId);
		put("pageBean", pageBean);
		return "myBacklogAll";
	}

	/**
	 * 显示
	 * 
	 * @return
	 */
	@Action("show")
	public String show() {
		project = project == null ? new Project() : project;
		List<ProjectProcessHistory> list = processHistoryService
				.getListByProjectIdAndUserId(project.getId(), this.getUseId());
		put("pphList", list);
		return "show";
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
}
