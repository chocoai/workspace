package com.yhcrt.weihu.bbs.action;

import static com.yhcrt.weihu.common.page.SimplePage.cpn;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.yhcrt.weihu.bbs.action.BbsForumAct;
import com.yhcrt.weihu.bbs.entity.BbsCategory;
import com.yhcrt.weihu.bbs.entity.BbsForum;
import com.yhcrt.weihu.bbs.entity.BbsTopic;
import com.yhcrt.weihu.bbs.entity.BbsUserGroup;
import com.yhcrt.weihu.bbs.manager.BbsCategoryMng;
import com.yhcrt.weihu.bbs.manager.BbsForumMng;
import com.yhcrt.weihu.bbs.manager.BbsTopicMng;
import com.yhcrt.weihu.bbs.manager.BbsUserGroupMng;
import com.yhcrt.weihu.bbs.web.CmsUtils;
import com.yhcrt.weihu.common.page.Pagination;
import com.yhcrt.weihu.common.util.GeneratorStr;
import com.yhcrt.weihu.common.web.CookieUtils;
import com.yhcrt.weihu.core.entity.CmsSite;

import net.sf.json.JSONObject;

@Controller
public class BbsForumAct {
	private static final Logger log = LoggerFactory
			.getLogger(BbsForumAct.class);

	@RequestMapping("/forum/v_list.do")
	public String list(Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		Pagination pagination = manager.getPage(cpn(pageNo), CookieUtils
				.getPageSize(request));
		model.addAttribute("pagination", pagination);
		model.addAttribute("pageNo", pagination.getPageNo());
		return "forum/list";
	}

	@RequestMapping("/forum/v_add.do")
	public String add(HttpServletRequest request, ModelMap model) {
		List<BbsCategory> categoryList = bbsCategoryMng.getList(CmsUtils
				.getSiteId(request));
		List<BbsUserGroup> groupList = bbsUserGroupMng.getList(CmsUtils
				.getSiteId(request));
		model.put("categoryList", categoryList);
		model.put("groupList", groupList);
		return "forum/add";
	}

	@RequestMapping("/forum/v_edit.do")
	public String edit(Integer id, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		List<BbsCategory> categoryList = bbsCategoryMng.getList(CmsUtils
				.getSiteId(request));
		List<BbsUserGroup> groupList = bbsUserGroupMng.getList(CmsUtils
				.getSiteId(request));
		model.put("categoryList", categoryList);
		model.put("groupList", groupList);
		model.addAttribute("bbsForum", manager.findById(id));
		model.addAttribute("pageNo", pageNo);
		return "forum/edit";
	}

	@RequestMapping("/forum/o_save.do")
	public String save(BbsForum bean, Integer categoryId, Integer[] views,
			Integer[] topics, Integer[] replies, HttpServletRequest request,
			ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		bean = manager.save(bean, categoryId, site, views, topics, replies);
		log.info("save BbsForum id={}", bean.getId());
		return "redirect:v_list.do";
	}

	@RequestMapping("/forum/o_update.do")
	public String update(BbsForum bean, Integer categoryId, Integer[] views,
			Integer[] topics, Integer[] replies, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		bean = manager.update(bean, categoryId, site, views, topics, replies);
		log.info("update BbsForum id={}.", bean.getId());
		return list(pageNo, request, model);
	}

	@RequestMapping("/forum/o_delete.do")
	public String delete(Integer[] ids, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		for (int i = 0; i < ids.length; i++) {
			List<BbsTopic> list = bbsTopicMng.getList(ids[i],null,null,0,Integer.MAX_VALUE);
			for (int j = 0; j < list.size(); j++) {
				BbsTopic topic = bbsTopicMng.deleteById(list.get(j).getId());
				log.info("delete BbsTopic id={}", topic.getId());
			}
		}
		BbsForum[] beans = manager.deleteByIds(ids);
		for (BbsForum bean : beans) {
			log.info("delete BbsForum id={}", bean.getId());
		}
		
		return list(pageNo, request, model);
	}

	@RequestMapping("/forum/o_priority.do")
	public String priorityUpdate(Integer[] ids, Integer[] prioritys,
			Integer pageNo, HttpServletRequest request, ModelMap model) {
		if (ids == null || ids.length <= 0) {
			return "redirect:v_list.do";
		}
		CmsSite site = CmsUtils.getSite(request);
		BbsForum t;
		Integer id;
		Integer priority;
		for (int i = 0; i < ids.length; i++) {
			id = ids[i];
			priority = prioritys[i];
			if (id != null && priority != null) {
				t = manager.findById(id);
				if (t != null && t.getSite().getId().equals(site.getId())) {
					t.setPriority(priority);
					manager.update(t);
				}
			}
		}
		return list(pageNo, request, model);
	}
	
	@RequestMapping("/forum/o_Upload.do")
	@ResponseBody
    public String uploadImg(
    		String fileName,
    		String path,
    		HttpServletRequest request,
    		ModelMap model) {
		JSONObject json = new JSONObject();
		String s = request.getSession().getServletContext().getRealPath("/");
		String[] arr = s.split("\\\\");
		StringBuffer savePath = new StringBuffer();
		for(int i=0; i<arr.length-1; i++){
			savePath.append(arr[i]+"\\");
		}
		savePath.append("upload/"+path+"/");
		String saveUrl = "upload/"+path+"/";
		//创建文件夹
		File saveDirFile = new File(savePath.toString());
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath.append(ymd + "/");
		saveUrl += ymd+"/";
		File dirFile = new File(savePath.toString());
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		byte[] fis = null;
		FileOutputStream fos = null;
		try {
			MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;
			MultipartFile imgFile  =  multipartRequest.getFile("bliFile");
			String imgName = imgFile.getOriginalFilename();
			if(imgName.lastIndexOf(".")!=-1){
				String suffix = imgName.substring(imgName.lastIndexOf(".")+1);
	            String ip = request.getLocalAddr();
	            String port = request.getLocalPort()+"";
				if(suffix.equals("jpg") || suffix.equals("png") || suffix.equals("jpeg") 
						|| suffix.equals("gif") || suffix.equals("bmp")){
					String newName = new Date().getTime()+GeneratorStr.getString(4)+"."+suffix;
					File destFile = new File(savePath.toString(), newName);
					fis = imgFile.getBytes();
					fos = new FileOutputStream(destFile);
		            for (int i=0; i<fis.length; i++) {
		                fos.write(fis[i]);
		            }
					json.put("status", "1");
					json.put("imgUrl", "http://"+ip+":"+port+"/"+saveUrl+"/"+newName);
				}else{
					json.put("status", "0");
				}
			}else{
				json.put("status", "0");
			}
		} catch (Exception e) {
		    e.printStackTrace();
		    json.put("status", "0");
		} finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		return json.toString();
    }

	@Autowired
	private BbsForumMng manager;
	@Autowired
	private BbsCategoryMng bbsCategoryMng;
	@Autowired
	private BbsUserGroupMng bbsUserGroupMng;
	@Autowired
	private BbsTopicMng bbsTopicMng;
}