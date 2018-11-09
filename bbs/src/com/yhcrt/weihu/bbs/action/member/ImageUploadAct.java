package com.yhcrt.weihu.bbs.action.member;

import static com.yhcrt.weihu.bbs.Constants.TPLDIR_MEMBER;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.yhcrt.weihu.bbs.action.member.ImageUploadAct;
import com.yhcrt.weihu.bbs.entity.BbsUser;
import com.yhcrt.weihu.bbs.web.CmsUtils;
import com.yhcrt.weihu.bbs.web.FrontUtils;
import com.yhcrt.weihu.common.image.ImageUtils;
import com.yhcrt.weihu.common.upload.FileRepository;
import com.yhcrt.weihu.core.entity.CmsSite;
import com.yhcrt.weihu.core.web.WebErrors;

@Controller
public class ImageUploadAct {
	private static final Logger log = LoggerFactory
			.getLogger(ImageUploadAct.class);
	
	/**
	 * 结果页
	 */
	private static final String RESULT_PAGE = "tpl.iframe_upload";
	/**
	 * 错误信息参数
	 */
	public static final String ERROR = "error";

	@RequestMapping("/member/o_upload_image.jspx")
	public String execute(
			String filename,
			Integer uploadNum,
			Boolean mark,
			@RequestParam(value = "uploadFile", required = false) MultipartFile file,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validate(filename, file, request);
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		if (user == null) {
			return FrontUtils.showLogin(request, model, site);
		}
		if (errors.hasErrors()) {
			model.addAttribute(ERROR, errors.getErrors().get(0));
			return FrontUtils.getTplPath(request, site,
					TPLDIR_MEMBER, RESULT_PAGE);
		}
		String origName = file.getOriginalFilename();
		String ext = FilenameUtils.getExtension(origName).toLowerCase(
				Locale.ENGLISH);
		try {
			String fileUrl;
				String ctx = request.getContextPath();
				if (!StringUtils.isBlank(filename)) {
					filename = filename.substring(ctx.length());
						fileUrl = fileRepository
								.storeByFilename(filename, file);
				} else {
					String s = request.getSession().getServletContext().getRealPath("/");
					String[] arr = s.split("\\\\");
					StringBuffer savePath = new StringBuffer();
					for(int i=0; i<arr.length-1; i++){
						savePath.append(arr[i]+"\\");
					}
					savePath.append("upload/userImg/");
					String saveUrl = "upload/userImg";
					//创建文件夹
					//savePath += dirName + "/";
					File saveDirFile = new File(savePath.toString());
					if (!saveDirFile.exists()) {
						saveDirFile.mkdirs();
					}
					String ip = request.getLocalAddr();
		            String port = request.getLocalPort()+"";
					// 得到上传文件的保存目录，将上传的文件存放于tomcat的webapps目录下
					fileUrl = fileRepository.storeByExt(savePath.toString(), ext, file);
					// 加上部署路径      2016-11-11 去除部署路径
					//fileUrl = ctx + fileUrl;
					fileUrl = "http://"+ip+":"+port+"/"+saveUrl+fileUrl.substring(savePath.toString().length());
				}
			model.addAttribute("uploadPath", fileUrl);
			model.addAttribute("uploadNum", uploadNum);
		} catch (IllegalStateException e) {
			model.addAttribute(ERROR, e.getMessage());
			log.error("upload file error!", e);
		} catch (IOException e) {
			model.addAttribute(ERROR, e.getMessage());
			log.error("upload file error!", e);
		} catch (Exception e) {
			model.addAttribute(ERROR, e.getMessage());
			log.error("upload file error!", e);
		}
		return FrontUtils.getTplPath(request, site,
				TPLDIR_MEMBER, RESULT_PAGE);
	}

	private WebErrors validate(String filename, MultipartFile file,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (file == null) {
			errors.addErrorCode("imageupload.error.noFileToUpload");
			return errors;
		}
		if (StringUtils.isBlank(filename)) {
			filename = file.getOriginalFilename();
		}
		if(filename!=null&&(filename.contains("/")||filename.contains("\\")||filename.indexOf("\0")!=-1)){
			errors.addErrorCode("upload.error.filename", filename);
		}
		String ext = FilenameUtils.getExtension(filename);
		if (!ImageUtils.isValidImageExt(ext)) {
			errors.addErrorCode("imageupload.error.notSupportExt", ext);
			return errors;
		}
		try {
			if (!ImageUtils.isImage(file.getInputStream())) {
				errors.addErrorCode("imageupload.error.notImage", ext);
				return errors;
			}
		} catch (IOException e) {
			log.error("image upload error", e);
			errors.addErrorCode("imageupload.error.ioError", ext);
			return errors;
		}
		return errors;
	}

	
	private FileRepository fileRepository;

	@Autowired
	public void setFileRepository(FileRepository fileRepository) {
		this.fileRepository = fileRepository;
	}


}