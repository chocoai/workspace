package com.smart.web.action.t_seal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.model.Project;
import com.smart.model.T_sealBorrow;
import com.smart.model.T_sealOption;
import com.smart.service.ProjectService;
import com.smart.service.T_sealBorrowService;
import com.smart.service.T_sealOptionService;
import com.smart.util.Page;
import com.smart.web.action.BaseAction;

@ParentPackage("control-user")
public class T_sealAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private T_sealBorrowService borrowService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private T_sealOptionService optionService;

	private Project project;

	private T_sealBorrow t_sealBorrow;

	private T_sealOption t_sealOption;

	private String uproject;// 项目名称-查询

	private String ubranch;// 部门名称-查询

	private String uname;// 借用人-查询

	private String rtimec;// 开始日期-查询

	private String rtimej;// 结束日期-查询

	private String Stringid;// 印章借用id-删除

	private String zzid;// 印章借用id-归还

	/**
	 * 列表and条件查询
	 */
	@Action("list")
	public String list() {
		t_sealBorrow = t_sealBorrow == null ? new T_sealBorrow() : t_sealBorrow;
		Page<T_sealBorrow> pageBean = borrowService.getPage(getPageNo(), 10,
				getUproject(), getUbranch(), getUname(), getRtimec(),
				getRtimej(), t_sealBorrow.getType(), this.getMyDeptId(),
				this.getUser().getId());
		put("pageBean", pageBean);
		put("user", this.getUser().getId());
		put("dept", this.getMyDept().getId());
		return "list";
	}

	/**
	 * 新建
	 * 
	 * @return
	 */
	@Action("new")
	public String news() {
		// List<Project> list=projectService.getAll();
		// put("list",list);
		List<T_sealBorrow> seals = borrowService.getSeals();
		List<Integer> types = new ArrayList<Integer>();
		Integer type = null;
		for (T_sealBorrow seal : seals) {// 获取非空闲状态的印章
			type = seal.getType();
			types.add(type);
		}
		// 此处写死了，以后若要再增加印章，则在此处将i小于的判断条件修改即可，目前是1,2,3,4
		// 同时，此处修改了还得修改new.ftl页面的印章显示
		// 这样写主要原因是库里面没有印章的表，所以若要增加印章，就得修改代码
		for (int i = 1; i < 5; i++) {// 获取空闲状态的印章
			if (types.contains(i)) {
				types.remove(types.indexOf(i));
			} else {
				types.add(i);
			}
		}
		if (t_sealBorrow != null && t_sealBorrow.getId() != null) {
			t_sealBorrow = borrowService.get(t_sealBorrow.getId());
			put("t_sealBorrow", t_sealBorrow);
			type = t_sealBorrow.getType();
			types.add(type);
			put("types", types);
			put("check", 1);
		} else {
			String username = this.getUser().getName();
			String deptname = this.getMyDept().getName();
			Date date = new Date();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String rctime = format.format(date);
			put("rctime", rctime);
			put("username", username);
			put("deptname", deptname);
			put("types", types);
			put("check", 0);
		}
		return "new";
	}

	/**
	 * 保存
	 * 
	 * @return
	 */
	@Action(value = "save", results = {
			@Result(name = "list", location = "list", type = "redirectAction") })
	public String saves() {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String rctime = format.format(date);
		if (t_sealBorrow != null && t_sealBorrow.getId() != null) {
			T_sealBorrow px = borrowService.get(t_sealBorrow.getId());
			px.setProject_id(t_sealBorrow.getProject_id());
			px.setType(t_sealBorrow.getType());
			px.setCount(t_sealBorrow.getCount());
			px.setDetail(t_sealBorrow.getDetail());
			px.setReturn_date(t_sealBorrow.getReturn_date());
			px.setSealdept_id(t_sealBorrow.getSealdept_id());
			borrowService.update(px);
		} else {
			t_sealBorrow.setUser_id(this.getUser());
			t_sealBorrow.setDept_id(this.getMyDept());
			t_sealBorrow.setRtime(rctime);
			t_sealBorrow.setState(0);
			borrowService.save(t_sealBorrow);
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
		String[] a = Stringid.split(",");
		for (int i = 0; i < a.length; i++) {
			t_sealBorrow = borrowService.get(Integer.parseInt(a[i]));
			t_sealBorrow.setStatus(-1);
			borrowService.update(t_sealBorrow);
		}
		write("1"); // ajax请求用write返回数据
		return null;
	}

	/**
	 * 选择项目
	 * 
	 * @param id
	 * @return
	 */
	@Action("choose")
	public String choose() {
		project = project == null ? new Project() : project;
		Page<Project> pageBean = projectService.getPageByNoAndName(pageNo,
				pageSize, project.getNo(), project.getName());
		put("pageBean", pageBean);
		return "selectProjet";
	}

	/**
	 * 归还
	 * 
	 * @param id
	 * @return
	 */
	@Action("revert")
	public String revert() {
		t_sealBorrow = borrowService.get(Integer.parseInt(getZzid()));
		if (t_sealBorrow.getOption_id().getUser_id().getId() == this.getUser()
				.getId()) {
			t_sealBorrow.setState(1);
			t_sealBorrow.setGh_date(new Date());
			t_sealBorrow.setGh_userid(this.getUser());
			borrowService.update(t_sealBorrow);
		}
		write("1"); // ajax请求用write返回数据
		return null;
	}

	/**
	 * 查看
	 * 
	 * @param id
	 * @return
	 */
	@Action("show")
	public String show() {
		t_sealBorrow = borrowService.get(t_sealBorrow.getId());
		put("t_sealBorrow", t_sealBorrow);
		return "show";
	}

	/**
	 * 处理
	 * 
	 * @param id
	 * @return
	 */
	@Action("manage")
	public String manage() {
		int borrowId = t_sealBorrow.getId();
		t_sealBorrow = borrowService.get(borrowId);
		put("t_sealBorrow", t_sealBorrow);
		if ((t_sealBorrow.getSealdept_id().getId())
				.equals(this.getMyDept().getId())) {
			put("t_sealBorrow", t_sealBorrow);
			t_sealOption = optionService.get(borrowId);
			Date date = new Date();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String rctime = format.format(date);
			String username = this.getUser().getName();
			put("rctime", rctime);
			put("username", username);
			put("t_sealOption", t_sealOption);
			return "manage";
		}
		return null;
	}

	/**
	 * 处理保存
	 * 
	 * @param id
	 * @return
	 */
	@Action(value = "sav", results = {
			@Result(name = "list", location = "list", type = "redirectAction") })
	public String sav() {
		t_sealBorrow = borrowService.get(t_sealBorrow.getId());
		if (t_sealBorrow.getOption_id() == null
				&& (t_sealBorrow.getSealdept_id().getId())
						.equals(this.getMyDept().getId())) {
			t_sealOption.setUser_id(this.getUser());
			optionService.save(t_sealOption);
			if (t_sealOption.getOption() == 1) {
				t_sealBorrow.setState(2);
				borrowService.update(t_sealBorrow);
			}
			T_sealBorrow px = borrowService.get(t_sealBorrow.getId());
			px.setOption_id(t_sealOption);
			borrowService.update(px);
		}
		return "list";
	}

	/*--------------------get和set----------------------------*/
	public T_sealBorrow getT_sealBorrow() {
		return t_sealBorrow;
	}

	public void setT_sealBorrow(T_sealBorrow t_sealBorrow) {
		this.t_sealBorrow = t_sealBorrow;
	}

	public String getUproject() {
		return uproject;
	}

	public void setUproject(String uproject) {
		this.uproject = uproject;
	}

	public String getUbranch() {
		return ubranch;
	}

	public void setUbranch(String ubranch) {
		this.ubranch = ubranch;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getRtimec() {
		return rtimec;
	}

	public void setRtimec(String rtimec) {
		this.rtimec = rtimec;
	}

	public String getRtimej() {
		return rtimej;
	}

	public void setRtimej(String rtimej) {
		this.rtimej = rtimej;
	}

	public String getStringid() {
		return Stringid;
	}

	public void setStringid(String stringid) {
		Stringid = stringid;
	}

	public String getZzid() {
		return zzid;
	}

	public void setZzid(String zzid) {
		this.zzid = zzid;
	}

	public T_sealOption getT_sealOption() {
		return t_sealOption;
	}

	public void setT_sealOption(T_sealOption t_sealOption) {
		this.t_sealOption = t_sealOption;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
