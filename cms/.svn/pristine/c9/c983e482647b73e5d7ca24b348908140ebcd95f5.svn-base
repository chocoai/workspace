package com.yhcrt.weihu.cms.action.directive;

import static com.yhcrt.weihu.common.web.freemarker.DirectiveUtils.OUT_LIST;
import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.yhcrt.weihu.cms.entity.assist.CmsAdvertising;
import com.yhcrt.weihu.cms.manager.assist.CmsAdvertisingMng;
import com.yhcrt.weihu.common.web.freemarker.DirectiveUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 广告对象标签
 */
public class CmsAdvertisingListDirective implements TemplateDirectiveModel {
	/**
	 * 输入参数，广告ID。
	 */
	public static final String PARAM_SPACEID = "spaceId";
	
	public static final String PARAM_ENABLED = "enabled";
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		Integer spaceId = getSpaceId(params);
		Boolean enabled = getEnabled(params);
		if (enabled == null) {
			enabled = true;
		}
		List<CmsAdvertising> list = cmsAdvertisingMng.getList(spaceId,enabled);

		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(
				params);
		paramWrap.put(OUT_LIST, DEFAULT_WRAPPER.wrap(list));
		Map<String, TemplateModel> origMap = DirectiveUtils
				.addParamsToVariable(env, paramWrap);
		body.render(env.getOut());
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
	}

	private Integer getSpaceId(Map<String, TemplateModel> params)
			throws TemplateException {
		return DirectiveUtils.getInt(PARAM_SPACEID, params);
	}

	private Boolean getEnabled(Map<String, TemplateModel> params)
			throws TemplateException {
		return DirectiveUtils.getBool(PARAM_ENABLED, params);
	}
	@Autowired
	private CmsAdvertisingMng cmsAdvertisingMng;
}
