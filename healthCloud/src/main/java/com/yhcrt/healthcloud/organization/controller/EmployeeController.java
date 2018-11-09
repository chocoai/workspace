package com.yhcrt.healthcloud.organization.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yhcrt.healthcloud.base.controller.BaseController;
import com.yhcrt.healthcloud.common.Constants;
import com.yhcrt.healthcloud.common.DataTable;
import com.yhcrt.healthcloud.memberBack.entity.MemberBack;
import com.yhcrt.healthcloud.memberBack.service.MemberBackService;
import com.yhcrt.healthcloud.organization.entity.Employee;
import com.yhcrt.healthcloud.organization.entity.Organization;
import com.yhcrt.healthcloud.organization.service.EmployeeService;
import com.yhcrt.healthcloud.system.entity.SysRole;
import com.yhcrt.healthcloud.system.entity.SysUser;
import com.yhcrt.healthcloud.system.entity.UserRole;
import com.yhcrt.healthcloud.system.service.SysRoleService;
import com.yhcrt.healthcloud.system.service.SysUserService;
import com.yhcrt.healthcloud.system.service.UserRoleService;
import com.yhcrt.healthcloud.util.DateUtil;
import com.yhcrt.healthcloud.util.Md5PwdEncoder;
import com.yhcrt.healthcloud.util.NetworkUtil;
import com.yhcrt.healthcloud.util.StringUtil;
import com.yhcrt.healthcloud.util.exportUtil;
import com.yhcrt.healthcloud.util.importUtil;

/* @Description: 机构用户管理下的员工管理模块
 * @version	1.0		2017年5月8日
 * @author jimmy
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController extends BaseController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private MemberBackService memberBackService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private UserRoleService userRoleService;

	/**
	 * 跳转到员工列表页
	 * @param request
	 * @param response
	 * @param status
	 * @param orgId
	 * @param userCode
	 * @param realName
	 * @param specialty
	 * @return
	 */
	@RequestMapping("/list")
	public String search(HttpServletRequest request, HttpServletResponse response) {
		String orgId = request.getParameter("orgId");
		String status = request.getParameter("status");
		String userCode = request.getParameter("userCode");
		if(StringUtils.isNotBlank(userCode)){
			request.setAttribute("userCode", userCode.trim());
		}
		String realName = request.getParameter("realName");
		if(StringUtils.isNotBlank(realName)){
			request.setAttribute("realName", realName.trim());
		}
		String specialty = request.getParameter("specialty");
		if(StringUtils.isNotBlank(specialty)){
			request.setAttribute("specialty", specialty.trim());
		}
		Organization selectOrg;
		if (orgId == null) {
			selectOrg = getLoginOrg(getLoginUser());
		} else {
			selectOrg = organizationService.selectByPrimaryKey(StringUtils.isNotBlank(orgId)?Integer.parseInt(orgId):null);
		}
		if (!getRoleIdList().isEmpty()) {
			List<SysRole> role_list = sysRoleService.queryByList(getRoleIdList());
			request.setAttribute("role_list", role_list);
		}
		request.setAttribute("emp", new Employee());
		request.setAttribute("org", selectOrg);
		if (StringUtils.isNotBlank(status) && "1".equals(status)) {
			return "orgUsers/employee/disabled";
		} else {
			return "orgUsers/employee/list";
		}
	}
	
	/**
	 * 返回表格查询数据
	 * @param request
	 * @param response
	 * @param modelMap
	 */
	@RequestMapping(value = "/pageList")
	public void pageList(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> map = new HashMap<String,Object>();
		String orgId = request.getParameter("orgId");
		String status = request.getParameter("status");
        map.put("status", StringUtils.isNotBlank(status)?Integer.parseInt(status):0);
		String userCode = request.getParameter("userCode");
		if(StringUtils.isNotBlank(userCode)){
			map.put("userCode", userCode.trim());
		}
		String realName = request.getParameter("realName");
		if(StringUtils.isNotBlank(realName)){
			map.put("realName", realName.trim());
		}
		String specialty = request.getParameter("specialty");
		if(StringUtils.isNotBlank(specialty)){
			map.put("specialty", specialty.trim());
		}
		try {
			DataTable dataTable = new DataTable(request);
			List<Integer> orgId_list = getOrgList(StringUtils.isNotBlank(orgId)?Integer.parseInt(orgId):null);
	        map.put("orgId_list", orgId_list);
			List<Employee> list = new ArrayList<Employee>();
			if (!getRoleIdList().isEmpty()) {
				map.put("roleList", getRoleIdList());
				PageHelper.startPage(dataTable.getPageNum(), dataTable.getPageSize());
				list = employeeService.queryList(map);
			}
			PageInfo<Employee> pageInfo = new PageInfo<Employee>(list);
			//封装数据给DataTables  
			dataTable.setDraw(dataTable.getDraw());  
			dataTable.setData(pageInfo.getList());
			dataTable.setRecordsTotal((int)pageInfo.getTotal());    
			dataTable.setRecordsFiltered(dataTable.getRecordsTotal()); 
			String jsonString = JSON.toJSONString(dataTable);
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Title: 编辑员工信息
	 * @Description: 新建员工时userId为null，则不会携带信息到edit页面，
	 *               编辑员工信息时则根据userId查出员工将其信息携带到edit页面
	 * @param request
	 * @param response
	 * @param userId
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request, HttpServletResponse response, Integer empId) {
		// 获取当前登录用户的机构
		SysUser loginUser = getLoginUser();
		Organization org = getLoginOrg(loginUser);
		// 判断是添加还是编辑，是编辑的话会将要编辑的员工信息传到页面上
		Employee emp = new Employee();
		if (empId != null) {
			emp = employeeService.select(empId);
		}else{
			emp.setOrg(org);
		}
		// 根据当前登录用户查询其角色
		if (!getRoleIdList().isEmpty()) {
			List<SysRole> role_list = sysRoleService.queryByList(getRoleIdList());
			request.setAttribute("role_list", role_list);
		}
		request.setAttribute("emp", emp);
		return "orgUsers/employee/edit";
	}

	/**
	 * @Title: 保存员工信息
	 * @Description: 接收ajax请求，保存在edit页面填写的员工信息并返回保存结果
	 * @param request
	 * @param response
	 * @param emp
	 * @return 返回保存结果而不是一个页面
	 */
	@RequestMapping("/save")
	public String insert(HttpServletRequest request, HttpServletResponse response, Employee emp, String userCode,
			String pwd, String repwd, Integer roleId, RedirectAttributes attr) {
		// attr 为spring自带的为重定向添加值的类
		// 判断是否含有员工ID信息，如果有则为修改，没有则为新增
		// 获取登录用户
		SysUser loginUser = getLoginUser();
		Organization org = getLoginOrg(loginUser);
		if (loginUser == null) {
			return "redirect:login";
		}
		if (emp != null && emp.getEmpId() != null) {
			emp.setUpdateTime(DateUtil.getDateTime());
			Employee oldEmp = employeeService.select(emp.getEmpId());
			// 将emp中的值复制到oldEmp中，后面写了的属性除外
			BeanUtils.copyProperties(emp, oldEmp, "createUser", "createTime", "userId", "status");
			int i = 0;
			try {
				// 先更新权限信息，失败回滚
				userRoleService.update(roleId, oldEmp.getUserId());
				i = employeeService.update(oldEmp);
			} catch (RuntimeException e) {
				i = 0;
			}
			if (i == 1) {
				attr.addFlashAttribute("result", "更新成功");
				return "redirect:list";
			} else {
				attr.addFlashAttribute("result", "更新失败");
				return "redirect:list";
			}
		} else {
			// 后台验证
			if (!validate(emp, userCode, pwd, repwd, attr)) {
				attr.addFlashAttribute("result", "输入参数不合法！");
				return "redirect:list";
			}
			if (emp.getOrgId() == null) {
				emp.setOrgId(org.getOrgId());
			}
			// 创建登录信息
			SysUser user = new SysUser();
			Integer userId = (sysSequenceService.getSequenceValue(Constants.SequenceName.SYS_USER));
			user.setUserId(userId);
			user.setUserCode(userCode);
			if (!StringUtil.isEmpty(pwd)) {
				user.setPassword(Md5PwdEncoder.encodePassword(pwd));
			} else {
				user.setPassword(Md5PwdEncoder.encodePassword("123456"));
			}
			user.setUserType(0);
			user.setStatus(Constants.STATUS_NORMAL);
			user.setCreateTime(sdf.format(new Date()));
			user.setUpdateTime(sdf.format(new Date()));
			user.setCreateUser(loginUser.getUserCode());
			try {
				user.setCreateIp(NetworkUtil.getIpAddress(request));
			} catch (IOException e) {
				System.out.println("获取IP异常");
				user.setCreateIp("127.0.0.1");
			}
			emp.setEmpId(sysSequenceService.getSequenceValue(Constants.SequenceName.EMPLOYEE));
			emp.setUserId(userId);
			emp.setCreateTime(sdf.format(new Date()));
			emp.setUpdateTime(sdf.format(new Date()));
			emp.setCreateUser(loginUser.getUserCode());
			emp.setStatus(Constants.STATUS_NORMAL);
			int i = 0;
			try {
				// 两个中有一个失败就回滚
				userRoleService.update(roleId, userId);
				i = employeeService.insert(emp, user);
			} catch (RuntimeException r) {
				i = 0;
			}
			if (i == 1) {
				attr.addFlashAttribute("result", "添加成功");
				return "redirect:list";
			} else {
				attr.addFlashAttribute("result", "添加失败");
				return "redirect:list";
			}
		}
	}

	/**
	 * @Title: resetPwd
	 * @Description: 重置密码
	 * @param request
	 * @param response
	 * @param empId
	 * @return
	 */
	@RequestMapping("/resetPwd")
	@ResponseBody
	public String resetPwd(HttpServletRequest request, HttpServletResponse response, Integer empId) {
		Employee emp = employeeService.select(empId);
		SysUser user = sysUserService.selUser(emp.getUserId());
		if (user != null) {
			user.setPassword(Md5PwdEncoder.encodePassword("123456"));
			user.setUpdateTime(sdf.format(new Date()));
			int i = sysUserService.update(user);
			if (i == 1) {
				return "重置成功";
			} else {
				return "重置失败";
			}
		}
		return "没有查到登录信息，重置失败";
	}

	/**
	 * @Title: 禁用员工
	 * @Description: 禁用选中的员工，即将该员工的状态改为1
	 * @param request
	 * @param response
	 * @param emp
	 * @return 禁用结果
	 */
	@RequestMapping("/disabled")
	@ResponseBody
	public String disabled(HttpServletRequest request,HttpServletResponse response, Integer empId) {
		Employee emp = employeeService.select(empId);
		SysUser user = sysUserService.selUser(emp.getUserId());
		List<MemberBack> member_list = memberBackService.selectByEmpId(empId, Constants.STATUS_NORMAL);
		for (MemberBack member : member_list) {
			member.setEmpId(null);
			member.setUpdateTime(sdf.format(new Date()));
			memberBackService.update(member);
		}
		int i = 0;
		emp.setUpdateTime(sdf.format(new Date()));
		emp.setStatus(Constants.STATUS_DISABLE);
		user.setUpdateTime(sdf.format(new Date()));
		user.setStatus(Constants.STATUS_DISABLE);
		try {
			i = employeeService.delete(emp, user);
		} catch (NullPointerException n) {
			i = 0;
		} catch (RuntimeException r) {
			i = 0;
		}
		if (i == 1) {
			return "禁用成功";
		} else {
			return "禁用失败";
		}
	}

	/**
	 * @Title: 恢复被禁用的员工
	 * @Description: 恢复选中的被禁用员工，即将该员工的状态改为1
	 * @param request
	 * @param response
	 * @param emp
	 * @return 恢复结果
	 */
	@RequestMapping("/recover")
	@ResponseBody
	public String recover(HttpServletRequest request, HttpServletResponse response, Integer empId) {
		Employee emp = employeeService.select(empId);
		SysUser user = sysUserService.selUser(emp.getUserId());
		emp.setUpdateTime(sdf.format(new Date()));
		emp.setStatus(Constants.STATUS_NORMAL);
		user.setUpdateTime(sdf.format(new Date()));
		user.setStatus(Constants.STATUS_NORMAL);
		int i = 0;
		try {
			i = employeeService.recover(emp, user);
		} catch (RuntimeException r) {
			i = 0;
		}
		if (i == 1) {
			return "恢复成功";
		} else {
			return "恢复失败";
		}
	}

	/**
	 * @Title: 表格下载功能
	 * @Description: 员工信息导出
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/export")
	public void employeeExport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> mapParam = new HashMap<String,Object>();
		String orgId = request.getParameter("orgId");
		String status = request.getParameter("status");
		mapParam.put("status", StringUtils.isNotBlank(status)?Integer.parseInt(status):0);
		String userCode = request.getParameter("userCode");
		if(StringUtils.isNotBlank(userCode)){
			mapParam.put("userCode", userCode.trim());
		}
		String realName = request.getParameter("realName");
		if(StringUtils.isNotBlank(realName)){
			mapParam.put("realName", realName.trim());
		}
		String specialty = request.getParameter("specialty");
		if(StringUtils.isNotBlank(specialty)){
			mapParam.put("specialty", specialty.trim());
		}
		List<Integer> orgId_list = getOrgList(StringUtils.isNotBlank(orgId)?Integer.parseInt(orgId):null);
		mapParam.put("orgId_list", orgId_list);
		List<Employee> empList = new ArrayList<Employee>();
		if (!getRoleIdList().isEmpty()) {
			mapParam.put("roleList", getRoleIdList());
			empList = employeeService.queryList(mapParam);
		}
		// 表格头
		String[] entity = { "所属机构", "账号", "姓名", "身份证号", "性别", "职位", "手机", "邮箱" };
		// list存放所有结果集
		List<Map<Integer, Object>> list = new ArrayList<Map<Integer, Object>>();
		Map<Integer, Object> map = null;
		for (Employee emp : empList) {
			// map存放一行数据，所以每一行都要新建一个map
			map = new HashMap<Integer, Object>();
			Object[] value = {
					emp.getOrg() == null ? "" : emp.getOrg().getOrgName(),
					emp.getUser() == null ? "" : emp.getUser().getUserCode(),
					emp.getRealName(), emp.getIdentityCard(),
					emp.getSex() == 1 ? "男" : "女", emp.getSpecialty(),
					emp.getPhoneNo(), emp.getEmail() };
			// 先将要存的数据放入到一个Object数组，然后遍历数组，将值存到map里
			for (int y = 0; y < value.length; y++) {
				map.put(y, value[y]);
			}
			// 将每一行的结果存放到结果集里
			list.add(map);
		}
		String name = "员工数据导出" + DateUtil.getDateTime("yyyyMMddHHmmss");
		Workbook wb = null;
		try {
			wb = exportUtil.create(name, entity, list);
		} catch (FileNotFoundException e1) {
			System.out.println("文件没有找到");
		} catch (IOException e) {
			System.out.println("IO异常");
		}
		String fileName = "员工数据.xlsx";
		response.setContentType("application/vnd.ms-excel");
		try {
			response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		OutputStream ouputStream;
		try {
			ouputStream = response.getOutputStream();
			wb.write(ouputStream);
			ouputStream.flush();
			ouputStream.close();
		} catch (IOException e) {
			System.out.println("IO异常");
		}
	}

	/**
	 * @Title: 表格上传功能
	 * @Description: 员工信息导入
	 * @param request
	 * @param response
	 * @param mfile
	 * @return 提示
	 * @throws Exception
	 */
	@RequestMapping("/upload")
	@ResponseBody
	public String employeeImport(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("file") MultipartFile mfile, Integer orgId, Integer roleId) throws Exception {
		SysUser loginUser = getLoginUser();
		String userName = getRealName(loginUser);
		String date = DateUtil.getDateTime();
		request.setCharacterEncoding("utf-8"); // 设置编码
		importUtil importUtilWeb = new importUtil();
		// 获取父目录
		String filePath = new File(request.getSession().getServletContext().getRealPath("/")).getParent();
		File dest = new File(filePath + "/temp/" + userName + DateUtil.getDateTime("yyyy-MM-dd") + "导入员工.xlsx");
		try {
			if (!dest.getParentFile().exists()) {
				dest.getParentFile().mkdirs();
			}
			mfile.transferTo(dest);
		} catch (IOException e) {
			System.out.println("上传文件异常");
		}
		List<Map<Integer, Object>> list = importUtilWeb.read(dest, 7);
		if (list == null || list.size() == 0) {
			return "-1";
		}
		List<Map<Integer, Object>> errorList = new ArrayList<Map<Integer, Object>>();
		List<SysUser> userList = new ArrayList<SysUser>();
		List<Employee> empList = new ArrayList<Employee>();
		List<UserRole> roleList = new ArrayList<UserRole>();
		for (Map<Integer, Object> map : list) {
			SysUser user = new SysUser();
			user.setUserId(sysSequenceService.getSequenceValue(Constants.SequenceName.SYS_USER));
			user.setUserCode(map.get(0).toString());
			user.setPassword(Md5PwdEncoder.encodePassword("123456"));
			user.setUserType(0);
			user.setStatus(0);
			user.setCreateUser(loginUser.getUserCode());
			user.setCreateTime(date);
			user.setUpdateTime(date);
			try {
				user.setCreateIp(NetworkUtil.getIpAddress(request));
			} catch (IOException e) {
				System.out.println("获取IP异常");
				user.setCreateIp("127.0.0.1");
			}

			Employee emp = new Employee();
			emp.setOrgId(orgId);
			emp.setEmpId(sysSequenceService.getSequenceValue(Constants.SequenceName.EMPLOYEE));
			emp.setUserId(user.getUserId());
			emp.setRealName(map.get(1).toString());
			emp.setIdentityCard(map.get(2).toString());
			emp.setSex("男".equals(map.get(3)) ? 1 : 0);
			emp.setSpecialty(map.get(4).toString());
			emp.setPhoneNo(map.get(5).toString());
			emp.setEmail(map.get(6).toString());
			emp.setCreateUser(loginUser.getUserCode());
			emp.setCreateTime(date);
			emp.setUpdateTime(date);
			emp.setStatus(0);

			UserRole userRole = new UserRole();
			userRole.setCid(sysSequenceService.getSequenceValue(Constants.SequenceName.USER_ROLE));
			userRole.setUserId(user.getUserId());
			userRole.setRoleId(roleId);
			userRole.setCreateTime(date);
			if (validate(emp, user.getUserCode())) {
				userList.add(user);
				empList.add(emp);
				roleList.add(userRole);
			} else {
				errorList.add(map);
			}
		}
		if (empList.size() > 0) {
			try {
				employeeService.batchAdd(userList, empList, roleList);
			} catch (Exception e) {
				System.out.println("创建对象异常，不添加该条数据");
			}
		}
		return empList.size() + "," + errorList.size();
	}

	/**
	 * @Title: managementMember
	 * @Description: 显示员工管理的会员
	 * @return
	 */
	@RequestMapping("/management")
	public String managementMember(HttpServletRequest request, HttpServletResponse response, Integer empId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", Constants.STATUS_NORMAL);
		Employee emp = employeeService.select(empId);
		List<Integer> orgIdList = getOrgList(emp.getOrgId());
		map.put("orgIdList", orgIdList);
		//获取未分配会员
		List<MemberBack> fromList = memberBackService.queryByEid(map);
		//获取已分配会员
		List<MemberBack> toList = memberBackService.selectByEmpId(empId,Constants.STATUS_NORMAL);
		request.setAttribute("emp", emp);
		request.setAttribute("fromList", fromList);
		request.setAttribute("toList", toList);
		return "orgUsers/employee/management";
	}
	
	/**
	 * ajax异步查询可分配会员
	 * @param request
	 * @param response
	 * @param empId
	 * @param realName
	 * @param phoneNo
	 * @param status
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryMan")
	public List<MemberBack> queryMan(HttpServletRequest request, HttpServletResponse response,
			String empId, String orgId, String realName, String phoneNo, Integer[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("empId", Integer.parseInt(empId));
		map.put("status", Constants.STATUS_NORMAL);
		List<Integer> orgIdList = getOrgList(StringUtils.isNotBlank(orgId) ? Integer.parseInt(orgId) : null);
		map.put("orgIdList", orgIdList);
		if(StringUtils.isNotBlank(realName)){
			map.put("realName", realName.trim());
		}
		if(StringUtils.isNotBlank(phoneNo)){
			map.put("phoneNo", phoneNo.trim());
		}
		if(ids != null && ids.length > 0){
			List<Integer> list = StringUtil.toArray(ids);
			map.put("memberIdList", list);
		}
		List<MemberBack> fromList = memberBackService.queryByEid(map);
		return fromList;
	}

	/**
	 * 保存分配的会员
	 * @param request
	 * @param response
	 * @param empId
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveBatch")
	public Integer saveBatch(HttpServletRequest request, HttpServletResponse response, Integer empId, Integer[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("empId", empId);
		// 根据empId修改会员表中的empId为空
		memberBackService.updateByEmpId(map);
		if(ids != null && ids.length > 0){
			// 将数组转化为list集合
			List<Integer> list = StringUtil.toArray(ids);
			map.put("list", list);
			map.put("updateTime", DateUtil.getDateTime());
			//批量分配
			Integer i = memberBackService.updateBatchEid(map);
			if (i > 0) {
				return 1;
			}else{
				return 0;
			}
		}
		return 1;
	}
	
	/**
	 * @Title: checkUserCode
	 * @Description: 验证账号是否已被占用
	 * @param userCode
	 * @return 验证结果
	 */
	@RequestMapping("/checkUserCode")
	@ResponseBody
	public boolean checkUserCode(String userCode) {
		SysUser user = sysUserService.selectByUserCode(userCode);
		if (user == null) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: checkID
	 * @Description: 检查身份证号是否被占用
	 * @param imei
	 * @return
	 */
	@RequestMapping("/checkID")
	@ResponseBody
	public boolean checkID(String identityCard, Integer empId) {
		Employee employee = new Employee();
		employee.setEmpId(empId);
		employee.setIdentityCard(identityCard);
		// 根据排除empId判断identityCard在表中是否存在
		int count = employeeService.countExtEmpId(employee);
		if (count == 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: validate
	 * @Description: 添加/编辑员工验证
	 * @param emp
	 * @param userCode
	 * @param pwd
	 * @param repwd
	 * @param attr
	 * @return
	 */
	private boolean validate(Employee emp, String userCode, String pwd,
			String repwd, RedirectAttributes attr) {
		if (StringUtil.isEmpty(userCode) || StringUtil.isEmpty(pwd)) {
			attr.addFlashAttribute("result", "添加失败,必填值不能为空");
			return false;
		}
		if (StringUtil.isEmpty(emp.getRealName())
				|| StringUtil.isEmpty(emp.getIdentityCard())
				|| StringUtil.isEmpty(emp.getPhoneNo())) {
			attr.addFlashAttribute("result", "添加失败,必填值不能为空");
			return false;
		}
		if (!checkUserCode(userCode)) {
			attr.addFlashAttribute("result", "添加失败,用户名已存在");
			return false;
		}
		if (!pwd.equals(repwd)) {
			attr.addFlashAttribute("result", "添加失败,两次输入的密码不相同");
			return false;
		}
		return true;
	}

	/**
	 * @Title: validate
	 * @Description: 批量导入员工验证
	 * @param emp
	 * @param userCode
	 * @return
	 */
	private boolean validate(Employee emp, String userCode) {
		if (StringUtil.isEmpty(userCode)) {
			return false;
		}
		if (StringUtil.isEmpty(emp.getRealName())
				|| StringUtil.isEmpty(emp.getIdentityCard())
				|| StringUtil.isEmpty(emp.getPhoneNo())
				|| emp.getOrgId() == null) {
			return false;
		}
		if (!checkUserCode(userCode)) {
			return false;
		}
		return true;
	}

}
