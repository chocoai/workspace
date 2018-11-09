package com.yhcrt.healthcloud.system.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yhcrt.healthcloud.base.controller.BaseController;
import com.yhcrt.healthcloud.system.entity.SysVersion;
import com.yhcrt.healthcloud.system.service.SysVersionService;
import com.yhcrt.healthcloud.util.DateUtil;
import com.yhcrt.healthcloud.util.FileUtil;
import com.yhcrt.healthcloud.util.StringUtil;
import com.yhcrt.healthcloud.util.UUIDGenerator;

/**
 * 版本模块
 * 
 * @author hull
 *
 */
@RequestMapping("/version")
@Controller
public class SysVersionController extends BaseController {

	@Autowired
	private SysVersionService sysVersionService;

	/**
	 * 根据条件查询版本
	 * 
	 * @param request
	 * @param response
	 * @param cAppCode
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getVersionList(HttpServletRequest request, HttpServletResponse response, String cAppCode) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("cAppCode", cAppCode);
		List<SysVersion> versionList = sysVersionService.queryAll(params);
		request.setAttribute("versionList", versionList);
		request.setAttribute("cAppCode", StringUtils.isBlank(cAppCode) ? "" : cAppCode);
		return "version/list";

	}

	/**
	 * 跳转到版本新增或修改界面，如果kid为null，则是新增操作，否则返回到编辑页面
	 * 
	 * @param request
	 * @param response
	 * @param goodsId
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editVersion(HttpServletRequest request, HttpServletResponse response, String kid) {
		// 判断进入界面
		if (StringUtil.isEmpty(kid)) {
			kid = UUIDGenerator.getId();
			request.setAttribute("kid", kid);
			request.setAttribute("version", new SysVersion());
		} else {
			SysVersion version = sysVersionService.queryById(kid);
			request.setAttribute("version", version);
		}
		return "version/edit";
	}

	/**
	 * @Title: saveGoods
	 * @Description: 根据editType参数来决定请求是新增还是修改
	 * @param request
	 * @param response
	 * @param attr
	 * @param goods
	 * @param titleImgFile
	 * @param editType
	 * @return
	 */
	@RequestMapping("/save")
	public String saveGoods(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attr,
			SysVersion version, @RequestParam("apkFile") MultipartFile apkFile, String editType) {
		if (apkFile != null && !apkFile.isEmpty()) {
			String fileOSPath = new File(request.getSession().getServletContext().getRealPath("/")).getParent();
			String fileAbsolutePath = "/upload/" + version.getcAppCode() + "/" + version.getiVersionCode()+"/"
					+ apkFile.getOriginalFilename();
			String fileUrl = "/upload/" + version.getcAppCode() + "/" + version.getiVersionCode();
			String pathPrefix = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
			version.setcDownloadUrl(pathPrefix+fileAbsolutePath);
			File dest = new File(fileOSPath + fileUrl);	//删除文件夹内apk
			File file = new File(fileOSPath + fileAbsolutePath);	//新增文件获取文件夹路径
			if (file.getParentFile().exists()) {
				FileUtil.deleteAll(dest);
			}
			try {
				file.getParentFile().mkdirs();
				apkFile.transferTo(file);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}else{
			if("new".equals(editType)){
				attr.addFlashAttribute("state", "NULLFILE");
				return "redirect:list";
			}
		}
		version.setcUploadDate(DateUtil.getDateTime());
		version.setcUploadUser(getLoginUser().getUserId().toString());
		Integer i = 0;
		if ("edit".equals(editType)) {
			i = sysVersionService.update(version);
		} else {
			i = sysVersionService.insert(version);
		}
		if (i == 1) {
			attr.addFlashAttribute("state", "SUCCESS");
		} else if(i == -1){
			attr.addFlashAttribute("state", "SAME");
		}else{
			attr.addFlashAttribute("state", "FALSE");
		}
		return "redirect:list";
	}
	
	/**
	 * 删除版本信息
	 * @param request
	 * @param response
	 * @param attr
	 * @param kids
	 */
	@RequestMapping(value = "/batchDelete", method = RequestMethod.POST)
    @ResponseBody
    public void batchDeleteService(HttpServletRequest request, HttpServletResponse response, 
            RedirectAttributes attr, String[] kids){
		List<String> list=new ArrayList<String>();
        for(String kid:kids){
        	String path = sysVersionService.queryUrl(kid);
        	String fileOSPath = new File(request.getSession().getServletContext().getRealPath("/")).getParent();
			if(!StringUtil.isEmpty(path)){
				path = StringUtil.extractString(path);
				FileUtil.remove(fileOSPath+"/"+path);
			}
			list.add(kid);
        }
        sysVersionService.batchDel(list);
    }

}
