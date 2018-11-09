package com.whty.wfd.page.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.whty.wfd.common.utils.CookiesUtil;
import com.whty.wfd.page.model.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.whty.wfd.base.controller.BaseController;
import com.whty.wfd.common.utils.HttpUtils;
import com.whty.wfd.page.dao.IdDao;
import com.whty.wfd.page.dao.TPlateMapper;
import com.whty.wfd.page.dao.TSchoolMapper;
import com.whty.wfd.page.dao.TUserMapper;
import com.whty.wfd.page.service.BasePropertyService;
import com.whty.wfd.page.service.ManagerService;
import com.whty.wfd.page.service.PersonCenterService;
import com.whty.wfd.page.service.PlateService;
import com.whty.wfd.page.service.PostService;
import com.whty.wfd.page.service.impl.BasePropertyServiceImpl;
import com.whty.wfd.page.vo.Message;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * \* User: zjd \* Date: 2018/8/18 \* Time: 9:37 \* Description: \
 */
@Controller
public class IndexController extends BaseController {

	@Autowired
	private PersonCenterService personCenterService;

	@Autowired
	private PostService postService;
	@Autowired
	private TPlateMapper tPlateMapper;

	@Autowired
	private TUserMapper tUserMapper;

	@Autowired
	private BasePropertyService basePropertyService;

	@Autowired
	private TSchoolMapper tSchoolMapper;

	@Autowired
	private IdDao idDao;

	@Autowired
	private PlateService plateService;

	@Autowired
	private MemcachedClient memcachedClient;

	@Autowired
	private ManagerService managerService;

	final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@RequestMapping("updateReceiveMessage")
	public void updateReceiveMessage(HttpServletRequest request, HttpServletResponse response, ModelMap map,
			String plateId) {
		String userId = request.getParameter("userId");
		userId = userId.replace(",", "");
		String receiveMessage = request.getParameter("receiveMessage");

		TUser tuser = tUserMapper.selectByPrimaryKey(Integer.valueOf(userId));

		tuser.setReceiveMessage(receiveMessage);

		tUserMapper.updateByPrimaryKey(tuser);
		request.getSession().setAttribute("userObj", tuser);
		printText(response, "success");

	}

	@RequestMapping("index.html")
	public String index(HttpServletRequest request, ModelMap map, String plateId, String tabType) {
		TUser tUser = (TUser) request.getSession().getAttribute("userObj");
		Message message = personCenterService.getMessage(tUser.getId());
		List<TPlate> tPlates = new ArrayList<>();
		if (StringUtil.isNotEmpty(tUser.getIdentityId()) && tUser.getIdentityId().equals("3")) {
			// 如果是管理员查询改学校的所有版块
			tPlates = tPlateMapper.selectByIdentityIdSchool(tUser.getSchoolId());
		} else {
			Map<String, Object> userParam = new HashMap<>();
			userParam.put("userId", tUser.getId());
			tPlates = tPlateMapper.selectByUserId(userParam);
		}
		// 查询所有的帖子
		map.put("user", tUser);
		map.put("tabType", tabType);
		map.put("plateId", plateId);
		map.put("message", message);
		map.put("tPlates", tPlates);
		return "index";
	}

	/**
	 * @param map
	 * @param request
	 * @param plateId
	 * @return
	 */
	@RequestMapping("init")
	public String init(ModelMap map, HttpServletRequest request, HttpServletResponse response, String plateId) {
		Map<String, Object> param = this.getParameterMap(request);

		for (String s : param.keySet()) {
			System.out.println("key : " + s + " value : " + param.get(s));
		}

		String personId = request.getParameter("personId");
		String loginPlatformCode = request.getParameter("loginPlatformCode");
		String platformCode = request.getParameter("platformCode");
		String returnUrl = request.getParameter("returnUrl");
		String userPlatformCode = request.getParameter("userPlatformCode");

		if (StringUtils.isNotEmpty(userPlatformCode)) {
			platformCode = userPlatformCode;
		}

		// if(StringUtils.isEmpty(platformCode)){
		// platformCode = userPlatformCode;
		// }

		// if (platformCode.equals("640000")) {
		// platformCode = "640100";
		// }

		TUserExample bean = new TUserExample();
		bean.createCriteria().andPersonIdEqualTo(personId).andPlatformCodeEqualTo(platformCode);

		List<TUser> list = tUserMapper.selectByExample(bean);
		String aamUrl = null;
		if (loginPlatformCode != null) {
			aamUrl = basePropertyService.getPropertyValue("aamUrl", loginPlatformCode);
		} else {
			aamUrl = basePropertyService.getPropertyValue("aamUrl", platformCode);
		}

		final String aaaUrm = aamUrl;

		TUser user = null;
		if (list.size() == 0) {
			// 没找到用户 同步用户数据

			String userInfoUrl = aamUrl + "/user/" + personId;
			try {
				String userInfoStr = HttpUtils.getInstance().httpGet(userInfoUrl);
				JSONObject userInfoJson = JSONObject.fromObject(userInfoStr);
				if (userInfoJson.optString("result").equals("000000")) {
					JSONObject userInfo = userInfoJson.optJSONObject("userinfo");

					String usertype = userInfo.optString("usertype");

					// 不是学生和老师，跳转页面
					// if (!usertype.equals("1") && !usertype.equals("0")) {
					// return "noUse";
					// }

					String orgaid = userInfo.optString("orgaid");
					String schoolName = userInfo.optString("organame");
					String account = userInfo.optString("account");
					TSchoolExample schoolExample = new TSchoolExample();
					schoolExample.createCriteria().andOrgaIdEqualTo(orgaid).andPlatformCodeEqualTo(platformCode);

					List<TSchool> schoolList = tSchoolMapper.selectByExample(schoolExample);

					Integer schoolId = null;
					if (schoolList.size() == 0) {
						Map<String, Object> idParam = new HashMap<String, Object>(2);
						idParam.put("databaseName", "wfd");
						idParam.put("tableName", "t_school");
						schoolId = idDao.getId(idParam);

						TSchool school = new TSchool();
						school.setName(schoolName);
						school.setCreateTime(new Date());
						school.setUpdateTime(new Date());
						school.setPlatformCode(loginPlatformCode);
						school.setOrgaId(orgaid);

						tSchoolMapper.insert(school);
					} else {
						TSchool school = schoolList.get(0);
						schoolId = school.getId();
					}

					Map<String, Object> idParam = new HashMap<String, Object>(2);
					idParam.put("databaseName", "wfd");
					idParam.put("tableName", "t_user");
					Integer currentUserId = idDao.getId(idParam);

					TUser userBean = new TUser();
					userBean.setName(userInfo.optString("name"));
					userBean.setUserType(usertype);
					userBean.setPlatformCode(userInfo.optString("platformCode"));
					userBean.setSchoolId(schoolId);
					userBean.setAccount(account);
					userBean.setCreateTime(new Date());
					userBean.setUpdateTime(new Date());
					userBean.setOrgaId(userInfo.optString("orgaid"));
					userBean.setPersonId(userInfo.optString("personid"));
					userBean.setUserType(userInfo.optString("usertype"));
					userBean.setLoginPlatformCode(loginPlatformCode);

					JSONArray userlogolist = userInfo.getJSONArray("userlogolist");

					String userLogo = "http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/images/img_narmal.png";

					if (userlogolist.size() > 0) {
						for (int k = 0; k < userlogolist.size(); k++) {
							JSONObject userlogoJsonObject = userlogolist.optJSONObject(k);
							if ("2".equals(userlogoJsonObject.optString("logotype"))) {
								userLogo = userlogoJsonObject.optString("logourl");
								break;
							}
						}
					}

					Map<String, Object> userParam = new HashMap<String, Object>();
					userParam.put("groupId", orgaid);
					userParam.put("identityId", "3");

					String answerStr = querySchoolUser(userParam, aamUrl + "/member/querySchoolIdentityInfo");

					String result = JSONObject.fromObject(answerStr).optString("result");
					if ("000000".equals(result)) {
						JSONArray dataJsonArray = JSONObject.fromObject(answerStr).optJSONArray("list");

						if (dataJsonArray.size() > 0) {
							for (int i = 0; i < dataJsonArray.size(); i++) {
								JSONObject data = dataJsonArray.optJSONObject(i);
								String personid = data.optString("personid");
								if (personid.equals(userBean.getPersonId())) {
									userBean.setIdentityId("3");
								}
							}
						}
					}

					userBean.setLogoUrl(userLogo);
					userBean.setOrgaId(orgaid);
					tUserMapper.insert(userBean);
					userBean.setId(currentUserId);
					user = userBean;
					request.getSession().setAttribute("userObj", userBean);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			user = list.get(0);
			// 更新头像
			try {
				String userInfoUrl = aamUrl + "/user/" + personId;
				String userInfoStr = HttpUtils.getInstance().httpGet(userInfoUrl);
				JSONObject userInfoJson = JSONObject.fromObject(userInfoStr);
				String userLogo = "http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/images/img_narmal.png";
				String name = user.getName();
				if (userInfoJson.optString("result").equals("000000")) {
					JSONObject userInfo = userInfoJson.optJSONObject("userinfo");

					name = userInfo.optString("name");

					JSONArray userlogolist = userInfo.getJSONArray("userlogolist");

					if (userlogolist.size() > 0) {
						for (int k = 0; k < userlogolist.size(); k++) {
							JSONObject userlogoJsonObject = userlogolist.optJSONObject(k);
							if ("2".equals(userlogoJsonObject.optString("logotype"))) {
								userLogo = userlogoJsonObject.optString("logourl");
								break;
							}
						}
					}
				}

				Map<String, Object> userParam = new HashMap<String, Object>();
				userParam.put("groupId", user.getOrgaId());
				userParam.put("identityId", "3");

				String answerStr = querySchoolUser(userParam, aamUrl + "/member/querySchoolIdentityInfo");

				String result = JSONObject.fromObject(answerStr).optString("result");
				if ("000000".equals(result)) {
					JSONArray dataJsonArray = JSONObject.fromObject(answerStr).optJSONArray("list");

					if (dataJsonArray.size() > 0) {
						for (int i = 0; i < dataJsonArray.size(); i++) {
							JSONObject data = dataJsonArray.optJSONObject(i);
							String personid = data.optString("personid");
							if (personid.equals(user.getPersonId())) {
								user.setIdentityId("3");
							}
						}
					}
				}
				user.setName(name);
				user.setLogoUrl(userLogo);

				tUserMapper.updateByPrimaryKey(user);
			} catch (Exception e) {
				e.printStackTrace();
			}

			request.getSession().setAttribute("userObj", user);
		}
		if (user == null) {
			return "noUse";
		}
		Cookie cookie = new Cookie("userObj", user.getId().toString());
		cookie.setMaxAge(60 * 60 * 24);
		CookiesUtil.setCookie(response, cookie);

		// 如果是学校管理员则将学校数据写入缓存

		if (StringUtils.isNotEmpty(user.getIdentityId())) {
			if (user.getIdentityId().equals("3")) {

				final TUser threadBean = user;
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							memcachedClient.delete("school_teacher" + threadBean.getSchoolId());
							memcachedClient.delete("school_class" + threadBean.getSchoolId());

							String schoolStudentKey = threadBean.getSchoolId() + "_" + threadBean.getPlatformCode()
									+ "_studentList";

							memcachedClient.delete(schoolStudentKey);

							JSONArray schoolStudentArray = new JSONArray();

							JSONArray json = managerService.getClassByUserId(threadBean.getId());

							for (int i = 0; i < json.size(); i++) {
								JSONObject classObject = json.getJSONObject(i);
								String classId = classObject.optString("classId");
								schoolStudentArray
										.addAll(managerService.getStudentByClassId(classId, threadBean.getId()));
							}

							memcachedClient.add(schoolStudentKey, BasePropertyServiceImpl.generateExpireTime(),
									schoolStudentArray);

							memcachedClient.add("school_class" + threadBean.getSchoolId(),
									BasePropertyServiceImpl.generateExpireTime(), json);

						} catch (TimeoutException | InterruptedException | MemcachedException e) {
							e.printStackTrace();
						}

						Map<String, Object> schoolIdparam = new HashMap<String, Object>();
						schoolIdparam.put("groupid", threadBean.getOrgaId());
						schoolIdparam.put("type", "[\"1\"]");
						plateService.addMemcache(schoolIdparam, aaaUrm + "/member/query", threadBean.getSchoolId());

					}
				}).start();
			}
		}

		if (StringUtils.isNotEmpty(returnUrl)) {
			return "redirect:" + returnUrl;
		} else {
			return "redirect:index.html";
		}

	}

	public String querySchoolUser(Map<String, Object> param, String url) {

		String answer = null;
		try {
			answer = HttpUtils.getInstance().httpPost(url, JSONObject.fromObject(param).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return answer;
	}

	@RequestMapping("getIndexPost")
	public void getIndexPost(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name = "type", defaultValue = "0") Integer type,
			@RequestParam(name = "pageNum", defaultValue = "0") Integer pageNum, String plateId, String orderByClause,
			@RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("pageNum", pageNum);
			TUser tUser = (TUser) request.getSession().getAttribute("userObj");
			List<TPlatePost> list = new ArrayList<>();
			List<TPlatePost> posts = new ArrayList<>();
			pageNum = pageNum * pageSize;
			if (StringUtil.isNotEmpty(orderByClause)) {
				type = 1;
			}
			if (StringUtil.isNotEmpty(tUser.getIdentityId()) && tUser.getIdentityId().equals("3")) {
				list = postService.getPostByPlateIdAllMasterPage3(plateId, pageNum, pageSize, tUser.getId(), type,
						tUser.getSchoolId());
				posts = postService.getPostByPlateIdAllMaster(plateId, tUser.getId(), orderByClause);
			} else {
				if (tUser.getUserType().equals("0")) {
					list = postService.getPostByPlateIdAllPage1(plateId, pageNum, pageSize, tUser.getId(), type);
					posts = postService.getPostByPlateIdAll(plateId, tUser.getId(), orderByClause);
				}
				if (tUser.getUserType().equals("1")) {
					list = postService.getPostByPlateIdAllTeacherPage2(plateId, pageNum, pageSize, tUser.getId(), type);
					posts = postService.getPostByPlateIdAllTeacher(plateId, tUser.getId(), orderByClause);
				}
			}

			jsonObject.put("list", list);
			jsonObject.put("type", type);
			jsonObject.put("posts", posts);
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("getPostRealTime")
	public void getPostRealTime(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name = "type", defaultValue = "0") Integer type,
			@RequestParam(name = "pageNum", defaultValue = "0") Integer pageNum,
			@RequestParam(name = "pageSize", defaultValue = "30") Integer pageSize) {
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("pageNum", pageNum);
			TUser tUser = (TUser) request.getSession().getAttribute("userObj");
			List<TPlatePost> list = new ArrayList<>();
			pageNum = pageNum * pageSize;
			if (StringUtil.isNotEmpty(tUser.getIdentityId()) && tUser.getIdentityId().equals("3")) {
				list = postService.getPostByPlateIdAllMasterPage("", pageNum, pageSize, tUser.getId(), type,
						tUser.getSchoolId());
			} else {
				if (tUser.getUserType().equals("0")) {
					list = postService.getPostByPlateIdAllPage("", pageNum, pageSize, tUser.getId(), type);
				}
				if (tUser.getUserType().equals("1")) {
					list = postService.getPostByPlateIdAllTeacherPage("", pageNum, pageSize, tUser.getId(), type);
				}
			}

			jsonObject.put("list", list);
			jsonObject.put("type", type);
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "error.html")
	public String errorPage() {
		return "errorpage/404";
	}

}