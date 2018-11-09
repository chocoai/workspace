package com.yhcrt.weihu.bbs.action.member;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yhcrt.weihu.bbs.entity.BbsAlbum;
import com.yhcrt.weihu.bbs.entity.BbsAlbumImg;
import com.yhcrt.weihu.bbs.entity.BbsUser;
import com.yhcrt.weihu.bbs.manager.BbsAlbumImgMng;
import com.yhcrt.weihu.bbs.manager.BbsAlbumMng;
import com.yhcrt.weihu.bbs.manager.BbsPointDetailMng;
import com.yhcrt.weihu.bbs.web.CmsUtils;
import com.yhcrt.weihu.bbs.web.FrontUtils;
import com.yhcrt.weihu.common.page.Page;
import com.yhcrt.weihu.common.upload.FileRepository;
import com.yhcrt.weihu.core.entity.CmsSite;

import net.sf.json.JSONObject;
/**
 * 个人中心我的相册功能
 * /member/* 的请求如果通过了，那么user是必定存在的，所以不需要再进行判断
 * @author 
 *
 */
@Controller
public class BbsAlbumAct {
	
	@RequestMapping("/member/set_album_img.jspx")
	@ResponseBody
	public void setAlbumImg(Integer albumImgId,
			HttpServletRequest request,HttpServletResponse response, ModelMap model) throws IOException{
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		BbsUser user = CmsUtils.getUser(request);
		if(user == null){
			json.put("status", "-1");
			json.put("msg", "用户未登录");
			response.getWriter().write(json.toString());
			return ;
		}
		if(albumImgId == null){
			json.put("status", "-1");
			json.put("msg", "参数错误");
			response.getWriter().write(json.toString());
			return ;
		}
		BbsAlbumImg albumImg = bbsAlbumImgMng.findById(albumImgId);
		if(albumImg == null){
			json.put("status", "-1");
			json.put("msg", "参数错误");
			response.getWriter().write(json.toString());
			return ;
		}
		bbsAlbumMng.setAlbumImg(albumImg.getImgUrl(), albumImg.getAlbum().getId());
		json.put("status", "1");
		json.put("msg", "设置成功");
		response.getWriter().write(json.toString());
	}
	
	@RequestMapping("/member/album_img_upload{albumId}.jspx")
	public String toUploadImg(@PathVariable Integer albumId,
			HttpServletRequest request, ModelMap model){
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		BbsUser user = CmsUtils.getUser(request);
		if(albumId == null || albumId == 0 || !bbsAlbumMng.isMyAlbum(user.getId(), albumId)){
			//此时没有传albumId  重定向到个人相册的首页
			return "redirect:/member/album.jspx";
		}
		BbsAlbum album = bbsAlbumMng.findById(albumId);
		model.put("album", album);
		model.put("isSign", bbsPointDetailMng.isSign(user.getId()));
		return "/WEB-INF/t/cms/www/blue/member/album_img_upload.html";
	}
	
	@RequestMapping("/member/sub_album_img.jspx")
	@ResponseBody
	public void subAlbumImg(Integer id,
			HttpServletRequest request,HttpServletResponse response, ModelMap model) throws IOException{
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		BbsUser user = CmsUtils.getUser(request);
		if(user == null){
			json.put("status", "-1");
			response.getWriter().write(json.toString());
			return ;
		}
		bbsAlbumImgMng.deleteById(id);
		json.put("status", "1");
		response.getWriter().write(json.toString());
	}
	
	@RequestMapping("/member/person_upload_img{albumId}.jspx")
	@ResponseBody
	public void uploadImg(@PathVariable Integer albumId,
			@RequestParam(value = "file", required = false)MultipartFile[] file,
			HttpServletRequest request,HttpServletResponse response, ModelMap model){
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		if (user == null) {
			return ;
		}
		for(int j=0; j<file.length; j++){
			String origName = file[j].getOriginalFilename();
			String ext = FilenameUtils.getExtension(origName).toLowerCase(
					Locale.ENGLISH);
			try {
				String fileUrl;
					String s = request.getSession().getServletContext().getRealPath("/");
					String[] arr = s.split("\\\\");
					StringBuffer savePath = new StringBuffer();
					for(int i=0; i<arr.length-1; i++){
						savePath.append(arr[i]+"\\");
					}
					savePath.append("upload/userImg/");
					String saveUrl = "upload/userImg/";
					//创建文件夹
					//savePath += dirName + "/";
					File saveDirFile = new File(savePath.toString());
					if (!saveDirFile.exists()) {
						saveDirFile.mkdirs();
					}
					String ip = request.getLocalAddr();
		            String port = request.getLocalPort()+"";
					// 得到上传文件的保存目录，将上传的文件存放于tomcat的webapps目录下
					fileUrl = fileRepository.storeByExt(savePath.toString(), ext, file[j]);
					// 加上部署路径      2016-11-11 去除部署路径
					//fileUrl = ctx + fileUrl;
					fileUrl = "http://"+ip+":"+port+"/"+saveUrl+fileUrl.substring(savePath.toString().length());
					//上传完成，开始保存数据到数据库
					if(origName.length()>32){
						origName = origName.substring(0, 26)+ext;
					}
					//保存到数据库，同时对相册中的图片数量进行增加
					bbsAlbumImgMng.save(origName, null, user, fileUrl, albumId);
					bbsAlbumMng.updateCount(albumId);
			} catch (IllegalStateException e) {
				e.printStackTrace();
				//提示
			} catch (IOException e) {
				e.printStackTrace();
				//提示
			} catch (Exception e) {
				e.printStackTrace();
				//提示
			}
		}
		//提示
	}
	
	@RequestMapping("/member/album_img{albumId}.jspx")
	public String albumImg(@PathVariable Integer albumId,Integer pageSize,Integer pageNo,
			HttpServletRequest request, ModelMap model){
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		BbsUser user = CmsUtils.getUser(request);
		if(albumId == null || albumId == 0 || !bbsAlbumMng.isMyAlbum(user.getId(), albumId)){
			//此时没有传albumId  重定向到个人相册的首页
			return "redirect:/member/album.jspx";
		}
		Page<BbsAlbumImg> pageBean = bbsAlbumImgMng.getImgByAlbumId(pageSize, pageNo, albumId);
		BbsAlbum album = bbsAlbumMng.findById(albumId);
		model.put("pageBean", pageBean);
		model.put("album", album);
		model.put("isSign", bbsPointDetailMng.isSign(user.getId()));
		return "/WEB-INF/t/cms/www/blue/member/personal_album_img.html";
	}
	
	@RequestMapping("/member/sub_album.jspx")
	@ResponseBody
	public void subAlbum(Integer id,
			HttpServletRequest request,HttpServletResponse response, ModelMap model) throws IOException{
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		BbsUser user = CmsUtils.getUser(request);
		if(user == null){
			json.put("status", "-1");
			response.getWriter().write(json.toString());
			return ;
		}
		bbsAlbumMng.deleteById(id);
		json.put("status", "1");
		response.getWriter().write(json.toString());
		return ;
	}
	
	@RequestMapping("/member/add_album.jspx")
	@ResponseBody
	public void addAlbum(String albumName,String albumDescription,Integer visitAuth,
			HttpServletRequest request,HttpServletResponse response, ModelMap model) throws IOException{
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		BbsUser user = CmsUtils.getUser(request);
		if(StringUtils.isBlank(albumName) || user == null || visitAuth == null){
			json.put("status", "-1");
			response.getWriter().write(json.toString());
			return ;
		}
		bbsAlbumMng.save(albumName, albumDescription, visitAuth, user);
		json.put("status", "1");
		response.getWriter().write(json.toString());
	}
	
	@RequestMapping("/member/album.jspx")
	public String album(Integer pageSize,Integer pageNo,
			HttpServletRequest request, ModelMap model){
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		BbsUser user = CmsUtils.getUser(request);
		bbsAlbumMng.setDefultImg(user.getId());
		Page<BbsAlbum> pageBean = bbsAlbumMng.getAlbumByUserId(pageSize, pageNo, user.getId());
		model.put("pageBean", pageBean);
		model.put("isSign", bbsPointDetailMng.isSign(user.getId()));
		return "/WEB-INF/t/cms/www/blue/member/personal_album.html";
	}

	@Autowired
	private BbsAlbumMng bbsAlbumMng;
	@Autowired
	private BbsAlbumImgMng bbsAlbumImgMng;
	@Autowired
	private BbsPointDetailMng bbsPointDetailMng;
	
	private FileRepository fileRepository;

	@Autowired
	public void setFileRepository(FileRepository fileRepository) {
		this.fileRepository = fileRepository;
	}
	
}
