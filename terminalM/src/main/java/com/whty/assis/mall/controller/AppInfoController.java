/**
 * 
 */
package com.whty.assis.mall.controller;

import java.io.File;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.whty.assis.base.controller.BaseController;
import com.whty.assis.basicdata.dao.GradeDao;
import com.whty.assis.basicdata.dao.SubjectDao;
import com.whty.assis.basicdata.model.Grade;
import com.whty.assis.basicdata.model.Subject;
import com.whty.assis.mall.model.ApkInfo;
import com.whty.assis.mall.model.AppInfo;
import com.whty.assis.mall.service.AppInfoService;
import com.whty.common.util.ApkUtils;
import com.whty.common.util.CommonFunction;
import com.whty.common.util.Plupload;
import com.whty.common.util.PluploadUtil;
import com.whty.common.util.SysConfig;
import com.whty.common.util.TimeUtils;
import com.whty.common.util.ZipCompress;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * 引用控制台
 * 
 * @author zhangzheng
 * @date 2018年4月18日
 */
@Controller
@RequestMapping(value = "/manage/appInfo")
public class AppInfoController extends BaseController {

	@Autowired
	private AppInfoService appInfoService;

	@Autowired
	private GradeDao gradeDao;

	@Autowired
	private SubjectDao subjectDao;

	/**
	 * 上传 后添加的方法
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/detail")
	public void detail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");

		AppInfo bean = appInfoService.loadById(Integer.valueOf(id));

		printJson(response, bean);
	}

	/**
	 * 上传 后添加的方法
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/save")
	public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String result = appInfoService.saveAppInfo(request);

		printText(response, result);
	}

	/**
	 * 获取文件图标
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/getIcon")
	public void getIcon(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String filePath = request.getParameter("filePath");

		File uploadFile = new File(filePath);
		String fileName = uploadFile.getName();
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		String iconPath = null;

		Map<String, Object> param = new HashMap<String, Object>();

		if (suffix.equals("apk")) {// apk 文件
			ApkInfo apkInfo = new ApkUtils().getApkInfo(filePath);
			StringBuilder iconPathSb = new StringBuilder();
			while (uploadFile.getParentFile() != null && uploadFile.getParentFile().getName().length() != 0) {
				iconPathSb.insert(0, "/" + uploadFile.getParentFile().getName());
				uploadFile = uploadFile.getParentFile();
			}
			System.out.println(iconPathSb);
			String decompressDir = iconPathSb.append(File.separator).append(apkInfo.getApplicationLable()).toString();
			ZipCompress.readByApacheZipFile(filePath, decompressDir);

			System.out.println(decompressDir + File.separator + apkInfo.getApplicationIcon());

			iconPath = decompressDir + File.separator + apkInfo.getApplicationIcon();

			StringBuffer bosStringBuffer = new StringBuffer();
			Calendar cal = Calendar.getInstance();

			String ak = SysConfig.getStrValue("baidu.bos.ak");
			String sk = SysConfig.getStrValue("baidu.bos.sk");
			String bucketname = SysConfig.getStrValue("baidu.bos.bucketname");
			String pathProductName = SysConfig.getStrValue("baidu.bos.path.productname");
			String pathVersion = SysConfig.getStrValue("baidu.bos.path.version");
			String baiduBosUrl = SysConfig.getStrValue("baidu.bos.url");
			bosStringBuffer.append(pathProductName).append("/").append(pathVersion).append("/")
					.append(cal.get(Calendar.YEAR)).append(cal.get(Calendar.MONTH))
					.append(cal.get(Calendar.DAY_OF_MONTH)).append("/").append(cal.getTime().getTime());

			BosClientConfiguration config = new BosClientConfiguration();
			config.setCredentials(new DefaultBceCredentials(ak, sk));
			BosClient client = new BosClient(config);

			File iconFile = new File(iconPath);

			client.putObject(bucketname, bosStringBuffer + iconFile.getName(), iconFile);// 上传到百度云
			iconPath = baiduBosUrl + bosStringBuffer + iconFile.getName();// 图标地址

			param.put("iconPath", iconPath);// 图标地址
			param.put("packageName", apkInfo.getPackageName());// 包名
			param.put("versionCode", apkInfo.getVersionCode());// 外部版本号
			param.put("version", apkInfo.getVersionName());// 外部版本号

			String os = System.getProperty("os.name");
			if (os.toLowerCase().startsWith("win")) {
				if (StringUtils.isNotEmpty(apkInfo.getApplicationLable())) {
					param.put("label", apkInfo.getApplicationLable());// 应用名称
				}
			} else {
				if (StringUtils.isNotEmpty(apkInfo.getName())) {
					param.put("label", apkInfo.getName());// 应用名称
				}
			}
		}

		// TODO 先只让传apk文件
		// if (suffix.equals("exe")) {// exe 文件
		// StringBuilder iconPathSb = new StringBuilder();
		//
		// while (uploadFile.getParentFile() != null &&
		// uploadFile.getParentFile().getName().length() != 0) {
		// iconPathSb.insert(0, "/" + uploadFile.getParentFile().getName());
		// uploadFile = uploadFile.getParentFile();
		// }
		//
		// System.out.println(iconPathSb);
		// iconPath = iconPathSb.append("/icon.png").toString();
		// OutputStream inStream = new FileOutputStream(new File(iconPath));
		// try {
		// BufferedImage www = (BufferedImage) ((ImageIcon)
		// IconUtils.toIcon(f)).getImage();
		// ImageIO.write(www, "png", inStream);
		// inStream.flush();
		// inStream.close();
		// } catch (IOException e) {
		//
		// e.printStackTrace();
		// }
		// }

		printJson(response, param);

	}

	@RequestMapping(value = "/deleteAppInfo")
	public void deleteAppInfo(Plupload plupload, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("id");

		String result = appInfoService.deleteAppInfo(Integer.valueOf(id));

		printText(response, result);
	}

	@RequestMapping(value = "/editAppInfo")
	public void editAppInfo(Plupload plupload, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String result = appInfoService.updateAppInfo(request);

		printText(response, result);
	}

	/**
	 * 上传 后添加的方法
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/appImgUpload")
	public void appImgUpload(Plupload plupload, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		plupload.setRequest(request);
		String uploadDir = SysConfig.getStrValue("file_path_pre") + SysConfig.getStrValue("soft_upload_dir")
				+ CommonFunction.getDateSampleString(new Date()) + File.separator;

		File file = new File(uploadDir);
		if (!file.exists() || !file.isDirectory()) {
			file.mkdirs();
		}
		// 上传文件
		PluploadUtil.upload(plupload, new File(uploadDir));

		String allPath = uploadDir + plupload.getRename();

		File iconFile = new File(allPath);

		StringBuffer bosStringBuffer = new StringBuffer();
		Calendar cal = Calendar.getInstance();

		String ak = SysConfig.getStrValue("baidu.bos.ak");
		String sk = SysConfig.getStrValue("baidu.bos.sk");
		String bucketname = SysConfig.getStrValue("baidu.bos.bucketname");
		String pathProductName = SysConfig.getStrValue("baidu.bos.path.productname");
		String pathVersion = SysConfig.getStrValue("baidu.bos.path.version");
		String baiduBosUrl = SysConfig.getStrValue("baidu.bos.url");
		bosStringBuffer.append(pathProductName).append("/").append(pathVersion).append("/")
				.append(cal.get(Calendar.YEAR)).append(cal.get(Calendar.MONTH)).append(cal.get(Calendar.DAY_OF_MONTH))
				.append("/").append(cal.getTime().getTime());

		BosClientConfiguration config = new BosClientConfiguration();
		config.setCredentials(new DefaultBceCredentials(ak, sk));
		BosClient client = new BosClient(config);

		client.putObject(bucketname, bosStringBuffer + iconFile.getName(), iconFile);// 上传到百度云
		String iconPath = baiduBosUrl + bosStringBuffer + iconFile.getName();// 图标地址

		Map<String, Object> s = new HashMap<String, Object>();
		s.put("uploadFileUrl", iconPath);
		s.put("creator", "admin");
		s.put("updateTime", TimeUtils.date2String(new Date(), TimeUtils.DAY_FORMAT_1));
		printJson(response, s);
	}

	/**
	 * 上传 后添加的方法
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/upload")
	public void uploadInfo(Plupload plupload, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		plupload.setRequest(request);
		String uploadDir = SysConfig.getStrValue("file_path_pre") + SysConfig.getStrValue("soft_upload_dir")
				+ CommonFunction.getDateSampleString(new Date()) + File.separator;

		File file = new File(uploadDir);
		if (!file.exists() || !file.isDirectory()) {
			file.mkdirs();
		}
		// 上传文件
		PluploadUtil.upload(plupload, new File(uploadDir));

		String allPath = uploadDir + plupload.getRename();

		Map<String, Object> s = new HashMap<String, Object>();
		s.put("uploadFileUrl", allPath);
		s.put("creator", "admin");
		s.put("updateTime", TimeUtils.date2String(new Date(), TimeUtils.DAY_FORMAT_1));
		printJson(response, s);
	}

	@RequestMapping(value = "/queryAppInfo")
	public void queryAppInfo(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		Map<String, Object> paraMap = this.getParameterMap(request);
		PageContext page = PageContext.getContext();
		page.setCurrentPage(Integer.parseInt(paraMap.get("currPage").toString()));
		page.setPageSize(Integer.parseInt(paraMap.get("pageSize").toString()));

		String gradeIds = request.getParameter("gradeIds");
		String subjectIds = request.getParameter("subjectIds");

		if (StringUtils.isNotEmpty(gradeIds)) {
			paraMap.put("gradeIds", Arrays.asList(gradeIds));
		}

		if (StringUtils.isNotEmpty(subjectIds)) {
			paraMap.put("subjectIds", Arrays.asList(subjectIds));
		}

		HandlerResult pageList = new HandlerResult();

		try {
			page.setPagination(true);
			pageList = appInfoService.queryAppInfoPage(paraMap, page);
			page.setPagination(false);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", pageList.getResultList());
		resultMap.put("page", pageList.getPage());
		printJson(response, resultMap);
	}

	@RequestMapping(value = "/list")
	private String list(HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println(request.getRequestURI());
		System.out.println(request.getContextPath());
		System.out.println(request.getServletPath());

		Map<String, Object> paramMap = new HashMap<String, Object>();
		String searchSubjects = request.getParameter("searchSubjects");
		String searchGrades = request.getParameter("searchGrades");
		String appName = request.getParameter("appName");

		if (StringUtils.isNotEmpty(appName)) {
			paramMap.put("appName", appName);
			model.addAttribute("appName", appName);
		}

		if (StringUtils.isNotEmpty(searchGrades)) {
			List<String> searchGradeList = Arrays.asList(searchGrades.split(","));
			if (!searchGradeList.contains("-1")) {
				paramMap.put("gradeIds", Arrays.asList(searchGrades));
			}
			model.addAttribute("searchGrades", searchGrades);
		}

		if (StringUtils.isNotEmpty(searchSubjects)) {
			List<String> searchSubjectList = Arrays.asList(searchSubjects.split(","));

			if (!searchSubjectList.contains("-1")) {
				paramMap.put("subjectIds", Arrays.asList(searchGrades));
			}
			model.addAttribute("searchSubjects", searchSubjects);
		}

		String search_type = request.getParameter("search_type");
		String currentPage = request.getParameter("currPage");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");
		PageContext page = PageContext.getContext();

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

		HandlerResult pageList = appInfoService.queryAppInfoPage(paramMap);
		List<Grade> gradeList = gradeDao.listByCondition(new HashMap<String, Object>(0));
		List<Subject> subjectList = subjectDao.listByCondition(new HashMap<String, Object>(0));

		model.addAttribute("gradeList", gradeList);
		model.addAttribute("subjectList", subjectList);
		model.addAttribute("list", pageList.getResultList());

		return "appInfo.list";
	}

}
