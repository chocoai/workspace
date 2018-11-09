/**
 * 
 */
package com.whty.wfd.page.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.whty.wfd.base.controller.BaseController;
import com.whty.wfd.common.utils.ChineseCharacterUtil;
import com.whty.wfd.common.utils.GUIDGenerator;
import com.whty.wfd.page.dao.TClassMapper;
import com.whty.wfd.page.dao.TClassPlateMapper;
import com.whty.wfd.page.dao.TPlateMapper;
import com.whty.wfd.page.dao.TPlateUserMapper;
import com.whty.wfd.page.dao.TUserMapper;
import com.whty.wfd.page.model.TClass;
import com.whty.wfd.page.model.TClassExample;
import com.whty.wfd.page.model.TClassPlate;
import com.whty.wfd.page.model.TClassPlateExample;
import com.whty.wfd.page.model.TClassUser;
import com.whty.wfd.page.model.TClassUserExample;
import com.whty.wfd.page.model.TPlate;
import com.whty.wfd.page.model.TPlateUser;
import com.whty.wfd.page.model.TPlateUserExample;
import com.whty.wfd.page.model.TUser;
import com.whty.wfd.page.model.TUserExample;
import com.whty.wfd.page.service.PlateService;
import com.whty.wfd.page.service.impl.BasePropertyServiceImpl;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.monitor.MemcachedClientNameHolder;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 板块管理
 * 
 * @author zhangzheng
 * @date 2018年8月20日
 */
@Controller
@RequestMapping("/page/manager/plate")
public class PlateManageController extends BaseController {

	@Autowired
	private PlateService plateService;

	@Autowired
	private TPlateMapper tPlateMapper;

	@Autowired
	private TUserMapper tUserMapper;

	@Autowired
	private MemcachedClient memcachedClient;

	@Autowired
	private TClassMapper tClassMapper;

	@Autowired
	private TClassPlateMapper tClassPlateMapper;

	@Autowired
	private TPlateUserMapper tPlateUserMapper;

	/**
	 * 保存临时数据
	 * 
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping("searchTeacher")
	public void searchTeacher(HttpServletRequest request, Model model, HttpServletResponse response) {
		String userId = request.getParameter("userId");
		String teacherName = request.getParameter("teacherName");
		TUser tuser = tUserMapper.selectByPrimaryKey(Integer.valueOf(userId));
		if (StringUtils.isEmpty(teacherName)) {
			List<TUser> allTeacher = plateService.getAllTeacherList(tuser);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("teacherList", allTeacher);
			printJson(response, resultMap);
		} else {
			List<TUser> allTeacher = plateService.getAllTeacherList(tuser);
			List<TUser> result = new ArrayList<TUser>();
			for (TUser user : allTeacher) {
				if (user.getName().contains(teacherName)) {
					result.add(user);
				}
			}
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("teacherList", result);
			printJson(response, resultMap);
		}

	}

	/**
	 * 保存临时数据
	 * 
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping("addTeacherToPlate")
	public void addTeacherToPlate(HttpServletRequest request, Model model, HttpServletResponse response) {
		String plateId = request.getParameter("plateId");
		String teacherList = request.getParameter("teacherList");
		String userId = request.getParameter("userId");

		userId = userId.replace(",", "");
		plateService.addTeacherToPlate(plateId, teacherList, userId);

	}

	@RequestMapping("saveTmpTeacher")
	public void saveTmpTeacher(HttpServletRequest request, Model model, HttpServletResponse response) {
		String plateId = request.getParameter("plateId");

		String teacherList = request.getParameter("teacherList");

		String teacherListKey = plateId + "_teacherList";

		try {
			memcachedClient.delete(teacherListKey);

			if (StringUtils.isNotEmpty(teacherList)) {
				memcachedClient.add(teacherListKey, BasePropertyServiceImpl.generateExpireTime(), teacherList);
			}

			printText(response, "success");
		} catch (TimeoutException | InterruptedException | MemcachedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存临时数据
	 * 
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping("saveTmp")
	public void saveTmp(HttpServletRequest request, Model model, HttpServletResponse response) {
		String plateId = request.getParameter("plateId");

		String plateName = request.getParameter("plateName");
		String teacherList = request.getParameter("teacherList");
		String plateText = request.getParameter("plateText");
		String icon = request.getParameter("icon");
		// 将学生信息保存到

		String teacherListKey = plateId + "_teacherList";
		String plateNameKey = plateId + "_name";
		String plateTextKey = plateId + "_text";
		String iconKey = plateId + "_iconKey";
		try {
			memcachedClient.delete(teacherListKey);
			memcachedClient.delete(plateNameKey);
			memcachedClient.delete(plateTextKey);
			memcachedClient.delete(iconKey);

			if (StringUtils.isNotEmpty(teacherList)) {
				memcachedClient.add(teacherListKey, BasePropertyServiceImpl.generateExpireTime(), teacherList);
			}

			if (StringUtils.isNotEmpty(plateName)) {
				memcachedClient.add(plateNameKey, BasePropertyServiceImpl.generateExpireTime(), plateName);
			}

			if (StringUtils.isNotEmpty(plateText)) {
				memcachedClient.add(plateTextKey, BasePropertyServiceImpl.generateExpireTime(), plateText);
			}

			if (StringUtils.isNotEmpty(icon)) {
				memcachedClient.add(iconKey, BasePropertyServiceImpl.generateExpireTime(), icon);
			}

			printText(response, "success");
		} catch (TimeoutException | InterruptedException | MemcachedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除板块
	 * 
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping("delete")
	public void delete(HttpServletRequest request, Model model, HttpServletResponse response) {
		String plateId = request.getParameter("plateId");
		plateService.deletePlate(plateId);
	}

	/**
	 * 关联板块
	 * 
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping("relationPlate")
	public void relationPlate(HttpServletRequest request, Model model, HttpServletResponse response) {
		String plateId = request.getParameter("plateId");
		String classId = request.getParameter("classId");

		TClassPlate bean = new TClassPlate();
		bean.setClassId(classId);
		bean.setPlateId(plateId);
		bean.setCreateTime(new Date());
		bean.setUpdateTime(new Date());

		tClassPlateMapper.insert(bean);

	}

	/**
	 * 取消关联板块
	 * 
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping("deleteRelationPlate")
	public void deleteRelationPlate(HttpServletRequest request, Model model, HttpServletResponse response) {
		String plateId = request.getParameter("plateId");
		String classId = request.getParameter("classId");

		TClassPlateExample bean = new TClassPlateExample();
		bean.createCriteria().andClassIdEqualTo(classId).andPlateIdEqualTo(plateId);
		tClassPlateMapper.deleteByExample(bean);
	}

	/**
	 * 删除板块用户
	 * 
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping("deletePlateUser")
	public void deletePlateUser(HttpServletRequest request, Model model, HttpServletResponse response) {
		String plateId = request.getParameter("plateId");
		String userId = request.getParameter("userId");
		userId = userId.replace(",", "");
		TPlateUserExample bean = new TPlateUserExample();
		bean.createCriteria().andPlateIdEqualTo(plateId).andUserIdEqualTo(Integer.valueOf(userId));

		tPlateUserMapper.deleteByExample(bean);
	}

	/**
	 * 保存板块
	 * 
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping("save")
	public void save(HttpServletRequest request, Model model, HttpServletResponse response) {
		try {
			plateService.save(request);
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
	@RequestMapping("saveTeacher")
	public void saveStudent(HttpServletRequest request, Model model, HttpServletResponse response) {
		String plateId = request.getParameter("plateId");
		String teacherList = request.getParameter("teacher");
		// 将老师信息保存到缓存
		try {
			memcachedClient.delete(plateId);
			memcachedClient.add(plateId, BasePropertyServiceImpl.generateExpireTime(), teacherList);
			printText(response, "success");
		} catch (TimeoutException | InterruptedException | MemcachedException e) {
			e.printStackTrace();
		}
	}

	// ----------------------页面

	@RequestMapping("editTeacher.html")
	public String editTeacher(HttpServletRequest request, Model model, HttpServletResponse response) {
		String plateId = request.getParameter("plateId");
		model.addAttribute("plateId", plateId);

		String userId = request.getParameter("userId");

		userId = userId.replace(",", "");

		model.addAttribute("userId", userId);

		TUser user = tUserMapper.selectByPrimaryKey(Integer.valueOf(userId));

		// 板块所有用户
		Map<String, Object> plateParam = new HashMap<String, Object>();
		plateParam.put("plateId", plateId);
		List<TUser> plateUser = tUserMapper.selectByPlate(plateParam);
		model.addAttribute("plateUserList", plateUser);

		// 学校所有老师
		List<TUser> allUser = plateService.getAllTeacherList(user);
		model.addAttribute("allUserList", allUser);

		return "manager/plate/editTeacher";
	}

	@RequestMapping("list.html")
	public String list(HttpServletRequest request, Model model, HttpServletResponse response) {
		String userId = request.getParameter("userId");
		userId = userId.replace(",", "");
		model.addAttribute("userId", Integer.valueOf(userId));

		// 获取所有板块
		TUser user = tUserMapper.selectByPrimaryKey(Integer.valueOf(userId));
		Map<String, Object> schoolParam = new HashMap<String, Object>();
		schoolParam.put("schoolId", user.getSchoolId());

		List<TPlate> plateList = tPlateMapper.selectBySchoolId(schoolParam);
		model.addAttribute("plateList", plateList);
		return "manager/plate/list";
	}

	@RequestMapping("create.html")
	public String create(HttpServletRequest request, ModelMap model, HttpServletResponse response) {

		String userId = request.getParameter("userId");
		userId = userId.replace(",", "");
		model.put("userId", userId);
		String plateId = request.getParameter("plateId");

		if (StringUtils.isEmpty(plateId)) {
			plateId = GUIDGenerator.getUUID32();
		}

		TPlate plateBean = tPlateMapper.selectByPrimaryKey(plateId);

		String teacherListKey = null;
		String plateNameKey = null;
		String plateTextKey = null;
		String iconKey = null;

		String title = "创建版块";
		if (plateBean == null) {
			title = "创建版块";
			teacherListKey = plateId + "_teacherList";
			plateNameKey = plateId + "_name";
			plateTextKey = plateId + "_text";

			iconKey = plateId + "_iconKey";
		} else {
			title = "编辑版块";
			plateId = plateBean.getId();
			teacherListKey = plateId + "_teacherList";
			plateNameKey = plateId + "_name";
			plateTextKey = plateId + "_text";

			iconKey = plateId + "_iconKey";

			Map<String, Object> plateParam = new HashMap<String, Object>();
			plateParam.put("plateId", plateId);
			List<TUser> userList = tUserMapper.selectByPlate(plateParam);

			try {

				if (memcachedClient.get(teacherListKey) == null) {
					JSONArray teacherList = new JSONArray();
					for (TUser user : userList) {
						JSONObject teacher = new JSONObject();
						teacher.put("id", user.getPersonId());
						teacher.put("name", user.getName());
						teacher.put("logoUrl", user.getLogoUrl());
						teacher.put("platformCode", user.getPlatformCode());
						teacherList.add(teacher);
					}

					memcachedClient.add(teacherListKey, BasePropertyServiceImpl.generateExpireTime(),
							teacherList.toString());
				}
				if (memcachedClient.get(plateId + "_logoUrl") == null) {
					memcachedClient.add(plateId + "_logoUrl", BasePropertyServiceImpl.generateExpireTime(),
							plateBean.getIcon());
				}
				if (memcachedClient.get(plateTextKey) == null) {
					if (plateBean.getDescription() != null) {
						memcachedClient.add(plateTextKey, BasePropertyServiceImpl.generateExpireTime(),
								plateBean.getDescription());
					}
				}
				String egplateNameKey = memcachedClient.get(plateNameKey);
				if (StringUtils.isEmpty(egplateNameKey)) {
					memcachedClient.add(plateNameKey, BasePropertyServiceImpl.generateExpireTime(),
							plateBean.getName());
				}
				if (memcachedClient.get(iconKey) == null) {
					memcachedClient.add(iconKey, BasePropertyServiceImpl.generateExpireTime(), plateBean.getIcon());
				}

			} catch (TimeoutException | InterruptedException | MemcachedException e) {
				e.printStackTrace();
			}

		}

		model.put("title", title);
		model.put("plateId", plateId);

		String teacherStrList = null;
		String logoUrl = null;
		String plateText = null;
		String plateName = null;
		String icon = null;
		try {
			teacherStrList = memcachedClient.get(teacherListKey);
			logoUrl = memcachedClient.get(plateId + "_logoUrl");
			plateText = memcachedClient.get(plateTextKey);
			plateName = memcachedClient.get(plateNameKey);
			icon = memcachedClient.get(iconKey);
			memcachedClient.delete(teacherListKey);
			memcachedClient.delete(plateId + "_logoUrl");
			memcachedClient.delete(plateTextKey);
			memcachedClient.delete(plateNameKey);
			memcachedClient.delete(iconKey);

		} catch (TimeoutException | InterruptedException | MemcachedException e) {
			e.printStackTrace();
		}

		// 板块老师列表
		JSONArray teacherList = new JSONArray();
		if (StringUtils.isNotEmpty(teacherStrList)) {
			teacherList = JSONArray.fromObject(teacherStrList);
		}

		model.put("teacherList", teacherList);

		model.put("icon", icon);
		model.put("logoUrl", logoUrl);
		model.put("plateText", plateText);
		model.put("plateName", plateName);

		return "manager/plate/create";
	}

	@RequestMapping("edit.html")
	public String edit(HttpServletRequest request, Model model, HttpServletResponse response) {
		String userId = request.getParameter("userId");

		userId = userId.replace(",", "");

		model.addAttribute("userId", userId);

		String plateId = request.getParameter("plateId");
		TPlate plate = tPlateMapper.selectByPrimaryKey(plateId);
		model.addAttribute("plate", plate);

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

		Map<String, Object> plateParam = new HashMap<String, Object>();
		plateParam.put("plateId", plateId);
		List<TUser> userList = tUserMapper.selectByPlate(plateParam);// 板块的老师

		String name = request.getParameter("name");

		if (StringUtils.isNotEmpty(name)) {
			model.addAttribute("name", name);
		}

		// model.addAttribute("userList", userList);
		for (TUser user : userList) {

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

		TUser user = tUserMapper.selectByPrimaryKey(Integer.valueOf(userId));

		TClassExample tClassexample = new TClassExample();
		tClassexample.createCriteria().andSchoolIdEqualTo(user.getSchoolId());

		List<TClass> allList = tClassMapper.selectByExample(tClassexample);

		List<TClass> classList = tClassMapper.selectByPlate(plateParam);// 板块的班级

		List<TClass> pageClass = new ArrayList<TClass>();

		for (TClass tclass : allList) {
			tclass.setRelationStatus(0);
			for (TClass subClass : classList) {
				if (tclass.getId().equals(subClass.getId())) {
					tclass.setRelationStatus(1);
					break;
				}
			}
			pageClass.add(tclass);
		}
		model.addAttribute("classList", pageClass);

		return "manager/plate/edit";
	}

	@RequestMapping("addTeacher.html")
	public String addTeacher(HttpServletRequest request, Model model, HttpServletResponse response) {
		String plateId = request.getParameter("plateId");
		String userId = request.getParameter("userId");
		userId = userId.replace(",", "");
		TUser user = tUserMapper.selectByPrimaryKey(Integer.valueOf(userId));

		TUserExample userExample = new TUserExample();
		userExample.createCriteria().andSchoolIdEqualTo(user.getSchoolId()).andUserTypeEqualTo("1");

		// 学校所有老师
		List<TUser> allTeacherList = plateService.getAllTeacherList(user);
		model.addAttribute("allTeacherList", allTeacherList);

		String teacherStrList = null;
		try {
			teacherStrList = memcachedClient.get(plateId + "_teacherList");
		} catch (TimeoutException | InterruptedException | MemcachedException e) {
			e.printStackTrace();
		}
		JSONArray teacherList = new JSONArray();
		if (teacherStrList != null) {
			teacherList = JSONArray.fromObject(teacherStrList);
		}
		List<TUser> plateUser = new ArrayList<TUser>();
		if (teacherList != null) {
			for (int i = 0; i < teacherList.size(); i++) {
				JSONObject jsonObject = teacherList.optJSONObject(i);
				TUser bean = new TUser();

				String name = jsonObject.optString("name");
				String logoUrl = jsonObject.optString("logoUrl");

				bean.setPersonId(jsonObject.optString("id"));
				bean.setName(name);
				bean.setLogoUrl(logoUrl);
				bean.setPlatformCode(jsonObject.optString("platformCode"));
				plateUser.add(bean);
			}
		}

		model.addAttribute("teacherList", plateUser);
		model.addAttribute("plateId", plateId);
		model.addAttribute("userId", userId);

		return "manager/plate/addTeacher";
	}

	class Student {
		private String id;
		private String name;
		private String logoUrl;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getLogoUrl() {
			return logoUrl;
		}

		public void setLogoUrl(String logoUrl) {
			this.logoUrl = logoUrl;
		}

	}

}