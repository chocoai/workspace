/**
 * 
 */
package com.whty.ebp.manage.controller;

import java.io.File;
import java.io.IOException;
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
import com.whty.common.util.CommonFunction;
import com.whty.common.util.MD5Filter;
import com.whty.common.util.Plupload;
import com.whty.common.util.PluploadUtil;
import com.whty.common.util.SysConfig;
import com.whty.ebp.base.controller.BaseController;
import com.whty.ebp.manage.dao.IdDao;
import com.whty.ebp.manage.dao.PlatformInfoDao;
import com.whty.ebp.manage.model.ApkInfo;
import com.whty.ebp.manage.model.PlatformInfo;
import com.whty.ebp.manage.model.SchoolApp;
import com.whty.ebp.manage.service.AreaService;
import com.whty.ebp.manage.service.BasePropertyService;
import com.whty.ebp.manage.service.SchoolAppService;
import com.whty.ebp.manage.utils.ApkUtils;
import com.whty.ebp.manage.utils.ZipCompress;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

import net.sf.json.JSONArray;

/**
 * 学校应用管理
 * 
 * @author zhangzheng
 * @date 2018年10月23日
 */
@Controller
@RequestMapping("/manage/schoolApp")
public class SchoolAppManageController extends BaseController {

	@Autowired
	private SchoolAppService schoolAppService;

	@Autowired
	private PlatformInfoDao platformInfoDao;

	@Autowired
	private AreaService areaService;

	@Autowired
	private BasePropertyService basePropertyService;

	@Autowired
	private IdDao idDao;

	/**
	 * 分析apk
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value = "/analysisApk")
	public void analysisApk(Plupload plupload, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		plupload.setRequest(request);
		String uploadDir = SysConfig.getStrValue("file_path_pre") + SysConfig.getStrValue("soft_upload_dir")
				+ CommonFunction.getDateSampleString(new Date()) + File.separator;

		File uploadFile = new File(uploadDir);

		if (!uploadFile.exists() || !uploadFile.isDirectory()) {
			uploadFile.mkdirs();
		}
		// 上传文件
		PluploadUtil.upload(plupload, new File(uploadDir));

		String allPath = uploadDir + plupload.getRename();

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("filePath", allPath);

		File apkFile = new File(allPath);

		param.put("fileSize", apkFile.length());

		String md5 = MD5Filter.getMd5ByFile(apkFile);
		// apk 文件
		ApkInfo apkInfo = new ApkUtils().getApkInfo(allPath);
		StringBuilder iconPathSb = new StringBuilder();
		String iconPath = null;

		while (apkFile.getParentFile() != null && apkFile.getParentFile().getName().length() != 0) {
			iconPathSb.insert(0, "/" + apkFile.getParentFile().getName());
			apkFile = apkFile.getParentFile();
		}
		System.out.println(iconPathSb);
		String decompressDir = iconPathSb.append(File.separator).append(apkInfo.getApplicationLable()).toString();

		ZipCompress.readByApacheZipFile(allPath, decompressDir);

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
				.append(cal.get(Calendar.YEAR)).append(cal.get(Calendar.MONTH)).append(cal.get(Calendar.DAY_OF_MONTH))
				.append("/").append(cal.getTime().getTime());

		BosClientConfiguration config = new BosClientConfiguration();
		config.setCredentials(new DefaultBceCredentials(ak, sk));
		BosClient client = new BosClient(config);

		File iconFile = new File(iconPath);

		client.putObject(bucketname, bosStringBuffer + iconFile.getName(), iconFile);// 上传到百度云
		iconPath = baiduBosUrl + bosStringBuffer + iconFile.getName();// 图标地址

		param.put("md5", md5);
		param.put("iconPath", iconPath);// 图标地址
		param.put("packageName", apkInfo.getPackageName());// 包名
		param.put("versionCode", apkInfo.getVersionCode());// 外部版本号
		param.put("version", apkInfo.getVersionName());// 外部版本号

		if (StringUtils.isNotEmpty(apkInfo.getApplicationLable())) {
			param.put("label", apkInfo.getApplicationLable());// 应用名称
		} else {
			param.put("label", apkInfo.getApplicationName());// 应用名称
		}

		printJson(response, param);

	}

	@RequestMapping(value = "/getAllSchool")
	public void getAllSchool(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		String platformCode = request.getParameter("platformCode");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String orgName = request.getParameter("orgName");
		String aamUrl = basePropertyService.getPropertyValue("platform_url", platformCode);

		String url = aamUrl + "ogra/query";

		JSONArray schoolJson = schoolAppService.listSchool(url, platformCode, province, city, orgName);
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(schoolJson);
	}

	/**
	 * 跳转到版本管理
	 */
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, Model model) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("版本列表页数据加载");
		}
		String search_type = request.getParameter("search_type");
		String currentPage = request.getParameter("currPage");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");

		Map<String, Object> areaMap = new HashMap<String, Object>();
		areaMap.put("levelId", 1);
		List<Map<String, Object>> provinceList = areaService.queryArea(areaMap);
		model.addAttribute("provinceList", JSONArray.fromObject(provinceList));

		Map<String, Object> paramMap = new HashMap<String, Object>();

		PageContext page = PageContext.getContext();

		List<PlatformInfo> platformInfoList = platformInfoDao.listByCondition(paramMap);

		model.addAttribute("platformInfoList", platformInfoList);

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
		HandlerResult handlerResult = schoolAppService.querySchoolAppPage(paramMap);
		model.addAttribute("list", handlerResult.getResultList());
		model.addAttribute("page", page);
		model.addAllAttributes(paramMap);
		page.setPagination(false);
		return "schoolApp/list";
	}

	/**
	 * 保存
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/save")
	public void save(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

		String platformCode = request.getParameter("platformCode");
		String orgId = request.getParameter("orgId");
		String orgName = request.getParameter("orgName");
		String appName = request.getParameter("appName");
		String packageName = request.getParameter("packageName");
		String interVersionCode = request.getParameter("interVersionCode");
		String versionCode = request.getParameter("versionCode");
		String iconPath = request.getParameter("iconPath");
		String description = request.getParameter("description");
		String fileSize = request.getParameter("fileSize");
		String filePath = request.getParameter("filePath");
		String md5 = request.getParameter("md5");
		
		SchoolApp bean = new SchoolApp();
		bean.setPlatformCode(platformCode);
		bean.setCreateTime(new Date());
		bean.setOrgId(orgId);
		bean.setOrgName(orgName);
		bean.setApkPackageName(packageName);
		bean.setAppName(appName);
		bean.setInterVersionCode(interVersionCode);
		bean.setVersionCode(versionCode);
		bean.setIcon(iconPath);
		bean.setDescription(description);
		bean.setFileSize(fileSize);
		bean.setMd5(md5);
		
		Map<String, Object> idParam = new HashMap<String, Object>(2);
		idParam.put("databaseName", SysConfig.getStrValue("databaseName"));
		idParam.put("tableName", "EBP_SCHOOL_APP");
		Integer id = idDao.getId(idParam);

		schoolAppService.save(bean);
		
		bean.setId(id);
		
		final File f = new File(filePath);
		// 上传百度云
		new Thread(new Runnable() {
			@Override
			public void run() {
//				String ak = SysConfig.getStrValue("baidu.bos.ak");
//				String sk = SysConfig.getStrValue("baidu.bos.sk");
//				String bucketname = SysConfig.getStrValue("baidu.bos.bucketname");
//				String pathProductName = SysConfig.getStrValue("baidu.bos.path.productname");
//				String pathVersion = SysConfig.getStrValue("baidu.bos.path.version");
//				String baiduBosUrl = SysConfig.getStrValue("baidu.bos.url");
//				Calendar cal = Calendar.getInstance();
//				StringBuffer bosStringBuffer = new StringBuffer();
//				bosStringBuffer.append(pathProductName).append("/").append(pathVersion).append("/")
//						.append(cal.get(Calendar.YEAR)).append("/").append(cal.get(Calendar.MONTH)).append("/")
//						.append(cal.get(Calendar.DAY_OF_MONTH)).append("/").append(cal.getTime().getTime());
//				BosClientConfiguration config = new BosClientConfiguration();
//				config.setCredentials(new DefaultBceCredentials(ak, sk));
//				BosClient client = new BosClient(config);
//
//				client.putObject(bucketname, bosStringBuffer + f.getName(), f);// 上传到百度云
//				String bosAddress = baiduBosUrl + bosStringBuffer + f.getName();// 文件地址
//				System.out.println(bosAddress);
//
//				bean.setDownUrl(bosAddress);
				bean.setDownUrl("12");
				schoolAppService.update(bean);
			}
		}).start();

		printText(response, "success");
	}

	/**
	 * 保存
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateStatus")
	public void updateStatus(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String id = request.getParameter("id");
		String status = request.getParameter("status");

		SchoolApp schoolApp = new SchoolApp();
		schoolApp.setId(Integer.valueOf(id));
		schoolApp.setStatus(status);
		schoolAppService.update(schoolApp);
		printText(response, "success");
	}

	/**
	 * 保存
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String id = request.getParameter("id");
		schoolAppService.delete(Integer.valueOf(id));
		printText(response, "success");
	}

}
