package com.smart.web.action.demo;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.model.Dept;
import com.smart.model.DeptUser;
import com.smart.model.User;
import com.smart.service.DeptService;
import com.smart.service.DeptUserService;
import com.smart.util.Page;
import com.smart.web.action.BaseAction;

/**
 * 说明：根据包名的路径自动装配 比如本action路径，com.smart.demo.DemoAction，对应的页面文件夹content/demo
 * return "new" 则会找到content/demo/new.ftl
 * 
 * @author 充满智慧的威哥
 */
@ParentPackage("control-user")
public class DemoAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private DeptService deptService;

	@Autowired
	private DeptUserService deptUserService;

	private Integer id; // deptUser 的 id

	private Integer userId;

	private String deptId;

	private User user;

	private Dept dept;

	private String name;

	/**
	 * 新建
	 */
	@Action("new")
	public String _new() {
		User user = new User();
		put("user", user);
		return "new";
	}

	/**
	 * 保存
	 */
	@Action(value = "save", results = {
			@Result(name = "list", location = "list", type = "redirectAction") })
	public String save() {
		// 存在多个表同时保存数据的事物关系，需要把业务逻辑写到service里
		dept = deptService.get(deptId);
		deptUserService.save(userId, user, dept);
		return "list";
	}

	/**
	 * 编辑
	 */
	@Action("edit")
	public String edit() {
		DeptUser deptUser = deptUserService.get(id);
		put("user", deptUser.getUser());
		put("myDept", deptUser.getDept());
		return "new";
	}

	/**
	 * 列表
	 */
	@Action("list")
	public String list() {
		Page<DeptUser> pageBean = deptUserService.getPage(getPageNo(), pageSize,
				name, deptId);
		put("pageBean", pageBean);
		return "list";
	}

	/**
	 * 显示
	 * 
	 * @return
	 */
	@Action("show")
	public String show() {
		DeptUser deptUser = deptUserService.get(id);
		put("deptUser", deptUser);
		return "show";
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@Action("delete")
	public String delete() {
		DeptUser du = deptUserService.get(id);
		if (du == null) {
			return "-1";
		}
		du.setStatus(-1);
		deptUserService.update(du);
		write("1"); // ajax请求用write
		return null;
	}

	// ================= getter and setter ==========================
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
