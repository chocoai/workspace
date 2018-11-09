package com.whty.ebp.manage.controller;

import java.io.File;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whty.common.util.CommonFunction;
import com.whty.common.util.Plupload;
import com.whty.common.util.PluploadUtil;
import com.whty.common.util.SysConfig;
import com.whty.ebp.base.controller.BaseController;
import com.whty.ebp.manage.dao.DerivativeAppLineDao;
import com.whty.ebp.manage.model.ApkInfo;
import com.whty.ebp.manage.model.DerivativeApp;
import com.whty.ebp.manage.model.DerivativeAppLine;
import com.whty.ebp.manage.model.EbpApp;
import com.whty.ebp.manage.service.DerivativeAppService;
import com.whty.ebp.manage.utils.ApkUtils;
import com.whty.page.PageContext;
import com.whty.page.request.PageRequest;
import com.whty.page.util.HandlerResult;

@Controller
@RequestMapping(value="/manage/derivativeApp")
public class DerivativeAppController extends BaseController{

	@Autowired
	private DerivativeAppService derivativeAppService; 
	
	@Autowired
	private DerivativeAppLineDao derivativeAppLineDao;
	
	

	
	/**
	 * 上传 后添加的方法
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/upload")
	public void uploadInfo(Plupload plupload, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		plupload.setRequest(request);

		String uploadDir = SysConfig.getStrValue("file_path_pre") + SysConfig.getStrValue("app_upload_dir")
				+ CommonFunction.getDateSampleString(new Date()) + File.separator;
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
				System.out.println(plupload.getName() + "-----------上传完毕");
			}

		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		EbpApp ebpApp = new EbpApp();
		ebpApp.setOld_file_name(plupload.getName());
		ebpApp.setNew_file_name(plupload.getRename());
		ebpApp.setFile_path(uploadDir + plupload.getRename());

		ApkInfo apkInfo = new ApkUtils().getApkInfo(ebpApp.getFile_path());

		ebpApp.setInter_version_code(apkInfo.getVersionCode());
		ebpApp.setVersion_code(apkInfo.getVersionName());
		ebpApp.setApk_package_name(apkInfo.getPackageName());

		printJson(response, ebpApp);
	}
	
	/**
	 * 更新版本
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public void update(@ModelAttribute("ebpApp") DerivativeApp derivativeApp, Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		derivativeApp.setUpdateTime(new Date());
		derivativeAppService.update(derivativeApp);

		printText(response, "success");
	}
	
	/**
	 * 查看详情
	 */
	@RequestMapping(value = "/detail")
	@ResponseBody
	public void detail(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		if (StringUtils.isNotEmpty(id)) {
			printJson(response, derivativeAppService.queryById(id));
		} else {
			printText(response, "error");
		}
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response, Model model) {
		String id = request.getParameter("id");

		derivativeAppService.delete(id);
		
//		if (StringUtils.isNotEmpty(id)) {
//			EbpApp ebpApp = new EbpApp();
//			ebpApp.setId(id);
//			ebpApp.setStatus("1");
//			derivativeAppService.(ebpApp);
//		}

		printText(response, "success");
	}
	
	/**
	 * 创建版本
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public void save(@ModelAttribute("ebpApp") DerivativeApp derivativeApp, Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		derivativeAppService.save(derivativeApp);
		printText(response, "success");
	}
	
	/**
	 * 开放升级 update=2---根据参数来升级 否则---全部用户升级
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/openUpdate")
	@ResponseBody
	public void openUpdate(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map map = new HashMap();
		String canUpdate = request.getParameter("canUpdate");
		String id = request.getParameter("id");
		if (StringUtils.isNotEmpty(id) && StringUtils.isNotEmpty(canUpdate) ) {
			map.put("can_update", canUpdate);
			map.put("id", id);
			derivativeAppService.openUpdate(map);
			printText(response, "success");
		} else {
			printText(response, "error");
		}

	}
	
	/**
	 * 查询应用列表
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/queryProduct")
	@ResponseBody
	public void queryProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> paraMap = this.getParameterMap(request);

		PageContext page = PageContext.getContext();
		page.setCurrentPage(Integer.parseInt(paraMap.get("currPage").toString()));
		page.setPageSize(Integer.parseInt(paraMap.get("pageSize").toString()));
		page.setPagination(true);


		HandlerResult pageList = new HandlerResult();
		try {
			pageList = derivativeAppService.listDerivativeAppByPage(paraMap, page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		page.setPagination(false);

		Map resultMap = new HashMap();
		resultMap.put("list", pageList.getResultList());
		resultMap.put("page", pageList.getPage());
		printJson(response, resultMap);
	}
	
	@RequestMapping(value = "/listApp")
	public String listProduct(HttpServletRequest request, PageRequest pageRequest, Model model, String productName,
			String productType) {
		
		List<DerivativeAppLine> list = derivativeAppLineDao.listByCondition(new HashMap<String,Object>());
		
		model.addAttribute("derivativeAppLineList", list);
		
		return "derivativeApp/listApp";
	}
	
	
}
