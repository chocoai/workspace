/**
 * 
 */
package com.whty.wfd.page.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whty.wfd.base.controller.BaseController;
import com.whty.wfd.common.utils.ChineseCharacterUtil;
import com.whty.wfd.common.utils.GUIDGenerator;
import com.whty.wfd.page.dao.TClassMapper;
import com.whty.wfd.page.dao.TClassPlateMapper;
import com.whty.wfd.page.dao.TClassUserMapper;
import com.whty.wfd.page.dao.TPlateMapper;
import com.whty.wfd.page.dao.TUserMapper;
import com.whty.wfd.page.model.TClass;
import com.whty.wfd.page.model.TClassPlate;
import com.whty.wfd.page.model.TClassPlateExample;
import com.whty.wfd.page.model.TClassUserExample;
import com.whty.wfd.page.model.TPlate;
import com.whty.wfd.page.model.TUser;
import com.whty.wfd.page.service.ManagerService;
import com.whty.wfd.page.service.impl.BasePropertyServiceImpl;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 管理员页面
 * 
 * @author zhangzheng
 * @date 2018年8月18日
 */
@Controller
@RequestMapping("/page/manager")
public class ManagerController extends BaseController {

	@Autowired
	private TClassMapper tClassMapper;

	@Autowired
	private TUserMapper tUserMapper;

	@Autowired
	private TPlateMapper tPlateMapper;

	@Autowired
	private ManagerService managerService;;

	@Autowired
	private MemcachedClient memcachedClient;

	@Autowired
	private TClassPlateMapper tClassPlateMapper;

	@Autowired
	private TClassUserMapper tClassUserMapper;

	/**
	 * 查询版块所有用户
	 * 
	 * @param request
	 * @param model
	 * @param response
	 */
	@RequestMapping("atUser")
	public String atUser(HttpServletRequest request, Model model, HttpServletResponse response) {
		return "manager/atUser";
	}

	/**
	 * 查询版块根据人名模糊查询
	 */
	@RequestMapping("getPlateUser")
	@ResponseBody
	public void getPlateUser(HttpServletRequest request, HttpServletResponse response, String plateId,
			String userName) {
		TUser tUser = (TUser) request.getSession().getAttribute("userObj");
		Map<String, Object> plateParam = new HashMap<>();
		plateParam.put("plateId", plateId);
		plateParam.put("schoolId", tUser.getSchoolId());
		plateParam.put("userName", "%" + userName + "%");
		List<TUser> allUser = tUserMapper.selectUserByPlateName(plateParam);
		JSONObject obj = new JSONObject();
		obj.put("users", allUser);
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		try {
			response.getWriter().print(obj.toString());
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 查询版块所有用户
	 * 
	 * @param request
	 * @param model
	 * @param response
	 */
	@RequestMapping("allPlateUser")
	public String allPlateUser(HttpServletRequest request, Model model, HttpServletResponse response, String postId,
			String content, String turn) {
		TUser tUser = (TUser) request.getSession().getAttribute("userObj");
		String plateId = request.getParameter("plateId");

		Map<String, Object> plateParam = new HashMap<String, Object>();
		plateParam.put("plateId", plateId);
		plateParam.put("schoolId", tUser.getSchoolId());
		List<TUser> allUser = tUserMapper.selectUserByPlate(plateParam);

		List<TUser> aUserList = new ArrayList<TUser>();
		List<TUser> bUserList = new ArrayList<TUser>();
		List<TUser> cUserList = new ArrayList<TUser>();
		List<TUser> dUserList = new ArrayList<TUser>();
		List<TUser> eUserList = new ArrayList<TUser>();
		List<TUser> fUserList = new ArrayList<TUser>();
		List<TUser> gUserList = new ArrayList<TUser>();
		List<TUser> hUserList = new ArrayList<TUser>();
		List<TUser> iUserList = new ArrayList<TUser>();
		List<TUser> jUserList = new ArrayList<TUser>();
		List<TUser> kUserList = new ArrayList<TUser>();
		List<TUser> lUserList = new ArrayList<TUser>();
		List<TUser> mUserList = new ArrayList<TUser>();
		List<TUser> nUserList = new ArrayList<TUser>();
		List<TUser> oUserList = new ArrayList<TUser>();
		List<TUser> pUserList = new ArrayList<TUser>();
		List<TUser> qUserList = new ArrayList<TUser>();
		List<TUser> rUserList = new ArrayList<TUser>();
		List<TUser> sUserList = new ArrayList<TUser>();
		List<TUser> tUserList = new ArrayList<TUser>();
		List<TUser> uUserList = new ArrayList<TUser>();
		List<TUser> vUserList = new ArrayList<TUser>();
		List<TUser> wUserList = new ArrayList<TUser>();
		List<TUser> xUserList = new ArrayList<TUser>();
		List<TUser> yUserList = new ArrayList<TUser>();
		List<TUser> zUserList = new ArrayList<TUser>();

		// model.addAttribute("userList", userList);
		for (TUser user : allUser) {
			String zimu = ChineseCharacterUtil.convertHanzi2Pinyin(user.getName(), false).substring(0, 1).toUpperCase();

			if (zimu.equals("A")) {
				aUserList.add(user);
			}
			if (zimu.equals("B")) {
				bUserList.add(user);
			}
			if (zimu.equals("C")) {
				cUserList.add(user);
			}
			if (zimu.equals("D")) {
				dUserList.add(user);
			}
			if (zimu.equals("E")) {
				eUserList.add(user);
			}
			if (zimu.equals("F")) {
				fUserList.add(user);
			}
			if (zimu.equals("G")) {
				gUserList.add(user);
			}
			if (zimu.equals("H")) {
				hUserList.add(user);
			}
			if (zimu.equals("I")) {
				iUserList.add(user);
			}
			if (zimu.equals("J")) {
				jUserList.add(user);
			}
			if (zimu.equals("K")) {
				kUserList.add(user);
			}
			if (zimu.equals("L")) {
				lUserList.add(user);
			}
			if (zimu.equals("M")) {
				mUserList.add(user);
			}
			if (zimu.equals("N")) {
				nUserList.add(user);
			}
			if (zimu.equals("O")) {
				oUserList.add(user);
			}
			if (zimu.equals("P")) {
				pUserList.add(user);
			}
			if (zimu.equals("Q")) {
				qUserList.add(user);
			}
			if (zimu.equals("R")) {
				rUserList.add(user);
			}
			if (zimu.equals("S")) {
				sUserList.add(user);
			}
			if (zimu.equals("T")) {
				tUserList.add(user);
			}
			if (zimu.equals("U")) {
				uUserList.add(user);
			}
			if (zimu.equals("V")) {
				vUserList.add(user);
			}
			if (zimu.equals("W")) {
				wUserList.add(user);
			}
			if (zimu.equals("X")) {
				xUserList.add(user);
			}
			if (zimu.equals("Y")) {
				yUserList.add(user);
			}
			if (zimu.equals("Z")) {
				zUserList.add(user);
			}

		}

		model.addAttribute("aUserList", aUserList);
		model.addAttribute("bUserList", bUserList);
		model.addAttribute("cUserList", cUserList);
		model.addAttribute("dUserList", dUserList);
		model.addAttribute("eUserList", eUserList);
		model.addAttribute("fUserList", fUserList);
		model.addAttribute("gUserList", gUserList);
		model.addAttribute("hUserList", hUserList);
		model.addAttribute("iUserList", iUserList);
		model.addAttribute("jUserList", jUserList);
		model.addAttribute("kUserList", kUserList);
		model.addAttribute("lUserList", lUserList);
		model.addAttribute("mUserList", mUserList);
		model.addAttribute("nUserList", nUserList);
		model.addAttribute("oUserList", oUserList);
		model.addAttribute("pUserList", pUserList);
		model.addAttribute("qUserList", qUserList);
		model.addAttribute("rUserList", rUserList);
		model.addAttribute("sUserList", sUserList);
		model.addAttribute("tUserList", tUserList);
		model.addAttribute("uUserList", uUserList);
		model.addAttribute("vUserList", vUserList);
		model.addAttribute("wUserList", wUserList);
		model.addAttribute("xUserList", xUserList);
		model.addAttribute("yUserList", yUserList);
		model.addAttribute("zUserList", zUserList);
		model.addAttribute("content", content);
		model.addAttribute("postId", postId);
		model.addAttribute("turn", turn);

		return "/message/atPageMessage";
	}

	/**
	 * 
	 * 添加班级板块
	 * 
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping("addClassPlate")
	public void addClassPlate(HttpServletRequest request, Model model, HttpServletResponse response) {
		String classId = request.getParameter("classId");
		String plateId = request.getParameter("plateId");

		TClassPlate bean = new TClassPlate();
		bean.setClassId(classId);
		bean.setPlateId(plateId);
		bean.setCreateTime(new Date());
		bean.setUpdateTime(new Date());

		tClassPlateMapper.insert(bean);
	}

	/**
	 * 
	 * 删除班级用户
	 * 
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping("deleteClassUser")
	public void deleteClassUser(HttpServletRequest request, Model model, HttpServletResponse response) {
		String classId = request.getParameter("classId");
		String userId = request.getParameter("userId");
		userId = userId.replace(",", "");

		TClassUserExample bean = new TClassUserExample();
		bean.createCriteria().andClassIdEqualTo(classId).andUserIdEqualTo(Integer.valueOf(userId));

		tClassUserMapper.deleteByExample(bean);

	}

	/**
	 * 
	 * 删除班级板块
	 * 
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping("deleteClassPlate")
	public void deleteClassPlate(HttpServletRequest request, Model model, HttpServletResponse response) {
		String classId = request.getParameter("classId");
		String plateId = request.getParameter("plateId");

		TClassPlateExample bean = new TClassPlateExample();
		bean.createCriteria().andClassIdEqualTo(classId).andPlateIdEqualTo(plateId);

		tClassPlateMapper.deleteByExample(bean);

	}

	/**
	 * 
	 * 
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping("deleteClass")
	public void deleteClass(HttpServletRequest request, Model model, HttpServletResponse response) {
		String classId = request.getParameter("classId");
		managerService.deleteClass(classId);
	}

	/**
	 * 班级管理
	 * 
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping("classManage.html")
	public String classManage(HttpServletRequest request, Model model, HttpServletResponse response) {
		String userId = request.getParameter("userId");
		userId = userId.replace(",", "");
		model.addAttribute("userId", userId);

		TUser user = tUserMapper.selectByPrimaryKey(Integer.valueOf(userId));

		Integer schoolId = user.getSchoolId();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("schoolId", schoolId);
		List<TClass> classList = tClassMapper.selectBySchool(param);
		model.addAttribute("classList", classList);
		return "manager/classManage";
	}

	/**
	 * 创建班级
	 * 
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping("createClass.html")
	public String createClass(HttpServletRequest request, Model model, HttpServletResponse response) {
		String userId = request.getParameter("userId");
		userId = userId.replace(",", "");
		model.addAttribute("userId", userId);

		TUser user = tUserMapper.selectByPrimaryKey(Integer.valueOf(userId));
		String classId = request.getParameter("classId");

		if (StringUtils.isEmpty(classId)) {
			classId = GUIDGenerator.getUUID32();
		}

		TClass classBean = tClassMapper.selectByPrimaryKey(classId);

		String studentListKey = null;
		String plateListKey = null;
		String classNameKey = null;

		String title = "创建班级";
		if (classBean == null) {
			studentListKey = classId + "_studentList";
			plateListKey = classId + "_plateList";
			classNameKey = classId + "_className";
			title = "创建班级";
		} else {
			title = "编辑班级";
			classId = classBean.getId();
			studentListKey = classId + "_studentList";
			plateListKey = classId + "_plateList";
			classNameKey = classId + "_className";

			// 班级用户
			Map<String, Object> classParam = new HashMap<String, Object>();
			classParam.put("classId", classId);
			List<TUser> userList = tUserMapper.selectByClass(classParam);

			// 班级版块
			Map<String, Object> paramParam = new HashMap<String, Object>();
			paramParam.put("classId", classId);
			List<TPlate> plateList = tPlateMapper.selectByClassId(paramParam);

			try {
				if (memcachedClient.get(studentListKey) == null) {
					JSONArray teacherList = new JSONArray();
					for (TUser userBean : userList) {
						JSONObject teacher = new JSONObject();
						teacher.put("id", userBean.getPersonId());
						teacher.put("name", userBean.getName());
						teacher.put("logoUrl", userBean.getLogoUrl());
						teacher.put("platformCode", userBean.getPlatformCode());

						teacherList.add(teacher);
					}

					memcachedClient.add(studentListKey, BasePropertyServiceImpl.generateExpireTime(),
							teacherList.toString());
				}

				String egPlateListKey = memcachedClient.get(plateListKey);
				if (StringUtils.isEmpty(egPlateListKey) || egPlateListKey.equals("[]")) {
					JSONArray plateBeanList = new JSONArray();
					for (TPlate plateBean : plateList) {
						JSONObject plateObject = new JSONObject();
						plateObject.put("id", plateBean.getId());
						plateBeanList.add(plateObject);
					}
					memcachedClient.add(plateListKey, BasePropertyServiceImpl.generateExpireTime(),
							plateBeanList.toString());
				}

				String egClassName = memcachedClient.get(classNameKey);
				if (StringUtils.isEmpty(egClassName)) {
					if (classBean.getName() != null) {
						memcachedClient.add(classNameKey, BasePropertyServiceImpl.generateExpireTime(),
								classBean.getName());
					}
				}
			} catch (TimeoutException | InterruptedException | MemcachedException e) {
				e.printStackTrace();
			}

		}

		model.addAttribute("title", title);

		model.addAttribute("classId", classId);

		String studentStrList = null;
		String plateStrList = null;
		String className = null;
		try {
			studentStrList = memcachedClient.get(studentListKey);
			plateStrList = memcachedClient.get(plateListKey);
			className = memcachedClient.get(classNameKey);

			memcachedClient.delete(studentListKey);
			memcachedClient.delete(plateListKey);
			memcachedClient.delete(classNameKey);

			if (StringUtils.isEmpty(className)) {
				if (classBean != null) {
					className = classBean.getName();
				}
			}

		} catch (TimeoutException | InterruptedException | MemcachedException e) {
			e.printStackTrace();
		}

		// 班级学生列表
		JSONArray studentList = new JSONArray();
		if (StringUtils.isNotEmpty(studentStrList)) {
			studentList = JSONArray.fromObject(studentStrList);
		}
		JSONArray plateList = new JSONArray();
		if (StringUtils.isNotEmpty(plateStrList)) {
			plateList = JSONArray.fromObject(plateStrList);
		}

		model.addAttribute("className", className);
		model.addAttribute("plateList", plateList);
		model.addAttribute("studentList", studentList);

		Map<String, Object> schoolParam = new HashMap<String, Object>();
		schoolParam.put("schoolId", user.getSchoolId());
		List<TPlate> allPlateList = tPlateMapper.selectBySchoolId(schoolParam);
		model.addAttribute("allPlateList", allPlateList);

		return "manager/createClass";
	}

	/**
	 * 创建班级
	 * 
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping("editClass.html")
	public String editClass(HttpServletRequest request, Model model, HttpServletResponse response) {
		String classId = request.getParameter("classId");
		String userId = request.getParameter("userId");
		userId = userId.replace(",", "");
		model.addAttribute("userId", userId);

		TClass tclass = tClassMapper.selectByPrimaryKey(classId);
		model.addAttribute("tclass", tclass);
		TUser teacherUser = tUserMapper.selectByPrimaryKey(Integer.valueOf(userId));

		List<TUser> aUserList = new ArrayList<TUser>();
		List<TUser> bUserList = new ArrayList<TUser>();
		List<TUser> cUserList = new ArrayList<TUser>();
		List<TUser> dUserList = new ArrayList<TUser>();
		List<TUser> eUserList = new ArrayList<TUser>();
		List<TUser> fUserList = new ArrayList<TUser>();
		List<TUser> gUserList = new ArrayList<TUser>();
		List<TUser> hUserList = new ArrayList<TUser>();
		List<TUser> iUserList = new ArrayList<TUser>();
		List<TUser> jUserList = new ArrayList<TUser>();
		List<TUser> kUserList = new ArrayList<TUser>();
		List<TUser> lUserList = new ArrayList<TUser>();
		List<TUser> mUserList = new ArrayList<TUser>();
		List<TUser> nUserList = new ArrayList<TUser>();
		List<TUser> oUserList = new ArrayList<TUser>();
		List<TUser> pUserList = new ArrayList<TUser>();
		List<TUser> qUserList = new ArrayList<TUser>();
		List<TUser> rUserList = new ArrayList<TUser>();
		List<TUser> sUserList = new ArrayList<TUser>();
		List<TUser> tUserList = new ArrayList<TUser>();
		List<TUser> uUserList = new ArrayList<TUser>();
		List<TUser> vUserList = new ArrayList<TUser>();
		List<TUser> wUserList = new ArrayList<TUser>();
		List<TUser> xUserList = new ArrayList<TUser>();
		List<TUser> yUserList = new ArrayList<TUser>();
		List<TUser> zUserList = new ArrayList<TUser>();

		// 班级学生
		Map<String, Object> classParam = new HashMap<String, Object>();
		classParam.put("classId", classId);
		List<TUser> classUser = tUserMapper.selectByClass(classParam);

		String name = request.getParameter("name");

		if (StringUtils.isNotEmpty(name)) {
			model.addAttribute("name", name);
		}

		// model.addAttribute("userList", userList);
		for (TUser user : classUser) {
			if (user == null)
				continue;

			if (StringUtils.isNotEmpty(name)) {
				if (!user.getName().contains(name))
					continue;
			}

			String zimu = ChineseCharacterUtil.convertHanzi2Pinyin(user.getName(), false).substring(0, 1).toUpperCase();

			if (zimu.equals("A")) {
				aUserList.add(user);
			}
			if (zimu.equals("B")) {
				bUserList.add(user);
			}
			if (zimu.equals("C")) {
				cUserList.add(user);
			}
			if (zimu.equals("D")) {
				dUserList.add(user);
			}
			if (zimu.equals("E")) {
				eUserList.add(user);
			}
			if (zimu.equals("F")) {
				fUserList.add(user);
			}
			if (zimu.equals("G")) {
				gUserList.add(user);
			}
			if (zimu.equals("H")) {
				hUserList.add(user);
			}
			if (zimu.equals("I")) {
				iUserList.add(user);
			}
			if (zimu.equals("J")) {
				jUserList.add(user);
			}
			if (zimu.equals("K")) {
				kUserList.add(user);
			}
			if (zimu.equals("L")) {
				lUserList.add(user);
			}
			if (zimu.equals("M")) {
				mUserList.add(user);
			}
			if (zimu.equals("N")) {
				nUserList.add(user);
			}
			if (zimu.equals("O")) {
				oUserList.add(user);
			}
			if (zimu.equals("P")) {
				pUserList.add(user);
			}
			if (zimu.equals("Q")) {
				qUserList.add(user);
			}
			if (zimu.equals("R")) {
				rUserList.add(user);
			}
			if (zimu.equals("S")) {
				sUserList.add(user);
			}
			if (zimu.equals("T")) {
				tUserList.add(user);
			}
			if (zimu.equals("U")) {
				uUserList.add(user);
			}
			if (zimu.equals("V")) {
				vUserList.add(user);
			}
			if (zimu.equals("W")) {
				wUserList.add(user);
			}
			if (zimu.equals("X")) {
				xUserList.add(user);
			}
			if (zimu.equals("Y")) {
				yUserList.add(user);
			}
			if (zimu.equals("Z")) {
				zUserList.add(user);
			}

		}

		model.addAttribute("aUserList", aUserList);
		model.addAttribute("bUserList", bUserList);
		model.addAttribute("cUserList", cUserList);
		model.addAttribute("dUserList", dUserList);
		model.addAttribute("eUserList", eUserList);
		model.addAttribute("fUserList", fUserList);
		model.addAttribute("gUserList", gUserList);
		model.addAttribute("hUserList", hUserList);
		model.addAttribute("iUserList", iUserList);
		model.addAttribute("jUserList", jUserList);
		model.addAttribute("kUserList", kUserList);
		model.addAttribute("lUserList", lUserList);
		model.addAttribute("mUserList", mUserList);
		model.addAttribute("nUserList", nUserList);
		model.addAttribute("oUserList", oUserList);
		model.addAttribute("pUserList", pUserList);
		model.addAttribute("qUserList", qUserList);
		model.addAttribute("rUserList", rUserList);
		model.addAttribute("sUserList", sUserList);
		model.addAttribute("tUserList", tUserList);
		model.addAttribute("uUserList", uUserList);
		model.addAttribute("vUserList", vUserList);
		model.addAttribute("wUserList", wUserList);
		model.addAttribute("xUserList", xUserList);
		model.addAttribute("yUserList", yUserList);
		model.addAttribute("zUserList", zUserList);

		// 所有板块
		Map<String, Object> schoolParam = new HashMap<String, Object>();
		schoolParam.put("schoolId", teacherUser.getSchoolId());
		List<TPlate> allPlateList = tPlateMapper.selectBySchoolId(schoolParam);
		// 班级板块
		List<TPlate> classPlateList = tPlateMapper.selectByClassId(classParam);

		List<TPlate> pagePlate = new ArrayList<TPlate>();

		for (TPlate plate : allPlateList) {
			plate.setRelationStatus(0);
			for (TPlate subPlate : classPlateList) {

				if (subPlate.getId().equals(plate.getId())) {
					plate.setRelationStatus(1);
					break;
				}
			}
			pagePlate.add(plate);
		}
		model.addAttribute("plateList", pagePlate);

		return "manager/editClass";
	}

	/**
	 * 创建班级
	 * 
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping("saveClass")
	public void saveClass(HttpServletRequest request, Model model, HttpServletResponse response) {
		try {
			managerService.saveClass(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加学生
	 * 
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping("addStudentToClass")
	public void addStudentToClass(HttpServletRequest request, Model model, HttpServletResponse response) {
		String classId = request.getParameter("classId");
		String studentList = request.getParameter("studentList");
		String userId = request.getParameter("userId");
		userId = userId.replace(",", "");

		TUser user = tUserMapper.selectByPrimaryKey(Integer.valueOf(userId));
		managerService.addStudentToClass(classId, studentList, user);
	}

	/**
	 * 保存临时班级
	 * 
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping("saveTmpClass")
	public void saveTmpClass(HttpServletRequest request, Model model, HttpServletResponse response) {
		String classId = request.getParameter("classId");
		String plateList = request.getParameter("plateList");
		String className = request.getParameter("className");
		String studentList = request.getParameter("studentList");
		// 将学生信息保存到
		String studentListKey = classId + "_studentList";
		String plateKey = classId + "_plateList";
		String classNameKey = classId + "_className";
		try {
			memcachedClient.delete(studentListKey);
			memcachedClient.delete(plateKey);
			memcachedClient.delete(classNameKey);

			memcachedClient.add(studentListKey, BasePropertyServiceImpl.generateExpireTime(), studentList);
			memcachedClient.add(plateKey, BasePropertyServiceImpl.generateExpireTime(), plateList);
			memcachedClient.add(classNameKey, BasePropertyServiceImpl.generateExpireTime(), className);

			printText(response, "success");
		} catch (TimeoutException | InterruptedException | MemcachedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加学生
	 * 
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping("saveStudent")
	public void saveStudent(HttpServletRequest request, Model model, HttpServletResponse response) {
		String classId = request.getParameter("classId");
		String studentList = request.getParameter("studentList");
		// 将学生信息保存到
		try {
			memcachedClient.delete(classId + "_studentList");
			memcachedClient.add(classId + "_studentList", BasePropertyServiceImpl.generateExpireTime(), studentList);
			printText(response, "success");
		} catch (TimeoutException | InterruptedException | MemcachedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 编辑学生
	 * 
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping("editStudent.html")
	public String editStudent(HttpServletRequest request, Model model, HttpServletResponse response) {
		String userId = request.getParameter("userId");
		userId = userId.replace(",", "");
		model.addAttribute("userId", userId);

		String classId = request.getParameter("classId");
		model.addAttribute("classId", classId);

		// 查询班级学生
		Map<String, Object> classParam = new HashMap<String, Object>();
		classParam.put("classId", classId);
		List<TUser> student = tUserMapper.selectByClass(classParam);

		Iterator<TUser> it = student.iterator();
		while(it.hasNext()){
			TUser eg = it.next();
			if (eg == null) {
				it.remove();
			}
		}
		
//		for (TUser eg : student) {
//			if (eg == null) {
//				student.remove(eg);
//			}
//		}

		System.out.println(student.isEmpty());
		System.out.println(student.size());

		model.addAttribute("studentList", student);// 空用户

		// 查询班级列表
		JSONArray classList = managerService.getClassByUserId(Integer.valueOf(userId));
		model.addAttribute("classList", classList);
		// 查询学校所有班级

		return "manager/editStudent";
	}

	/**
	 * 添加学生
	 * 
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping("addStudent.html")
	public String addStudent(HttpServletRequest request, Model model, HttpServletResponse response) {
		String userId = request.getParameter("userId");
		userId = userId.replace(",", "");
		model.addAttribute("userId", userId);

		String classId = request.getParameter("classId");
		model.addAttribute("classId", classId);

		String studentStrList = null;
		try {
			studentStrList = memcachedClient.get(classId + "_studentList");
		} catch (TimeoutException | InterruptedException | MemcachedException e) {
			e.printStackTrace();
		}
		JSONArray studentList = new JSONArray();
		if (studentStrList != null) {
			studentList = JSONArray.fromObject(studentStrList);
		}

		List<TUser> classUser = new ArrayList<TUser>();

		if (studentList != null) {
			for (int i = 0; i < studentList.size(); i++) {
				JSONObject jsonObject = studentList.optJSONObject(i);
				TUser bean = new TUser();

				String name = jsonObject.optString("name");
				String logoUrl = jsonObject.optString("logoUrl");
				String platformCode = jsonObject.optString("platformCode");

				bean.setPersonId(jsonObject.optString("id"));
				bean.setName(name);
				bean.setLogoUrl(logoUrl);
				bean.setPlatformCode(platformCode);
				classUser.add(bean);
			}
		}

		model.addAttribute("studentList", classUser);// 空用户

		JSONArray classList = new JSONArray();

		TUser user = tUserMapper.selectByPrimaryKey(Integer.valueOf(userId));
		try {
			classList = memcachedClient.get("school_class" + user.getSchoolId());
			if (classList == null || classList.size() == 0) {
				classList = managerService.getClassByUserId(Integer.valueOf(userId));
			}
		} catch (TimeoutException | InterruptedException | MemcachedException e) {
			e.printStackTrace();
		}

		model.addAttribute("classList", classList);
		// 查询学校所有班级

		return "manager/addStudent";
	}

	/**
	 * 根据班级查询学生
	 * 
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping("getStudent")
	public void getStudent(HttpServletRequest request, Model model, HttpServletResponse response) {
		String userId = request.getParameter("userId");
		userId = userId.replace(",", "");
		String aamClassId = request.getParameter("classId");

		JSONArray studentList = managerService.getStudentByClassId(aamClassId, Integer.valueOf(userId));

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("classStuentList", studentList);
		printJson(response, resultMap);
	}

	/**
	 * 搜索学生
	 * 
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping("searchStudent")
	public void searchStudent(HttpServletRequest request, Model model, HttpServletResponse response) {
		String userId = request.getParameter("userId");
		userId = userId.replace(",", "");
		String studentName = request.getParameter("studentName");

		// 模糊查询学生
		List<TUser> studentList = managerService.getStudentByStudentName(studentName, Integer.valueOf(userId));

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("classStuentList", studentList);
		printJson(response, resultMap);
	}

}