package com.yhcrt.weihu.cms.action.front;

import static com.yhcrt.weihu.cms.Constants.TPLDIR_CSI;
import static com.yhcrt.weihu.cms.Constants.TPLDIR_SPECIAL;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// import com.octo.captcha.service.image.ImageCaptchaService;
import com.yhcrt.weihu.cms.entity.assist.CmsBaoLiao;
import com.yhcrt.weihu.cms.entity.assist.CmsComment;
import com.yhcrt.weihu.cms.entity.main.ChannelExt;
import com.yhcrt.weihu.cms.entity.main.Content;
import com.yhcrt.weihu.cms.entity.main.ContentDoc;
import com.yhcrt.weihu.cms.manager.assist.CmsBaoLiaoMng;
import com.yhcrt.weihu.cms.manager.assist.CmsCommentMng;
import com.yhcrt.weihu.cms.manager.main.ContentCountMng;
import com.yhcrt.weihu.cms.manager.main.ContentDocMng;
import com.yhcrt.weihu.cms.manager.main.ContentMng;
import com.yhcrt.weihu.common.web.RequestUtils;
import com.yhcrt.weihu.common.web.ResponseUtils;
// import com.yhcrt.weihu.common.web.session.SessionProvider;
import com.yhcrt.weihu.core.entity.CmsSite;
import com.yhcrt.weihu.core.entity.CmsUser;
import com.yhcrt.weihu.core.web.util.CmsUtils;
import com.yhcrt.weihu.core.web.util.FrontUtils;

@Controller
public class CommentAct {
    private static final Logger log = LoggerFactory.getLogger(CommentAct.class);

    public static final String COMMENT_PAGE = "tpl.commentPage";

    public static final String COMMENT_LIST = "tpl.commentList";

    public static final String BAOLIAOCOMMT_PAGE = "tpl.baoliaocommtPage";

    public static final String BAOLIAOCOMMT_LIST = "tpl.baoliaocommtList";

    // 新闻评论分页
    @RequestMapping(value = "/comment*.jspx", method = RequestMethod.GET)
    public String page(Integer contentId, Integer pageNo, HttpServletRequest request, HttpServletResponse response,
	    ModelMap model) {
	CmsSite site = CmsUtils.getSite(request);
	if (contentId == null) {
	    return FrontUtils.showMessage(request, model, "comment.contentNotFound");
	}
	Content content = contentMng.findById(contentId);
	if (content == null) {
	    return FrontUtils.showMessage(request, model, "comment.contentNotFound");
	}
	if (content.getChannel().getCommentControl() == ChannelExt.COMMENT_OFF) {
	    return FrontUtils.showMessage(request, model, "comment.closed");
	}
	// 将request中所有参数保存至model中。
	model.putAll(RequestUtils.getQueryParams(request));
	FrontUtils.frontData(request, model, site);
	FrontUtils.frontPageData(request, model);
	model.addAttribute("content", content);
	return FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_SPECIAL, COMMENT_PAGE);
    }

    // 新闻评论列表
    @RequestMapping(value = "/comment_list.jspx")
    public String list(Integer siteId, Integer contentId, Integer commentId, Integer greatTo, Integer recommend,
	    Integer checked, Integer orderBy, Integer count, HttpServletRequest request, HttpServletResponse response,
	    ModelMap model) {

	CmsComment parent = cmsCommentMng.findById(commentId);
	List<CmsComment> list = cmsCommentMng.getChildList(commentId, true);
	// getListForTag(siteId, contentId,greatTo, chk, rec, desc, count);
	// 将request中所有参数
	model.putAll(RequestUtils.getQueryParams(request));
	model.addAttribute("parent", parent);
	model.addAttribute("list", list);
	CmsSite site = CmsUtils.getSite(request);
	FrontUtils.frontData(request, model, site);
	return FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_CSI, COMMENT_LIST);
    }

    // 提交新闻评论
    @RequestMapping(value = "/comment.jspx", method = RequestMethod.POST)
    public void submit(Integer pid, Integer contentId, Integer score, String text, String sessionId,
	    HttpServletRequest request, HttpServletResponse response, ModelMap model)
	    throws JSONException, IOException {
	CmsSite site = CmsUtils.getSite(request);
	CmsUser user = CmsUtils.getUser(request);
	JSONObject json = new JSONObject();

	Content content = contentMng.findById(contentId);

	boolean checked = false;
	Integer userId = null;
	if (user != null) {
	    checked = !user.getGroup().getNeedCheck();
	    userId = user.getId();
	}
	ContentDoc doc = content.getContentDoc();
	if (doc != null) {
	    doc.setAvgScore(getNewAvgScore(content, score));
	    contentDocMng.update(doc, content);
	}
	String str = text.replaceAll("&lt;","<");
	String str1 = str.replaceAll("&gt;",">");
	cmsCommentMng.comment(pid, score, str1, RequestUtils.getIpAddr(request), contentId, site.getId(), userId,
		checked, false);
	json.put("success", true);
	json.put("status", 0);
	log.info("");
	ResponseUtils.renderJson(response, json.toString());
    }

    // 回复新闻评论
    @RequestMapping(value = "/comment_reply.jspx", method = RequestMethod.POST)
    public void comt_reply(Integer pid, Integer contentId, Integer score, String text, HttpServletRequest request,
	    HttpServletResponse response) throws JSONException, IOException {

	CmsComment comment1 = cmsCommentMng.findById(pid);

	CmsSite site = CmsUtils.getSite(request);
	CmsUser user = CmsUtils.getUser(request);
	JSONObject json = new JSONObject();

	Content content = comment1.getContent();

	boolean checked = false;
	Integer userId = null;
	if (user != null) {
	    checked = !user.getGroup().getNeedCheck();
	    userId = user.getId();
	}
	ContentDoc doc = content.getContentDoc();
	if (doc != null) {
	    doc.setAvgScore(getNewAvgScore(content, score));
	    contentDocMng.update(doc, content);
	}
	String str = text.replaceAll("&lt;","<");
	String str1 = str.replaceAll("&gt;",">");
	cmsCommentMng.comment(pid, score, str1, RequestUtils.getIpAddr(request), content.getId(), site.getId(), userId,
		checked, false);
	json.put("success", true);
	json.put("status", 0);
	ResponseUtils.renderJson(response, json.toString());
    }

    // 报料评论分页
    @RequestMapping(value = "/baoliaocommt*.jspx", method = RequestMethod.GET)
    public String baoliaoPage(Integer baoliaoId, Integer pageNo, HttpServletRequest request,
	    HttpServletResponse response, ModelMap model) {
	CmsSite site = CmsUtils.getSite(request);
	if (baoliaoId == null) {
	    return FrontUtils.showMessage(request, model, "comment.contentNotFound");
	}
	CmsBaoLiao baoliao = baoliaoMng.findById(baoliaoId);
	if (baoliao == null) {
	    return FrontUtils.showMessage(request, model, "comment.contentNotFound");
	}

	// 将request中所有参数保存至model中。
	model.putAll(RequestUtils.getQueryParams(request));
	FrontUtils.frontData(request, model, site);
	FrontUtils.frontPageData(request, model);
	model.addAttribute("baoliao", baoliao);
	return FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_SPECIAL, BAOLIAOCOMMT_PAGE);
    }

    // 报料评论列表
    @RequestMapping(value = "/baoliaocommt_list.jspx")
    public String baoliaoList(Integer siteId, Integer baoliaoId, Integer greatTo, Integer recommend, Integer checked,
	    Integer orderBy, Integer count, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
	if (count == null || count <= 0 || count > 200) {
	    count = 200;
	}
	boolean desc;
	if (orderBy == null || orderBy == 0) {
	    desc = true;
	} else {
	    desc = false;
	}
	Boolean rec;
	if (recommend != null) {
	    rec = recommend != 0;
	} else {
	    rec = null;
	}
	Boolean chk;
	if (checked != null) {
	    chk = checked != 0;
	} else {
	    chk = null;
	}
	CmsBaoLiao baoliao = baoliaoMng.findById(baoliaoId);
	List<CmsComment> list = cmsCommentMng.getBaoliaoList(siteId, baoliaoId, greatTo, chk, rec, desc, count);
	// 将request中所有参数
	model.putAll(RequestUtils.getQueryParams(request));
	model.addAttribute("list", list);
	model.addAttribute("baoliao", baoliao);
	CmsSite site = CmsUtils.getSite(request);
	FrontUtils.frontData(request, model, site);
	return FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_CSI, BAOLIAOCOMMT_LIST);
    }

    /**
     * 提交评论报料
     * 
     * @param pid
     * @param baoliaoId
     * @param score
     * @param text
     * @param sessionId
     * @param request
     * @param response
     * @param model
     * @throws JSONException
     * @throws IOException
     */
    @RequestMapping(value = "/baoliaocommt.jspx", method = RequestMethod.POST)
    public void baoliaoSubmit(Integer pid, Integer baoliaoId, Integer score, String text, String sessionId,
	    HttpServletRequest request, HttpServletResponse response, ModelMap model)
	    throws JSONException, IOException {
	CmsSite site = CmsUtils.getSite(request);
	CmsUser user = CmsUtils.getUser(request);
	JSONObject json = new JSONObject();
	CmsBaoLiao baoliao = baoliaoMng.findById(baoliaoId);
	if (baoliao == null) {
	    json.put("success", false);
	    json.put("status", 1);
	}
	baoliao.setUser(baoliao.getUser());
	boolean checked = false;
	Integer userId = null;
	if (user != null) {
	    checked = !user.getGroup().getNeedCheck();
	    userId = user.getId();
	}
	String str = text.replaceAll("&lt;","<");
	String str1 = str.replaceAll("&gt;",">");
	cmsCommentMng.commentByBaoliao(pid, score, str1, RequestUtils.getIpAddr(request), baoliao, site.getId(), userId,
		checked, false);
	if (baoliao.getCommtCount() != null) {
		
	    baoliao.setCommtCount(baoliao.getCommtCount() + 1);
	} else {
	    baoliao.setCommtCount(1);
	}
	
	baoliaoMng.update(baoliao);
	json.put("success", true);
	json.put("status", 0);
	ResponseUtils.renderJson(response, json.toString());
    }

    /**
     * 回复评论
     */
    @RequestMapping(value = "/blcommt_reply.jspx", method = RequestMethod.POST)
    public void blcomt_reply(Integer pid, Integer baoliaoId, Integer score, String text, HttpServletRequest request,
	    HttpServletResponse response) throws JSONException, IOException {
	CmsSite site = CmsUtils.getSite(request);
	CmsUser user = CmsUtils.getUser(request);
	CmsBaoLiao baoliao = baoliaoMng.findById(baoliaoId);
	JSONObject json = new JSONObject();
	boolean checked = false;
	Integer userId = null;
	if (user != null) {
	    checked = !user.getGroup().getNeedCheck();
	    userId = user.getId();
	}
	String str = text.replaceAll("&lt;","<");
	String str1 = str.replaceAll("&gt;",">");
	cmsCommentMng.commentByBaoliao(pid, score, str1, RequestUtils.getIpAddr(request), baoliao, site.getId(), userId,
		checked, false);
	if (baoliao.getCommtCount() != null) {
	    baoliao.setCommtCount(baoliao.getCommtCount() + 1);
	} else {
	    baoliao.setCommtCount(1);
	}
	baoliaoMng.update(baoliao);
	json.put("success", true);
	json.put("status", 0);
	ResponseUtils.renderJson(response, json.toString());
    }

    /**
     * 新闻/报料评论点赞
     * 
     * @param commentId
     * @param request
     * @param response
     */

    @RequestMapping(value = "/comment_up.jspx")
    public void up(Integer commentId, HttpServletRequest request, HttpServletResponse response) {
	if (commentId != null) {
	    cmsCommentMng.ups(commentId);
	    ResponseUtils.renderJson(response, "true");
	} else {
	    ResponseUtils.renderJson(response, "false");
	}
    }

    @RequestMapping(value = "/comment_down.jspx")
    public void down(Integer commentId, HttpServletRequest request, HttpServletResponse response) {
	if (commentId != null) {
	    cmsCommentMng.downs(commentId);
	    ResponseUtils.renderJson(response, "true");
	} else {
	    ResponseUtils.renderJson(response, "false");
	}
    }

    private Float getNewAvgScore(Content content, Integer score) {
	Integer commentCount = content.getCommentsCount();
	Float sumScore = content.getAvgScore() * commentCount;
	sumScore += score;
	return sumScore / (commentCount + 1);
    }

    public boolean hasCommented(CmsUser user, Content content) {
	if (content.hasCommentUser(user)) {
	    return true;
	} else {
	    return false;
	}
    }

    @Autowired
    private CmsCommentMng cmsCommentMng;

    @Autowired
    private ContentMng contentMng;

    @Autowired
    private CmsBaoLiaoMng baoliaoMng;

    @Autowired
    private ContentDocMng contentDocMng;
    @Autowired
    private ContentCountMng contentCountMng;
    // @Autowired
    // private SessionProvider session;

    // @Autowired
    // private ImageCaptchaService imageCaptchaService;
}
