package com.smart.web.action.role;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.model.Dept;
import com.smart.model.Role;
import com.smart.model.RoleRes;
import com.smart.model.User;
import com.smart.model.UserRole;
import com.smart.service.DeptService;
import com.smart.service.RoleResService;
import com.smart.service.RoleService;
import com.smart.service.UserRoleService;
import com.smart.service.UserService;
import com.smart.util.Page;
import com.smart.util.ReflectionUtil;
import com.smart.util.StringUtil;
import com.smart.web.action.BaseAction;

@ParentPackage("control-user")
public class RoleAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private RoleResService roleResService;

	@Autowired
	private DeptService deptService;
	
	@Autowired
	private UserService userService;

	private Integer id;

	private Integer userId;

	private String deptId;

	private Role role;

	private String roleIdList;

	@Action("selectRole")
	public String selectRole() {
		Dept dept = deptService.get(deptId);
		List<Role> roleList = roleService.getList(dept.getComid());
		put("roleList", roleList);

		List<Role> myRoleList = userRoleService.getMyRole(null, userId);
		put("myRoleList", myRoleList);

		return "selectRole";
	}

	@Action("saveUserRole")
	public String saveUserRole() {
		roleService.saveUserRole(deptId, userId, roleIdList);
		write("1");
		return null;
	}

	@Action("list")
	public String list() {
		if (StringUtil.isBlank(deptId)) {
			// 当前登录用户的部门id
			deptId = getMyDeptId();
		}
		// 获取当前登录用户所在公司
		Dept myCompany = deptService.get(this.getMyDept().getComid());
		put("myCompany", myCompany);
		// 获取当前登录用户所在部门或者选定部门下的角色
		List<Role> roleList = roleService.getList(deptId);
		put("roleList", roleList);
		Page<Role> pageBean = roleService.getPage(pageNo, pageSize, deptId);
		put("pageBean", pageBean);
		// 获取当前登录用户所在公司的所有部门
		List<Dept> deptList = deptService.getList(this.getMyDept().getComid());
		put("deptList", deptList);
		// 若当前登录用户所在部门是部门树状图根节点，则查询下级所有公司节点
		Dept parentDept = deptService.get(this.getMyDept().getPid());
		if (parentDept == null) {
			List<Dept> companyList = deptService
					.getChildDeptByPid(this.getMyDept().getId());
			put("companyList", companyList);
		}

		return "list";
	}

	@Action("new")
	public String _new() {
		Dept dept = deptService.get(deptId);
		put("dept", dept);
		return "new";
	}

	@Action("edit")
	public String edit() {
		role = roleService.get(id);
		return "new";
	}

	@Action(value = "save", results = {
			@Result(name = "list", location = "list", type = "redirectAction", params = {
					"deptId", "${deptId}" }) })
	public String save() {
		if (id == null) {
			role.setDept(deptService.get(deptId));
			role.setUser(getUser());
			role.setStatus(1);
			roleService.save(role);
		} else {
			Role oldRole = roleService.get(id);
			ReflectionUtil.bean2Bean(oldRole, role,
					"id,user,dept,ctime,status");
			roleService.update(oldRole);
		}
		return "list";
	}

	@Action("delete")
	public String delete() {
		role = roleService.get(id);// 删除角色
		role.setStatus(-1);
		roleService.update(role);
		userService.updateByLastId(id);
		List<RoleRes> listRoleRes = roleResService.getByRole(id);// 删除权限
		for (RoleRes roleRes : listRoleRes) {
			roleRes.setStatus(-1);
			roleResService.update(roleRes);
		}
		List<UserRole> listUserRole = userRoleService.getByRole(id);// 删除关联表
		for (UserRole userRole : listUserRole) {
			userRole.setStatus(-1);
			userRoleService.update(userRole);
		}
		write("1");
		return null;
	}

	// ==================== getter and setter ===========================
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getDeptId() {

		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(String roleIdList) {
		this.roleIdList = roleIdList;
	}

}
