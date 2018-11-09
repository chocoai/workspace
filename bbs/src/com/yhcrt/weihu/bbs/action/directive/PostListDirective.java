package com.yhcrt.weihu.bbs.action.directive;

import static com.yhcrt.weihu.common.web.freemarker.DirectiveUtils.OUT_LIST;
import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.yhcrt.weihu.bbs.entity.BbsPost;
import com.yhcrt.weihu.bbs.manager.BbsPostMng;
import com.yhcrt.weihu.bbs.web.FrontUtils;
import com.yhcrt.weihu.common.web.freemarker.DirectiveUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;


public class PostListDirective implements TemplateDirectiveModel {
	
	/**
	 * 输入参数，主题ID。
	 */
	public static final String PARAM_TOPIC_ID = "topicId";

	@SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		List<BbsPost> list = bbsPostMng.getPostByTopic(getTopicId(params),null,0,FrontUtils.getCount(params));
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(params);
		paramWrap.put(OUT_LIST, DEFAULT_WRAPPER.wrap(list));
		Map<String, TemplateModel> origMap = DirectiveUtils
				.addParamsToVariable(env, paramWrap);
		body.render(env.getOut());
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
	}
	
	protected Integer getTopicId(Map<String, TemplateModel> params)
			throws TemplateException {
		return DirectiveUtils.getInt(PARAM_TOPIC_ID, params);
	}

	@Autowired
	protected BbsPostMng bbsPostMng;
}
