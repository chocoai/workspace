package com.smart.web.action.user;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.model.Dept;
import com.smart.model.DeptUser;
import com.smart.model.User;
import com.smart.model.UserRole;
import com.smart.service.DeptService;
import com.smart.service.DeptUserService;
import com.smart.service.UserRoleService;
import com.smart.service.UserService;
import com.smart.util.Page;
import com.smart.util.ReflectionUtil;
import com.smart.util.StringUtil;
import com.smart.web.action.BaseAction;

@ParentPackage("control-user")
public class UserAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private UserService userService;

	@Autowired
	private DeptService deptService;

	@Autowired
	private DeptUserService deptUserService;

	@Autowired
	private UserRoleService userRoleService;

	private String name;

	private String name1;

	private Integer id;

	private User u;

	private String deptId;

	private String projectId;

	private String worker; // 查询任务分配的员工

	private String ids;// 获取项目id

	private String type; // session

	private String start; // checked的状态 1 选中 0 取消

	private String values; // 选中的Userid

	private Map<String, String> map;

	@Action(value = "list")
	public String list() {
		Dept company = getMyCompany();
		Page<User> pageBean = userService.getPage(pageNo, pageSize, company,
				name);
		put("pageBean", pageBean);
		return "list";
	}

	@Action(value = "save", results = {
			@Result(name = "list", location = "list", type = "redirectAction") })
	public String save() {
		if (id == null) {
			// 新增用户
			u.setUser(getUser());
			// 设置公司
			u.setComid(this.getMyDept().getComid());
			u.setDept(getMyDept());

			id = userService.save(u);
		} else {
			// 修改用户
			User user = userService.get(id);
			// 属性拷贝，把更新内容拷贝到数据库查出的project里，最后的那个参数是不需要拷贝的属性名称
			ReflectionUtil.bean2Bean(user, u, "id,username, status,comid");
			userService.update(user);

			List<DeptUser> du = deptUserService.getList(id);
			deptUserService.delete(du);
		}
		if (StringUtils.isNotBlank(deptId)) {
			String[] deptIds = deptId.split(",");
			for (int i = 0; i < deptIds.length; i++) {
				Dept dept = deptService.get(deptIds[i]);
				User user = userService.get(id);
				DeptUser deptUser = new DeptUser();
				deptUser.setDept(dept);
				deptUser.setUser(user);
				deptUserService.save(deptUser);
			}
		}
		return "list";
	}

	@Action("new")
	public String _new() {
		u = new User();
		return "new";
	}

	@Action("edit")
	public String edit() {
		u = userService.get(id);
		List<DeptUser> duList = deptUserService.getList(id);
		StringBuffer deptIds = new StringBuffer();
		StringBuffer deptNames = new StringBuffer();
		for (DeptUser du : duList) {
			Dept dept = du.getDept();
			deptIds.append(dept.getId()).append(",");
			deptNames.append(dept.getName()).append(",");
		}
		put("deptIds", deptIds.toString());
		put("deptNames", deptNames.toString());
		return "new";
	}

	@Action("revisepass")
	public String revisepass() {
		u = userService.get(getUser().getId());
		return "revisepass";
	}

	/**
	 * 判断密码是否正确
	 * 
	 * @return
	 */
	@Action("checkPassword")
	public String checkPassword() {
		String oldPassword = get("oldPassword");
		if (getUser().getPassword().equals(oldPassword)
				|| getUser().getPassword() == oldPassword) {
			write("1");
		} else {
			write("0");
		}
		return null;
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	@Action("revisepassword")
	public String revisepassword() {
		u = userService.get(id);
		String newPassword = get("newPassword");
		u.setPassword(newPassword);
		userService.update(u);
		write("1");
		return null;
	}

	@Action("delete")
	public String delete() {
		u = userService.get(id);
		if (u != null) {
			u.setStatus(-1);
			userService.update(u);
		}
		List<DeptUser> duList = deptUserService.getList(id);// 删除用户与部门的关系
		for (DeptUser deptUser : duList) {
			deptUser.setStatus(-1);
			deptUserService.update(deptUser);
		}
		List<UserRole> userRole = userRoleService.getMyUserRole(null, id);// 删除用户与角色的关系
		for (UserRole userRole2 : userRole) {
			userRole2.setStatus(-1);
			userRoleService.update(userRole2);
		}
		write("1");
		return null;
	}

	/**
	 * 判断用户名是否存在
	 * 
	 * @return
	 */
	@Action("checkNO")
	public String checkNO() {
		User user = userService.get(u.getUsername());
		if (user == null) {
			write("1");
		} else {
			write("0"); // ajax请求用write返回数据
		}
		return null;
	}

	@Action("selectUser")
	public String selectUser() {
		if (!StringUtil.isBlank(worker) && !StringUtil.isBlank(projectId)) { // 查询任务分配的员工
			List<User> userList = deptUserService.getList2(projectId);
			// List<string> li2 = new List<string> { "张三", "张三", "李四", "张三",
			// "王五", "李四" };
			for (int i = 0; i < userList.size(); i++) // 外循环是循环的次数
			{
				for (int j = userList.size() - 1; j > i; j--) // 内循环是 外循环一次比较的次数
				{

					if (userList.get(i) == userList.get(j)) {
						userList.remove(j);
					}

				}
			}
			put("userList", userList);
		} else if (StringUtil.isBlank(deptId)) {
			List<User> userList = deptUserService.getList1(getMyDeptId(),
					this.getMyDept().getComid());
			// List<User>
			// userList=UserService.getAll1(getMyDeptId(),this.getMyDept().getComid());
			put("userList", userList);
		} else {
			List<User> userList = deptUserService.getList1(deptId,
					this.getMyDept().getComid());
			put("userList", userList);
		}
		return "selectUser";
	}

	@Action("selectUser1")
	public String selectUser1() {// 获取一般多用户
		Page<User> pageBean = userService.getAll1(getPageNo(), 10, name1,
				this.getMyDept().getComid());
		put("pageBean", pageBean);
		return "selectUser1";

	}

	@Action("selectMultiUser")
	public String selectMultiUser() {
		Page<User> pageBean = userService.getAll1(getPageNo(), 9999,
				name1, this.getMyDept().getComid());
		put("pageBean", pageBean);
		String userIdId = get("userIdId");
		String userNameId = get("userNameId");
		put("userIdId", userIdId);
		put("userNameId", userNameId);
		return "selectMultiUser";
	}

	@Action("selectUser2")
	public String selectUser2() {
		if (StringUtil.isBlank(deptId)) {// 单选全部用户
			Page<User> pageBean = userService.getAll1(getPageNo(),
					getPageSize(), name1, this.getMyDept().getComid());
			put("pageBean", pageBean);
		} else {
			Page<User> pageBean = deptUserService.getList2(getPageNo(),
					getPageSize(), name1, deptId, this.getMyDept().getComid());
			put("pageBean", pageBean);
		}
		return "selectUser2";
	}

	@Action("getUserByDeptId")
	public String getUserByDeptId() {
		if (StringUtil.isBlank(deptId)) {
			Page<User> pageBean = userService.getAll1(getPageNo(),
					9999, name1, this.getMyDept().getComid());
			put("pageBean", pageBean);
		} else {
			Page<User> pageBean = deptUserService.getList2(getPageNo(),
					getPageSize(), name1, deptId, this.getMyDept().getComid());
			put("pageBean", pageBean);
		}
		String userIdId = get("userIdId");
		String userNameId = get("userNameId");
		put("userIdId", userIdId);
		put("userNameId", userNameId);
		return "getUserByDeptId";
	}

	/**
	 * 获取多个部门下的用户
	 * 
	 * @return
	 */
	@Action("getUserByDeptIds")
	public String getUserByDeptIds() {
		String deptIds = get("deptId");
		String name = get("name");
		Page<DeptUser> pageBean = deptUserService.getUserByDeptIds(getPageNo(),
				getPageSize(), getMyCompanyId(), deptIds, name);
		put("pageBean", pageBean);
		put("name", name);
		return "getUserByDeptIds";
	}

	@Action("selectUser3")
	// 获取多用户id和多项目id 用于共享
	public String selectUser3() {
		Page<User> pageBean = userService.getAll1(getPageNo(), 10, name1,
				this.getMyDept().getComid());
		put("pageBean", pageBean);
		put("ids", ids);
		return "selectUser3";
	}

	@SuppressWarnings("unchecked")
	@Action("selectUser4")
	public String selectUser4() {
		if (type.equals("0")) {// 将id保存到session
			if ((Map<String, String>) getSession()
					.get("user_session") == null) {
				map = new HashMap<String, String>();
			} else {
				map = (Map<String, String>) getSession().get("user_session");
			}
			if (start != null && values != null) {
				if (start.equals("1") && map.get(values) == null) {
					String vname = userService.get(Integer.parseInt(values))
							.getName();
					map.put(values, vname);
				}
				if (map.get(values) != null && start.equals("0")) {
					map.remove(values);
				}
			}
			getSession().put("user_session", map);
			String ids = "";
			String names = "";
			if (map != null) {
				Iterator<?> it = map.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry<String, String> entry = (Map.Entry<String, String>) it
							.next();
					String value = (String) entry.getKey();
					String name = (String) entry.getValue();
					if (ids.equals("")) {
						ids = value;
						names = name;
					} else {
						ids = ids + "," + value;
						names = names + "," + name;
					}
				}
			}
			write(ids + "==,,,==" + names);
		} else if (type.equals("1")) {// 清除session
			getSession().remove("user_session");
			write("1");
		}
		return null;
	}

	// ================= getter and setter ======================

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

	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getWorker() {
		return worker;
	}

	public void setWorker(String worker) {
		this.worker = worker;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getType() {
		return type;
	}

	public String getIds() {
		return ids;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
}