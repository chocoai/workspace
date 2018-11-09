package com.whty.wfd.page.controller;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.bos.model.PutObjectResponse;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.whty.wfd.base.controller.BaseController;
import com.whty.wfd.common.utils.GUIDGenerator;
import com.whty.wfd.page.dao.TPlateMapper;
import com.whty.wfd.page.model.*;
import com.whty.wfd.page.service.CollectionService;
import com.whty.wfd.page.service.PlateService;
import com.whty.wfd.page.service.PostService;
import com.whty.wfd.page.service.PostViewService;
import com.whty.wfd.page.util.BaiduYun;
import com.whty.wfd.page.util.FileMDFive;
import com.whty.wfd.page.vo.Like;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * \* User: zjd \* Date: 2018/8/16 \* Time: 9:42 \* Description: \
 */
@Controller
public class PostController extends BaseController {
	@Autowired
	private PlateService plateService;
	@Autowired
	private PostService postService;
	@Autowired
	private PostViewService postViewService;
	@Autowired
	private TPlateMapper tPlateMapper;
	@Autowired
	private CollectionService collectionService;

	final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	final SimpleDateFormat sdf1 = new SimpleDateFormat("MM月");
	final SimpleDateFormat sdf2 = new SimpleDateFormat("dd");
	final SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm:ss");

	@RequestMapping(value = "getPostDetail", method = RequestMethod.GET)
	public String getPostDetail(HttpServletRequest request,ModelMap map, String postId,
	String turn,String plateId,String tabType,String messageId,String sendEntrance
	) {
		TUser tUser = (TUser) request.getSession().getAttribute("userObj");
		if(StringUtil.isNotEmpty(turn)){
			map.put("turn", turn);
		}
		map.put("tabType", tabType);
		Boolean colType = false;
		if(collectionService.getCollectionPostByUserIDPostId(tUser.getId(),postId).size()>0){
			colType=true;
		}
		TPostViewLog tPostViewLog = new TPostViewLog();
		tPostViewLog.setCreateTime(new Date());
		tPostViewLog.setUpdateTime(new Date());
		tPostViewLog.setUserId(tUser.getId());
		tPostViewLog.setPlatePostId(postId);
		postViewService.addPostView(tPostViewLog);
		TPlatePost tPlatePost = postService.getPostByPostId(postId,tUser.getId());
		if(tPlatePost==null){
			return "post/post_del";
		}
		if(StringUtil.isNotEmpty(messageId)){
			map.put("messageId", messageId);
		}
		if(StringUtil.isNotEmpty(sendEntrance)){
			map.put("sendEntrance", sendEntrance);
		}
		map.put("tPlatePost", tPlatePost);
		map.put("plateId", plateId);
		map.put("colType", colType);
		return "post/post_detail";
	}

    @RequestMapping(value = "getUserPost.html",method = RequestMethod.GET)
    public String getUserPost(){
        return "post/post_list";
    }


    @RequestMapping(value = "sendPost.html",method = RequestMethod.GET)
    public String sendPost(HttpServletRequest request,ModelMap map,String plateId,String turn){
		TUser tUser = (TUser) request.getSession().getAttribute("userObj");
		List<TPlate> tPlates = new ArrayList<>();
		if(StringUtil.isNotEmpty(tUser.getIdentityId()) && tUser.getIdentityId().equals("3")){
			//如果是管理员查询改学校的所有版块
			tPlates = tPlateMapper.selectByIdentityIdSchool(tUser.getSchoolId());
		}else{
			Map<String, Object> userParam = new HashMap<>();
			userParam.put("userId", tUser.getId());
			tPlates = tPlateMapper.selectByUserId(userParam);
		}
		TPlatePostEdit tPlatePostEdit = postService.getTPlatePostEditByUserId(tUser.getId());
		List<String> userIds = new ArrayList<>();
		if(tPlatePostEdit!=null){
			if(StringUtil.isNotEmpty(tPlatePostEdit.getAtUserId())){
				String[] userIdss = tPlatePostEdit.getAtUserId().split(",");
				for(int i=0;i<userIdss.length;i++){
					userIds.add(userIdss[i]);
				}
			}
			map.put("userIds",userIds);
			map.put("tPlatePostEdit",tPlatePostEdit);
		}
		List<TPlatePostEditImg> tPlatePostEditImgs =  postService.getTPlatePostEditImgByUserId(tUser.getId());
		if(tPlatePostEditImgs.size()>0){
			map.put("tPlatePostEditImgs",tPlatePostEditImgs);
		}

		//首先查看用户预留的内容
        map.put("tPlates",tPlates);
        map.put("plateId",plateId);
        map.put("turn",turn);
        return "post/add_post";
    }
	

	@RequestMapping(value = "getPostList", method = RequestMethod.GET)
	@ResponseBody
	public void getPostList(HttpServletResponse response,HttpServletRequest request,
			@RequestParam(name = "pageNum", defaultValue = "0") Integer pageNum,
			@RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		TUser tUser = (TUser) request.getSession().getAttribute("userObj");
		Integer pageStart = pageNum*pageSize;
		List<TPlatePost> list = postService.getPostByUserId(tUser.getId(), pageStart, pageSize);
		Integer pageTotal =postService.getTotal(tUser.getId());
		JSONObject obj = new JSONObject();
		for (TPlatePost post : list) {
			post.setRelativeCreateTime(sdf.format(post.getCreateTime()));
			post.setMonth(sdf1.format(post.getCreateTime()));
			post.setDay(sdf2.format(post.getCreateTime()));
			post.setDataTime(sdf3.format(post.getCreateTime()));
		}
		obj.put("list", list);
		obj.put("pageNum", pageNum);
		obj.put("pageTotal", pageTotal);
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		try {
			response.getWriter().print(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "addPost", method = RequestMethod.POST)
	@ResponseBody
	public void addPost(HttpServletRequest request,HttpServletResponse response,String content, String plateId, String[] path,String turn,Integer[] userId,String[] imgHttpUrls) {
		JSONObject obj = new JSONObject();
		String postId= GUIDGenerator.getGUID();
		TUser tUser = (TUser) request.getSession().getAttribute("userObj");
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			String reg = "data:image/jpeg;base64,";
			String reg2 = "data:image/jpeg;base64";
			TPlatePost tPlatePost = new TPlatePost();
			tPlatePost.setId(postId);
			tPlatePost.setCreateTime(new Date());
			tPlatePost.setUpdateTime(new Date());
			tPlatePost.setPlateId(plateId);
			tPlatePost.setCreator(tUser.getId());
			tPlatePost.setContent(content);
			tPlatePost.setIsTop(false);
			tPlatePost.setIsDelete(false);
			List<TPlatePostImg> tPlatePostImgs = new ArrayList<>();
			if (path != null && path.length > 0) {
				for(int i=0;i<path.length;i++){
					if(reg2.equals(path[i])){
						continue;
					}
					String base64 =path[i].replace(reg,"");
					byte[] imageByte=decoder.decodeBuffer(base64);
                    String bosAddress = BaiduYun.upload(imageByte,".jpg");
					TPlatePostImg tPlatePostImg = new TPlatePostImg();
					tPlatePostImg.setPlatePostId(tPlatePost.getId());
					tPlatePostImg.setDownUrl(bosAddress);
					tPlatePostImg.setMd5("1");
					tPlatePostImg.setCreateTime(new Date());
					tPlatePostImg.setUpdateTime(new Date());
					tPlatePostImg.setCreator(tUser.getId());
					tPlatePostImgs.add(tPlatePostImg);
				}
			}
            if(imgHttpUrls != null && imgHttpUrls.length > 0){
                for(int i=0;i<imgHttpUrls.length;i++){
                    TPlatePostImg tPlatePostImg = new TPlatePostImg();
                    tPlatePostImg.setPlatePostId(tPlatePost.getId());
                    tPlatePostImg.setDownUrl(imgHttpUrls[i]);
                    tPlatePostImg.setMd5("1");
                    tPlatePostImg.setCreateTime(new Date());
                    tPlatePostImg.setUpdateTime(new Date());
                    tPlatePostImg.setCreator(tUser.getId());
                    tPlatePostImgs.add(tPlatePostImg);
                }
            }
			postService.addPost(tUser,tPlatePost, tPlatePostImgs,userId);
			obj.put("result","success");
			obj.put("postId",postId);
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("result","fali");
		}
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print(obj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//帖子置顶
	@RequestMapping(value = "updatePostByPostId", method = RequestMethod.GET)
    @ResponseBody
	public int updatePostByPostId(String postId,boolean type) {
        TPlatePost tPlatePost = new TPlatePost();
		tPlatePost.setUpdateTime(new Date());
        tPlatePost.setId(postId);
        tPlatePost.setIsTop(!type);
        return postService.updatePostByPostId(tPlatePost);
	}
	//帖子删除
    @RequestMapping(value = "deletePostByPostId", method = RequestMethod.GET)
    @ResponseBody
    public int deletePostByPostId(String postId){
        TPlatePost tPlatePost = new TPlatePost();
        tPlatePost.setId(postId);
        tPlatePost.setIsDelete(true);
        return postService.deletePostByPostId(tPlatePost);
    }

    //保存发帖内容
	@RequestMapping(value = "savePostContent", method = RequestMethod.POST)
	@ResponseBody
	public int savePostContent(HttpServletRequest request,String content,String plateId,
							   String[] imgUrls,String[] imgHttpUrls,String userIds){
		TUser tUser = (TUser) request.getSession().getAttribute("userObj");
		return postService.savePostContent(tUser.getId(),content,plateId,imgUrls,imgHttpUrls,userIds);
	}

	//删除发帖内容
	@RequestMapping(value = "deletePostContent", method = RequestMethod.GET)
	@ResponseBody
	public int deletePostContent(String id){
		return postService.deletePostContent(id);
	}
}