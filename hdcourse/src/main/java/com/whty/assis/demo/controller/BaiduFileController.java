/**
 * 
 */
package com.whty.assis.demo.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.whty.assis.base.controller.BaseController;
import com.whty.assis.demo.dao.IdDao;
import com.whty.assis.demo.model.BaiduFile;
import com.whty.assis.demo.model.Soft;
import com.whty.assis.demo.service.BaiduFileService;
import com.whty.common.util.FileMDFive;
import com.whty.common.util.Plupload;
import com.whty.common.util.PluploadUtil;
import com.whty.common.util.SysConfig;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/manage/baiduFile")
public class BaiduFileController extends BaseController {

	@Autowired
	private BaiduFileService baiduFileService;

	@Autowired
	private IdDao idDao;

	/**
	 * 上传 后添加的方法
	 */
	@RequestMapping(value = "/uploadInfo", method = RequestMethod.POST)
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

				// File f = new File(uploadDir + plupload.getRename());
				//
				// BaiduFile baiduFile = new BaiduFile();
				// baiduFile.setCreateTime(new Date());
				// baiduFile.setUpdateTime(new Date());
				// baiduFile.setFileName(plupload.getName());
				// baiduFile.setFileSize(f.length());
				//
				// String md5 = FileMDFive.getFileMD5(f);
				// baiduFile.setMd5(md5);
				//
				// baiduFileService.save(baiduFile);

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
	 * 上传 后添加的方法
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String fileUrl = request.getParameter("fileUrl");
		String fileName = request.getParameter("fileName");
		String fileRename = request.getParameter("fileRename");
		String description = request.getParameter("description");

		String uploadDir = SysConfig.getStrValue("file_path_pre") + SysConfig.getStrValue("soft_upload_dir");
		final File f = new File(uploadDir + fileRename);

		Map<String, Object> idParam = new HashMap<String, Object>(2);
		idParam.put("databaseName", SysConfig.getStrValue("databaseName"));
		idParam.put("tableName", "t_baidu_file");
		Integer id = idDao.getId(idParam);

		final BaiduFile baiduFile = new BaiduFile();
		baiduFile.setCreateTime(new Date());
		baiduFile.setUpdateTime(new Date());
		baiduFile.setFileName(fileName);
		baiduFile.setFileSize(f.length());
		baiduFile.setDescription(description);
		String md5 = FileMDFive.getFileMD5(f);
		baiduFile.setMd5(md5);
		baiduFile.setStatus(0);
		baiduFileService.save(baiduFile);

		baiduFile.setId(id);

		new Thread(new Runnable() {
			@Override
			public void run() {
				String ak = SysConfig.getStrValue("baidu.bos.ak");
				String sk = SysConfig.getStrValue("baidu.bos.sk");
				String bucketname = SysConfig.getStrValue("baidu.bos.bucketname");
				String pathProductName = SysConfig.getStrValue("baidu.bos.path.productname");
				String pathVersion = SysConfig.getStrValue("baidu.bos.path.version");
				String baiduBosUrl = SysConfig.getStrValue("baidu.bos.url");
				Calendar cal = Calendar.getInstance();
				StringBuffer bosStringBuffer = new StringBuffer();
				bosStringBuffer.append(pathProductName).append("/").append(pathVersion).append("/")
						.append(cal.get(Calendar.YEAR)).append("/").append(cal.get(Calendar.MONTH)).append("/")
						.append(cal.get(Calendar.DAY_OF_MONTH)).append("/").append(cal.getTime().getTime());

				BosClientConfiguration config = new BosClientConfiguration();
				config.setCredentials(new DefaultBceCredentials(ak, sk));
				BosClient client = new BosClient(config);

				client.putObject(bucketname, bosStringBuffer + f.getName(), f);// 上传到百度云
				String bosAddress = baiduBosUrl + bosStringBuffer + f.getName();// 图标地址
				System.out.println(bosAddress);

				baiduFile.setDownUrl(bosAddress);

				baiduFile.setStatus(1);
				baiduFileService.update(baiduFile);
			}
		}).start();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write("success");
	}

	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("授权机构列表页数据加载");
		}
		String search_type = request.getParameter("search_type");
		String currentPage = request.getParameter("currPage");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");

		Map<String, Object> paramMap = new HashMap<String, Object>();

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
		HandlerResult handlerResult = baiduFileService.listByConditionListPage(paramMap);

		model.addAttribute("list", handlerResult.getResultList());
		model.addAttribute("page", page);
		model.addAllAttributes(paramMap);
		page.setPagination(false);

		return "baiduFile/list";
	}

	/**
	 * 新增授权机构信息
	 */
	@RequestMapping("/save")
	public void save(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		// if (logger.isDebugEnabled()) {
		// logger.debug("新增授权机构信息");
		// }
		//
		// String contract_number = request.getParameter("contract_number");
		//
		// Map<String, Object> paramMap = new HashMap<String, Object>();
		// paramMap.put("contract_number", contract_number);
		// List<Ebp_licence_org> lst =
		// licenceOrgService.queryLicenceOrg(paramMap);
		//
		// if (lst != null && lst.size() > 0) {
		// response.setCharacterEncoding("UTF-8");
		// response.setContentType("text/html");
		// PrintWriter writer = response.getWriter();
		// writer.write("合同号已经存在!");
		// return;
		// }
		//
		// String school_name = request.getParameter("school_name");
		// String school_address = request.getParameter("school_address");
		// String contacts_name = request.getParameter("contacts_name");
		// String contacts_sex = request.getParameter("contacts_sex");
		// String contacts_cellphone =
		// request.getParameter("contacts_cellphone");
		// String contacts_phone = request.getParameter("contacts_phone");
		// String contacts_qq = request.getParameter("contacts_qq");
		// String contacts_email = request.getParameter("contacts_email");
		// String contacts_address = request.getParameter("contacts_address");
		// String managers_name = request.getParameter("managers_name");
		// String managers_cellphone =
		// request.getParameter("managers_cellphone");
		// String managers_email = request.getParameter("managers_email");
		//
		// Date create_time = new Date();
		//
		// Ebp_licence_org licenceOrg = new Ebp_licence_org();
		// licenceOrg.setId(GUIDGenerator.getUUID32());
		// licenceOrg.setContacts_address(contacts_address);
		// licenceOrg.setContacts_cellphone(contacts_cellphone);
		// licenceOrg.setContacts_email(contacts_email);
		// licenceOrg.setContacts_name(contacts_name);
		// licenceOrg.setContacts_phone(contacts_phone);
		// licenceOrg.setContacts_qq(contacts_qq);
		// licenceOrg.setContacts_sex(contacts_sex);
		// licenceOrg.setContract_number(contract_number);
		// licenceOrg.setCreate_time(create_time);
		// licenceOrg.setManagers_cellphone(managers_cellphone);
		// licenceOrg.setManagers_email(managers_email);
		// licenceOrg.setManagers_name(managers_name);
		// licenceOrg.setSchool_address(school_address);
		// licenceOrg.setSchool_name(school_name);
		// licenceOrg.setStatus("0");
		// licenceOrg.setUpdate_time(create_time);
		//
		// licenceOrgService.saveLicenceOrg(licenceOrg);
		// response.setCharacterEncoding("UTF-8");
		// response.setContentType("text/html");
		// PrintWriter writer = response.getWriter();
		// writer.write("success");
	}

	/**
	 * 修改授权机构信息
	 */
	@RequestMapping("/update")
	public void update(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		// if (logger.isDebugEnabled()) {
		// logger.debug("修改授权机构信息");
		// }
		// String id = request.getParameter("id");
		// String school_name = request.getParameter("school_name");
		// String school_address = request.getParameter("school_address");
		// String contacts_name = request.getParameter("contacts_name");
		// String contacts_sex = request.getParameter("contacts_sex");
		// String contacts_cellphone =
		// request.getParameter("contacts_cellphone");
		// String contacts_phone = request.getParameter("contacts_phone");
		// String contacts_qq = request.getParameter("contacts_qq");
		// String contacts_email = request.getParameter("contacts_email");
		// String contacts_address = request.getParameter("contacts_address");
		// String managers_name = request.getParameter("managers_name");
		// String managers_cellphone =
		// request.getParameter("managers_cellphone");
		// String managers_email = request.getParameter("managers_email");
		// String contract_number = request.getParameter("contract_number");
		// Ebp_licence_org licenceOrg = new Ebp_licence_org();
		// licenceOrg.setId(id);
		// licenceOrg.setContacts_address(contacts_address);
		// licenceOrg.setContacts_cellphone(contacts_cellphone);
		// licenceOrg.setContacts_email(contacts_email);
		// licenceOrg.setContacts_name(contacts_name);
		// licenceOrg.setContacts_phone(contacts_phone);
		// licenceOrg.setContacts_qq(contacts_qq);
		// licenceOrg.setContacts_sex(contacts_sex);
		// licenceOrg.setContract_number(contract_number);
		// licenceOrg.setManagers_cellphone(managers_cellphone);
		// licenceOrg.setManagers_email(managers_email);
		// licenceOrg.setManagers_name(managers_name);
		// licenceOrg.setSchool_address(school_address);
		// licenceOrg.setSchool_name(school_name);
		// licenceOrg.setUpdate_time(new Date());
		//
		// licenceOrgService.updateLicenceOrg(licenceOrg);
		// response.setCharacterEncoding("UTF-8");
		// response.setContentType("text/html");
		// PrintWriter writer = response.getWriter();
		// writer.write("success");
	}
}
