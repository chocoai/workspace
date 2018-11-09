package com.smart.web.action.performance;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.model.AnnexType;
import com.smart.model.Performance;
import com.smart.model.PerformanceUser;
import com.smart.model.Project;
import com.smart.model.User;
import com.smart.service.AnnexTypeService;
import com.smart.service.PerformanceService;
import com.smart.service.PerformanceUserService;
import com.smart.service.ProjectService;
import com.smart.service.UserService;
import com.smart.util.Page;
import com.smart.util.ReflectionUtil;
import com.smart.util.StringUtil;
import com.smart.web.action.BaseAction;

@ParentPackage("control-user")
public class PerformanceAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private PerformanceService performanceService;

	@Autowired
	private PerformanceUserService performanceUserService;

	@Autowired
	private AnnexTypeService annexTypeService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private UserService userService;

	private Performance performance;

	private Project project;

	private PerformanceUser performanceUser1;// 绩效人员对应页面1-10

	private PerformanceUser performanceUser2;

	private PerformanceUser performanceUser3;

	private PerformanceUser performanceUser4;

	private PerformanceUser performanceUser5;

	private PerformanceUser performanceUser6;

	private PerformanceUser performanceUser7;

	private PerformanceUser performanceUser8;

	private PerformanceUser performanceUser9;

	private PerformanceUser performanceUser10;

	private Integer projectTypeId; // 项目类型id

	private String id;

	private String id1;

	private Integer userid1;// 绩效人员

	private Integer userid2;

	private Integer userid3;

	private Integer userid4;

	private Integer userid5;

	private Integer userid6;

	private Integer userid7;

	private Integer userid8;

	private Integer userid9;

	private Integer userid10;

	@Action("new")
	public String _new() {
		project = projectService.get(Integer.parseInt(id));
		List<AnnexType> annexTypeList = annexTypeService.getByStep1("4", "yes"); //
		put("annexTypeList", annexTypeList);
		performanceUser1 = new PerformanceUser();
		performanceUser2 = new PerformanceUser();
		performanceUser3 = new PerformanceUser();
		performanceUser4 = new PerformanceUser();
		performanceUser5 = new PerformanceUser();
		performanceUser6 = new PerformanceUser();
		performanceUser7 = new PerformanceUser();
		performanceUser8 = new PerformanceUser();
		performanceUser9 = new PerformanceUser();
		performanceUser10 = new PerformanceUser();
		performance = new Performance();
		// List<PerformanceUser> ss=performanceUserService.getList(id);
		return "new";
	}

	@Action("list")
	public String list() {
		project = project == null ? new Project() : project;
		Page<Project> pageBean = projectService.getPage(getPageNo(),
				getPageSize(), project.getNo(), project.getName());// 只看存在的项目
		put("pageBean", pageBean);
		return "list";
	}

	@Action(value = "save", results = {
			@Result(name = "list", location = "list", type = "redirectAction") })
	public String save() {
		if (StringUtil.isBlank(id)) {
			project = projectService.get(Integer.parseInt(id1));
			project.setPerformanceid(1);
			projectService.update(project);
			performance.setCustomer(project.getCustomer());
			performance.setProject(project);
			performanceService.save(performance);
			if (userid1 != null) {
				User us = userService.get(userid1);
				performanceUser1.setUser(us);
			}
			performanceUser1.setPerformance(performance);
			performanceUserService.save(performanceUser1);
			if (userid2 != null && userid2 != 0) {
				User us = userService.get(userid2);
				performanceUser2.setUser(us);
			}
			performanceUser2.setPerformance(performance);
			performanceUserService.save(performanceUser2);
			if (userid3 != null) {
				User us = userService.get(userid3);
				performanceUser3.setUser(us);
			}
			performanceUser3.setPerformance(performance);
			performanceUserService.save(performanceUser3);
			if (userid4 != null) {
				User us = userService.get(userid4);
				performanceUser4.setUser(us);
			}
			performanceUser4.setPerformance(performance);
			performanceUserService.save(performanceUser4);
			if (userid5 != null) {
				User us = userService.get(userid5);
				performanceUser5.setUser(us);
			}
			performanceUser5.setPerformance(performance);
			performanceUserService.save(performanceUser5);
			if (userid6 != null) {
				User us = userService.get(userid6);
				performanceUser6.setUser(us);
			}
			performanceUser6.setPerformance(performance);
			performanceUserService.save(performanceUser6);
			if (userid7 != null) {
				User us = userService.get(userid7);
				performanceUser7.setUser(us);
			}
			performanceUser7.setPerformance(performance);
			performanceUserService.save(performanceUser7);
			if (userid8 != null) {
				User us = userService.get(userid8);
				performanceUser8.setUser(us);
			}
			performanceUser8.setPerformance(performance);
			performanceUserService.save(performanceUser8);
			if (userid9 != null) {
				User us = userService.get(userid9);
				performanceUser9.setUser(us);
			}
			performanceUser9.setPerformance(performance);
			performanceUserService.save(performanceUser9);
			if (userid10 != null) {
				User us = userService.get(userid10);
				performanceUser10.setUser(us);
			}
			performanceUser10.setPerformance(performance);
			performanceUserService.save(performanceUser10);
		} else {
			project = projectService.get(Integer.parseInt(id1));
			performance.setCustomer(project.getCustomer());
			performance.setProject(project);
			Performance oldperformance = performanceService
					.get(Integer.parseInt(id));
			List<PerformanceUser> ss = performanceUserService
					.getList(oldperformance.getId());
			ReflectionUtil.bean2Bean(oldperformance, performance, "id");
			performanceService.update(oldperformance);
			PerformanceUser oldperformanceUser1 = ss.get(0);
			if (userid1 != null) {
				User us = userService.get(userid1);
				performanceUser1.setUser(us);
			}
			ReflectionUtil.bean2Bean(oldperformanceUser1, performanceUser1,
					"id,performance");
			performanceUserService.update(oldperformanceUser1);
			PerformanceUser oldperformanceUser2 = ss.get(1);
			if (userid2 != null) {
				User us = userService.get(userid2);
				performanceUser2.setUser(us);
			}
			ReflectionUtil.bean2Bean(oldperformanceUser2, performanceUser2,
					"id,performance");
			performanceUserService.update(oldperformanceUser2);
			PerformanceUser oldperformanceUser3 = ss.get(2);
			if (userid3 != null) {
				User us = userService.get(userid3);
				performanceUser3.setUser(us);
			}
			ReflectionUtil.bean2Bean(oldperformanceUser3, performanceUser3,
					"id,performance");
			performanceUserService.update(oldperformanceUser3);
			PerformanceUser oldperformanceUser4 = ss.get(3);
			if (userid4 != null) {
				User us = userService.get(userid4);
				performanceUser4.setUser(us);
			}
			ReflectionUtil.bean2Bean(oldperformanceUser4, performanceUser4,
					"id,performance");
			performanceUserService.update(oldperformanceUser4);
			PerformanceUser oldperformanceUser5 = ss.get(4);
			if (userid5 != null) {
				User us = userService.get(userid5);
				performanceUser5.setUser(us);
			}
			ReflectionUtil.bean2Bean(oldperformanceUser5, performanceUser5,
					"id,performance");
			performanceUserService.update(oldperformanceUser5);
			PerformanceUser oldperformanceUser6 = ss.get(5);
			if (userid6 != null) {
				User us = userService.get(userid6);
				performanceUser6.setUser(us);
			}
			ReflectionUtil.bean2Bean(oldperformanceUser6, performanceUser6,
					"id,performance");
			performanceUserService.update(oldperformanceUser6);
			PerformanceUser oldperformanceUser7 = ss.get(6);
			if (userid7 != null) {
				User us = userService.get(userid7);
				performanceUser7.setUser(us);
			}
			ReflectionUtil.bean2Bean(oldperformanceUser7, performanceUser7,
					"id,performance");
			performanceUserService.update(oldperformanceUser7);
			PerformanceUser oldperformanceUser8 = ss.get(7);
			if (userid8 != null) {
				User us = userService.get(userid8);
				performanceUser8.setUser(us);
			}
			ReflectionUtil.bean2Bean(oldperformanceUser8, performanceUser8,
					"id,performance");
			performanceUserService.update(oldperformanceUser8);
			PerformanceUser oldperformanceUser9 = ss.get(8);
			if (userid9 != null) {
				User us = userService.get(userid9);
				performanceUser9.setUser(us);
			}
			ReflectionUtil.bean2Bean(oldperformanceUser9, performanceUser9,
					"id,performance");
			performanceUserService.update(oldperformanceUser9);
			PerformanceUser oldperformanceUser10 = ss.get(9);
			if (userid10 != null) {
				User us = userService.get(userid10);
				performanceUser10.setUser(us);
			}
			ReflectionUtil.bean2Bean(oldperformanceUser10, performanceUser10,
					"id,performance");
			performanceUserService.update(oldperformanceUser10);
		}
		return "list";
	}

	@Action("edit")
	public String edit() {
		List<AnnexType> annexTypeList = annexTypeService.getByStep1("4", "yes"); //
		put("annexTypeList", annexTypeList);
		project = projectService.get(Integer.parseInt(id));
		performance = performanceService.getList(Integer.parseInt(id));
		List<PerformanceUser> ss = performanceUserService
				.getList(performance.getId());
		performanceUser1 = ss.get(0);
		performanceUser2 = ss.get(1);
		performanceUser3 = ss.get(2);
		performanceUser4 = ss.get(3);
		performanceUser5 = ss.get(4);
		performanceUser6 = ss.get(5);
		performanceUser7 = ss.get(6);
		performanceUser8 = ss.get(7);
		performanceUser9 = ss.get(8);
		performanceUser10 = ss.get(9);
		return "new";
	}

	@Action("show")
	public String show() {
		List<AnnexType> annexTypeList = annexTypeService.getByStep1("4", "yes"); //
		put("annexTypeList", annexTypeList);
		project = projectService.get(Integer.parseInt(id));
		performance = performanceService.getList(Integer.parseInt(id));
		List<PerformanceUser> ss = performanceUserService
				.getList(performance.getId());
		performanceUser1 = ss.get(0);
		performanceUser2 = ss.get(1);
		performanceUser3 = ss.get(2);
		performanceUser4 = ss.get(3);
		performanceUser5 = ss.get(4);
		performanceUser6 = ss.get(5);
		performanceUser7 = ss.get(6);
		performanceUser8 = ss.get(7);
		performanceUser9 = ss.get(8);
		performanceUser10 = ss.get(9);
		return "show";
	}

	@Action("delete")
	public String delete() {
		project = projectService.get(Integer.parseInt(id1));
		project.setPerformanceid(-1);
		projectService.update(project);
		performance = performanceService.getList(Integer.parseInt(id1));
		List<PerformanceUser> s = performanceUserService
				.getList(performance.getId());
		for (int i = 0; i < s.size(); i++) {
			performanceUserService.delete(s.get(i).getId());
		}
		performanceService.delete(performance.getId());
		write("1");
		return null;
	}

	public Performance getPerformance() {
		return performance;
	}

	public void setPerformance(Performance performance) {
		this.performance = performance;
	}

	public PerformanceUser getPerformanceUser1() {
		return performanceUser1;
	}

	public void setPerformanceUser1(PerformanceUser performanceUser1) {
		this.performanceUser1 = performanceUser1;
	}

	public PerformanceUser getPerformanceUser2() {
		return performanceUser2;
	}

	public void setPerformanceUser2(PerformanceUser performanceUser2) {
		this.performanceUser2 = performanceUser2;
	}

	public PerformanceUser getPerformanceUser3() {
		return performanceUser3;
	}

	public void setPerformanceUser3(PerformanceUser performanceUser3) {
		this.performanceUser3 = performanceUser3;
	}

	public PerformanceUser getPerformanceUser4() {
		return performanceUser4;
	}

	public void setPerformanceUser4(PerformanceUser performanceUser4) {
		this.performanceUser4 = performanceUser4;
	}

	public PerformanceUser getPerformanceUser5() {
		return performanceUser5;
	}

	public void setPerformanceUser5(PerformanceUser performanceUser5) {
		this.performanceUser5 = performanceUser5;
	}

	public PerformanceUser getPerformanceUser6() {
		return performanceUser6;
	}

	public void setPerformanceUser6(PerformanceUser performanceUser6) {
		this.performanceUser6 = performanceUser6;
	}

	public PerformanceUser getPerformanceUser7() {
		return performanceUser7;
	}

	public void setPerformanceUser7(PerformanceUser performanceUser7) {
		this.performanceUser7 = performanceUser7;
	}

	public PerformanceUser getPerformanceUser8() {
		return performanceUser8;
	}

	public void setPerformanceUser8(PerformanceUser performanceUser8) {
		this.performanceUser8 = performanceUser8;
	}

	public PerformanceUser getPerformanceUser9() {
		return performanceUser9;
	}

	public void setPerformanceUser9(PerformanceUser performanceUser9) {
		this.performanceUser9 = performanceUser9;
	}

	public PerformanceUser getPerformanceUser10() {
		return performanceUser10;
	}

	public void setPerformanceUser10(PerformanceUser performanceUser10) {
		this.performanceUser10 = performanceUser10;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Integer getProjectTypeId() {
		return projectTypeId;
	}

	public void setProjectTypeId(Integer projectTypeId) {
		this.projectTypeId = projectTypeId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId1() {
		return id1;
	}

	public void setId1(String id1) {
		this.id1 = id1;
	}

	public Integer getUserid1() {
		return userid1;
	}

	public void setUserid1(Integer userid1) {
		this.userid1 = userid1;
	}

	public Integer getUserid2() {
		return userid2;
	}

	public void setUserid2(Integer userid2) {
		this.userid2 = userid2;
	}

	public Integer getUserid3() {
		return userid3;
	}

	public void setUserid3(Integer userid3) {
		this.userid3 = userid3;
	}

	public Integer getUserid4() {
		return userid4;
	}

	public void setUserid4(Integer userid4) {
		this.userid4 = userid4;
	}

	public Integer getUserid5() {
		return userid5;
	}

	public void setUserid5(Integer userid5) {
		this.userid5 = userid5;
	}

	public Integer getUserid6() {
		return userid6;
	}

	public void setUserid6(Integer userid6) {
		this.userid6 = userid6;
	}

	public Integer getUserid7() {
		return userid7;
	}

	public void setUserid7(Integer userid7) {
		this.userid7 = userid7;
	}

	public Integer getUserid8() {
		return userid8;
	}

	public void setUserid8(Integer userid8) {
		this.userid8 = userid8;
	}

	public Integer getUserid9() {
		return userid9;
	}

	public void setUserid9(Integer userid9) {
		this.userid9 = userid9;
	}

	public Integer getUserid10() {
		return userid10;
	}

	public void setUserid10(Integer userid10) {
		this.userid10 = userid10;
	}

}
