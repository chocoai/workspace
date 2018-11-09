package com.whty.assis.demo.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.whty.assis.base.controller.BaseController;
import com.whty.assis.demo.model.ManageUserInfo;
import com.whty.assis.demo.service_ht.ManageUserService;
import com.whty.assis.sysrole.model.SysModular;
import com.whty.assis.sysrole.service.SysroleService;
import com.whty.common.excel.FileVo;
import com.whty.common.excel.PoiUtil;
import com.whty.common.util.Constants;
import com.whty.common.util.GUIDGenerator;
import com.whty.common.util.SysConfig;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 管理后台用户管理
 * 
 * @author zhujg
 */
@Controller
@RequestMapping("/manageUser")
public class ManageUserController extends BaseController {

	// private static Logger logger =
	// Logger.getLogger(ManageUserController.class);
	private static Logger logger = LoggerFactory.getLogger(ManageUserController.class);
	@Autowired
	private ManageUserService manageUserService;
	@Autowired
	private SysroleService sysroleService;

	/**
	 * 首页头部数据加载
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/tologin")
	public String tologin(Model model) throws Exception {
		// 相关查询

		return "login";
	}

	/**
	 * 首页头部数据加载
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/login")
	public String login(Model model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "account", required = false) String account,
			@RequestParam(value = "password", required = false) String password) throws Exception {
		String code = "1";
		// 相关查询
		ManageUserInfo mUser = manageUserService.queryManageUserInfoByAccount(account);
		// 根据帐号查询后台用户并判断是否可以登录
		if (null != mUser) {
			if (mUser.getPassword().equals(password)) {
				if (mUser.getStatus().equals(Constants.MANAGE_USER_INVALID)) {
					// 帐号被禁用
					code = "3";
				} else {
					// 登录成功
					code = "0";
				}
			} else {
				// 密码不对
				code = "2";
			}
		} else {
			// 用户不存在
			code = "1";
		}

		/*
		 * 登录成功用户对象放session 更新登录次数等相关数据 查询用户菜单权限放session
		 */
		if (code.equals("0")) {
			request.getSession().setAttribute("manageUser", mUser);
			mUser.setLast_time(new Date());
			mUser.setLogin_count(1);
			mUser.setUpdate_time(new Date());
			manageUserService.updateManageUser(mUser);
			Map map = new HashMap();
			map.put("user_id", mUser.getId());
			List<SysModular> sysModularList = sysroleService.queryUserSysModularList(map);
			request.getSession().removeAttribute("sysModularList");
			request.getSession().setAttribute("sysModularList", sysModularList);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", code);
		return jsonObject.toString();
	}

	/*
	 * 注销、退出
	 */
	@RequestMapping(value = "/logout")
	public String logout(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession().removeAttribute("manageUser");
		response.sendRedirect("../login.html");
		return "index";
	}

	/*
	 * 修改密码
	 */
	@RequestMapping(value = "/changePassword")
	public void changePassword(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String newPassword = request.getParameter("newPassword");
		Date now = new Date();
		ManageUserInfo mUser = new ManageUserInfo();
		mUser.setId(id);
		mUser.setPassword(newPassword);
		mUser.setUpdate_time(now);
		manageUserService.updateManageUser(mUser);

		mUser = (ManageUserInfo) request.getSession().getAttribute("manageUser");
		mUser.setPassword(newPassword);
		mUser.setUpdate_time(now);
		request.getSession().setAttribute("manageUser", mUser);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write("success");
	}

	/**
	 * 管理员列表
	 */
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, HttpServletResponse response, Model model) {
		if (logger.isDebugEnabled()) {
			logger.debug("管理员列表数据加载");
		}
		try {
			String search_type = request.getParameter("search_type");
			String currentPage = request.getParameter("currPage");
			String pageSize = request.getParameter("pageSize");
			String totalPage = request.getParameter("totalPage");

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("account", request.getParameter("account"));
			paramMap.put("status", request.getParameter("status"));
			paramMap.put("role_name", request.getParameter("role_name"));

			PageContext page = PageContext.getContext();

			// 请自行验证
			if (null == currentPage || StringUtils.isNotEmpty(search_type)) {
				page.setCurrentPage(1);
				page.setPageSize(10);
				page.setTotalPage(0);
				page.setTotalRows(0);
			} else {
				page.setCurrentPage(Integer.parseInt(currentPage));
				page.setPageSize(Integer.parseInt(pageSize));
				page.setTotalPage(Integer.parseInt(totalPage));
			}
			page.setPagination(true);
			HandlerResult handlerResult = manageUserService.queryManageUser(paramMap);
			model.addAttribute("list", handlerResult.getResultList());
			model.addAttribute("page", page);
			model.addAllAttributes(paramMap);
			page.setPagination(false);
			String str = request.getParameter("msg");
			// if(str!=null&&!str.trim().equals("")){
			// str = new String(str.getBytes("ISO8859-1"),"UTF-8");
			// }
			model.addAttribute("msg", str);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return "managerUser/list";
	}

	/**
	 * 导出excel
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/exportexcel", method = RequestMethod.GET)
	@ResponseBody
	public void exportexcel(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			String name1 = request.getParameter("name");
			String name = new String(name1.getBytes("ISO-8859-1"), "utf-8");
			String type1 = request.getParameter("type");
			String type = new String(type1.getBytes("ISO-8859-1"), "utf-8");
			String status = request.getParameter("status");
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("account", name);
			paramMap.put("role_name", type);
			paramMap.put("status", status);
			@SuppressWarnings("unused")
			List<ManageUserInfo> userslist = manageUserService.queryallUsers(paramMap);
			String filename = "管理员信息";
			/*
			 * String[] headers = { "序号","用户名", "姓名", "用户角色", "账号状态",
			 * "创建时间","最后登录时间","登录次数","操作"};
			 */
			String[] tableHeader = { "帐号|account", "姓名|realName", "用户角色|rolename", "账号状态|status", "创建时间|creteTime",
					"最后登录时间|logintime", "登陆次数|counts" };
			List reportList = new ArrayList();
			Map cmap = null;
			ManageUserInfo manageUserInfo = null;
			for (int i = 0; i < userslist.size(); i++) {
				cmap = new HashMap();
				manageUserInfo = userslist.get(i);
				DateFormat sdf = new SimpleDateFormat("yyyy-MM-d HH:mm:ss");
				String createtime = "";
				if (null != manageUserInfo.getCreate_time()) {
					createtime = sdf.format(manageUserInfo.getCreate_time());
				}
				String logintime = "";
				if (null != manageUserInfo.getLast_time()) {
					logintime = sdf.format(manageUserInfo.getLast_time());
				}
				cmap.put("account", manageUserInfo.getAccount());
				cmap.put("realName", manageUserInfo.getUser_name());
				if (null != manageUserInfo.getRole_name()) {
					cmap.put("rolename", manageUserInfo.getRole_name());
				} else {
					cmap.put("rolename", "暂未授予角色");
				}

				if ("0".equals(manageUserInfo.getStatus())) {
					cmap.put("status", "正常");
				} else {
					cmap.put("status", "禁用");
				}

				cmap.put("creteTime", createtime);
				cmap.put("logintime", logintime);
				cmap.put("counts", manageUserInfo.getLogin_count());
				reportList.add(cmap);
			}
			String fileName = new String(filename.getBytes("GBK"), "ISO8859-1");
			ServletOutputStream os = null;
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-disposition", "attachment" + ";filename=" + fileName + ".xls");
			os = response.getOutputStream();
			HSSFWorkbook wb = new HSSFWorkbook();
			PoiUtil.createExcelSheet(wb, tableHeader, reportList);
			wb.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 导入excel
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/importexcel", method = RequestMethod.POST)
	public String upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request,
			Model model) {
		StringBuffer sb = new StringBuffer();
		int successCount = 0, allCount = 0, reCount = 0;// 成功导入数,总共要导入的记录数,重复帐号数
		String fileName = file.getOriginalFilename();
		// 文件重命名
		String fileRename = String.valueOf(System.currentTimeMillis()) + fileName.substring(fileName.lastIndexOf("."));
		File file3 = new File(SysConfig.getStrValue("file_path_pre") + SysConfig.getStrValue("temp_dir"));
		if (!file3.exists() || !file3.isDirectory()) {
			file3.mkdirs();
		}
		File targetFile = new File(file3 + File.separator + fileRename);
		if (!targetFile.exists()) {
			try {
				targetFile.createNewFile();
				file.transferTo(targetFile);
			} catch (Exception e) {
				sb.append("导入失败：文件处理错误");
				e.printStackTrace();
			}
		}
		FileVo fileVo = new FileVo();
		fileVo.setFile(targetFile);
		fileVo.setFileFileName(fileRename);
		List<Object> list;
		List<ManageUserInfo> managerlist = new ArrayList<ManageUserInfo>();
		try {
			list = PoiUtil.readerExcelSheet(fileVo, 1);
			allCount = list.size();
			for (int i = 0; i < allCount; i++) {
				String[] res = (String[]) list.get(i);
				// 不导入重复的账号
				ManageUserInfo tempUserInfo = manageUserService.queryManageUserInfoByAccount(res[0]);
				if (tempUserInfo != null) {
					reCount++;
					continue;
				}
				ManageUserInfo mUserInfo = new ManageUserInfo();
				mUserInfo.setId(GUIDGenerator.getGUID());
				mUserInfo.setAccount(res[0]);
				mUserInfo.setUser_name(res[1]);
				mUserInfo.setPassword(res[2]);
				Date mydate = new Date();
				mUserInfo.setCreate_time(mydate);
				mUserInfo.setLast_time(mydate);
				managerlist.add(mUserInfo);
				successCount++;
			}
			manageUserService.addManageUserList(managerlist);
			targetFile.delete();
		} catch (Exception e) {
			sb.append("导入数据出错");
			e.printStackTrace();
		}
		String msg = "总共" + allCount + "个帐号要导入,成功" + successCount + "个," + reCount + "个帐号已存在";
		if (sb.toString().equals("导入失败：文件处理错误")) {
			msg = "导入失败：文件处理错误";
		} else if (!sb.toString().equals("")) {
			msg = msg + ",第" + (successCount + reCount + 1) + "个" + sb.toString();
		}
		model.addAttribute("msg", msg);

		// 查询管理员列表
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			PageContext page = PageContext.getContext();
			page.setCurrentPage(1);
			page.setPageSize(10);
			page.setTotalPage(0);
			page.setTotalRows(0);
			page.setPagination(true);
			HandlerResult handlerResult = manageUserService.queryManageUser(paramMap);
			model.addAttribute("list", handlerResult.getResultList());
			model.addAttribute("page", page);
			model.addAttribute("listJson", JSONArray.fromObject(handlerResult.getResultList()));
			model.addAllAttributes(paramMap);
			page.setPagination(false);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return "managerUser/list";
	}

	/**
	 * 下载模板文件
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/uploadexcel", method = RequestMethod.GET)
	@ResponseBody
	public void downclient(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setHeader("Cache-Control", "no-cache");
		String aa = req.getSession().getServletContext().getRealPath("/");
		String fileurl = aa + "/doc/manageInfoTemplate.xls";
		File file = new File(fileurl);
		InputStream fis = new BufferedInputStream(new FileInputStream(fileurl));
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		// 清空response
		resp.reset();
		// 设置response的Header
		String filename = "管理员导入模版.xls";
		filename = URLEncoder.encode(filename, "UTF-8");
		if (filename.length() > 0) {
			String guessCharset = "gb2312";
			filename = new String(filename.getBytes(guessCharset), "ISO8859-1");
		}
		// 取得文件的后缀名。
		String ext = fileurl.substring(fileurl.lastIndexOf(".") + 1);
		String realfilename = filename;
		resp.setHeader("Content-Disposition", "attachment; filename=" + realfilename);
		resp.addHeader("Content-Length", "" + file.length());
		ServletOutputStream sout = resp.getOutputStream();
		OutputStream toClient = new BufferedOutputStream(sout);
		resp.setContentType("application/octet-stream");
		toClient.write(buffer);
		toClient.flush();
		toClient.close();
		sout.close();

	}

	/**
	 * 新建和编辑管理员信息
	 */
	@RequestMapping(value = "/save")
	public void save(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String id = request.getParameter("id");
		String account = request.getParameter("account");
		String user_name = request.getParameter("user_name");
		String password = request.getParameter("password");

		ManageUserInfo mUser = new ManageUserInfo();
		mUser.setId(id);
		mUser.setAccount(account);
		mUser.setUser_name(user_name);
		mUser.setPassword(password);

		String result = null;
		if (StringUtils.isEmpty(id)) {
			ManageUserInfo existsUser = manageUserService.queryManageUserInfoByAccount(account);
			if (existsUser != null) {
				result = "fail";
			} else {
				mUser.setId(GUIDGenerator.getGUID());
				mUser.setCreate_time(new Date());
				mUser.setUpdate_time(new Date());
				manageUserService.addManageUser(mUser);
				result = "success";
			}
		} else {
			mUser.setUpdate_time(new Date());
			manageUserService.updateManageUser(mUser);
			result = "success";
		}

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write(result);
	}

	/**
	 * 启用和禁用
	 */
	@RequestMapping(value = "/enableAndDisable")
	public void enableAndDisable(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		String ids = request.getParameter("ids");
		String status = request.getParameter("status");
		List idList = Arrays.asList(ids.split(","));

		Map paramap = new HashMap();
		paramap.put("idList", idList);
		paramap.put("status", status);

		manageUserService.updateManageUserStatus(paramap);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write("success");
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String ids = request.getParameter("ids");
		List idList = Arrays.asList(ids.split(","));

		Map paramap = new HashMap();
		paramap.put("idList", idList);

		manageUserService.deleteManageUser(paramap);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write("success");
	}
}
