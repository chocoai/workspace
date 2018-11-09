package com.smart.web.action.index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.model.Dept;
import com.smart.model.Res;
import com.smart.model.Role;
import com.smart.model.ServiceType;
import com.smart.model.User;
import com.smart.service.DeptUserService;
import com.smart.service.ResService;
import com.smart.service.RoleService;
import com.smart.service.ServiceTypeService;
import com.smart.service.UserRoleService;
import com.smart.service.UserService;
import com.smart.util.AppUtil;
import com.smart.util.StringUtil;
import com.smart.web.action.BaseAction;

@Results({
		@Result(name = "login", location = "/WEB-INF/content/index/login.ftl"),
		@Result(name = "error", location = "/WEB-INF/content/index/error.ftl"),
		@Result(name = "pageNotFound", location = "/WEB-INF/content/index/pageNotFound.ftl"),
		@Result(name = "error404", location = "/WEB-INF/content/index/error404.ftl"),
		@Result(name = "error500", location = "/WEB-INF/content/index/error500.ftl") })
public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 8877561106386540362L;

	private final Log log = LogFactory.getLog(getClass());

	@Autowired
	private UserService userService;

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private DeptUserService deptUserService;

	@Autowired
	private ResService resService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private ServiceTypeService serviceTypeService;

	private String username;

	private String password;

	private String roleId;

	@Action("/error")
	public String error() {
		return "error";
	}

	@Action("/pageNotFound")
	public String error1() {
		return "pageNotFound";
	}

	@Action("/error404")
	public String error404() {
		return "error404";
	}

	@Action("/error500")
	public String error500() {
		return "error500";
	}

	@Action("/login")
	public String login() {
		getSession().remove("user");
		getSession().remove("currentRoleId");
		getSession().remove("project_session");
		return "login";
	}

	@Action(value = "/logout", results = {
			@Result(name = "logout", location = "login", type = "redirectAction") })
	public String logout() {
		getSession().remove("user");
		getSession().remove("currentRoleId");
		getSession().remove("project_session");
		return "logout";
	}

	@Action("/checkUser")
	public String checkUser() throws Exception {
		try {
			// 咨询类型
			List<ServiceType> serviceTypeList = serviceTypeService.getAll();
			getSession().put("serviceTypeList", serviceTypeList);
			User user = userService.checkLoginByUserName(username, password);
			// 中文名登录
			user = user == null
					? userService.checkLoginByName(username, password) : user;

			if (user == null) {
				write("-1");
			} else {
				// 查询登录用户所在的所有部门
				List<Dept> myDeptList = deptUserService
						.getDeptByUser(user.getId());
				getSession().put("myDeptList", myDeptList);
				// 用户没有关联组织
				if (myDeptList == null || myDeptList.size() == 0) {
					log.info("当前用户没有与组织关联");
				}
				// 查询登录用户的所有角色
				List<Role> myAllRoleList = userRoleService
						.getMyRole(user.getId());
				getSession().put("myAllRoleList", myAllRoleList);
				// 没有为用户分配角色
				if (myAllRoleList == null || myAllRoleList.size() == 0) {
					log.info("当前用户没有配置角色");
				}

				String lastRoleId = user.getLastRoleId(); // 此字段存最后一次登录角色
				if (StringUtil.isBlank(lastRoleId)
						&& myAllRoleList.size() > 0) {
					lastRoleId = myAllRoleList.get(0).getId() + "";
					user.setLastRoleId(lastRoleId);
				}

				Map<String, Res> myResMap = new HashMap<String, Res>();
				Role role = new Role();
				if (!StringUtil.isBlank(lastRoleId)) {
					// 根据角色ID获取该角色所拥有的权限资源
					myResMap = resService.getMyRes(lastRoleId);
					// 页面上权限判断使用
					getSession().put("myResMap", myResMap);
					getSession().put("currentRoleId", lastRoleId); // 当前角色
					role = roleService.get(Integer.valueOf(lastRoleId));
				}

				if (role != null) {
					Dept myDept = role.getDept();
					Dept myCompany = role.getDept();
					getSession().put("myDept", myDept); // 当前部门
					getSession().put("myCompany", myCompany); // 当前公司
				}

				List<Integer> stepcontrol = new ArrayList<Integer>(); // 经营与项目权限
				if (myResMap.containsKey("002001002")) { // 项目立项编辑
					stepcontrol.add(-5);
				}
				if (myResMap.containsKey("002002002")) { // 投标策划编辑
					stepcontrol.add(-4);
				}
				// if(myResMap.containsKey("002006002")){ //投实施编辑
				// stepcontrol.add(-3);
				// }
				if (myResMap.containsKey("002003002")) { // 投标总结编辑
					stepcontrol.add(-3);
				}
				if (myResMap.containsKey("002005002")) { // 合同评审编辑
					stepcontrol.add(-2);
				}
				if (myResMap.containsKey("002004002")) { // 合同登记编辑
					stepcontrol.add(-1);
				}
				if (myResMap.containsKey("003001001")) { // 第1步编辑
					stepcontrol.add(1);
				}
				if (myResMap.containsKey("003002001")) { // 第2步编辑
					stepcontrol.add(2);
				}
				if (myResMap.containsKey("003003001")) { // 第3步编辑
					stepcontrol.add(3);
				}
				if (myResMap.containsKey("003004001")) { // 第4步编辑
					stepcontrol.add(4);
				}
				if (myResMap.containsKey("003005001")) { // 第5步编辑
					stepcontrol.add(5);
				}
				if (myResMap.containsKey("003006001")) { // 第6步编辑
					stepcontrol.add(6);
				}
				if (myResMap.containsKey("003007001")) { // 第7步编辑
					stepcontrol.add(7);
				}
				if (myResMap.containsKey("003008001")) { // 第8步编辑
					stepcontrol.add(8);
				}
				if (myResMap.containsKey("003009001")) { // 第9步编辑
					stepcontrol.add(9);
				}
				if (myResMap.containsKey("003010001")) { // 第10步编辑
					stepcontrol.add(10);
				}
				if (myResMap.containsKey("003011001")) { // 第11步编辑
					stepcontrol.add(11);
				}
				if (myResMap.containsKey("003012001")) { // 第12步编辑
					stepcontrol.add(12);
				}
				if (myResMap.containsKey("003013001")) { // 第13步编辑
					stepcontrol.add(13);
				}
				if (myResMap.containsKey("003014001")) { // 第14步编辑
					stepcontrol.add(14);
				}
				if (myResMap.containsKey("003015001")) { // 第15步编辑
					stepcontrol.add(15);
				}

				userService.update(user);
				getSession().put("stepControl", stepcontrol);
				getSession().put("user", user);
				getSession().put("SUPERADMIN", AppUtil.SUPERADMIN); // 特殊修改权限

				// 这里用url做key再存放一份，UserInterceptor中权限判断使用
				Map<String, Res> resMap = new HashMap<String, Res>();
				for (Entry<String, Res> map : myResMap.entrySet()) {
					Res res = map.getValue();
					resMap.put(res.getUrl(), res);
				}
				getSession().put("resMap", resMap);
				write("1");
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	/**
	 * 切换角色
	 * 
	 * @return
	 */
	@Action(value = "/changeDept", results = {
			@Result(name = "workbench", location = "workbench.htm", type = "redirectAction") })
	public String changeDept() {
		try {

			Map<String, Res> myResMap = new HashMap<String, Res>();
			Role role = new Role();
			if (!StringUtil.isBlank(roleId)) {
				myResMap = resService.getMyRes(roleId);
				getSession().put("myResMap", myResMap);
				getSession().put("currentRoleId", roleId);
				role = roleService.get(Integer.valueOf(roleId));
			}

			if (role != null) {
				Dept myDept = role.getDept();
				Dept myCompany = role.getDept();
				getSession().put("myDept", myDept); // 当前部门
				getSession().put("myCompany", myCompany); // 当前公司
			}

			User user = (User) getSession().get("user");
			user = userService.get(user.getId());
			user.setLastRoleId(roleId);
			userService.update(user);

			List<Integer> stepcontrol = new ArrayList<Integer>(); // 经营与项目权限
			if (myResMap.containsKey("002001002")) { // 项目立项编辑
				stepcontrol.add(-5);
			}
			if (myResMap.containsKey("002002002")) { // 投标策划编辑
				stepcontrol.add(-4);
			}
			// if(myResMap.containsKey("002006002")){ //投实施编辑
			// stepcontrol.add(-3);
			// }
			if (myResMap.containsKey("002003002")) { // 投标总结编辑
				stepcontrol.add(-3);
			}
			if (myResMap.containsKey("002005002")) { // 合同评审编辑
				stepcontrol.add(-2);
			}
			if (myResMap.containsKey("002004002")) { // 合同登记编辑
				stepcontrol.add(-1);
			}
			if (myResMap.containsKey("003001001")) { // 第1步编辑
				stepcontrol.add(1);
			}
			if (myResMap.containsKey("003002001")) { // 第2步编辑
				stepcontrol.add(2);
			}
			if (myResMap.containsKey("003003001")) { // 第3步编辑
				stepcontrol.add(3);
			}
			if (myResMap.containsKey("003004001")) { // 第4步编辑
				stepcontrol.add(4);
			}
			if (myResMap.containsKey("003005001")) { // 第5步编辑
				stepcontrol.add(5);
			}
			if (myResMap.containsKey("003006001")) { // 第6步编辑
				stepcontrol.add(6);
			}
			if (myResMap.containsKey("003007001")) { // 第7步编辑
				stepcontrol.add(7);
			}
			if (myResMap.containsKey("003008001")) { // 第8步编辑
				stepcontrol.add(8);
			}
			if (myResMap.containsKey("003009001")) { // 第9步编辑
				stepcontrol.add(9);
			}
			if (myResMap.containsKey("003010001")) { // 第10步编辑
				stepcontrol.add(10);
			}
			if (myResMap.containsKey("003011001")) { // 第11步编辑
				stepcontrol.add(11);
			}
			if (myResMap.containsKey("003012001")) { // 第12步编辑
				stepcontrol.add(12);
			}
			if (myResMap.containsKey("003013001")) { // 第13步编辑
				stepcontrol.add(13);
			}
			if (myResMap.containsKey("003014001")) { // 第14步编辑
				stepcontrol.add(14);
			}
			if (myResMap.containsKey("003015001")) { // 第15步编辑
				stepcontrol.add(15);
			}

			getSession().put("stepControl", stepcontrol);
			getSession().put("user", user);

			Map<String, Res> resMap = new HashMap<String, Res>();
			for (Entry<String, Res> map : myResMap.entrySet()) {
				Res res = map.getValue();
				resMap.put(res.getUrl(), res);
			}
			getSession().put("resMap", resMap);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "workbench";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}
