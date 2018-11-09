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
import com.yhcrt.healthcloud.organization.entity.Doctor;
import com.yhcrt.healthcloud.organization.entity.Organization;
import com.yhcrt.healthcloud.organization.service.DoctorService;
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

/**
 * @Description: 机构用户管理下的医师管理模块
 * @version 1.0 2017年5月8日
 * @author jimmy
 *
 */
@Controller
@RequestMapping("/doctor")
public class DoctorController extends BaseController {

	@Autowired
	private DoctorService doctorService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private MemberBackService memberBackService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private UserRoleService userRoleService;

	/**
	 * @Title: 根据条件查询医师
	 * @Description: 根据传入的条件查询出对应的医师信息，将结果集返回到list页面。
	 * @param request
	 * @param response
	 * @param orgId
	 * @param userCode
	 * @param realName
	 * @param specialty
	 * @param status
	 * @return 携带结果集返回到医师列表页
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
        if(orgId == null){
        	selectOrg = getLoginOrg(getLoginUser());
        }else{
        	selectOrg = organizationService.selectByPrimaryKey(StringUtils.isNotBlank(orgId)?Integer.parseInt(orgId):null);
        }
		if(!getRoleIdList().isEmpty()) {
			List<SysRole> role_list = sysRoleService.queryByList(getRoleIdList());
			request.setAttribute("role_list", role_list);
		}
		request.setAttribute("doc", new Doctor());
		request.setAttribute("org", selectOrg);
		if(StringUtils.isNotBlank(status) && "1".equals(status)) {
			return "orgUsers/doctor/disabled";
		} else {
			return "orgUsers/doctor/list";
		}
	}
	
	/**
	 * 返回表格查询数据
	 * @param request
	 * @param response
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
	        List<Doctor> list = new ArrayList<Doctor>();
			if (!getRoleIdList().isEmpty()) {
				map.put("roleList", getRoleIdList());
				PageHelper.startPage(dataTable.getPageNum(), dataTable.getPageSize());
				list = doctorService.queryList(map);
			}
			PageInfo<Doctor> pageInfo = new PageInfo<Doctor>(list);
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
	 * @Title: 编辑医师信息
	 * @Description: 新建医师时userId为null，则不会携带信息到edit页面，
	 *               编辑医师信息时则根据userId查出医师将其信息携带到edit页面
	 * @param request
	 * @param response
	 * @param userId
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request, HttpServletResponse response, Integer docId) {
		// 获取当前登录用户的机构
		SysUser loginUser = getLoginUser();
		Organization org = getLoginOrg(loginUser);
		Doctor doc = new Doctor();
		if (docId != null) {
			doc = doctorService.select(docId);
		}else{
			doc.setOrg(org);
		}
		if (!getRoleIdList().isEmpty()) {
			List<SysRole> role_list = sysRoleService.queryByList(getRoleIdList());
			request.setAttribute("role_list", role_list);
		}
		request.setAttribute("doc", doc);
		return "orgUsers/doctor/edit";
	}

	/**
	 * @Title: 保存医师信息
	 * @Description: 接收ajax请求，保存在edit页面填写的医师信息并返回保存结果
	 * @param request
	 * @param response
	 * @param doc
	 * @return 返回保存结果而不是一个页面
	 */
	@RequestMapping("/save")
	public String insert(HttpServletRequest request, HttpServletResponse response, Doctor doc, String userCode,
			String pwd, String repwd, Integer roleId, RedirectAttributes attr) {
		SysUser loginUser = getLoginUser();
		Organization org = getLoginOrg(loginUser);
		// 判断是否含有医师ID信息，如果有则为修改，没有则为新增
		if (doc != null && doc.getDocId() != null) {
			doc.setUpdateTime(sdf.format(new Date()));
			Doctor oldDoc = doctorService.select(doc.getDocId());
			BeanUtils.copyProperties(doc, oldDoc, "createUser", "createTime", "userId", "status");
			int i = 0;
			try {
				// 先更新权限信息，失败回滚
				userRoleService.update(roleId, oldDoc.getUserId());
				i = doctorService.update(oldDoc);
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
			if (!validate(doc, userCode, pwd, repwd, attr)) {
				attr.addFlashAttribute("result", "输入参数不合法！");
				return "redirect:list";
			}
			if (doc.getOrgId() == null) {
				doc.setOrgId(org.getOrgId());
			}
			SysUser user = new SysUser();
			Integer userId = (sysSequenceService.getSequenceValue(Constants.SequenceName.SYS_USER));
			user.setUserId(userId);
			user.setUserCode(userCode);
			if (!StringUtil.isEmpty(pwd)) {
				user.setPassword(Md5PwdEncoder.encodePassword(pwd));
			} else {
				user.setPassword(Md5PwdEncoder.encodePassword("123456"));
			}
			user.setUserType(-1);
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
			doc.setDocId(sysSequenceService.getSequenceValue(Constants.SequenceName.DOCTOR));
			doc.setUserId(userId);
			doc.setCreateTime(sdf.format(new Date()));
			doc.setUpdateTime(sdf.format(new Date()));
			doc.setCreateUser(loginUser.getUserCode());
			doc.setStatus(Constants.STATUS_NORMAL);
			int i = 0;
			try {
				// 两个中有一个失败就回滚
				userRoleService.update(roleId, userId);
				i = doctorService.insert(doc, user);
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
	 * @param docId
	 * @return
	 */
	@RequestMapping("/resetPwd")
	@ResponseBody
	public String resetPwd(HttpServletRequest request, HttpServletResponse response, Integer docId) {
		Doctor doc = doctorService.select(docId);
		SysUser user = sysUserService.selUser(doc.getUserId());
		if (user != null) {
			user.setPassword(Md5PwdEncoder.encodePassword("123456"));
			user.setUpdateTime(sdf.format(new Date()));
			int i = sysUserService.update(user);
			if (i == 1) {
				return "重置成功";
			} else {
				return "系统异常，重置失败";
			}
		}
		return "没有查到登录信息，重置失败";
	}

	/**
	 * @Title: 禁用医师
	 * @Description: 禁用选中的医师，即将该医师的状态改为1
	 * @param request
	 * @param response
	 * @param doc
	 * @return 禁用结果
	 */
	@RequestMapping("/disabled")
	@ResponseBody
	public String disabled(HttpServletRequest request, HttpServletResponse response, Integer docId) {
		Doctor doc = doctorService.select(docId);
		SysUser user = sysUserService.selUser(doc.getUserId());
		List<MemberBack> memberList = memberBackService.selectByDocId(docId, Constants.STATUS_NORMAL);
		for (MemberBack member : memberList) {
			member.setDocId(null);
			member.setUpdateTime(sdf.format(new Date()));
			memberBackService.update(member);
		}
		int i = 0;
		doc.setUpdateTime(sdf.format(new Date()));
		doc.setStatus(Constants.STATUS_DISABLE);
		user.setUpdateTime(sdf.format(new Date()));
		user.setStatus(Constants.STATUS_DISABLE);
		try {
			i = doctorService.delete(doc, user);
		} catch (NullPointerException n) {
			System.out.println("没有对应的user对象");
			i = 0;
		} catch (RuntimeException r) {
			System.out.println("新增异常，开始回滚");
			i = 0;
		}
		if (i == 1) {
			return "禁用成功";
		} else {
			return "禁用失败";
		}
	}

	/**
	 * @Title: 恢复被禁用的医师
	 * @Description: 恢复选中的被禁用医师，即将该医师的状态改为1
	 * @param request
	 * @param response
	 * @param doc
	 * @return 恢复结果
	 */
	@RequestMapping("/recover")
	@ResponseBody
	public String recover(HttpServletRequest request, HttpServletResponse response, Integer docId) {
		Doctor doc = doctorService.select(docId);
		SysUser user = sysUserService.selUser(doc.getUserId());
		doc.setUpdateTime(sdf.format(new Date()));
		doc.setStatus(Constants.STATUS_NORMAL);
		user.setUpdateTime(sdf.format(new Date()));
		user.setStatus(Constants.STATUS_NORMAL);
		int i = 0;
		try {
			i = doctorService.recover(doc, user);
		} catch (RuntimeException r) {
			System.out.println("恢复异常，开始回滚");
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
	 * @Description: 医师信息导出
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/export")
	public void doctorExport(HttpServletRequest request, HttpServletResponse response) {
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
		List<Doctor> docList = new ArrayList<Doctor>();
		if (!getRoleIdList().isEmpty()) {
			mapParam.put("roleList", getRoleIdList());
			docList = doctorService.queryList(mapParam);
		}
		String[] entity = { "所属机构", "账号", "姓名", "身份证号", "性别", "职位", "手机", "邮箱" };
		List<Map<Integer, Object>> list = new ArrayList<Map<Integer, Object>>();
		Map<Integer, Object> map = null;
		for (Doctor doc : docList) {
			map = new HashMap<Integer, Object>();
			Object[] value = {
					doc.getOrg() == null ? "" : doc.getOrg().getOrgName(),
					doc.getUser() == null ? "" : doc.getUser().getUserCode(), 
					doc.getRealName(), 
					doc.getIdentityCard(),
					doc.getSex() == 1 ? "男" : "女", 
					doc.getSpecialty(),
					doc.getPhoneNo().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"), 
					doc.getEmail() 
			};
			for (int y = 0; y < entity.length; y++) {
				map.put(y, value[y]);
			}
			list.add(map);
		}
		String name = "医师数据导出" + DateUtil.getDateTime("yyyyMMddhhmmss");

		Workbook wb = null;
		try {
			wb = exportUtil.create(name, entity, list);
		} catch (FileNotFoundException e1) {
			System.out.println("文件没有找到");
		} catch (IOException e) {
			System.out.println("IO异常");
		}
		String fileName = "医师数据.xlsx";
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
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
	 * @Description: 医师信息导入
	 * @param request
	 * @param response
	 * @param mfile
	 * @return 提示
	 * @throws Exception
	 */
	@RequestMapping("/upload")
	@ResponseBody
	public String doctorImport(HttpServletRequest request, HttpServletResponse response, Integer orgId, Integer roleId,
			@RequestParam("file") MultipartFile mfile) throws Exception {
		SysUser loginUser = getLoginUser();
		String userName = getRealName(loginUser);
		String date = DateUtil.getDateTime();
		// 设置编码
		request.setCharacterEncoding("utf-8");
		importUtil importUtilWeb = new importUtil();
		// 获取父目录
		String filePath = new File(request.getSession().getServletContext().getRealPath("/")).getParent();
		File dest = new File(filePath + "/temp/" + userName + DateUtil.getDateTime("yyyy-MM-dd") + "导入医师.xlsx");
		try {
			if (!dest.getParentFile().exists()) {
				dest.getParentFile().mkdirs();
			}
			mfile.transferTo(dest);
		} catch (IOException e) {
			throw e;
		}
		List<Map<Integer, Object>> list = importUtilWeb.read(dest, 7);
		if (list == null || list.size() == 0) {
			return "-1";
		}
		// 添加失败的数据
		List<Map<Integer, Object>> errorList = new ArrayList<Map<Integer, Object>>();
		List<SysUser> userList = new ArrayList<SysUser>();
		List<Doctor> docList = new ArrayList<Doctor>();
		List<UserRole> roleList = new ArrayList<UserRole>();
		for (Map<Integer, Object> map : list) {

			SysUser user = new SysUser();
			user.setUserId(sysSequenceService.getSequenceValue(Constants.SequenceName.SYS_USER));
			user.setUserCode(map.get(0).toString());
			user.setPassword(Md5PwdEncoder.encodePassword("123456"));
			user.setUserType(-1);
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
			
			Doctor doc = new Doctor();
			doc.setDocId(sysSequenceService.getSequenceValue(Constants.SequenceName.DOCTOR));
			doc.setUserId(user.getUserId());
			doc.setOrgId(orgId);
			doc.setRealName(map.get(1).toString());
			doc.setIdentityCard(map.get(2).toString());
			doc.setSex("男".equals(map.get(3).toString()) ? 1 : 0);
			doc.setSpecialty(map.get(4).toString());
			doc.setPhoneNo(map.get(5).toString());
			doc.setEmail(map.get(6).toString());
			doc.setCreateUser(loginUser.getUserCode());
			doc.setCreateTime(date);
			doc.setUpdateTime(date);
			doc.setStatus(0);
			
			UserRole userRole = new UserRole();
			userRole.setCid(sysSequenceService.getSequenceValue(Constants.SequenceName.USER_ROLE));
			userRole.setUserId(user.getUserId());
			userRole.setRoleId(roleId);
			userRole.setCreateTime(date);
			
			if (validate(doc, user.getUserCode())) {
				userList.add(user);
				docList.add(doc);
				roleList.add(userRole);
			} else {
				errorList.add(map);
			}
		}
		if(docList.size()>0){
			try {
				doctorService.batchAdd(userList,docList,roleList);
			} catch (Exception e) {
				System.out.println("创建对象异常，不添加该条数据");
			}
		}
		return docList.size() + "," + errorList.size();
	}

	/**
	 * 跳转分配界面显示该医师可分配和已分配会员
	 * @param request
	 * @param response
	 * @param docId
	 * @return
	 */
	@RequestMapping("/management")
	public String managementMember(HttpServletRequest request, HttpServletResponse response, Integer docId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", Constants.STATUS_NORMAL);
		Doctor doc = doctorService.select(docId);
		List<Integer> orgIdList = getOrgList(doc.getOrgId());
		map.put("orgIdList", orgIdList);
		//查询可分配医师的会员
		List<MemberBack> fromList = memberBackService.selectByDid(map);
		//查询已分配医师的会员
		List<MemberBack> toList = memberBackService.selectByDocId(docId, Constants.STATUS_NORMAL);
		request.setAttribute("doc", doc);
		request.setAttribute("fromList", fromList);
		request.setAttribute("toList", toList);
		return "orgUsers/doctor/management";
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
			String docId, String orgId, String realName, String phoneNo, Integer[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("docId", Integer.parseInt(docId));
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
		List<MemberBack> fromList = memberBackService.selectByDid(map);
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
	public Integer saveBatch(HttpServletRequest request, HttpServletResponse response, Integer docId, Integer[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("docId", docId);
		// 根据docId修改会员表中的docId为空
		memberBackService.updateByDocId(map);
		if(ids != null && ids.length > 0){
			// 将数组转化为list集合
			List<Integer> list = StringUtil.toArray(ids);
			map.put("list", list);
			map.put("updateTime", DateUtil.getDateTime());
			//批量分配
			Integer i = memberBackService.updateBatchDid(map);
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
	public boolean checkID(String identityCard, Integer docId) {
		// 根据排除docId判断identityCard在表中是否存在
		Doctor doc = new Doctor();
		doc.setDocId(docId);
		doc.setIdentityCard(identityCard);
		int count = doctorService.countExtDocId(doc);
		if (count == 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: validate
	 * @Description: 添加员工验证
	 * @param emp
	 * @param userCode
	 * @param pwd
	 * @param repwd
	 * @param attr
	 * @return
	 */
	private boolean validate(Doctor doc, String userCode, String pwd, String repwd, RedirectAttributes attr) {
		if (StringUtil.isEmpty(userCode) || StringUtil.isEmpty(pwd)) {
			attr.addFlashAttribute("result", "添加失败,必填值不能为空");
			return false;
		}
		if (StringUtil.isEmpty(doc.getRealName()) || StringUtil.isEmpty(doc.getIdentityCard())
				|| StringUtil.isEmpty(doc.getPhoneNo())) {
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
	private boolean validate(Doctor doc, String userCode) {
		if (StringUtil.isEmpty(userCode)) {
			return false;
		}
		if (StringUtil.isEmpty(doc.getRealName()) || StringUtil.isEmpty(doc.getIdentityCard())
				|| StringUtil.isEmpty(doc.getPhoneNo())) {
			return false;
		}
		if (!checkUserCode(userCode)) {
			return false;
		}
		return true;
	}

}
