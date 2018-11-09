package com.smart.web.action.dept;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.model.Dept;
import com.smart.model.DeptUser;
import com.smart.model.Role;
import com.smart.model.RoleRes;
import com.smart.model.User;
import com.smart.model.UserRole;
import com.smart.service.DeptService;
import com.smart.service.DeptUserService;
import com.smart.service.RoleResService;
import com.smart.service.RoleService;
import com.smart.service.UserRoleService;
import com.smart.service.UserService;
import com.smart.util.AppUtil;
import com.smart.util.StringUtil;
import com.smart.web.action.BaseAction;

@ParentPackage("control-user")
public class DeptAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private DeptService deptService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private DeptUserService deptUserService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private RoleResService roleResService;

	private String id;

	private Integer userId;

	private String userIds;

	private Integer duId;

	private Dept dept;

	private String order1;

	// private String ids;
	@Action("list")
	public String list() {
		Dept rootDept = deptService.get(this.getMyDept().getComid());
		put("rootDept", rootDept);
		List<Dept> deptList = deptService.getList(this.getMyDept().getComid());
		put("deptList", deptList);
		id = StringUtil.isBlank(id) ? getMyDeptId() : id;
		List<DeptUser> duList = deptUserService.getList(id);
		put("duList", duList);
		dept = deptService.get(id);
		return "list";
	}

	// @Action("list")
	// public String list() {
	//
	// List<Dept> deptList = deptService.getList(getMyDeptId());
	// put("deptList", deptList);
	//
	// id = StringUtil.isBlank(id) ? getMyDeptId() : id;
	// System.out.println(id);
	// List<DeptUser> duList = deptUserService.getList(id);
	// put("duList", duList);
	//
	// //dept = deptService.get(id);
	//
	// return "list";
	// }
	@Action("orderS")
	public String orderS() {
		String ss = deptService.get(id).getPid();
		int sss = deptService.get(id).getOrd();
		if (sss == 1) {
			write("1");
		} else {
			int s = sss - 1;// 获取上一个节点的ord
			Dept dep1 = deptService.getOrd(s, ss);
			Dept dep = deptService.getOrd(sss, ss);
			dep1.setOrd(dep.getOrd());
			deptService.update(dep1);
			dep.setOrd(s);
			deptService.update(dep);
		}
		return null;
	}

	@Action("orderX")
	public String orderX() {
		int iii = deptService.get(id).getOrd();
		int i = iii + 1;
		String ii = deptService.get(id).getPid();
		Dept dep = deptService.getOrd(i, ii);
		if (dep != null) {
			Dept dep1 = deptService.getOrd(iii, ii);
			dep.setOrd(dep1.getOrd());
			deptService.update(dep);
			dep1.setOrd(i);
			deptService.update(dep1);
		} else {
			write("1");
		}
		return null;
	}

	@Action("selectDept")
	public String selectDept() {
		// 根据公司ID获取公司信息
		Dept myCompany = deptService.get(this.getMyDept().getComid());
		put("myCompany", myCompany);
		// 根据公司ID查询公司下的所有部门
		List<Dept> deptList = deptService.getList(this.getMyDept().getComid());
		put("deptList", deptList);
		return "selectDept";
	}

	@Action("selectMultiDept")
	public String selectMultiDept() {
		// 根据公司ID获取公司信息
		Dept myCompany = deptService.get(this.getMyDept().getComid());
		put("myCompany", myCompany);
		// 根据公司ID查询公司下的所有部门
		List<Dept> deptList = deptService.getList(this.getMyDept().getComid());
		put("deptList", deptList);
		return "selectMultiDept";
	}

	@Action("new")
	public String _new() {
		if (StringUtil.isBlank(id) || "0".equals(id)) {
			id = getMyDeptId();
		}
		Dept parentDept = deptService.get(id);
		List<Dept> childDepts = deptService.getChildDeptByPid(id);
		put("parentDept", parentDept);
		put("childDepts", childDepts);
		return "new";
	}

	@Action("edit")
	public String edit() {
		dept = deptService.get(id);
		Dept parentDept = deptService.get(dept.getPid());
		List<Dept> childDepts = new ArrayList<Dept>();
		if (!dept.getPid().equals("001")) {
			childDepts = deptService.getChildDeptByPid(dept.getPid());
		} else {
			if (this.getMyDept().getId().equals("001")) {
				childDepts = deptService.getChildDeptByPid(dept.getPid());
			}
		}
		put("parentDept", parentDept);
		put("childDepts", childDepts);
		return "new";
	}

	@Action("save")
	public String save() {

		if (StringUtil.isBlank(id)) {
			String pid = dept.getPid();
			// 按部门Id排序，查询最后一个子部门
			Dept last = deptService.getLast(pid);
			String id = AppUtil.createMenuId(pid,
					last != null ? last.getId() : "");
			dept.setId(id);
			dept.setSname(dept.getName());
			dept.setUser(getUser());
			dept.setDept(getMyDept());

			// 根据父节点Id查询所有子部门
			List<Dept> de = deptService.getChildDeptByPid(pid);
			if (this.getMyDept().getComid().equals("001")) {
				dept.setComid(id);
				// dept.setOrd(de.get(de.size()-1).getOrd()+1);
			} else {
				dept.setComid(this.getMyDept().getComid());
			}
			if (de.size() > 0) {
				// 获取选择的部门
				Dept dep = deptService.get(order1);
				int ord1 = dep.getOrd();
				dept.setOrd(ord1 + 1);
				for (int i = 0; i < de.size(); i++) {
					Dept d = de.get(i);
					if (d.getOrd() > ord1) {
						d.setOrd(d.getOrd() + 1);
						deptService.update(d);
					}
				}
			} else {
				dept.setOrd(1);
			}
			deptService.save(dept);
		} else {
			Dept oldDept = deptService.get(id);
			oldDept.setName(dept.getName());
			if (!order1.equals(id)) {
				// 获取选择的部门
				Dept dep = deptService.get(order1);
				// 根据父节点Id查询所有子部门
				List<Dept> de = deptService.getChildDeptByPid(dept.getPid());
				for (int i = 0; i < de.size(); i++) {
					Dept d = de.get(i);
					// 向上移动
					if (oldDept.getOrd() > dep.getOrd()
							&& d.getOrd() > dep.getOrd()
							&& d.getOrd() < oldDept.getOrd()) {
						d.setOrd(d.getOrd() + 1);
						deptService.update(d);

					} else if (oldDept.getOrd() < dep.getOrd()
							&& d.getOrd() > oldDept.getOrd()
							&& d.getOrd() <= dep.getOrd()) {
						d.setOrd(d.getOrd() - 1);
						deptService.update(d);

					}
				}
				oldDept.setOrd(dep.getOrd() + 1);

			}
			deptService.update(oldDept);

		}
		write("1");
		return null;
	}

	@Action("delete")
	public String delete() {
		Dept dep = deptService.get(id);
		int ii = dep.getOrd() + 1;
		String pid = dep.getPid();
		List<Dept> dep1 = deptService.getChildDeptByPid(pid);
		int ord = dep1.get(dep1.size() - 1).getOrd() + 1;
		for (int i = ii; i < ord; i++) {
			Dept s = new Dept();
			s = deptService.getOrd(i, pid);
			s.setOrd(i - 1);
			deptService.update(s);
		}
		List<Dept> dep2 = deptService.getChildDeptByPid(id);// 删除所有下级部门
		dep2.add(dep);
		for (int i = 0; i < dep2.size(); i++) {
			Dept dept = dep2.get(i);
			dept.setStatus(-1);
			deptService.update(dept);

			List<DeptUser> listDeptUser = deptUserService.getList(dept.getId());// 清除相关联的表
			for (int j = 0; j < listDeptUser.size(); j++) {
				DeptUser deptUser = listDeptUser.get(j);
				deptUser.setStatus(-1);
				deptUserService.update(deptUser);
				List<UserRole> listUserRole = userRoleService.getUserRole(
						deptUser.getUser().getId(), deptUser.getDept().getId());
				for (int k = 0; k < listUserRole.size(); k++) {
					UserRole userRole = listUserRole.get(k);
					userRole.setStatus(-1);
					userRoleService.update(userRole);
				}
			}
			List<Role> listRole = roleService.getList(dept.getId());// 删除所有下级部门中角色
			for (int j = 0; j < listRole.size(); j++) {
				Role role = listRole.get(j);
				role.setStatus(-1);
				roleService.update(role);
				List<RoleRes> listRoleRes = roleResService
						.getByRole(role.getId());// 删除所有下级部门角色对应的权限
				for (int k = 0; k < listRoleRes.size(); k++) {
					RoleRes roleRes = listRoleRes.get(k);
					roleRes.setStatus(-1);
					roleResService.update(roleRes);
				}
			}

			// deptService.delete(dep2.get(i).getId());
		}
		// deptService.delete(id);
		write("1");
		return null;
	}

	@Action("addUser")
	public String addUser() {
		// 已经在这个部门里
		String[] ids = userIds.split(",");
		for (String uid : ids) {
			if (StringUtil.isBlank(uid)) {
				continue;
			}
			DeptUser du = deptUserService.get(id, Integer.parseInt(uid));
			if (du != null) {
				// write("-2");
				return null;
			}

			Dept dept = deptService.get(id);
			User user = userService.get(Integer.parseInt(uid));
			DeptUser deptUser = new DeptUser();
			deptUser.setDept(dept);
			deptUser.setUser(user);
			deptUserService.save(deptUser);
			// write("1");
		}
		return null;
	}

	@Action("deleteUser")
	public String deleteUser() {
		DeptUser deptUser = deptUserService.get(duId);
		deptUser.setStatus(-1);
		deptUserService.update(deptUser);
		List<UserRole> userRole = userRoleService.getMyUserRole(
				deptUser.getDept().getId(), deptUser.getUser().getId());
		for (UserRole u : userRole) {
			u.setStatus(-1);
			userRoleService.update(u);
		}
		write("1");
		return null;
	}

	// =================== getter and setter ======================
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public Integer getDuId() {
		return duId;
	}

	public void setDuId(Integer duId) {
		this.duId = duId;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public String getOrder1() {
		return order1;
	}

	public void setOrder1(String order1) {
		this.order1 = order1;
	}

}
