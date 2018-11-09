package com.yhcrt.weihu.plug.weixin.action.directive;

import static com.yhcrt.weihu.common.web.freemarker.DirectiveUtils.OUT_BEAN;
import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.yhcrt.weihu.common.web.freemarker.DirectiveUtils;
import com.yhcrt.weihu.core.entity.CmsSite;
import com.yhcrt.weihu.core.web.util.FrontUtils;
import com.yhcrt.weihu.plug.weixin.entity.Weixin;
import com.yhcrt.weihu.plug.weixin.manager.WeixinMng;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class WeixinDirective implements TemplateDirectiveModel {

	@SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		CmsSite site = FrontUtils.getSite(env);
		Weixin entity = manager.find(site.getId());

		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(
				params);
		paramWrap.put(OUT_BEAN, DEFAULT_WRAPPER.wrap(entity));
		Map<String, TemplateModel> origMap = DirectiveUtils
				.addParamsToVariable(env, paramWrap);
		body.render(env.getOut());
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
	}
	
	@Autowired
	private WeixinMng manager;
}