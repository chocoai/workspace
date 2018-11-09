package com.whty.wfd.page.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletRequest;

import com.whty.wfd.page.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.whty.wfd.common.utils.HttpUtils;
import com.whty.wfd.common.utils.SysConfigUtils;
import com.whty.wfd.page.model.TPlate;
import com.whty.wfd.page.model.TPlateExample;
import com.whty.wfd.page.model.TPlateUser;
import com.whty.wfd.page.model.TPlateUserExample;
import com.whty.wfd.page.model.TUser;
import com.whty.wfd.page.model.TUserExample;
import com.whty.wfd.page.service.BasePropertyService;
import com.whty.wfd.page.service.PlateService;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import sun.misc.BASE64Decoder;

/**
 * \* User: zjd \* Date: 2018/8/16 \* Time: 14:47 \* Description: \
 */
@Service
public class PlateServiceImpl implements PlateService {

	@Autowired
	private TPlateMapper tPlateMapper;

	@Autowired
	private TPlateUserMapper tPlateUserMapper;

	@Autowired
	private TUserMapper tUserMapper;

	@Autowired
	private BasePropertyService basePropertyService;

	@Autowired
	private IdDao idDao;

	@Autowired
	private MemcachedClient memcachedClient;

	@Autowired
	private TPlatePostMapper tPlatePostMapper;

	@Autowired
	private TPostMessageMapper tPostMessageMapper;

	@Override
	public List<TPlate> getPlates() {
		TPlateExample example = new TPlateExample();
		return tPlateMapper.selectByExample(example);
	}

	@Override
	public void removeTeacher(String plateId, Integer teacherId) {
		TPlateUserExample example = new TPlateUserExample();
		example.createCriteria().andPlateIdEqualTo(plateId).andUserIdEqualTo(teacherId);
		tPlateUserMapper.deleteByExample(example);
	}

	@Override
	public void createPlate(TPlate tPlate) {
		tPlateMapper.insert(tPlate);
	}

	/**
	 * 标识删除版块，并删除版块下所有的帖子，和评论
	 * @param plateId
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void deletePlate(String plateId) {
		try{
			TPlate tPlate = tPlateMapper.selectByPrimaryKey(plateId);
			tPlate.setIsDelete(1);
			tPlatePostMapper.updateByPlateId(plateId);
			List<String> postIds =tPlatePostMapper.getPostIdsByPlateId(plateId);
			tPostMessageMapper.updateByPostIds(postIds);
			tPlateMapper.updateByPrimaryKey(tPlate);
		}catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.wfd.page.service.PlateService#save(javax.servlet.http.
	 * HttpServletRequest)
	 */
	@Override
	public void save(HttpServletRequest request) {
		String logo = request.getParameter("plateLogo");

		String http = logo.substring(0, 4);

		String bosAddress = "http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/images/img_narmal.png";
		if (http.equals("http")) {
			bosAddress = logo;
		} else {
			String reg = "data:image/jpeg;base64,";

			String base64 = logo.replace(reg, "");

			BASE64Decoder decoder = new BASE64Decoder();

			try {
				byte[] imageByte = decoder.decodeBuffer(base64);
				// 生成图片路径和文件名
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String pathString = SysConfigUtils.getStrValue("file_path_pre") + sdf.format(new Date()) + "/";
				String picName = "base64-" + (int) ((Math.random() * 9 + 1) * 1000) + ".jpg";
				File filePath = new File(pathString);
				if (!filePath.exists()) {
					filePath.mkdirs();
				}
				OutputStream out = new FileOutputStream(pathString + picName);
				out.write(imageByte);
				out.flush();
				out.close();

				File filePicName = new File(pathString + picName);

				String ak = SysConfigUtils.getStrValue("baidu.bos.ak");
				String sk = SysConfigUtils.getStrValue("baidu.bos.sk");
				String bucketname = SysConfigUtils.getStrValue("baidu.bos.bucketname");
				String pathProductName = SysConfigUtils.getStrValue("baidu.bos.path.productname");
				String pathVersion = SysConfigUtils.getStrValue("baidu.bos.path.version");
				String baiduBosUrl = SysConfigUtils.getStrValue("baidu.bos.url");
				Calendar cal = Calendar.getInstance();
				StringBuffer bosStringBuffer = new StringBuffer();
				bosStringBuffer.append(pathProductName).append("/").append(pathVersion).append("/")
						.append(cal.get(Calendar.YEAR)).append(cal.get(Calendar.MONTH))
						.append(cal.get(Calendar.DAY_OF_MONTH)).append("/").append(cal.getTime().getTime());
				BosClientConfiguration config = new BosClientConfiguration();
				config.setCredentials(new DefaultBceCredentials(ak, sk));
				BosClient client = new BosClient(config);

				client.putObject(bucketname, bosStringBuffer + filePicName.getName(), filePicName);// 上传到百度云
				bosAddress = baiduBosUrl + bosStringBuffer + filePicName.getName();// 图标地址
				System.out.println(bosAddress);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		String teacherStr = request.getParameter("teacherList");
		String plateName = request.getParameter("plateName");
		String plateText = request.getParameter("plateText");
		String userId = request.getParameter("userId");
		userId = userId.replace(",", "");
		String plateId = request.getParameter("plateId");

		TPlate plateBean = tPlateMapper.selectByPrimaryKey(plateId);

		TUser user = tUserMapper.selectByPrimaryKey(Integer.valueOf(userId));
		if (plateBean == null) {
			TPlate plate = new TPlate();
			// plateId = GUIDGenerator.getUUID32();
			plate.setId(plateId);
			plate.setCreateTime(new Date());
			plate.setUpdateTime(new Date());
			plate.setCreator(Integer.valueOf(userId));
			plate.setIcon(bosAddress);
			plate.setIsDelete(0);
			plate.setName(plateName);
			plate.setSchoolId(user.getSchoolId());
			plate.setDescription(plateText);
			tPlateMapper.insert(plate);
			JSONArray teacherList = JSONArray.fromObject(teacherStr);
			saveTeacherList(teacherList, user, plateId);
		} else {
			plateBean.setUpdateTime(new Date());
			plateBean.setIcon(bosAddress);
			plateBean.setName(plateName);
			plateBean.setDescription(plateText);
			tPlateMapper.updateByPrimaryKey(plateBean);

			// 删除原来的
			TPlateUserExample bean = new TPlateUserExample();
			bean.createCriteria().andPlateIdEqualTo(plateId);
			tPlateUserMapper.deleteByExample(bean);
			JSONArray teacherList = JSONArray.fromObject(teacherStr);
			saveTeacherList(teacherList, user, plateId);
		}
	}

	public void saveTeacherList(JSONArray teacherList, TUser user, String plateId) {
		for (int i = 0; i < teacherList.size(); i++) {
			JSONObject student = teacherList.optJSONObject(i);
			String personid = student.optString("id");
			String platformCode = student.optString("platformCode");
			TUserExample userExample = new TUserExample();
			userExample.createCriteria().andPersonIdEqualTo(personid).andPlatformCodeEqualTo(platformCode);

			List<TUser> users = tUserMapper.selectByExample(userExample);

			Integer studentId = null;
			if (users.size() == 0) {

				String aamUrl = basePropertyService.getPropertyValue("aamUrl", user.getPlatformCode()) + "/user/"
						+ personid;

				String result;
				try {
					result = HttpUtils.getInstance().httpGet(aamUrl);
					JSONObject resultJsonObject = JSONObject.fromObject(result);

					if (resultJsonObject.optString("result").equals("000000")) {
						JSONObject userinfo = resultJsonObject.optJSONObject("userinfo");

						TUser tUser = new TUser();
						tUser.setName(userinfo.optString("name"));
						tUser.setUserType(userinfo.optString("usertype"));
						tUser.setPlatformCode(userinfo.optString("platformCode"));
						tUser.setSchoolId(user.getSchoolId());
						tUser.setAccount(userinfo.optString("account"));
						tUser.setCreateTime(new Date());
						tUser.setUpdateTime(new Date());
						tUser.setOrgaId(userinfo.optString("orgaid"));
						tUser.setPersonId(userinfo.optString("personid"));
						tUser.setLoginPlatformCode(user.getLoginPlatformCode());// 登录平台编码
						JSONArray userlogolist = userinfo.optJSONArray("userlogolist");
						String userLogo = null;
						if (userlogolist.size() > 0) {
							for (int j = 0; j < userlogolist.size(); j++) {
								JSONObject userlogoJsonObject = userlogolist.optJSONObject(j);
								if ("1".equals(userlogoJsonObject.optString("logotype"))) {
									userLogo = userlogoJsonObject.optString("logourl");
									break;
								}
							}
						}
						tUser.setLogoUrl(userLogo);

						Map<String, Object> idParam = new HashMap<String, Object>(2);
						idParam.put("databaseName", "wfd");
						idParam.put("tableName", "t_user");
						studentId = idDao.getId(idParam);

						tUserMapper.insert(tUser);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				studentId = users.get(0).getId();
			}

			TPlateUser plateUser = new TPlateUser();
			plateUser.setCreateTime(new Date());
			plateUser.setUpdateTime(new Date());
			plateUser.setPlateId(plateId);
			plateUser.setUserId(studentId);
			tPlateUserMapper.insert(plateUser);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.wfd.page.service.PlateService#delete(java.lang.String)
	 */
	@Override
	public void delete(String plateId) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.wfd.page.service.PlateService#addTeacherToPlate(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public void addTeacherToPlate(String plateId, String teacherListStr, String userId) {
		TUser user = tUserMapper.selectByPrimaryKey(Integer.valueOf(userId));

		TPlateUserExample bean = new TPlateUserExample();
		bean.createCriteria().andPlateIdEqualTo(plateId);
		tPlateUserMapper.deleteByExample(bean);

		JSONArray teacherList = JSONArray.fromObject(teacherListStr);

		for (int i = 0; i < teacherList.size(); i++) {
			JSONObject student = teacherList.optJSONObject(i);
			String personid = student.optString("id");
			String platformCode = student.optString("platformCode");

			TUserExample userExample = new TUserExample();
			userExample.createCriteria().andPersonIdEqualTo(personid).andPlatformCodeEqualTo(platformCode);

			List<TUser> users = tUserMapper.selectByExample(userExample);

			Integer teacherId = null;
			if (users.size() == 0) {
				String aamUrl = basePropertyService.getPropertyValue("aamUrl", user.getPlatformCode()) + "/user/"
						+ personid;

				String result;
				try {
					result = HttpUtils.getInstance().httpGet(aamUrl);
					JSONObject resultJsonObject = JSONObject.fromObject(result);

					if (resultJsonObject.optString("result").equals("000000")) {
						JSONObject userinfo = resultJsonObject.optJSONObject("userinfo");

						TUser tUser = new TUser();
						tUser.setName(userinfo.optString("name"));
						tUser.setUserType(userinfo.optString("usertype"));
						tUser.setPlatformCode(userinfo.optString("platformCode"));
						tUser.setSchoolId(user.getSchoolId());
						tUser.setAccount(userinfo.optString("account"));
						tUser.setCreateTime(new Date());
						tUser.setUpdateTime(new Date());
						tUser.setOrgaId(userinfo.optString("orgaid"));
						tUser.setPersonId(userinfo.optString("personid"));
						tUser.setLoginPlatformCode(user.getLoginPlatformCode());// 登录平台编码

						JSONArray userlogolist = userinfo.optJSONArray("userlogolist");
						String userLogo = "http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/images/img_narmal.png";
						if (userlogolist.size() > 0) {
							for (int j = 0; j < userlogolist.size(); j++) {
								JSONObject userlogoJsonObject = userlogolist.optJSONObject(j);
								if ("2".equals(userlogoJsonObject.optString("logotype"))) {
									userLogo = userlogoJsonObject.optString("logourl");
									break;
								}
							}
						}
						tUser.setLogoUrl(userLogo);

						Map<String, Object> idParam = new HashMap<String, Object>(2);
						idParam.put("databaseName", "wfd");
						idParam.put("tableName", "t_user");
						teacherId = idDao.getId(idParam);

						tUserMapper.insert(tUser);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				teacherId = users.get(0).getId();
			}

			TPlateUser plateUser = new TPlateUser();
			plateUser.setCreateTime(new Date());
			plateUser.setUpdateTime(new Date());
			plateUser.setPlateId(plateId);
			plateUser.setUserId(teacherId);

			tPlateUserMapper.insert(plateUser);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.wfd.page.service.PlateService#getAllTeacherList(com.whty.wfd.
	 * page.model.TUser)
	 */
	@Override
	public List<TUser> getAllTeacherList(TUser user) {
		List<TUser> tuser = new ArrayList<TUser>();
		String aamUrl = basePropertyService.getPropertyValue("aamUrl", user.getPlatformCode());
		aamUrl = aamUrl + "/member/query";

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("groupid", user.getOrgaId());
		param.put("type", "[\"1\"]");

		try {
			JSONArray userlist = memcachedClient.get("school_teacher" + user.getSchoolId());
			if (userlist != null && userlist.size() > 0) {
				for (int j = 0; j < userlist.size(); j++) {
					JSONObject userInfo = userlist.optJSONObject(j);
					TUser bean = new TUser();

					bean.setAccount(userInfo.optString("account"));
					bean.setPersonId(userInfo.optString("personid"));
					bean.setUserType(userInfo.optString("usertype"));
					bean.setCreateTime(new Date());
					bean.setUpdateTime(new Date());
					bean.setName(userInfo.optString("name"));
					bean.setPlatformCode(userInfo.optString("platformCode"));
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
					bean.setLogoUrl(userLogo);
					tuser.add(bean);
				}
			} else {
				tuser = addMemcache(param, aamUrl, user.getSchoolId());
			}
		} catch (TimeoutException | InterruptedException | MemcachedException e) {
			e.printStackTrace();
		}

		return tuser;
	}

	@Override
	public List<TUser> addMemcache(Map<String, Object> param, String aamUrl, Integer schoolId) {
		List<TUser> tuser = new ArrayList<TUser>();

		JSONArray json = new JSONArray();
		int start = 0;
		int end = 100;
		int pageSize = 100;
		int totalPageNo = 0;// 当且页面

		String answerStr = querySchoolUser(param, aamUrl, start, end);

		String result = JSONObject.fromObject(answerStr).optString("result");

		if ("000000".equals(result)) {
			// 计算共多少页
			int totalCount = JSONObject.fromObject(answerStr).optInt("count");

			totalPageNo = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;

			for (int i = 0; i < totalPageNo; i++) {
				String subAnswerUrl = querySchoolUser(param, aamUrl, start, end);

				JSONArray subUserInfolist = JSONObject.fromObject(subAnswerUrl).optJSONArray("userinfolist");

				for (int j = 0; j < subUserInfolist.size(); j++) {
					JSONObject userInfo = subUserInfolist.optJSONObject(j);
					TUser bean = new TUser();

					bean.setAccount(userInfo.optString("account"));
					bean.setPersonId(userInfo.optString("personid"));
					bean.setUserType(userInfo.optString("usertype"));
					bean.setCreateTime(new Date());
					bean.setUpdateTime(new Date());
					bean.setName(userInfo.optString("name"));
					bean.setPlatformCode(userInfo.optString("platformCode"));
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
					bean.setLogoUrl(userLogo);

					tuser.add(bean);

					json.add(userInfo);

				}
				start = start + pageSize;
				end = end + pageSize;
			}
		}
		try {
			memcachedClient.add("school_teacher" + schoolId, BasePropertyServiceImpl.generateExpireTime(), json);
		} catch (TimeoutException | InterruptedException | MemcachedException e) {
			e.printStackTrace();
		}
		return tuser;
	}

	public String querySchoolUser(Map<String, Object> param, String url, int start, int end) {
		param.put("start", start);
		param.put("end", end);
		String answer = null;
		try {
			answer = HttpUtils.getInstance().httpPost(url, JSONObject.fromObject(param).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return answer;
	}
}