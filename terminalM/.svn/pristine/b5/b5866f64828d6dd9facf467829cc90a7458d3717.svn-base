/**
 * 
 */
package com.whty.assis.mall.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.whty.assis.basicdata.dao.GradeDao;
import com.whty.assis.basicdata.dao.IdDao;
import com.whty.assis.basicdata.dao.SubjectDao;
import com.whty.assis.basicdata.model.Grade;
import com.whty.assis.basicdata.model.Subject;
import com.whty.assis.mall.dao.AppImgDao;
import com.whty.assis.mall.dao.AppInfoDao;
import com.whty.assis.mall.model.AppGrade;
import com.whty.assis.mall.model.AppImg;
import com.whty.assis.mall.model.AppInfo;
import com.whty.assis.mall.model.AppSubject;
import com.whty.assis.mall.service.AppInfoService;
import com.whty.assis.sysres.model.TaManageUserInfo;
import com.whty.common.util.SysConfig;
import com.whty.common.util.SysConstants;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * @author zhangzheng
 * @date 2018年4月18日
 */
@Service
public class AppInfoServiceImpl implements AppInfoService {

	@Autowired
	private AppInfoDao appInfoDao;

	@Autowired
	private AppImgDao appImgDao;

	@Autowired
	private IdDao idDao;

	@Autowired
	private SubjectDao subjectDao;

	@Autowired
	private GradeDao gradeDao;

	@Override
	public AppInfo loadById(Integer id) {
		AppInfo bean = appInfoDao.loadById(id);

		List<Subject> subjectList = subjectDao.listByConditionByAppId(id);
		List<Grade> gradeList = gradeDao.listByConditionByAppId(id);

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("appId", id);
		List<AppImg> appImgList = appImgDao.listByCondition(param);

		bean.setAppImgList(appImgList);
		bean.setSubjectList(subjectList);
		bean.setGradeList(gradeList);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.mall.service.AppInfoService#saveAppInfo(javax.servlet.http
	 * .HttpServletRequest)
	 */
	@Override
	public String saveAppInfo(HttpServletRequest request) {
		String versionCode = request.getParameter("versionCode");
		String packageName = request.getParameter("packageName");
		String appName = request.getParameter("appName");
		String fileSize = request.getParameter("fileSize");
		String iconPath = request.getParameter("iconPath");
		String[] subjectList = request.getParameterValues("subjectList");
		String[] gradeList = request.getParameterValues("gradeList");
		String company = request.getParameter("company");
		String uploadFileUrl = request.getParameter("uploadFileUrl");
		String description = request.getParameter("description");
		String version = request.getParameter("version");

		String[] appImgList = request.getParameterValues("appImgList");

		Map<String, Object> idParam = new HashMap<String, Object>();

		idParam.put("databaseName", SysConfig.getStrValue("databaseName"));
		idParam.put("tableName", SysConfig.getStrValue("t_app_info"));

		final Integer appInfoId = idDao.getId(idParam);

		final AppInfo appInfo = new AppInfo();
		appInfo.setCreateTime(new Date());
		appInfo.setUpdateTime(new Date());
		appInfo.setCompany(company);
		appInfo.setName(appName);
		appInfo.setClassify(1);
		TaManageUserInfo mUser = (TaManageUserInfo) request.getSession().getAttribute(SysConstants.SESSION_USER);
		appInfo.setCreator(mUser.getId());
		appInfo.setBosStatus("1");
		final File file = new File(uploadFileUrl);
		String md5 = null;

		try {
			md5 = DigestUtils.md5Hex(new FileInputStream(uploadFileUrl));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Map<String, Object> appInfoParam = new HashMap<String, Object>();
		appInfoParam.put("md5", md5);

		List<AppInfo> appInfos = appInfoDao.listByCondition(appInfoParam);

		if (appInfos != null && appInfos.size() > 0) {
			return "不能重复上传相同版本的软件!";
		}

		

		appInfo.setMd5(md5);
		appInfo.setFileSize(fileSize);
		appInfo.setDescription(description);

		// 上传图标
		// File iconFile = new File(iconPath);
		// client.putObject(bucketname, bosStringBuffer + iconFile.getName(),
		// iconFile);// TODO
		// 注释放开
		appInfo.setIcon(iconPath);

		final StringBuffer bosStringBuffer = new StringBuffer();
		final Calendar cal = Calendar.getInstance();

		final String ak = SysConfig.getStrValue("baidu.bos.ak");
		final String sk = SysConfig.getStrValue("baidu.bos.sk");
		final String bucketname = SysConfig.getStrValue("baidu.bos.bucketname");
		final String pathProductName = SysConfig.getStrValue("baidu.bos.path.productname");
		final String pathVersion = SysConfig.getStrValue("baidu.bos.path.version");
		final String baiduBosUrl = SysConfig.getStrValue("baidu.bos.url");
		// 上传文件
		appInfo.setVersion(version);
		appInfo.setVersionCode(versionCode);
		appInfo.setPackageName(packageName);

		appInfoDao.save(appInfo);

		if (appImgList != null && appImgList.length > 0) {
			for (int i = 0; i < appImgList.length; i++) {
				AppImg appImg = new AppImg();
				appImg.setUrl(appImgList[i]);
				appImg.setAppId(appInfoId);
				appImgDao.save(appImg);
			}
		}

		if (subjectList != null && subjectList.length > 0) {
			for (int i = 0; i < subjectList.length; i++) {
				AppSubject appSubject = new AppSubject();
				appSubject.setAppId(appInfoId);
				appSubject.setSubjectId(Integer.valueOf(subjectList[i]));
				appInfoDao.saveAppSubject(appSubject);
			}
		}

		if (gradeList != null && gradeList.length > 0) {
			for (int i = 0; i < gradeList.length; i++) {
				AppGrade appGrade = new AppGrade();
				appGrade.setAppId(appInfoId);
				appGrade.setGradeId(Integer.valueOf(gradeList[i]));
				appInfoDao.saveAppGrade(appGrade);
			}
		}

		// 异步上传客户端
		new Thread(new Runnable() {
			@Override
			public void run() {
				appInfo.setId(appInfoId);
				
				
				bosStringBuffer.append(pathProductName).append("/").append(pathVersion).append("/")
						.append(cal.get(Calendar.YEAR)).append(cal.get(Calendar.MONTH))
						.append(cal.get(Calendar.DAY_OF_MONTH)).append("/").append(cal.getTime().getTime());

				BosClientConfiguration config = new BosClientConfiguration();
				config.setCredentials(new DefaultBceCredentials(ak, sk));
				BosClient client = new BosClient(config);

				client.putObject(bucketname, bosStringBuffer + file.getName(), file);
				appInfo.setDownUrl(baiduBosUrl + bosStringBuffer + file.getName());

				appInfo.setBosStatus("2");
				appInfoDao.update(appInfo);
			}

		}).start();

		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.mall.service.AppInfoService#deleteAppInfo(java.lang.
	 * Integer)
	 */
	@Override
	public String deleteAppInfo(Integer id) {
		appInfoDao.deleteById(id);

		appImgDao.deleteByAppId(id);

		appInfoDao.deleteAppGradeByAppId(id);

		appInfoDao.deleteAppSubjectByAppId(id);

		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.mall.service.AppInfoService#listByCondition(java.util.Map)
	 */
	@Override
	public List<AppInfo> listByCondition(Map<String, Object> paramMap) {
		return appInfoDao.listByCondition(paramMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.mall.service.AppInfoService#queryAppInfoPage(java.util.
	 * Map)
	 */
	@Override
	public HandlerResult queryAppInfoPage(Map<String, Object> paramMap) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(appInfoDao.listByCondition(paramMap));
		return rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.mall.service.AppInfoService#updateAppInfo(com.whty.assis.
	 * mall.model.AppInfo)
	 */
	@Override
	public String updateAppInfo(HttpServletRequest request) {
		String appId = request.getParameter("id");
		String appName = request.getParameter("appName");

		// String fileSize = request.getParameter("fileSize");
		// String iconPath = request.getParameter("iconPath");
		String[] subjectList = request.getParameterValues("subjectList");
		String[] gradeList = request.getParameterValues("gradeList");
		String company = request.getParameter("company");
		// String uploadFileUrl = request.getParameter("uploadFileUrl");
		String description = request.getParameter("description");
		String version = request.getParameter("version");

		String[] appImgList = request.getParameterValues("appImgList");

		Map<String, Object> idParam = new HashMap<String, Object>();

		idParam.put("databaseName", SysConfig.getStrValue("databaseName"));
		idParam.put("tableName", SysConfig.getStrValue("t_app_info"));

//		StringBuffer bosStringBuffer = new StringBuffer();
//		Calendar cal = Calendar.getInstance();

//		String ak = SysConfig.getStrValue("baidu.bos.ak");
//		String sk = SysConfig.getStrValue("baidu.bos.sk");
//		String bucketname = SysConfig.getStrValue("baidu.bos.bucketname");
//		String pathProductName = SysConfig.getStrValue("baidu.bos.path.productname");
//		String pathVersion = SysConfig.getStrValue("baidu.bos.path.version");
//		String baiduBosUrl = SysConfig.getStrValue("baidu.bos.url");

//		bosStringBuffer.append(pathProductName).append("/").append(pathVersion).append("/")
//				.append(cal.get(Calendar.YEAR)).append(cal.get(Calendar.MONTH)).append(cal.get(Calendar.DAY_OF_MONTH))
//				.append("/").append(cal.getTime().getTime());

//		BosClientConfiguration config = new BosClientConfiguration();
//		config.setCredentials(new DefaultBceCredentials(ak, sk));
//		BosClient client = new BosClient(config);

		AppInfo appInfo = appInfoDao.loadById(Integer.valueOf(appId));

		appInfo.setUpdateTime(new Date());
		appInfo.setCompany(company);
		appInfo.setName(appName);
		appInfo.setClassify(1);
		TaManageUserInfo mUser = (TaManageUserInfo) request.getSession().getAttribute(SysConstants.SESSION_USER);
		appInfo.setCreator(mUser.getId());

		appInfo.setDescription(description);
		appInfo.setVersion(version);

		appInfoDao.update(appInfo);

		appImgDao.deleteByAppId(Integer.valueOf(appId));

		if (appImgList != null && appImgList.length > 0) {
			for (int i = 0; i < appImgList.length; i++) {
				if (appImgList[i].contains("http://")) {
					AppImg appImg = new AppImg();
					appImg.setUrl(appImgList[i]);
					appImg.setAppId(Integer.valueOf(appId));
					appImgDao.save(appImg);
				}
				// else {
				// AppImg appImg = new AppImg();
				// File imgFile = new File(appImgList[i]);
				// StringBuffer ImgBosStringBuffer = new StringBuffer();
				// ImgBosStringBuffer.append(pathProductName).append("/").append(pathVersion).append("/")
				// .append(cal.get(Calendar.YEAR)).append(cal.get(Calendar.MONTH))
				// .append(cal.get(Calendar.DAY_OF_MONTH)).append("/").append(cal.getTime().getTime())
				// .append("/").append(imgFile.getName());
				//
				// // client.putObject(bucketname, bosStringBuffer +
				// // imgFile.getName(), imgFile);TODO 注释放开
				// appImg.setUrl(baiduBosUrl + bosStringBuffer +
				// imgFile.getName());
				// appImg.setAppId(Integer.valueOf(appId));
				// appImgDao.save(appImg);
				// }
			}
		}

		appInfoDao.deleteAppSubjectByAppId(Integer.valueOf(appId));
		if (subjectList != null && subjectList.length > 0) {
			for (int i = 0; i < subjectList.length; i++) {
				AppSubject appSubject = new AppSubject();
				appSubject.setAppId(Integer.valueOf(appId));
				appSubject.setSubjectId(Integer.valueOf(subjectList[i]));
				appInfoDao.saveAppSubject(appSubject);
			}
		}

		appInfoDao.deleteAppGradeByAppId(Integer.valueOf(appId));
		if (gradeList != null && gradeList.length > 0) {
			for (int i = 0; i < gradeList.length; i++) {
				AppGrade appGrade = new AppGrade();
				appGrade.setAppId(Integer.valueOf(appId));
				appGrade.setGradeId(Integer.valueOf(gradeList[i]));
				appInfoDao.saveAppGrade(appGrade);
			}
		}

		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.mall.service.AppInfoService#queryAppInfoPage(java.util.
	 * Map, com.whty.page.PageContext)
	 */
	@Override
	public HandlerResult queryAppInfoPage(Map<String, Object> paraMap, PageContext page) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(appInfoDao.listByCondition(paraMap));
		rs.setPage(page);
		return rs;
	}

	/* (non-Javadoc)
	 * @see com.whty.assis.mall.service.AppInfoService#listByParam(java.util.Map)
	 */
	@Override
	public List<AppInfo> listByParam(Map<String, Object> paramMap) {
		return appInfoDao.listByParam(paramMap);
	}

}
