package com.whty.assis.demo.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.whty.assis.api.respvo.ClientFile;
import com.whty.assis.base.controller.BaseController;
import com.whty.assis.demo.model.Soft;
import com.whty.assis.demo.model.SoftFile;
import com.whty.assis.demo.service.SoftService;
import com.whty.common.util.DelFile;
import com.whty.common.util.MD5Filter;
import com.whty.common.util.Plupload;
import com.whty.common.util.PluploadUtil;
import com.whty.common.util.SysConfig;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

import net.sf.json.JSONObject;

/**
 * 版本管理
 * 
 * @author zhanggz
 */
@Controller
@RequestMapping("/manage/version")
public class VersionController extends BaseController {

	@Autowired
	private SoftService softService;

	private Map<String, Object> getParameter(HttpServletRequest request) {
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if (request.getParameter("provinceCode") != null
				&& StringUtils.isNotEmpty(request.getParameter("provinceCode"))) {
			String provinceCode = request.getParameter("provinceCode");
			paraMap.put("provinceCode", provinceCode);
			paraMap.put("areaid", provinceCode.substring(0, 2));
		}
		if (request.getParameter("cityCode") != null && StringUtils.isNotEmpty(request.getParameter("cityCode"))) {
			String cityCode = request.getParameter("cityCode");
			paraMap.put("cityCode", cityCode);
			paraMap.put("areaid", cityCode.substring(0, 4));

		}
		if (request.getParameter("areaCode") != null && StringUtils.isNotEmpty(request.getParameter("areaCode"))) {
			String areaCode = request.getParameter("areaCode");
			paraMap.put("areaCode", areaCode);
			paraMap.put("areaid", areaCode);
		}

		if (request.getParameter("org") != null && StringUtils.isNotEmpty(request.getParameter("org"))) {
			paraMap.put("orgId", request.getParameter("org"));
		}

		if (request.getParameter("start_time") != null && StringUtils.isNotEmpty(request.getParameter("start_time"))) {
			paraMap.put("start_time", request.getParameter("start_time"));
		}

		return paraMap;
	}

	@RequestMapping(value = "/deleteSpecifyAreaUpgrades")
	public void deleteSpecifyAreaUpgrades(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("softId", request.getParameter("softId"));
		param.put("areaCode", request.getParameter("areaCode"));
		softService.deleteSoftAreaUpgrade(param);
		PrintWriter writer = response.getWriter();
		writer.write("success");
	}

	@RequestMapping(value = "/uploadBos")
	public void uploadBos(HttpServletRequest request, Model model, HttpServletResponse response) throws Exception {
		final String id = request.getParameter("id");

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);

		List<Soft> softList = softService.querySoft(param);

		for (final Soft soft : softList) {
			soft.setBaiduBosStatus(1);
			softService.updateSoft(soft);
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						File file = new File(soft.getFileUrl());
						// 上传到百度云
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

						client.putObject(bucketname, bosStringBuffer + file.getName(), file);// 上传到百度云
						String bosAddress = baiduBosUrl + bosStringBuffer + file.getName();// 图标地址
						System.out.println(bosAddress);
						soft.setBaiduBosStatus(2);
						soft.setBosUrl(bosAddress);
						softService.updateSoft(soft);
					} catch (Exception e) {
						soft.setBaiduBosStatus(999);
						softService.updateSoft(soft);
					}

				}
			}).start();
		}

		PrintWriter writer = response.getWriter();
		writer.write("success");
	}

	@RequestMapping(value = "/specifyAreaUpgrades")
	public String specifyAreaUpgrades(HttpServletRequest request, Model model) throws Exception {

		String softId = request.getParameter("softId");
		String isleveup = request.getParameter("isleveup");
		String userUpdateConfict = request.getParameter("userUpdateConfict");

		String areaCode = null;
		if (request.getParameter("provinceCode") != null
				&& StringUtils.isNotEmpty(request.getParameter("provinceCode").toString())) {
			areaCode = request.getParameter("provinceCode");
		}

		// //查询区列表
		if (request.getParameter("cityCode") != null
				&& StringUtils.isNotEmpty(request.getParameter("cityCode").toString())) {
			areaCode = request.getParameter("cityCode");
		}

		if (areaCode != null) {
			Map<String, Object> softArea = new HashMap<String, Object>();
			softArea.put("softId", softId);
			softArea.put("areaCode", areaCode);
			softService.saveSoftAreaUpgrade(softArea);
		}

		String currentPage = request.getParameter("currPage");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");

		PageContext page = PageContext.getContext();

		Map<String, Object> paramMap = this.getParameter(request);
		paramMap.put("softId", softId);
		paramMap.put("isleveup", isleveup);
		paramMap.put("userUpdateConfict", userUpdateConfict);

		// 请自行验证
		if (null == currentPage) {
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
		HandlerResult handlerResult = softService.querySoftAreaUpgradePage(paramMap);
		model.addAttribute("softAreaUpgradeList", handlerResult.getResultList());
		model.addAttribute("page", page);
		model.addAllAttributes(paramMap);
		page.setPagination(false);
		return "version/specifyAreaUpgrades";
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

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("softNameLike", request.getParameter("softName"));
		paramMap.put("versionCodeLike", request.getParameter("versionCode"));
		paramMap.put("fileType", request.getParameter("fileType"));
		paramMap.put("startTime", request.getParameter("startTime"));
		paramMap.put("endTime", request.getParameter("endTime"));

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
		HandlerResult handlerResult = softService.querySoftPage(paramMap);
		model.addAttribute("versionList", handlerResult.getResultList());
		model.addAttribute("page", page);
		model.addAllAttributes(paramMap);
		page.setPagination(false);
		return "version/list";
	}

	/**
	 * 查看详情
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/detail")
	public void detail(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("版本详情数据加载");
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String id = request.getParameter("id");
		Map map = new HashMap();
		map.put("id", id);
		List<Soft> softList = softService.querySoft(map);
		Soft soft = softList.get(0);
		PrintWriter writer = response.getWriter();
		writer.write(JSONObject.fromObject(soft).toString());
	}

	/**
	 * 新增
	 */
	@RequestMapping(value = "/add")
	public void add(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("新增版本信息");
		}
		String fileUrl = request.getParameter("fileUrl");
		String fileName = request.getParameter("fileName");
		String fileRename = request.getParameter("fileRename");
		String softName = request.getParameter("softName");
		String versionCode = request.getParameter("versionCode");
		String softType = request.getParameter("softType");
		String fileType = request.getParameter("fileType");
		String updateContent = request.getParameter("updateContent");
		String forceUpdate = request.getParameter("forceUpdate");
		String fileSizeStr = request.getParameter("fileSize");
		String status = request.getParameter("status");
		String downloadUrl = request.getParameter("downloadUrl");
		int fileSize = 0;
		if (StringUtils.isNotEmpty(fileSizeStr)) {
			fileSize = Integer.parseInt(fileSizeStr);
		}

		Soft soft = new Soft();
		soft.setFileName(fileName);
		soft.setFileUrl(fileUrl);
		soft.setFileRename(fileRename);
		soft.setSoftName(softName);
		soft.setVersionCode(versionCode);
		soft.setSoftType(softType);
		soft.setFileType(fileType);
		soft.setUpdateContent(updateContent);
		soft.setForceUpdate(forceUpdate);
		soft.setFileSize(fileSize);
		soft.setCreateTime(new Date());
		soft.setUpdateTime(new Date());
		soft.setStatus(status);
		soft.setDownloadUrl(downloadUrl);

		soft.setMd5(MD5Filter.getMd5ByFile(new File(fileUrl)));

		softService.saveSoft(soft);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write("success");
	}

	/**
	 * 解压入库
	 */
	@RequestMapping(value = "/fileUploadBos")
	public void fileUploadBos(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		final String id = request.getParameter("id");

		Map<String, Object> softParam = new HashMap<String, Object>();
		softParam.put("softId", id);
		List<Soft> softList = softService.querySoft(softParam);

		final Soft soft = softList.get(0);

		new Thread(new Runnable() {
			@Override
			public void run() {
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("softId", id);
				List<SoftFile> softFileList = softService.querySoftFile(param);

				Calendar cal = Calendar.getInstance();
				String ak = SysConfig.getStrValue("baidu.bos.ak");
				String sk = SysConfig.getStrValue("baidu.bos.sk");
				String bucketname = SysConfig.getStrValue("baidu.bos.bucketname");
				String pathProductName = SysConfig.getStrValue("baidu.bos.path.productname");
				String pathVersion = SysConfig.getStrValue("baidu.bos.path.version");
				String baiduBosUrl = SysConfig.getStrValue("baidu.bos.url");

				for (SoftFile bean : softFileList) {

					StringBuffer bosStringBuffer = new StringBuffer();

					bosStringBuffer.append(pathProductName).append("/").append(pathVersion).append("/")
							.append(cal.get(Calendar.YEAR)).append(cal.get(Calendar.MONTH))
							.append(cal.get(Calendar.DAY_OF_MONTH)).append("/").append(soft.getId()).append("/")
							.append(cal.getTime().getTime()).append("/");

					BosClientConfiguration config = new BosClientConfiguration();
					config.setCredentials(new DefaultBceCredentials(ak, sk));
					BosClient client = new BosClient(config);

					File file = new File(bean.getAbsoluteUrl());

					client.putObject(bucketname, bosStringBuffer + file.getName(), file);// 上传到百度云
					String bosAddress = baiduBosUrl + bosStringBuffer + file.getName();// 图标地址

					bean.setBosStatus(1);
					bean.setBosUrl(bosAddress);
					softService.updateSoftFile(bean);
				}
				soft.setFileBosStatus(2);
				softService.updateSoft(soft);
			}
		}).start();

		soft.setFileBosStatus(1);
		softService.updateSoft(soft);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write("success");
	}

	/**
	 * 解压入库
	 */
	@RequestMapping(value = "/unzip")
	public void unzip(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("解压入库");
		}
		String id = request.getParameter("id");

		boolean flag = softService.unzipSoft(id);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write(flag ? "success" : "error");
	}

	/**
	 * 修改
	 */
	@RequestMapping(value = "/edit")
	public void edit(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("修改版本信息");
		}
		String id = request.getParameter("id");
		String softName = request.getParameter("softName");
		String versionCode = request.getParameter("versionCode");
		String updateContent = request.getParameter("updateContent");
		String forceUpdate = request.getParameter("forceUpdate");
		String status = request.getParameter("status");
		String downloadUrl = request.getParameter("downloadUrl");

		Soft soft = new Soft();
		soft.setId(id);
		soft.setSoftName(softName);
		soft.setVersionCode(versionCode);
		soft.setUpdateContent(updateContent);
		soft.setForceUpdate(forceUpdate);
		soft.setUpdateTime(new Date());
		soft.setStatus(status);
		soft.setDownloadUrl(downloadUrl);
		softService.updateSoft(soft);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write("success");
	}

	/**
	 * 查询最新版本信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/queryNew")
	public void queryNew(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("查询最新版本信息");
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		Map paramMap = new HashMap();
		paramMap.put("fileType", "1");
		paramMap.put("softType", "0");
		Soft soft = softService.queryNew(paramMap);
		PrintWriter writer = response.getWriter();
		writer.write(JSONObject.fromObject(soft).toString());
	}

	/**
	 * 生成升级数据文件
	 */
	@RequestMapping(value = "/createUpgradeFile")
	public void createUpgradeFile(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("生成升级数据文件");
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String softId = request.getParameter("softId");
		softService.createSoftUpgradeFile(softId);
		PrintWriter writer = response.getWriter();
		writer.write("success");
	}

	/**
	 * 删除软件版本 1.删除软件版本信息 2.删除绿色软件包软件列表信息 3.删除软件对应的升级文件列表信息
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/deleteSoft")
	public void deleteSoft(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("删除软件版本");
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String ids = request.getParameter("ids");
		if (StringUtils.isNotEmpty(ids)) {
			List softIdList = Arrays.asList(ids.split(","));
			softService.deleteSoft(softIdList);
		}
		PrintWriter writer = response.getWriter();
		writer.write("success");
	}

	/**
	 * 上传
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/uploadInfo", method = RequestMethod.POST)
	public void uploadInfo(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String uploadDir = SysConfig.getStrValue("file_path_pre") + SysConfig.getStrValue("soft_upload_dir");
		File file = new File(uploadDir);
		if (!file.exists() || !file.isDirectory()) {
			file.mkdirs();
		}
		response.setCharacterEncoding("UTF-8");

		MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());

		// MultipartHttpServletRequest multipartRequest =
		// resolver.resolveMultipart(request);

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 获取前台传值
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();

		Soft soft = new Soft();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile mf = entity.getValue();
			String fileName = mf.getOriginalFilename();

			String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

			String fileRename = String.valueOf(System.currentTimeMillis()) + "." + fileExt;
			File uploadFile = new File(uploadDir + fileRename);
			try {
				FileCopyUtils.copy(mf.getBytes(), uploadFile);
			} catch (IOException e) {
				e.printStackTrace();
			}

			soft.setFileName(fileName);
			soft.setFileRename(fileRename);
			soft.setFileUrl(uploadDir + fileRename);

		}

		PrintWriter writer = response.getWriter();
		writer.write(JSONObject.fromObject(soft).toString());
	}

	/**
	 * 上传 后添加的方法
	 */
	@RequestMapping(value = "/uploadInfo1", method = RequestMethod.POST)
	public void uploadInfo1(Plupload plupload, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		plupload.setRequest(request);

		String uploadDir = SysConfig.getStrValue("file_path_pre") + SysConfig.getStrValue("soft_upload_dir");
		File file = new File(uploadDir);
		if (!file.exists() || !file.isDirectory()) {
			file.mkdirs();
		}

		response.setCharacterEncoding("UTF-8");

		try {
			// 上传文件
			PluploadUtil.upload(plupload, new File(uploadDir));
			// 判断文件是否上传成功（被分成块的文件是否全部上传完成）
			if (PluploadUtil.isUploadFinish(plupload)) {
				logger.info(plupload.getName() + "-----------上传完毕");
			}

		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Soft soft = new Soft();
		soft.setFileName(plupload.getName());
		soft.setFileRename(plupload.getRename());
		soft.setFileUrl(uploadDir + plupload.getRename());

		PrintWriter writer = response.getWriter();
		writer.write(JSONObject.fromObject(soft).toString());
	}

	/**
	 * 删除上传的无用文件
	 */
	@RequestMapping(value = "/deleteDisableFile")
	public void deleteDisableFile(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("删除上传的无用文件");
		}
		String fileUrl = request.getParameter("fileUrl");
		if (StringUtils.isNotEmpty(fileUrl)) {
			File softFile = new File(fileUrl);
			if (softFile.exists()) {
				softFile.deleteOnExit();
				try {
					FileUtils.forceDelete(softFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write("success");
	}

	/**
	 * 指定用户升级
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/initSetUserUpgrade")
	public String initSetUserUpgrade(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("指定用户升级");
		}
		model.addAttribute("softId", request.getParameter("softId"));
		return "version/setUserUpgradeList";
	}

	/**
	 * 指定用户升级 普通用户查询列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/querySetUserUpgrade")
	public void querySetUserUpgrade(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("指定用户升级--普通用户查询列表数据加载");
		}

		Map<String, Object> paramMap = this.getParameterMap(request);
		PageContext page = PageContext.getContext();

		if (paramMap.get("currPage") == null || StringUtils.isEmpty(paramMap.get("currPage").toString())) {
			page.setCurrentPage(1);
			page.setPageSize(10);
			page.setTotalPage(0);
			page.setTotalRows(0);
		} else {
			page.setCurrentPage(Integer.parseInt(paramMap.get("currPage").toString()));
			page.setPageSize(Integer.parseInt(paramMap.get("pageSize").toString()));
		}

		page.setPagination(true);
		HandlerResult handlerResult = softService.querySetUserUpgrade(paramMap);

		Map resultMap = new HashMap();

		resultMap.put("list", handlerResult.getResultList());
		resultMap.put("page", page);
		page.setPagination(false);

		printJson(response, resultMap);
	}

	/**
	 * 指定用户升级
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/setUserUpgrade")
	public void setUserUpgrade(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("指定用户升级");
		}
		String softId = request.getParameter("softId");
		String allIds = request.getParameter("allIds");
		String ids = request.getParameter("ids");

		List allIdList = new ArrayList();
		List idList = new ArrayList();
		if (StringUtils.isNotEmpty(allIds)) {
			allIdList = Arrays.asList(allIds.split(","));
		}
		if (StringUtils.isNotEmpty(ids)) {
			idList = Arrays.asList(ids.split(","));
		}

		softService.setUserUpgrade(softId, allIdList, idList);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write("success");
	}

	/**
	 * 指定全部用户升级
	 */
	@RequestMapping(value = "/setAllUserUpgrade")
	public void setAllUserUpgrade(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("指定全部用户升级");
		}
		String id = request.getParameter("id");
		String allUserUpgrade = request.getParameter("allUserUpgrade");

		Soft soft = new Soft();
		soft.setId(id);
		soft.setAllUserUpgrade(allUserUpgrade);
		if ("1".equals(allUserUpgrade)) {
			soft.setUserUpdate("0");
		}

		softService.setAllUserUpgrade(soft);
		;

		printText(response, "success");
	}

	/**
	 * 开放安装包的下载
	 */
	@RequestMapping(value = "/openDownload")
	public void openDownload(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("开放安装包的下载");
		}
		String id = request.getParameter("id");
		String softType = request.getParameter("softType");

		Soft soft = new Soft();
		soft.setId(id);
		soft.setIsleveup("1");
		soft.setSoftType(softType);
		softService.openDownload(soft);
		Soft softDownload = softService.getSoft(id);
		String path = SysConfig.getStrValue("file_path_pre") + "tmpfs";
		File tmps = new File(path);
		if (tmps.exists()) {
			// 把安装包文件放入内存挂载的硬盘tmpfs/client中
			DelFile.delFolderFile(path);
			File srcFile = new File(softDownload.getFileUrl());
			File destFile = new File(softDownload.getFileUrl().replace("teachassistantfiles", "tmpfs"));
			FileUtils.copyFile(srcFile, destFile);
			// 文件入内存改数据库路径
			soft.setFileUrl(softDownload.getFileUrl().replace("teachassistantfiles", "tmpfs"));
			softService.updateSoft(soft);
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write("success");
	}

	/**
	 * 增量升级文件放入内存挂载的硬盘中
	 */
	@RequestMapping(value = "/putTmpfs")
	public void putTmpfs(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("增量升级文件放入内存挂载的硬盘中");
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String softId = request.getParameter("softId");
		Soft soft = softService.getSoft(softId);
		// 先把原来的数据路径还原
		softService.updateSoftUpgradeFileOldPath(softId);
		// 再更新内存硬盘的文件
		String folderPath = SysConfig.getStrValue("file_path_pre") + "tmpfs" + File.separator + "unzip";
		DelFile.delFolderAllFile(folderPath);
		File srcFile = new File(soft.getFileUrl());
		File destFile = new File(soft.getFileUrl().replace("teachassistantfiles", "tmpfs"));
		FileUtils.copyFile(srcFile, destFile);
		// 最后更新当前数据的路径
		softService.updateSoftUpgradeFileTmpfs(softId);
		PrintWriter writer = response.getWriter();
		writer.write("success");
	}

	/**
	 * 跳转到升级管理
	 */
	@RequestMapping(value = "/upgrade")
	public String upgrade(HttpServletRequest request, Model model) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("跳转到版本升级管理页面");
		}
		return "version/upgrade";
	}

	/**
	 * 生成升级文件
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/generateFiles")
	public void generateFiles(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("生成升级文件");
		final String id = request.getParameter("id");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("softId", id);
		final List<Soft> s = softService.ListUpgradeSoft(param);
		new Thread(new Runnable() {
			@Override
			public void run() {
				if (s != null && s.size() > 0) {
					for (Soft soft : s) {
						String versionStrs = soft.getUpgradeVersionCode();
						String softType = soft.getSoftType();
						String[] versionAttr = versionStrs.split("；");
						String newSoftId = soft.getId();

						Map<String, Object> oldSoftParam = new HashMap<String, Object>();
						oldSoftParam.put("softType", softType);

						for (int i = 0; i < versionAttr.length; i++) {
							oldSoftParam.put("versionCode", versionAttr[i]);
							Soft oldSoft = softService.querySoftByMap(oldSoftParam);
							Map<String, Object> newVersionFiles = new HashMap<String, Object>();
							newVersionFiles.put("id", newSoftId);// 最新版id
							newVersionFiles.put("versionCode", oldSoft.getVersionCode());// 老版本号
							newVersionFiles.put("softType", soft.getSoftType());// 软件类型

							List<ClientFile> clientFileList = softService.clientFileList(newVersionFiles);

							if (clientFileList != null && clientFileList.size() != 0) {
								try {
									System.out.println("生成软件包");
									softService.uploadPackageUrl(newVersionFiles, clientFileList);

								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}
						// 上传百度bos完成
						System.out.println("生成软件包完成");
						soft.setBaiduBosStatus(2);
						softService.updateBaiduBosStatus(soft);
					}

				}

			}

		}).start();

		if (s != null && s.size() > 0) {
			// for (Soft soft : s) {
			// soft.setBaiduBosStatus(1);

			// softService.updateBaiduBosStatus(soft);
			// }
		}
	}

	/**
	 * 查询版本升级列表
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/upgradeList")
	public void upgradeList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("版本升级列表页数据加载");
		}
		Map<String, Object> paraMap = this.getParameterMap(request);
		PageContext page = PageContext.getContext();
		// 请自行验证
		if (paraMap.get("currPage") == null || StringUtils.isEmpty(paraMap.get("currPage").toString())) {
			page.setCurrentPage(1);
			page.setPageSize(10);
			page.setTotalPage(0);
			page.setTotalRows(0);
		} else {
			page.setCurrentPage(Integer.parseInt(paraMap.get("currPage").toString()));
			page.setPageSize(Integer.parseInt(paraMap.get("pageSize").toString()));
		}
		page.setPagination(true);
		HandlerResult handlerResult = softService.queryUpgradeSoft(paraMap);
		Map resultMap = new HashMap();
		resultMap.put("list", handlerResult.getResultList());
		resultMap.put("page", page);
		page.setPagination(false);

		printJson(response, resultMap);
	}

	/**
	 * 创建升级
	 */
	@RequestMapping(value = "/createUpgradeSoft")
	public void createUpgradeSoft(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("创建升级");
		}
		String softId = request.getParameter("softId");
		String isleveup = request.getParameter("isleveup");
		String upgradeSoftId = request.getParameter("upgradeSoftId");

		Soft soft = new Soft();
		soft.setId(softId);
		soft.setIsleveup(isleveup);

		List<String> upgradeSoftIdList = Arrays.asList(upgradeSoftId.split(","));

		softService.createUpgradeSoft(soft, upgradeSoftIdList);

		printText(response, "000000");
	}

	/**
	 * 查看详情
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/queryUpgradeSoftDetail")
	public void queryUpgradeSoftDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map paramap = this.getParameterMap(request);
		Soft soft = softService.queryUpgradeSoftDetail(paramap);
		printJson(response, soft);
	}

	/**
	 * 编辑升级
	 */
	@RequestMapping(value = "/editUpgradeSoft")
	public void editUpgradeSoft(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("编辑升级");
		}
		String softId = request.getParameter("softId");
		String isleveup = request.getParameter("isleveup");
		String upgradeSoftId = request.getParameter("upgradeSoftId");

		Soft soft = new Soft();
		soft.setId(softId);
		soft.setIsleveup(isleveup);

		List<String> upgradeSoftIdList = Arrays.asList(upgradeSoftId.split(","));

		softService.editUpgradeSoft(soft, upgradeSoftIdList);

		printText(response, "000000");
	}

	/**
	 * 删除升级
	 */
	@RequestMapping(value = "/deleteUpgradeSoft")
	public void deleteUpgradeSoft(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("删除升级");
		}
		String softId = request.getParameter("softId");
		softService.deleteUpgradeSoft(softId);

		printText(response, "000000");
	}

	/**
	 * 查询所有的绿色包版本号
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/queryAllVersionCode")
	public void queryAllVersionCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("查询所有的绿色包版本号");
		}
		Map paramap = new HashMap();
		paramap.put("isleveup", "0");

		String softType = request.getParameter("softType");

		paramap.put("softType", softType);

		List<Soft> list = softService.queryVersionCode(paramap);

		Map resultMap = new HashMap();
		resultMap.put("list", list);

		printJson(response, resultMap);
	}

	/**
	 * 取比当前版本小的，不是最新版本
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/queryUpgradeVersionCode")
	public void queryUpgradeVersionCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("取比当前版本小的，不是最新版本，没有当前升级类型的");
		}
		Map paramap = this.getParameterMap(request);

		Soft soft = softService.querySoftByMap(paramap);

		paramap.put("softType", soft.getSoftType());

		List<Soft> list = softService.queryUpgradeVersionCode(paramap);

		Map resultMap = new HashMap();
		resultMap.put("list", list);

		printJson(response, resultMap);
	}

	/**
	 * 生成升级包
	 */
	@RequestMapping(value = "/createUpgradePackage")
	public void createUpgradePackage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("生成升级包");
		}
		String softId = request.getParameter("softId");

		softService.createUpgradePackage(softId);

		printText(response, "000000");
	}
}
