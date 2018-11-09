package com.yhcrt.weihu.cms.action.front;

import static com.yhcrt.weihu.cms.Constants.TPLDIR_TOPIC;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yhcrt.weihu.cms.entity.main.Channel;
import com.yhcrt.weihu.cms.entity.main.CmsTopic;
import com.yhcrt.weihu.cms.entity.main.TopicChannel;
import com.yhcrt.weihu.cms.manager.main.ChannelMng;
import com.yhcrt.weihu.cms.manager.main.CmsTopicChannelMng;
import com.yhcrt.weihu.cms.manager.main.CmsTopicMng;
import com.yhcrt.weihu.core.entity.CmsSite;
import com.yhcrt.weihu.core.web.util.CmsUtils;
import com.yhcrt.weihu.core.web.util.FrontUtils;

@Controller
public class TopicAct {

    public static final String TOPIC_INDEX = "tpl.topicIndex";

    public static final String TOPIC_CHANNEL = "tpl.topicChannel";

    public static final String TOPIC_DEFAULT = "tpl.topicDefault";
    
    public static final String TOPIC_LIST = "tpl.topicList";

    @RequestMapping(value = "/topic*.jspx", method = RequestMethod.GET)
    public String index(Integer channelId, Integer topicId, HttpServletRequest request, HttpServletResponse response,
	    ModelMap model) {
	// 全部？按站点？按栏目？要不同模板？
	CmsSite site = CmsUtils.getSite(request);
	FrontUtils.frontData(request, model, site);
	FrontUtils.frontPageData(request, model);
	if (topicId != null) {
	    CmsTopic topic = cmsTopicMng.findById(topicId);
	    if (topic == null) {
		return FrontUtils.pageNotFound(request, response, model);
	    }
	    model.addAttribute("topic", topic);
	    String tpl = topic.getTplContent();
	    if (StringUtils.isBlank(tpl)) {
		tpl = FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_TOPIC, TOPIC_DEFAULT);
	    }
	    return tpl;
	} else if (channelId != null) {
	    Channel channel = channelMng.findById(channelId);
	    if (channel != null) {
		model.addAttribute("channel", channel);
	    } else {
		return FrontUtils.pageNotFound(request, response, model);
	    }
	    return FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_TOPIC, TOPIC_CHANNEL);
	} else {
	    return FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_TOPIC, TOPIC_INDEX);
	}
    }

    @RequestMapping(value = "/topic/{id}.jspx", method = RequestMethod.GET)
    public String topic(@PathVariable String id, HttpServletRequest request, HttpServletResponse response,
	    ModelMap model) {
	CmsSite site = CmsUtils.getSite(request);
	if (id == null) {
	    return FrontUtils.pageNotFound(request, response, model);
	}
	Integer topicId = null;
	try {
	    topicId = Integer.parseInt(id);
	} catch (Exception e) {
	    return FrontUtils.pageNotFound(request, response, model);
	}
	CmsTopic topic = null;
	if (topicId != null) {
	    topic = cmsTopicMng.findById(topicId);
	}
	if (topic == null) {
	    return FrontUtils.pageNotFound(request, response, model);
	}
	List<TopicChannel> topicChannels = cmsTopicChannelMng.getListByTopic(topicId);
	model.addAttribute("topic", topic);
	model.addAttribute("topicChannels", topicChannels);
	String tpl = topic.getTplContent();
	if (StringUtils.isBlank(tpl)) {
	    tpl = FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_TOPIC, TOPIC_DEFAULT);
	}
	//拦截到是手机访问就直接跳转到手机模板
    String equipment=(String) request.getAttribute("ua");
    if(StringUtils.isNotBlank(equipment)&&equipment.equals("mobile")){
	    tpl = "/WEB-INF/t/cms/www/mobile/topic/topic_news.html";
	}
	FrontUtils.frontData(request, model, site);
	FrontUtils.frontPageData(request, model);
	return tpl;
    }
    
    @RequestMapping(value = "/topic/getList.jsp", method = RequestMethod.GET)
    public String getList(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
	CmsSite site = CmsUtils.getSite(request);
	String id = request.getParameter("topicId");
	String ChannelId = request.getParameter("topicChannelId");
	if (StringUtils.isBlank(id)) {
	    return FrontUtils.pageNotFound(request, response, model);
	}
	Integer topicId = null;
	try {
	    topicId = Integer.parseInt(id);
	} catch (Exception e) {
	    return FrontUtils.pageNotFound(request, response, model);
	}
	CmsTopic topic = null;
	if (topicId != null) {
	    topic = cmsTopicMng.findById(topicId);
	}
	if (topic == null) {
	    return FrontUtils.pageNotFound(request, response, model);
	}
	Integer topicChannelId = null;
	if (StringUtils.isNotBlank(ChannelId)) {
	    topicChannelId = Integer.parseInt(ChannelId);
	    TopicChannel topicChannel = cmsTopicChannelMng.findById(topicChannelId);
	    model.addAttribute("topicChannel", topicChannel);
	}
	
	model.addAttribute("topic", topic);
	model.addAttribute("topicId", topicId);
	model.addAttribute("topicChannelId", topicChannelId);
	
	String tpl = FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_TOPIC, TOPIC_LIST);
	FrontUtils.frontData(request, model, site);
	FrontUtils.frontPageData(request, model);
	return tpl;
    }

    @Autowired
    private CmsTopicMng cmsTopicMng;

    @Autowired
    private ChannelMng channelMng;

    @Autowired
    private CmsTopicChannelMng cmsTopicChannelMng;
}
