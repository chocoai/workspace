/**
 * 
 */
package com.whty.assis.manage.controller;

import java.io.File;
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

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.whty.assis.base.controller.BaseController;
import com.whty.assis.demo.dao.IdDao;
import com.whty.assis.demo.model.Soft;
import com.whty.assis.manage.model.SoftBackDoor;
import com.whty.assis.manage.service.SoftBackDoorService;
import com.whty.assis.manage.service.impl.SoftBackDoorServiceImpl;
import com.whty.common.util.MD5Filter;
import com.whty.common.util.SysConfig;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * 软件后门包管理
 * 
 * @author zhangzheng
 * @date 2018年9月14日
 */
@Controller
@RequestMapping("/manage/softBackDoor")
public class SoftBackDoorController extends BaseController {

	@Autowired
	private SoftBackDoorService softBackDoorService;

	@Autowired
	private IdDao idDao;

	@RequestMapping(value = "/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String id = request.getParameter("id");

		softBackDoorService.delete(Integer.valueOf(id));

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write("success");
	}

	@RequestMapping(value = "/updateCrb")
	public void updateCrb(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String id = request.getParameter("id");
		String status = request.getParameter("cbt");

		SoftBackDoor softBackDoor = softBackDoorService.loadById(Integer.valueOf(id));

		softBackDoor.setCbt(status);
		softBackDoor.setCreateTime(new Date());
		softBackDoorService.update(softBackDoor);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write("success");
	}

	@RequestMapping(value = "/updateStatus")
	public void updateStatus(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String id = request.getParameter("id");

		String status = request.getParameter("status");

		SoftBackDoor softBackDoor = softBackDoorService.loadById(Integer.valueOf(id));

		softBackDoor.setStatus(status);

		softBackDoorService.update(softBackDoor);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write("success");
	}

	@RequestMapping(value = "/add")
	public void add(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("新增版本信息");
		}
		String fileUrl = request.getParameter("fileUrl");
		String fileName = request.getParameter("fileName");
		String fileRename = request.getParameter("fileRename");

		String versionCode = request.getParameter("versionCode");
		String softType = request.getParameter("softType");
		String fileType = request.getParameter("fileType");

		String zipCodeVersion = request.getParameter("zipCodeVersion");

		Map<String, Object> idParam = new HashMap<String, Object>(2);
		idParam.put("databaseName", SysConfig.getStrValue("databaseName"));
		idParam.put("tableName", "ta_soft_back_door");
		Integer id = idDao.getId(idParam);

		final SoftBackDoor softBackDoor = new SoftBackDoor();
		softBackDoor.setSoftType(Integer.valueOf(softType));
		softBackDoor.setVersionCode(versionCode);
		softBackDoor.setZipCodeVersion(zipCodeVersion);
		softBackDoor.setCreateTime(new Date());
		softBackDoor.setUpdateTime(new Date());
		softBackDoor.setFilePath(fileUrl);

		softBackDoor.setMd5(MD5Filter.getMd5ByPath(fileUrl));
		softBackDoor.setCbt("0");
		// softBackDoor.setCbt(false);

		softBackDoorService.save(softBackDoor);

		softBackDoor.setId(id);

		final File f = new File(fileUrl);
		// 上传百度云
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
				String bosAddress = baiduBosUrl + bosStringBuffer + f.getName();// 文件地址
				System.out.println(bosAddress);

				softBackDoor.setBosStatus("1");
				softBackDoor.setDownUrl(bosAddress);

				softBackDoorService.updateBos(softBackDoor);
			}
		}).start();

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write("success");
	}

	@RequestMapping(value = "/list")
	public String save(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

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

		HandlerResult handlerResult = softBackDoorService.querySoftBackDoor(paramMap);
		model.addAttribute("list", handlerResult.getResultList());
		model.addAttribute("page", page);
		model.addAllAttributes(paramMap);
		page.setPagination(false);

		return "softBackDoor/list";
	}

}
